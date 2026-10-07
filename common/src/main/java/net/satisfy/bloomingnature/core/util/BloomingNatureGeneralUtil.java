package net.satisfy.bloomingnature.core.util;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.block.Block;


public class BloomingNatureGeneralUtil {
    public static class BloomingNatureVillagerUtil {
        public BloomingNatureVillagerUtil() {
        }

        public static class SellItemFactory implements VillagerTrades.ItemListing {
            private final ItemStack sell;
            private final int price;
            private final int count;
            private final int maxUses;
            private final int experience;
            private final float multiplier;

            public SellItemFactory(Block block, int price, int count, int maxUses, int experience) {
                this(new ItemStack(block), price, count, maxUses, experience);
            }

            public SellItemFactory(ItemStack stack, int price, int count, int maxUses, int experience) {
                this(stack, price, count, maxUses, experience, 0.05F);
            }

            public SellItemFactory(ItemStack stack, int price, int count, int maxUses, int experience, float multiplier) {
                this.sell = stack;
                this.price = price;
                this.count = count;
                this.maxUses = maxUses;
                this.experience = experience;
                this.multiplier = multiplier;
            }

            @Override
            public MerchantOffer getOffer(Entity entity, RandomSource random) {
                return new MerchantOffer(
                        new ItemCost(Items.EMERALD, this.price),
                        new ItemStack(this.sell.getItem(), this.count),
                        this.maxUses,
                        this.experience,
                        this.multiplier
                );
            }
        }
    }
}
