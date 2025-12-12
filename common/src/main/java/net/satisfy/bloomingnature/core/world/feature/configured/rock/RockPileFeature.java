package net.satisfy.bloomingnature.core.world.feature.configured.rock;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.ArrayList;
import java.util.List;

public class RockPileFeature extends Feature<RockPileFeatureConfig> {
    public RockPileFeature() {
        super(RockPileFeatureConfig.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<RockPileFeatureConfig> context) {
        var level = context.level();
        var origin = context.origin();
        var random = context.random();
        var config = context.config();

        int span = Math.max(0, config.maxCount() - config.minCount());
        int pileCount = config.minCount() + (span == 0 ? 0 : random.nextInt(span + 1));

        boolean placedAny = false;

        for (int n = 0; n < pileCount; n++) {
            if (config.rocks().isEmpty()) break;
            var shapeSpec = config.rocks().get(random.nextInt(config.rocks().size()));

            int sizeX = Math.max(1, shapeSpec.pickSizeX(random));
            int sizeY = Math.max(1, shapeSpec.pickSizeY(random));
            int sizeZ = Math.max(1, shapeSpec.pickSizeZ(random));
            int bury = Math.max(0, Math.min(shapeSpec.pickBury(random), sizeY));
            float roughness = Math.max(0f, shapeSpec.pickRoughness(random));

            int offsetX = random.nextInt(config.spreadX() * 2 + 1) - config.spreadX();
            int offsetZ = random.nextInt(config.spreadZ() * 2 + 1) - config.spreadZ();

            int surfaceY = level.getHeight(Heightmap.Types.WORLD_SURFACE_WG, origin.getX() + offsetX, origin.getZ() + offsetZ) - 1;
            var basePosition = new BlockPos(origin.getX() + offsetX, surfaceY, origin.getZ() + offsetZ);

            if (!level.getBlockState(basePosition).getFluidState().isEmpty()) continue;

            if (placeOne(context, basePosition, sizeX, sizeY, sizeZ, bury, roughness, config.rocks())) placedAny = true;
        }

        return placedAny;
    }

    private boolean placeOne(FeaturePlaceContext<RockPileFeatureConfig> context, BlockPos basePosition, int sizeX, int sizeY, int sizeZ, int bury, float roughness, List<RockPileFeatureConfig.RockSpec> rockSpecs) {
        var level = context.level();
        var random = context.random();

        var stateAboveBase = level.getBlockState(basePosition.above());
        if (!stateAboveBase.getFluidState().isEmpty()) {
            return false;
        }

        int radiusX = Math.max(1, sizeX / 2);
        int radiusY = Math.max(1, sizeY / 2);
        int radiusZ = Math.max(1, sizeZ / 2);
        int topLayer = sizeY - bury - 1;

        var topCornerPositions = new ArrayList<BlockPos>();
        var placedPositions = new ArrayList<BlockPos>();
        boolean placed = false;

        for (int offsetY = -bury; offsetY < sizeY - bury; offsetY++) {
            for (int offsetX = -radiusX; offsetX <= radiusX; offsetX++) {
                for (int offsetZ = -radiusZ; offsetZ <= radiusZ; offsetZ++) {
                    double normalizedX = (double) offsetX / radiusX;
                    double normalizedY = (double) offsetY / radiusY;
                    double normalizedZ = (double) offsetZ / radiusZ;
                    double shape = normalizedX * normalizedX + normalizedY * normalizedY + normalizedZ * normalizedZ;
                    double jitter = random.nextDouble() * roughness;
                    if (shape + jitter > 1.0) continue;

                    var currentPosition = basePosition.offset(offsetX, offsetY, offsetZ);
                    var stateAtPosition = level.getBlockState(currentPosition);

                    boolean belowSurface = offsetY < 0;
                    boolean canReplaceBelow = belowSurface && stateAtPosition.getFluidState().isEmpty();
                    boolean canReplaceAbove = !belowSurface && (stateAtPosition.isAir() || stateAtPosition.is(BlockTags.REPLACEABLE) || stateAtPosition.is(BlockTags.SNOW));
                    if (!(canReplaceBelow || canReplaceAbove)) continue;

                    boolean isOuterShell = Math.abs(offsetX) == radiusX || Math.abs(offsetZ) == radiusZ;
                    var stateToPlace = (belowSurface || isOuterShell)
                            ? Blocks.GRAVEL.defaultBlockState()
                            : rockSpecs.get(random.nextInt(rockSpecs.size())).state(random, currentPosition);

                    level.setBlock(currentPosition, stateToPlace, 2);
                    placed = true;
                    placedPositions.add(currentPosition.immutable());

                    boolean nearTop = offsetY >= topLayer - 1;
                    boolean nearCorner = Math.abs(offsetX) >= radiusX - 1 && Math.abs(offsetZ) >= radiusZ - 1;
                    if (nearTop && nearCorner) topCornerPositions.add(currentPosition.immutable());
                }
            }
        }

        if (!topCornerPositions.isEmpty()) {
            int carveCount = Math.min(topCornerPositions.size(), 1 + random.nextInt(3));
            for (int i = 0; i < carveCount; i++) {
                int index = random.nextInt(topCornerPositions.size());
                var carvePosition = topCornerPositions.remove(index);
                level.setBlock(carvePosition, Blocks.AIR.defaultBlockState(), 2);
            }
        }

        if (!placedPositions.isEmpty()) {
            for (var rockPosition : placedPositions) {
                transformAdjacentBlocks(context, rockPosition);
            }
        }

        return placed;
    }

    private void transformAdjacentBlocks(FeaturePlaceContext<RockPileFeatureConfig> context, BlockPos rockPosition) {
        var level = context.level();
        var random = context.random();

        if (random.nextFloat() >= 0.6F) {
            return;
        }

        var northPosition = rockPosition.north();
        var southPosition = rockPosition.south();
        var eastPosition = rockPosition.east();
        var westPosition = rockPosition.west();

        transformSingleAdjacentBlock(level, northPosition);
        transformSingleAdjacentBlock(level, southPosition);
        transformSingleAdjacentBlock(level, eastPosition);
        transformSingleAdjacentBlock(level, westPosition);
    }

    private void transformSingleAdjacentBlock(net.minecraft.world.level.WorldGenLevel level, BlockPos position) {
        var stateAtPosition = level.getBlockState(position);
        if (!(stateAtPosition.is(Blocks.SAND) || stateAtPosition.is(Blocks.GRASS_BLOCK))) {
            return;
        }

        var stateAbovePosition = level.getBlockState(position.above());
        if (!(stateAbovePosition.isAir() || stateAbovePosition.is(BlockTags.REPLACEABLE) || stateAbovePosition.is(BlockTags.SNOW))) {
            return;
        }

        level.setBlock(position, Blocks.GRAVEL.defaultBlockState(), 2);
    }
}
