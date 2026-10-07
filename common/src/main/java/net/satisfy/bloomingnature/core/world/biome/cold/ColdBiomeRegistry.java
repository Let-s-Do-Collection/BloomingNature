package net.satisfy.bloomingnature.core.world.biome.cold;

import net.satisfy.bloomingnature.core.world.biome.ConfiguredBiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.CriterionBuilder;
import com.terraformersmc.biolith.api.biome.sub.RatioTargets;
import com.terraformersmc.biolith.api.surface.BiolithSurfaceBuilder;
import net.minecraft.world.level.biome.Biomes;
import net.satisfy.bloomingnature.core.world.biome.BloomingNatureBiomeKeys;


public final class ColdBiomeRegistry extends BiolithSurfaceBuilder {
    public static void registerBiomePlacement() {
        registerColdRiverPlacement();
        registerColdGrasslandPlacement();
        registerLarchForestPlacement();
        registerFenPlacement();
        registerHighlandWoodsPlacement();
    }

    private static void registerColdRiverPlacement() {
        var coldBiomes = CriterionBuilder.anyOf(
                CriterionBuilder.neighbor(Biomes.SNOWY_TAIGA),
                CriterionBuilder.neighbor(Biomes.TAIGA),
                CriterionBuilder.neighbor(Biomes.OLD_GROWTH_SPRUCE_TAIGA),
                CriterionBuilder.neighbor(Biomes.OLD_GROWTH_PINE_TAIGA),
                CriterionBuilder.neighbor(BloomingNatureBiomeKeys.LARCH_FOREST),
                CriterionBuilder.neighbor(BloomingNatureBiomeKeys.COLD_GRASSLAND)
        );

        ConfiguredBiomePlacement.addSubOverworld(Biomes.RIVER, BloomingNatureBiomeKeys.COLD_RIVER, coldBiomes);
    }

    private static void registerColdGrasslandPlacement() {
        ConfiguredBiomePlacement.replaceOverworld(Biomes.TAIGA, BloomingNatureBiomeKeys.COLD_GRASSLAND, 0.30D);
        ConfiguredBiomePlacement.replaceOverworld(Biomes.SNOWY_TAIGA, BloomingNatureBiomeKeys.COLD_GRASSLAND, 0.30D);
        ConfiguredBiomePlacement.replaceOverworld(Biomes.OLD_GROWTH_SPRUCE_TAIGA, BloomingNatureBiomeKeys.COLD_GRASSLAND, 0.10D);
        ConfiguredBiomePlacement.replaceOverworld(Biomes.OLD_GROWTH_PINE_TAIGA, BloomingNatureBiomeKeys.COLD_GRASSLAND, 0.10D);
    }

    private static void registerLarchForestPlacement() {
        ConfiguredBiomePlacement.replaceOverworld(Biomes.TAIGA, BloomingNatureBiomeKeys.LARCH_FOREST, 0.10D);
        ConfiguredBiomePlacement.replaceOverworld(Biomes.SNOWY_TAIGA, BloomingNatureBiomeKeys.LARCH_FOREST, 0.40D);
        ConfiguredBiomePlacement.replaceOverworld(Biomes.OLD_GROWTH_SPRUCE_TAIGA, BloomingNatureBiomeKeys.LARCH_FOREST, 0.125D);
        ConfiguredBiomePlacement.replaceOverworld(Biomes.OLD_GROWTH_PINE_TAIGA, BloomingNatureBiomeKeys.LARCH_FOREST, 0.125D);
    }

