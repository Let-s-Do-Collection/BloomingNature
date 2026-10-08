package net.satisfy.bloomingnature.core.world.biome;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.BlockColumn;

public final class SupportedColumn implements BlockColumn {
    private final BlockColumn column;

    private SupportedColumn(BlockColumn column) {
        this.column = column;
    }

    public static BlockColumn wrap(BlockColumn column) {
        return column instanceof SupportedColumn ? column : new SupportedColumn(column);
    }

    @Override
    public BlockState getBlock(int y) {
        return column.getBlock(y);
    }

    @Override
    public void setBlock(int y, BlockState state) {
        BlockState current = column.getBlock(y);
        if (current.isAir() || !current.getFluidState().isEmpty()) return;
        if (state.getBlock() instanceof FallingBlock && !isSupported(y)) {
            state = solidReplacement(state);
        }
        column.setBlock(y, state);
    }

    private boolean isSupported(int y) {
        BlockState below = column.getBlock(y - 1);
        return !below.isAir() && below.getFluidState().isEmpty();
    }

    private static BlockState solidReplacement(BlockState state) {
        if (state.is(Blocks.SAND)) {
            return Blocks.SANDSTONE.defaultBlockState();
        }
        if (state.is(Blocks.RED_SAND)) {
            return Blocks.RED_SANDSTONE.defaultBlockState();
        }
        return Blocks.STONE.defaultBlockState();
    }
}
