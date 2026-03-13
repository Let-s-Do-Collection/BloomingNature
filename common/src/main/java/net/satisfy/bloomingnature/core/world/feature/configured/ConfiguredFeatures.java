package net.satisfy.bloomingnature.core.world.feature.configured;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.satisfy.bloomingnature.BloomingNature;
import net.satisfy.bloomingnature.core.world.feature.configured.decoration.FallenHollowTrunkConfiguration;
import net.satisfy.bloomingnature.core.world.feature.configured.decoration.FallenHollowTrunkFeature;
import net.satisfy.bloomingnature.core.world.feature.configured.decoration.GroundLitterConfiguration;
import net.satisfy.bloomingnature.core.world.feature.configured.decoration.GroundLitterFeature;
import net.satisfy.bloomingnature.core.world.feature.configured.rock.RockPileFeature;
import net.satisfy.bloomingnature.core.world.feature.configured.rock.RockPileFeatureConfig;

public class ConfiguredFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(BloomingNature.MOD_ID, Registries.FEATURE);

    public static final ResourceKey<ConfiguredFeature<?, ?>> FAN_PALM_TREE_KEY = registerConfiguredFeature("trees/fan_palm/fan_palm");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FOREST_MOSS_PATCH_BONEMEAL_KEY = registerConfiguredFeature("vegetation/forest_moss_patch_bonemeal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BOG_MOSS_PATCH_BONEMEAL_KEY = registerConfiguredFeature("vegetation/bog_moss_patch_bonemeal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GROUND_LITTER_KEY = registerConfiguredFeature("vegetation/ground_litter");

    public static final RegistrySupplier<Feature<RockPileFeatureConfig>> ROCK_PILE_FEATURE = FEATURES.register("rock_pile", RockPileFeature::new);
    public static final RegistrySupplier<Feature<FallenHollowTrunkConfiguration>> FALLEN_HOLLOW_TRUNK_FEATURE = FEATURES.register("fallen_hollow_trunk", () -> new FallenHollowTrunkFeature(FallenHollowTrunkConfiguration.CODEC));
    public static final RegistrySupplier<Feature<GroundLitterConfiguration>> GROUND_LITTER_FEATURE = FEATURES.register("ground_litter", () -> new GroundLitterFeature(GroundLitterConfiguration.CODEC));

    public static ResourceKey<ConfiguredFeature<?, ?>> registerConfiguredFeature(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, BloomingNature.identifier(name));
    }
}