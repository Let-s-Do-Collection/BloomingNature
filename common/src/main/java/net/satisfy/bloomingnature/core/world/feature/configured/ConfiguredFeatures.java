package net.satisfy.bloomingnature.core.world.feature.configured;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.satisfy.bloomingnature.BloomingNature;
import net.satisfy.bloomingnature.core.world.feature.configured.rock.RockPileFeature;
import net.satisfy.bloomingnature.core.world.feature.configured.rock.RockPileFeatureConfig;


public class ConfiguredFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(BloomingNature.MOD_ID, Registries.FEATURE);

    public static final ResourceKey<ConfiguredFeature<?, ?>> FAN_PALM_TREE_KEY = registerConfiguredFeature("beach_fan_palm");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FOREST_MOSS_PATCH_BONEMEAL_KEY = registerConfiguredFeature("forest_moss_patch_bonemeal");

    public static final RegistrySupplier<Feature<RockPileFeatureConfig>> ROCK_PILE_FEATURE = FEATURES.register("rock_pile", RockPileFeature::new);

    public static ResourceKey<ConfiguredFeature<?, ?>> registerConfiguredFeature(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, BloomingNature.identifier(name));
    }
}