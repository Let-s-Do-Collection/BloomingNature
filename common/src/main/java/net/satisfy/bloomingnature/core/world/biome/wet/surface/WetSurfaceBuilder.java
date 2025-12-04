package net.satisfy.bloomingnature.core.world.biome.wet.surface;

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

public final class WetSurfaceBuilder extends BiolithSurfaceBuilder {
    public enum Profile {JUNGLE, JUNGLE_RIVER, SPARSE_JUNGLE}

    private final Profile profile;

    public WetSurfaceBuilder(Profile profile) {
        this.profile = profile;
    }

    @Override
    public void generate(BiomeManager biomeManager, BlockColumn column, RandomSource random, ChunkAccess chunk, Biome biome, int x, int z, int vHeight, int seaLevel) {
        int localX = x & 15;
        int localZ = z & 15;
        int topY = chunk.getHeight(Heightmap.Types.WORLD_SURFACE_WG, localX, localZ);
        int floorY = chunk.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, localX, localZ);
        int westX = localX > 0 ? localX - 1 : localX;
        int eastX = localX < 15 ? localX + 1 : localX;
        int northZ = localZ > 0 ? localZ - 1 : localZ;
        int southZ = localZ < 15 ? localZ + 1 : localZ;
        int heightNorth = chunk.getHeight(Heightmap.Types.WORLD_SURFACE_WG, localX, northZ);
        int heightSouth = chunk.getHeight(Heightmap.Types.WORLD_SURFACE_WG, localX, southZ);
        int heightWest = chunk.getHeight(Heightmap.Types.WORLD_SURFACE_WG, westX, localZ);
        int heightEast = chunk.getHeight(Heightmap.Types.WORLD_SURFACE_WG, eastX, localZ);
        int slope = Math.max(Math.max(Math.abs(topY - heightNorth), Math.abs(topY - heightSouth)), Math.max(Math.abs(topY - heightWest), Math.abs(topY - heightEast)));

        if (profile == Profile.SPARSE_JUNGLE) {
            for (int d = 0; d <= 3 && topY - d >= 0; d++) {
                if (column.getBlock(topY - d).getFluidState().is(FluidTags.WATER)) {
                    return;
                }
            }

            float baseNoise = smoothNoise(RandomSource.create(712345L), x + 17, z - 29, 0.032f);
            float roughNoise = smoothNoise(RandomSource.create(398721L), x - 41, z + 63, 0.085f);
            float dryness = saturate((baseNoise - 0.30f) / 0.45f);
            float structure = saturate((roughNoise - 0.35f) / 0.45f);
            float stoneFactor = saturate((roughNoise - 0.65f) / 0.25f);

            for (int y = 0; y <= topY; y++) {
                if (y != topY) {
                    continue;
                }

                if (slope >= 4) {
                    int mix = mixIndex(x, y, z);
                    if (mix < 30) {
                        column.setBlock(y, Blocks.STONE.defaultBlockState());
                    } else if (mix < 65) {
                        column.setBlock(y, Blocks.COARSE_DIRT.defaultBlockState());
                    } else {
                        column.setBlock(y, ObjectRegistry.LATERIT.get().defaultBlockState());
                    }
                    if (y - 1 >= 0) {
                        column.setBlock(y - 1, Blocks.DIRT.defaultBlockState());
                    }
                    continue;
                }

                if (dryness < 0.25f) {
                    column.setBlock(y, Blocks.GRASS_BLOCK.defaultBlockState());
                    continue;
                }

                if (dryness < 0.55f) {
                    column.setBlock(y, Blocks.COARSE_DIRT.defaultBlockState());
                    if (y - 1 >= 0) {
                        column.setBlock(y - 1, Blocks.DIRT.defaultBlockState());
                    }
                    continue;
                }

                if (dryness < 0.85f) {
                    if (stoneFactor > 0.5f) {
                        column.setBlock(y, Blocks.STONE.defaultBlockState());
                    } else {
                        column.setBlock(y, ObjectRegistry.LATERIT.get().defaultBlockState());
                    }
                    if (y - 1 >= 0) {
                        column.setBlock(y - 1, Blocks.DIRT.defaultBlockState());
                    }
                    continue;
                }

                if (structure > 0.6f) {
                    column.setBlock(y, Blocks.STONE.defaultBlockState());
                } else {
                    column.setBlock(y, ObjectRegistry.LATERIT.get().defaultBlockState());
                }
                if (y - 1 >= 0) {
                    column.setBlock(y - 1, ObjectRegistry.LATERIT.get().defaultBlockState());
                }
            }

            return;
        }

