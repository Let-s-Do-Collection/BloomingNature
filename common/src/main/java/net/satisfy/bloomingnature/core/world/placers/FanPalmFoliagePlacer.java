package net.satisfy.bloomingnature.core.world.placers;

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
import net.satisfy.bloomingnature.core.registry.PlacerTypesRegistry;
import org.jetbrains.annotations.NotNull;

public class FanPalmFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<FanPalmFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            foliagePlacerParts(instance).and(
                    com.mojang.serialization.Codec.intRange(0, 16).fieldOf("leaf_length").forGetter(placer -> placer.leafLength)
            ).apply(instance, FanPalmFoliagePlacer::new)
    );

    private final int leafLength;

    public FanPalmFoliagePlacer(IntProvider range, IntProvider offset, int leafLength) {
        super(range, offset);
        this.leafLength = leafLength;
    }

    @Override
    protected @NotNull FoliagePlacerType<?> type() {
        return PlacerTypesRegistry.FAN_PALM_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader level, FoliageSetter foliageSetter, RandomSource random, TreeConfiguration config, int trunkHeight, FoliageAttachment attachment, int foliageHeight, int radius, int offset) {
        BlockPos blockPos = attachment.pos();
        int attempts = random.nextInt(this.leafLength) + 3;

        placeLeaf(level, foliageSetter, random, config, blockPos);

        BlockPos.MutableBlockPos mutable = blockPos.mutable();
        for (int i = 0; i > -1; --i) {
            int j = 1 + i;
            this.placeLeavesRow(level, foliageSetter, random, config, blockPos, j, i, false);
        }

        for (int i = 0; i < 10; ++i) {
            mutable.setWithOffset(blockPos, random.nextInt(radius) - random.nextInt(radius), random.nextInt(radius) - radius + 2, random.nextInt(radius) - random.nextInt(radius));
            placeLeaf(level, foliageSetter, random, config, mutable);
        }

        for (Direction dir : Direction.Plane.HORIZONTAL) {
            BlockPos.MutableBlockPos horiz = new BlockPos.MutableBlockPos(blockPos.getX(), blockPos.getY(), blockPos.getZ());
            int gravity = 0;
            int maxLimit = attempts / 3;

            for (int i = 0; i < attempts; ++i) {
                horiz.move(dir);
                if (gravity >= maxLimit) {
                    gravity = 0;
                    placeLeaf(level, foliageSetter, random, config, horiz);
                    horiz.move(Direction.DOWN);
                } else {
                    ++gravity;
                }
                placeLeaf(level, foliageSetter, random, config, horiz);
            }
        }
    }

    @Override
    public int foliageHeight(RandomSource random, int i, TreeConfiguration config) {
        return 0;
    }

    protected void placeLeaf(LevelSimulatedReader level, FoliageSetter setter, RandomSource random, TreeConfiguration config, BlockPos pos) {
        if (level.isStateAtPosition(pos, state -> state.isAir() || state.is(config.foliageProvider.getState(random, pos).getBlock()))) {
            setter.set(pos, config.foliageProvider.getState(random, pos));
        }
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int dx, int dy, int dz, int radius, boolean large) {
        return false;
    }
}
