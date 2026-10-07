package net.satisfy.bloomingnature.core.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.satisfy.foundation.block.WindowBlock;
import net.satisfy.foundation.wood.BoatWood;
import net.satisfy.foundation.wood.WoodBoatItem;
import net.satisfy.foundation.wood.WoodCeilingHangingSignBlock;
import net.satisfy.foundation.wood.WoodStandingSignBlock;
import net.satisfy.foundation.wood.WoodWallHangingSignBlock;
import net.satisfy.foundation.wood.WoodWallSignBlock;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static net.satisfy.bloomingnature.core.registry.ObjectRegistry.*;

public final class WoodSet {
    private static final List<WoodSet> ALL = new ArrayList<>();

    public final String name;
    public final WoodType woodType;

    public final @Nullable RegistrySupplier<Block> log, wood, strippedLog, strippedWood;
    public final RegistrySupplier<Block> planks, stairs, slab, fence, fenceGate, door, trapdoor, pressurePlate, button, window;
    public final @Nullable RegistrySupplier<Block> leaves, sapling, pottedSapling;
    public final RegistrySupplier<Block> sign, wallSign, hangingSign, wallHangingSign;
    public final RegistrySupplier<Item> signItem, hangingSignItem, boat, chestBoat;
    public final BoatWood boatWood;

    WoodSet(String name, WoodType woodType, ResourceLocation id, boolean logs, @Nullable Supplier<Block> leaves, @Nullable String saplingName, @Nullable Supplier<Block> sapling) {
        this.name = name;
        this.woodType = woodType;

        this.log = logs ? registerWithItem(name + "_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).sound(SoundType.WOOD).strength(2.0f))) : null;
        this.wood = logs ? registerWithItem(name + "_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).sound(SoundType.WOOD).strength(2.0f))) : null;
        this.strippedWood = logs ? registerWithItem("stripped_" + name + "_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).sound(SoundType.WOOD).strength(2.0f))) : null;
        this.strippedLog = logs ? registerWithItem("stripped_" + name + "_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).sound(SoundType.WOOD).strength(2.0f))) : null;

        this.planks = registerWithItem(name + "_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(2.0f, 3.0f).mapColor(MapColor.TERRACOTTA_ORANGE)));
        this.stairs = registerWithItem(name + "_stairs", () -> new StairBlock(this.planks.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
        this.pressurePlate = registerWithItem(name + "_pressure_plate", () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().noCollission().strength(0.5f).sound(SoundType.WOOD).mapColor(this.planks.get().defaultMapColor())));
        this.door = registerWithItem(name + "_door", () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().strength(3.0f).sound(SoundType.WOOD).noOcclusion().mapColor(this.planks.get().defaultMapColor())));
        this.fenceGate = registerWithItem(name + "_fence_gate", () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.of().strength(2.0f, 3.0f).sound(SoundType.WOOD).mapColor(this.planks.get().defaultMapColor())));
        this.slab = registerWithItem(name + "_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
        this.button = registerWithItem(name + "_button", WoodSet::woodenButton);
        this.trapdoor = registerWithItem(name + "_trapdoor", () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));
        this.fence = registerWithItem(name + "_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().strength(2.0f, 3.0f).sound(SoundType.WOOD)));
        this.window = registerWithItem(name + "_window", () -> new WindowBlock(BlockBehaviour.Properties.of().strength(0.2f).randomTicks().sound(SoundType.GLASS).noOcclusion().isViewBlocking((state, world, pos) -> false).isSuffocating((state, world, pos) -> false).mapColor(MapColor.GRASS).pushReaction(PushReaction.IGNORE)));

        this.leaves = leaves != null ? registerWithItem(name + "_leaves", leaves) : null;
        this.sapling = saplingName != null ? registerWithItem(saplingName, sapling) : null;
        this.pottedSapling = saplingName != null ? registerWithoutItem("potted_" + saplingName, () -> new FlowerPotBlock(this.sapling.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT))) : null;

        this.sign = registerWithoutItem(name + "_sign", () -> new WoodStandingSignBlock(woodType, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN), EntityTypeRegistry.MOD_SIGN));
        this.wallSign = registerWithoutItem(name + "_wall_sign", () -> new WoodWallSignBlock(woodType, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN), EntityTypeRegistry.MOD_SIGN));
        this.hangingSign = registerWithoutItem(name + "_hanging_sign", () -> new WoodCeilingHangingSignBlock(woodType, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN), EntityTypeRegistry.MOD_HANGING_SIGN));
        this.wallHangingSign = registerWithoutItem(name + "_wall_hanging_sign", () -> new WoodWallHangingSignBlock(woodType, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN), EntityTypeRegistry.MOD_HANGING_SIGN));
        this.signItem = ITEMS.register(name + "_sign", () -> new SignItem(new Item.Properties().stacksTo(16), this.sign.get(), this.wallSign.get()));
        this.hangingSignItem = ITEMS.register(name + "_hanging_sign", () -> new HangingSignItem(this.hangingSign.get(), this.wallHangingSign.get(), new Item.Properties().stacksTo(16)));

        this.boat = ITEMS.register(name + "_boat", () -> new WoodBoatItem(EntityTypeRegistry.MOD_BOAT, id, new Item.Properties()));
        this.chestBoat = ITEMS.register(name + "_chest_boat", () -> new WoodBoatItem(EntityTypeRegistry.MOD_CHEST_BOAT, id, new Item.Properties()));

        this.boatWood = BoatWood.register(id, this.boat, this.chestBoat);

        ALL.add(this);
    }

    public static List<WoodSet> all() {
        return Collections.unmodifiableList(ALL);
    }

    public void forEachPlankBlock(Consumer<Block> consumer) {
        for (RegistrySupplier<Block> supplier : List.of(planks, slab, stairs, fence, fenceGate)) {
            consumer.accept(supplier.get());
        }
    }

    public void forEachLogBlock(Consumer<Block> consumer) {
        if (log == null) {
            return;
        }
        for (RegistrySupplier<Block> supplier : List.of(log, wood, strippedLog, strippedWood)) {
            consumer.accept(supplier.get());
        }
    }

    private static ButtonBlock woodenButton() {
        return new ButtonBlock(BlockSetType.OAK, 30, BlockBehaviour.Properties.of().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY).requiredFeatures(FeatureFlags.VANILLA));
    }
}
