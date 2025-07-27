package satisfy.bloomingnature.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import satisfy.bloomingnature.BloomingNature;
import satisfy.bloomingnature.forge.registry.BloomingNatureBiomeModifiers;
import satisfy.bloomingnature.registry.CompostableRegistry;

@Mod(BloomingNature.MOD_ID)
public class BloomingNatureForge {
    public BloomingNatureForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(BloomingNature.MOD_ID, modEventBus);
        BloomingNature.init();
        BloomingNatureBiomeModifiers.BIOME_MODIFIER_SERIALIZERS.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
    }


        private void commonSetup(final FMLCommonSetupEvent event) {
            event.enqueueWork(CompostableRegistry::init);
            BloomingNature.commonInit();
        }
    }