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
    public static void registerBiomePlacement() {
        registerForestEdgePlacement();
        registerFlowerGladePlacement();
        registerGoldenGladePlacement();
        registerOldGrowthBirchPlacement();
    }

    private static void registerForestEdgePlacement() {
        var neighborAnyForest = CriterionBuilder.neighbor(BiomeTags.IS_FOREST);

        var excludeWaterAndFlowerForest = CriterionBuilder.allOf(
                CriterionBuilder.not(CriterionBuilder.neighbor(BiomeTags.IS_RIVER)),
                CriterionBuilder.not(CriterionBuilder.neighbor(BiomeTags.IS_OCEAN)),
                CriterionBuilder.not(CriterionBuilder.neighbor(Biomes.FLOWER_FOREST))
        );

        var thinEdgeBand = CriterionBuilder.allOf(
                CriterionBuilder.ratio(RatioTargets.CENTER, 0.28f, 0.52f),
                CriterionBuilder.deviationMax(BiomeParameterTargets.PEAKS_VALLEYS, 0.12f),
                excludeWaterAndFlowerForest
        );

        var edgeOnForestSide = CriterionBuilder.allOf(neighborAnyForest, thinEdgeBand);

        BiomePlacement.addSubOverworld(Biomes.PLAINS, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnForestSide);
        BiomePlacement.addSubOverworld(Biomes.SUNFLOWER_PLAINS, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnForestSide);
        BiomePlacement.addSubOverworld(Biomes.MEADOW, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnForestSide);
        BiomePlacement.addSubOverworld(Biomes.BEACH, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnForestSide);
        BiomePlacement.addSubOverworld(Biomes.STONY_SHORE, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnForestSide);
    }

    private static void registerFlowerGladePlacement() {
        var innerBand = CriterionBuilder.allOf(
                CriterionBuilder.deviationMin(BiomeParameterTargets.PEAKS_VALLEYS, 0.06f),
                CriterionBuilder.ratio(RatioTargets.CENTER, 0.24f, 0.30f)
        );
        BiomePlacement.addSubOverworld(Biomes.FOREST, BloomingNatureBiomeKeys.FLOWER_GLADE, innerBand);
        BiomePlacement.addSubOverworld(Biomes.FLOWER_FOREST, BloomingNatureBiomeKeys.FLOWER_GLADE, innerBand);
    }

    private static void registerGoldenGladePlacement() {
        var innerBand = CriterionBuilder.allOf(
                CriterionBuilder.deviationMin(BiomeParameterTargets.PEAKS_VALLEYS, 0.06f),
                CriterionBuilder.ratio(RatioTargets.CENTER, 0.24f, 0.30f)
        );
        BiomePlacement.addSubOverworld(Biomes.OLD_GROWTH_BIRCH_FOREST, BloomingNatureBiomeKeys.GOLDEN_GLADE, innerBand);
        BiomePlacement.addSubOverworld(Biomes.BIRCH_FOREST, BloomingNatureBiomeKeys.GOLDEN_GLADE, innerBand);
    }

    private static void registerOldGrowthBirchPlacement() {
        var neighborBirch = CriterionBuilder.neighbor(Biomes.BIRCH_FOREST);
        var cond = CriterionBuilder.allOf(
                neighborBirch,
                CriterionBuilder.ratio(RatioTargets.CENTER, 0.20f, 0.26f),
                CriterionBuilder.deviationMin(BiomeParameterTargets.PEAKS_VALLEYS, 0.04f)
        );
        BiomePlacement.addSubOverworld(Biomes.BIRCH_FOREST, Biomes.OLD_GROWTH_BIRCH_FOREST, cond);
    }
}