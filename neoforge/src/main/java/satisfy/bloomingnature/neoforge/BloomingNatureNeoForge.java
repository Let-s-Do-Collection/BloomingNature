package satisfy.bloomingnature.neoforge;

import dev.architectury.platform.hooks.EventBusesHooks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import satisfy.bloomingnature.BloomingNature;
import satisfy.bloomingnature.neoforge.registry.BloomingNatureBiomeModifiers;
import satisfy.bloomingnature.registry.CompostableRegistry;

@Mod(BloomingNature.MOD_ID)
public class BloomingNatureNeoForge {

    public BloomingNatureNeoForge(final IEventBus modEventBus, final ModContainer modContainer) {
        EventBusesHooks.whenAvailable(BloomingNature.MOD_ID, IEventBus::start);
        BloomingNature.init();
        BloomingNatureBiomeModifiers.BIOME_MODIFIER_SERIALIZERS.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
    }


    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(CompostableRegistry::init);
        BloomingNature.commonInit();
    }
}