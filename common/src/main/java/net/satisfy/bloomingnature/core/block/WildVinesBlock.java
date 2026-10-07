package net.satisfy.bloomingnature.core.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class WildVinesBlock extends VineBlock {
    public static final MapCodec<WildVinesBlock> CODEC = simpleCodec(WildVinesBlock::new);
    public static final EnumProperty<Part> PART = EnumProperty.create("part", Part.class);
    public static final BooleanProperty NIGHT = BooleanProperty.create("night");
    private static final int LIGHT = 3;
    private static final long DAY_LENGTH = 24000L;
    private static final long NIGHT_START = 12500L;
    private static final long NIGHT_END = 23500L;

    public WildVinesBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(PART, Part.BOTTOM).setValue(NIGHT, false));
    }

    @Override
    public MapCodec<VineBlock> codec() {
        return (MapCodec) CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(PART, NIGHT);
    }

    public static int light(BlockState state) {
        return state.getValue(NIGHT) ? LIGHT : 0;
    }

    private BlockState withContext(BlockState state, LevelAccessor level, BlockPos pos) {
        boolean above = level.getBlockState(pos.above()).is(this);
        boolean below = level.getBlockState(pos.below()).is(this);
        Part part = !below ? Part.BOTTOM : above ? Part.MID : Part.TOP;
        return state.setValue(PART, part);
    }

    private static boolean isNight(Level level) {
        long time = level.getDayTime() % DAY_LENGTH;
        return time > NIGHT_START && time < NIGHT_END;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        return state == null ? null : withContext(state, context.getLevel(), context.getClickedPos()).setValue(NIGHT, isNight(context.getLevel()));
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighbor, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        BlockState updated = super.updateShape(state, direction, neighbor, level, pos, neighborPos);
        return updated.is(this) ? withContext(updated, level, pos) : updated;
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        super.onPlace(state, level, pos, oldState, movedByPiston);
        BlockState fixed = withContext(state, level, pos).setValue(NIGHT, isNight(level));
        if (fixed != state) {
            level.setBlock(pos, fixed, Block.UPDATE_CLIENTS);
        }
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        boolean night = isNight(level);
        if (state.getValue(NIGHT) != night) {
            level.setBlock(pos, state.setValue(NIGHT, night), Block.UPDATE_CLIENTS);
            return;
        }
        super.randomTick(state, level, pos, random);
    }

    public enum Part implements StringRepresentable {
        TOP("top"),
        MID("mid"),
        BOTTOM("bottom");

        private final String name;

        Part(String name) {
            this.name = name;
        }

        @Override
        public String getSerializedName() {
            return name;
        }
    }
}
