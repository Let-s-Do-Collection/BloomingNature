package net.satisfy.bloomingnature.core.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.satisfy.bloomingnature.BloomingNature;
import net.satisfy.bloomingnature.core.world.feature.configured.rock.RockPileFeature;
import net.satisfy.bloomingnature.core.world.feature.configured.rock.RockPileFeatureConfig;
import net.satisfy.bloomingnature.core.world.feature.configured.tree.foliage.AspenFoliagePlacer;
import net.satisfy.bloomingnature.core.world.feature.configured.tree.foliage.CornFoliagePlacer;
import net.satisfy.bloomingnature.core.world.feature.configured.tree.trunk.PalmTrunkPlacer;
import net.satisfy.bloomingnature.core.world.feature.configured.tree.foliage.CypressFoliagePlacer;
import net.satisfy.bloomingnature.core.world.feature.configured.tree.foliage.PalmFoliagePlacer;
import net.satisfy.bloomingnature.core.world.feature.configured.tree.foliage.LarchFoliagePlacer;
import net.satisfy.bloomingnature.core.world.feature.configured.tree.foliage.RodBirchFoliagePlacer;
import net.satisfy.bloomingnature.core.world.feature.configured.tree.foliage.TaigaFoliagePlacer;

public class PlacerTypeRegistry {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER_TYPES = DeferredRegister.create(BloomingNature.MOD_ID, Registries.FOLIAGE_PLACER_TYPE);
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER_TYPES = DeferredRegister.create(BloomingNature.MOD_ID, Registries.TRUNK_PLACER_TYPE);
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(BloomingNature.MOD_ID, Registries.FEATURE);

    public static final RegistrySupplier<FoliagePlacerType<AspenFoliagePlacer>> ASPEN_FOLIAGE_PLACER = FOLIAGE_PLACER_TYPES.register("aspen_foliage_placer", () -> new FoliagePlacerType<>(AspenFoliagePlacer.CODEC));
    public static final RegistrySupplier<FoliagePlacerType<CornFoliagePlacer>> CORN_FOLIAGE_PLACER = FOLIAGE_PLACER_TYPES.register("corn_foliage_placer", () -> new FoliagePlacerType<>(CornFoliagePlacer.CODEC));
    public static final RegistrySupplier<FoliagePlacerType<CypressFoliagePlacer>> CYPRESS_FOLIAGE_PLACER = FOLIAGE_PLACER_TYPES.register("cypress_foliage_placer", () -> new FoliagePlacerType<>(CypressFoliagePlacer.CODEC));
    public static final RegistrySupplier<FoliagePlacerType<LarchFoliagePlacer>> LARCH_FOLIAGE_PLACER = FOLIAGE_PLACER_TYPES.register("larch_foliage_placer", () -> new FoliagePlacerType<>(LarchFoliagePlacer.CODEC));
    public static final RegistrySupplier<FoliagePlacerType<RodBirchFoliagePlacer>> ROD_BIRCH_FOLIAGE_PLACER = FOLIAGE_PLACER_TYPES.register("birch_foliage_placer", () -> new FoliagePlacerType<>(RodBirchFoliagePlacer.CODEC));
    public static final RegistrySupplier<FoliagePlacerType<TaigaFoliagePlacer>> TAIGA_FOLIAGE_PLACER = FOLIAGE_PLACER_TYPES.register("taiga_foliage_placer", () -> new FoliagePlacerType<>(TaigaFoliagePlacer.CODEC));
    public static final RegistrySupplier<FoliagePlacerType<PalmFoliagePlacer>> PALM_FOLIAGE_PLACER = FOLIAGE_PLACER_TYPES.register("palm_foliage_placer", () -> new FoliagePlacerType<>(PalmFoliagePlacer.CODEC));

    public static final RegistrySupplier<TrunkPlacerType<PalmTrunkPlacer>> PALM_TRUNK_PLACER = TRUNK_PLACER_TYPES.register("palm_trunk_placer", () -> new TrunkPlacerType<>(PalmTrunkPlacer.CODEC));

    public static final RegistrySupplier<Feature<RockPileFeatureConfig>> ROCK_PILE_FEATURE = FEATURES.register("rock_pile", RockPileFeature::new);

    public static void init() {
        FOLIAGE_PLACER_TYPES.register();
        TRUNK_PLACER_TYPES.register();
        FEATURES.register();
    }
}