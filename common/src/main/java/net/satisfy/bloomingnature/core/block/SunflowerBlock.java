package net.satisfy.bloomingnature.core.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public final class SunflowerBlock extends DoublePlantBlock implements BonemealableBlock {

    public enum Phase implements StringRepresentable {
        MORNING, DAY, EVENING, NIGHT;

        @Override
        public @NotNull String getSerializedName() {
            return name().toLowerCase(Locale.ROOT);
        }
    }

    public static final EnumProperty<Phase> PHASE = EnumProperty.create("phase", Phase.class);

    public SunflowerBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(HALF, DoubleBlockHalf.LOWER).setValue(PHASE, Phase.DAY));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HALF, PHASE);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        super.onPlace(state, level, pos, oldState, movedByPiston);
        if (!level.isClientSide && state.getValue(HALF) == DoubleBlockHalf.UPPER) {
            Phase current = getPhase(level);
            level.setBlock(pos, state.setValue(PHASE, current), Block.UPDATE_ALL);
            BlockPos below = pos.below();
            BlockState lower = level.getBlockState(below);
            if (lower.getBlock() == this) {
                level.setBlock(below, lower.setValue(PHASE, current), Block.UPDATE_ALL);
            }
            scheduleNextTick((ServerLevel) level, pos);
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(HALF) != DoubleBlockHalf.UPPER) return;
        Phase current = getPhase(level);
        if (state.getValue(PHASE) != current) {
            level.setBlock(pos, state.setValue(PHASE, current), Block.UPDATE_ALL);
            BlockPos below = pos.below();
            BlockState lower = level.getBlockState(below);
            if (lower.getBlock() == this) {
                level.setBlock(below, lower.setValue(PHASE, current), Block.UPDATE_ALL);
            }
        }
        scheduleNextTick(level, pos);
    }

    private void scheduleNextTick(ServerLevel level, BlockPos pos) {
        level.scheduleTick(pos, this, 10);
    }

    private Phase getPhase(Level level) {
        long time = level.dayTime() % 24000L;
        if (level.isNight() || level.dimension() != Level.OVERWORLD) {
            return Phase.NIGHT;
        } else if (time < 2000L) {
            return Phase.MORNING;
        } else if (time >= 7000L) {
            return Phase.EVENING;
        } else {
            return Phase.DAY;
        }
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return false;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        return blockState.getValue(HALF) == DoubleBlockHalf.UPPER;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos blockPos, BlockState blockState) {
        return blockState.getValue(HALF) == DoubleBlockHalf.UPPER;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos blockPos, BlockState blockState) {
        ItemStack drop = new ItemStack(asItem(), 1 + random.nextInt(2));
        popResource(level, blockPos, drop);
    }
}