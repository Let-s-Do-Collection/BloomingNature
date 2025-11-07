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
import net.satisfy.bloomingnature.core.registry.ObjectRegistry;
import net.satisfy.bloomingnature.core.registry.PlacerTypeRegistry;
import org.jetbrains.annotations.NotNull;

public class LarchFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<LarchFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            foliagePlacerParts(instance)
                    .and(IntProvider.codec(0, 24).fieldOf("trunk_height").forGetter(placer -> placer.trunkHeight))
                    .apply(instance, LarchFoliagePlacer::new)
    );

    private final IntProvider trunkHeight;

    public LarchFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider trunkHeight) {
        super(radius, offset);
        this.trunkHeight = trunkHeight;
    }

    @Override
    protected @NotNull FoliagePlacerType<?> type() {
        return PlacerTypeRegistry.LARCH_FOLIAGE_PLACER.get();
    }

    @Override
    public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
        return Math.max(12, trunkHeight - this.trunkHeight.sample(random));
    }

    @Override
    protected void createFoliage(LevelSimulatedReader level, FoliageSetter setter, RandomSource random, TreeConfiguration config, int trunkHeight, FoliageAttachment node, int foliageHeight, int radius, int offset) {
        BlockPos blockPos = node.pos();
        BlockPos.MutableBlockPos mutable = blockPos.mutable();

        for (int l = 0; l < trunkHeight; ++l) {
            BlockPos trunkPos = blockPos.above(l);
            setter.set(trunkPos, ObjectRegistry.LARCH_LOG.get().defaultBlockState());
        }

        for (int l = offset; l >= -foliageHeight - 4; --l) {
            if (l >= offset - 2) {
                mutable.setWithOffset(blockPos, 0, l, 0);
                tryPlaceLeaf(level, setter, random, config, mutable);
            } else if (l >= offset - foliageHeight - 4) {
                mutable.setWithOffset(blockPos, 0, l, 0);
                if (random.nextBoolean()) tryPlaceLeaf(level, setter, random, config, mutable.relative(Direction.NORTH, 1).relative(Direction.EAST, 1));
                if (random.nextBoolean()) tryPlaceLeaf(level, setter, random, config, mutable.relative(Direction.NORTH, 1).relative(Direction.WEST, 1));
                if (random.nextBoolean()) tryPlaceLeaf(level, setter, random, config, mutable.relative(Direction.SOUTH, 1).relative(Direction.EAST, 1));
                if (random.nextBoolean()) tryPlaceLeaf(level, setter, random, config, mutable.relative(Direction.SOUTH, 1).relative(Direction.WEST, 1));
                placeLeavesRow(level, setter, random, config, blockPos, Math.max(1, radius - 1), l, node.doubleTrunk());
            }
        }
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        return dx == radius && dz == radius && radius > 0;
    }

    protected void placeLeavesRow(LevelSimulatedReader level, FoliageSetter setter, RandomSource random, TreeConfiguration config, BlockPos centerPos, int radius, int y, boolean giantTrunk) {
        int i = giantTrunk ? 1 : 0;
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        for (int j = -radius; j <= radius + i; ++j) {
            for (int k = -radius; k <= radius + i; ++k) {
                if (!shouldSkipLocationSigned(random, j, y, k, radius, giantTrunk)) {
                    mutable.setWithOffset(centerPos, j, y, k);
                    tryPlaceLeaf(level, setter, random, config, mutable);
                }
            }
        }
    }
}
