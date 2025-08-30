package net.satisfy.bloomingnature.neoforge;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.satisfy.bloomingnature.BloomingNature;
import net.satisfy.bloomingnature.core.registry.CompostableRegistry;
import net.satisfy.bloomingnature.forge.registry.BloomingNatureBiomeModifiers;
import net.satisfy.bloomingnature.platform.forge.PlatformHelperImpl;


@Mod(BloomingNature.MOD_ID)
public class BloomingNatureNeoForge {
    public BloomingNatureNeoForge(IEventBus modBus, ModContainer modContainer) {
        BloomingNature.init();
        // Falls du ein Config hast, hier registrieren:
        // modContainer.registerConfig(ModConfig.Type.COMMON, BloomingNatureNeoForgeConfig.COMMON_CONFIG);

        PlatformHelperImpl.ENTITY_TYPES.register(modBus);
        BloomingNatureBiomeModifiers.BIOME_MODIFIER_SERIALIZERS.register(modBus);

        modBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(CompostableRegistry::init);
        BloomingNature.commonInit();
    }
}
