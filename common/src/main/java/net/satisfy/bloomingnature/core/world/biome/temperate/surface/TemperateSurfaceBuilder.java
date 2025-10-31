package net.satisfy.bloomingnature.core.world.biome.temperate.surface;

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

public final class TemperateSurfaceBuilder extends BiolithSurfaceBuilder {
    public enum Profile {RIVER, FOREST, PLAINS}

    private final Profile profile;

    public TemperateSurfaceBuilder(Profile profile) {
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

        if (profile == Profile.FOREST) {
            for (int d = 0; d <= 4 && topY - d >= 0; d++) {
                if (column.getBlock(topY - d).getFluidState().is(FluidTags.WATER)) return;
            }

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
            int bedrockY = chunk.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, localX, localZ);
            int topSurfaceY = chunk.getHeight(Heightmap.Types.WORLD_SURFACE_WG, localX, localZ);
            int waterTopY = topSurfaceY;
            while (waterTopY > bedrockY && column.getBlock(waterTopY).getFluidState().is(FluidTags.WATER)) waterTopY--;
            boolean hasWater = waterTopY < topSurfaceY && column.getBlock(waterTopY + 1).getFluidState().is(FluidTags.WATER);
            int maxDepth = hasWater ? 3 : 0;

            float bedNoise = smoothNoise(RandomSource.create(31117L), x, z, 0.11f);
            float patchNoise = smoothNoise(RandomSource.create(53101L), x - 27, z + 9, 0.07f);
            float pocketNoise = smoothNoise(RandomSource.create(17171L), x + 41, z - 33, 0.12f);

            int waterFloorY = hasWater ? bedrockY : topSurfaceY;
            int underwaterTop = hasWater ? Math.min(waterTopY - 1, bedrockY + maxDepth) : bedrockY - 1;

            for (int y = waterFloorY; y <= underwaterTop; y++) {
                var state = column.getBlock(y);
                boolean replaceable = state.is(Blocks.STONE) || state.is(Blocks.DIRT) || state.is(Blocks.GRAVEL) || state.is(Blocks.SAND);
                boolean belowWater = column.getBlock(y + 1).getFluidState().is(FluidTags.WATER) || column.getBlock(y).getFluidState().is(FluidTags.WATER);
                boolean steep = slope >= 3;
                if (!replaceable || !belowWater) continue;

                if (bedNoise > 0.55f || steep) {
                    if (patchNoise > 0.66f) {
                        int mix = mixIndex(x, y, z);
                        if (mix < 20) column.setBlock(y, ObjectRegistry.MOSSY_TRAVERTIN.get().defaultBlockState());
                        else if (mix < 60) column.setBlock(y, ObjectRegistry.TRAVERTIN.get().defaultBlockState());
                        else column.setBlock(y, ObjectRegistry.COBBLED_TRAVERTIN.get().defaultBlockState());
                    } else if (bedNoise > 0.75f && y <= bedrockY + 1) {
                        column.setBlock(y, Blocks.CLAY.defaultBlockState());
                    } else if (bedNoise > 0.70f && y <= bedrockY + 1) {
                        column.setBlock(y, Blocks.GRAVEL.defaultBlockState());
                    }
                }
            }

            int bankTopY = hasWater ? waterTopY : topSurfaceY;
            int bankStartY = Math.max(bedrockY, bankTopY - 2);
            int bankEndY = Math.min(topSurfaceY, bankTopY + 1);
            boolean warmBiome = biome.getBaseTemperature() > 0.8f;

            for (int y = bankStartY; y <= bankEndY; y++) {
                var state = column.getBlock(y);
                boolean nearWater = (y + 1 <= topSurfaceY && column.getBlock(y + 1).getFluidState().is(FluidTags.WATER)) || column.getBlock(y).getFluidState().is(FluidTags.WATER);
                boolean replaceable = state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.DIRT) || state.is(Blocks.COARSE_DIRT) || state.is(Blocks.GRAVEL) || state.is(Blocks.SAND);
                if (!nearWater || !replaceable) continue;

                int choice = Math.floorMod(mixIndex(x, y, z), 100);
                if (warmBiome && choice < 6) {
                    column.setBlock(y, Blocks.SAND.defaultBlockState());
                } else if (choice < 58) {
                    column.setBlock(y, Blocks.COARSE_DIRT.defaultBlockState());
                } else {
                    column.setBlock(y, Blocks.GRAVEL.defaultBlockState());
                }
            }

            if (!hasWater) {
                var ground = column.getBlock(topSurfaceY);
                boolean groundOk = ground.is(Blocks.GRASS_BLOCK) || ground.is(Blocks.DIRT) || ground.is(Blocks.SAND) || ground.is(Blocks.GRAVEL);
                if (groundOk && pocketNoise > 0.82f && slope >= 2) {
                    column.setBlock(topSurfaceY, Blocks.GRAVEL.defaultBlockState());
                    if (topSurfaceY - 1 >= 0 && !column.getBlock(topSurfaceY - 1).getFluidState().is(FluidTags.WATER)) column.setBlock(topSurfaceY - 1, Blocks.DIRT.defaultBlockState());
                }
            }
            return;
        }
        if (profile == Profile.PLAINS) {
            for (int d = 0; d <= 4 && topY - d >= 0; d++) {
                if (column.getBlock(topY - d).getFluidState().is(FluidTags.WATER)) return;
            }

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
        for (int y = 0; y <= topY; y++) {
            if (y != topY) continue;
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
                BloomingNature.identifier("river"),
                new TemperateSurfaceBuilder(Profile.RIVER).setBiomeKey(Biomes.RIVER)
        );
        SurfaceGeneration.addSurfaceBuilder(
                BloomingNature.identifier("forest"),
                new TemperateSurfaceBuilder(Profile.FOREST).setBiomeKey(Biomes.FOREST)
        );
        SurfaceGeneration.addSurfaceBuilder(
                BloomingNature.identifier("plains"),
                new TemperateSurfaceBuilder(Profile.PLAINS).setBiomeKey(Biomes.PLAINS)
        );
        SurfaceGeneration.addSurfaceBuilder(
                BloomingNature.identifier("sunflower_plains"),
                new TemperateSurfaceBuilder(Profile.PLAINS).setBiomeKey(Biomes.SUNFLOWER_PLAINS)
        );
    }
}
