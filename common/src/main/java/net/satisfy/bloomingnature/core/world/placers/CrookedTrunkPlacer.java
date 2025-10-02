package net.satisfy.bloomingnature.core.world.placers;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.satisfy.bloomingnature.core.registry.PlacerTypesRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.BiConsumer;

public class CrookedTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<CrookedTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(
            builder -> trunkPlacerParts(builder).apply(builder, CrookedTrunkPlacer::new)
    );

    public CrookedTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected @NotNull TrunkPlacerType<?> type() {
        return PlacerTypesRegistry.CROOKED_TRUNK_PLACER.get();
    }

    @Override
    public @NotNull List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, int freeTreeHeight, BlockPos pos, TreeConfiguration config) {
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        BlockPos.MutableBlockPos mutable = pos.mutable();

        placeLog(level, blockSetter, random, mutable.relative(direction.getOpposite()), config, state -> state.setValue(RotatedPillarBlock.AXIS, direction.getAxis()));
        placeLog(level, blockSetter, random, mutable.relative(random.nextInt(2) == 0 ? direction.getClockWise() : direction.getCounterClockWise()), config);

        for (int i = 0; i < freeTreeHeight; i++) {
            if (random.nextFloat() < 0.4F && i > 2) {
                mutable.move(direction);
            }
            placeLog(level, blockSetter, random, mutable, config);
            mutable.move(Direction.UP);
        }

        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(mutable, 0, false));
    }
}
