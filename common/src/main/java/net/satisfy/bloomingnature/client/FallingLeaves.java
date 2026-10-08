package net.satisfy.bloomingnature.client;

import dev.architectury.event.events.client.ClientTickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.satisfy.bloomingnature.BloomingNature;
import net.satisfy.bloomingnature.client.particle.FallingLeafParticle;
import net.satisfy.bloomingnature.core.config.BloomingNatureConfig;

public final class FallingLeaves {
    public static final TagKey<Block> DROPS_LEAVES = TagKey.create(Registries.BLOCK, BloomingNature.identifier("drops_falling_leaves"));
    private static final int RANGE = 20;
    private static final int VERTICAL_RANGE = 14;
    private static final int SAMPLES = 500;
    private static final float BASE_CHANCE = 0.2F;
    private static boolean warned;

    private FallingLeaves() {
    }

    public static void init() {
        ClientTickEvent.CLIENT_POST.register(FallingLeaves::tick);
    }

    private static void tick(Minecraft minecraft) {
        ClientLevel level = minecraft.level;
        if (level == null || minecraft.player == null || minecraft.isPaused()) {
            return;
        }
        Wind.tick(level);
        SpriteSet sprites = FallingLeafParticle.Provider.sprites();
        if (!BloomingNatureConfig.fallingLeavesEnabled) {
            return;
        }
        if (sprites == null) {
            if (!warned) {
                warned = true;
                BloomingNature.LOGGER.warn("Falling leaf particle provider was never created, falling leaves are disabled");
            }
            return;
        }
        RandomSource random = level.random;
        float gust = Wind.strength();
        float chance = BASE_CHANCE * (0.5F + gust * 0.5F) * BloomingNatureConfig.fallingLeavesDensity / 100.0F;
        BlockPos center = minecraft.player.blockPosition();
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos below = new BlockPos.MutableBlockPos();
        for (int i = 0; i < SAMPLES; i++) {
            pos.set(center.getX() + random.nextInt(RANGE * 2 + 1) - RANGE, center.getY() + random.nextInt(VERTICAL_RANGE * 2 + 1) - VERTICAL_RANGE, center.getZ() + random.nextInt(RANGE * 2 + 1) - RANGE);
            BlockState state = level.getBlockState(pos);
            if (!state.is(DROPS_LEAVES) || state.hasProperty(LeavesBlock.PERSISTENT) && state.getValue(LeavesBlock.PERSISTENT) || !level.getBlockState(below.setWithOffset(pos, 0, -1, 0)).isAir() || random.nextFloat() >= chance) {
                continue;
            }
            float exposure = level.canSeeSky(below) ? 1.0F : 0.3F;
            FallingLeafParticle particle = new FallingLeafParticle(level, pos.getX() + 0.1D + random.nextDouble() * 0.8D, pos.getY() - 0.08D, pos.getZ() + 0.1D + random.nextDouble() * 0.8D, leafColor(minecraft, level, state, pos), exposure);
            particle.pickSprite(sprites);
            minecraft.particleEngine.add(particle);
        }
    }

    private static int leafColor(Minecraft minecraft, ClientLevel level, BlockState state, BlockPos pos) {
        int color = minecraft.getBlockColors().getColor(state, level, pos, 0);
        return color != -1 ? color : state.getMapColor(level, pos).col;
    }
}
