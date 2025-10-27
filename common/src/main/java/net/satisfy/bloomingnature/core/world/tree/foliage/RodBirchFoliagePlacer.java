package net.satisfy.bloomingnature.core.world.tree.foliage;

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
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.satisfy.bloomingnature.core.registry.PlacerTypesRegistry;
import org.jetbrains.annotations.NotNull;

public class RodBirchFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<RodBirchFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            foliagePlacerParts(instance)
                    .and(com.mojang.serialization.Codec.intRange(0, 16).fieldOf("height").forGetter(cdc -> cdc.height))
                    .and(com.mojang.serialization.Codec.FLOAT.fieldOf("offset_chance").forGetter(cdc -> cdc.offsetChance))
                    .apply(instance, RodBirchFoliagePlacer::new)
    );

    protected final int height;
    protected final float offsetChance;

    public RodBirchFoliagePlacer(IntProvider radius, IntProvider offset, int height, float offsetChance) {
        super(radius, offset);
        this.height = height;
        this.offsetChance = offsetChance;
    }

    @Override
    protected @NotNull FoliagePlacerType<?> type() {
        return PlacerTypesRegistry.ROD_BIRCH_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader level, FoliageSetter foligateSetter, RandomSource random, TreeConfiguration treeConfiguration, int trunkHeight, FoliageAttachment attachment, int foliageHeight, int radius, int offset) {
        BoundingBox box = new BoundingBox(attachment.pos());
        int offsetValue = this.offset.sample(random);
        int radiusValue = this.radius.sample(random);

        for (int placeOffset = offsetValue; placeOffset >= offsetValue - foliageHeight; --placeOffset) {
            int baseHeight;
            if (attachment.radiusOffset() > 0)
                baseHeight = Math.max(radiusValue + attachment.radiusOffset() - 1 - placeOffset / 2, 0);
            else baseHeight = Math.max(radiusValue + attachment.radiusOffset() - placeOffset / 2, 0);

            this.placeLeavesRow(level, foligateSetter, random, treeConfiguration, attachment.pos(), baseHeight, placeOffset, attachment.doubleTrunk(), box);
        }
    }

    protected void placeLeavesRow(LevelSimulatedReader level, FoliageSetter foliageSetter, RandomSource random, TreeConfiguration treeConfiguration, BlockPos blockPos, int radius, int yy, boolean isBig, BoundingBox box) {
        int offset = isBig ? 1 : 0;
        BlockPos.MutableBlockPos placePos = new BlockPos.MutableBlockPos();
        for (int xx = -radius; xx <= radius + offset; ++xx) {
            for (int zz = -radius; zz <= radius + offset; ++zz) {
                if (this.shouldSkipLocationSigned(random, xx, yy, zz, radius, isBig)) continue;
                placePos.setWithOffset(blockPos, xx, yy, zz);
                placeLeaf(level, foliageSetter, random, treeConfiguration, placePos, box);

                if (random.nextFloat() < offsetChance) {
                    Direction offsetDir = Direction.getRandom(random);
                    placeLeaf(level, foliageSetter, random, treeConfiguration, placePos.relative(offsetDir), box);
                }
            }
        }
    }

    protected void placeLeaf(LevelSimulatedReader level, FoliageSetter setter, RandomSource random, TreeConfiguration configuration, BlockPos placePos, BoundingBox box) {
        FoliagePlacer.tryPlaceLeaf(level, setter, random, configuration, placePos);
        box.encapsulate(new BlockPos(placePos));
    }

    @Override
    public int foliageHeight(RandomSource random, int i, TreeConfiguration treeConfiguration) {
        return this.height;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int baseHeight, int dx, int dy, int dz, boolean giantTrunk) {
        if (baseHeight + dy >= 4) {
            return true;
        } else {
            return baseHeight * baseHeight + dy * dy > dz * dz;
        }
    }
}
