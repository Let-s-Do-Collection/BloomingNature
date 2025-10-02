package net.satisfy.bloomingnature.core.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.lighting.LightEngine;
import net.satisfy.bloomingnature.core.registry.ObjectRegistry;

import java.util.List;
import java.util.Optional;

public class LateritGrassBlock extends SnowyDirtBlock implements BonemealableBlock {
    public LateritGrassBlock(Properties properties) {
        super(properties);
    }

    private static boolean canBeGrass(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        BlockPos blockPos2 = blockPos.above();
        BlockState blockState2 = levelReader.getBlockState(blockPos2);
        if (blockState2.is(Blocks.SNOW) && blockState2.getValue(SnowLayerBlock.LAYERS) == 1) {
            return true;
        } else if (blockState2.getFluidState().getAmount() == 8) {
            return false;
        } else {
            int i = LightEngine.getLightBlockInto(levelReader, blockState, blockPos, blockState2, blockPos2, Direction.UP, blockState2.getLightBlock(levelReader, blockPos2));
            return i < levelReader.getMaxLightLevel();
        }
    }

    private static boolean canPropagate(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        BlockPos blockPos2 = blockPos.above();
        return canBeGrass(blockState, levelReader, blockPos) && !levelReader.getFluidState(blockPos2).is(FluidTags.WATER);
    }

    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        return levelReader.getBlockState(blockPos.above()).isAir();
    }

    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        BlockPos blockPos2 = blockPos.above();
        BlockState blockState2 = Blocks.GRASS_BLOCK.defaultBlockState();
        Optional<Holder.Reference<PlacedFeature>> optional = serverLevel.registryAccess().registryOrThrow(Registries.PLACED_FEATURE).getHolder(VegetationPlacements.GRASS_BONEMEAL);

        label49:
        for (int i = 0; i < 128; ++i) {
            BlockPos blockPos3 = blockPos2;

            for (int j = 0; j < i / 16; ++j) {
                blockPos3 = blockPos3.offset(randomSource.nextInt(3) - 1, (randomSource.nextInt(3) - 1) * randomSource.nextInt(3) / 2, randomSource.nextInt(3) - 1);
                if (!serverLevel.getBlockState(blockPos3.below()).is(this) || serverLevel.getBlockState(blockPos3).isCollisionShapeFullBlock(serverLevel, blockPos3)) {
                    continue label49;
                }
            }

            BlockState blockState3 = serverLevel.getBlockState(blockPos3);
            if (blockState3.is(blockState2.getBlock()) && randomSource.nextInt(10) == 0) {
                ((BonemealableBlock) blockState2.getBlock()).performBonemeal(serverLevel, randomSource, blockPos3, blockState3);
            }

            if (blockState3.isAir()) {
                Holder<PlacedFeature> holder;
                if (randomSource.nextInt(8) == 0) {
                    List<ConfiguredFeature<?, ?>> list = serverLevel.getBiome(blockPos3).value().getGenerationSettings().getFlowerFeatures();
                    if (list.isEmpty()) {
                        continue;
                    }

                    holder = ((RandomPatchConfiguration) list.get(0).config()).feature();
                } else {
                    if (optional.isEmpty()) {
                        continue;
                    }

                    holder = optional.get();
                }

                holder.value().place(serverLevel, serverLevel.getChunkSource().getGenerator(), randomSource, blockPos3);
            }
        }
    }

    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (!canBeGrass(blockState, serverLevel, blockPos)) {
            serverLevel.setBlockAndUpdate(blockPos, ObjectRegistry.LATERIT.get().defaultBlockState());
        } else {
            if (serverLevel.getMaxLocalRawBrightness(blockPos.above()) >= 9) {
                BlockState blockState2 = this.defaultBlockState();

                for (int i = 0; i < 4; ++i) {
                    BlockPos blockPos2 = blockPos.offset(randomSource.nextInt(3) - 1, randomSource.nextInt(5) - 3, randomSource.nextInt(3) - 1);
                    if (serverLevel.getBlockState(blockPos2).is(ObjectRegistry.LATERIT.get()) && canPropagate(blockState2, serverLevel, blockPos2)) {
                        serverLevel.setBlockAndUpdate(blockPos2, blockState2.setValue(SNOWY, serverLevel.getBlockState(blockPos2.above()).is(Blocks.SNOW)));
                    }
                }
            }

        }
    }
}
