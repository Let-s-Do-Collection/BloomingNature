package net.satisfy.bloomingnature.core.world.feature.configured.tree.foliage;

import com.mojang.datafixers.Products;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.satisfy.bloomingnature.core.registry.PlacerTypeRegistry;
import org.jetbrains.annotations.NotNull;

public class CornFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<CornFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            createCodec(instance).apply(instance, CornFoliagePlacer::new)
    );

    protected final int height;

    protected static <P extends CornFoliagePlacer> Products.P3<RecordCodecBuilder.Mu<P>, IntProvider, IntProvider, Integer> createCodec(RecordCodecBuilder.Instance<P> builder) {
        return foliagePlacerParts(builder).and(
                com.mojang.serialization.Codec.intRange(0, 16).fieldOf("height").forGetter(placer -> placer.height)
        );
    }

    public CornFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
        super(radius, offset);
        this.height = height;
    }

    @Override
    protected @NotNull FoliagePlacerType<?> type() {
        return PlacerTypeRegistry.CORN_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader world, FoliageSetter placer, RandomSource random, TreeConfiguration config, int trunkHeight, FoliageAttachment treeNode, int foliageHeight, int radius, int offset) {
        for (int y = offset; y >= offset - foliageHeight; --y) {
            int currentRadius = Math.max(Math.min(radius + treeNode.radiusOffset() - 1 - y / 2, 3), 0);
            placeLeavesRow(world, placer, random, config, treeNode.pos(), currentRadius, y, treeNode.doubleTrunk());
        }
    }

    @Override
    public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
        return this.height;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        if (dx == radius && dz == radius && (random.nextInt(2) == 0 || y == 0)) {
            return true;
        } else if (y < radius) {
            return random.nextFloat() < 0.2;
        } else {
            return random.nextFloat() < 0.05;
        }
    }
}
