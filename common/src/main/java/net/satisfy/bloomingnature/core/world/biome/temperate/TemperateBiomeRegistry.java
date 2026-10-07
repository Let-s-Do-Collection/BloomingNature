package net.satisfy.bloomingnature.core.world.biome.temperate;

import net.satisfy.bloomingnature.core.world.biome.ConfiguredBiomePlacement;
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
        registerAspenForestPlacement();
    }

    private static void registerForestEdgePlacement() {
        var neighborAnyForest = CriterionBuilder.anyOf(
                CriterionBuilder.neighbor(Biomes.FOREST),
                CriterionBuilder.neighbor(Biomes.FLOWER_FOREST),
                CriterionBuilder.neighbor(Biomes.DARK_FOREST),
                CriterionBuilder.neighbor(Biomes.BIRCH_FOREST),
                CriterionBuilder.neighbor(Biomes.OLD_GROWTH_BIRCH_FOREST),
                CriterionBuilder.neighbor(Biomes.OLD_GROWTH_SPRUCE_TAIGA),
                CriterionBuilder.neighbor(Biomes.TAIGA)
        );

        var excludeWaterAndFlowerForest = CriterionBuilder.allOf(
                CriterionBuilder.not(CriterionBuilder.neighbor(BiomeTags.IS_RIVER)),
                CriterionBuilder.not(CriterionBuilder.neighbor(BiomeTags.IS_OCEAN)),
                CriterionBuilder.not(CriterionBuilder.neighbor(Biomes.FLOWER_FOREST))
        );

        var thinEdgeBand = CriterionBuilder.allOf(
                CriterionBuilder.ratio(RatioTargets.EDGE, 0.0f, 0.16f),
                CriterionBuilder.deviationMax(BiomeParameterTargets.PEAKS_VALLEYS, 0.08f),
                excludeWaterAndFlowerForest
        );

        var edgeOnForestSide = CriterionBuilder.allOf(neighborAnyForest, thinEdgeBand);

        ConfiguredBiomePlacement.addSubOverworld(Biomes.PLAINS, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnForestSide);
        ConfiguredBiomePlacement.addSubOverworld(Biomes.SUNFLOWER_PLAINS, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnForestSide);
        ConfiguredBiomePlacement.addSubOverworld(Biomes.MEADOW, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnForestSide);
        ConfiguredBiomePlacement.addSubOverworld(Biomes.BADLANDS, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnForestSide);
        ConfiguredBiomePlacement.addSubOverworld(Biomes.SAVANNA, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnForestSide);
        ConfiguredBiomePlacement.addSubOverworld(Biomes.SAVANNA_PLATEAU, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnForestSide);
        ConfiguredBiomePlacement.addSubOverworld(Biomes.STONY_PEAKS, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnForestSide);
        ConfiguredBiomePlacement.addSubOverworld(Biomes.SNOWY_PLAINS, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnForestSide);
        ConfiguredBiomePlacement.addSubOverworld(BloomingNatureBiomeKeys.CYPRESS_FIELDS, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnForestSide);
        ConfiguredBiomePlacement.addSubOverworld(BloomingNatureBiomeKeys.BAOBAB_SAVANNA, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnForestSide);
        ConfiguredBiomePlacement.addSubOverworld(BloomingNatureBiomeKeys.COLD_GRASSLAND, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnForestSide);
        ConfiguredBiomePlacement.addSubOverworld(BloomingNatureBiomeKeys.BRUSHLANDS, BloomingNatureBiomeKeys.FOREST_EDGE, edgeOnForestSide);
    }

    private static void registerFlowerGladePlacement() {
        var innerBand = CriterionBuilder.allOf(
                CriterionBuilder.deviationMin(BiomeParameterTargets.PEAKS_VALLEYS, 0.06f),
                CriterionBuilder.ratio(RatioTargets.CENTER, 0.24f, 0.30f)
        );
        ConfiguredBiomePlacement.addSubOverworld(Biomes.FOREST, BloomingNatureBiomeKeys.FLOWER_GLADE, innerBand);
        ConfiguredBiomePlacement.addSubOverworld(Biomes.FLOWER_FOREST, BloomingNatureBiomeKeys.FLOWER_GLADE, innerBand);
    }

    private static void registerGoldenGladePlacement() {
        var innerBand = CriterionBuilder.allOf(
                CriterionBuilder.deviationMin(BiomeParameterTargets.PEAKS_VALLEYS, 0.06f),
                CriterionBuilder.ratio(RatioTargets.CENTER, 0.24f, 0.30f)
        );
        ConfiguredBiomePlacement.addSubOverworld(Biomes.OLD_GROWTH_BIRCH_FOREST, BloomingNatureBiomeKeys.GOLDEN_GLADE, innerBand);
        ConfiguredBiomePlacement.addSubOverworld(Biomes.BIRCH_FOREST, BloomingNatureBiomeKeys.GOLDEN_GLADE, innerBand);
    }

    private static void registerOldGrowthBirchPlacement() {
        var neighborBirch = CriterionBuilder.neighbor(Biomes.BIRCH_FOREST);
        var cond = CriterionBuilder.allOf(
                neighborBirch,
                CriterionBuilder.ratio(RatioTargets.CENTER, 0.20f, 0.26f),
                CriterionBuilder.deviationMin(BiomeParameterTargets.PEAKS_VALLEYS, 0.04f)
        );
        ConfiguredBiomePlacement.addSubOverworld(Biomes.BIRCH_FOREST, Biomes.OLD_GROWTH_BIRCH_FOREST, cond);
    }

    private static void registerAspenForestPlacement() {
        ConfiguredBiomePlacement.replaceOverworld(Biomes.BIRCH_FOREST, BloomingNatureBiomeKeys.ASPEN_FOREST, 0.55D);
        ConfiguredBiomePlacement.replaceOverworld(Biomes.OLD_GROWTH_BIRCH_FOREST, BloomingNatureBiomeKeys.ASPEN_FOREST, 0.85D);
    }
}