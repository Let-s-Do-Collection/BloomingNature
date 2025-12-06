package net.satisfy.bloomingnature.core.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.NotNull;

public final class MegaSaplingBlock extends BushBlock implements BonemealableBlock {
    private final ResourceKey<ConfiguredFeature<?, ?>> featureKey;
    public static final MapCodec<MegaSaplingBlock> CODEC = simpleCodec(MegaSaplingBlock::new);

    public MegaSaplingBlock(BlockBehaviour.Properties properties, ResourceKey<ConfiguredFeature<?, ?>> featureKey) {
        super(properties);
        this.featureKey = featureKey;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (level.random.nextInt(7) == 0) tryGrow(level, pos, state, level.random);
    }


    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        tryGrow(level, pos, state, random);
    }

    private void tryGrow(ServerLevel level, BlockPos pos, BlockState state, RandomSource random) {
        BlockPos origin = findFourByFourOrigin(level, pos, state);
        if (origin == null) return;

        for (int dx = 0; dx < 4; dx++) {
            for (int dz = 0; dz < 4; dz++) {
                level.setBlock(origin.offset(dx, 0, dz), Blocks.AIR.defaultBlockState(), 3);
            }
        }

        ConfiguredFeature<?, ?> feature = level.registryAccess()
                .registryOrThrow(Registries.CONFIGURED_FEATURE)
                .get(this.featureKey);

        if (feature == null) {
            for (int dx = 0; dx < 4; dx++) {
                for (int dz = 0; dz < 4; dz++) {
                    level.setBlock(origin.offset(dx, 0, dz), state, 3);
                }
            }
            return;
        }

        ChunkGenerator generator = level.getChunkSource().getGenerator();
        boolean placed = feature.place(level, generator, random, origin);

        if (!placed) {
            for (int dx = 0; dx < 4; dx++) {
                for (int dz = 0; dz < 4; dz++) {
                    level.setBlock(origin.offset(dx, 0, dz), state, 3);
                }
            }
        }
    }

    private BlockPos findFourByFourOrigin(ServerLevel level, BlockPos pos, BlockState state) {
        for (int ox = 0; ox >= -3; ox--) {
            for (int oz = 0; oz >= -3; oz--) {
                BlockPos base = pos.offset(ox, 0, oz);
                if (isFourByFourOfSame(level, base, state)) return base;
            }
        }
        return null;
    }

    private boolean isFourByFourOfSame(ServerLevel level, BlockPos origin, BlockState state) {
        for (int dx = 0; dx < 4; dx++) {
            for (int dz = 0; dz < 4; dz++) {
                if (!level.getBlockState(origin.offset(dx, 0, dz)).is(state.getBlock())) return false;
            }
        }
        return true;
    }

    @Override
    protected @NotNull MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }

    public MegaSaplingBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.featureKey = null;
    }
}