package net.satisfy.bloomingnature.core.world.feature.configured.decoration;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class FallenHollowTrunkFeature extends Feature<FallenHollowTrunkConfiguration> {
    public FallenHollowTrunkFeature(Codec<FallenHollowTrunkConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<FallenHollowTrunkConfiguration> context) {
        WorldGenLevel level = context.level();
        FallenHollowTrunkConfiguration config = context.config();
        BlockPos origin = context.origin();
        RandomSource random = context.random();
        int length = config.length();

        boolean rotated = random.nextBoolean();
        BlockPos mainStep = rotated ? new BlockPos(0, 0, 1) : new BlockPos(1, 0, 0);
        BlockPos lateral = rotated ? new BlockPos(1, 0, 0) : new BlockPos(0, 0, 1);
        if (random.nextBoolean()) lateral = lateral.multiply(-1);

        boolean breakStart = random.nextBoolean();
        boolean breakEnd = random.nextBoolean();

        for (int i = 0; i < length; i++) {
            int baseX = origin.getX() + mainStep.getX() * i;
            int baseZ = origin.getZ() + mainStep.getZ() * i;
            int lateralShift = random.nextFloat() < 0.4f ? (random.nextBoolean() ? 1 : -1) : 0;
            int fx = baseX + lateral.getX() * lateralShift;
            int fz = baseZ + lateral.getZ() * lateralShift;
            int y = level.getHeight(Heightmap.Types.WORLD_SURFACE_WG, fx, fz);

            BlockPos center = new BlockPos(fx, y - random.nextInt(2), fz);
            boolean broken = (i == 0 && breakStart) || (i == length - 1 && breakEnd);
            placeShell(level, center, random, config, broken);
        }

        return true;
    }

    private void placeShell(LevelAccessor level, BlockPos center, RandomSource random, FallenHollowTrunkConfiguration config, boolean broken) {
        BlockState log = config.log();
        BlockState moss = config.moss();

        BlockState mushBrown = Blocks.BROWN_MUSHROOM.defaultBlockState();
        BlockState mushRed = Blocks.RED_MUSHROOM.defaultBlockState();

        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                BlockPos p = center.offset(dx, 0, dz);
                boolean wall = Math.abs(dx) + Math.abs(dz) > 0;

                if (wall) {
                    if (broken && random.nextFloat() < 0.4f) {
                        level.setBlock(p, Blocks.AIR.defaultBlockState(), 3);
                        continue;
                    }

                    BlockState chosen = random.nextFloat() < 0.25f ? moss : log;
                    level.setBlock(p, chosen, 3);

                    if (config.vegetation() && random.nextFloat() < 0.1f) {
                        BlockPos top = p.above();
                        if (level.getBlockState(top).isAir()) {
                            level.setBlock(top, moss, 3);
                        }
                    }
                } else {
                    level.setBlock(p, Blocks.AIR.defaultBlockState(), 3);
                }
            }
        }

        if (config.brown() && random.nextFloat() < 0.04f) {
            BlockPos t = center.above();
            if (level.getBlockState(t).isAir()) level.setBlock(t, mushBrown, 3);
        }

        if (config.red() && random.nextFloat() < 0.02f) {
            BlockPos t = center.above();
            if (level.getBlockState(t).isAir()) level.setBlock(t, mushRed, 3);
        }
    }
}