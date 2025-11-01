package net.satisfy.bloomingnature.core.world.feature.configured;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.satisfy.bloomingnature.BloomingNature;

public class ConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> FAN_PALM_TREE_KEY = registerConfiguredFeature("beach_fan_palm");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FOREST_MOSS_PATCH_BONEMEAL_KEY = registerConfiguredFeature("forest_moss_patch_bonemeal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ANDESITE_PILE_KEY = registerConfiguredFeature("arid/andesite_pile");

    public static ResourceKey<ConfiguredFeature<?, ?>> registerConfiguredFeature(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, BloomingNature.identifier(name));
    }
}