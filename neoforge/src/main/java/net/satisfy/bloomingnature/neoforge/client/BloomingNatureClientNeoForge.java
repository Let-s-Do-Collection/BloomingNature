package net.satisfy.bloomingnature.neoforge.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.satisfy.bloomingnature.BloomingNature;
import net.satisfy.bloomingnature.client.BloomingNatureClient;

@SuppressWarnings("removal")
@EventBusSubscriber(modid = BloomingNature.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class BloomingNatureClientNeoForge {

    @SubscribeEvent
    public static void beforeClientSetup(RegisterEvent event) {
        BloomingNatureClient.preInitClient();
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(BloomingNatureClient::initClient);
    }
}