package net.satisfy.bloomingnature.core.world.feature.configured.tree.trunk;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.satisfy.bloomingnature.core.registry.PlacerTypeRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

@SuppressWarnings("unused")
public class BaobabTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<BaobabTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(b -> trunkPlacerParts(b).apply(b, BaobabTrunkPlacer::new));

    public BaobabTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected @NotNull TrunkPlacerType<?> type() {
        return PlacerTypeRegistry.BAOBAB_TRUNK_PLACER.get();
    }

    @Override
    public @NotNull List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, int freeTreeHeight, BlockPos pos, TreeConfiguration config) {
        List<FoliagePlacer.FoliageAttachment> attachments = new ArrayList<>();

        List<BlockPos> baseFeet = placeBaseRootsPattern(level, blockSetter, random, pos, config);
        for (BlockPos foot : baseFeet) extendToGround(level, blockSetter, random, foot.below(), config);

        maybePlaceInnerWater(level, blockSetter, random, pos, config);

        BlockPos.MutableBlockPos trunkCorner = pos.mutable();
        for (int y = 0; y < freeTreeHeight; y++) {
            placeLayerDiamondHollow(level, blockSetter, random, trunkCorner, config);
            trunkCorner.move(Direction.UP);
        }

        int baseX = pos.getX();
        int baseY = pos.getY();
        int baseZ = pos.getZ();
        int topY = baseY + freeTreeHeight - 1;

        List<BlockPos> crownStarts = placeUpperRootCrownPattern(level, blockSetter, random, new BlockPos(baseX - 1, topY, baseZ - 1), config);
        placeTopArmsBranches(level, blockSetter, random, new BlockPos(baseX, topY, baseZ), crownStarts, config, attachments);
        placeTopCap(level, blockSetter, random, new BlockPos(baseX, topY + 1, baseZ), config);

        maybeFillInnerWithWater(level, blockSetter, random, pos, config);

        return ImmutableList.copyOf(attachments);
    }

    private void maybeFillInnerWithWater(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, BlockPos pos, TreeConfiguration config) {
        if (random.nextFloat() >= 0.33f) return;
        for (int dx = 1; dx < 3; dx++) {
            for (int dz = 1; dz < 3; dz++) {
                BlockPos pool = pos.offset(dx, 0, dz);
                blockSetter.accept(pool, Blocks.WATER.defaultBlockState());
            }
        }
    }

    private void placeTopCap(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, BlockPos origin, TreeConfiguration config) {
        BlockPos base = origin.below();
        for (int dx = 0; dx < 4; dx++) {
            for (int dz = 0; dz < 4; dz++) {
                BlockPos top = base.offset(dx, 0, dz);
                placeVerticalLog(level, blockSetter, random, top, config);
            }
        }
    }

    private void maybePlaceInnerWater(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, BlockPos base, TreeConfiguration config) {
        if (random.nextFloat() >= 0.35f) return;
        int rx = 1 + random.nextInt(2);
        int rz = 1 + random.nextInt(2);
        BlockPos pool = base.offset(rx, 0, rz);
        boolean cavity = level.isStateAtPosition(pool, s -> s.isAir() || s.getFluidState().is(FluidTags.WATER));
        boolean support = level.isStateAtPosition(pool.below(), s -> !s.isAir() || !s.getFluidState().isEmpty());
        if (cavity && support) blockSetter.accept(pool, Blocks.WATER.defaultBlockState());
    }

    private List<BlockPos> placeUpperRootCrownPattern(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, BlockPos origin, TreeConfiguration config) {
        boolean[][] mask = new boolean[][]{
                {false, false, true, true, false, false},
                {false, true,  true, true,  true,  false},
                {true,  true,  false,false, true,  true },
                {true,  true,  false,false, true,  true },
                {false, true,  true, true,  true,  false},
                {false, false, true, true,  false, false}
        };
        List<BlockPos> topStarts = new ArrayList<>();
        int[][] lenGrid = new int[6][6];
        for (int dz = 0; dz < 6; dz++) {
            for (int dx = 0; dx < 6; dx++) {
                if (!mask[dz][dx]) continue;
                int len = 2 + random.nextInt(3);
                int left = dx > 0 && mask[dz][dx - 1] ? lenGrid[dz][dx - 1] : -1;
                int up = dz > 0 && mask[dz - 1][dx] ? lenGrid[dz - 1][dx] : -1;
                int tries = 0;
                while ((len == left || len == up) && tries++ < 4) len = 2 + random.nextInt(3);
                if (len == left) len = left == 2 ? 3 : 2;
                lenGrid[dz][dx] = len;
                BlockPos head = origin.offset(dx, 0, dz);
                topStarts.add(head);
                for (int dy = 0; dy < len; dy++) placeVerticalLog(level, blockSetter, random, head.below(dy), config);
            }
        }
        return topStarts;
    }

    private List<BlockPos> placeBaseRootsPattern(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, BlockPos base, TreeConfiguration config) {
        List<BlockPos> feet = new ArrayList<>();
        boolean[][] mask = new boolean[][]{
                {false, false, true, true, false, false},
                {false, true,  true, true,  true,  false},
                {true,  true,  false,false, true,  true },
                {true,  true,  false,false, true,  true },
                {false, true,  true, true,  true,  false},
                {false, false, true, true,  false, false}
        };
        BlockPos origin = base.offset(-1, 0, -1);
        int[][] lenGrid = new int[6][6];
        for (int dz = 0; dz < 6; dz++) {
            for (int dx = 0; dx < 6; dx++) {
                if (!mask[dz][dx]) continue;
                int len = 2 + random.nextInt(3);
                int left = dx > 0 && mask[dz][dx - 1] ? lenGrid[dz][dx - 1] : -1;
                int up = dz > 0 && mask[dz - 1][dx] ? lenGrid[dz - 1][dx] : -1;
                int tries = 0;
                while ((len == left || len == up) && tries++ < 4) len = 2 + random.nextInt(3);
                if (len == left) len = left == 2 ? 3 : 2;
                lenGrid[dz][dx] = len;
                BlockPos foot = origin.offset(dx, 0, dz);
                for (int dy = 0; dy < len; dy++) placeVerticalLog(level, blockSetter, random, foot.above(dy), config);
                feet.add(foot);
            }
        }
        return feet;
    }

    private void placeLayerDiamondHollow(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, BlockPos corner, TreeConfiguration config) {
        boolean[][] mask = new boolean[][]{
                {false, true,  true,  false},
                {true,  false, false, true },
                {true,  false, false, true },
                {false, true,  true,  false}
        };
        for (int dx = 0; dx < 4; dx++) {
            for (int dz = 0; dz < 4; dz++) {
                if (!mask[dz][dx]) continue;
                placeVerticalLog(level, blockSetter, random, corner.offset(dx, 0, dz), config);
            }
        }
    }

    private void placeVerticalLog(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, BlockPos pos, TreeConfiguration config) {
        BlockState state = config.trunkProvider.getState(random, pos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y);
        blockSetter.accept(pos, state);
    }

    private void extendToGround(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, BlockPos start, TreeConfiguration config) {
        BlockPos.MutableBlockPos cursor = start.mutable();
        int tries = 0;
        while (tries < 96 && level.isStateAtPosition(cursor, s -> s.isAir() || s.getFluidState().is(FluidTags.WATER))) {
            placeVerticalLog(level, blockSetter, random, cursor, config);
            cursor.move(Direction.DOWN);
            tries++;
        }
    }

    private void placeTopArmsBranches(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, BlockPos topCorner, List<BlockPos> crownStarts, TreeConfiguration config, List<FoliagePlacer.FoliageAttachment> attachments) {
        Direction[] directions = {Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST};
        for (Direction dir : directions) {
            List<BlockPos> candidates = selectCandidatesForDirection(topCorner, crownStarts, dir);
            BlockPos start = candidates.get(random.nextInt(candidates.size()));
            growArm(level, blockSetter, random, start, dir, config, attachments);
        }
    }

    private List<BlockPos> selectCandidatesForDirection(BlockPos topCorner, List<BlockPos> crownStarts, Direction dir) {
        List<BlockPos> result = new ArrayList<>();
        int minX = topCorner.getX();
        int minZ = topCorner.getZ();
        int maxX = minX + 3;
        int maxZ = minZ + 3;

        if (dir == Direction.NORTH) {
            for (int dx = 0; dx < 4; dx++) result.add(topCorner.offset(dx, 0, 0));
            for (BlockPos p : crownStarts) if (p.getZ() < minZ) result.add(p);
        } else if (dir == Direction.SOUTH) {
            for (int dx = 0; dx < 4; dx++) result.add(topCorner.offset(dx, 0, 3));
            for (BlockPos p : crownStarts) if (p.getZ() > maxZ) result.add(p);
        } else if (dir == Direction.WEST) {
            for (int dz = 0; dz < 4; dz++) result.add(topCorner.offset(0, 0, dz));
            for (BlockPos p : crownStarts) if (p.getX() < minX) result.add(p);
        } else {
            for (int dz = 0; dz < 4; dz++) result.add(topCorner.offset(3, 0, dz));
            for (BlockPos p : crownStarts) if (p.getX() > maxX) result.add(p);
        }
        return result;
    }

    private void growArm(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, BlockPos start, Direction dir, TreeConfiguration config, List<FoliagePlacer.FoliageAttachment> attachments) {
        BlockPos cursor = start;

        int upLen = 1 + random.nextInt(3);
        int outLen = 3 + random.nextInt(3);

        for (int i = 0; i < upLen; i++) {
            cursor = cursor.above();
            placeVerticalLog(level, blockSetter, random, cursor, config);
        }

        for (int s = 0; s < outLen; s++) {
            cursor = cursor.relative(dir);
            placeHorizontalLog(level, blockSetter, random, cursor, dir.getAxis(), config);
        }

        attachments.add(new FoliagePlacer.FoliageAttachment(cursor.above(), 0, false));
    }

    private void placeHorizontalLog(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, BlockPos pos, Direction.Axis axis, TreeConfiguration config) {
        BlockState state = config.trunkProvider.getState(random, pos).setValue(RotatedPillarBlock.AXIS, axis);
        blockSetter.accept(pos, state);
    }
}