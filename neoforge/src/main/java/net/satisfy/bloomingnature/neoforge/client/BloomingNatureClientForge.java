package net.satisfy.bloomingnature.neoforge.client;

import dev.architectury.event.events.client.ClientLifecycleEvent;
import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.satisfy.bloomingnature.BloomingNature;
import net.satisfy.bloomingnature.client.BloomingNatureClient;
import net.satisfy.bloomingnature.core.entity.ModBoatEntity;

public class BloomingNatureClientForge {
    static {
        ClientLifecycleEvent.CLIENT_SETUP.register(mc -> {
            BloomingNatureClient.preInitClient();
            BloomingNatureClient.initClient();
        });
        for (ModBoatEntity.Type type : ModBoatEntity.Type.values()) {
            EntityModelLayerRegistry.register(new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(BloomingNature.MOD_ID, type.getModelLocation()), "main"), BoatModel::createBodyModel);
            EntityModelLayerRegistry.register(new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(BloomingNature.MOD_ID, type.getChestModelLocation()), "main"), ChestBoatModel::createBodyModel);
        }
    }
}
