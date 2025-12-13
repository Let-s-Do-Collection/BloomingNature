package net.satisfy.bloomingnature.core.world.biome.cold.surface;

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

public final class ColdSurfaceBuilder extends BiolithSurfaceBuilder {
    public enum Profile {FEN, COLD_RIVER, TAIGA, OLD_GROWTH_SPRUCE_TAIGA, OLD_GROWTH_PINE_TAIGA, COLD_GRASSLAND, LARCH_FOREST, HIGHLAND_WOODS}

    private final Profile profile;

    public ColdSurfaceBuilder(Profile profile) {
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

        if (profile == ColdSurfaceBuilder.Profile.COLD_GRASSLAND) {
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

        if (profile == Profile.OLD_GROWTH_SPRUCE_TAIGA || profile == Profile.LARCH_FOREST) {
            for (int y = 0; y <= topY; y++) {
                if (column.getBlock(y).is(Blocks.SAND)) {
                    column.setBlock(y, Blocks.DIRT.defaultBlockState());
                }
            }

            for (int depth = 0; depth <= 4 && topY - depth >= 0; depth++) {
                if (column.getBlock(topY - depth).getFluidState().is(FluidTags.WATER)) return;
            }

            float soilNoise = smoothNoise(RandomSource.create(23874L), x, z, 0.08f);
            float mossNoise = smoothNoise(RandomSource.create(57813L), x + 31, z - 11, 0.12f);
            float podzolNoise = smoothNoise(RandomSource.create(98723L), x - 12, z + 91, 0.06f);

            for (int y = 0; y <= topY; y++) {
                if (y != topY) continue;
                var state = column.getBlock(y);

                if (slope >= 3 && state.is(Blocks.STONE)) {
                    int stoneIndex = mixIndex(x, y, z);
                    if (stoneIndex < 35) {
                        column.setBlock(y, Blocks.STONE.defaultBlockState());
                    } else if (stoneIndex < 65) {
                        column.setBlock(y, Blocks.COBBLESTONE.defaultBlockState());
                    } else {
                        column.setBlock(y, Blocks.MOSSY_COBBLESTONE.defaultBlockState());
                    }
                    continue;
                }

                if (podzolNoise > 0.45f) {
                    column.setBlock(y, Blocks.PODZOL.defaultBlockState());
                    continue;
                }

                if (slope >= 2 || soilNoise > 0.35f) {
                    int coarseIndex = mixIndex(x, y, z);
                    if (coarseIndex < 10) {
                        column.setBlock(y, Blocks.ROOTED_DIRT.defaultBlockState());
                    } else {
                        column.setBlock(y, Blocks.COARSE_DIRT.defaultBlockState());
                    }
                    continue;
                }

                if (mossNoise > 0.75f && podzolNoise < 0.35f) {
                    column.setBlock(y, ObjectRegistry.FOREST_MOSS.get().defaultBlockState());
                    if (y - 1 >= 0) {
                        column.setBlock(y - 1, Blocks.DIRT.defaultBlockState());
                    }
                    continue;
                }

                if (soilNoise > 0.25f) {
                    column.setBlock(y, Blocks.DIRT.defaultBlockState());
                    continue;
                }

                column.setBlock(y, Blocks.GRASS_BLOCK.defaultBlockState());
            }
            return;
        }

        if (profile == Profile.TAIGA) {
            for (int y = 0; y <= topY; y++) {
                if (column.getBlock(y).is(Blocks.SAND)) {
                    column.setBlock(y, Blocks.DIRT.defaultBlockState());
                }
            }

            for (int d = 0; d <= 4 && topY - d >= 0; d++) {
                if (column.getBlock(topY - d).getFluidState().is(FluidTags.WATER)) return;
            }

            if (column.getBlock(topY + 1).getFluidState().is(FluidTags.WATER)) return;

            float soilNoise = smoothNoise(RandomSource.create(23874L), x, z, 0.1f);
            float mossNoise = smoothNoise(RandomSource.create(57813L), x + 17, z - 43, 0.15f);
            float podzolNoise = smoothNoise(RandomSource.create(98723L), x - 23, z + 61, 0.09f);

            for (int y = 0; y <= topY; y++) {
                if (y != topY) continue;
                var state = column.getBlock(y);

                if (slope >= 3 && state.is(Blocks.STONE)) {
                    if (y - 1 < 0 || column.getBlock(y - 1).isAir()) {
                        continue;
                    }
                    int r = mixIndex(x, y, z);
                    if (r < 40) column.setBlock(y, Blocks.STONE.defaultBlockState());
                    else if (r < 70) column.setBlock(y, Blocks.COBBLESTONE.defaultBlockState());
                    else column.setBlock(y, Blocks.MOSSY_COBBLESTONE.defaultBlockState());
                    continue;
                }

                if (soilNoise > 0.9f) {
                    column.setBlock(y, Blocks.ROOTED_DIRT.defaultBlockState());
                    continue;
                }
                if (podzolNoise > 0.8f) {
                    column.setBlock(y, Blocks.PODZOL.defaultBlockState());
                    continue;
                }
                if (soilNoise > 0.7f) {
                    column.setBlock(y, ObjectRegistry.FOREST_MOSS.get().defaultBlockState());
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

        if (profile == Profile.FEN) {
            for (int scanY = 0; scanY <= topY; scanY++) {
                if (column.getBlock(scanY).is(Blocks.SAND)) {
                    column.setBlock(scanY, Blocks.DIRT.defaultBlockState());
                }
            }

            int surfaceY = topY;
            while (surfaceY > 0 && column.getBlock(surfaceY).getFluidState().is(FluidTags.WATER)) {
                surfaceY--;
            }
            while (surfaceY > 0 && column.getBlock(surfaceY).isAir()) {
                surfaceY--;
            }
            if (surfaceY <= 1) {
                return;
            }

            float mossNoise = smoothNoise(RandomSource.create(77113L), x + 19, z - 37, 0.09f);
            float coarseNoise = smoothNoise(RandomSource.create(88217L), x - 41, z + 23, 0.075f);
            float mudNoise = smoothNoise(RandomSource.create(91337L), x + 7, z + 11, 0.11f);

            boolean mossPatch = mossNoise > 0.62f;
            boolean coarsePatch = coarseNoise > 0.72f;
            boolean rareMudPatch = mudNoise > 0.89f;

            int y = surfaceY;
            boolean aboveWater = column.getBlock(y + 1).getFluidState().is(FluidTags.WATER);

            BlockState topState;
            BlockState belowState;

            if (slope >= 3) {
                topState = Blocks.GRASS_BLOCK.defaultBlockState();
                int mix = Math.floorMod(mixIndex(x, y - 1, z), 100);
                belowState = mix < 70 ? Blocks.MUD.defaultBlockState() : Blocks.COARSE_DIRT.defaultBlockState();
            } else if (rareMudPatch) {
                topState = Blocks.MUD.defaultBlockState();
                belowState = Blocks.MUD.defaultBlockState();
            } else if (mossPatch && !aboveWater) {
                topState = ObjectRegistry.FEN_MOSS.get().defaultBlockState();
                belowState = Blocks.MUD.defaultBlockState();
            } else if (coarsePatch) {
                topState = Blocks.COARSE_DIRT.defaultBlockState();
                belowState = Blocks.MUD.defaultBlockState();
            } else {
                topState = Blocks.GRASS_BLOCK.defaultBlockState();
                int mix = Math.floorMod(mixIndex(x, y - 1, z), 100);
                belowState = mix < 70 ? Blocks.MUD.defaultBlockState() : Blocks.COARSE_DIRT.defaultBlockState();
            }

            column.setBlock(y, topState);
            column.setBlock(y - 1, belowState);

            for (int subY = y - 2; subY >= y - 5 && subY >= 0; subY--) {
                int clayMix = Math.floorMod(mixIndex(x, subY, z), 100);
                column.setBlock(subY, clayMix < 85 ? Blocks.CLAY.defaultBlockState() : Blocks.COARSE_DIRT.defaultBlockState());
            }

            return;
        }

        if (profile == Profile.OLD_GROWTH_PINE_TAIGA) {
            for (int y = 0; y <= topY; y++) {
                if (column.getBlock(y).is(Blocks.SAND)) {
                    column.setBlock(y, Blocks.DIRT.defaultBlockState());
                }
            }

            for (int d = 0; d <= 4 && topY - d >= 0; d++) {
                if (column.getBlock(topY - d).getFluidState().is(FluidTags.WATER)) return;
            }

            float soilNoise = smoothNoise(RandomSource.create(23874L), x, z, 0.09f);
            float mossNoise = smoothNoise(RandomSource.create(57813L), x + 9, z - 21, 0.11f);
            float dryNoise = smoothNoise(RandomSource.create(98723L), x - 17, z + 47, 0.08f);

            for (int y = 0; y <= topY; y++) {
                if (y != topY) continue;
                var state = column.getBlock(y);

                if (slope >= 4 && state.is(Blocks.STONE)) {
                    column.setBlock(y, Blocks.COARSE_DIRT.defaultBlockState());
                    continue;
                }

                if (slope == 3 && state.is(Blocks.STONE)) {
                    int r = mixIndex(x, y, z);
                    if (r < 55) {
                        column.setBlock(y, Blocks.COARSE_DIRT.defaultBlockState());
                    } else {
                        column.setBlock(y, Blocks.GRAVEL.defaultBlockState());
                    }
                    continue;
                }

                if (mossNoise > 0.78f && slope <= 2) {
                    column.setBlock(y, ObjectRegistry.FOREST_MOSS.get().defaultBlockState());
                    if (y - 1 >= 0) {
                        column.setBlock(y - 1, Blocks.DIRT.defaultBlockState());
                    }
                    continue;
                }

                if (soilNoise > 0.7f) {
                    column.setBlock(y, Blocks.DIRT.defaultBlockState());
                    continue;
                }

                if (soilNoise > 0.5f && dryNoise < 0.55f) {
                    column.setBlock(y, Blocks.PODZOL.defaultBlockState());
                    continue;
                }

                if (dryNoise > 0.6f) {
                    column.setBlock(y, Blocks.COARSE_DIRT.defaultBlockState());
                    continue;
                }

                column.setBlock(y, Blocks.GRASS_BLOCK.defaultBlockState());
            }
            return;
        }

        if (profile == Profile.COLD_RIVER) {
            int bedrockY = chunk.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, localX, localZ);
            int topSurfaceY = chunk.getHeight(Heightmap.Types.WORLD_SURFACE_WG, localX, localZ);
            int waterTopY = topSurfaceY;
            while (waterTopY > bedrockY && column.getBlock(waterTopY).getFluidState().is(FluidTags.WATER)) waterTopY--;
            boolean hasWater = waterTopY < topSurfaceY && column.getBlock(waterTopY + 1).getFluidState().is(FluidTags.WATER);
            int maxDepth = hasWater ? 3 : 0;

            float bedNoise = smoothNoise(RandomSource.create(31117L), x, z, 0.11f);
            float pocketNoise = smoothNoise(RandomSource.create(17171L), x + 41, z - 33, 0.12f);

            int waterFloorY = hasWater ? bedrockY : topSurfaceY;
            int underwaterTop = hasWater ? Math.min(waterTopY - 1, bedrockY + maxDepth) : bedrockY - 1;

            for (int y = waterFloorY; y <= underwaterTop; y++) {
                BlockState state = column.getBlock(y);
                boolean replaceable = state.is(Blocks.STONE) || state.is(Blocks.DIRT) || state.is(Blocks.GRAVEL) || state.is(Blocks.SAND);
                boolean belowWater = column.getBlock(y + 1).getFluidState().is(FluidTags.WATER) || state.getFluidState().is(FluidTags.WATER);
                if (!replaceable || !belowWater) continue;
                if (y - 1 < 0 || column.getBlock(y - 1).isAir()) continue;

                if (state.is(Blocks.STONE)) {
                    int chance = Math.floorMod(mixIndex(x, y, z), 100);
                    if (chance < 30) {
                        int mix = Math.floorMod(mixIndex(x, y + 11, z - 7), 100);
                        if (mix < 20) column.setBlock(y, ObjectRegistry.MOSSY_TRAVERTIN.get().defaultBlockState());
                        else if (mix < 60) column.setBlock(y, ObjectRegistry.TRAVERTIN.get().defaultBlockState());
                        else column.setBlock(y, ObjectRegistry.COBBLED_TRAVERTIN.get().defaultBlockState());
                    } else if (bedNoise > 0.75f && y <= bedrockY + 1) {
                        column.setBlock(y, Blocks.CLAY.defaultBlockState());
                    } else if (bedNoise > 0.70f && y <= bedrockY + 1) {
                        column.setBlock(y, Blocks.GRAVEL.defaultBlockState());
                    }
                } else if (bedNoise > 0.75f && y <= bedrockY + 1) {
                    column.setBlock(y, Blocks.CLAY.defaultBlockState());
                } else if (bedNoise > 0.70f && y <= bedrockY + 1) {
                    column.setBlock(y, Blocks.GRAVEL.defaultBlockState());
                }

                if (column.getBlock(y).is(Blocks.SAND)) {
                    column.setBlock(y, Blocks.GRAVEL.defaultBlockState());
                }
            }

            int bankTopY = hasWater ? waterTopY : topSurfaceY;
            int bankStartY = Math.max(bedrockY, bankTopY - 2);
            int bankEndY = Math.min(topSurfaceY, bankTopY + 1);

            for (int y = bankStartY; y <= bankEndY; y++) {
                BlockState state = column.getBlock(y);
                boolean nearWater = (y + 1 <= topSurfaceY && column.getBlock(y + 1).getFluidState().is(FluidTags.WATER)) || state.getFluidState().is(FluidTags.WATER);
                boolean replaceable = state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.DIRT) || state.is(Blocks.COARSE_DIRT) || state.is(Blocks.GRAVEL) || state.is(Blocks.SAND);
                if (!nearWater || !replaceable) continue;

                if (state.is(Blocks.SAND)) {
                    column.setBlock(y, Blocks.COARSE_DIRT.defaultBlockState());
                    continue;
                }

                int choice = Math.floorMod(mixIndex(x, y, z), 100);
                if (choice < 58) {
                    column.setBlock(y, Blocks.COARSE_DIRT.defaultBlockState());
                } else {
                    column.setBlock(y, Blocks.GRAVEL.defaultBlockState());
                }
            }

            if (!hasWater) {
                BlockState ground = column.getBlock(topSurfaceY);
                boolean groundOk = ground.is(Blocks.GRASS_BLOCK) || ground.is(Blocks.DIRT) || ground.is(Blocks.SAND) || ground.is(Blocks.GRAVEL);
                if (groundOk && pocketNoise > 0.82f && slope >= 2) {
                    column.setBlock(topSurfaceY, Blocks.GRAVEL.defaultBlockState());
                    if (topSurfaceY - 1 >= 0 && !column.getBlock(topSurfaceY - 1).getFluidState().is(FluidTags.WATER)) {
                        column.setBlock(topSurfaceY - 1, Blocks.DIRT.defaultBlockState());
                    }
                }
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
                BloomingNature.identifier("cold_river"),
                new ColdSurfaceBuilder(Profile.COLD_RIVER).setBiomeKey(BloomingNatureBiomeKeys.COLD_RIVER)
        );
        SurfaceGeneration.addSurfaceBuilder(
                BloomingNature.identifier("old_growth_spruce_taiga"),
                new ColdSurfaceBuilder(Profile.OLD_GROWTH_SPRUCE_TAIGA).setBiomeKey(Biomes.OLD_GROWTH_SPRUCE_TAIGA)
        );
        SurfaceGeneration.addSurfaceBuilder(
                BloomingNature.identifier("taiga"),
                new ColdSurfaceBuilder(Profile.TAIGA).setBiomeKey(Biomes.TAIGA)
        );
        SurfaceGeneration.addSurfaceBuilder(
                BloomingNature.identifier("old_growth_pine_taiga"),
                new ColdSurfaceBuilder(Profile.OLD_GROWTH_PINE_TAIGA).setBiomeKey(Biomes.OLD_GROWTH_PINE_TAIGA)
        );
        SurfaceGeneration.addSurfaceBuilder(
                BloomingNature.identifier("cold_grassland"),
                new ColdSurfaceBuilder(Profile.COLD_GRASSLAND).setBiomeKey(BloomingNatureBiomeKeys.COLD_GRASSLAND)
        );
        SurfaceGeneration.addSurfaceBuilder(
                BloomingNature.identifier("larch_forest"),
                new ColdSurfaceBuilder(Profile.LARCH_FOREST).setBiomeKey(BloomingNatureBiomeKeys.LARCH_FOREST)
        );
        SurfaceGeneration.addSurfaceBuilder(
                BloomingNature.identifier("highland_woods"),
                new ColdSurfaceBuilder(Profile.OLD_GROWTH_SPRUCE_TAIGA).setBiomeKey(BloomingNatureBiomeKeys.HIGHLAND_WOODS)
        );
        SurfaceGeneration.addSurfaceBuilder(
                BloomingNature.identifier("fen"),
                new ColdSurfaceBuilder(Profile.FEN).setBiomeKey(BloomingNatureBiomeKeys.FEN)
        );
    }
}