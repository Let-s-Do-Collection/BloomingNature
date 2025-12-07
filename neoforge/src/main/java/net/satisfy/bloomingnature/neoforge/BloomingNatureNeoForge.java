package net.satisfy.bloomingnature.neoforge;

import dev.architectury.platform.hooks.EventBusesHooks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.satisfy.bloomingnature.BloomingNature;
import net.satisfy.bloomingnature.core.registry.CompostableRegistry;
import net.satisfy.bloomingnature.neoforge.core.registry.BloomingNatureBiomeModifiers;
import net.satisfy.bloomingnature.platform.neoforge.PlatformHelperImpl;


@Mod(BloomingNature.MOD_ID)
public class BloomingNatureNeoForge {
    public BloomingNatureNeoForge(final IEventBus modEventBus) {
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
