package net.satisfy.bloomingnature.core.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.satisfy.bloomingnature.core.block.WildVinesBlock;
import net.satisfy.bloomingnature.core.registry.FloraRegistry;
import net.satisfy.bloomingnature.core.registry.ObjectRegistry;

public class FireflyHotspotFeature extends Feature<NoneFeatureConfiguration> {
    private static final int RADIUS = 6;
    private static final int TRIES = 90;
    private static final int MIN_WATER = 4;

    public FireflyHotspotFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        RandomSource random = context.random();
        BlockPos origin = context.origin();
        if (countWater(level, origin) < MIN_WATER) {
            return false;
        }
        boolean placed = false;
        for (int i = 0; i < TRIES; i++) {
            int x = origin.getX() + random.nextInt(RADIUS * 2 + 1) - RADIUS;
            int z = origin.getZ() + random.nextInt(RADIUS * 2 + 1) - RADIUS;
            BlockPos surface = level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE_WG, new BlockPos(x, 0, z));
            BlockState below = level.getBlockState(surface.below());
            if (!level.getBlockState(surface).isAir()) {
                continue;
            }
            if (below.getFluidState().isSource() && random.nextInt(3) == 0) {
                level.setBlock(surface, (random.nextBoolean() ? FloraRegistry.FLOWERING_LILY_PAD.get() : Blocks.LILY_PAD).defaultBlockState(), 2);
                placed = true;
            } else if (below.getFluidState().isEmpty() && nextToWater(level, surface.below())) {
                BlockState plant = (random.nextBoolean() ? ObjectRegistry.CATTAIL : ObjectRegistry.REED).get().defaultBlockState();
                if (plant.canSurvive(level, surface) && level.getBlockState(surface.above()).isAir()) {
                    DoublePlantBlock.placeAt(level, plant, surface, 2);
                    placed = true;
                }
            }
        }
        placed |= hangVines(level, random, origin);
        return placed;
    }

    private static int countWater(WorldGenLevel level, BlockPos origin) {
        int water = 0;
        for (int dx = -RADIUS; dx <= RADIUS; dx += 2) {
            for (int dz = -RADIUS; dz <= RADIUS; dz += 2) {
                BlockPos surface = level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE_WG, origin.offset(dx, 0, dz));
                if (level.getBlockState(surface.below()).getFluidState().isSource()) {
                    water++;
                }
            }
        }
        return water;
    }

    private static boolean nextToWater(WorldGenLevel level, BlockPos ground) {
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            if (!level.getFluidState(ground.relative(direction)).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private static boolean hangVines(WorldGenLevel level, RandomSource random, BlockPos origin) {
        boolean placed = false;
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        for (int i = 0; i < TRIES; i++) {
            pos.set(origin.getX() + random.nextInt(RADIUS * 2 + 1) - RADIUS, origin.getY() + 2 + random.nextInt(6), origin.getZ() + random.nextInt(RADIUS * 2 + 1) - RADIUS);
            if (!level.isEmptyBlock(pos)) {
                continue;
            }
            for (Direction side : Direction.Plane.HORIZONTAL) {
                if (level.getBlockState(pos.relative(side)).is(BlockTags.LOGS)) {
                    BlockState vine = FloraRegistry.WILD_VINES.get().defaultBlockState().setValue(VineBlock.getPropertyForFace(side), true);
                    int length = 0;
                    int wanted = 1 + random.nextInt(4);
                    while (length < wanted && level.isEmptyBlock(pos.below(length))) {
                        length++;
                    }
                    for (int j = 0; j < length; j++) {
                        WildVinesBlock.Part part = j == length - 1 ? WildVinesBlock.Part.BOTTOM : j == 0 ? WildVinesBlock.Part.TOP : WildVinesBlock.Part.MID;
                        level.setBlock(pos.below(j), vine.setValue(WildVinesBlock.PART, part), 2);
                    }
                    placed |= length > 0;
                    break;
                }
            }
        }
        return placed;
    }
}