        if (profile == Profile.JUNGLE) {
            for (int d = 0; d <= 4 && topY - d >= 0; d++) {
                if (column.getBlock(topY - d).getFluidState().is(FluidTags.WATER)) {
                    return;
                }
            }

            float maskLow = smoothNoise(RandomSource.create(912345L), x - 113, z + 271, 0.018f);
            float maskHigh = smoothNoise(RandomSource.create(34187L), x + 31, z - 47, 0.045f);
            float detail = smoothNoise(RandomSource.create(90123L), x, z, 0.095f);

            float bandBase = saturate((maskLow - 0.50f) / 0.28f);
            float bandDetail = saturate((maskHigh - 0.40f) / 0.35f);
            float wetFactor = bandBase * 0.65f + bandDetail * 0.35f;
            wetFactor *= saturate((detail - 0.35f) / 0.40f);

            for (int y = 0; y <= topY; y++) {
                if (y != topY) {
                    continue;
                }

                if (slope >= 3) {
                    int mix = mixIndex(x, y, z);
                    if (mix < 25) {
                        column.setBlock(y, ObjectRegistry.LATERIT.get().defaultBlockState());
                    } else if (mix < 55) {
                        column.setBlock(y, ObjectRegistry.COBBLED_LATERIT.get().defaultBlockState());
                    } else if (mix < 80) {
                        column.setBlock(y, Blocks.COARSE_DIRT.defaultBlockState());
                    } else {
                        column.setBlock(y, Blocks.MOSS_BLOCK.defaultBlockState());
                    }
                    if (y - 1 >= 0) {
                        column.setBlock(y - 1, ObjectRegistry.LATERIT.get().defaultBlockState());
                    }
                    continue;
                }

                if (wetFactor <= 0.18f) {
                    column.setBlock(y, Blocks.GRASS_BLOCK.defaultBlockState());
                    continue;
                }

                if (wetFactor <= 0.45f) {
                    column.setBlock(y, Blocks.COARSE_DIRT.defaultBlockState());
                    if (y - 1 >= 0) {
                        column.setBlock(y - 1, Blocks.DIRT.defaultBlockState());
                    }
                    continue;
                }

                if (wetFactor <= 0.78f) {
                    column.setBlock(y, Blocks.MOSS_BLOCK.defaultBlockState());
                    if (y - 1 >= 0) {
                        column.setBlock(y - 1, Blocks.DIRT.defaultBlockState());
                    }
                    continue;
                }

                column.setBlock(y, Blocks.MUD.defaultBlockState());
                if (y - 1 >= 0) {
                    column.setBlock(y - 1, Blocks.DIRT.defaultBlockState());
                }
            }

            if (wetFactor > 0.72f && slope <= 2) {
                int chance = mixIndex(x, topY + 13, z);
                if (chance < 7) {
                    int depthRange = 2 + chance % 6;
                    for (int d = 0; d < depthRange; d++) {
                        int marshY = topY - d;
                        if (marshY < floorY) {
                            break;
                        }
                        if (!(column.getBlock(marshY).is(Blocks.MUD)
                                || column.getBlock(marshY).is(Blocks.DIRT)
                                || column.getBlock(marshY).is(Blocks.COARSE_DIRT)
                                || column.getBlock(marshY).is(Blocks.GRASS_BLOCK)
                                || column.getBlock(marshY).is(Blocks.MOSS_BLOCK)
                                || column.getBlock(marshY).is(ObjectRegistry.MARSH_BLOCK.get()))) {
                            break;
                        }
                        column.setBlock(marshY, ObjectRegistry.MARSH_BLOCK.get().defaultBlockState());
                    }
                }
            }

            return;
        }

