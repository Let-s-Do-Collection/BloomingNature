package net.satisfy.bloomingnature.core.world.biome.arid;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.BiomeParameterTargets;
import com.terraformersmc.biolith.api.biome.sub.RatioTargets;
import com.terraformersmc.biolith.api.surface.BiolithSurfaceBuilder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.satisfy.bloomingnature.core.world.biome.BloomingNatureBiomeKeys;

import static com.terraformersmc.biolith.api.biome.sub.CriterionBuilder.allOf;
import static com.terraformersmc.biolith.api.biome.sub.CriterionBuilder.anyOf;
import static com.terraformersmc.biolith.api.biome.sub.CriterionBuilder.deviationMin;
import static com.terraformersmc.biolith.api.biome.sub.CriterionBuilder.neighbor;
import static com.terraformersmc.biolith.api.biome.sub.CriterionBuilder.not;
import static com.terraformersmc.biolith.api.biome.sub.CriterionBuilder.ratio;
import static com.terraformersmc.biolith.api.biome.sub.CriterionBuilder.value;
import static com.terraformersmc.biolith.api.biome.sub.CriterionBuilder.BEACHSIDE;
import static com.terraformersmc.biolith.api.biome.sub.CriterionBuilder.OCEANSIDE;

public final class AridBiomeRegistry extends BiolithSurfaceBuilder {

    public static void registerBiomePlacement() {
        BiomePlacement.replaceOverworld(Biomes.PLAINS, BloomingNatureBiomeKeys.CYPRESS_FIELDS, 0.28D);
        BiomePlacement.replaceOverworld(Biomes.SUNFLOWER_PLAINS, BloomingNatureBiomeKeys.CYPRESS_FIELDS, 0.24D);
        BiomePlacement.replaceOverworld(Biomes.SAVANNA, BloomingNatureBiomeKeys.CYPRESS_FIELDS, 0.12D);
        BiomePlacement.replaceOverworld(Biomes.SAVANNA, BloomingNatureBiomeKeys.BRUSHLANDS, 0.30D);
        BiomePlacement.replaceOverworld(Biomes.SAVANNA_PLATEAU, BloomingNatureBiomeKeys.BRUSHLANDS, 0.30D);
        BiomePlacement.replaceOverworld(Biomes.PLAINS, BloomingNatureBiomeKeys.BRUSHLANDS, 0.10D);
        BiomePlacement.replaceOverworld(Biomes.SAVANNA, BloomingNatureBiomeKeys.BAOBAB_SAVANNA, 0.18D);
        BiomePlacement.replaceOverworld(Biomes.SAVANNA_PLATEAU, BloomingNatureBiomeKeys.BAOBAB_SAVANNA, 0.22D);
        BiomePlacement.replaceOverworld(Biomes.DESERT, BloomingNatureBiomeKeys.DESERT_OASIS, 0.0725D);
        refineAridReplacements();
        registerDesertRiverPlacement();
        registerOasisPlacement();
        registerBrushlandDividerPlacement();
    }

    private static void refineAridReplacements() {
        var flatTight = value(BiomeParameterTargets.DEPTH, -0.04f, 0.04f);
        var centerWindow = ratio(RatioTargets.CENTER, 0.68f, 0.80f);
        var awayFromEdges = ratio(RatioTargets.EDGE, 0.0f, 0.18f);
        var awayFromCoast = allOf(not(BEACHSIDE), not(OCEANSIDE));
        var awayFromRivers = not(neighbor(BiomeTags.IS_RIVER));
        var awayFromBadlands = allOf(not(neighbor(Biomes.BADLANDS)), not(neighbor(Biomes.WOODED_BADLANDS)), not(neighbor(Biomes.ERODED_BADLANDS)));
        BiomePlacement.addSubOverworld(BloomingNatureBiomeKeys.DESERT_OASIS, Biomes.DESERT, allOf(flatTight, centerWindow, awayFromEdges, awayFromCoast, awayFromRivers, awayFromBadlands));
    }

    private static void registerDesertRiverPlacement() {
        var nearArid = anyOf(neighbor(Biomes.DESERT), neighbor(Biomes.BADLANDS), neighbor(Biomes.ERODED_BADLANDS), neighbor(Biomes.WOODED_BADLANDS));
        var warm = value(BiomeParameterTargets.TEMPERATURE, 0.45f, 2.0f);
        var shallow = value(BiomeParameterTargets.DEPTH, -0.35f, 0.25f);
        BiomePlacement.addSubOverworld(Biomes.RIVER, BloomingNatureBiomeKeys.DESERT_RIVER, allOf(nearArid, warm, shallow));
    }

