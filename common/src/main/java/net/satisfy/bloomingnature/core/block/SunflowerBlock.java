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
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.satisfy.bloomingnature.core.block.entity.SunflowerBlockEntity;
import net.satisfy.bloomingnature.core.registry.EntityTypeRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public final class SunflowerBlock extends DoublePlantBlock implements BonemealableBlock, EntityBlock {

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
            Phase currentPhase = getCurrentPhase(level);
            level.setBlock(pos, state.setValue(PHASE, currentPhase), Block.UPDATE_ALL);
            BlockPos belowPos = pos.below();
            BlockState belowState = level.getBlockState(belowPos);
            if (belowState.getBlock() == this) {
                level.setBlock(belowPos, belowState.setValue(PHASE, currentPhase), Block.UPDATE_ALL);
            }
        }
    }

    public static Phase getCurrentPhase(Level level) {
        long time = level.dayTime() % 24000L;
        if (time < 0L) {
            time = 0L;
        }

        if (level.dimension() != Level.OVERWORLD) {
            return Phase.DAY;
        }

        if (level.isNight()) {
            return Phase.NIGHT;
        }

        if (time < 2000L) {
            return Phase.MORNING;
        }

        if (time < 7000L) {
            return Phase.DAY;
        }

        if (time < 13000L) {
            return Phase.EVENING;
        }

        return Phase.NIGHT;
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

    @Override
    public @NotNull BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new SunflowerBlockEntity(blockPos, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        if (level.isClientSide) {
            return null;
        }
        if (blockState.getValue(HALF) != DoubleBlockHalf.UPPER) {
            return null;
        }
        if (blockEntityType != EntityTypeRegistry.SUNFLOWER.get()) {
            return null;
        }
        return (lvl, pos, state, be) -> SunflowerBlockEntity.tick(lvl, pos, state, (SunflowerBlockEntity) be);
    }
}