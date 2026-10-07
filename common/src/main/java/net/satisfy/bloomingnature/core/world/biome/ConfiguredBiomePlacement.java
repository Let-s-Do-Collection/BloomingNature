package net.satisfy.bloomingnature.core.world.biome;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.Criterion;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.satisfy.bloomingnature.core.config.BloomingNatureConfig;

public final class ConfiguredBiomePlacement {
    private ConfiguredBiomePlacement() {
    }

    public static void replaceOverworld(ResourceKey<Biome> target, ResourceKey<Biome> biome, double proportion) {
        if (BloomingNatureConfig.isBiomeEnabled(biome)) {
            BiomePlacement.replaceOverworld(target, biome, proportion);
        }
    }

    public static void addSubOverworld(ResourceKey<Biome> target, ResourceKey<Biome> biome, Criterion criterion) {
        if (BloomingNatureConfig.isBiomeEnabled(target) && BloomingNatureConfig.isBiomeEnabled(biome)) {
            BiomePlacement.addSubOverworld(target, biome, criterion);
        }
    }
}
