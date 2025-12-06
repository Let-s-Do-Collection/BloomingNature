package net.satisfy.bloomingnature.core.world.feature.configured.decoration;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public final class GroundLitterFeature extends Feature<GroundLitterConfiguration> {
    public GroundLitterFeature(Codec<GroundLitterConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<GroundLitterConfiguration> context) {
        WorldGenLevel level = context.level();
        RandomSource random = context.random();
        BlockPos origin = context.origin();
        GroundLitterConfiguration configuration = context.config();

        int tries = configuration.tries();
        int xzSpread = configuration.xzSpread();
        int ySpread = configuration.ySpread();

        boolean placedAny = false;

        for (int attempt = 0; attempt < tries; attempt++) {
            int offsetX = random.nextInt(xzSpread * 2 + 1) - xzSpread;
            int offsetZ = random.nextInt(xzSpread * 2 + 1) - xzSpread;
            int offsetY = random.nextInt(ySpread * 2 + 1) - ySpread;

            BlockPos offsetBasePos = origin.offset(offsetX, offsetY, offsetZ);
            BlockPos placePos = level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE, offsetBasePos);
            BlockPos belowPos = placePos.below();

            BlockState belowState = level.getBlockState(belowPos);
            BlockState currentState = level.getBlockState(placePos);

            if (!currentState.isAir()) {
                continue;
            }
            if (!belowState.is(Blocks.GRASS_BLOCK) && !belowState.is(Blocks.COARSE_DIRT)) {
                continue;
            }

            BlockState stateToPlace = configuration.stateProvider().getState(random, placePos);
            if (!stateToPlace.canSurvive(level, placePos)) {
                continue;
            }

            level.setBlock(placePos, stateToPlace, 2);
            placedAny = true;
        }

        return placedAny;
    }
}