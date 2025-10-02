package net.satisfy.bloomingnature.core.util;

import dev.architectury.platform.Platform;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;


import java.util.*;
import java.util.function.Supplier;

public class BloomingNatureGeneralUtil {
    public static <T extends Block> RegistrySupplier<T> registerWithItem(DeferredRegister<Block> registerB, Registrar<Block> registrarB, DeferredRegister<Item> registerI, Registrar<Item> registrarI, ResourceLocation name, Supplier<T> block) {
        RegistrySupplier<T> toReturn = registerWithoutItem(registerB, registrarB, name, block);
        registerItem(registerI, registrarI, name, () -> new BlockItem(toReturn.get(), new Item.Properties()));
        return toReturn;
    }

    public static <T extends Block> RegistrySupplier<T> registerWithoutItem(DeferredRegister<Block> register, Registrar<Block> registrar, ResourceLocation path, Supplier<T> block) {
        return Platform.isNeoForge() ? register.register(path.getPath(), block) : registrar.register(path, block);
    }

    public static <T extends Item> RegistrySupplier<T> registerItem(DeferredRegister<Item> register, Registrar<Item> registrar, ResourceLocation path, Supplier<T> itemSupplier) {
        return Platform.isNeoForge() ? register.register(path.getPath(), itemSupplier) : registrar.register(path, itemSupplier);
    }

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
