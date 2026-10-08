package net.satisfy.bloomingnature.core.block;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.OptionalInt;
import java.util.Set;


public class ExtendedLeavesBlock extends LeavesBlock {
    public static final IntegerProperty DISTANCE_11 = IntegerProperty.create("distance_11", 1, 11);

    public ExtendedLeavesBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(DISTANCE_11, 11).setValue(PERSISTENT, false).setValue(DISTANCE, 7).setValue(WATERLOGGED, false));
    }

    private static BlockState updateDistance(BlockState pState, LevelAccessor pLevel, BlockPos pPos) {
        int i = 11;
        BlockPos.MutableBlockPos blockpos$autocloseable = new BlockPos.MutableBlockPos();

        for (Direction direction : Direction.values()) {
            blockpos$autocloseable.setWithOffset(pPos, direction);
            i = Math.min(i, getDistanceAt(pLevel.getBlockState(blockpos$autocloseable)) + 1);
            if (i == 1) {
                break;
            }
        }

        return pState.setValue(DISTANCE_11, i);
    }

    private static int getDistanceAt(BlockState blockState) {
        return getOptionalDistanceAt(blockState).orElse(11);
    }

    public static @NotNull OptionalInt getOptionalDistanceAt(BlockState blockState) {
        if (blockState.is(BlockTags.LOGS)) {
            return OptionalInt.of(0);
        } else {
            return blockState.hasProperty(DISTANCE_11) ? OptionalInt.of(blockState.getValue(DISTANCE_11)) : OptionalInt.empty();
        }
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(DISTANCE_11) == 11 && !state.getValue(PERSISTENT);
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (!this.decaying(blockState)) return;
        int distance = findLogDistance(serverLevel, blockPos);
        if (distance < 11) {
            serverLevel.setBlock(blockPos, blockState.setValue(DISTANCE_11, distance), 3);
        } else {
            LeavesBlock.dropResources(blockState, serverLevel, blockPos);
            serverLevel.removeBlock(blockPos, false);
        }
    }

    private static int findLogDistance(ServerLevel level, BlockPos start) {
        Set<BlockPos> visited = new HashSet<>();
        ArrayDeque<BlockPos> queue = new ArrayDeque<>();
        visited.add(start);
        queue.add(start);
        for (int distance = 1; distance < 11 && !queue.isEmpty(); distance++) {
            for (int n = queue.size(); n > 0; n--) {
                BlockPos current = queue.poll();
                for (Direction direction : Direction.values()) {
                    BlockPos next = current.relative(direction);
                    if (!visited.add(next)) continue;
                    BlockState state = level.getBlockState(next);
                    if (state.is(BlockTags.LOGS)) return distance;
                    if (state.hasProperty(DISTANCE_11)) queue.add(next);
                }
            }
        }
        return 11;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(DISTANCE_11);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
        BlockState blockstate = this.defaultBlockState().setValue(PERSISTENT, true).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
        return updateDistance(blockstate, pContext.getLevel(), pContext.getClickedPos());
    }

    @Override
    protected boolean decaying(BlockState pState) {
        return !pState.getValue(PERSISTENT) && pState.getValue(DISTANCE_11) == 11;
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        pLevel.setBlock(pPos, updateDistance(pState, pLevel, pPos), 3);
    }

    @Override
    public @NotNull BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if (pState.getValue(WATERLOGGED)) {
            pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        }

        int i = getDistanceAt(pFacingState) + 1;
        if (i != 1 || pState.getValue(DISTANCE_11) != i) {
            pLevel.scheduleTick(pCurrentPos, this, 1);
        }

        return pState;
    }
}