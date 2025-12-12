package net.satisfy.bloomingnature.core.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.satisfy.bloomingnature.core.world.feature.configured.ConfiguredFeatures;

public class FenMossBlock extends Block implements BonemealableBlock {
    public FenMossBlock(Properties properties) {
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
                .flatMap(registry -> registry.getHolder(ConfiguredFeatures.BOG_MOSS_PATCH_BONEMEAL_KEY))
                .ifPresent(reference -> reference.value().place(serverLevel, serverLevel.getChunkSource().getGenerator(), randomSource, blockPos.above()));

        int conversions = 1 + randomSource.nextInt(5);
        int attempts = conversions * 8;

        for (int i = 0; i < attempts && conversions > 0; i++) {
            int offsetX = randomSource.nextInt(5) - 2;
            int offsetZ = randomSource.nextInt(5) - 2;
            BlockPos targetPos = blockPos.offset(offsetX, 0, offsetZ);

            BlockState targetState = serverLevel.getBlockState(targetPos);
            if (targetState.is(Blocks.DIRT) || targetState.is(Blocks.COARSE_DIRT)) {
                serverLevel.setBlock(targetPos, Blocks.CLAY.defaultBlockState(), Block.UPDATE_CLIENTS);
                conversions--;
            }
        }
    }

    @Override
    public boolean isRandomlyTicking(BlockState blockState) {
        return true;
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel level, BlockPos blockPos, RandomSource randomSource) {
        if (randomSource.nextInt(20) != 0) {
            return;
        }

        BlockPos belowPos = blockPos.below();
        BlockState belowState = level.getBlockState(belowPos);

        if (belowState.is(Blocks.DIRT) || belowState.is(Blocks.COARSE_DIRT)) {
            level.setBlock(belowPos, Blocks.CLAY.defaultBlockState(), Block.UPDATE_CLIENTS);
        }
    }

    @Override
    public void fallOn(Level world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        super.fallOn(world, state, pos, entity, fallDistance * 0.5F);
    }

    @Override
    public void updateEntityAfterFallOn(BlockGetter world, Entity entity) {
        if (entity.isSuppressingBounce()) {
            super.updateEntityAfterFallOn(world, entity);
        } else {
            bounceUp(entity);
        }
    }

    private void bounceUp(Entity entity) {
        Vec3 motion = entity.getDeltaMovement();
        if (motion.y < 0.0) {
            double bounceFactor = entity instanceof LivingEntity ? 1.0 : 0.8;
            entity.setDeltaMovement(motion.x, -motion.y * 0.66 * bounceFactor, motion.z);
        }
    }
}
