package net.satisfy.bloomingnature.core.world.feature.configured.tree.decorator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.satisfy.bloomingnature.core.registry.PlacerTypeRegistry;
import org.jetbrains.annotations.NotNull;

public final class MushroomDecorator extends TreeDecorator {
    public static final MapCodec<MushroomDecorator> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(Codec.FLOAT.fieldOf("chance").orElse(0.18f).forGetter(d -> d.chance), Codec.INT.fieldOf("min_y_offset").orElse(0).forGetter(d -> d.minYOffset), Codec.INT.fieldOf("max_y_offset").orElse(4).forGetter(d -> d.maxYOffset), Codec.INT.fieldOf("max_radius").orElse(1).forGetter(d -> d.maxRadius), Codec.INT.fieldOf("max_thickness").orElse(2).forGetter(d -> d.maxThickness), BlockStateProvider.CODEC.fieldOf("block_provider").orElse(BlockStateProvider.simple(Blocks.BROWN_MUSHROOM_BLOCK)).forGetter(d -> d.provider)).apply(i, MushroomDecorator::new));

    private final float chance;
    private final int minYOffset;
    private final int maxYOffset;
    private final int maxRadius;
    private final int maxThickness;
    private final BlockStateProvider provider;

    public MushroomDecorator(float chance, int minYOffset, int maxYOffset, int maxRadius, int maxThickness, BlockStateProvider provider) {
        this.chance = chance;
        this.minYOffset = Math.max(0, minYOffset);
        this.maxYOffset = Math.max(this.minYOffset, maxYOffset);
        this.maxRadius = Math.max(1, maxRadius);
        this.maxThickness = Math.max(1, maxThickness);
        this.provider = provider;
    }

    @Override
    protected @NotNull TreeDecoratorType<?> type() {
        return PlacerTypeRegistry.MUSHROOM_DECORATOR.get();
    }

    @Override
    public void place(Context context) {
        var random = context.random();
        int baseY = context.logs().stream().mapToInt(BlockPos::getY).min().orElse(Integer.MAX_VALUE);
        int span = Math.max(0, maxYOffset - minYOffset);
        int targetOffset = minYOffset + (span == 0 ? 0 : random.nextInt(span + 1));

        for (var logPos : context.logs()) {
            if (logPos.getY() - baseY != targetOffset) continue;
            if (random.nextFloat() >= chance) continue;

            var facing = Direction.Plane.HORIZONTAL.getRandomDirection(random);
            int radius = 1 + random.nextInt(maxRadius);
            int thickness = 1 + random.nextInt(maxThickness);
            BlockState mushroomState = provider.getState(random, logPos);

            var attachPos = logPos.relative(facing);
            if (!context.isAir(attachPos)) continue;

            placeShelf(context, attachPos, facing, radius, thickness, mushroomState);
            break;
        }
    }

    private void placeShelf(Context context, BlockPos base, Direction facing, int radius, int thickness, BlockState state) {
        var random = context.random();
        var widthAxis = facing.getAxis() == Direction.Axis.X ? Direction.NORTH : Direction.WEST;

        for (int w = -radius; w <= radius; w++) {
            double mask = (w * w) / (double) (radius * radius);
            if (mask > 1.0) continue;
            for (int t = 0; t < thickness; t++) {
                var p = base.relative(widthAxis, w).relative(facing, t);
                if (!context.isAir(p)) continue;
                if (context.logs().contains(p)) continue;
                if (random.nextFloat() < 0.12f) continue;
                context.setBlock(p, state);
            }
        }
    }
}