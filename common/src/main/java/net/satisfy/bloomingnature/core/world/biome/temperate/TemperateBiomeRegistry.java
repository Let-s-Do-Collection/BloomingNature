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
        var nextToForest = CriterionBuilder.allOf(
                CriterionBuilder.anyOf(
                        CriterionBuilder.neighbor(Biomes.FOREST),
                        CriterionBuilder.neighbor(Biomes.FLOWER_FOREST)
                ),
                CriterionBuilder.ratio(RatioTargets.EDGE, 0.0f, 0.18f),
                CriterionBuilder.deviationMax(BiomeParameterTargets.PEAKS_VALLEYS, 0.08f),
                CriterionBuilder.not(CriterionBuilder.neighbor(BiomeTags.IS_RIVER)),
                CriterionBuilder.not(CriterionBuilder.neighbor(BiomeTags.IS_OCEAN))
        );

        ConfiguredBiomePlacement.addSubOverworld(Biomes.PLAINS, BloomingNatureBiomeKeys.FOREST_EDGE, nextToForest);
        ConfiguredBiomePlacement.addSubOverworld(Biomes.SUNFLOWER_PLAINS, BloomingNatureBiomeKeys.FOREST_EDGE, nextToForest);
        ConfiguredBiomePlacement.addSubOverworld(Biomes.MEADOW, BloomingNatureBiomeKeys.FOREST_EDGE, nextToForest);
    }

    private static void registerFlowerGladePlacement() {
        var innerBand = CriterionBuilder.ratioMax(RatioTargets.CENTER, 0.08f);
        ConfiguredBiomePlacement.addSubOverworld(Biomes.FOREST, BloomingNatureBiomeKeys.FLOWER_GLADE, innerBand);
    }

    private static void registerGoldenGladePlacement() {
        var innerBand = CriterionBuilder.ratioMax(RatioTargets.CENTER, 0.08f);
        ConfiguredBiomePlacement.addSubOverworld(Biomes.OLD_GROWTH_BIRCH_FOREST, BloomingNatureBiomeKeys.GOLDEN_GLADE, innerBand);
        ConfiguredBiomePlacement.addSubOverworld(Biomes.BIRCH_FOREST, BloomingNatureBiomeKeys.GOLDEN_GLADE, innerBand);
    }

    private static void registerOldGrowthBirchPlacement() {
        var neighborBirch = CriterionBuilder.neighbor(Biomes.BIRCH_FOREST);
        var cond = CriterionBuilder.allOf(
                neighborBirch,
                CriterionBuilder.ratioMax(RatioTargets.CENTER, 0.10f)
        );
        ConfiguredBiomePlacement.addSubOverworld(Biomes.BIRCH_FOREST, Biomes.OLD_GROWTH_BIRCH_FOREST, cond);
    }

    private static void registerAspenForestPlacement() {
        ConfiguredBiomePlacement.replaceOverworld(Biomes.BIRCH_FOREST, BloomingNatureBiomeKeys.ASPEN_FOREST, 0.35D);
        ConfiguredBiomePlacement.replaceOverworld(Biomes.OLD_GROWTH_BIRCH_FOREST, BloomingNatureBiomeKeys.ASPEN_FOREST, 0.40D);
    }
}