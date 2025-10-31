package net.satisfy.bloomingnature.core.block;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class BloomingLeavesBlock extends LeavesBlock {
    public static final BooleanProperty BLOOMING = BooleanProperty.create("blooming");

    public BloomingLeavesBlock(BlockBehaviour.Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(BLOOMING, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(BLOOMING);
    }

    @Override
    public @NotNull ItemInteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (itemStack.is(Items.SHEARS) && state.getValue(BLOOMING)) {
            level.setBlock(pos, state.setValue(BLOOMING, false), 2);
            if (!player.isCreative()) itemStack.hurtAndBreak(1, player, hand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
            return ItemInteractionResult.sidedSuccess(level.isClientSide());
        }
        if (itemStack.is(ItemTags.SMALL_FLOWERS) && !state.getValue(BLOOMING)) {
            level.setBlock(pos, state.setValue(BLOOMING, true), 2);
            if (!player.isCreative()) itemStack.shrink(1);
            return ItemInteractionResult.sidedSuccess(level.isClientSide());
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public float getShadeBrightness(BlockState state, BlockGetter level, BlockPos pos) {
        return state.getValue(BLOOMING) ? 1.0F : 0.8F;
    }
}