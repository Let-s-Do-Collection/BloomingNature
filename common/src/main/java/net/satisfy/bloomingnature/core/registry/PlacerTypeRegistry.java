package net.satisfy.bloomingnature.core.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.satisfy.bloomingnature.BloomingNature;
import net.satisfy.bloomingnature.core.world.feature.configured.tree.decorator.MushroomDecorator;
import net.satisfy.bloomingnature.core.world.feature.configured.tree.foliage.*;
import net.satisfy.bloomingnature.core.world.feature.configured.tree.trunk.BaobabTrunkPlacer;
import net.satisfy.bloomingnature.core.world.feature.configured.tree.trunk.CrookedTrunkPlacer;

import static net.satisfy.bloomingnature.core.world.feature.configured.ConfiguredFeatures.FEATURES;

public class PlacerTypeRegistry {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER_TYPES = DeferredRegister.create(BloomingNature.MOD_ID, Registries.FOLIAGE_PLACER_TYPE);
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER_TYPES = DeferredRegister.create(BloomingNature.MOD_ID, Registries.TRUNK_PLACER_TYPE);
    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATOR_TYPES = DeferredRegister.create(BloomingNature.MOD_ID, Registries.TREE_DECORATOR_TYPE);

    public static final RegistrySupplier<FoliagePlacerType<AspenFoliagePlacer>> ASPEN_FOLIAGE_PLACER = FOLIAGE_PLACER_TYPES.register("aspen_foliage_placer", () -> new FoliagePlacerType<>(AspenFoliagePlacer.CODEC));
    public static final RegistrySupplier<FoliagePlacerType<CypressFoliagePlacer>> CYPRESS_FOLIAGE_PLACER = FOLIAGE_PLACER_TYPES.register("cypress_foliage_placer", () -> new FoliagePlacerType<>(CypressFoliagePlacer.CODEC));
    public static final RegistrySupplier<FoliagePlacerType<LarchFoliagePlacer>> LARCH_FOLIAGE_PLACER = FOLIAGE_PLACER_TYPES.register("larch_foliage_placer", () -> new FoliagePlacerType<>(LarchFoliagePlacer.CODEC));
    public static final RegistrySupplier<FoliagePlacerType<PalmFoliagePlacer>> PALM_FOLIAGE_PLACER = FOLIAGE_PLACER_TYPES.register("palm_foliage_placer", () -> new FoliagePlacerType<>(PalmFoliagePlacer.CODEC));
    public static final RegistrySupplier<FoliagePlacerType<TaigaFoliagePlacer>> TAIGA_FOLIAGE_PLACER = FOLIAGE_PLACER_TYPES.register("taiga_foliage_placer", () -> new FoliagePlacerType<>(TaigaFoliagePlacer.CODEC));

    public static final RegistrySupplier<TrunkPlacerType<CrookedTrunkPlacer>> CROOKED_TRUNK_PLACER = TRUNK_PLACER_TYPES.register("crooked_trunk_placer", () -> new TrunkPlacerType<>(CrookedTrunkPlacer.CODEC));
    public static final RegistrySupplier<TrunkPlacerType<BaobabTrunkPlacer>> BAOBAB_TRUNK_PLACER = TRUNK_PLACER_TYPES.register("baobab_trunk_placer", () -> new TrunkPlacerType<>(BaobabTrunkPlacer.CODEC));

    public static final RegistrySupplier<TreeDecoratorType<MushroomDecorator>> MUSHROOM_DECORATOR = TREE_DECORATOR_TYPES.register("mushroom_decorator", () -> new TreeDecoratorType<>(MushroomDecorator.CODEC));


    public static void init() {
        FOLIAGE_PLACER_TYPES.register();
        TRUNK_PLACER_TYPES.register();
        TREE_DECORATOR_TYPES.register();
        FEATURES.register();
    }
}