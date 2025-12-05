package net.satisfy.bloomingnature.core.world.feature.configured.tree.trunk;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.satisfy.bloomingnature.core.registry.PlacerTypeRegistry;

import java.util.List;
import java.util.function.BiConsumer;

public class TallGiantTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<TallGiantTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
        trunkPlacerParts(instance)
            .and(Codec.intRange(0, 16).fieldOf("extra_height").forGetter(placer -> placer.extraHeight))
            .apply(instance, TallGiantTrunkPlacer::new)
    );

    private final int extraHeight;

    public TallGiantTrunkPlacer(int baseHeight, int heightRandA, int heightRandB, int extraHeight) {
        super(baseHeight, heightRandA, heightRandB);
        this.extraHeight = extraHeight;
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return PlacerTypeRegistry.TALL_GIANT_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader levelReader, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource randomSource, int trunkHeight, BlockPos startPos, TreeConfiguration configuration) {
        BlockPos groundPos = startPos.below();
        setDirtAt(levelReader, blockSetter, randomSource, groundPos, configuration);
        setDirtAt(levelReader, blockSetter, randomSource, groundPos.east(), configuration);
        setDirtAt(levelReader, blockSetter, randomSource, groundPos.south(), configuration);
        setDirtAt(levelReader, blockSetter, randomSource, groundPos.south().east(), configuration);

        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

        int mainHeight = trunkHeight;
        int totalHeight = mainHeight + this.extraHeight;

        for (int y = 0; y < mainHeight; y++) {
            placeLogIfFreeWithOffset(levelReader, blockSetter, randomSource, mutablePos, configuration, startPos, 0, y, 0);
            if (y < mainHeight - 1) {
                placeLogIfFreeWithOffset(levelReader, blockSetter, randomSource, mutablePos, configuration, startPos, 1, y, 0);
                placeLogIfFreeWithOffset(levelReader, blockSetter, randomSource, mutablePos, configuration, startPos, 1, y, 1);
                placeLogIfFreeWithOffset(levelReader, blockSetter, randomSource, mutablePos, configuration, startPos, 0, y, 1);
            }
        }

        for (int y = mainHeight; y < totalHeight; y++) {
            placeLogIfFreeWithOffset(levelReader, blockSetter, randomSource, mutablePos, configuration, startPos, 0, y, 0);
        }

        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(startPos.above(totalHeight), 0, true));
    }

    private void placeLogIfFreeWithOffset(LevelSimulatedReader levelReader, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource randomSource, BlockPos.MutableBlockPos mutablePos, TreeConfiguration configuration, BlockPos startPos, int offsetX, int offsetY, int offsetZ) {
        mutablePos.setWithOffset(startPos, offsetX, offsetY, offsetZ);
        placeLogIfFree(levelReader, blockSetter, randomSource, mutablePos, configuration);
    }
}