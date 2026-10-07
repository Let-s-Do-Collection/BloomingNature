package net.satisfy.bloomingnature.core.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PlaceOnWaterBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.satisfy.bloomingnature.BloomingNature;
import net.satisfy.bloomingnature.core.block.JungleFernBlock;
import net.satisfy.bloomingnature.core.block.WildVinesBlock;
import net.satisfy.bloomingnature.core.world.feature.WildVinesFeature;

public final class FloraRegistry {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(BloomingNature.MOD_ID, Registries.FEATURE);

    public static final RegistrySupplier<Block> TALL_JUNGLE_FERN = ObjectRegistry.registerWithItem("tall_jungle_fern", () -> new DoublePlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LARGE_FERN)));
    public static final RegistrySupplier<Block> JUNGLE_FERN = ObjectRegistry.registerWithItem("jungle_fern", () -> new JungleFernBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.FERN)));
    public static final RegistrySupplier<Block> POTTED_JUNGLE_FERN = ObjectRegistry.registerWithoutItem("potted_jungle_fern", () -> new FlowerPotBlock(JUNGLE_FERN.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> WILD_VINES = ObjectRegistry.registerWithItem("wild_vines", () -> new WildVinesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.VINE).lightLevel(WildVinesBlock::light)));
    public static final RegistrySupplier<Block> FLOWERING_LILY_PAD = ObjectRegistry.registerWithoutItem("flowering_lily_pad", () -> new WaterlilyBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LILY_PAD)));
    public static final RegistrySupplier<Item> FLOWERING_LILY_PAD_ITEM = ObjectRegistry.registerItem("flowering_lily_pad", () -> new PlaceOnWaterBlockItem(FLOWERING_LILY_PAD.get(), new Item.Properties()));

    public static final RegistrySupplier<Feature<NoneFeatureConfiguration>> WILD_VINES_FEATURE = FEATURES.register(BloomingNature.identifier("wild_vines"), WildVinesFeature::new);

    private FloraRegistry() {
    }

    public static void init() {
        FEATURES.register();
    }
}
