package net.satisfy.bloomingnature.core.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CattailBlock extends TallFlowerBlock implements SimpleWaterloggedBlock {
    public static final MapCodec<TallFlowerBlock> CODEC = simpleCodec(CattailBlock::new);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public CattailBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(HALF, DoubleBlockHalf.LOWER).setValue(WATERLOGGED, false));
    }

    @Override
    public @NotNull MapCodec<TallFlowerBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<net.minecraft.world.level.block.Block, BlockState> builder) {
        builder.add(HALF, WATERLOGGED);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return super.canSurvive(state, level, pos);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        BlockPos clickedPos = ctx.getClickedPos();
        if (clickedPos.getY() >= ctx.getLevel().getMaxBuildHeight() - 1) return null;
        if (!ctx.getLevel().getBlockState(clickedPos.above()).canBeReplaced(ctx)) return null;
        BlockState base = super.getStateForPlacement(ctx);
        if (base == null) return null;
        boolean isWaterHere = ctx.getLevel().getFluidState(clickedPos).getType() == Fluids.WATER;
        return base.setValue(WATERLOGGED, isWaterHere);
    }

    @Override
    public @NotNull BlockState updateShape(BlockState state, Direction direction, BlockState neighbor, LevelAccessor level, BlockPos pos, BlockPos fromPos) {
        BlockState result = super.updateShape(state, direction, neighbor, level, pos, fromPos);

        if (result.getBlock() != this) {
            return result;
        }

        if (!result.hasProperty(WATERLOGGED) || !state.hasProperty(WATERLOGGED)) {
            return result;
        }

        if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
            if (direction == Direction.DOWN && neighbor.getBlock() == this && neighbor.hasProperty(WATERLOGGED)) {
                boolean lowerWaterlogged = neighbor.getValue(WATERLOGGED);
                if (result.getValue(WATERLOGGED) != lowerWaterlogged) {
                    return result.setValue(WATERLOGGED, lowerWaterlogged);
                }
            }
            return result;
        }

        return result;
    }


    @Override
    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return super.mayPlaceOn(floor, world, pos)
                || floor.is(BlockTags.SAND)
                || floor.is(Blocks.CLAY)
                || floor.is(Blocks.GRASS_BLOCK)
                || floor.is(Blocks.COARSE_DIRT)
                || floor.is(Blocks.DIRT)
                || floor.is(Blocks.MUD);
    }

    @Override
    public @NotNull FluidState getFluidState(BlockState state) {
        if (state.getValue(HALF) == DoubleBlockHalf.LOWER && state.getValue(WATERLOGGED)) {
            return Fluids.WATER.getSource(false);
        }
        return super.getFluidState(state);
    }
}