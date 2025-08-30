package net.satisfy.bloomingnature.core.world.placers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.satisfy.bloomingnature.core.registry.PlacerTypesRegistry;
import org.jetbrains.annotations.NotNull;

public class FanPalmFoliagePlacer extends FoliagePlacer {
    public static final Codec<FanPalmFoliagePlacer> CODEC = RecordCodecBuilder.create(instance ->
            foliagePlacerParts(instance).and(
                    Codec.intRange(0, 16).fieldOf("leaf_length").forGetter(placer -> placer.leafLength)
            ).apply(instance, FanPalmFoliagePlacer::new)
    );
    private final int leafLength;

    public FanPalmFoliagePlacer(IntProvider range, IntProvider rangeOffset, int leafLength) {
        super(range, rangeOffset);
        this.leafLength = leafLength;
    }

    @Override
    protected @NotNull FoliagePlacerType<?> type() {
        return PlacerTypesRegistry.FAN_PALM_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader levelSimulatedReader, FoliageSetter foliageSetter, RandomSource randomSource, TreeConfiguration treeConfiguration, int trunkHeight, FoliageAttachment foliageAttachment, int foliageHeight, int radius, int offset) {
        BlockPos blockPos = foliageAttachment.pos();
        int attempts = randomSource.nextInt(this.leafLength) + 3;
        placeLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockPos);
        BlockPos.MutableBlockPos mutableBlockPos = blockPos.mutable();
        for (int i = 0; i > -1; --i) {
            int j = 1 + i;
            this.placeLeavesRow(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, blockPos, j, i, false);
        }
        for (int i = 0; i < 10; ++i) {
            mutableBlockPos.setWithOffset(blockPos, randomSource.nextInt(radius) - randomSource.nextInt(radius), randomSource.nextInt(radius) - radius + 2, randomSource.nextInt(radius) - randomSource.nextInt(radius));
            placeLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, mutableBlockPos);
        }

        for (Direction direction : Direction.Plane.HORIZONTAL) {
            BlockPos.MutableBlockPos horizontalPos = new BlockPos.MutableBlockPos(blockPos.getX(), blockPos.getY(), blockPos.getZ());
            int leavesGravity = 0;
            int maxLimit = attempts / 3;
            for (int i = 0; i < attempts; ++i) {
                horizontalPos.move(direction);
                if (leavesGravity >= maxLimit) {
                    leavesGravity = 0;
                    placeLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, horizontalPos);
                    horizontalPos.move(Direction.DOWN);
                } else {
                    ++leavesGravity;
                }
                placeLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, horizontalPos);
            }
        }
    }

    @Override
    public int foliageHeight(RandomSource randomSource, int i, TreeConfiguration treeConfiguration) {
        return 0;
    }

    protected void placeLeaf(LevelSimulatedReader levelSimulatedReader, FoliageSetter foliageSetter, RandomSource randomSource, TreeConfiguration treeConfiguration, BlockPos pos) {
        if (levelSimulatedReader.isStateAtPosition(pos, state -> state.isAir() || state.is(treeConfiguration.foliageProvider.getState(randomSource, pos).getBlock()))) {
            foliageSetter.set(pos, treeConfiguration.foliageProvider.getState(randomSource, pos));
        }
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource randomSource, int dx, int dy, int dz, int radius, boolean large) {
        return false;
    }
}
