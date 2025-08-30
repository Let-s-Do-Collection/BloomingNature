package net.satisfy.bloomingnature.core.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.satisfy.bloomingnature.core.entity.TermiteEntity;
import net.satisfy.bloomingnature.core.registry.EntityTypeRegistry;

public class TermiteBlock extends Block {
    public TermiteBlock(Block block, BlockBehaviour.Properties properties) {
        super(properties.destroyTime(block.defaultDestroyTime() / 2.0F).explosionResistance(0.75F));
    }

    private void spawnInfestation(ServerLevel serverLevel, BlockPos blockPos) {
        TermiteEntity termiteEntity = new TermiteEntity(EntityTypeRegistry.TERMITE.get(), serverLevel);
        termiteEntity.moveTo(blockPos.getX() + 0.4, blockPos.getY(), blockPos.getZ() + 0.5, 0.0F, 0.0F);
        serverLevel.addFreshEntity(termiteEntity);
        termiteEntity.spawnAnim();
    }

    @Override
    public void spawnAfterBreak(BlockState state, ServerLevel level, BlockPos pos, ItemStack stack, boolean dropExperience) {
        super.spawnAfterBreak(state, level, pos, stack, dropExperience);

        if (level.getGameRules().getBoolean(GameRules.RULE_DOBLOCKDROPS) && EnchantmentHelper.getItemEnchantmentLevel(level.registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.SILK_TOUCH), stack) == 0) {

            if (level.random.nextFloat() < 0.5F) {
                int numTermites = level.random.nextInt(2) + 3;
                for (int i = 0; i < numTermites; i++) {
                    spawnInfestation(level, pos);
                }
            }
        }
    }
}
