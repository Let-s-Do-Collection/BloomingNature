package net.satisfy.bloomingnature.core.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CattailBlock extends TallFlowerBlock implements LiquidBlockContainer {
    public static final MapCodec<TallFlowerBlock> CODEC = simpleCodec(CattailBlock::new);

    public CattailBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(HALF, DoubleBlockHalf.LOWER));
    }

    @Override
    public @NotNull MapCodec<TallFlowerBlock> codec() {
        return CODEC;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
            return super.canSurvive(state, level, pos) && level.getFluidState(pos).getType() == Fluids.WATER;
        }
        return super.canSurvive(state, level, pos);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        BlockPos pos = ctx.getClickedPos();
        return pos.getY() < ctx.getLevel().getMaxBuildHeight() - 1
                && ctx.getLevel().getBlockState(pos.above()).canBeReplaced(ctx)
                && ctx.getLevel().getFluidState(pos.above()).isEmpty()
                ? super.getStateForPlacement(ctx) : null;
    }

    @Override
    public boolean canPlaceLiquid(@Nullable Player player, BlockGetter blockGetter, BlockPos blockPos, BlockState blockState, Fluid fluid) {
        return false;
    }

    @Override
    public boolean placeLiquid(LevelAccessor level, BlockPos pos, BlockState state, FluidState fluidState) {
        return false;
    }

    @Override
    public @NotNull BlockState updateShape(BlockState state, Direction dir, BlockState newState, LevelAccessor level, BlockPos pos, BlockPos fromPos) {
        BlockState blockState = super.updateShape(state, dir, newState, level, pos, fromPos);
        if (!blockState.isAir()) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        return blockState;
    }

    @Override
    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return super.mayPlaceOn(floor, world, pos)
                || floor.is(BlockTags.SAND)
                || floor.is(Blocks.CLAY)
                || floor.is(Blocks.COARSE_DIRT)
                || floor.is(Blocks.DIRT)
                || floor.is(Blocks.MUD);
    }

    @Override
    public @NotNull FluidState getFluidState(BlockState state) {
        if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
            return Fluids.WATER.getSource(false);
        }
        return super.getFluidState(state);
    }
}
