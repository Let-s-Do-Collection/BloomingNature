package net.satisfy.bloomingnature.core.world.feature;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.satisfy.bloomingnature.core.block.WildVinesBlock;
import net.satisfy.bloomingnature.core.registry.FloraRegistry;

public class WildVinesFeature extends Feature<NoneFeatureConfiguration> {
    private static final int ATTEMPTS = 24;
    private static final int SPREAD = 4;
    private static final int HEIGHT = 16;
    private static final int MAX_LENGTH = 5;

    public WildVinesFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        RandomSource random = context.random();
        BlockPos origin = context.origin();
        boolean placed = false;
        for (int attempt = 0; attempt < ATTEMPTS; attempt++) {
            BlockPos pos = origin.offset(random.nextInt(SPREAD * 2 + 1) - SPREAD, random.nextInt(HEIGHT) + 1, random.nextInt(SPREAD * 2 + 1) - SPREAD);
            if (!level.isEmptyBlock(pos)) {
                continue;
            }
            for (Direction side : Direction.Plane.HORIZONTAL) {
                if (level.getBlockState(pos.relative(side)).is(BlockTags.LOGS)) {
                    placed |= hang(level, pos, side, random);
                    break;
                }
            }
        }
        return placed;
    }

    private static boolean hang(WorldGenLevel level, BlockPos top, Direction side, RandomSource random) {
        BlockState vine = FloraRegistry.WILD_VINES.get().defaultBlockState().setValue(VineBlock.getPropertyForFace(side), true);
        int length = 0;
        int wanted = 1 + random.nextInt(MAX_LENGTH);
        while (length < wanted && level.isEmptyBlock(top.below(length))) {
            length++;
        }
        for (int i = 0; i < length; i++) {
            WildVinesBlock.Part part = i == length - 1 ? WildVinesBlock.Part.BOTTOM : i == 0 ? WildVinesBlock.Part.TOP : WildVinesBlock.Part.MID;
            level.setBlock(top.below(i), vine.setValue(WildVinesBlock.PART, part), Block.UPDATE_CLIENTS);
        }
        return length > 0;
    }
}
