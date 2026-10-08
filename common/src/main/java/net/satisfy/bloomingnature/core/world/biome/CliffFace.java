package net.satisfy.bloomingnature.core.world.biome;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.BlockColumn;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Heightmap;

public final class CliffFace {
    private static final int MIN_DROP = 3;

    public record Palette(BlockState primary, BlockState secondary, BlockState accent) {
        public static Palette of(BlockState primary, BlockState secondary, BlockState accent) {
            return new Palette(primary, secondary, accent);
        }
    }

    private CliffFace() {
    }

    public static void paint(ChunkAccess chunk, BlockColumn column, int x, int z, Palette palette) {
        if (palette == null) return;
        int localX = x & 15;
        int localZ = z & 15;
        int topY = chunk.getHeight(Heightmap.Types.WORLD_SURFACE_WG, localX, localZ);
        int lowest = Math.min(
                Math.min(height(chunk, localX - 1, localZ, topY), height(chunk, localX + 1, localZ, topY)),
                Math.min(height(chunk, localX, localZ - 1, topY), height(chunk, localX, localZ + 1, topY)));
        if (topY - lowest < MIN_DROP) return;

        for (int y = topY - 1; y > lowest; y--) {
            BlockState state = column.getBlock(y);
            if (!isTerrain(state)) continue;
            int r = SurfaceNoise.patchIndex(x, y >> 1, z);
            column.setBlock(y, r < 70 ? palette.primary() : r < 90 ? palette.secondary() : palette.accent());
        }
    }

    private static int height(ChunkAccess chunk, int localX, int localZ, int fallback) {
        if (localX < 0 || localX > 15 || localZ < 0 || localZ > 15) return fallback;
        return chunk.getHeight(Heightmap.Types.WORLD_SURFACE_WG, localX, localZ);
    }

    private static boolean isTerrain(BlockState state) {
        if (state.isAir() || !state.getFluidState().isEmpty()) return false;
        return state.is(BlockTags.DIRT) || state.is(BlockTags.BASE_STONE_OVERWORLD) || state.is(BlockTags.SAND)
                || state.is(Blocks.GRAVEL) || state.is(Blocks.SANDSTONE) || state.is(Blocks.CLAY);
    }
}