    private static void registerFenPlacement() {
        var coldNeighbors = CriterionBuilder.anyOf(
                CriterionBuilder.neighbor(Biomes.TAIGA),
                CriterionBuilder.neighbor(Biomes.SNOWY_TAIGA),
                CriterionBuilder.neighbor(Biomes.OLD_GROWTH_SPRUCE_TAIGA),
                CriterionBuilder.neighbor(Biomes.OLD_GROWTH_PINE_TAIGA),
                CriterionBuilder.neighbor(BloomingNatureBiomeKeys.LARCH_FOREST),
                CriterionBuilder.neighbor(BloomingNatureBiomeKeys.COLD_GRASSLAND)
        );

        ConfiguredBiomePlacement.addSubOverworld(Biomes.RIVER, BloomingNatureBiomeKeys.FEN, coldNeighbors);
        ConfiguredBiomePlacement.addSubOverworld(Biomes.FROZEN_RIVER, BloomingNatureBiomeKeys.FEN, coldNeighbors);
        ConfiguredBiomePlacement.addSubOverworld(BloomingNatureBiomeKeys.COLD_RIVER, BloomingNatureBiomeKeys.FEN, coldNeighbors);

        var riverTouch = CriterionBuilder.anyOf(
                CriterionBuilder.neighbor(Biomes.RIVER),
                CriterionBuilder.neighbor(Biomes.FROZEN_RIVER),
                CriterionBuilder.neighbor(BloomingNatureBiomeKeys.COLD_RIVER)
        );

        var shoreZone = CriterionBuilder.ratio(RatioTargets.EDGE, 0.0f, 0.45f);
        var inlandZone = CriterionBuilder.ratio(RatioTargets.EDGE, 0.45f, 1.0f);

        var shoreCond = CriterionBuilder.allOf(riverTouch, shoreZone);
        var inlandCond = CriterionBuilder.allOf(riverTouch, inlandZone);

        ConfiguredBiomePlacement.addSubOverworld(Biomes.TAIGA, BloomingNatureBiomeKeys.FEN, shoreCond);
        ConfiguredBiomePlacement.addSubOverworld(Biomes.SNOWY_TAIGA, BloomingNatureBiomeKeys.FEN, shoreCond);
        ConfiguredBiomePlacement.addSubOverworld(Biomes.OLD_GROWTH_SPRUCE_TAIGA, BloomingNatureBiomeKeys.FEN, shoreCond);
        ConfiguredBiomePlacement.addSubOverworld(Biomes.OLD_GROWTH_PINE_TAIGA, BloomingNatureBiomeKeys.FEN, shoreCond);
        ConfiguredBiomePlacement.addSubOverworld(BloomingNatureBiomeKeys.LARCH_FOREST, BloomingNatureBiomeKeys.FEN, shoreCond);
        ConfiguredBiomePlacement.addSubOverworld(BloomingNatureBiomeKeys.COLD_GRASSLAND, BloomingNatureBiomeKeys.FEN, shoreCond);

        ConfiguredBiomePlacement.addSubOverworld(Biomes.TAIGA, BloomingNatureBiomeKeys.FEN, inlandCond);
        ConfiguredBiomePlacement.addSubOverworld(Biomes.SNOWY_TAIGA, BloomingNatureBiomeKeys.FEN, inlandCond);
        ConfiguredBiomePlacement.addSubOverworld(Biomes.OLD_GROWTH_SPRUCE_TAIGA, BloomingNatureBiomeKeys.FEN, inlandCond);
        ConfiguredBiomePlacement.addSubOverworld(Biomes.OLD_GROWTH_PINE_TAIGA, BloomingNatureBiomeKeys.FEN, inlandCond);
        ConfiguredBiomePlacement.addSubOverworld(BloomingNatureBiomeKeys.LARCH_FOREST, BloomingNatureBiomeKeys.FEN, inlandCond);
        ConfiguredBiomePlacement.addSubOverworld(BloomingNatureBiomeKeys.COLD_GRASSLAND, BloomingNatureBiomeKeys.FEN, inlandCond);
    }

    private static void registerHighlandWoodsPlacement() {
        var nearCold = CriterionBuilder.anyOf(
                CriterionBuilder.neighbor(Biomes.TAIGA),
                CriterionBuilder.neighbor(Biomes.SNOWY_TAIGA),
                CriterionBuilder.neighbor(Biomes.OLD_GROWTH_SPRUCE_TAIGA),
                CriterionBuilder.neighbor(Biomes.OLD_GROWTH_PINE_TAIGA),
                CriterionBuilder.neighbor(BloomingNatureBiomeKeys.LARCH_FOREST),
                CriterionBuilder.neighbor(BloomingNatureBiomeKeys.COLD_RIVER),
                CriterionBuilder.neighbor(Biomes.FROZEN_RIVER)
        );

        ConfiguredBiomePlacement.addSubOverworld(Biomes.PLAINS, BloomingNatureBiomeKeys.HIGHLAND_WOODS, nearCold);
        ConfiguredBiomePlacement.addSubOverworld(BloomingNatureBiomeKeys.COLD_GRASSLAND, BloomingNatureBiomeKeys.HIGHLAND_WOODS, nearCold);
    }
}