    private static void registerOasisPlacement() {
        var nearWater = anyOf(BEACHSIDE, neighbor(BiomeTags.IS_RIVER));
        var center = ratio(RatioTargets.CENTER, 0.34f, 0.52f);
        var shallow = value(BiomeParameterTargets.DEPTH, -0.12f, 0.08f);
        var relief = deviationMin(BiomeParameterTargets.PEAKS_VALLEYS, 0.015f);
        var hot = value(BiomeParameterTargets.TEMPERATURE, 0.8f, 2.0f);
        var cond = allOf(nearWater, center, shallow, relief, hot);
        BiomePlacement.addSubOverworld(Biomes.DESERT, BloomingNatureBiomeKeys.DESERT_OASIS, cond);
        BiomePlacement.addSubOverworld(Biomes.BADLANDS, BloomingNatureBiomeKeys.DESERT_OASIS, cond);
    }

    private static void registerBrushlandDividerPlacement() {
        var arid = anyOf(neighbor(Biomes.DESERT), neighbor(Biomes.BADLANDS), neighbor(Biomes.ERODED_BADLANDS), neighbor(Biomes.WOODED_BADLANDS));
        var temperate = anyOf(neighbor(Biomes.PLAINS), neighbor(Biomes.SUNFLOWER_PLAINS), neighbor(Biomes.FOREST), neighbor(Biomes.BIRCH_FOREST), neighbor(Biomes.OLD_GROWTH_BIRCH_FOREST), neighbor(Biomes.TAIGA), neighbor(Biomes.OLD_GROWTH_SPRUCE_TAIGA), neighbor(Biomes.OLD_GROWTH_PINE_TAIGA), neighbor(Biomes.DARK_FOREST), neighbor(Biomes.MEADOW), neighbor(Biomes.SAVANNA), neighbor(Biomes.SAVANNA_PLATEAU));
        var outerEdge = ratio(RatioTargets.EDGE, 0.6f, 1.0f);
        var excludeWater = allOf(not(neighbor(BiomeTags.IS_RIVER)), not(neighbor(BiomeTags.IS_OCEAN)), not(neighbor(Biomes.BEACH)), not(neighbor(Biomes.STONY_SHORE)));
        var condInTemperate = allOf(outerEdge, excludeWater, arid);
        var condInArid = allOf(outerEdge, excludeWater, temperate);
        BiomePlacement.addSubOverworld(Biomes.PLAINS, BloomingNatureBiomeKeys.BRUSHLANDS, condInTemperate);
        BiomePlacement.addSubOverworld(Biomes.SUNFLOWER_PLAINS, BloomingNatureBiomeKeys.BRUSHLANDS, condInTemperate);
        BiomePlacement.addSubOverworld(Biomes.FOREST, BloomingNatureBiomeKeys.BRUSHLANDS, condInTemperate);
        BiomePlacement.addSubOverworld(Biomes.BIRCH_FOREST, BloomingNatureBiomeKeys.BRUSHLANDS, condInTemperate);
        BiomePlacement.addSubOverworld(Biomes.OLD_GROWTH_BIRCH_FOREST, BloomingNatureBiomeKeys.BRUSHLANDS, condInTemperate);
        BiomePlacement.addSubOverworld(Biomes.TAIGA, BloomingNatureBiomeKeys.BRUSHLANDS, condInTemperate);
        BiomePlacement.addSubOverworld(Biomes.OLD_GROWTH_SPRUCE_TAIGA, BloomingNatureBiomeKeys.BRUSHLANDS, condInTemperate);
        BiomePlacement.addSubOverworld(Biomes.OLD_GROWTH_PINE_TAIGA, BloomingNatureBiomeKeys.BRUSHLANDS, condInTemperate);
        BiomePlacement.addSubOverworld(Biomes.DARK_FOREST, BloomingNatureBiomeKeys.BRUSHLANDS, condInTemperate);
        BiomePlacement.addSubOverworld(Biomes.MEADOW, BloomingNatureBiomeKeys.BRUSHLANDS, condInTemperate);
        BiomePlacement.addSubOverworld(Biomes.SAVANNA, BloomingNatureBiomeKeys.BRUSHLANDS, condInTemperate);
        BiomePlacement.addSubOverworld(Biomes.SAVANNA_PLATEAU, BloomingNatureBiomeKeys.BRUSHLANDS, condInTemperate);
        BiomePlacement.addSubOverworld(Biomes.DESERT, BloomingNatureBiomeKeys.BRUSHLANDS, condInArid);
        BiomePlacement.addSubOverworld(Biomes.BADLANDS, BloomingNatureBiomeKeys.BRUSHLANDS, condInArid);
        BiomePlacement.addSubOverworld(Biomes.ERODED_BADLANDS, BloomingNatureBiomeKeys.BRUSHLANDS, condInArid);
        BiomePlacement.addSubOverworld(Biomes.WOODED_BADLANDS, BloomingNatureBiomeKeys.BRUSHLANDS, condInArid);
    }
}