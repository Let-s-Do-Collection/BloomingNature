package net.satisfy.bloomingnature.client.particle;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.satisfy.bloomingnature.client.Wind;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;

public class FallingLeafParticle extends TextureSheetParticle {
    private static final int FADE = 20;

    private final boolean tumbling;
    private final float swingSpeed;
    private final float swingAmount;
    private final float sinkSpeed;
    private final float windResponse;
    private float swingPhase;
    private float yaw;
    private float oYaw;
    private float tilt;
    private float oTilt;
    private float spin;
    private float oSpin;
    private float spinSpeed;
    private float exposure;
    private boolean landed;
    private boolean floating;
    private int restTime;

    public FallingLeafParticle(ClientLevel level, double x, double y, double z, int color, float exposure) {
        super(level, x, y, z);
        this.gravity = 0.0F;
        this.friction = 1.0F;
        this.hasPhysics = true;
        this.lifetime = 600;
        this.exposure = exposure;
        this.tumbling = random.nextFloat() < 0.22F;
        this.swingSpeed = 0.11F + random.nextFloat() * 0.07F;
        this.swingAmount = 0.035F + random.nextFloat() * 0.03F;
        this.sinkSpeed = 0.022F + random.nextFloat() * 0.014F;
        this.windResponse = 0.035F + random.nextFloat() * 0.03F;
        this.swingPhase = random.nextFloat() * Mth.TWO_PI;
        this.yaw = random.nextFloat() * Mth.TWO_PI;
        this.spin = random.nextFloat() * Mth.TWO_PI;
        this.spinSpeed = (0.12F + random.nextFloat() * 0.18F) * (random.nextBoolean() ? 1.0F : -1.0F);
        this.oYaw = yaw;
        this.oSpin = spin;
        this.quadSize = 0.11F + random.nextFloat() * 0.05F;
        this.restTime = 80 + random.nextInt(120);
        this.alpha = 0.0F;
        this.xd = 0.0D;
        this.yd = -0.01D;
        this.zd = 0.0D;
        setSize(0.12F, 0.04F);
        setColor(color);
    }

    private void setColor(int color) {
        float shade = 0.9F + random.nextFloat() * 0.18F;
        float r = (color >> 16 & 255) / 255.0F;
        float g = (color >> 8 & 255) / 255.0F;
        float b = (color & 255) / 255.0F;
        if (random.nextFloat() < 0.12F) {
            float dry = 0.25F + random.nextFloat() * 0.35F;
            r = Mth.lerp(dry, r, 0.72F);
            g = Mth.lerp(dry, g, 0.56F);
            b = Mth.lerp(dry, b, 0.18F);
        }
        this.rCol = Math.min(1.0F, r * shade);
        this.gCol = Math.min(1.0F, g * shade);
        this.bCol = Math.min(1.0F, b * shade);
    }

    @Override
    public void tick() {
        xo = x;
        yo = y;
        zo = z;
        oYaw = yaw;
        oTilt = tilt;
        oSpin = spin;

        if (age++ >= lifetime) {
            remove();
            return;
        }
        if (age % 10 == 0) {
            float target = level.canSeeSky(BlockPos.containing(x, y + 1.0D, z)) ? 1.0F : 0.3F;
            exposure += (target - exposure) * 0.5F;
        }

        BlockPos pos = BlockPos.containing(x, y, z);
        FluidState fluid = level.getFluidState(pos);
        if (fluid.is(FluidTags.LAVA)) {
            remove();
            return;
        }
        if (!landed && fluid.is(FluidTags.WATER)) {
            float surface = pos.getY() + fluid.getHeight(level, pos);
            if (y <= surface) {
                land(true);
                y = surface;
            }
        }

        if (landed) {
            restTick(pos, fluid);
        } else {
            fallTick();
        }
        updateAlpha();
    }

