package net.satisfy.bloomingnature.core.world.biome.arid.surface;

import com.terraformersmc.biolith.api.surface.BiolithSurfaceBuilder;
import com.terraformersmc.biolith.api.surface.SurfaceGeneration;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.BlockColumn;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Heightmap;
import net.satisfy.bloomingnature.BloomingNature;
import net.satisfy.bloomingnature.core.registry.ObjectRegistry;
import net.satisfy.bloomingnature.core.world.biome.BloomingNatureBiomeKeys;

public final class AridSurfaceBuilder extends BiolithSurfaceBuilder {
    public enum Profile { CYPRESS_FIELDS, RIVER, FOREST }

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
        int slope = Math.max(Math.max(Math.abs(topY - heightNorth), Math.abs(topY - heightSouth)), Math.max(Math.abs(topY - heightWest), Math.max(Math.abs(topY - heightEast), 0)));

        if (profile == Profile.FOREST) {
            float soilNoise = smoothNoise(RandomSource.create(23874L), x, z, 0.1f);
            float mossNoise = smoothNoise(RandomSource.create(57813L), x + 17, z - 43, 0.15f);
            float podzolNoise = smoothNoise(RandomSource.create(98723L), x - 23, z + 61, 0.09f);

            for (int y = 0; y <= topY; y++) {
                if (y != topY) continue;
                var state = column.getBlock(y);

                if (slope >= 3 && state.is(Blocks.STONE)) {
                    int r = mixIndex(x, y, z);
                    if (r < 40) column.setBlock(y, Blocks.STONE.defaultBlockState());
                    else if (r < 70) column.setBlock(y, Blocks.COBBLESTONE.defaultBlockState());
                    else column.setBlock(y, Blocks.MOSSY_COBBLESTONE.defaultBlockState());
                    continue;
                }

                if (soilNoise > 0.9f) {
                    column.setBlock(y, Blocks.MOSS_BLOCK.defaultBlockState());
                    continue;
                }
                if (podzolNoise > 0.8f) {
                    column.setBlock(y, Blocks.PODZOL.defaultBlockState());
                    continue;
                }
                if (soilNoise > 0.7f) {
                    column.setBlock(y, Blocks.ROOTED_DIRT.defaultBlockState());
                    if (y - 1 >= 0) column.setBlock(y - 1, Blocks.DIRT.defaultBlockState());
                    continue;
                }
                if (mossNoise < 0.25f && slope >= 3) {
                    column.setBlock(y, Blocks.COARSE_DIRT.defaultBlockState());
                    continue;
                }

                column.setBlock(y, Blocks.GRASS_BLOCK.defaultBlockState());
            }
            return;
        }

        if (profile == Profile.RIVER) {
            int bedY = chunk.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, localX, localZ);
            boolean columnHasWater = topY > bedY && column.getBlock(topY).is(Blocks.WATER);
            int maxDepth = columnHasWater ? 4 : 2;
            float temperature = biome.getBaseTemperature();
            boolean warmClimate = temperature > 0.8f;
            var bankState = warmClimate ? Blocks.SAND.defaultBlockState() : Blocks.GRAVEL.defaultBlockState();
            float bankNoise = smoothNoise(RandomSource.create(17171L), x, z, 0.12f);
            float clayNoise = smoothNoise(RandomSource.create(88447L), x + 37, z - 19, 0.18f);
            float gravelNoise = smoothNoise(RandomSource.create(55891L), x - 61, z + 24, 0.10f);

            for (int y = bedY; y <= Math.min(topY, bedY + maxDepth); y++) {
                var state = column.getBlock(y);
                if (state.is(Blocks.STONE) || state.is(Blocks.DIRT) || state.is(Blocks.GRAVEL) || state.is(Blocks.SAND)) {
                    if (clayNoise > 0.85f && y <= bedY + 2) {
                        column.setBlock(y, Blocks.CLAY.defaultBlockState());
                        if (y + 1 <= topY && column.getBlock(y + 1).is(Blocks.STONE)) column.setBlock(y + 1, bankState);
                        if (y - 1 >= 0 && column.getBlock(y - 1).is(Blocks.STONE)) column.setBlock(y - 1, bankState);
                        continue;
                    }
                    if (gravelNoise > 0.80f) {
                        column.setBlock(y, Blocks.GRAVEL.defaultBlockState());
                        continue;
                    }
                    int mix = mixIndex(x, y, z);
                    if (mix < 20) {
                        column.setBlock(y, ObjectRegistry.MOSSY_TRAVERTIN.get().defaultBlockState());
                    } else if (mix < 60) {
                        column.setBlock(y, ObjectRegistry.TRAVERTIN.get().defaultBlockState());
                    } else {
                        column.setBlock(y, ObjectRegistry.COBBLED_TRAVERTIN.get().defaultBlockState());
                    }
                }
            }

            if (!columnHasWater) {
                if (column.getBlock(topY).is(Blocks.GRASS_BLOCK) || column.getBlock(topY).is(Blocks.DIRT) || column.getBlock(topY).is(Blocks.SAND)) {
                    if (bankNoise > 0.55f) {
                        column.setBlock(topY, Blocks.GRAVEL.defaultBlockState());
                        if (topY - 1 >= 0) column.setBlock(topY - 1, Blocks.GRAVEL.defaultBlockState());
                    } else if (bankNoise > 0.35f) {
                        column.setBlock(topY, Blocks.COARSE_DIRT.defaultBlockState());
                        if (topY - 1 >= 0) column.setBlock(topY - 1, Blocks.DIRT.defaultBlockState());
                    } else {
                        column.setBlock(topY, bankState);
                        if (topY - 1 >= 0) column.setBlock(topY - 1, bankState);
                    }
                }
            }
            return;
        }

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
                BloomingNature.identifier("river"),
                new AridSurfaceBuilder(Profile.RIVER).setBiomeKey(Biomes.RIVER)
        );
        SurfaceGeneration.addSurfaceBuilder(
                BloomingNature.identifier("forest"),
                new AridSurfaceBuilder(Profile.FOREST).setBiomeKey(Biomes.FOREST)
        );
    }
}
