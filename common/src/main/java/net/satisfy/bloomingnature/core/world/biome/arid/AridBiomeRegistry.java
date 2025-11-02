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
    public static void registerBiomePlacement() {
        registerCypressFieldsPlacement();
        registerBrushlandsPlacement();
        registerBaobabSavannaPlacement();
    }

    private static void registerCypressFieldsPlacement() {
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

    private static void registerBaobabSavannaPlacement() {
        var criterion = CriterionBuilder.allOf(
                CriterionBuilder.ratioMax(RatioTargets.CENTER, 0.35f),
                CriterionBuilder.deviationMin(BiomeParameterTargets.PEAKS_VALLEYS, 0.05f),
                CriterionBuilder.not(CriterionBuilder.neighbor(BiomeTags.IS_FOREST)),
                CriterionBuilder.not(CriterionBuilder.neighbor(BiomeTags.IS_JUNGLE)),
                CriterionBuilder.not(CriterionBuilder.neighbor(BiomeTags.IS_TAIGA))
        );

        BiomePlacement.addSubOverworld(Biomes.SAVANNA, BloomingNatureBiomeKeys.BAOBAB_SAVANNA, criterion);
        BiomePlacement.addSubOverworld(Biomes.SAVANNA_PLATEAU, BloomingNatureBiomeKeys.BAOBAB_SAVANNA, criterion);
    }

    private static void registerBrushlandsPlacement() {
        var smallWarmTransition = CriterionBuilder.allOf(
                CriterionBuilder.ratioMax(RatioTargets.CENTER, 0.55f),
                CriterionBuilder.ratioMax(RatioTargets.EDGE, 0.5f),
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

        BiomePlacement.addSubOverworld(Biomes.PLAINS, BloomingNatureBiomeKeys.BRUSHLANDS, smallWarmTransition);
        BiomePlacement.addSubOverworld(Biomes.SUNFLOWER_PLAINS, BloomingNatureBiomeKeys.BRUSHLANDS, smallWarmTransition);
        BiomePlacement.addSubOverworld(Biomes.SAVANNA, BloomingNatureBiomeKeys.BRUSHLANDS, smallWarmTransition);
        BiomePlacement.addSubOverworld(Biomes.SAVANNA_PLATEAU, BloomingNatureBiomeKeys.BRUSHLANDS, smallWarmTransition);
        BiomePlacement.addSubOverworld(Biomes.WINDSWEPT_SAVANNA, BloomingNatureBiomeKeys.BRUSHLANDS, smallWarmTransition);
    }
}