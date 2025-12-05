package net.satisfy.bloomingnature.core.world.biome.cold;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.RatioTargets;
import com.terraformersmc.biolith.api.surface.BiolithSurfaceBuilder;
import net.minecraft.world.level.biome.Biomes;
import net.satisfy.bloomingnature.core.world.biome.BloomingNatureBiomeKeys;

import static com.terraformersmc.biolith.api.biome.sub.CriterionBuilder.allOf;
import static com.terraformersmc.biolith.api.biome.sub.CriterionBuilder.anyOf;
import static com.terraformersmc.biolith.api.biome.sub.CriterionBuilder.neighbor;
import static com.terraformersmc.biolith.api.biome.sub.CriterionBuilder.ratio;

public final class ColdBiomeRegistry extends BiolithSurfaceBuilder {

    public static void registerBiomePlacement() {
        registerColdRiverPlacement();
    }

    private static void registerColdRiverPlacement() {
        var coldBiomes = anyOf(neighbor(Biomes.SNOWY_TAIGA), neighbor(Biomes.TAIGA), neighbor(Biomes.OLD_GROWTH_SPRUCE_TAIGA), neighbor(Biomes.OLD_GROWTH_PINE_TAIGA));

        BiomePlacement.addSubOverworld(Biomes.RIVER, BloomingNatureBiomeKeys.COLD_RIVER, coldBiomes);
    }
}