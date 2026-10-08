package net.satisfy.bloomingnature.core.world.biome.wet;

import net.satisfy.bloomingnature.core.world.biome.ConfiguredBiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.RatioTargets;
import com.terraformersmc.biolith.api.surface.BiolithSurfaceBuilder;
import net.minecraft.world.level.biome.Biomes;
import net.satisfy.bloomingnature.core.world.biome.BloomingNatureBiomeKeys;

import static com.terraformersmc.biolith.api.biome.sub.CriterionBuilder.allOf;
import static com.terraformersmc.biolith.api.biome.sub.CriterionBuilder.anyOf;
import static com.terraformersmc.biolith.api.biome.sub.CriterionBuilder.neighbor;
import static com.terraformersmc.biolith.api.biome.sub.CriterionBuilder.ratio;

public final class WetBiomeRegistry extends BiolithSurfaceBuilder {

    public static void registerBiomePlacement() {
        registerJungleRiverPlacement();
        registerMarshlandPlacement();
    }

    private static void registerJungleRiverPlacement() {
        var nearJungle = anyOf(neighbor(Biomes.JUNGLE), neighbor(Biomes.SPARSE_JUNGLE), neighbor(Biomes.BAMBOO_JUNGLE));
        ConfiguredBiomePlacement.addSubOverworld(Biomes.RIVER, BloomingNatureBiomeKeys.JUNGLE_RIVER, nearJungle);

        var riverTouch = neighbor(Biomes.RIVER);
        var edgeBand = ratio(RatioTargets.EDGE, 0.0f, 0.15f);
        var condEdge = allOf(riverTouch, edgeBand);

        ConfiguredBiomePlacement.addSubOverworld(Biomes.JUNGLE, BloomingNatureBiomeKeys.JUNGLE_RIVER, condEdge);
        ConfiguredBiomePlacement.addSubOverworld(Biomes.SPARSE_JUNGLE, BloomingNatureBiomeKeys.JUNGLE_RIVER, condEdge);
        ConfiguredBiomePlacement.addSubOverworld(Biomes.BAMBOO_JUNGLE, BloomingNatureBiomeKeys.JUNGLE_RIVER, condEdge);
    }

    private static void registerMarshlandPlacement() {
        var nearWet = anyOf(neighbor(Biomes.SWAMP), neighbor(Biomes.MANGROVE_SWAMP));
        var edgeZone = ratio(RatioTargets.EDGE, 0.0f, 0.55f);
        var cond = allOf(nearWet, edgeZone);

        ConfiguredBiomePlacement.addSubOverworld(Biomes.SWAMP, BloomingNatureBiomeKeys.MARSHLAND, cond);
        ConfiguredBiomePlacement.addSubOverworld(Biomes.MANGROVE_SWAMP, BloomingNatureBiomeKeys.MARSHLAND, cond);
    }
}