package net.satisfy.bloomingnature.core.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class BonemealableTallGrassBlock extends BushBlock implements BonemealableBlock {
    private final Supplier<Block> tallVariant;

    public BonemealableTallGrassBlock(BlockBehaviour.Properties properties, Supplier<Block> tallVariant) {
        super(properties);
        this.tallVariant = tallVariant;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        Block tallGrass = tallVariant.get();
        if (tallGrass instanceof DoublePlantBlock doublePlantBlock) {
            if (doublePlantBlock.defaultBlockState().canSurvive(serverLevel, blockPos)
                    && serverLevel.isEmptyBlock(blockPos.above())) {
                DoublePlantBlock.placeAt(serverLevel, doublePlantBlock.defaultBlockState(), blockPos, 2);
            }
        }
    }

    @Override
    protected @NotNull MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }

    public static final MapCodec<BonemealableTallGrassBlock> CODEC = simpleCodec(
            props -> new BonemealableTallGrassBlock(props, () -> null)
    );
}
