package net.satisfy.bloomingnature.core.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.satisfy.bloomingnature.core.world.feature.configured.ConfiguredFeatures;
import net.satisfy.bloomingnature.core.world.feature.placed.PlacedFeatures;

import java.util.Optional;

public class FanPalmSproutBlock extends SaplingBlock {
    public FanPalmSproutBlock() {
        super(new TreeGrower("fan_palm", Optional.empty(), Optional.of(ConfiguredFeatures.FAN_PALM_TREE_KEY), Optional.empty()), Properties.ofFullCopy(Blocks.ACACIA_SAPLING).noCollission().randomTicks().instabreak().sound(SoundType.GRASS));
    }

    @Override
    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return pState.is(Blocks.SAND);
    }
}