package net.satisfy.bloomingnature.core.world;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.satisfy.bloomingnature.core.util.BloomingNatureIdentifier;

public class ConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> FAN_PALM_TREE_KEY = registerKey("beach_fan_palm");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FOREST_MOSS_PATCH_BONEMEAL_KEY = registerKey("forest_moss_patch_bonemeal");

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new BloomingNatureIdentifier(name));
    }
}

