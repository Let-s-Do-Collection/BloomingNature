package net.satisfy.bloomingnature.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;

public class FireflyParticle extends TextureSheetParticle {
    private static final int FULL_BRIGHT = 15728880;
    private static final double WANDER = 0.003;
    private static final double MAX_SPEED = 0.02;

    private final SpriteSet sprites;
    private final float pulseOffset;

    protected FireflyParticle(ClientLevel level, double x, double y, double z, SpriteSet sprites) {
        super(level, x, y, z);
        this.sprites = sprites;
        this.lifetime = 100 + this.random.nextInt(80);
        this.gravity = 0.0F;
        this.hasPhysics = false;
        this.quadSize = 0.04F + this.random.nextFloat() * 0.02F;
        this.pulseOffset = this.random.nextFloat() * Mth.TWO_PI;
        this.xd = (this.random.nextDouble() - 0.5) * MAX_SPEED;
        this.yd = (this.random.nextDouble() - 0.5) * MAX_SPEED * 0.5;
        this.zd = (this.random.nextDouble() - 0.5) * MAX_SPEED;
        this.alpha = 0.0F;
        this.setSpriteFromAge(sprites);
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
            return;
        }
        this.xd = Mth.clamp(this.xd + (this.random.nextDouble() - 0.5) * WANDER, -MAX_SPEED, MAX_SPEED);
        this.yd = Mth.clamp(this.yd + (this.random.nextDouble() - 0.5) * WANDER * 0.5, -MAX_SPEED * 0.5, MAX_SPEED * 0.5);
        this.zd = Mth.clamp(this.zd + (this.random.nextDouble() - 0.5) * WANDER, -MAX_SPEED, MAX_SPEED);
        this.move(this.xd, this.yd, this.zd);
        float fade = Math.min(1.0F, Math.min(this.age, this.lifetime - this.age) / 20.0F);
        float pulse = 0.55F + 0.45F * Mth.sin(this.age * 0.15F + this.pulseOffset);
        this.alpha = fade * pulse;
        this.setSpriteFromAge(this.sprites);
    }

    @Override
    protected int getLightColor(float partialTick) {
        return FULL_BRIGHT;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new FireflyParticle(level, x, y, z, this.sprites);
        }
    }
}
