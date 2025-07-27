package satisfy.bloomingnature.registry;

import de.cristelknight.doapi.Util;
import de.cristelknight.doapi.common.block.FlowerBoxBlock;
import de.cristelknight.doapi.common.block.WindowBlock;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import satisfy.bloomingnature.BloomingNature;
import satisfy.bloomingnature.block.*;
import satisfy.bloomingnature.block.storage.BNFlowerPotBigBlock;
import satisfy.bloomingnature.item.BloomingNatureStandardItem;
import satisfy.bloomingnature.util.BloomingNatureIdentifier;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class ObjectRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BloomingNature.MOD_ID, Registries.ITEM);
    public static final Registrar<Item> ITEM_REGISTRAR = ITEMS.getRegistrar();
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BloomingNature.MOD_ID, Registries.BLOCK);
    public static final Registrar<Block> BLOCK_REGISTRAR = BLOCKS.getRegistrar();

    public static final RegistrySupplier<Block> LARCH_LOG = registerWithItem("larch_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> LARCH_WOOD = registerWithItem("larch_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> STRIPPED_LARCH_WOOD = registerWithItem("stripped_larch_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> STRIPPED_LARCH_LOG = registerWithItem("stripped_larch_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> LARCH_PLANKS = registerWithItem("larch_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(2.0f, 3.0f).mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final RegistrySupplier<Block> LARCH_STAIRS = registerWithItem("larch_stairs", () -> new StairBlock(LARCH_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final RegistrySupplier<Block> LARCH_SLAB = registerWithItem("larch_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final RegistrySupplier<Block> LARCH_PRESSURE_PLATE = registerWithItem("larch_pressure_plate", () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().noCollission().strength(0.5f).sound(SoundType.WOOD).mapColor(LARCH_PLANKS.get().defaultMapColor())));
    public static final RegistrySupplier<Block> LARCH_BUTTON = registerWithItem("larch_button", () -> woodenButton(FeatureFlags.VANILLA));
    public static final RegistrySupplier<Block> LARCH_TRAPDOOR = registerWithItem("larch_trapdoor", () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));
    public static final RegistrySupplier<Block> LARCH_DOOR = registerWithItem("larch_door", () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().strength(3.0f).sound(SoundType.WOOD).noOcclusion().mapColor(LARCH_PLANKS.get().defaultMapColor())));
    public static final RegistrySupplier<Block> LARCH_FENCE = registerWithItem("larch_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().strength(2.0f, 3.0f).sound(SoundType.WOOD)));
    public static final RegistrySupplier<Block> LARCH_FENCE_GATE = registerWithItem("larch_fence_gate", () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.of().strength(2.0f, 3.0f).sound(SoundType.WOOD).mapColor(LARCH_PLANKS.get().defaultMapColor())));
    public static final RegistrySupplier<Block> LARCH_LEAVES = registerWithItem("larch_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of().strength(0.2f).randomTicks().sound(SoundType.GRASS).noOcclusion().isViewBlocking((state, world, pos) -> false).isSuffocating((state, world, pos) -> false).mapColor(MapColor.GRASS)));
    public static final RegistrySupplier<Block> LARCH_WINDOW = registerWithItem("larch_window", () -> new WindowBlock(BlockBehaviour.Properties.of().strength(0.2f).randomTicks().sound(SoundType.GLASS).noOcclusion().isViewBlocking((state, world, pos) -> false).isSuffocating((state, world, pos) -> false).mapColor(MapColor.GRASS).pushReaction(PushReaction.IGNORE)));
    public static final RegistrySupplier<Block> LARCH_SAPLING = registerWithItem("larch_sapling", () -> new SaplingBlock(new TreeGrower("larch", Optional.empty(), Optional.of(configuredFeatureKey("larch")), Optional.empty()), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SAPLING)));
    public static final RegistrySupplier<Block> BAOBAB_LOG = registerWithItem("baobab_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> BAOBAB_WOOD = registerWithItem("baobab_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> STRIPPED_BAOBAB_WOOD = registerWithItem("stripped_baobab_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> STRIPPED_BAOBAB_LOG = registerWithItem("stripped_baobab_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> BAOBAB_PLANKS = registerWithItem("baobab_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(2.0f, 3.0f).mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final RegistrySupplier<Block> BAOBAB_STAIRS = registerWithItem("baobab_stairs", () -> new StairBlock(BAOBAB_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final RegistrySupplier<Block> BAOBAB_SLAB = registerWithItem("baobab_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final RegistrySupplier<Block> BAOBAB_PRESSURE_PLATE = registerWithItem("baobab_pressure_plate", () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().noCollission().strength(0.5f).sound(SoundType.WOOD).mapColor(BAOBAB_PLANKS.get().defaultMapColor())));
    public static final RegistrySupplier<Block> BAOBAB_BUTTON = registerWithItem("baobab_button", () -> woodenButton(FeatureFlags.VANILLA));
    public static final RegistrySupplier<Block> BAOBAB_TRAPDOOR = registerWithItem("baobab_trapdoor", () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));
    public static final RegistrySupplier<Block> BAOBAB_DOOR = registerWithItem("baobab_door", () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().strength(3.0f).sound(SoundType.WOOD).noOcclusion().mapColor(BAOBAB_PLANKS.get().defaultMapColor())));
    public static final RegistrySupplier<Block> BAOBAB_FENCE = registerWithItem("baobab_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().strength(2.0f, 3.0f).sound(SoundType.WOOD)));
    public static final RegistrySupplier<Block> BAOBAB_FENCE_GATE = registerWithItem("baobab_fence_gate", () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.of().strength(2.0f, 3.0f).sound(SoundType.WOOD).mapColor(BAOBAB_PLANKS.get().defaultMapColor())));
    public static final RegistrySupplier<Block> BAOBAB_LEAVES = registerWithItem("baobab_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of().strength(0.2f).randomTicks().sound(SoundType.GRASS).noOcclusion().isViewBlocking((state, world, pos) -> false).isSuffocating((state, world, pos) -> false).mapColor(MapColor.GRASS)));
    public static final RegistrySupplier<Block> BAOBAB_WINDOW = registerWithItem("baobab_window", () -> new WindowBlock(BlockBehaviour.Properties.of().strength(0.2f).randomTicks().sound(SoundType.GLASS).noOcclusion().isViewBlocking((state, world, pos) -> false).isSuffocating((state, world, pos) -> false).mapColor(MapColor.GRASS).pushReaction(PushReaction.IGNORE)));
    public static final RegistrySupplier<Block> BAOBAB_SAPLING = registerWithItem("baobab_sapling", () -> new SaplingBlock(new TreeGrower("savanna_baobab", Optional.empty(), Optional.of(configuredFeatureKey("savanna_baobab")), Optional.empty()), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SAPLING)));
    public static final RegistrySupplier<Block> SWAMP_OAK_LOG = registerWithItem("swamp_oak_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> SWAMP_OAK_WOOD = registerWithItem("swamp_oak_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> STRIPPED_SWAMP_OAK_WOOD = registerWithItem("stripped_swamp_oak_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> STRIPPED_SWAMP_OAK_LOG = registerWithItem("stripped_swamp_oak_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> SWAMP_OAK_PLANKS = registerWithItem("swamp_oak_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(2.0f, 3.0f).mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final RegistrySupplier<Block> SWAMP_OAK_STAIRS = registerWithItem("swamp_oak_stairs", () -> new StairBlock(SWAMP_OAK_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final RegistrySupplier<Block> SWAMP_OAK_SLAB = registerWithItem("swamp_oak_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final RegistrySupplier<Block> SWAMP_OAK_PRESSURE_PLATE = registerWithItem("swamp_oak_pressure_plate", () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().noCollission().strength(0.5f).sound(SoundType.WOOD).mapColor(SWAMP_OAK_PLANKS.get().defaultMapColor())));
    public static final RegistrySupplier<Block> SWAMP_OAK_BUTTON = registerWithItem("swamp_oak_button", () -> woodenButton(FeatureFlags.VANILLA));
    public static final RegistrySupplier<Block> SWAMP_OAK_TRAPDOOR = registerWithItem("swamp_oak_trapdoor", () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));
    public static final RegistrySupplier<Block> SWAMP_OAK_DOOR = registerWithItem("swamp_oak_door", () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().strength(3.0f).sound(SoundType.WOOD).noOcclusion().mapColor(SWAMP_OAK_PLANKS.get().defaultMapColor())));
    public static final RegistrySupplier<Block> SWAMP_OAK_FENCE = registerWithItem("swamp_oak_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().strength(2.0f, 3.0f).sound(SoundType.WOOD)));
    public static final RegistrySupplier<Block> SWAMP_OAK_FENCE_GATE = registerWithItem("swamp_oak_fence_gate", () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.of().strength(2.0f, 3.0f).sound(SoundType.WOOD).mapColor(SWAMP_OAK_PLANKS.get().defaultMapColor())));
    public static final RegistrySupplier<Block> SWAMP_OAK_WINDOW = registerWithItem("swamp_oak_window", () -> new WindowBlock(BlockBehaviour.Properties.of().strength(0.2f).randomTicks().sound(SoundType.GLASS).noOcclusion().isViewBlocking((state, world, pos) -> false).isSuffocating((state, world, pos) -> false).mapColor(MapColor.GRASS).pushReaction(PushReaction.IGNORE)));
    public static final RegistrySupplier<Block> ORANGE_LEAVES = registerWithItem("orange_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of().strength(0.2f).randomTicks().sound(SoundType.GRASS).noOcclusion().isViewBlocking((state, world, pos) -> false).isSuffocating((state, world, pos) -> false).mapColor(MapColor.GRASS)));
    public static final RegistrySupplier<Block> SWAMP_OAK_LEAVES = registerWithItem("swamp_oak_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of().strength(0.2f).randomTicks().sound(SoundType.GRASS).noOcclusion().isViewBlocking((state, world, pos) -> false).isSuffocating((state, world, pos) -> false).mapColor(MapColor.GRASS)));
    public static final RegistrySupplier<Block> SWAMP_OAK_SAPLING = registerWithItem("swamp_oak_sapling", () -> new SaplingBlock(new TreeGrower("forest_oak_branched", Optional.empty(), Optional.of(configuredFeatureKey("forest_oak_branched")), Optional.empty()), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final RegistrySupplier<Block> ASPEN_LOG = registerWithItem("aspen_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> ASPEN_WOOD = registerWithItem("aspen_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> STRIPPED_ASPEN_WOOD = registerWithItem("stripped_aspen_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> STRIPPED_ASPEN_LOG = registerWithItem("stripped_aspen_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> ASPEN_PLANKS = registerWithItem("aspen_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(2.0f, 3.0f).mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final RegistrySupplier<Block> ASPEN_STAIRS = registerWithItem("aspen_stairs", () -> new StairBlock(ASPEN_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final RegistrySupplier<Block> ASPEN_SLAB = registerWithItem("aspen_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final RegistrySupplier<Block> ASPEN_PRESSURE_PLATE = registerWithItem("aspen_pressure_plate", () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().noCollission().strength(0.5f).sound(SoundType.WOOD).mapColor(ASPEN_PLANKS.get().defaultMapColor())));
    public static final RegistrySupplier<Block> ASPEN_BUTTON = registerWithItem("aspen_button", () -> woodenButton(FeatureFlags.VANILLA));
    public static final RegistrySupplier<Block> ASPEN_TRAPDOOR = registerWithItem("aspen_trapdoor", () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));
    public static final RegistrySupplier<Block> ASPEN_DOOR = registerWithItem("aspen_door", () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().strength(3.0f).sound(SoundType.WOOD).noOcclusion().mapColor(ASPEN_PLANKS.get().defaultMapColor())));
    public static final RegistrySupplier<Block> ASPEN_FENCE = registerWithItem("aspen_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().strength(2.0f, 3.0f).sound(SoundType.WOOD)));
    public static final RegistrySupplier<Block> ASPEN_FENCE_GATE = registerWithItem("aspen_fence_gate", () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.of().strength(2.0f, 3.0f).sound(SoundType.WOOD).mapColor(ASPEN_PLANKS.get().defaultMapColor())));
    public static final RegistrySupplier<Block> ASPEN_WINDOW = registerWithItem("aspen_window", () -> new WindowBlock(BlockBehaviour.Properties.of().strength(0.2f).randomTicks().sound(SoundType.GLASS).noOcclusion().isViewBlocking((state, world, pos) -> false).isSuffocating((state, world, pos) -> false).mapColor(MapColor.GRASS).pushReaction(PushReaction.IGNORE)));
    public static final RegistrySupplier<Block> ASPEN_LEAVES = registerWithItem("aspen_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of().strength(0.2f).randomTicks().sound(SoundType.GRASS).noOcclusion().isViewBlocking((state, world, pos) -> false).isSuffocating((state, world, pos) -> false).mapColor(MapColor.GRASS)));
    public static final RegistrySupplier<Block> ASPEN_SAPLING = registerWithItem("aspen_sapling", () -> new SaplingBlock(new TreeGrower("aspen_tree_branched", Optional.empty(), Optional.of(configuredFeatureKey("aspen_tree_branched")), Optional.empty()), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final RegistrySupplier<Block> FAN_PALM_LOG = registerWithItem("fan_palm_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> FAN_PALM_WOOD = registerWithItem("fan_palm_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> STRIPPED_FAN_PALM_WOOD = registerWithItem("stripped_fan_palm_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> STRIPPED_FAN_PALM_LOG = registerWithItem("stripped_fan_palm_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> FAN_PALM_PLANKS = registerWithItem("fan_palm_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(2.0f, 3.0f).mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final RegistrySupplier<Block> FAN_PALM_STAIRS = registerWithItem("fan_palm_stairs", () -> new StairBlock(FAN_PALM_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final RegistrySupplier<Block> FAN_PALM_SLAB = registerWithItem("fan_palm_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final RegistrySupplier<Block> FAN_PALM_PRESSURE_PLATE = registerWithItem("fan_palm_pressure_plate", () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().noCollission().strength(0.5f).sound(SoundType.WOOD).mapColor(FAN_PALM_PLANKS.get().defaultMapColor())));
    public static final RegistrySupplier<Block> FAN_PALM_BUTTON = registerWithItem("fan_palm_button", () -> woodenButton(FeatureFlags.VANILLA));
    public static final RegistrySupplier<Block> FAN_PALM_TRAPDOOR = registerWithItem("fan_palm_trapdoor", () -> new TrapDoorBlock(BlockSetType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));
    public static final RegistrySupplier<Block> FAN_PALM_DOOR = registerWithItem("fan_palm_door", () -> new DoorBlock(BlockSetType.OAK,BlockBehaviour.Properties.of().strength(3.0f).sound(SoundType.WOOD).noOcclusion().mapColor(FAN_PALM_PLANKS.get().defaultMapColor())));
    public static final RegistrySupplier<Block> FAN_PALM_FENCE = registerWithItem("fan_palm_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().strength(2.0f, 3.0f).sound(SoundType.WOOD)));
    public static final RegistrySupplier<Block> FAN_PALM_FENCE_GATE = registerWithItem("fan_palm_fence_gate", () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.of().strength(2.0f, 3.0f).sound(SoundType.WOOD).mapColor(FAN_PALM_PLANKS.get().defaultMapColor())));
    public static final RegistrySupplier<Block> FAN_PALM_WINDOW = registerWithItem("fan_palm_window", () -> new WindowBlock(BlockBehaviour.Properties.of().strength(0.2f).randomTicks().sound(SoundType.GLASS).noOcclusion().isViewBlocking((state, world, pos) -> false).isSuffocating((state, world, pos) -> false).mapColor(MapColor.GRASS).pushReaction(PushReaction.IGNORE)));
    public static final RegistrySupplier<Block> FAN_PALM_LEAVES = registerWithItem("fan_palm_leaves", () -> new FanPalmLeavesBlock(BlockBehaviour.Properties.of().strength(0.2f).randomTicks().sound(SoundType.GRASS).noOcclusion().isViewBlocking((state, world, pos) -> false).isSuffocating((state, world, pos) -> false).mapColor(MapColor.GRASS)));
    public static final Supplier<SaplingBlock> FAN_PALM_SPROUT = registerWithItem("fan_palm_sprout", FanPalmSproutBlock::new);
    public static final RegistrySupplier<Block> FIR_LOG = registerWithItem("fir_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> FIR_WOOD = registerWithItem("fir_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> STRIPPED_FIR_WOOD = registerWithItem("stripped_fir_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> STRIPPED_FIR_LOG = registerWithItem("stripped_fir_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> FIR_PLANKS = registerWithItem("fir_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(2.0f, 3.0f).mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final RegistrySupplier<Block> FIR_STAIRS = registerWithItem("fir_stairs", () -> new StairBlock(FIR_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final RegistrySupplier<Block> FIR_SLAB = registerWithItem("fir_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final RegistrySupplier<Block> FIR_PRESSURE_PLATE = registerWithItem("fir_pressure_plate", () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().noCollission().strength(0.5f).sound(SoundType.WOOD).mapColor(FIR_PLANKS.get().defaultMapColor())));
    public static final RegistrySupplier<Block> FIR_BUTTON = registerWithItem("fir_button", () -> woodenButton(FeatureFlags.VANILLA));
    public static final RegistrySupplier<Block> FIR_TRAPDOOR = registerWithItem("fir_trapdoor", () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));
    public static final RegistrySupplier<Block> FIR_DOOR = registerWithItem("fir_door", () -> new DoorBlock(BlockSetType.OAK,BlockBehaviour.Properties.of().strength(3.0f).sound(SoundType.WOOD).noOcclusion().mapColor(FIR_PLANKS.get().defaultMapColor())));
    public static final RegistrySupplier<Block> FIR_FENCE = registerWithItem("fir_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().strength(2.0f, 3.0f).sound(SoundType.WOOD)));
    public static final RegistrySupplier<Block> FIR_FENCE_GATE = registerWithItem("fir_fence_gate", () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.of().strength(2.0f, 3.0f).sound(SoundType.WOOD).mapColor(FIR_PLANKS.get().defaultMapColor())));
    public static final RegistrySupplier<Block> FIR_WINDOW = registerWithItem("fir_window", () -> new WindowBlock(BlockBehaviour.Properties.of().strength(0.2f).randomTicks().sound(SoundType.GLASS).noOcclusion().isViewBlocking((state, world, pos) -> false).isSuffocating((state, world, pos) -> false).mapColor(MapColor.GRASS).pushReaction(PushReaction.IGNORE)));
    public static final RegistrySupplier<Block> FIR_LEAVES = registerWithItem("fir_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of().strength(0.2f).randomTicks().sound(SoundType.GRASS).noOcclusion().isViewBlocking((state, world, pos) -> false).isSuffocating((state, world, pos) -> false).mapColor(MapColor.GRASS)));
    public static final RegistrySupplier<Block> FIR_SAPLING = registerWithItem("fir_sapling", () -> new SaplingBlock(new TreeGrower("snowy_taiga_fir", Optional.empty(), Optional.of(configuredFeatureKey("snowy_taiga_fir")), Optional.empty()), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final RegistrySupplier<Block> SWAMP_CYPRESS_LOG = registerWithItem("swamp_cypress_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> SWAMP_CYPRESS_WOOD = registerWithItem("swamp_cypress_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> STRIPPED_SWAMP_CYPRESS_WOOD = registerWithItem("stripped_swamp_cypress_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> STRIPPED_SWAMP_CYPRESS_LOG = registerWithItem("stripped_swamp_cypress_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> SWAMP_CYPRESS_PLANKS = registerWithItem("swamp_cypress_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(2.0f, 3.0f).mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final RegistrySupplier<Block> SWAMP_CYPRESS_STAIRS = registerWithItem("swamp_cypress_stairs", () -> new StairBlock(SWAMP_CYPRESS_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final RegistrySupplier<Block> SWAMP_CYPRESS_SLAB = registerWithItem("swamp_cypress_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final RegistrySupplier<Block> SWAMP_CYPRESS_PRESSURE_PLATE = registerWithItem("swamp_cypress_pressure_plate", () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().noCollission().strength(0.5f).sound(SoundType.WOOD).mapColor(SWAMP_CYPRESS_PLANKS.get().defaultMapColor())));
    public static final RegistrySupplier<Block> SWAMP_CYPRESS_BUTTON = registerWithItem("swamp_cypress_button", () -> woodenButton(FeatureFlags.VANILLA));
    public static final RegistrySupplier<Block> SWAMP_CYPRESS_TRAPDOOR = registerWithItem("swamp_cypress_trapdoor", () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));
    public static final RegistrySupplier<Block> SWAMP_CYPRESS_DOOR = registerWithItem("swamp_cypress_door", () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().strength(3.0f).sound(SoundType.WOOD).noOcclusion().mapColor(SWAMP_CYPRESS_PLANKS.get().defaultMapColor())));
    public static final RegistrySupplier<Block> SWAMP_CYPRESS_FENCE = registerWithItem("swamp_cypress_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().strength(2.0f, 3.0f).sound(SoundType.WOOD)));
    public static final RegistrySupplier<Block> SWAMP_CYPRESS_FENCE_GATE = registerWithItem("swamp_cypress_fence_gate", () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.of().strength(2.0f, 3.0f).sound(SoundType.WOOD).mapColor(SWAMP_CYPRESS_PLANKS.get().defaultMapColor())));
    public static final RegistrySupplier<Block> SWAMP_CYPRESS_WINDOW = registerWithItem("swamp_cypress_window", () -> new WindowBlock(BlockBehaviour.Properties.of().strength(0.2f).randomTicks().sound(SoundType.GLASS).noOcclusion().isViewBlocking((state, world, pos) -> false).isSuffocating((state, world, pos) -> false).mapColor(MapColor.GRASS).pushReaction(PushReaction.IGNORE)));
    public static final RegistrySupplier<Block> SWAMP_CYPRESS_LEAVES = registerWithItem("swamp_cypress_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of().strength(0.2f).randomTicks().sound(SoundType.GRASS).noOcclusion().isViewBlocking((state, world, pos) -> false).isSuffocating((state, world, pos) -> false).mapColor(MapColor.GRASS)));
    public static final RegistrySupplier<Block> SWAMP_CYPRESS_SAPLING = registerWithItem("swamp_cypress_sapling", () -> new SaplingBlock(new TreeGrower("swamp_cypress", Optional.empty(), Optional.of(configuredFeatureKey("swamp_cypress")), Optional.empty()), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final RegistrySupplier<Block> EBONY_LOG = registerWithItem("ebony_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> EBONY_WOOD = registerWithItem("ebony_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> STRIPPED_EBONY_WOOD = registerWithItem("stripped_ebony_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> STRIPPED_EBONY_LOG = registerWithItem("stripped_ebony_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> EBONY_PLANKS = registerWithItem("ebony_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(2.0f, 3.0f).mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final RegistrySupplier<Block> EBONY_STAIRS = registerWithItem("ebony_stairs", () -> new StairBlock(EBONY_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final RegistrySupplier<Block> EBONY_SLAB = registerWithItem("ebony_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final RegistrySupplier<Block> EBONY_PRESSURE_PLATE = registerWithItem("ebony_pressure_plate", () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().noCollission().strength(0.5f).sound(SoundType.WOOD).mapColor(EBONY_PLANKS.get().defaultMapColor())));
    public static final RegistrySupplier<Block> EBONY_BUTTON = registerWithItem("ebony_button", () -> woodenButton(FeatureFlags.VANILLA));
    public static final RegistrySupplier<Block> EBONY_TRAPDOOR = registerWithItem("ebony_trapdoor", () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));
    public static final RegistrySupplier<Block> EBONY_DOOR = registerWithItem("ebony_door", () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().strength(3.0f).sound(SoundType.WOOD).noOcclusion().mapColor(EBONY_PLANKS.get().defaultMapColor())));
    public static final RegistrySupplier<Block> EBONY_FENCE = registerWithItem("ebony_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().strength(2.0f, 3.0f).sound(SoundType.WOOD)));
    public static final RegistrySupplier<Block> EBONY_FENCE_GATE = registerWithItem("ebony_fence_gate", () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.of().strength(2.0f, 3.0f).sound(SoundType.WOOD).mapColor(EBONY_PLANKS.get().defaultMapColor())));
    public static final RegistrySupplier<Block> EBONY_WINDOW = registerWithItem("ebony_window", () -> new WindowBlock(BlockBehaviour.Properties.of().strength(0.2f).randomTicks().sound(SoundType.GLASS).noOcclusion().isViewBlocking((state, world, pos) -> false).isSuffocating((state, world, pos) -> false).mapColor(MapColor.GRASS).pushReaction(PushReaction.IGNORE)));
    public static final RegistrySupplier<Block> EBONY_LEAVES = registerWithItem("ebony_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of().strength(0.2f).randomTicks().sound(SoundType.GRASS).noOcclusion().isViewBlocking((state, world, pos) -> false).isSuffocating((state, world, pos) -> false).mapColor(MapColor.GRASS)));
    public static final RegistrySupplier<Block> EBONY_SAPLING = registerWithItem("ebony_sapling", () -> new SaplingBlock(new TreeGrower("sparse_jungle_ebony_tree", Optional.empty(), Optional.of(configuredFeatureKey("sparse_jungle_ebony_tree")), Optional.empty()), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final RegistrySupplier<Block> CHESTNUT_LOG = registerWithItem("chestnut_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> CHESTNUT_WOOD = registerWithItem("chestnut_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> STRIPPED_CHESTNUT_WOOD = registerWithItem("stripped_chestnut_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> STRIPPED_CHESTNUT_LOG = registerWithItem("stripped_chestnut_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> CHESTNUT_PLANKS = registerWithItem("chestnut_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).strength(2.0f, 3.0f).mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final RegistrySupplier<Block> CHESTNUT_STAIRS = registerWithItem("chestnut_stairs", () -> new StairBlock(CHESTNUT_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final RegistrySupplier<Block> CHESTNUT_SLAB = registerWithItem("chestnut_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final RegistrySupplier<Block> CHESTNUT_PRESSURE_PLATE = registerWithItem("chestnut_pressure_plate", () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().noCollission().strength(0.5f).sound(SoundType.WOOD).mapColor(CHESTNUT_PLANKS.get().defaultMapColor())));
    public static final RegistrySupplier<Block> CHESTNUT_BUTTON = registerWithItem("chestnut_button", () -> woodenButton(FeatureFlags.VANILLA));
    public static final RegistrySupplier<Block> CHESTNUT_TRAPDOOR = registerWithItem("chestnut_trapdoor", () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));
    public static final RegistrySupplier<Block> CHESTNUT_DOOR = registerWithItem("chestnut_door", () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().strength(3.0f).sound(SoundType.WOOD).noOcclusion().mapColor(CHESTNUT_PLANKS.get().defaultMapColor())));
    public static final RegistrySupplier<Block> CHESTNUT_FENCE = registerWithItem("chestnut_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().strength(2.0f, 3.0f).sound(SoundType.WOOD)));
    public static final RegistrySupplier<Block> CHESTNUT_FENCE_GATE = registerWithItem("chestnut_fence_gate", () -> new FenceGateBlock( WoodType.OAK, BlockBehaviour.Properties.of().strength(2.0f, 3.0f).sound(SoundType.WOOD).mapColor(CHESTNUT_PLANKS.get().defaultMapColor())));
    public static final RegistrySupplier<Block> CHESTNUT_WINDOW = registerWithItem("chestnut_window", () -> new WindowBlock(BlockBehaviour.Properties.of().strength(0.2f).randomTicks().sound(SoundType.GLASS).noOcclusion().isViewBlocking((state, world, pos) -> false).isSuffocating((state, world, pos) -> false).mapColor(MapColor.GRASS).pushReaction(PushReaction.IGNORE)));
    public static final RegistrySupplier<Block> CHESTNUT_LEAVES = registerWithItem("chestnut_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of().strength(0.2f).randomTicks().sound(SoundType.GRASS).noOcclusion().isViewBlocking((state, world, pos) -> false).isSuffocating((state, world, pos) -> false).mapColor(MapColor.GRASS)));
    public static final RegistrySupplier<Block> CHESTNUT_SAPLING = registerWithItem("chestnut_sapling", () -> new SaplingBlock(new TreeGrower("plains_chestnut_tree", Optional.empty(), Optional.of(configuredFeatureKey("plains_chestnut_tree")), Optional.empty()), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final RegistrySupplier<Block> RED_BRICKS = registerWithItem("red_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS)));
    public static final RegistrySupplier<Block> RED_BRICK_STAIRS = registerWithItem("red_brick_stairs", () -> new StairBlock(RED_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(RED_BRICKS.get())));
    public static final RegistrySupplier<Block> RED_BRICK_SLAB = registerWithItem("red_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(RED_BRICKS.get())));
    public static final RegistrySupplier<Block> RED_BRICK_WALL = registerWithItem("red_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(RED_BRICKS.get())));
    public static final RegistrySupplier<Block> TRAVERTIN = registerWithItem("travertin", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE)));
    public static final RegistrySupplier<Block> TRAVERTIN_BRICKS = registerWithItem("travertin_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE)));
    public static final RegistrySupplier<Block> TRAVERTIN_BRICK_STAIRS = registerWithItem("travertin_brick_stairs", () -> new StairBlock(TRAVERTIN_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(RED_BRICKS.get())));
    public static final RegistrySupplier<Block> TRAVERTIN_BRICK_SLAB = registerWithItem("travertin_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(TRAVERTIN_BRICKS.get())));
    public static final RegistrySupplier<Block> TRAVERTIN_BRICK_WALL = registerWithItem("travertin_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(TRAVERTIN_BRICKS.get())));
    public static final RegistrySupplier<Block> MOSSY_LATERIT = registerWithItem("mossy_laterit", () -> new LateritGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.GRASS).randomTicks().strength(0.9F).sound(SoundType.GRASS)));
    public static final RegistrySupplier<Block> LATERIT = registerWithItem("laterit", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final RegistrySupplier<Block> LATERIT_BRICKS = registerWithItem("laterit_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final RegistrySupplier<Block> LATERIT_BRICK_STAIRS = registerWithItem("laterit_brick_stairs", () -> new StairBlock(LATERIT_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(RED_BRICKS.get())));
    public static final RegistrySupplier<Block> LATERIT_BRICK_SLAB = registerWithItem("laterit_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(LATERIT_BRICKS.get())));
    public static final RegistrySupplier<Block> LATERIT_BRICK_WALL = registerWithItem("laterit_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(LATERIT_BRICKS.get())));
    public static final RegistrySupplier<Block> MUSHROOM_BRICKS = registerWithItem("mushroom_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MUSHROOM_STEM)));
    public static final RegistrySupplier<Block> MUSHROOM_BRICK_STAIRS = registerWithItem("mushroom_brick_stairs", () -> new StairBlock(MUSHROOM_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MUSHROOM_BRICKS.get())));
    public static final RegistrySupplier<Block> MUSHROOM_BRICK_SLAB = registerWithItem("mushroom_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MUSHROOM_BRICKS.get())));
    public static final RegistrySupplier<Block> MUSHROOM_BRICK_WALL = registerWithItem("mushroom_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(MUSHROOM_BRICKS.get())));
    public static final RegistrySupplier<Block> BROWN_MUSHROOM_BRICKS = registerWithItem("brown_mushroom_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MUSHROOM_STEM)));
    public static final RegistrySupplier<Block> BROWN_MUSHROOM_BRICK_STAIRS = registerWithItem("brown_mushroom_brick_stairs", () -> new StairBlock(MUSHROOM_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MUSHROOM_BRICKS.get())));
    public static final RegistrySupplier<Block> BROWN_MUSHROOM_BRICK_SLAB = registerWithItem("brown_mushroom_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MUSHROOM_BRICKS.get())));
    public static final RegistrySupplier<Block> BROWN_MUSHROOM_BRICK_WALL = registerWithItem("brown_mushroom_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(MUSHROOM_BRICKS.get())));
    public static final RegistrySupplier<Block> FOREST_MOSS = registerWithItem("forest_moss", () -> new MudBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK).speedFactor(0.75F)));
    public static final RegistrySupplier<Block> FOREST_MOSS_CARPET = registerWithItem("forest_moss_carpet", () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSS_CARPET).speedFactor(0.75F)));
    public static final RegistrySupplier<Block> MARSH_BLOCK = registerWithItem("marsh_block", () -> new SinkInBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MUD)));
    public static final RegistrySupplier<Block> TERMITE_MOUND = registerWithItem("termite_mound", () -> new TermiteBlock(Blocks.ROOTED_DIRT, BlockBehaviour.Properties.of().mapColor(MapColor.CLAY)));
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
    public static final RegistrySupplier<Block> FOXGLOVE_WHITE = registerWithItem("foxglove_white", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_TULIP)));
    public static final RegistrySupplier<Block> POTTED_FOXGLOVE_WHITE = registerWithoutItem("potted_foxglove_white", () -> new FlowerPotBlock(FOXGLOVE_WHITE.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> FOXGLOVE_PINK = registerWithItem("foxglove_pink", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_TULIP)));
    public static final RegistrySupplier<Block> POTTED_FOXGLOVE_PINK = registerWithoutItem("potted_foxglove_pink", () -> new FlowerPotBlock(FOXGLOVE_PINK.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
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
    public static final RegistrySupplier<Block> BEACH_GRASS = registerWithItem("beach_grass", () -> new DeadBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ALLIUM)));
    public static final RegistrySupplier<Block> BEACH_BUSH = registerWithItem("beach_bush", () -> new DeadBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> POTTED_BEACH_BUSH = registerWithoutItem("potted_beach_bush", () -> new FlowerPotBlock(BEACH_BUSH.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> BEACH_BUSH_TALL = registerWithItem("beach_bush_tall", () -> new DeadBushTallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH)));
    public static final RegistrySupplier<Block> CATTAIL = registerWithItem("cattail", () -> new CattailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_SEAGRASS)));
    public static final RegistrySupplier<Block> REED = registerWithItem("reed", () -> new CattailBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_SEAGRASS)));
    public static final RegistrySupplier<Block> CARDINAL = registerWithItem("cardinal", () -> new TallFlowerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH)));
    public static final RegistrySupplier<Block> TALL_MOUNTAIN_LAUREL = registerWithItem("tall_mountain_laurel", () -> new TallFlowerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH)));
    public static final RegistrySupplier<Block> WILD_SUNFLOWER = registerWithItem("wild_sunflower", () -> new TallFlowerBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH)));
    public static final RegistrySupplier<Block> FLOWER_POT_BIG = registerWithItem("flower_pot_big", () -> new BNFlowerPotBigBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> FLOWER_BOX = registerWithItem("flower_box", () -> new FlowerBoxBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Item>  BLOOMINGNATURE_STANDARD = registerItem("bloomingnature_standard", () -> new BloomingNatureStandardItem(new Item.Properties().stacksTo(16).rarity(Rarity.EPIC)));
    public static final RegistrySupplier<Item> WANDERING_GARDENER_SPAWN_EGG = registerItem("wandering_gardener_spawn_egg", () -> new ArchitecturySpawnEggItem(EntityRegistry.WANDERING_GARDENER, -1, -1, getSettings()));
    public static final RegistrySupplier<Item> DEER_SPAWN_EGG = registerItem("deer_spawn_egg", () -> new ArchitecturySpawnEggItem(EntityRegistry.DEER, -1, -1, getSettings()));
    public static final RegistrySupplier<Item> RED_WOLF_SPAWN_EGG = registerItem("red_wolf_spawn_egg", () -> new ArchitecturySpawnEggItem(EntityRegistry.RED_WOLF, -1, -1, getSettings()));
    public static final RegistrySupplier<Item> RACCOON_SPAWN_EGG = registerItem("raccoon_spawn_egg", () -> new ArchitecturySpawnEggItem(EntityRegistry.RACCOON, -1, -1, getSettings()));
    public static final RegistrySupplier<Item> SQUIRREL_SPAWN_EGG = registerItem("squirrel_spawn_egg", () -> new ArchitecturySpawnEggItem(EntityRegistry.SQUIRREL, -1, -1, getSettings()));
    public static final RegistrySupplier<Item> MOSSY_SHEEP_SPAWN_EGG = registerItem("mossy_sheep_spawn_egg", () -> new ArchitecturySpawnEggItem(EntityRegistry.MOSSY_SHEEP, -1, -1, getSettings()));
    public static final RegistrySupplier<Item> MUDDY_PIG_SPAWN_EGG = registerItem("muddy_pig_spawn_egg", () -> new ArchitecturySpawnEggItem(EntityRegistry.MUDDY_PIG, -1, -1, getSettings()));
    public static final RegistrySupplier<Item> PELICAN_SPAWN_EGG = registerItem("pelican_spawn_egg", () -> new ArchitecturySpawnEggItem(EntityRegistry.PELICAN, -1, -1, getSettings()));
    public static final RegistrySupplier<Item> OWL_SPAWN_EGG = registerItem("owl_spawn_egg", () -> new ArchitecturySpawnEggItem(EntityRegistry.OWL, -1, -1, getSettings()));
    public static final RegistrySupplier<Item> BOAR_SPAWN_EGG = registerItem("boar_spawn_egg", () -> new ArchitecturySpawnEggItem(EntityRegistry.BOAR, -1, -1, getSettings()));
    public static final RegistrySupplier<Item> TERMITE_SPAWN_EGG = registerItem("termite_spawn_egg", () -> new ArchitecturySpawnEggItem(EntityRegistry.TERMITE, -1, -1, getSettings()));
    public static final RegistrySupplier<Item> BISON_SPAWN_EGG = registerItem("bison_spawn_egg", () -> new ArchitecturySpawnEggItem(EntityRegistry.BISON, -1, -1, getSettings()));
    public static final RegistrySupplier<Item> TURKEY_SPAWN_EGG = registerItem("turkey_spawn_egg", () -> new ArchitecturySpawnEggItem(EntityRegistry.TURKEY, -1, -1, getSettings()));
    public static final RegistrySupplier<Block> FLOATING_LEAVES = registerWithoutItem("floating_leaves", () -> new FloatingLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LILY_PAD)));
    public static final RegistrySupplier<Block> COCONUT_HANGING = registerWithoutItem("coconut_hanging", () -> new HangingCoconutBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO)));
    public static final RegistrySupplier<Block> POTTED_LARCH_SAPLING = registerWithoutItem("potted_larch_sapling", () -> new FlowerPotBlock(LARCH_SAPLING.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_FIR_SAPLING = registerWithoutItem("potted_fir_sapling", () -> new FlowerPotBlock(FIR_SAPLING.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_SWAMP_CYPRESS_SAPLING = registerWithoutItem("potted_swamp_cypress_sapling", () -> new FlowerPotBlock(SWAMP_CYPRESS_SAPLING.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_SWAMP_OAK_SAPLING = registerWithoutItem("potted_swamp_oak_sapling", () -> new FlowerPotBlock(SWAMP_OAK_SAPLING.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_FAN_PALM_SPROUT = registerWithoutItem("potted_fan_palm_sprout", () -> new FlowerPotBlock(FAN_PALM_SPROUT.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_ASPEN_SAPLING = registerWithoutItem("potted_aspen_sapling", () -> new FlowerPotBlock(ASPEN_SAPLING.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_BAOBAB_SAPLING = registerWithoutItem("potted_baobab_sapling", () -> new FlowerPotBlock(BAOBAB_SAPLING.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_EBONY_SAPLING = registerWithoutItem("potted_ebony_sapling", () -> new FlowerPotBlock(EBONY_SAPLING.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_CHESTNUT_SAPLING = registerWithoutItem("potted_chestnut_sapling", () -> new FlowerPotBlock(CHESTNUT_SAPLING.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));

    public static void init() {
        BloomingNature.LOGGER.debug("Registering Mod Block and Items for " + BloomingNature.MOD_ID);
        ITEMS.register();
        BLOCKS.register();
    }

    private static RegistrySupplier<Block> registerLog(String path) {
        return registerWithItem(path, () -> new RotatedPillarBlock(getLogBlockSettings()));
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
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, BloomingNatureIdentifier.of( name));
    }
    
    public static void commonInit() {
        FuelRegistry.register(300, FAN_PALM_FENCE.get(), FAN_PALM_FENCE_GATE.get(), FAN_PALM_PLANKS.get(), FAN_PALM_LOG.get(), FAN_PALM_WOOD.get(),
                STRIPPED_FAN_PALM_LOG.get(), STRIPPED_FAN_PALM_WOOD.get(), BAOBAB_PLANKS.get(), BAOBAB_SLAB.get(), BAOBAB_STAIRS.get(), BAOBAB_FENCE.get(),
                SWAMP_OAK_PLANKS.get(), SWAMP_OAK_SLAB.get(), SWAMP_OAK_STAIRS.get(), SWAMP_OAK_FENCE.get(), SWAMP_OAK_FENCE_GATE.get(),
                SWAMP_CYPRESS_PLANKS.get(), SWAMP_CYPRESS_SLAB.get(), SWAMP_CYPRESS_STAIRS.get(), SWAMP_CYPRESS_FENCE.get(), SWAMP_CYPRESS_FENCE_GATE.get(),
                LARCH_PLANKS.get(), LARCH_SLAB.get(), LARCH_STAIRS.get(), LARCH_FENCE.get(), LARCH_FENCE_GATE.get(), BAOBAB_FENCE_GATE.get(),
                FIR_PLANKS.get(), FIR_SLAB.get(), FIR_STAIRS.get(), FIR_FENCE.get(), FIR_FENCE_GATE.get(),
                CHESTNUT_PLANKS.get(), CHESTNUT_SLAB.get(), CHESTNUT_STAIRS.get(), CHESTNUT_FENCE.get(), CHESTNUT_FENCE_GATE.get(),
                FAN_PALM_PLANKS.get(), FAN_PALM_SLAB.get(), FAN_PALM_STAIRS.get(), FAN_PALM_FENCE.get(), FAN_PALM_FENCE_GATE.get(),
                ASPEN_PLANKS.get(), ASPEN_SLAB.get(), ASPEN_STAIRS.get(), ASPEN_FENCE.get(), ASPEN_FENCE_GATE.get(),
                EBONY_PLANKS.get(), EBONY_SLAB.get(), EBONY_STAIRS.get(), EBONY_FENCE.get(), EBONY_FENCE_GATE.get(),
                SWAMP_OAK_LOG.get(), SWAMP_OAK_WOOD.get(), STRIPPED_SWAMP_OAK_LOG.get(), STRIPPED_SWAMP_OAK_WOOD.get(),
                SWAMP_CYPRESS_LOG.get(), SWAMP_CYPRESS_WOOD.get(), STRIPPED_SWAMP_CYPRESS_LOG.get(), STRIPPED_SWAMP_CYPRESS_WOOD.get(),
                LARCH_LOG.get(), LARCH_WOOD.get(), STRIPPED_LARCH_LOG.get(), STRIPPED_LARCH_WOOD.get(),
                FIR_LOG.get(), FIR_WOOD.get(), STRIPPED_FIR_LOG.get(), STRIPPED_FIR_WOOD.get(),
                CHESTNUT_LOG.get(), CHESTNUT_WOOD.get(), STRIPPED_CHESTNUT_LOG.get(), STRIPPED_CHESTNUT_WOOD.get(),
                FAN_PALM_LOG.get(), FAN_PALM_WOOD.get(), STRIPPED_FAN_PALM_LOG.get(), STRIPPED_FAN_PALM_WOOD.get(),
                ASPEN_LOG.get(), ASPEN_WOOD.get(), STRIPPED_ASPEN_LOG.get(), STRIPPED_ASPEN_WOOD.get(),
                EBONY_LOG.get(), EBONY_WOOD.get(), STRIPPED_EBONY_LOG.get(), STRIPPED_EBONY_WOOD.get(),
                BAOBAB_LOG.get(), BAOBAB_WOOD.get(), STRIPPED_BAOBAB_LOG.get(), STRIPPED_BAOBAB_WOOD.get());
    }

    private static ButtonBlock woodenButton(FeatureFlag... featureFlags) {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.of().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY);
        if (featureFlags.length > 0) {
            properties = properties.requiredFeatures(featureFlags);
        }

        return new ButtonBlock(BlockSetType.OAK, 30, properties);
    }
    
    public static <T extends Block> RegistrySupplier<T> registerWithItem(String name, Supplier<T> block) {
        return Util.registerWithItem(BLOCKS, BLOCK_REGISTRAR, ITEMS, ITEM_REGISTRAR, BloomingNatureIdentifier.of(name), block);
    }

    public static <T extends Block> RegistrySupplier<T> registerWithoutItem(String path, Supplier<T> block) {
        return Util.registerWithoutItem(BLOCKS, BLOCK_REGISTRAR, BloomingNatureIdentifier.of(path), block);
    }

    public static <T extends Item> RegistrySupplier<T> registerItem(String path, Supplier<T> itemSupplier) {
        return Util.registerItem(ITEMS, ITEM_REGISTRAR, BloomingNatureIdentifier.of(path), itemSupplier);
    }
}
