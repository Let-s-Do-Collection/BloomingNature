package net.satisfy.bloomingnature.core.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.satisfy.bloomingnature.BloomingNature;
import net.satisfy.bloomingnature.core.world.feature.configured.RockPileFeature;
import net.satisfy.bloomingnature.core.world.feature.configured.RockPileFeatureConfig;
import net.satisfy.bloomingnature.core.world.tree.foliage.AspenFoliagePlacer;
import net.satisfy.bloomingnature.core.world.tree.foliage.CornFoliagePlacer;
import net.satisfy.bloomingnature.core.world.tree.foliage.CrookedTrunkPlacer;
import net.satisfy.bloomingnature.core.world.tree.foliage.CypressFoliagePlacer;
import net.satisfy.bloomingnature.core.world.tree.foliage.FanPalmFoliagePlacer;
import net.satisfy.bloomingnature.core.world.tree.foliage.LarchFoliagePlacer;
import net.satisfy.bloomingnature.core.world.tree.foliage.RodBirchFoliagePlacer;
import net.satisfy.bloomingnature.core.world.tree.foliage.TaigaFoliagePlacer;

public class PlacerTypesRegistry {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER_TYPES = DeferredRegister.create(BloomingNature.MOD_ID, Registries.FOLIAGE_PLACER_TYPE);
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER_TYPES = DeferredRegister.create(BloomingNature.MOD_ID, Registries.TRUNK_PLACER_TYPE);
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(BloomingNature.MOD_ID, Registries.FEATURE);

    public static final RegistrySupplier<FoliagePlacerType<AspenFoliagePlacer>> ASPEN_FOLIAGE_PLACER = FOLIAGE_PLACER_TYPES.register("aspen_foliage_placer", () -> new FoliagePlacerType<>(AspenFoliagePlacer.CODEC));
    public static final RegistrySupplier<FoliagePlacerType<CornFoliagePlacer>> CORN_FOLIAGE_PLACER = FOLIAGE_PLACER_TYPES.register("corn_foliage_placer", () -> new FoliagePlacerType<>(CornFoliagePlacer.CODEC));
    public static final RegistrySupplier<FoliagePlacerType<CypressFoliagePlacer>> CYPRESS_FOLIAGE_PLACER = FOLIAGE_PLACER_TYPES.register("cypress_foliage_placer", () -> new FoliagePlacerType<>(CypressFoliagePlacer.CODEC));
    public static final RegistrySupplier<FoliagePlacerType<LarchFoliagePlacer>> LARCH_FOLIAGE_PLACER = FOLIAGE_PLACER_TYPES.register("larch_foliage_placer", () -> new FoliagePlacerType<>(LarchFoliagePlacer.CODEC));
    public static final RegistrySupplier<FoliagePlacerType<RodBirchFoliagePlacer>> ROD_BIRCH_FOLIAGE_PLACER = FOLIAGE_PLACER_TYPES.register("birch_foliage_placer", () -> new FoliagePlacerType<>(RodBirchFoliagePlacer.CODEC));
    public static final RegistrySupplier<FoliagePlacerType<TaigaFoliagePlacer>> TAIGA_FOLIAGE_PLACER = FOLIAGE_PLACER_TYPES.register("taiga_foliage_placer", () -> new FoliagePlacerType<>(TaigaFoliagePlacer.CODEC));
    public static final RegistrySupplier<FoliagePlacerType<FanPalmFoliagePlacer>> FAN_PALM_FOLIAGE_PLACER = FOLIAGE_PLACER_TYPES.register("fan_palm_foliage_placer", () -> new FoliagePlacerType<>(FanPalmFoliagePlacer.CODEC));

    public static final RegistrySupplier<TrunkPlacerType<CrookedTrunkPlacer>> CROOKED_TRUNK_PLACER = TRUNK_PLACER_TYPES.register("crooked_trunk_placer", () -> new TrunkPlacerType<>(CrookedTrunkPlacer.CODEC));

    public static final RegistrySupplier<Feature<RockPileFeatureConfig>> ROCK_PILE_FEATURE = FEATURES.register("rock_pile", RockPileFeature::new);

    public static void init() {
        FOLIAGE_PLACER_TYPES.register();
        TRUNK_PLACER_TYPES.register();
        FEATURES.register();
    }
}