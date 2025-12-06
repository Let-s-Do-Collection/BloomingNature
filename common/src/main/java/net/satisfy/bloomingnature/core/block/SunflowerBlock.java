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

    private record PhaseInfo(Phase phase, long nextTime) {
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
        if (!level.isClientSide && state.getValue(HALF) == DoubleBlockHalf.UPPER && level instanceof ServerLevel serverLevel) {
            PhaseInfo phaseInfo = getPhaseInfo(level);
            Phase currentPhase = phaseInfo.phase();
            level.setBlock(pos, state.setValue(PHASE, currentPhase), Block.UPDATE_ALL);
            BlockPos belowPos = pos.below();
            BlockState belowState = level.getBlockState(belowPos);
            if (belowState.getBlock() == this) {
                level.setBlock(belowPos, belowState.setValue(PHASE, currentPhase), Block.UPDATE_ALL);
            }
            scheduleNextTick(serverLevel, pos, phaseInfo);
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(HALF) != DoubleBlockHalf.UPPER) {
            return;
        }

        PhaseInfo phaseInfo = getPhaseInfo(level);
        Phase currentPhase = phaseInfo.phase();

        if (state.getValue(PHASE) != currentPhase) {
            level.setBlock(pos, state.setValue(PHASE, currentPhase), Block.UPDATE_ALL);
            BlockPos belowPos = pos.below();
            BlockState belowState = level.getBlockState(belowPos);
            if (belowState.getBlock() == this) {
                level.setBlock(belowPos, belowState.setValue(PHASE, currentPhase), Block.UPDATE_ALL);
            }
        }

        scheduleNextTick(level, pos, phaseInfo);
    }

    private void scheduleNextTick(ServerLevel level, BlockPos pos, PhaseInfo phaseInfo) {
        long dayTime = level.dayTime() % 24000L;
        if (dayTime < 0L) {
            dayTime = 0L;
        }

        long targetTime = phaseInfo.nextTime();
        long delta = targetTime - dayTime;
        if (delta <= 0L) {
            delta += 24000L;
        }

        int delay = (int) Math.max(1L, Math.min(delta, 24000L));
        level.scheduleTick(pos, this, delay);
    }

    private PhaseInfo getPhaseInfo(Level level) {
        long time = level.dayTime() % 24000L;
        if (time < 0L) {
            time = 0L;
        }

        if (level.dimension() != Level.OVERWORLD) {
            long nextTime = (time + 200L) % 24000L;
            return new PhaseInfo(Phase.DAY, nextTime);
        }

        if (level.isNight()) {
            return new PhaseInfo(Phase.NIGHT, 0L);
        }

        if (time < 2000L) {
            return new PhaseInfo(Phase.MORNING, 2000L);
        }

        if (time < 7000L) {
            return new PhaseInfo(Phase.DAY, 7000L);
        }

        if (time < 13000L) {
            return new PhaseInfo(Phase.EVENING, 13000L);
        }

        return new PhaseInfo(Phase.NIGHT, 0L);
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