    private void fallTick() {
        float windX = Wind.x() * exposure;
        float windZ = Wind.z() * exposure;
        float gust = Wind.strength() * exposure;

        swingPhase += swingSpeed * (1.0F + gust * 0.25F);
        float swing = Mth.sin(swingPhase);
        float headingX = Mth.sin(yaw);
        float headingZ = Mth.cos(yaw);

        float lateral = tumbling ? 0.0F : Mth.cos(swingPhase) * swingAmount;
        float fall = tumbling ? sinkSpeed * 1.6F : sinkSpeed * (0.45F + 1.1F * (1.0F - Math.abs(swing)));
        if (level.isRaining() && exposure > 0.5F) {
            fall *= 1.6F;
        }

        xd += (windX + headingX * lateral - xd) * windResponse * 2.0F;
        zd += (windZ + headingZ * lateral - zd) * windResponse * 2.0F;
        yd += (-fall + gust * 0.004F * Mth.sin(swingPhase * 0.37F) - yd) * 0.2F;

        move(xd, yd, zd);

        if (tumbling) {
            spin += spinSpeed * (1.0F + gust * 0.4F);
            tilt += (0.9F - tilt) * 0.1F;
            yaw += spinSpeed * 0.15F;
        } else {
            tilt = swing * 0.75F;
            spin += (Mth.sin(swingPhase * 0.5F) * 0.4F - spin) * 0.05F;
            yaw += (random.nextFloat() - 0.5F) * 0.04F + gust * 0.01F * Mth.sign(spinSpeed);
        }

        if (onGround) {
            land(false);
        }
    }

    private void land(boolean onWater) {
        landed = true;
        floating = onWater;
        xd *= 0.2D;
        zd *= 0.2D;
        yd = 0.0D;
        tilt = 0.0F;
        oTilt = 0.0F;
        lifetime = Math.min(lifetime, age + restTime * (onWater ? 2 : 1));
    }

    private void restTick(BlockPos pos, FluidState fluid) {
        if (floating) {
            if (!fluid.is(FluidTags.WATER)) {
                floating = false;
                landed = false;
                return;
            }
            Vec3 flow = fluid.getFlow(level, pos);
            xd += (flow.x * 0.04D + Wind.x() * exposure * 0.3D - xd) * 0.1D;
            zd += (flow.z * 0.04D + Wind.z() * exposure * 0.3D - zd) * 0.1D;
            y = pos.getY() + fluid.getHeight(level, pos);
            yaw += (float) (xd - zd) * 0.6F;
            move(xd, 0.0D, zd);
            return;
        }
        if (level.getBlockState(BlockPos.containing(x, y - 0.05D, z)).isAir()) {
            landed = false;
            return;
        }
        float gust = Wind.strength() * exposure;
        if (gust > 2.0F && random.nextFloat() < 0.01F) {
            landed = false;
            yd = 0.06D;
            return;
        }
        xd = 0.0D;
        zd = 0.0D;
    }

    private void updateAlpha() {
        if (age < 8) {
            alpha = age / 8.0F;
        } else {
            int remaining = lifetime - age;
            alpha = remaining < FADE ? remaining / (float) FADE : 1.0F;
        }
    }

    @Override
    public void render(@NotNull VertexConsumer buffer, Camera camera, float partialTicks) {
        Vec3 cameraPos = camera.getPosition();
        float px = (float) (Mth.lerp(partialTicks, xo, x) - cameraPos.x());
        float py = (float) (Mth.lerp(partialTicks, yo, y) - cameraPos.y());
        float pz = (float) (Mth.lerp(partialTicks, zo, z) - cameraPos.z());
        float currentYaw = Mth.lerp(partialTicks, oYaw, yaw);
        Quaternionf rotation = new Quaternionf().rotateY(currentYaw);
        if (landed) {
            py += 0.012F;
            rotation.rotateX(Mth.HALF_PI);
        } else {
            rotation.rotateX(Mth.HALF_PI - Mth.lerp(partialTicks, oTilt, tilt)).rotateZ(Mth.lerp(partialTicks, oSpin, spin));
        }
        renderRotatedQuad(buffer, rotation, px, py, pz, partialTicks);
        renderRotatedQuad(buffer, rotation.rotateY(Mth.PI), px, py, pz, partialTicks);
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private static SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            Provider.sprites = sprites;
        }

        public static @Nullable SpriteSet sprites() {
            return sprites;
        }

        @Override
        public @Nullable Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            FallingLeafParticle particle = new FallingLeafParticle(level, x, y, z, level.getBiome(BlockPos.containing(x, y, z)).value().getFoliageColor(), 1.0F);
            particle.pickSprite(Provider.sprites);
            return particle;
        }
    }
}
