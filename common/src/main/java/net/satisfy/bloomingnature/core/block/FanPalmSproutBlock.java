package net.satisfy.bloomingnature.core.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.satisfy.bloomingnature.core.world.feature.configured.ConfiguredFeatures;

import java.util.Optional;

public class FanPalmSproutBlock extends SaplingBlock {
    public FanPalmSproutBlock() {
        super(new TreeGrower("trees/fan_palm/fan_palm", Optional.empty(), Optional.of(ConfiguredFeatures.FAN_PALM_TREE_KEY), Optional.empty()), Properties.ofFullCopy(Blocks.ACACIA_SAPLING).noCollission().randomTicks().instabreak().sound(SoundType.GRASS));
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(Blocks.SAND) || state.is(Blocks.RED_SAND) || state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.DIRT) || state.is(Blocks.COARSE_DIRT);
    }
}