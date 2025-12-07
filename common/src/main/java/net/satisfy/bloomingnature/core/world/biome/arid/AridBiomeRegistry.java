package net.satisfy.bloomingnature.core.world.biome.arid;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.BiomeParameterTargets;
import com.terraformersmc.biolith.api.biome.sub.CriterionBuilder;
import com.terraformersmc.biolith.api.biome.sub.RatioTargets;
import com.terraformersmc.biolith.api.surface.BiolithSurfaceBuilder;
import net.minecraft.world.level.biome.Biomes;
import net.satisfy.bloomingnature.core.world.biome.BloomingNatureBiomeKeys;

public final class AridBiomeRegistry extends BiolithSurfaceBuilder {

    public static void registerBiomePlacement() {
        BiomePlacement.replaceOverworld(Biomes.PLAINS, BloomingNatureBiomeKeys.CYPRESS_FIELDS, 0.28D);
        BiomePlacement.replaceOverworld(Biomes.SUNFLOWER_PLAINS, BloomingNatureBiomeKeys.CYPRESS_FIELDS, 0.24D);

        BiomePlacement.replaceOverworld(Biomes.SAVANNA, BloomingNatureBiomeKeys.BRUSHLANDS, 0.16D);
        BiomePlacement.replaceOverworld(Biomes.SAVANNA_PLATEAU, BloomingNatureBiomeKeys.BRUSHLANDS, 0.22D);
        BiomePlacement.replaceOverworld(Biomes.DESERT, BloomingNatureBiomeKeys.BRUSHLANDS, 0.22D);
        BiomePlacement.replaceOverworld(Biomes.BADLANDS, BloomingNatureBiomeKeys.BRUSHLANDS, 0.22D);

        BiomePlacement.replaceOverworld(Biomes.SAVANNA, BloomingNatureBiomeKeys.BAOBAB_SAVANNA, 0.55D);
        BiomePlacement.replaceOverworld(Biomes.SAVANNA_PLATEAU, BloomingNatureBiomeKeys.BAOBAB_SAVANNA, 0.65D);

        registerBaobabSavannaPlacement();
        registerDesertRiverPlacement();
        registerOasisPlacement();
    }

    private static void registerBaobabSavannaPlacement() {
        BiomePlacement.replaceOverworld(Biomes.SAVANNA, BloomingNatureBiomeKeys.BAOBAB_SAVANNA, 0.40D);
        BiomePlacement.replaceOverworld(Biomes.SAVANNA_PLATEAU, BloomingNatureBiomeKeys.BAOBAB_SAVANNA, 0.40D);
        BiomePlacement.replaceOverworld(Biomes.WINDSWEPT_SAVANNA, BloomingNatureBiomeKeys.BAOBAB_SAVANNA, 0.40D);
    }

    private static void registerDesertRiverPlacement() {
        var nearArid = CriterionBuilder.anyOf(
                CriterionBuilder.neighbor(Biomes.DESERT),
                CriterionBuilder.neighbor(Biomes.BADLANDS),
                CriterionBuilder.neighbor(Biomes.ERODED_BADLANDS),
                CriterionBuilder.neighbor(Biomes.WOODED_BADLANDS)
        );
        var warm = CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, 0.45f, 2.0f);
        var shallow = CriterionBuilder.value(BiomeParameterTargets.DEPTH, -0.35f, 0.25f);

        BiomePlacement.addSubOverworld(Biomes.RIVER, BloomingNatureBiomeKeys.DESERT_RIVER, CriterionBuilder.allOf(nearArid, warm, shallow));
    }

    private static void registerOasisPlacement() {
        var center = CriterionBuilder.ratio(RatioTargets.CENTER, 0.45f, 0.55f);
        var depth = CriterionBuilder.value(BiomeParameterTargets.DEPTH, -0.20f, 0.10f);
        var temp = CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, 0.9f, 2.0f);

        var cond = CriterionBuilder.allOf(center, depth, temp);

        BiomePlacement.addSubOverworld(Biomes.DESERT, BloomingNatureBiomeKeys.DESERT_OASIS, cond);
    }
}