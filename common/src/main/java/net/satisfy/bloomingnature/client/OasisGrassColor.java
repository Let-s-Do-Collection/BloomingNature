package net.satisfy.bloomingnature.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.BlockAndTintGetter;
import net.satisfy.bloomingnature.core.world.biome.BloomingNatureBiomeKeys;
import net.satisfy.bloomingnature.core.world.biome.SurfaceNoise;

@Environment(EnvType.CLIENT)
public final class OasisGrassColor {
    private static final int LUSH = 0xA9C964;
    private static final int DRY = 0xE3DC86;

    private OasisGrassColor() {
    }

    public static int get(BlockAndTintGetter world, BlockPos pos) {
        var level = Minecraft.getInstance().level;
        if (level == null || !level.getBiome(pos).is(BloomingNatureBiomeKeys.DESERT_OASIS)) {
            return BiomeColors.getAverageGrassColor(world, pos);
        }
        float coarse = SurfaceNoise.smooth(pos.getX() + 509, pos.getZ() - 377, 0.04f);
        float fine = SurfaceNoise.smooth(pos.getX() - 91, pos.getZ() + 233, 0.15f);
        float t = Mth.clamp((coarse * 0.75f + fine * 0.25f - 0.3f) / 0.4f, 0.0f, 1.0f);
        return lerpColor(LUSH, DRY, t);
    }

    private static int lerpColor(int a, int b, float t) {
        int r = Mth.lerpInt(t, a >> 16 & 0xFF, b >> 16 & 0xFF);
        int g = Mth.lerpInt(t, a >> 8 & 0xFF, b >> 8 & 0xFF);
        int bl = Mth.lerpInt(t, a & 0xFF, b & 0xFF);
        return r << 16 | g << 8 | bl;
    }
}
