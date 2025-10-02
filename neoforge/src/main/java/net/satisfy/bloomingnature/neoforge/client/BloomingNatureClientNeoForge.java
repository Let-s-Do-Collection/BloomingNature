package net.satisfy.bloomingnature.neoforge.client;

import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.satisfy.bloomingnature.BloomingNature;
import net.satisfy.bloomingnature.client.BloomingNatureClient;
import net.satisfy.bloomingnature.core.entity.ModBoatEntity;

@EventBusSubscriber(modid = BloomingNature.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class BloomingNatureClientNeoForge {

    @SubscribeEvent
    public static void beforeClientSetup(RegisterEvent event) {
        BloomingNatureClient.preInitClient();
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        BloomingNatureClient.initClient();
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        for (ModBoatEntity.Type type : ModBoatEntity.Type.values()) {
            event.registerLayerDefinition(new ModelLayerLocation(BloomingNature.identifier(type.getModelLocation()), "main"), BoatModel::createBodyModel);
            event.registerLayerDefinition(new ModelLayerLocation(BloomingNature.identifier(type.getChestModelLocation()), "main"), ChestBoatModel::createBodyModel);
        }
    }
}
