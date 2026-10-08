package net.satisfy.bloomingnature;

import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.hooks.item.tool.AxeItemHooks;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.satisfy.bloomingnature.core.registry.WoodSet;
import net.satisfy.bloomingnature.core.spawn.GardenerSpawner;
import net.satisfy.bloomingnature.core.registry.*;
import net.satisfy.foundation.rarity.FoundationRarities;
import net.satisfy.foundation.rarity.FoundationRarity;

public class BloomingNature {
    public static final String MOD_ID = "bloomingnature";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static ResourceLocation identifier(String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }

    public static void init() {
        EntityTypeRegistry.init();
        ObjectRegistry.init();
        FloraRegistry.init();
        TabRegistry.init();
        PlacerTypeRegistry.init();
        WorldgenRegistry.init();
        ParticleRegistry.init();
        LifecycleEvent.SETUP.register(BloomingNature::registerRarities);
    }

    private static void registerRarities() {
        FoundationRarities.register(ObjectRegistry.BLOOMINGNATURE_BANNER.get(), FoundationRarity.LEGENDARY);
    }

    public static void commonInit() {
        FlammableBlockRegistry.init();
        GardenerSpawner.init();
        for (WoodSet set : WoodSet.all()) {
            if (set.log != null) {
                AxeItemHooks.addStrippable(set.log.get(), set.strippedLog.get());
                AxeItemHooks.addStrippable(set.wood.get(), set.strippedWood.get());
            }
        }
    }
}

