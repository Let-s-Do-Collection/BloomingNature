package net.satisfy.bloomingnature.core.world.biome.arid.surface;

import com.terraformersmc.biolith.api.surface.BiolithSurfaceBuilder;
import com.terraformersmc.biolith.api.surface.SurfaceGeneration;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.BlockColumn;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Heightmap;
import net.satisfy.bloomingnature.BloomingNature;
import net.satisfy.bloomingnature.core.registry.ObjectRegistry;
import net.satisfy.bloomingnature.core.world.biome.BloomingNatureBiomeKeys;

public final class AridSurfaceBuilder extends BiolithSurfaceBuilder {
    public enum Profile {CYPRESS_FIELDS, DRY_PLAINS}

    private final Profile profile;

    public AridSurfaceBuilder(Profile profile) {
        this.profile = profile;
    }

    @Override
    public void generate(BiomeManager biomeManager, BlockColumn column, RandomSource random, ChunkAccess chunk, Biome biome, int x, int z, int vHeight, int seaLevel) {
        int localX = x & 15;
        int localZ = z & 15;
        int topY = chunk.getHeight(Heightmap.Types.WORLD_SURFACE_WG, localX, localZ);
        int westX = localX > 0 ? localX - 1 : localX;
        int eastX = localX < 15 ? localX + 1 : localX;
        int northZ = localZ > 0 ? localZ - 1 : localZ;
        int southZ = localZ < 15 ? localZ + 1 : localZ;
        int heightNorth = chunk.getHeight(Heightmap.Types.WORLD_SURFACE_WG, localX, northZ);
        int heightSouth = chunk.getHeight(Heightmap.Types.WORLD_SURFACE_WG, localX, southZ);
        int heightWest = chunk.getHeight(Heightmap.Types.WORLD_SURFACE_WG, westX, localZ);
        int heightEast = chunk.getHeight(Heightmap.Types.WORLD_SURFACE_WG, eastX, localZ);
        int slope = Math.max(Math.max(Math.abs(topY - heightNorth), Math.abs(topY - heightSouth)), Math.max(Math.abs(topY - heightWest), Math.abs(topY - heightEast)));

        for (int d = 0; d <= 4 && topY - d >= 0; d++) {
            if (column.getBlock(topY - d).getFluidState().is(FluidTags.WATER)) return;
        }

        if (profile == Profile.DRY_PLAINS) {
            float mask = smoothNoise(RandomSource.create(912345L), x - 113, z + 271, 0.02f);
            float n1 = smoothNoise(RandomSource.create(34187L), x, z, 0.08f);
            float warpA = smoothNoise(RandomSource.create(87777L), x - 113, z + 271, 0.03f) * 6.0f;
            float warpB = smoothNoise(RandomSource.create(12341L), x + 47, z - 31, 0.07f) * 2.5f;
            float patchNoise = smoothNoise(RandomSource.create(44417L), x + (int) warpA, z + (int) warpB, 0.028f);
            float detailNoise = smoothNoise(RandomSource.create(90123L), x, z, 0.085f);
            boolean inPatch = patchNoise > 0.74f && detailNoise > 0.45f;

            for (int y = 0; y <= topY; y++) {
                if (y != topY) continue;
                if (slope >= 3) {
                    column.setBlock(y, Blocks.GRASS_BLOCK.defaultBlockState());
                    continue;
                }
                if (inPatch) {
                    int mix = mixIndex(x, y, z);
                    if (mix < 90) {
                        column.setBlock(y, Blocks.COARSE_DIRT.defaultBlockState());
                        if (y - 1 >= 0) column.setBlock(y - 1, Blocks.DIRT.defaultBlockState());
                    } else {
                        column.setBlock(y, Blocks.ROOTED_DIRT.defaultBlockState());
                        if (y - 1 >= 0) column.setBlock(y - 1, Blocks.DIRT.defaultBlockState());
                    }
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
                    column.setBlock(y, Blocks.COARSE_DIRT.defaultBlockState());
                    if (y - 1 >= 0) column.setBlock(y - 1, Blocks.DIRT.defaultBlockState());
                    continue;
                }
                column.setBlock(y, Blocks.GRASS_BLOCK.defaultBlockState());
            }
            return;
        }

        float mask = smoothNoise(RandomSource.create(912345L), x - 113, z + 271, 0.02f);
        float n1 = smoothNoise(RandomSource.create(34187L), x, z, 0.08f);
        float n2 = smoothNoise(RandomSource.create(7123L), x + 91, z + 37, 0.12f);

        for (int y = 0; y <= topY; y++) {
            if (y != topY) continue;

            if (slope >= 3) {
                int r = mixIndex(x, y, z);
                if (r < 20) {
                    column.setBlock(y, ObjectRegistry.MOSSY_COBBLED_MARLSTONE.get().defaultBlockState());
                } else if (r < 50) {
                    column.setBlock(y, ObjectRegistry.COBBLED_MARLSTONE.get().defaultBlockState());
                } else {
                    column.setBlock(y, ObjectRegistry.MARLSTONE.get().defaultBlockState());
                }
                if (y - 1 >= 0) {
                    var below = column.getBlock(y - 1);
                    if (below.is(Blocks.DIRT) || below.is(Blocks.GRASS_BLOCK) || below.is(Blocks.COARSE_DIRT)) {
                        column.setBlock(y - 1, ObjectRegistry.MARLSTONE.get().defaultBlockState());
                    }
                }
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

    private int mixIndex(int x, int y, int z) {
        long seed = (long) x * 341873128712L + (long) y * 132897987541L + (long) z * 42317861L;
        return RandomSource.create(seed).nextInt(100);
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

    public static void registerSurfaceRules() {
        SurfaceGeneration.addSurfaceBuilder(
                BloomingNature.identifier("cypress_fields"),
                new AridSurfaceBuilder(Profile.CYPRESS_FIELDS).setBiomeKey(BloomingNatureBiomeKeys.CYPRESS_FIELDS)
        );
        SurfaceGeneration.addSurfaceBuilder(
                BloomingNature.identifier("dry_plains"),
                new AridSurfaceBuilder(Profile.DRY_PLAINS).setBiomeKey(BloomingNatureBiomeKeys.DRY_PLAINS)
        );
    }
}
