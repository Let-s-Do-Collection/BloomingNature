package net.satisfy.bloomingnature.core.world.biome.cold;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.surface.BiolithSurfaceBuilder;
import net.minecraft.world.level.biome.Biomes;
import net.satisfy.bloomingnature.core.world.biome.BloomingNatureBiomeKeys;

import static com.terraformersmc.biolith.api.biome.sub.CriterionBuilder.anyOf;
import static com.terraformersmc.biolith.api.biome.sub.CriterionBuilder.neighbor;

public final class ColdBiomeRegistry extends BiolithSurfaceBuilder {
    public static void registerBiomePlacement() {
        registerColdRiverPlacement();
        registerColdGrasslandPlacement();
        registerLarchForestPlacement();
    }

    private static void registerColdRiverPlacement() {
        var coldBiomes = anyOf(
                neighbor(Biomes.SNOWY_TAIGA),
                neighbor(Biomes.TAIGA),
                neighbor(Biomes.OLD_GROWTH_SPRUCE_TAIGA),
                neighbor(Biomes.OLD_GROWTH_PINE_TAIGA),
                neighbor(BloomingNatureBiomeKeys.LARCH_FOREST),
                neighbor(BloomingNatureBiomeKeys.COLD_GRASSLAND)
        );

        BiomePlacement.addSubOverworld(Biomes.RIVER, BloomingNatureBiomeKeys.COLD_RIVER, coldBiomes);
    }

    private static void registerColdGrasslandPlacement() {
        BiomePlacement.replaceOverworld(Biomes.TAIGA, BloomingNatureBiomeKeys.COLD_GRASSLAND, 0.30D);
        BiomePlacement.replaceOverworld(Biomes.SNOWY_TAIGA, BloomingNatureBiomeKeys.COLD_GRASSLAND, 0.30D);
        BiomePlacement.replaceOverworld(Biomes.OLD_GROWTH_SPRUCE_TAIGA, BloomingNatureBiomeKeys.COLD_GRASSLAND, 0.10D);
        BiomePlacement.replaceOverworld(Biomes.OLD_GROWTH_PINE_TAIGA, BloomingNatureBiomeKeys.COLD_GRASSLAND, 0.10D);
    }

    private static void registerLarchForestPlacement() {
        BiomePlacement.replaceOverworld(Biomes.TAIGA, BloomingNatureBiomeKeys.LARCH_FOREST, 0.10D);
        BiomePlacement.replaceOverworld(Biomes.SNOWY_TAIGA, BloomingNatureBiomeKeys.LARCH_FOREST, 0.40D);
        BiomePlacement.replaceOverworld(Biomes.OLD_GROWTH_SPRUCE_TAIGA, BloomingNatureBiomeKeys.LARCH_FOREST, 0.125D);
        BiomePlacement.replaceOverworld(Biomes.OLD_GROWTH_PINE_TAIGA, BloomingNatureBiomeKeys.LARCH_FOREST, 0.125D);
    }
}