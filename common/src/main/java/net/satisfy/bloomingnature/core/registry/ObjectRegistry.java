package net.satisfy.bloomingnature.core.registry;

import dev.architectury.core.item.ArchitecturySpawnEggItem;
import dev.architectury.registry.fuel.FuelRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.satisfy.bloomingnature.BloomingNature;
import net.satisfy.bloomingnature.core.block.*;
import net.satisfy.bloomingnature.core.block.MegaSaplingBlock;
import net.satisfy.foundation.util.RegistryUtil;
import net.satisfy.bloomingnature.core.util.BloomingNatureWoodType;
import net.satisfy.bloomingnature.core.config.BloomingNatureConfig;
import net.satisfy.foundation.banner.BannerSettings;
import net.satisfy.foundation.block.WindowBlock;
import net.satisfy.foundation.wood.*;
import net.satisfy.foundation.banner.CompletionistBannerBlock;
import net.satisfy.foundation.banner.CompletionistWallBannerBlock;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class ObjectRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BloomingNature.MOD_ID, Registries.ITEM);
    public static final Registrar<Item> ITEM_REGISTRAR = ITEMS.getRegistrar();
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BloomingNature.MOD_ID, Registries.BLOCK);
    public static final Registrar<Block> BLOCK_REGISTRAR = BLOCKS.getRegistrar();

    public static final RegistrySupplier<Item> WANDERING_GARDENER_SPAWN_EGG = registerItem("wandering_gardener_spawn_egg", () -> new ArchitecturySpawnEggItem(EntityTypeRegistry.WANDERING_GARDENER, -1, -1, getSettings()));
    public static final RegistrySupplier<Block> BLOOMING_OAK_SAPLING = registerWithItem("blooming_oak_sapling", () -> new SaplingBlock(new TreeGrower("oak", Optional.empty(), Optional.of(configuredFeatureKey("trees/oak/blooming_oak_tree_tall")), Optional.empty()), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final RegistrySupplier<Block> POTTED_BLOOMING_OAK_SAPLING = registerWithoutItem("potted_blooming_oak_sapling", () -> new FlowerPotBlock(BLOOMING_OAK_SAPLING.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));

    public static final WoodSet ASPEN = new WoodSet("aspen", BloomingNatureWoodType.ASPEN, BloomingNatureWoodType.ASPEN_ID, true, () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)), "aspen_sapling", sapling("aspen_tree_branched", "trees/aspen/aspen_tree_mid", Blocks.OAK_SAPLING));
    public static final WoodSet BAOBAB = new WoodSet("baobab", BloomingNatureWoodType.BAOBAB, BloomingNatureWoodType.BAOBAB_ID, true, () -> new ExtendedLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)), "baobab_sapling", () -> new MegaSaplingBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SAPLING), configuredFeatureKey("trees/baobab/baobab_tree")));
    public static final WoodSet LARCH = new WoodSet("larch", BloomingNatureWoodType.LARCH, BloomingNatureWoodType.LARCH_ID, true, () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)), "larch_sapling", sapling("larch", "trees/larch/larch_tree", Blocks.SPRUCE_SAPLING));
    public static final WoodSet EBONY = new WoodSet("ebony", BloomingNatureWoodType.EBONY, BloomingNatureWoodType.EBONY_ID, true, () -> new ExtendedLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)), "ebony_sapling", sapling("ebony_tree", "trees/ebony/ebony_tree_small", Blocks.OAK_SAPLING));
    public static final WoodSet CHESTNUT = new WoodSet("chestnut", BloomingNatureWoodType.CHESTNUT, BloomingNatureWoodType.CHESTNUT_ID, true, () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)), "chestnut_sapling", sapling("plains_chestnut_tree", "trees/chestnut/chestnut_tree", Blocks.OAK_SAPLING));
    public static final WoodSet SWAMP_OAK = new WoodSet("swamp_oak", BloomingNatureWoodType.SWAMP_OAK, BloomingNatureWoodType.SWAMP_OAK_ID, true, () -> new ExtendedLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)), "swamp_oak_sapling", sapling("forest_oak_straight", "trees/swamp_oak/swamp_oak_tree_mid", Blocks.OAK_SAPLING));
    public static final WoodSet SWAMP_CYPRESS = new WoodSet("swamp_cypress", BloomingNatureWoodType.SWAMP_CYPRESS, BloomingNatureWoodType.SWAMP_CYPRESS_ID, true, () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)), "swamp_cypress_sapling", sapling("swamp_cypress", "trees/swamp_cypress/swamp_cypress_tree", Blocks.OAK_SAPLING));
    public static final WoodSet FAN_PALM = new WoodSet("fan_palm", BloomingNatureWoodType.FAN_PALM, BloomingNatureWoodType.FAN_PALM_ID, true, () -> new ExtendedLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)), "fan_palm_sprout", FanPalmSproutBlock::new);
    public static final WoodSet FIR = new WoodSet("fir", BloomingNatureWoodType.FIR, BloomingNatureWoodType.FIR_ID, true, () -> new SnowyLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)), "fir_sapling", sapling("snowy_taiga_fir", "trees/fir/fir_tree_mid_green", Blocks.OAK_SAPLING));
    public static final WoodSet CACTUS = new WoodSet("cactus", BloomingNatureWoodType.CACTUS, BloomingNatureWoodType.CACTUS_ID, false, null, null, null);
    public static final WoodSet CYPRESS = new WoodSet("cypress", BloomingNatureWoodType.CYPRESS, BloomingNatureWoodType.CYPRESS_ID, true, () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)), "cypress_sapling", sapling("cypress", "trees/cypress/cypress_tree", Blocks.SPRUCE_SAPLING));

    private static Supplier<Block> sapling(String grower, String feature, Block copy) {
        return () -> new SaplingBlock(new TreeGrower(grower, Optional.empty(), Optional.of(configuredFeatureKey(feature)), Optional.empty()), BlockBehaviour.Properties.ofFullCopy(copy));
    }
    public static final RegistrySupplier<Block> ORANGE_LEAVES = registerWithItem("orange_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of().strength(0.2f).randomTicks().sound(SoundType.GRASS).noOcclusion().isViewBlocking((state, world, pos) -> false).isSuffocating((state, world, pos) -> false).mapColor(MapColor.COLOR_ORANGE)));
    public static final RegistrySupplier<Block> TRAVERTIN = registerWithItem("travertin", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final RegistrySupplier<Block> TRAVERTIN_STAIRS = registerWithItem("travertin_stairs", () -> new StairBlock(TRAVERTIN.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(TRAVERTIN.get())));
    public static final RegistrySupplier<Block> TRAVERTIN_SLAB = registerWithItem("travertin_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(TRAVERTIN.get())));
    public static final RegistrySupplier<Block> TRAVERTIN_WALL = registerWithItem("travertin_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(TRAVERTIN.get())));
    public static final RegistrySupplier<Block> COBBLED_TRAVERTIN = registerWithItem("cobbled_travertin", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final RegistrySupplier<Block> COBBLED_TRAVERTIN_STAIRS = registerWithItem("cobbled_travertin_stairs", () -> new StairBlock(COBBLED_TRAVERTIN.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(TRAVERTIN.get())));
    public static final RegistrySupplier<Block> COBBLED_TRAVERTIN_SLAB = registerWithItem("cobbled_travertin_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(COBBLED_TRAVERTIN.get())));
    public static final RegistrySupplier<Block> COBBLED_TRAVERTIN_WALL = registerWithItem("cobbled_travertin_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(COBBLED_TRAVERTIN.get())));
    public static final RegistrySupplier<Block> CHISELED_TRAVERTIN = registerWithItem("chiseled_travertin", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final RegistrySupplier<Block> TRAVERTIN_BRICKS = registerWithItem("travertin_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final RegistrySupplier<Block> TRAVERTIN_BRICK_STAIRS = registerWithItem("travertin_brick_stairs", () -> new StairBlock(TRAVERTIN_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(TRAVERTIN.get())));
    public static final RegistrySupplier<Block> TRAVERTIN_BRICK_SLAB = registerWithItem("travertin_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(TRAVERTIN_BRICKS.get())));
    public static final RegistrySupplier<Block> TRAVERTIN_BRICK_WALL = registerWithItem("travertin_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(TRAVERTIN_BRICKS.get())));
    public static final RegistrySupplier<Block> CRACKED_TRAVERTIN_BRICKS = registerWithItem("cracked_travertin_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final RegistrySupplier<Block> MOSSY_TRAVERTIN_BRICKS = registerWithItem("mossy_travertin_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final RegistrySupplier<Block> MOSSY_TRAVERTIN_BRICK_STAIRS = registerWithItem("mossy_travertin_brick_stairs", () -> new StairBlock(MOSSY_TRAVERTIN_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(TRAVERTIN.get())));
    public static final RegistrySupplier<Block> MOSSY_TRAVERTIN_BRICK_SLAB = registerWithItem("mossy_travertin_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_TRAVERTIN_BRICKS.get())));
    public static final RegistrySupplier<Block> MOSSY_TRAVERTIN_BRICK_WALL = registerWithItem("mossy_travertin_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_TRAVERTIN_BRICKS.get())));
    public static final RegistrySupplier<Block> MOSSY_COBBLED_TRAVERTIN = registerWithItem("mossy_cobbled_travertin", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final RegistrySupplier<Block> MOSSY_COBBLED_TRAVERTIN_STAIRS = registerWithItem("mossy_cobbled_travertin_stairs", () -> new StairBlock(MOSSY_TRAVERTIN_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(TRAVERTIN.get())));
    public static final RegistrySupplier<Block> MOSSY_COBBLED_TRAVERTIN_SLAB = registerWithItem("mossy_cobbled_travertin_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_TRAVERTIN_BRICKS.get())));
    public static final RegistrySupplier<Block> MOSSY_COBBLED_TRAVERTIN_WALL = registerWithItem("mossy_cobbled_travertin_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_TRAVERTIN_BRICKS.get())));
    public static final RegistrySupplier<Block> MOSSY_CHISELED_TRAVERTIN = registerWithItem("mossy_chiseled_travertin", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final RegistrySupplier<Block> LATERIT = registerWithItem("laterit", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final RegistrySupplier<Block> LATERIT_STAIRS = registerWithItem("laterit_stairs", () -> new StairBlock(LATERIT.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(TRAVERTIN.get())));
    public static final RegistrySupplier<Block> LATERIT_SLAB = registerWithItem("laterit_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(LATERIT.get())));
    public static final RegistrySupplier<Block> LATERIT_WALL = registerWithItem("laterit_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(LATERIT.get())));
    public static final RegistrySupplier<Block> COBBLED_LATERIT = registerWithItem("cobbled_laterit", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final RegistrySupplier<Block> COBBLED_LATERIT_STAIRS = registerWithItem("cobbled_laterit_stairs", () -> new StairBlock(COBBLED_LATERIT.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(TRAVERTIN.get())));
    public static final RegistrySupplier<Block> COBBLED_LATERIT_SLAB = registerWithItem("cobbled_laterit_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(COBBLED_LATERIT.get())));
    public static final RegistrySupplier<Block> COBBLED_LATERIT_WALL = registerWithItem("cobbled_laterit_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(COBBLED_LATERIT.get())));
    public static final RegistrySupplier<Block> CHISELED_LATERIT = registerWithItem("chiseled_laterit", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final RegistrySupplier<Block> LATERIT_BRICKS = registerWithItem("laterit_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final RegistrySupplier<Block> LATERIT_BRICK_STAIRS = registerWithItem("laterit_brick_stairs", () -> new StairBlock(LATERIT_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(TRAVERTIN.get())));
    public static final RegistrySupplier<Block> LATERIT_BRICK_SLAB = registerWithItem("laterit_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(LATERIT_BRICKS.get())));
    public static final RegistrySupplier<Block> LATERIT_BRICK_WALL = registerWithItem("laterit_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(LATERIT_BRICKS.get())));
    public static final RegistrySupplier<Block> CRACKED_LATERIT_BRICKS = registerWithItem("cracked_laterit_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final RegistrySupplier<Block> MOSSY_LATERIT_BRICKS = registerWithItem("mossy_laterit_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final RegistrySupplier<Block> MOSSY_LATERIT_BRICK_STAIRS = registerWithItem("mossy_laterit_brick_stairs", () -> new StairBlock(MOSSY_LATERIT_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(TRAVERTIN.get())));
    public static final RegistrySupplier<Block> MOSSY_LATERIT_BRICK_SLAB = registerWithItem("mossy_laterit_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_LATERIT_BRICKS.get())));
    public static final RegistrySupplier<Block> MOSSY_LATERIT_BRICK_WALL = registerWithItem("mossy_laterit_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_LATERIT_BRICKS.get())));
    public static final RegistrySupplier<Block> MOSSY_COBBLED_LATERIT = registerWithItem("mossy_cobbled_laterit", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final RegistrySupplier<Block> MOSSY_COBBLED_LATERIT_STAIRS = registerWithItem("mossy_cobbled_laterit_stairs", () -> new StairBlock(MOSSY_LATERIT_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(TRAVERTIN.get())));
    public static final RegistrySupplier<Block> MOSSY_COBBLED_LATERIT_SLAB = registerWithItem("mossy_cobbled_laterit_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_LATERIT_BRICKS.get())));
    public static final RegistrySupplier<Block> MOSSY_COBBLED_LATERIT_WALL = registerWithItem("mossy_cobbled_laterit_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_LATERIT_BRICKS.get())));
    public static final RegistrySupplier<Block> MOSSY_CHISELED_LATERIT = registerWithItem("mossy_chiseled_laterit", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final RegistrySupplier<Block> SLATE = registerWithItem("slate", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final RegistrySupplier<Block> SLATE_STAIRS = registerWithItem("slate_stairs", () -> new StairBlock(SLATE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(TRAVERTIN.get())));
    public static final RegistrySupplier<Block> SLATE_SLAB = registerWithItem("slate_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SLATE.get())));
    public static final RegistrySupplier<Block> SLATE_WALL = registerWithItem("slate_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(SLATE.get())));
    public static final RegistrySupplier<Block> COBBLED_SLATE = registerWithItem("cobbled_slate", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final RegistrySupplier<Block> COBBLED_SLATE_STAIRS = registerWithItem("cobbled_slate_stairs", () -> new StairBlock(COBBLED_SLATE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(TRAVERTIN.get())));
    public static final RegistrySupplier<Block> COBBLED_SLATE_SLAB = registerWithItem("cobbled_slate_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(COBBLED_SLATE.get())));
    public static final RegistrySupplier<Block> COBBLED_SLATE_WALL = registerWithItem("cobbled_slate_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(COBBLED_SLATE.get())));
    public static final RegistrySupplier<Block> CHISELED_SLATE = registerWithItem("chiseled_slate", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final RegistrySupplier<Block> SLATE_BRICKS = registerWithItem("slate_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final RegistrySupplier<Block> SLATE_BRICK_STAIRS = registerWithItem("slate_brick_stairs", () -> new StairBlock(SLATE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(TRAVERTIN.get())));
    public static final RegistrySupplier<Block> SLATE_BRICK_SLAB = registerWithItem("slate_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SLATE_BRICKS.get())));
    public static final RegistrySupplier<Block> SLATE_BRICK_WALL = registerWithItem("slate_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(SLATE_BRICKS.get())));
    public static final RegistrySupplier<Block> CRACKED_SLATE_BRICKS = registerWithItem("cracked_slate_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final RegistrySupplier<Block> MOSSY_SLATE_BRICKS = registerWithItem("mossy_slate_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final RegistrySupplier<Block> MOSSY_SLATE_BRICK_STAIRS = registerWithItem("mossy_slate_brick_stairs", () -> new StairBlock(MOSSY_SLATE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(TRAVERTIN.get())));
    public static final RegistrySupplier<Block> MOSSY_SLATE_BRICK_SLAB = registerWithItem("mossy_slate_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_SLATE_BRICKS.get())));
    public static final RegistrySupplier<Block> MOSSY_SLATE_BRICK_WALL = registerWithItem("mossy_slate_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_SLATE_BRICKS.get())));
    public static final RegistrySupplier<Block> MOSSY_COBBLED_SLATE = registerWithItem("mossy_cobbled_slate", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final RegistrySupplier<Block> MOSSY_COBBLED_SLATE_STAIRS = registerWithItem("mossy_cobbled_slate_stairs", () -> new StairBlock(MOSSY_SLATE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(TRAVERTIN.get())));
    public static final RegistrySupplier<Block> MOSSY_COBBLED_SLATE_SLAB = registerWithItem("mossy_cobbled_slate_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_SLATE_BRICKS.get())));
    public static final RegistrySupplier<Block> MOSSY_COBBLED_SLATE_WALL = registerWithItem("mossy_cobbled_slate_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_SLATE_BRICKS.get())));
    public static final RegistrySupplier<Block> MOSSY_CHISELED_SLATE = registerWithItem("mossy_chiseled_slate", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final RegistrySupplier<Block> MOSSY_SLATE = registerWithItem("mossy_slate", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final RegistrySupplier<Block> MOSSY_SLATE_STAIRS = registerWithItem("mossy_slate_stairs", () -> new StairBlock(MOSSY_SLATE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MOSSY_SLATE.get())));
    public static final RegistrySupplier<Block> MOSSY_SLATE_SLAB = registerWithItem("mossy_slate_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_SLATE.get())));
    public static final RegistrySupplier<Block> MOSSY_SLATE_WALL = registerWithItem("mossy_slate_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_SLATE.get())));
    public static final RegistrySupplier<Block> MOSSY_TRAVERTIN = registerWithItem("mossy_travertin", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final RegistrySupplier<Block> MOSSY_TRAVERTIN_STAIRS = registerWithItem("mossy_travertin_stairs", () -> new StairBlock(MOSSY_TRAVERTIN.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MOSSY_TRAVERTIN.get())));
    public static final RegistrySupplier<Block> MOSSY_TRAVERTIN_SLAB = registerWithItem("mossy_travertin_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_TRAVERTIN.get())));
    public static final RegistrySupplier<Block> MOSSY_TRAVERTIN_WALL = registerWithItem("mossy_travertin_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_TRAVERTIN.get())));
    public static final RegistrySupplier<Block> MOSSY_LATERIT_STONE = registerWithItem("mossy_laterit_stone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final RegistrySupplier<Block> MOSSY_LATERIT_STAIRS = registerWithItem("mossy_laterit_stairs", () -> new StairBlock(MOSSY_LATERIT_STONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MOSSY_LATERIT_STONE.get())));
    public static final RegistrySupplier<Block> MOSSY_LATERIT_SLAB = registerWithItem("mossy_laterit_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_LATERIT_STONE.get())));
    public static final RegistrySupplier<Block> MOSSY_LATERIT_WALL = registerWithItem("mossy_laterit_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_LATERIT_STONE.get())));
    public static final RegistrySupplier<Block> MUSHROOM_BRICKS = registerWithItem("mushroom_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MUSHROOM_STEM)));
    public static final RegistrySupplier<Block> MUSHROOM_BRICK_STAIRS = registerWithItem("mushroom_brick_stairs", () -> new StairBlock(MUSHROOM_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MUSHROOM_BRICKS.get())));
    public static final RegistrySupplier<Block> MUSHROOM_BRICK_SLAB = registerWithItem("mushroom_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MUSHROOM_BRICKS.get())));
    public static final RegistrySupplier<Block> MUSHROOM_BRICK_WALL = registerWithItem("mushroom_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(MUSHROOM_BRICKS.get())));
    public static final RegistrySupplier<Block> BROWN_MUSHROOM_BRICK_STAIRS = registerWithItem("brown_mushroom_brick_stairs", () -> new StairBlock(MUSHROOM_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MUSHROOM_BRICKS.get())));
    public static final RegistrySupplier<Block> BROWN_MUSHROOM_BRICK_SLAB = registerWithItem("brown_mushroom_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MUSHROOM_BRICKS.get())));
    public static final RegistrySupplier<Block> BROWN_MUSHROOM_BRICK_WALL = registerWithItem("brown_mushroom_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(MUSHROOM_BRICKS.get())));
    public static final RegistrySupplier<Block> BROWN_MUSHROOM_BRICKS = registerWithItem("brown_mushroom_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MUSHROOM_STEM)));
    public static final RegistrySupplier<Block> FOREST_MOSS = registerWithItem("forest_moss", () -> new ForestMossBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK).randomTicks()));
    public static final RegistrySupplier<Block> FOREST_MOSS_CARPET = registerWithItem("forest_moss_carpet", () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSS_CARPET).speedFactor(0.75F)));
    public static final RegistrySupplier<Block> MARSH_BLOCK = registerWithItem("marsh_block", () -> new SinkInBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MUD)));
    public static final RegistrySupplier<Block> QUICKSAND = registerWithItem("quicksand", () -> new SinkInSandBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SAND)));
    public static final RegistrySupplier<Block> JOE_PYE = registerWithItem("joe_pye", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_TULIP)));
    public static final RegistrySupplier<Block> POTTED_JOE_PYE = registerWithoutItem("potted_joe_pye", () -> new FlowerPotBlock(JOE_PYE.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> HYSSOP = registerWithItem("hyssop", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_TULIP)));
    public static final RegistrySupplier<Block> POTTED_HYSSOP = registerWithoutItem("potted_hyssop", () -> new FlowerPotBlock(HYSSOP.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> MOUNTAIN_SNOWBELL = registerWithItem("mountain_snowbell", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_TULIP)));
    public static final RegistrySupplier<Block> POTTED_MOUNTAIN_SNOWBELL = registerWithoutItem("potted_mountain_snowbell", () -> new FlowerPotBlock(MOUNTAIN_SNOWBELL.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> MOUNTAIN_LAUREL = registerWithItem("mountain_laurel", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_TULIP)));
    public static final RegistrySupplier<Block> POTTED_MOUNTAIN_LAUREL = registerWithoutItem("potted_mountain_laurel", () -> new FlowerPotBlock(MOUNTAIN_LAUREL.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> GOLDEN_ROD = registerWithItem("golden_rod", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_TULIP)));
    public static final RegistrySupplier<Block> POTTED_GOLDEN_ROD = registerWithoutItem("potted_golden_rod", () -> new FlowerPotBlock(GOLDEN_ROD.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> BIRD_OF_PARADISE = registerWithItem("bird_of_paradise", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_TULIP)));
    public static final RegistrySupplier<Block> POTTED_BIRD_OF_PARADISE = registerWithoutItem("potted_bird_of_paradise", () -> new FlowerPotBlock(BIRD_OF_PARADISE.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> WHITE_ORCHID = registerWithItem("white_orchid", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_TULIP)));
    public static final RegistrySupplier<Block> POTTED_WHITE_ORCHID = registerWithoutItem("potted_white_orchid", () -> new FlowerPotBlock(WHITE_ORCHID.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> DAPHNE = registerWithItem("daphne", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_TULIP)));
    public static final RegistrySupplier<Block> POTTED_DAPHNE = registerWithoutItem("potted_daphne", () -> new FlowerPotBlock(DAPHNE.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> BOTTLEBRUSHES = registerWithItem("bottlebrushes", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_TULIP)));
    public static final RegistrySupplier<Block> POTTED_BOTTLEBRUSHES = registerWithoutItem("potted_bottlebrushes", () -> new FlowerPotBlock(BOTTLEBRUSHES.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> BLUEBELL = registerWithItem("bluebell", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_TULIP)));
    public static final RegistrySupplier<Block> POTTED_BLUEBELL = registerWithoutItem("potted_bluebell", () -> new FlowerPotBlock(BLUEBELL.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> BEGONIE = registerWithItem("begonie", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_TULIP)));
    public static final RegistrySupplier<Block> POTTED_BEGONIE = registerWithoutItem("potted_begonie", () -> new FlowerPotBlock(BEGONIE.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> GOATSBEARD = registerWithItem("goatsbeard", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_TULIP)));
    public static final RegistrySupplier<Block> POTTED_GOATSBEARD = registerWithoutItem("potted_goatsbeard", () -> new FlowerPotBlock(GOATSBEARD.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> GENISTEAE = registerWithItem("genisteae", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_TULIP)));
    public static final RegistrySupplier<Block> POTTED_GENISTEAE = registerWithoutItem("potted_genisteae", () -> new FlowerPotBlock(GENISTEAE.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> FORSYTHIA = registerWithItem("forsythia", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_TULIP)));
    public static final RegistrySupplier<Block> POTTED_FORSYTHIA = registerWithoutItem("potted_forsythia", () -> new FlowerPotBlock(FORSYTHIA.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> FOXGLOVE_WHITE = registerWithItem("foxglove_white", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_TULIP)));
    public static final RegistrySupplier<Block> POTTED_FOXGLOVE_WHITE = registerWithoutItem("potted_foxglove_white", () -> new FlowerPotBlock(FOXGLOVE_WHITE.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> AMARYLLIS = registerWithItem("amaryllis", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_TULIP)));
    public static final RegistrySupplier<Block> POTTED_AMARYLLIS = registerWithoutItem("potted_amaryllis", () -> new FlowerPotBlock(AMARYLLIS.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> ANEMONE = registerWithItem("anemone", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_TULIP)));
    public static final RegistrySupplier<Block> POTTED_ANEMONE = registerWithoutItem("potted_anemone", () -> new FlowerPotBlock(ANEMONE.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> FOXGLOVE_PINK = registerWithItem("foxglove_pink", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_TULIP)));
    public static final RegistrySupplier<Block> POTTED_FOXGLOVE_PINK = registerWithoutItem("potted_foxglove_pink", () -> new FlowerPotBlock(FOXGLOVE_PINK.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> GLADIOLUS = registerWithItem("gladiolus", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_TULIP)));
    public static final RegistrySupplier<Block> POTTED_GLADIOLUS = registerWithoutItem("potted_gladiolus", () -> new FlowerPotBlock(GLADIOLUS.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> FREESIA_YELLOW = registerWithItem("freesia_yellow", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_TULIP)));
    public static final RegistrySupplier<Block> POTTED_FREESIA_YELLOW = registerWithoutItem("potted_freesia_yellow", () -> new FlowerPotBlock(FREESIA_YELLOW.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> FREESIA_PINK = registerWithItem("freesia_pink", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_TULIP)));
    public static final RegistrySupplier<Block> POTTED_FREESIA_PINK = registerWithoutItem("potted_freesia_pink", () -> new FlowerPotBlock(FREESIA_PINK.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> LUPINE_PURPLE = registerWithItem("lupine_purple", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_TULIP)));
    public static final RegistrySupplier<Block> POTTED_LUPINE_PURPLE = registerWithoutItem("potted_lupine_purple", () -> new FlowerPotBlock(LUPINE_PURPLE.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> LUPINE_BLUE = registerWithItem("lupine_blue", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_TULIP)));
    public static final RegistrySupplier<Block> POTTED_LUPINE_BLUE = registerWithoutItem("potted_lupine_blue", () -> new FlowerPotBlock(LUPINE_BLUE.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> TALL_LUPINE_BLUE = registerWithItem("tall_lupine_blue", () -> new TallFlowerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH)));
    public static final RegistrySupplier<Block> TALL_LUPINE_PURPLE = registerWithItem("tall_lupine_purple", () -> new TallFlowerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH)));
    public static final RegistrySupplier<Block> DRY_GRASS = registerWithItem("dry_grass", () -> new DeadBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ALLIUM)));
    public static final RegistrySupplier<Block> DRY_BUSH = registerWithItem("dry_bush", () -> new DeadBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> POTTED_DRY_BUSH = registerWithoutItem("potted_dry_bush", () -> new FlowerPotBlock(DRY_BUSH.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> DRY_BUSH_TALL = registerWithItem("dry_bush_tall", () -> new DeadBushTallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH)));
    public static final RegistrySupplier<Block> CATTAIL = registerWithItem("cattail", () -> new CattailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_SEAGRASS)));
    public static final RegistrySupplier<Block> REED = registerWithItem("reed", () -> new CattailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_SEAGRASS)));
    public static final RegistrySupplier<Block> CARDINAL = registerWithItem("cardinal", () -> new TallFlowerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH)));
    public static final RegistrySupplier<Block> TALL_MOUNTAIN_LAUREL = registerWithItem("tall_mountain_laurel", () -> new TallFlowerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH)));
    public static final RegistrySupplier<Block> WILD_SUNFLOWER = registerWithItem("wild_sunflower", () -> new TallFlowerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH)));
    public static final RegistrySupplier<Block> FLOATING_LEAVES = registerWithItem("floating_leaves", () -> new WaterlilyBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).instabreak().sound(SoundType.LILY_PAD).noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final RegistrySupplier<Block> SUNGRASS = registerWithItem("sungrass", () -> new TallGrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CORNFLOWER)));
    public static final RegistrySupplier<Block> RED_OAT_GRASS = registerWithItem("red_oat_grass", () -> new TallGrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CORNFLOWER)));
    public static final RegistrySupplier<Block> TALL_SUNGRASS = registerWithItem("tall_sungrass", () -> new DoublePlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)));
    public static final RegistrySupplier<Block> TALL_RED_OAT_GRASS = registerWithItem("tall_red_oat_grass", () -> new DoublePlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)));
    public static final RegistrySupplier<Block> SILKGRASS = registerWithItem("silkgrass", () -> new TallGrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CORNFLOWER)));
    public static final RegistrySupplier<Block> TALL_SILKGRASS = registerWithItem("tall_silkgrass", () -> new DoublePlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)));
    public static final RegistrySupplier<Block> PAMPAS_GRASS = registerWithItem("pampas_grass", () -> new DoublePlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)));
    public static final RegistrySupplier<Block> MOSSGRASS = registerWithItem("mossgrass", () -> new TallGrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CORNFLOWER)));
    public static final RegistrySupplier<Block> SMALL_CACTUS = registerWithItem("small_cactus", () -> new SmallCactusBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CACTUS)));
    public static final RegistrySupplier<Block> BARREL_CACTUS = registerWithItem("barrel_cactus", () -> new DeadBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEAD_BUSH)));
    public static final RegistrySupplier<Block> PRICKLY_PEAR_CACTUS = registerWithItem("prickly_pear_cactus", () -> new DeadBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEAD_BUSH)));
    public static final RegistrySupplier<Block> POTTED_BARREL_CACTUS = registerWithoutItem("potted_barrel_cactus", () -> new FlowerPotBlock(BARREL_CACTUS.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_PRICKLY_PEAR_CACTUS = registerWithoutItem("potted_prickly_pear_cactus", () -> new FlowerPotBlock(PRICKLY_PEAR_CACTUS.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> SAND = registerWithItem("sand", () -> new SandLayerBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).replaceable().forceSolidOff().randomTicks().strength(0.1F).requiresCorrectToolForDrops().sound(SoundType.SAND).isViewBlocking((blockStatex, blockGetter, blockPos) -> blockStatex.getValue(SnowLayerBlock.LAYERS) >= 8).pushReaction(PushReaction.DESTROY)));
    private static final BannerSettings BANNER_SETTINGS = new BannerSettings(
            () -> EntityTypeRegistry.BLOOMINGNATURE_BANNER.get(),
            () -> ObjectRegistry.BLOOMINGNATURE_WALL_BANNER.get(),
            BloomingNature.identifier("textures/banner/bloomingnature_banner.png"),
            "tooltip.bloomingnature.banner",
            MobEffects.LUCK,
            () -> BloomingNatureConfig.bannerGiveEffect ? BloomingNatureConfig.bannerRadius : 0,
            () -> BloomingNatureConfig.bannerAmplifier);
    public static final RegistrySupplier<Block> BLOOMINGNATURE_BANNER = registerWithItem("bloomingnature_banner", () -> new CompletionistBannerBlock(BlockBehaviour.Properties.of().strength(1F).instrument(NoteBlockInstrument.BASS).noCollission().sound(SoundType.WOOD), BANNER_SETTINGS));
    public static final RegistrySupplier<Block> BLOOMINGNATURE_WALL_BANNER = registerWithoutItem("bloomingnature_wall_banner", () -> new CompletionistWallBannerBlock(BlockBehaviour.Properties.of().strength(1F).instrument(NoteBlockInstrument.BASS).noCollission().sound(SoundType.WOOD), BANNER_SETTINGS));
    public static final RegistrySupplier<Block> BLUFF_GRASS = registerWithItem( "bluff_grass", () -> new TallGrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AZURE_BLUET)));
    public static final RegistrySupplier<Block> TALL_BLUFF_GRASS = registerWithItem("tall_bluff_grass", () -> new DoublePlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)));
    public static final RegistrySupplier<Block> MUNSTEAD = registerWithItem("munstead", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_TULIP)));
    public static final RegistrySupplier<Block> POTTED_MUNSTEAD = registerWithoutItem("potted_munstead", () -> new FlowerPotBlock(MUNSTEAD.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> MARLSTONE = registerWithItem("marlstone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final RegistrySupplier<Block> MARLSTONE_STAIRS = registerWithItem("marlstone_stairs", () -> new StairBlock(MARLSTONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(TRAVERTIN.get())));
    public static final RegistrySupplier<Block> MARLSTONE_SLAB = registerWithItem("marlstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MARLSTONE.get())));
    public static final RegistrySupplier<Block> MARLSTONE_WALL = registerWithItem("marlstone_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(MARLSTONE.get())));
    public static final RegistrySupplier<Block> COBBLED_MARLSTONE = registerWithItem("cobbled_marlstone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final RegistrySupplier<Block> COBBLED_MARLSTONE_STAIRS = registerWithItem("cobbled_marlstone_stairs", () -> new StairBlock(COBBLED_MARLSTONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(TRAVERTIN.get())));
    public static final RegistrySupplier<Block> COBBLED_MARLSTONE_SLAB = registerWithItem("cobbled_marlstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(COBBLED_MARLSTONE.get())));
    public static final RegistrySupplier<Block> COBBLED_MARLSTONE_WALL = registerWithItem("cobbled_marlstone_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(COBBLED_MARLSTONE.get())));
    public static final RegistrySupplier<Block> CHISELED_MARLSTONE = registerWithItem("chiseled_marlstone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final RegistrySupplier<Block> MARLSTONE_BRICKS = registerWithItem("marlstone_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final RegistrySupplier<Block> MARLSTONE_BRICK_STAIRS = registerWithItem("marlstone_brick_stairs", () -> new StairBlock(MARLSTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(TRAVERTIN.get())));
    public static final RegistrySupplier<Block> MARLSTONE_BRICK_SLAB = registerWithItem("marlstone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MARLSTONE_BRICKS.get())));
    public static final RegistrySupplier<Block> MARLSTONE_BRICK_WALL = registerWithItem("marlstone_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(MARLSTONE_BRICKS.get())));
    public static final RegistrySupplier<Block> CRACKED_MARLSTONE_BRICKS = registerWithItem("cracked_marlstone_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final RegistrySupplier<Block> MOSSY_MARLSTONE_BRICKS = registerWithItem("mossy_marlstone_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final RegistrySupplier<Block> MOSSY_MARLSTONE_BRICK_STAIRS = registerWithItem("mossy_marlstone_brick_stairs", () -> new StairBlock(MOSSY_MARLSTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(TRAVERTIN.get())));
    public static final RegistrySupplier<Block> MOSSY_MARLSTONE_BRICK_SLAB = registerWithItem("mossy_marlstone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_MARLSTONE_BRICKS.get())));
    public static final RegistrySupplier<Block> MOSSY_MARLSTONE_BRICK_WALL = registerWithItem("mossy_marlstone_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_MARLSTONE_BRICKS.get())));
    public static final RegistrySupplier<Block> MOSSY_COBBLED_MARLSTONE = registerWithItem("mossy_cobbled_marlstone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final RegistrySupplier<Block> MOSSY_COBBLED_MARLSTONE_STAIRS = registerWithItem("mossy_cobbled_marlstone_stairs", () -> new StairBlock(MOSSY_MARLSTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(TRAVERTIN.get())));
    public static final RegistrySupplier<Block> MOSSY_COBBLED_MARLSTONE_SLAB = registerWithItem("mossy_cobbled_marlstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_MARLSTONE_BRICKS.get())));
    public static final RegistrySupplier<Block> MOSSY_COBBLED_MARLSTONE_WALL = registerWithItem("mossy_cobbled_marlstone_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_MARLSTONE_BRICKS.get())));
    public static final RegistrySupplier<Block> MOSSY_CHISELED_MARLSTONE = registerWithItem("mossy_chiseled_marlstone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final RegistrySupplier<Block> MOSSY_MARLSTONE = registerWithItem("mossy_marlstone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final RegistrySupplier<Block> MOSSY_MARLSTONE_STAIRS = registerWithItem("mossy_marlstone_stairs", () -> new StairBlock(MOSSY_MARLSTONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MOSSY_MARLSTONE.get())));
    public static final RegistrySupplier<Block> MOSSY_MARLSTONE_SLAB = registerWithItem("mossy_marlstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_MARLSTONE.get())));
    public static final RegistrySupplier<Block> MOSSY_MARLSTONE_WALL = registerWithItem("mossy_marlstone_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_MARLSTONE.get())));
    public static final RegistrySupplier<Block> MYOSOTIS = registerWithItem("myosotis", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_TULIP)));
    public static final RegistrySupplier<Block> POTTED_MYOSOTIS = registerWithoutItem("potted_myosotis", () -> new FlowerPotBlock(MYOSOTIS.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> BLOOMING_OAK_LEAVES = registerWithItem("blooming_oak_leaves", () -> new BloomingLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
    public static final RegistrySupplier<Block> SUNFLOWER = registerWithItem("sunflower", () -> new SunflowerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH).randomTicks()));
    public static final RegistrySupplier<Block> DESERT_LILY = registerWithItem("desert_lily", () -> new DeadBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_TULIP)));
    public static final RegistrySupplier<Block> POTTED_DESERT_LILY = registerWithoutItem("potted_desert_lily", () -> new FlowerPotBlock(DESERT_LILY.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> TWIGS = registerWithItem("twigs", () -> new GroundDetailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).instabreak().noOcclusion()));
    public static final RegistrySupplier<Block> PEBBLES = registerWithItem("pebbles", () -> new GroundDetailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).sound(SoundType.STONE).instabreak().noOcclusion()));
    public static final RegistrySupplier<Block> FEN_MOSS = registerWithItem("fen_moss", () -> new FenMossBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSS_BLOCK).randomTicks()));
    public static final RegistrySupplier<Block> FEN_MOSS_CARPET = registerWithItem("fen_moss_carpet", () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSS_CARPET)));

    public static void init() {
        ITEMS.register();
        BLOCKS.register();
    }

    private static BlockBehaviour.Properties getLogBlockSettings() {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F).sound(SoundType.WOOD);
    }

    private static BlockBehaviour.Properties getSlabSettings() {
        return getLogBlockSettings().explosionResistance(3.0F);
    }

    private static Item.Properties getSettings(Consumer<Item.Properties> consumer) {
        Item.Properties settings = new Item.Properties();
        consumer.accept(settings);
        return settings;
    }

    static Item.Properties getSettings() {
        return getSettings(settings -> {
        });
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, BloomingNature.identifier( name));
    }

    public static void commonInit() {
        for (WoodSet set : WoodSet.all()) {
            if (set != CACTUS) {
                set.forEachPlankBlock(block -> FuelRegistry.register(300, block));
                set.forEachLogBlock(block -> FuelRegistry.register(300, block));
            }
        }
    }

    public static <T extends Block> RegistrySupplier<T> registerWithItem(String name, Supplier<T> block) {
        return RegistryUtil.registerWithItem(BLOCKS, BLOCK_REGISTRAR, ITEMS, ITEM_REGISTRAR, BloomingNature.identifier(name), block);
    }

    public static <T extends Block> RegistrySupplier<T> registerWithoutItem(String path, Supplier<T> block) {
        return RegistryUtil.registerWithoutItem(BLOCKS, BLOCK_REGISTRAR, BloomingNature.identifier(path), block);
    }

    public static <T extends Item> RegistrySupplier<T> registerItem(String path, Supplier<T> itemSupplier) {
        return RegistryUtil.registerItem(ITEMS, ITEM_REGISTRAR, BloomingNature.identifier(path), itemSupplier);
    }
}