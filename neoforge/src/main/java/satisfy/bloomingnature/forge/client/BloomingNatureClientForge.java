package satisfy.bloomingnature.forge.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegisterEvent;
import satisfy.bloomingnature.BloomingNature;
import satisfy.bloomingnature.client.BloomingNatureClient;

@Mod.EventBusSubscriber(modid = BloomingNature.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BloomingNatureClientForge {

    @SubscribeEvent
    public static void beforeClientSetup(RegisterEvent event) {
        BloomingNatureClient.preInitClient();
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        BloomingNatureClient.initClient();
    }
}
