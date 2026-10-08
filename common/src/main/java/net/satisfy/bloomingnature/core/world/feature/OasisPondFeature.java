package net.satisfy.bloomingnature.core.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.satisfy.bloomingnature.core.registry.FloraRegistry;
import net.satisfy.bloomingnature.core.registry.ObjectRegistry;
import net.satisfy.bloomingnature.core.world.feature.configured.ConfiguredFeatures;

public class OasisPondFeature extends Feature<NoneFeatureConfiguration> {
    private static final int MAX_HEIGHT_DIFFERENCE = 3;
    private static final int MIN_PALMS = 2;
    private static final int MAX_PALMS = 4;

    public OasisPondFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        RandomSource random = context.random();
        BlockPos origin = context.origin();
        float radiusX = 3.5F + random.nextFloat() * 2.5F;
        float radiusZ = 3.5F + random.nextFloat() * 2.5F;
        int reach = Mth.ceil(Math.max(radiusX, radiusZ)) + 4;
        int waterY = origin.getY() - 1;

        if (!isFlat(level, origin, reach, waterY) || !level.getBlockState(origin.below()).getFluidState().isEmpty()) {
            return false;
        }

        float phase = random.nextFloat() * Mth.TWO_PI;
        float wobble = 0.12F + random.nextFloat() * 0.1F;
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        for (int dx = -reach; dx <= reach; dx++) {
            for (int dz = -reach; dz <= reach; dz++) {
                float distance = distance(dx, dz, radiusX, radiusZ, phase, wobble);
                if (distance > 1.0F) {
                    continue;
                }
                int depth = distance < 0.45F ? 3 : distance < 0.75F ? 2 : 1;
                int x = origin.getX() + dx;
                int z = origin.getZ() + dz;
                for (int y = waterY + 1; y <= waterY + 3; y++) {
                    pos.set(x, y, z);
                    if (!level.getBlockState(pos).isAir()) {
                        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
                    }
                }
                for (int y = waterY; y > waterY - depth; y--) {
                    level.setBlock(pos.set(x, y, z), Blocks.WATER.defaultBlockState(), 2);
                }
                BlockState floor = distance < 0.5F && random.nextInt(3) == 0 ? Blocks.CLAY.defaultBlockState() : Blocks.SAND.defaultBlockState();
                level.setBlock(pos.set(x, waterY - depth, z), floor, 2);
            }
        }

