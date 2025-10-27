package net.satisfy.bloomingnature.core.world.surface;

import com.terraformersmc.biolith.api.surface.BiolithSurfaceBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.chunk.BlockColumn;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;

public final class CypressFieldsSurfaceBuilder extends BiolithSurfaceBuilder {
    @Override
    public void generate(BiomeManager biomeManager, BlockColumn column, RandomSource random, ChunkAccess chunk, Biome biome, int x, int z, int vHeight, int seaLevel) {
        int lx = x & 15;
        int lz = z & 15;
        int topY = chunk.getHeight(Heightmap.Types.WORLD_SURFACE_WG, lx, lz);
        int nx = lx > 0 ? lx - 1 : lx;
        int px = lx < 15 ? lx + 1 : lx;
        int nz = lz > 0 ? lz - 1 : lz;
        int pz = lz < 15 ? lz + 1 : lz;
        int hN = chunk.getHeight(Heightmap.Types.WORLD_SURFACE_WG, lx, nz);
        int hS = chunk.getHeight(Heightmap.Types.WORLD_SURFACE_WG, lx, pz);
        int hW = chunk.getHeight(Heightmap.Types.WORLD_SURFACE_WG, nx, lz);
        int hE = chunk.getHeight(Heightmap.Types.WORLD_SURFACE_WG, px, lz);
        int slope = Math.max(Math.max(Math.abs(topY - hN), Math.abs(topY - hS)), Math.max(Math.abs(topY - hW), Math.max(Math.abs(topY - hE), 0)));

        float mask = smoothNoise(RandomSource.create(912345L), x - 113, z + 271, 0.02f);
        float n1 = smoothNoise(RandomSource.create(34187L), x, z, 0.08f);
        float n2 = smoothNoise(RandomSource.create(7123L), x + 91, z + 37, 0.12f);

        for (int y = 0; y <= topY; y++) {
            if (y != topY) continue;
            if (slope >= 3) {
                column.setBlock(y, Blocks.GRASS_BLOCK.defaultBlockState());
                continue;
            }
            if (mask <= 0.70f) {
                column.setBlock(y, Blocks.GRASS_BLOCK.defaultBlockState());
                continue;
            }
            if (n1 > 0.85f) {
                column.setBlock(y, Blocks.ROOTED_DIRT.defaultBlockState());
                if (y - 1 >= 0) column.setBlock(y - 1, Blocks.DIRT.defaultBlockState());
                continue;
            }
            if (n1 > 0.70f) {
                if (n2 > 0.90f) {
                    column.setBlock(y, Blocks.WHITE_TERRACOTTA.defaultBlockState());
                } else {
                    column.setBlock(y, Blocks.COARSE_DIRT.defaultBlockState());
                    if (y - 1 >= 0) column.setBlock(y - 1, Blocks.DIRT.defaultBlockState());
                }
                continue;
            }
            column.setBlock(y, Blocks.GRASS_BLOCK.defaultBlockState());
        }
    }

    private float smoothNoise(RandomSource random, int x, int z, float scale) {
        float xf = x * scale;
        float zf = z * scale;
        int xi = (int) Math.floor(xf);
        int zi = (int) Math.floor(zf);
        float tx = xf - xi;
        float tz = zf - zi;
        random.setSeed(xi * 49632L + zi * 325176L);
        float c = random.nextFloat();
        random.setSeed((xi + 1) * 49632L + zi * 325176L);
        float e = random.nextFloat();
        random.setSeed(xi * 49632L + (zi + 1) * 325176L);
        float s = random.nextFloat();
        random.setSeed((xi + 1) * 49632L + (zi + 1) * 325176L);
        float se = random.nextFloat();
        float i1 = lerp(c, e, tx);
        float i2 = lerp(s, se, tx);
        return lerp(i1, i2, tz);
    }

    private float lerp(float a, float b, float t) {
        return a + (b - a) * t;
    }
}
