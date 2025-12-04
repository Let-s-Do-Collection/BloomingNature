package net.satisfy.bloomingnature.core.world.biome.wet;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.RatioTargets;
import com.terraformersmc.biolith.api.surface.BiolithSurfaceBuilder;
import net.minecraft.world.level.biome.Biomes;
import net.satisfy.bloomingnature.core.world.biome.BloomingNatureBiomeKeys;

import static com.terraformersmc.biolith.api.biome.sub.CriterionBuilder.allOf;
import static com.terraformersmc.biolith.api.biome.sub.CriterionBuilder.anyOf;
import static com.terraformersmc.biolith.api.biome.sub.CriterionBuilder.neighbor;
import static com.terraformersmc.biolith.api.biome.sub.CriterionBuilder.ratio;

public final class WetBiomesRegistry extends BiolithSurfaceBuilder {

    public static void registerBiomePlacement() {
        registerJungleRiverPlacement();
    }

    private static void registerJungleRiverPlacement() {
        var nearJungle = anyOf(neighbor(Biomes.JUNGLE), neighbor(Biomes.SPARSE_JUNGLE), neighbor(Biomes.BAMBOO_JUNGLE));
        BiomePlacement.addSubOverworld(Biomes.RIVER, BloomingNatureBiomeKeys.JUNGLE_RIVER, nearJungle);

        var riverTouch = neighbor(Biomes.RIVER);
        var edgeBand = ratio(RatioTargets.EDGE, 0.35f, 1.0f);
        var condEdge = allOf(riverTouch, edgeBand);

        BiomePlacement.addSubOverworld(Biomes.JUNGLE, BloomingNatureBiomeKeys.JUNGLE_RIVER, condEdge);
        BiomePlacement.addSubOverworld(Biomes.SPARSE_JUNGLE, BloomingNatureBiomeKeys.JUNGLE_RIVER, condEdge);
        BiomePlacement.addSubOverworld(Biomes.BAMBOO_JUNGLE, BloomingNatureBiomeKeys.JUNGLE_RIVER, condEdge);
    }
}