        sealEdges(level, origin, reach, radiusX, radiusZ, phase, wobble, waterY);
        placeRim(level, random, origin, reach, radiusX, radiusZ, phase, wobble, waterY);
        placeLilyPads(level, random, origin, reach, radiusX, radiusZ, phase, wobble, waterY);
        placePalms(context, origin, Math.max(radiusX, radiusZ));
        return true;
    }

    private static float distance(int dx, int dz, float radiusX, float radiusZ, float phase, float wobble) {
        float angle = (float) Math.atan2(dz, dx);
        float edge = 1.0F + wobble * Mth.sin(angle * 3.0F + phase) + wobble * 0.5F * Mth.sin(angle * 5.0F - phase);
        float nx = dx / radiusX;
        float nz = dz / radiusZ;
        return Mth.sqrt(nx * nx + nz * nz) / edge;
    }

    private static boolean isFlat(WorldGenLevel level, BlockPos origin, int reach, int waterY) {
        for (int dx = -reach; dx <= reach; dx += 2) {
            for (int dz = -reach; dz <= reach; dz += 2) {
                int top = level.getHeight(Heightmap.Types.WORLD_SURFACE_WG, origin.getX() + dx, origin.getZ() + dz) - 1;
                if (Math.abs(top - waterY) > MAX_HEIGHT_DIFFERENCE) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void sealEdges(WorldGenLevel level, BlockPos origin, int reach, float radiusX, float radiusZ, float phase, float wobble, int waterY) {
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        for (int dx = -reach; dx <= reach; dx++) {
            for (int dz = -reach; dz <= reach; dz++) {
                if (distance(dx, dz, radiusX, radiusZ, phase, wobble) <= 1.0F || !touchesPond(dx, dz, radiusX, radiusZ, phase, wobble)) {
                    continue;
                }
                for (int y = waterY - 3; y <= waterY; y++) {
                    pos.set(origin.getX() + dx, y, origin.getZ() + dz);
                    BlockState state = level.getBlockState(pos);
                    if (state.isAir() || !state.getFluidState().isEmpty()) {
                        level.setBlock(pos, Blocks.SAND.defaultBlockState(), 2);
                    }
                }
            }
        }
    }

    private static boolean touchesPond(int dx, int dz, float radiusX, float radiusZ, float phase, float wobble) {
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            if (distance(dx + direction.getStepX(), dz + direction.getStepZ(), radiusX, radiusZ, phase, wobble) <= 1.0F) {
                return true;
            }
        }
        return false;
    }

    private static void placeRim(WorldGenLevel level, RandomSource random, BlockPos origin, int reach, float radiusX, float radiusZ, float phase, float wobble, int waterY) {
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        for (int dx = -reach; dx <= reach; dx++) {
            for (int dz = -reach; dz <= reach; dz++) {
                float distance = distance(dx, dz, radiusX, radiusZ, phase, wobble);
                if (distance <= 1.0F || distance > 1.7F) {
                    continue;
                }
                int x = origin.getX() + dx;
                int z = origin.getZ() + dz;
                int top = level.getHeight(Heightmap.Types.WORLD_SURFACE_WG, x, z) - 1;
                pos.set(x, top, z);
                BlockState ground = level.getBlockState(pos);
                if (!ground.is(Blocks.SAND) && !ground.is(Blocks.RED_SAND) && !ground.is(Blocks.GRASS_BLOCK)) {
                    continue;
                }
                boolean inner = distance < 1.35F;
                if (inner || random.nextFloat() < 0.6F) {
                    level.setBlock(pos, Blocks.GRASS_BLOCK.defaultBlockState(), 2);
                }
                BlockPos above = pos.above();
                if (!level.getBlockState(above).isAir()) {
                    continue;
                }
                if (inner && touchesPond(dx, dz, radiusX, radiusZ, phase, wobble) && random.nextFloat() < 0.35F) {
                    placeShorePlant(level, above, random);
                } else if (random.nextFloat() < 0.45F) {
                    level.setBlock(above, random.nextInt(4) == 0 ? Blocks.FERN.defaultBlockState() : Blocks.SHORT_GRASS.defaultBlockState(), 2);
                }
            }
        }
    }

    private static void placeShorePlant(WorldGenLevel level, BlockPos base, RandomSource random) {
        if (random.nextBoolean()) {
            BlockState cattail = (random.nextBoolean() ? ObjectRegistry.CATTAIL : ObjectRegistry.REED).get().defaultBlockState();
            if (cattail.canSurvive(level, base) && level.getBlockState(base.above()).isAir()) {
                DoublePlantBlock.placeAt(level, cattail, base, 2);
                return;
            }
        }
        int height = 1 + random.nextInt(3);
        for (int i = 0; i < height && level.getBlockState(base.above(i)).isAir(); i++) {
            level.setBlock(base.above(i), Blocks.SUGAR_CANE.defaultBlockState(), 2);
        }
    }

    private static void placeLilyPads(WorldGenLevel level, RandomSource random, BlockPos origin, int reach, float radiusX, float radiusZ, float phase, float wobble, int waterY) {
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        for (int dx = -reach; dx <= reach; dx++) {
            for (int dz = -reach; dz <= reach; dz++) {
                if (distance(dx, dz, radiusX, radiusZ, phase, wobble) > 0.9F || random.nextFloat() > 0.08F) {
                    continue;
                }
                pos.set(origin.getX() + dx, waterY + 1, origin.getZ() + dz);
                BlockState pad = random.nextInt(3) == 0 ? FloraRegistry.FLOWERING_LILY_PAD.get().defaultBlockState() : Blocks.LILY_PAD.defaultBlockState();
                level.setBlock(pos, pad, 2);
            }
        }
    }

    private static void placePalms(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos origin, float radius) {
        WorldGenLevel level = context.level();
        RandomSource random = context.random();
        ConfiguredFeature<?, ?> palm = level.registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE).get(ConfiguredFeatures.FAN_PALM_TREE_KEY);
        if (palm == null) {
            return;
        }
        int count = MIN_PALMS + random.nextInt(MAX_PALMS - MIN_PALMS + 1);
        float start = random.nextFloat() * Mth.TWO_PI;
        for (int i = 0; i < count; i++) {
            float angle = start + i * Mth.TWO_PI / count + (random.nextFloat() - 0.5F) * 0.5F;
            float distance = radius + 2.0F + random.nextFloat() * 2.0F;
            int x = origin.getX() + Mth.floor(Mth.cos(angle) * distance);
            int z = origin.getZ() + Mth.floor(Mth.sin(angle) * distance);
            BlockPos base = new BlockPos(x, level.getHeight(Heightmap.Types.WORLD_SURFACE_WG, x, z), z);
            BlockState ground = level.getBlockState(base.below());
            if (ground.is(Blocks.GRASS_BLOCK) || ground.is(Blocks.SAND) || ground.is(Blocks.RED_SAND)) {
                palm.place(level, context.chunkGenerator(), random, base);
            }
        }
    }
}
