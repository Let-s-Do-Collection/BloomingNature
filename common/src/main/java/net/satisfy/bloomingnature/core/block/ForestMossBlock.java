package net.satisfy.bloomingnature.core.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.MudBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.satisfy.bloomingnature.core.registry.ObjectRegistry;

public class ForestMossBlock extends MudBlock implements BonemealableBlock {
    public ForestMossBlock(Properties properties) {
        super(properties);
    }

    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        return levelReader.getBlockState(blockPos.above()).isAir();
    }

    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        for (int i = 0; i < 3; i++) {
            BlockPos pos = blockPos.offset(randomSource.nextInt(3) - 1, 0, randomSource.nextInt(3) - 1);
            if (serverLevel.getBlockState(pos).isAir()) {
                BlockState toSpawn;
                switch (randomSource.nextInt(3)) {
                    case 0:
                        toSpawn = ObjectRegistry.FOREST_MOSS_CARPET.get().defaultBlockState();
                        break;
                    case 1:
                        toSpawn = ObjectRegistry.MOSSGRASS.get().defaultBlockState();
                        break;
                    case 2:
                        toSpawn = Blocks.BROWN_MUSHROOM.defaultBlockState();
                        break;
                    default:
                        continue;
                }
                serverLevel.setBlock(pos, toSpawn, 3);
            }
        }
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (!level.isClientSide && level.getGameTime() % 240 == 0) {
            if (entity instanceof LivingEntity livingEntity) {
                livingEntity.heal(2.0F);
            }
        }
    }
}
