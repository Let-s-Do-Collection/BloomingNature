package net.satisfy.bloomingnature.neoforge;

import dev.architectury.platform.hooks.EventBusesHooks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.satisfy.bloomingnature.neoforge.config.BloomingNatureNeoForgeConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.satisfy.bloomingnature.BloomingNature;
import net.satisfy.bloomingnature.core.registry.CompostableRegistry;
import net.satisfy.bloomingnature.neoforge.core.registry.BloomingNatureBiomeModifiers;
import net.satisfy.bloomingnature.platform.neoforge.PlatformHelperImpl;


@Mod(BloomingNature.MOD_ID)
public class BloomingNatureNeoForge {
    public BloomingNatureNeoForge(final IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.STARTUP, BloomingNatureNeoForgeConfig.STARTUP);
        BloomingNatureNeoForgeConfig.applyStartup();
        modContainer.registerConfig(ModConfig.Type.COMMON, BloomingNatureNeoForgeConfig.COMMON);
        modEventBus.addListener((ModConfigEvent.Loading event) -> {
            if (event.getConfig().getSpec() == BloomingNatureNeoForgeConfig.COMMON) BloomingNatureNeoForgeConfig.applyCommon();
        });
        modEventBus.addListener((ModConfigEvent.Reloading event) -> {
            if (event.getConfig().getSpec() == BloomingNatureNeoForgeConfig.COMMON) BloomingNatureNeoForgeConfig.applyCommon();
        });
        EventBusesHooks.whenAvailable(BloomingNature.MOD_ID, IEventBus::start);
        PlatformHelperImpl.ENTITY_TYPES.register(modEventBus);

        BloomingNature.init();
        BloomingNatureBiomeModifiers.BIOME_MODIFIER_SERIALIZERS.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            CompostableRegistry.init();
            BloomingNature.commonInit();
        });
    }
}
