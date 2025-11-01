package net.satisfy.bloomingnature.core.world.biome.temperate;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.BiomeParameterTargets;
import com.terraformersmc.biolith.api.biome.sub.CriterionBuilder;
import com.terraformersmc.biolith.api.biome.sub.RatioTargets;
import com.terraformersmc.biolith.api.surface.BiolithSurfaceBuilder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.satisfy.bloomingnature.core.world.biome.BloomingNatureBiomeKeys;


public final class TemperateBiomeRegistry extends BiolithSurfaceBuilder {



    //TODO:
    // * Taiga: Keine Beaches, nur gravel beach!
    // *

    public static void registerBiomePlacement() {
        registerForestEdgePlacement();
        registerFlowerGladePlacement();
        registerOldGrowthBirchPlacement();
        registerGoldenGladePlacement();
    }

    private static void registerForestEdgePlacement() {
        var neighborForest = CriterionBuilder.neighbor(Biomes.FOREST);
        var excludeNeighbors = CriterionBuilder.allOf(
                CriterionBuilder.not(CriterionBuilder.neighbor(BiomeTags.IS_RIVER)),
                CriterionBuilder.not(CriterionBuilder.neighbor(BiomeTags.IS_OCEAN)),
                CriterionBuilder.not(CriterionBuilder.neighbor(Biomes.FLOWER_FOREST))
        );
        var wideEdgeBand = CriterionBuilder.allOf(
                CriterionBuilder.ratioMax(RatioTargets.CENTER, 0.5f),
                excludeNeighbors
        );
        var edgeOnNeighborSide = CriterionBuilder.allOf(neighborForest, wideEdgeBand);

        BiomePlacement.addSubOverworld(Biomes.PLAINS, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnNeighborSide);
        BiomePlacement.addSubOverworld(Biomes.SUNFLOWER_PLAINS, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnNeighborSide);
        BiomePlacement.addSubOverworld(Biomes.MEADOW, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnNeighborSide);
        BiomePlacement.addSubOverworld(Biomes.SAVANNA, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnNeighborSide);
        BiomePlacement.addSubOverworld(Biomes.SAVANNA_PLATEAU, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnNeighborSide);
        BiomePlacement.addSubOverworld(Biomes.WINDSWEPT_SAVANNA, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnNeighborSide);
        BiomePlacement.addSubOverworld(Biomes.DESERT, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnNeighborSide);
        BiomePlacement.addSubOverworld(Biomes.BADLANDS, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnNeighborSide);
        BiomePlacement.addSubOverworld(Biomes.ERODED_BADLANDS, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnNeighborSide);
        BiomePlacement.addSubOverworld(Biomes.WOODED_BADLANDS, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnNeighborSide);
        BiomePlacement.addSubOverworld(Biomes.STONY_SHORE, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnNeighborSide);
        BiomePlacement.addSubOverworld(Biomes.BEACH, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnNeighborSide);
        BiomePlacement.addSubOverworld(Biomes.TAIGA, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnNeighborSide);
        BiomePlacement.addSubOverworld(Biomes.OLD_GROWTH_SPRUCE_TAIGA, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnNeighborSide);
        BiomePlacement.addSubOverworld(Biomes.OLD_GROWTH_PINE_TAIGA, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnNeighborSide);
        BiomePlacement.addSubOverworld(Biomes.SNOWY_TAIGA, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnNeighborSide);
        BiomePlacement.addSubOverworld(Biomes.SNOWY_PLAINS, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnNeighborSide);
        BiomePlacement.addSubOverworld(Biomes.JAGGED_PEAKS, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnNeighborSide);
        BiomePlacement.addSubOverworld(Biomes.FROZEN_PEAKS, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnNeighborSide);
        BiomePlacement.addSubOverworld(Biomes.STONY_PEAKS, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnNeighborSide);
        BiomePlacement.addSubOverworld(Biomes.JUNGLE, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnNeighborSide);
        BiomePlacement.addSubOverworld(Biomes.SPARSE_JUNGLE, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnNeighborSide);
        BiomePlacement.addSubOverworld(Biomes.BAMBOO_JUNGLE, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnNeighborSide);
        BiomePlacement.addSubOverworld(Biomes.SWAMP, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnNeighborSide);
        BiomePlacement.addSubOverworld(Biomes.MANGROVE_SWAMP, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnNeighborSide);
        BiomePlacement.addSubOverworld(Biomes.DARK_FOREST, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnNeighborSide);
    }

    private static void registerFlowerGladePlacement() {
        var cond = CriterionBuilder.allOf(
                CriterionBuilder.deviationMin(BiomeParameterTargets.PEAKS_VALLEYS, 0.06f),
                CriterionBuilder.ratio(RatioTargets.CENTER, 0.24f, 0.30f),
                CriterionBuilder.not(CriterionBuilder.neighbor(Biomes.FLOWER_FOREST))
        );
        BiomePlacement.addSubOverworld(Biomes.FOREST, BloomingNatureBiomeKeys.FLOWER_GLADE, cond);
    }

    private static void registerGoldenGladePlacement() {
        var cond = CriterionBuilder.allOf(
                CriterionBuilder.deviationMin(BiomeParameterTargets.PEAKS_VALLEYS, 0.06f),
                CriterionBuilder.ratio(RatioTargets.CENTER, 0.24f, 0.30f),
                CriterionBuilder.not(CriterionBuilder.neighbor(Biomes.FLOWER_FOREST))
        );
        BiomePlacement.addSubOverworld(Biomes.OLD_GROWTH_BIRCH_FOREST, BloomingNatureBiomeKeys.GOLDEN_GLADE, cond);
    }

    private static void registerOldGrowthBirchPlacement() {
        var cond = CriterionBuilder.allOf(
                CriterionBuilder.ratio(RatioTargets.CENTER, 0.20f, 0.26f),
                CriterionBuilder.deviationMin(BiomeParameterTargets.PEAKS_VALLEYS, 0.04f),
                CriterionBuilder.not(CriterionBuilder.neighbor(Biomes.FOREST)),
                CriterionBuilder.not(CriterionBuilder.neighbor(Biomes.PLAINS))
        );
        BiomePlacement.addSubOverworld(Biomes.BIRCH_FOREST, Biomes.OLD_GROWTH_BIRCH_FOREST, cond);
    }

}