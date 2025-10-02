package net.satisfy.bloomingnature.core.world;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.satisfy.bloomingnature.BloomingNature;

public class ConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?,?>> FAN_PALM_TREE_KEY = registerKey("beach_fan_palm");

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, BloomingNature.identifier(name));
    }
}