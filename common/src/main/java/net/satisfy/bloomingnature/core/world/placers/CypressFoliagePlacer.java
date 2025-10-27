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

public class CypressFoliagePlacer extends FoliagePlacer {

    public static final MapCodec<CypressFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(i -> foliagePlacerParts(i).and(IntProvider.codec(0, 24).fieldOf("trunk_height").forGetter(p -> p.trunkDrop)).apply(i, CypressFoliagePlacer::new));

    private final IntProvider trunkDrop;

    public CypressFoliagePlacer(IntProvider layerRadius, IntProvider layerOffset, IntProvider trunkHeight) {
        super(layerRadius, layerOffset);
        this.trunkDrop = trunkHeight;
    }

    protected @NotNull FoliagePlacerType<?> type() {
        return PlacerTypesRegistry.CYPRESS_FOLIAGE_PLACER.get();
    }

    protected void createFoliage(LevelSimulatedReader level, FoliageSetter setter, RandomSource random, TreeConfiguration config, int trunkHeight, FoliageAttachment attachment, int foliageHeight, int radius, int topOffset) {
        BlockPos anchor = attachment.pos();
        BlockPos.MutableBlockPos pos = anchor.mutable();
        int[] radii = new int[]{0,0,0,0,1,1,1,1,1,2,2,2,2,1};
        int layers = radii.length;
        int endY = topOffset - (layers - 1);

        for (int y = topOffset; y >= endY; y--) {
            int dy = topOffset - y;
            int ringRadius = radii[dy];
            if (ringRadius == 0) {
                pos.setWithOffset(anchor, 0, y, 0);
                tryPlaceLeaf(level, setter, random, config, pos);
                if (dy <= 2 && random.nextInt(5) == 0) {
                    Direction d1 = Direction.Plane.HORIZONTAL.getRandomDirection(random);
                    Direction d2 = random.nextBoolean() ? d1.getClockWise() : d1.getCounterClockWise();
                    tryPlaceLeaf(level, setter, random, config, pos.relative(d1, 1).relative(d2, 1));
                }
                continue;
            }
            placeRing(level, setter, random, config, anchor, ringRadius, y, attachment.doubleTrunk(), dy, random);
        }
    }

    private void placeRing(LevelSimulatedReader level, FoliageSetter setter, RandomSource random, TreeConfiguration config, BlockPos center, int ringRadius, int relY, boolean doubleTrunk, int layerIndex, RandomSource rng) {
        int inflate = doubleTrunk ? 1 : 0;
        BlockPos.MutableBlockPos p = new BlockPos.MutableBlockPos();
        boolean thin = ringRadius == 1;
        boolean mid = ringRadius == 2;
        boolean forceCardinals = layerIndex <= 6;
        int skipOdds = thin ? 5 : (mid ? 7 : 8);

        for (int dx = -ringRadius; dx <= ringRadius + inflate; dx++) {
            for (int dz = -ringRadius; dz <= ringRadius + inflate; dz++) {
                if (dx == 0 && dz == 0) continue;
                int ax = Math.abs(dx);
                int az = Math.abs(dz);
                if ((float)(ax * ax + az * az) > (ringRadius + 0.25F) * (ringRadius + 0.25F)) continue;

                boolean isCardinal = ax + az == 1;

                if (!forceCardinals) {
                    if (thin) {
                        if (isCardinal && rng.nextInt(skipOdds + 1) == 0) continue;
                        if (ax == 1 && az == 1 && rng.nextInt(skipOdds + 2) == 0) continue;
                    } else if (mid) {
                        if ((ax == ringRadius || az == ringRadius) && rng.nextInt(skipOdds + 1) == 0) continue;
                    } else {
                        if ((ax == ringRadius && az == ringRadius) || rng.nextInt(skipOdds + 3) == 0) continue;
                    }
                } else if (!isCardinal && thin && rng.nextInt(skipOdds + 2) == 0) {
                    continue;
                }

                p.setWithOffset(center, dx, relY, dz);
                tryPlaceLeaf(level, setter, random, config, p);
            }
        }
    }

    public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
        return 14;
    }

    protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int radius, boolean doubleTrunk) {
        return false;
    }
}