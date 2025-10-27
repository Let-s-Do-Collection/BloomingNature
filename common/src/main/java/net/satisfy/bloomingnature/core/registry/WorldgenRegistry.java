package net.satisfy.bloomingnature.core.registry;

import net.minecraft.world.level.biome.Biomes;
import net.satisfy.bloomingnature.core.world.biome.arid.AridBiomeRegistry;
import net.satisfy.bloomingnature.core.world.biome.arid.surface.AridSurfaceBuilder;
import net.satisfy.bloomingnature.core.world.biome.temperate.TemperateBiomeRegistry;
import net.satisfy.bloomingnature.core.world.biome.temperate.surface.TemperateSurfaceBuilder;
import com.terraformersmc.biolith.api.biome.BiomePlacement;

public final class WorldgenRegistry {
    public static void init() {
        TemperateSurfaceBuilder.registerSurfaceRules();
        TemperateBiomeRegistry.registerBiomePlacement();

        AridSurfaceBuilder.registerSurfaceRules();
        AridBiomeRegistry.registerBiomePlacement();

        BiomePlacement.removeOverworld(Biomes.WINDSWEPT_HILLS);
        BiomePlacement.removeOverworld(Biomes.WINDSWEPT_FOREST);
        BiomePlacement.removeOverworld(Biomes.WINDSWEPT_SAVANNA);
        BiomePlacement.removeOverworld(Biomes.WINDSWEPT_GRAVELLY_HILLS);
    }
}
