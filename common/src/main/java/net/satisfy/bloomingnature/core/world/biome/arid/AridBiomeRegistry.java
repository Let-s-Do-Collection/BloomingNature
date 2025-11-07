package net.satisfy.bloomingnature.core.world.biome.arid;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.BiomeParameterTargets;
import com.terraformersmc.biolith.api.biome.sub.CriterionBuilder;
import com.terraformersmc.biolith.api.biome.sub.RatioTargets;
import com.terraformersmc.biolith.api.surface.BiolithSurfaceBuilder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.satisfy.bloomingnature.core.world.biome.BloomingNatureBiomeKeys;

import static com.terraformersmc.biolith.api.biome.sub.CriterionBuilder.*;

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
        registerBrushlandEdgePlacement();
    }

    private static void refineAridReplacements() {
        var flatTight = CriterionBuilder.value(BiomeParameterTargets.DEPTH, -0.04f, 0.04f);
        var centerWindow = CriterionBuilder.ratio(RatioTargets.CENTER, 0.68f, 0.80f);
        var awayFromEdges = CriterionBuilder.ratio(RatioTargets.EDGE, 0.0f, 0.18f);
        var awayFromCoast = CriterionBuilder.allOf(CriterionBuilder.not(BEACHSIDE), CriterionBuilder.not(OCEANSIDE));
        var awayFromRivers = CriterionBuilder.not(CriterionBuilder.neighbor(BiomeTags.IS_RIVER));
        var awayFromBadlands = CriterionBuilder.allOf(CriterionBuilder.not(CriterionBuilder.neighbor(Biomes.BADLANDS)), CriterionBuilder.not(CriterionBuilder.neighbor(Biomes.WOODED_BADLANDS)), CriterionBuilder.not(CriterionBuilder.neighbor(Biomes.ERODED_BADLANDS))
        );

        BiomePlacement.addSubOverworld(BloomingNatureBiomeKeys.DESERT_OASIS, Biomes.DESERT, CriterionBuilder.allOf(flatTight, centerWindow, awayFromEdges, awayFromCoast, awayFromRivers, awayFromBadlands)
        );
    }

    private static void registerDesertRiverPlacement() {
        var nearArid = anyOf(neighbor(Biomes.DESERT), neighbor(Biomes.BADLANDS), neighbor(Biomes.ERODED_BADLANDS), neighbor(Biomes.WOODED_BADLANDS));

        var warm = value(BiomeParameterTargets.TEMPERATURE, 0.45f, 2.0f);
        var shallow = value(BiomeParameterTargets.DEPTH, -0.35f, 0.25f);
        var cond = allOf(nearArid, warm, shallow);

        BiomePlacement.addSubOverworld(Biomes.RIVER, BloomingNatureBiomeKeys.DESERT_RIVER, cond);
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

    private static void registerBrushlandEdgePlacement() {
        var nearArid = anyOf(
                neighbor(Biomes.DESERT),
                neighbor(Biomes.BADLANDS),
                neighbor(Biomes.ERODED_BADLANDS),
                neighbor(Biomes.WOODED_BADLANDS)
        );
        var nearTemperate = anyOf(
                neighbor(Biomes.PLAINS),
                neighbor(Biomes.SUNFLOWER_PLAINS),
                neighbor(Biomes.FOREST),
                neighbor(Biomes.DARK_FOREST),
                neighbor(Biomes.BIRCH_FOREST),
                neighbor(Biomes.OLD_GROWTH_BIRCH_FOREST)
        );
        var edge = ratio(RatioTargets.CENTER, 0.18f, 0.28f);
        var excludeWater = allOf(
                not(neighbor(BiomeTags.IS_RIVER)),
                not(neighbor(BiomeTags.IS_OCEAN)),
                not(neighbor(Biomes.BEACH)),
                not(neighbor(Biomes.STONY_SHORE))
        );
        var cond = allOf(edge, excludeWater, anyOf(nearArid, nearTemperate));

        BiomePlacement.addSubOverworld(Biomes.PLAINS, BloomingNatureBiomeKeys.BRUSHLANDS, cond);
        BiomePlacement.addSubOverworld(Biomes.SUNFLOWER_PLAINS, BloomingNatureBiomeKeys.BRUSHLANDS, cond);
        BiomePlacement.addSubOverworld(Biomes.FOREST, BloomingNatureBiomeKeys.BRUSHLANDS, cond);
        BiomePlacement.addSubOverworld(Biomes.BIRCH_FOREST, BloomingNatureBiomeKeys.BRUSHLANDS, cond);
        BiomePlacement.addSubOverworld(Biomes.DARK_FOREST, BloomingNatureBiomeKeys.BRUSHLANDS, cond);
        BiomePlacement.addSubOverworld(Biomes.MEADOW, BloomingNatureBiomeKeys.BRUSHLANDS, cond);
        BiomePlacement.addSubOverworld(Biomes.SAVANNA, BloomingNatureBiomeKeys.BRUSHLANDS, cond);
        BiomePlacement.addSubOverworld(Biomes.SAVANNA_PLATEAU, BloomingNatureBiomeKeys.BRUSHLANDS, cond);
    }
}