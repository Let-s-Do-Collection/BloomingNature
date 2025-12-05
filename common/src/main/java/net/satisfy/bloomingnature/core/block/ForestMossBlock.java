package net.satisfy.bloomingnature.core.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.satisfy.bloomingnature.core.world.feature.configured.ConfiguredFeatures;
import net.minecraft.world.level.block.Blocks;

public class ForestMossBlock extends Block implements BonemealableBlock {
    public ForestMossBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        return levelReader.getBlockState(blockPos.above()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        serverLevel.registryAccess().registry(Registries.CONFIGURED_FEATURE)
                .flatMap(registry -> registry.getHolder(ConfiguredFeatures.FOREST_MOSS_PATCH_BONEMEAL_KEY))
                .ifPresent(reference -> reference.value().place(serverLevel, serverLevel.getChunkSource().getGenerator(), randomSource, blockPos.above()));
    }

    @Override
    public boolean isRandomlyTicking(BlockState blockState) {
        return true;
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel level, BlockPos blockPos, RandomSource randomSource) {
        if (!level.getBiome(blockPos).is(BiomeTags.IS_TAIGA)) {
            return;
        }

        if (randomSource.nextInt(8) != 0) {
            return;
        }

        BlockPos targetPos = blockPos.above();
        if (!level.getBlockState(targetPos).isAir()) {
            return;
        }

        if (level.getMaxLocalRawBrightness(targetPos) >= 9) {
            return;
        }

        BlockState mushroomState = randomSource.nextBoolean()
                ? Blocks.BROWN_MUSHROOM.defaultBlockState()
                : Blocks.RED_MUSHROOM.defaultBlockState();

        if (mushroomState.canSurvive(level, targetPos)) {
            level.setBlock(targetPos, mushroomState, Block.UPDATE_CLIENTS);
        }
    }

    @Override
    public void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity) {
        if (!level.isClientSide && entity instanceof LivingEntity livingEntity) {
            boolean isStandingStill = livingEntity.getX() == livingEntity.xOld && livingEntity.getZ() == livingEntity.zOld;

            if (isStandingStill && level.random.nextInt(80) == 0) {
                livingEntity.heal(1.0F);
            }

            if (livingEntity.isOnFire() && level.random.nextInt(40) == 0) {
                livingEntity.clearFire();
            }

            if (livingEntity.fallDistance > 3.0F) {
                livingEntity.fallDistance *= 0.5F;
            }
        }
    }

    @Override
    public void fallOn(Level level, BlockState blockState, BlockPos blockPos, Entity entity, float fallDistance) {
        entity.causeFallDamage(fallDistance, 0.5F, level.damageSources().fall());
    }
}