        int waterTop = topY;
        while (waterTop > floorY && column.getBlock(waterTop).getFluidState().is(FluidTags.WATER)) {
            waterTop--;
        }
        boolean hasWater = waterTop < topY && column.getBlock(waterTop + 1).getFluidState().is(FluidTags.WATER);
        int edgeGap = Math.max(Math.abs(topY - heightNorth), Math.max(Math.abs(topY - heightSouth), Math.max(Math.abs(topY - heightWest), Math.abs(topY - heightEast))));
        int ramp = Math.max(1, Math.min(4, edgeGap / 2));

        if (topY <= floorY) {
            return;
        }

        float noise = smoothNoise(random, x, z, 0.08f);
        boolean isSteep = slope >= 3;
        boolean isShore = hasWater && topY <= seaLevel + ramp;

        int depth = 0;
        for (int y = topY; y >= floorY; y--) {
            if (column.getBlock(y).isAir()) {
                if (depth > 0) {
                    break;
                }
                continue;
            }

            if (column.getBlock(y).is(Blocks.WATER)) {
                depth = 0;
                continue;
            }

            if (depth == 0) {
                if (isShore) {
                    if (isSteep) {
                        column.setBlock(y, Blocks.COARSE_DIRT.defaultBlockState());
                    } else {
                        column.setBlock(y, Blocks.MUD.defaultBlockState());
                    }
                } else {
                    if (isSteep) {
                        column.setBlock(y, Blocks.COARSE_DIRT.defaultBlockState());
                    } else {
                        if (noise > 0.6f) {
                            column.setBlock(y, Blocks.MOSS_BLOCK.defaultBlockState());
                        } else if (noise < 0.3f) {
                            column.setBlock(y, Blocks.COARSE_DIRT.defaultBlockState());
                        } else {
                            column.setBlock(y, Blocks.GRASS_BLOCK.defaultBlockState());
                        }
                    }
                }
            } else if (depth < 3) {
                column.setBlock(y, Blocks.DIRT.defaultBlockState());
            } else {
                break;
            }

            depth++;
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
        float i1 = c + (e - c) * tx;
        float i2 = s + (se - s) * tx;
        return lerp(i1, i2, tz);
    }

    private int mixIndex(int x, int y, int z) {
        long seed = (long) x * 341873128712L + (long) y * 132897987541L + (long) z * 42317861L;
        return RandomSource.create(seed).nextInt(100);
    }

    private float saturate(float value) {
        if (value < 0.0f) {
            return 0.0f;
        }
        return Math.min(value, 1.0f);
    }

    private float lerp(float a, float b, float t) {
        return a + (b - a) * t;
    }


    public static void registerSurfaceRules() {
        SurfaceGeneration.addSurfaceBuilder(
                BloomingNature.identifier("jungle"),
                new WetSurfaceBuilder(Profile.JUNGLE).setBiomeKey(Biomes.JUNGLE)
        );
        SurfaceGeneration.addSurfaceBuilder(
                BloomingNature.identifier("sparse_jungle"),
                new WetSurfaceBuilder(Profile.JUNGLE).setBiomeKey(Biomes.SPARSE_JUNGLE)
        );
        SurfaceGeneration.addSurfaceBuilder(
                BloomingNature.identifier("jungle_river"),
                new WetSurfaceBuilder(Profile.JUNGLE_RIVER).setBiomeKey(BloomingNatureBiomeKeys.JUNGLE_RIVER)
        );
    }
}