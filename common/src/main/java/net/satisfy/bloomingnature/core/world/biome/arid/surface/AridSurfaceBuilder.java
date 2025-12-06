package net.satisfy.bloomingnature.core.world.biome.arid.surface;

import com.terraformersmc.biolith.api.surface.BiolithSurfaceBuilder;
import com.terraformersmc.biolith.api.surface.SurfaceGeneration;
import net.minecraft.tags.FluidTags;
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
    public enum Profile {CYPRESS_FIELDS, BRUSHLAND, BAOBAB_SAVANNA, DESERT_OASIS, DESERT, DESERT_RIVER}

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

        if (profile == Profile.BRUSHLAND) {
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

        if (profile == Profile.DESERT_OASIS) {
            float sandNoiseA = smoothNoise(RandomSource.create(91111L), x - 73, z + 159, 0.025f);
            float sandNoiseB = smoothNoise(RandomSource.create(91222L), x + 41, z - 93, 0.032f);
            float sandMask = (sandNoiseA + sandNoiseB) * 0.5f;
            boolean sandPatch = sandMask > 0.62f;

            for (int y = 0; y <= topY; y++) {
                if (y != topY) continue;

                if (slope >= 3) {
                    int r = mixIndex(x, y, z);
                    if (r < 30) {
                        column.setBlock(y, ObjectRegistry.COBBLED_SLATE.get().defaultBlockState());
                    } else if (r < 60) {
                        column.setBlock(y, ObjectRegistry.MOSSY_COBBLED_SLATE.get().defaultBlockState());
                    } else {
                        column.setBlock(y, Blocks.SANDSTONE.defaultBlockState());
                    }
                    continue;
                }

                column.setBlock(y, Blocks.GRASS_BLOCK.defaultBlockState());
                if (y - 1 >= 0) {
                    column.setBlock(y - 1, sandPatch ? Blocks.SAND.defaultBlockState() : Blocks.DIRT.defaultBlockState());
                }
            }
            return;
        }

        for (int d = 0; d <= 4 && topY - d >= 0; d++) {
            if (profile != Profile.DESERT_RIVER && column.getBlock(topY - d).getFluidState().is(FluidTags.WATER)) return;
        }

        if (profile == Profile.DESERT) {
            int sandDepth = 4 + random.nextInt(3);
            int sandstoneDepth = sandDepth + 2;
            for (int y = 0; y <= topY; y++) {
                if (y > topY - sandDepth) {
                    column.setBlock(y, Blocks.SAND.defaultBlockState());
                    continue;
                }
                if (y > topY - sandstoneDepth) {
                    column.setBlock(y, Blocks.SANDSTONE.defaultBlockState());
                    continue;
                }
                var state = column.getBlock(y);
                if (state.is(Blocks.STONE) && y < topY - sandstoneDepth - 3) {
                    column.setBlock(y, ObjectRegistry.SLATE.get().defaultBlockState());
                }
            }

            int chunkX = x >> 4;
            int chunkZ = z >> 4;
            var patchRand = RandomSource.create(chunkX * 915131L + chunkZ * 121421L + 4973L);
            float chunkMask = smoothNoise(RandomSource.create(1337L), chunkX, chunkZ, 0.18f);
            boolean patchActive = patchRand.nextInt(23) == 0 && chunkMask > 0.75f;
            if (patchActive) {
                int cx = (chunkX << 4) + 2 + patchRand.nextInt(12);
                int cz = (chunkZ << 4) + 2 + patchRand.nextInt(12);
                int rx = 3 + patchRand.nextInt(3);
                int rz = 3 + patchRand.nextInt(4);
                float theta = (float) (patchRand.nextFloat() * Math.PI);
                float ct = (float) Math.cos(theta);
                float st = (float) Math.sin(theta);
                float p = 0.7f + patchRand.nextFloat() * 0.9f;
                int depth = 3 + patchRand.nextInt(4);

                int cx2 = cx + patchRand.nextInt(5) - 2;
                int cz2 = cz + patchRand.nextInt(5) - 2;
                boolean dual = patchRand.nextInt(4) == 0;

                int dx0 = x - cx;
                int dz0 = z - cz;
                float xr = ct * dx0 - st * dz0;
                float zr = st * dx0 + ct * dz0;
                float s1 = (float) (Math.pow(Math.abs(xr) / rx, p) + Math.pow(Math.abs(zr) / rz, p));

                float s2 = 2f;
                if (dual) {
                    int dx1 = x - cx2;
                    int dz1 = z - cz2;
                    float xr2 = ct * dx1 - st * dz1;
                    float zr2 = st * dx1 + ct * dz1;
                    s2 = (float) (Math.pow(Math.abs(xr2) / rx, p) + Math.pow(Math.abs(zr2) / rz, p));
                }

                float jitter = smoothNoise(RandomSource.create(8849L), x + 37, z - 21, 0.12f) * 0.2f;
                boolean inside = Math.min(s1, s2) + jitter <= 1.0f;

                if (inside) {
                    for (int i = 0; i < depth; i++) {
                        int y = topY - i;
                        if (y < 0) break;
                        var s = column.getBlock(y);
                        if (s.is(Blocks.SAND) || s.is(Blocks.SANDSTONE)) {
                            column.setBlock(y, ObjectRegistry.QUICKSAND.get().defaultBlockState());
                        }
                    }
                }
            }
            return;
        }

        if (profile == Profile.DESERT_RIVER) {
            int bedrockY = chunk.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, localX, localZ);
            int topSurfaceY = chunk.getHeight(Heightmap.Types.WORLD_SURFACE_WG, localX, localZ);
            int waterTopY = topSurfaceY;
            while (waterTopY > bedrockY && column.getBlock(waterTopY).getFluidState().is(FluidTags.WATER)) waterTopY--;
            boolean hasWater = waterTopY < topSurfaceY && column.getBlock(waterTopY + 1).getFluidState().is(FluidTags.WATER);
            int maxDepth = hasWater ? 4 : 0;

            int waterFloorY = hasWater ? bedrockY : topSurfaceY;
            int underwaterTop = hasWater ? Math.min(waterTopY - 1, bedrockY + maxDepth) : bedrockY - 1;

            for (int y = waterFloorY; y <= underwaterTop; y++) {
                var state = column.getBlock(y);
                boolean replaceable = state.is(Blocks.STONE) || state.is(Blocks.DIRT) || state.is(Blocks.GRAVEL) || state.is(Blocks.SAND) || state.is(Blocks.CLAY);
                if (!replaceable) continue;
                int r = mixIndex(x, y, z);
                if (y <= bedrockY + 1) {
                    if (r < 6) {
                        column.setBlock(y, Blocks.CLAY.defaultBlockState());
                    } else {
                        column.setBlock(y, Blocks.SANDSTONE.defaultBlockState());
                    }
                } else {
                    if (r < 3) {
                        column.setBlock(y, Blocks.CLAY.defaultBlockState());
                    } else {
                        column.setBlock(y, Blocks.SAND.defaultBlockState());
                    }
                }
            }

            int bankTopY = hasWater ? waterTopY : topSurfaceY;
            int bankStartY = Math.max(bedrockY, bankTopY - 3);
            int bankEndY = Math.min(topSurfaceY, bankTopY + 2);

            for (int y = bankStartY; y <= bankEndY; y++) {
                var state = column.getBlock(y);
                boolean nearWater = column.getBlock(y).getFluidState().is(FluidTags.WATER)
                        || (y + 1 <= topSurfaceY && column.getBlock(y + 1).getFluidState().is(FluidTags.WATER))
                        || (y + 2 <= topSurfaceY && column.getBlock(y + 2).getFluidState().is(FluidTags.WATER));
                boolean replaceable = state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.DIRT) || state.is(Blocks.COARSE_DIRT) || state.is(Blocks.GRAVEL) || state.is(Blocks.SAND) || state.is(Blocks.RED_SAND);
                if (!nearWater || !replaceable) continue;
                column.setBlock(y, Blocks.SAND.defaultBlockState());
            }

            var surf = column.getBlock(topSurfaceY);
            if (surf.is(Blocks.GRASS_BLOCK) || surf.is(Blocks.DIRT) || surf.is(Blocks.COARSE_DIRT) || surf.is(Blocks.GRAVEL)) {
                column.setBlock(topSurfaceY, Blocks.SAND.defaultBlockState());
            }
            if (topSurfaceY - 1 >= 0) {
                var below = column.getBlock(topSurfaceY - 1);
                if (below.is(Blocks.DIRT) || below.is(Blocks.GRASS_BLOCK) || below.is(Blocks.COARSE_DIRT)) {
                    column.setBlock(topSurfaceY - 1, Blocks.SAND.defaultBlockState());
                }
            }

            int vStart = Math.max(bedrockY, bankTopY - 2);
            int vEnd = Math.min(topSurfaceY, bankTopY + 2);
            for (int y = vStart; y <= vEnd; y++) {
                var s = column.getBlock(y);
                if (s.is(Blocks.GRASS_BLOCK) || s.is(Blocks.DIRT) || s.is(Blocks.COARSE_DIRT)) {
                    column.setBlock(y, Blocks.SAND.defaultBlockState());
                }
            }
            return;
        }

        if (profile == Profile.BAOBAB_SAVANNA) {
            float dryness = smoothNoise(RandomSource.create(912349L), x - 77, z + 193, 0.012f);
            float bandNoise = smoothNoise(RandomSource.create(55123L), x + 53, z - 41, 0.018f);
            float patchNoise = smoothNoise(RandomSource.create(77411L), x - 19, z + 87, 0.022f);
            float combined = dryness * 0.65f + bandNoise * 0.35f;

            for (int y = 0; y <= topY; y++) {
                if (y != topY) continue;

                if (slope >= 3) {
                    if (combined > 0.58f) {
                        column.setBlock(y, Blocks.COARSE_DIRT.defaultBlockState());
                    } else {
                        column.setBlock(y, Blocks.GRASS_BLOCK.defaultBlockState());
                    }
                    continue;
                }

                int r = mixIndex(x, y, z);
                boolean redBand = combined > 0.63f && patchNoise > 0.70f && r < 32;
                boolean coarseCore = combined > 0.57f && patchNoise > 0.58f;
                boolean coarseFringe = !coarseCore && combined > 0.53f && patchNoise > 0.54f;

                if (redBand) {
                    column.setBlock(y, Blocks.RED_SAND.defaultBlockState());
                    if (y - 1 >= 0) {
                        column.setBlock(y - 1, Blocks.RED_SAND.defaultBlockState());
                    }
                    continue;
                }

                if (coarseCore) {
                    column.setBlock(y, Blocks.COARSE_DIRT.defaultBlockState());
                    if (y - 1 >= 0) {
                        column.setBlock(y - 1, Blocks.DIRT.defaultBlockState());
                    }
                    continue;
                }

                if (coarseFringe && r < 40) {
                    column.setBlock(y, Blocks.COARSE_DIRT.defaultBlockState());
                    if (y - 1 >= 0) {
                        column.setBlock(y - 1, Blocks.DIRT.defaultBlockState());
                    }
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
                BloomingNature.identifier("brushland"),
                new AridSurfaceBuilder(Profile.BRUSHLAND).setBiomeKey(BloomingNatureBiomeKeys.BRUSHLANDS)
        );
        SurfaceGeneration.addSurfaceBuilder(
                BloomingNature.identifier("baobab_savanna"),
                new AridSurfaceBuilder(Profile.BAOBAB_SAVANNA).setBiomeKey(BloomingNatureBiomeKeys.BAOBAB_SAVANNA)
        );
        SurfaceGeneration.addSurfaceBuilder(
                BloomingNature.identifier("desert_oasis"),
                new AridSurfaceBuilder(Profile.DESERT_OASIS).setBiomeKey(BloomingNatureBiomeKeys.DESERT_OASIS)
        );
        SurfaceGeneration.addSurfaceBuilder(
                BloomingNature.identifier("desert"),
                new AridSurfaceBuilder(Profile.DESERT).setBiomeKey(Biomes.DESERT)
        );
        SurfaceGeneration.addSurfaceBuilder(
                BloomingNature.identifier("desert_river"),
                new AridSurfaceBuilder(Profile.DESERT_RIVER).setBiomeKey(BloomingNatureBiomeKeys.DESERT_RIVER)
        );
    }
}