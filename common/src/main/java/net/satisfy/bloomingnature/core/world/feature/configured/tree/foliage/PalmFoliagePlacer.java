package net.satisfy.bloomingnature.core.world.feature.configured.tree.foliage;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.satisfy.bloomingnature.core.registry.PlacerTypeRegistry;
import org.jetbrains.annotations.NotNull;

public class PalmFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<PalmFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec((placer) -> foliagePlacerParts(placer).apply(placer, PalmFoliagePlacer::new));
    private static final int MAX_LEAF_DISTANCE = 10;

    public PalmFoliagePlacer(IntProvider pRadius, IntProvider pOffset) {
        super(pRadius, pOffset);
    }

    @Override
    protected @NotNull FoliagePlacerType<?> type() {
        return PlacerTypeRegistry.PALM_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader level, FoliageSetter setter, RandomSource random, TreeConfiguration config, int maxFreeTreeHeight, FoliageAttachment attachment, int foliageHeight, int foliageRadius, int offset) {
        BlockPos top = attachment.pos().above(offset);

        tryPlaceLeaf(level, setter, random, config, top);
        tryPlaceLeaf(level, setter, random, config, top.above());
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            tryPlaceLeaf(level, setter, random, config, top.relative(direction));
        }

        int baseLength = 4 + foliageRadius;
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            createFrond(level, setter, random, config, top, direction, null, baseLength + random.nextInt(2));
            if (random.nextInt(5) != 0) {
                createFrond(level, setter, random, config, top, direction, direction.getClockWise(), baseLength - 1 + random.nextInt(2));
            }
        }
    }

    private static void createFrond(LevelSimulatedReader level, FoliageSetter setter, RandomSource random, TreeConfiguration config, BlockPos top, Direction primary, Direction secondary, int length) {
        BlockPos.MutableBlockPos pos = top.mutable().move(Direction.UP);
        int droopStart = length / 2 + random.nextInt(2);
        int distance = 2;

        for (int step = 1; step <= length; step++) {
            pos.move(primary);
            if (!placeFrondLeaf(level, setter, random, config, pos, ++distance)) return;
            if (secondary != null) {
                pos.move(secondary);
                if (!placeFrondLeaf(level, setter, random, config, pos, ++distance)) return;
            }
            if (step == 1 || (step >= droopStart && step < length)) {
                pos.move(Direction.DOWN);
                if (!placeFrondLeaf(level, setter, random, config, pos, ++distance)) return;
            }
        }

        if (random.nextBoolean()) {
            placeFrondLeaf(level, setter, random, config, pos.move(Direction.DOWN), ++distance);
        }
    }

    private static boolean placeFrondLeaf(LevelSimulatedReader level, FoliageSetter setter, RandomSource random, TreeConfiguration config, BlockPos pos, int distance) {
        if (distance > MAX_LEAF_DISTANCE) return false;
        tryPlaceLeaf(level, setter, random, config, pos);
        return true;
    }

    @Override
    public int foliageHeight(RandomSource random, int height, TreeConfiguration config) {
        return 0;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        return false;
    }
}
