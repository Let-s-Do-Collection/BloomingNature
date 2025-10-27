package net.satisfy.bloomingnature.core.world.biome.arid;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.BiomeParameterTargets;
import com.terraformersmc.biolith.api.biome.sub.CriterionBuilder;
import com.terraformersmc.biolith.api.biome.sub.RatioTargets;
import com.terraformersmc.biolith.api.surface.BiolithSurfaceBuilder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.satisfy.bloomingnature.core.world.biome.BloomingNatureBiomeKeys;


public final class AridBiomeRegistry extends BiolithSurfaceBuilder {



    //TODO:
    // * Birch Forest
    // * Flower Forest
    // * Old Growth Birch  Forest -> Remove
    // * Taiga: Keine Beaches, nur gravel beach!
    // *

    public static void registerBiomePlacement() {
        registerDryPlainsPlacement();
        registerLavenderFieldsPlacement();
    }

    private static void registerDryPlainsPlacement() {
        var neighborSavanna = CriterionBuilder.anyOf(
                CriterionBuilder.neighbor(Biomes.SAVANNA),
                CriterionBuilder.neighbor(Biomes.SAVANNA_PLATEAU),
                CriterionBuilder.neighbor(Biomes.WINDSWEPT_SAVANNA)
        );

        var neighborArid = CriterionBuilder.anyOf(
                CriterionBuilder.neighbor(Biomes.DESERT),
                CriterionBuilder.neighbor(Biomes.BADLANDS),
                CriterionBuilder.neighbor(Biomes.ERODED_BADLANDS)
        );

        var excludeNeighbors = CriterionBuilder.allOf(
                CriterionBuilder.not(CriterionBuilder.neighbor(BiomeTags.IS_RIVER)),
                CriterionBuilder.not(CriterionBuilder.neighbor(BiomeTags.IS_OCEAN)),
                CriterionBuilder.not(CriterionBuilder.neighbor(Biomes.FLOWER_FOREST))
        );

        var wideEdgeBand = CriterionBuilder.allOf(
                CriterionBuilder.ratioMax(RatioTargets.CENTER, 0.85f),
                CriterionBuilder.deviationMax(BiomeParameterTargets.PEAKS_VALLEYS, 0.15f),
                excludeNeighbors
        );

        var broadEdge = CriterionBuilder.allOf(
                CriterionBuilder.anyOf(neighborSavanna, neighborArid),
                wideEdgeBand
        );

        BiomePlacement.addSubOverworld(Biomes.PLAINS, BloomingNatureBiomeKeys.DRY_PLAINS, broadEdge);
        BiomePlacement.addSubOverworld(Biomes.SUNFLOWER_PLAINS, BloomingNatureBiomeKeys.DRY_PLAINS, broadEdge);
        BiomePlacement.addSubOverworld(Biomes.MEADOW, BloomingNatureBiomeKeys.DRY_PLAINS, broadEdge);
        BiomePlacement.addSubOverworld(Biomes.FOREST, BloomingNatureBiomeKeys.DRY_PLAINS, broadEdge);
        BiomePlacement.addSubOverworld(Biomes.BIRCH_FOREST, BloomingNatureBiomeKeys.DRY_PLAINS, broadEdge);
        BiomePlacement.addSubOverworld(Biomes.OLD_GROWTH_BIRCH_FOREST, BloomingNatureBiomeKeys.DRY_PLAINS, broadEdge);
        BiomePlacement.addSubOverworld(Biomes.DARK_FOREST, BloomingNatureBiomeKeys.DRY_PLAINS, broadEdge);
        BiomePlacement.addSubOverworld(Biomes.BEACH, BloomingNatureBiomeKeys.DRY_PLAINS, broadEdge);
        BiomePlacement.addSubOverworld(Biomes.STONY_SHORE, BloomingNatureBiomeKeys.DRY_PLAINS, broadEdge);
    }

    private static void registerLavenderFieldsPlacement() {
        var warmTransition = CriterionBuilder.allOf(
                CriterionBuilder.ratioMax(RatioTargets.CENTER, 0.8f),
                CriterionBuilder.anyOf(
                        CriterionBuilder.neighbor(Biomes.SAVANNA),
                        CriterionBuilder.neighbor(Biomes.SAVANNA_PLATEAU),
                        CriterionBuilder.neighbor(Biomes.SUNFLOWER_PLAINS),
                        CriterionBuilder.neighbor(Biomes.PLAINS)
                ),
                CriterionBuilder.not(CriterionBuilder.neighbor(BiomeTags.IS_TAIGA)),
                CriterionBuilder.not(CriterionBuilder.neighbor(BiomeTags.IS_JUNGLE)),
                CriterionBuilder.not(CriterionBuilder.neighbor(BiomeTags.IS_FOREST))
        );

        BiomePlacement.addSubOverworld(Biomes.PLAINS, BloomingNatureBiomeKeys.CYPRESS_FIELDS, warmTransition);
        BiomePlacement.addSubOverworld(Biomes.SUNFLOWER_PLAINS, BloomingNatureBiomeKeys.CYPRESS_FIELDS, warmTransition);
        BiomePlacement.addSubOverworld(Biomes.SAVANNA, BloomingNatureBiomeKeys.CYPRESS_FIELDS, warmTransition);
        BiomePlacement.addSubOverworld(Biomes.SAVANNA_PLATEAU, BloomingNatureBiomeKeys.CYPRESS_FIELDS, warmTransition);
        BiomePlacement.addSubOverworld(Biomes.WINDSWEPT_SAVANNA, BloomingNatureBiomeKeys.CYPRESS_FIELDS, warmTransition);
    }
}