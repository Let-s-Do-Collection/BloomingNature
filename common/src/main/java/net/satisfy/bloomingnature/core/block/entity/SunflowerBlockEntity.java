package net.satisfy.bloomingnature.core.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;

import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.satisfy.bloomingnature.core.block.SunflowerBlock;
import net.satisfy.bloomingnature.core.registry.EntityTypeRegistry;

public final class SunflowerBlockEntity extends BlockEntity {

    public SunflowerBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(EntityTypeRegistry.SUNFLOWER.get(), blockPos, blockState);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, SunflowerBlockEntity blockEntity) {
        if (level.isClientSide) {
            return;
        }
        if (!(blockState.getBlock() instanceof SunflowerBlock)) {
            return;
        }
        if (blockState.getValue(DoublePlantBlock.HALF) != DoubleBlockHalf.UPPER) {
            return;
        }

        SunflowerBlock.Phase currentPhase = SunflowerBlock.getCurrentPhase(level);
        if (blockState.getValue(SunflowerBlock.PHASE) != currentPhase) {
            level.setBlock(blockPos, blockState.setValue(SunflowerBlock.PHASE, currentPhase), Block.UPDATE_ALL);
            BlockPos belowPos = blockPos.below();
            BlockState belowState = level.getBlockState(belowPos);
            if (belowState.getBlock() instanceof SunflowerBlock) {
                level.setBlock(belowPos, belowState.setValue(SunflowerBlock.PHASE, currentPhase), Block.UPDATE_ALL);
            }
        }
    }
}