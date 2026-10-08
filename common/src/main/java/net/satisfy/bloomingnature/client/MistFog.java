package net.satisfy.bloomingnature.client;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.levelgen.Heightmap;
import net.satisfy.bloomingnature.BloomingNature;
import net.satisfy.bloomingnature.core.config.BloomingNatureConfig;
import net.satisfy.foundation.client.fog.BiomeFog;

public final class MistFog {
    private static final long DAY_LENGTH = 24000L;
    private static final long RISE_START = 20000L;
    private static final long FULL_START = 22500L;
    private static final long FULL_END = 500L;
    private static final long FADE_END = 3500L;
    private static final float THUNDER_THICKENING = 0.3F;
    private static final int MIN_SKY_LIGHT = 4;
    private static final int FULL_SKY_LIGHT = 12;
    private static final long NIGHT_START = 12000L;
    private static final long NIGHT_FULL_START = 14000L;
    private static final int HOLLOW_SAMPLES = 8;
    private static final int HOLLOW_RADIUS = 14;
    private static final int HOLLOW_MAX_HEIGHT_ABOVE_GROUND = 6;
    private static final float HOLLOW_MIN_DEPTH = 1.0F;
    private static final float HOLLOW_FULL_DEPTH = 5.0F;

    private MistFog() {
    }

    public static void init() {
        BiomeFog.register(BloomingNature.identifier("mist"), MistFog::fog);
    }

    private static BiomeFog.Fog fog(ClientLevel level, BlockPos cameraPos, float renderDistance) {
        if (!BloomingNatureConfig.fogEnabled || !isFoggyBiome(level, cameraPos)) {
            return null;
        }
        float morning = BloomingNatureConfig.fogMorning ? morningStrength(level.getDayTime() % DAY_LENGTH) : 0.0F;
        float start = renderDistance * BloomingNatureConfig.fogStartPercent / 100.0F;
        float clearEnd = Mth.lerp(morning, renderDistance, renderDistance * BloomingNatureConfig.fogMorningEndPercent / 100.0F);
        float end = clearEnd;
        float rain = BloomingNatureConfig.fogRain ? level.getRainLevel(1.0F) : 0.0F;
        if (rain > 0.0F) {
            float rainEnd = renderDistance * BloomingNatureConfig.fogRainEndPercent / 100.0F * (1.0F - THUNDER_THICKENING * level.getThunderLevel(1.0F));
            end = Mth.lerp(rain, clearEnd, Math.min(rainEnd, clearEnd));
        }
        if (BloomingNatureConfig.fogHollows) {
            float hollow = hollowDepth(level, cameraPos) * Math.max(morning, nightStrength(level.getDayTime() % DAY_LENGTH));
            end = Math.min(end, Mth.lerp(hollow, renderDistance, renderDistance * BloomingNatureConfig.fogHollowEndPercent / 100.0F));
        }
        end = Mth.lerp(skyExposure(level, cameraPos), renderDistance, end);
        return end < renderDistance - 1.0F ? fog(start, end) : null;
    }

    private static float hollowDepth(ClientLevel level, BlockPos camera) {
        int ground = level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, camera.getX(), camera.getZ());
        if (camera.getY() - ground > HOLLOW_MAX_HEIGHT_ABOVE_GROUND) {
            return 0.0F;
        }
        int total = 0;
        for (int i = 0; i < HOLLOW_SAMPLES; i++) {
            float angle = i * Mth.TWO_PI / HOLLOW_SAMPLES;
            int x = camera.getX() + Mth.floor(Mth.cos(angle) * HOLLOW_RADIUS);
            int z = camera.getZ() + Mth.floor(Mth.sin(angle) * HOLLOW_RADIUS);
            total += level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, x, z);
        }
        float depth = total / (float) HOLLOW_SAMPLES - ground;
        return smooth((depth - HOLLOW_MIN_DEPTH) / (HOLLOW_FULL_DEPTH - HOLLOW_MIN_DEPTH));
    }

    private static float nightStrength(long time) {
        if (time >= NIGHT_FULL_START && time < FULL_START) {
            return 1.0F;
        }
        if (time >= NIGHT_START && time < NIGHT_FULL_START) {
            return smooth((time - NIGHT_START) / (float) (NIGHT_FULL_START - NIGHT_START));
        }
        return 0.0F;
    }

    private static float skyExposure(ClientLevel level, BlockPos pos) {
        return smooth((level.getBrightness(LightLayer.SKY, pos) - MIN_SKY_LIGHT) / (float) (FULL_SKY_LIGHT - MIN_SKY_LIGHT));
    }

    private static float morningStrength(long time) {
        if (time >= FULL_START || time <= FULL_END) {
            return 1.0F;
        }
        if (time >= RISE_START) {
            return smooth((time - RISE_START) / (float) (FULL_START - RISE_START));
        }
        if (time < FADE_END) {
            return smooth(1.0F - (time - FULL_END) / (float) (FADE_END - FULL_END));
        }
        return 0.0F;
    }

    private static float smooth(float value) {
        float clamped = Mth.clamp(value, 0.0F, 1.0F);
        return clamped * clamped * (3.0F - 2.0F * clamped);
    }

    private static BiomeFog.Fog fog(float start, float end) {
        return new BiomeFog.Fog(Math.min(start, end - 1.0F), end);
    }

    private static boolean isFoggyBiome(ClientLevel level, BlockPos pos) {
        return level.getBiome(pos).unwrapKey().map(key -> BloomingNatureConfig.fogBiomes.getOrDefault(key.location().toString(), false)).orElse(false);
    }
}
