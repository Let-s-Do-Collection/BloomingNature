package net.satisfy.bloomingnature.client;

import dev.architectury.event.events.client.ClientTickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.satisfy.bloomingnature.BloomingNature;
import net.satisfy.bloomingnature.core.registry.FloraRegistry;

public final class FireflyAmbience {
    private static final TagKey<Block> ATTRACTS_FIREFLIES = TagKey.create(Registries.BLOCK, BloomingNature.identifier("attracts_fireflies"));
    private static final int RANGE = 12;
    private static final int SAMPLES = 300;
    private static final int CHANCE = 6;
    private static final double SPREAD = 1.5;
    private static final long DAY_LENGTH = 24000L;
    private static final long NIGHT_START = 12500L;
    private static final long NIGHT_END = 23500L;

    private FireflyAmbience() {
    }

    public static void init() {
        ClientTickEvent.CLIENT_POST.register(FireflyAmbience::tick);
    }

    private static void tick(Minecraft minecraft) {
        Level level = minecraft.level;
        if (level == null || minecraft.player == null || minecraft.isPaused() || level.isRaining()) {
            return;
        }
        long time = level.getDayTime() % DAY_LENGTH;
        if (time <= NIGHT_START || time >= NIGHT_END) {
            return;
        }
        RandomSource random = level.random;
        BlockPos center = minecraft.player.blockPosition();
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        for (int i = 0; i < SAMPLES; i++) {
            pos.set(center.getX() + random.nextInt(RANGE * 2 + 1) - RANGE, center.getY() + random.nextInt(RANGE * 2 + 1) - RANGE, center.getZ() + random.nextInt(RANGE * 2 + 1) - RANGE);
            if (!level.getBlockState(pos).is(ATTRACTS_FIREFLIES) || random.nextInt(CHANCE) != 0) {
                continue;
            }
            level.addParticle(FloraRegistry.FIREFLY.get(),
                    pos.getX() + 0.5 + (random.nextDouble() - 0.5) * SPREAD * 2,
                    pos.getY() + random.nextDouble() * 0.8,
                    pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * SPREAD * 2,
                    0.0, 0.0, 0.0);
        }
    }
}
