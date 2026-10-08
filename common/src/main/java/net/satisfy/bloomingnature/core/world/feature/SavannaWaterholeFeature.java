package net.satisfy.bloomingnature.core.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.satisfy.bloomingnature.core.registry.ObjectRegistry;

public class SavannaWaterholeFeature extends Feature<NoneFeatureConfiguration> {
    private static final int MAX_HEIGHT_DIFFERENCE = 2;

    public SavannaWaterholeFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        RandomSource random = context.random();
        BlockPos origin = context.origin();
        float radiusX = 3.0F + random.nextFloat() * 3.0F;
        float radiusZ = 3.0F + random.nextFloat() * 3.0F;
        int reach = Mth.ceil(Math.max(radiusX, radiusZ) * 1.8F) + 1;
        int waterY = origin.getY() - 1;

        if (!isFlat(level, origin, reach, waterY) || !level.getBlockState(origin.below()).getFluidState().isEmpty()) {
            return false;
        }

        float phase = random.nextFloat() * Mth.TWO_PI;
        float wobble = 0.15F + random.nextFloat() * 0.12F;
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        for (int dx = -reach; dx <= reach; dx++) {
            for (int dz = -reach; dz <= reach; dz++) {
                float distance = distance(dx, dz, radiusX, radiusZ, phase, wobble);
                if (distance > 1.0F) {
                    continue;
                }
                int depth = distance < 0.5F ? 2 : 1;
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
                BlockState floor = distance < 0.55F ? (random.nextInt(3) == 0 ? Blocks.MUD : Blocks.CLAY).defaultBlockState() : Blocks.MUD.defaultBlockState();
                level.setBlock(pos.set(x, waterY - depth, z), floor, 2);
            }
        }

        sealEdges(level, origin, reach, radiusX, radiusZ, phase, wobble, waterY);
        placeShore(level, random, origin, reach, radiusX, radiusZ, phase, wobble);
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

    private static boolean touchesPond(int dx, int dz, float radiusX, float radiusZ, float phase, float wobble) {
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            if (distance(dx + direction.getStepX(), dz + direction.getStepZ(), radiusX, radiusZ, phase, wobble) <= 1.0F) {
                return true;
            }
        }
        return false;
    }

    private static void sealEdges(WorldGenLevel level, BlockPos origin, int reach, float radiusX, float radiusZ, float phase, float wobble, int waterY) {
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        for (int dx = -reach; dx <= reach; dx++) {
            for (int dz = -reach; dz <= reach; dz++) {
                if (distance(dx, dz, radiusX, radiusZ, phase, wobble) <= 1.0F || !touchesPond(dx, dz, radiusX, radiusZ, phase, wobble)) {
                    continue;
                }
                for (int y = waterY - 2; y <= waterY; y++) {
                    pos.set(origin.getX() + dx, y, origin.getZ() + dz);
                    BlockState state = level.getBlockState(pos);
                    if (state.isAir() || !state.getFluidState().isEmpty()) {
                        level.setBlock(pos, Blocks.MUD.defaultBlockState(), 2);
                    }
                }
            }
        }
    }

    private static void placeShore(WorldGenLevel level, RandomSource random, BlockPos origin, int reach, float radiusX, float radiusZ, float phase, float wobble) {
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        for (int dx = -reach; dx <= reach; dx++) {
            for (int dz = -reach; dz <= reach; dz++) {
                float distance = distance(dx, dz, radiusX, radiusZ, phase, wobble);
                if (distance <= 1.0F || distance > 1.8F) {
                    continue;
                }
                int x = origin.getX() + dx;
                int z = origin.getZ() + dz;
                int top = level.getHeight(Heightmap.Types.WORLD_SURFACE_WG, x, z) - 1;
                pos.set(x, top, z);
                BlockState ground = level.getBlockState(pos);
                if (!ground.is(Blocks.GRASS_BLOCK) && !ground.is(Blocks.DIRT) && !ground.is(Blocks.COARSE_DIRT) && !ground.is(Blocks.SAND) && !ground.is(Blocks.RED_SAND)) {
                    continue;
                }

                BlockState shore = shoreBlock(distance, random);
                if (shore != null) {
                    level.setBlock(pos, shore, 2);
                }

                BlockPos above = pos.above();
                if (!level.getBlockState(above).isAir()) {
                    continue;
                }
                if (distance < 1.3F && touchesPond(dx, dz, radiusX, radiusZ, phase, wobble) && random.nextFloat() < 0.25F) {
                    placeReed(level, above, random);
                } else if (distance > 1.3F && random.nextFloat() < 0.3F && level.getBlockState(pos).is(Blocks.GRASS_BLOCK)) {
                    level.setBlock(above, Blocks.SHORT_GRASS.defaultBlockState(), 2);
                }
            }
        }
    }

    private static BlockState shoreBlock(float distance, RandomSource random) {
        if (distance < 1.2F) {
            return random.nextInt(4) == 0 ? Blocks.CLAY.defaultBlockState() : Blocks.MUD.defaultBlockState();
        }
        if (distance < 1.45F) {
            return switch (random.nextInt(5)) {
                case 0, 1 -> Blocks.MUD.defaultBlockState();
                case 2 -> Blocks.COARSE_DIRT.defaultBlockState();
                default -> Blocks.SAND.defaultBlockState();
            };
        }
        if (distance < 1.65F && random.nextFloat() < 0.5F) {
            return random.nextBoolean() ? Blocks.SAND.defaultBlockState() : Blocks.COARSE_DIRT.defaultBlockState();
        }
        return null;
    }

    private static void placeReed(WorldGenLevel level, BlockPos base, RandomSource random) {
        BlockState reed = (random.nextBoolean() ? ObjectRegistry.CATTAIL : ObjectRegistry.REED).get().defaultBlockState();
        if (reed.canSurvive(level, base) && level.getBlockState(base.above()).isAir()) {
            DoublePlantBlock.placeAt(level, reed, base, 2);
        }
    }
}
