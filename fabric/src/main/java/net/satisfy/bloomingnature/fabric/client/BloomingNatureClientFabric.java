package net.satisfy.bloomingnature.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.satisfy.bloomingnature.BloomingNature;
import net.satisfy.bloomingnature.client.BloomingNatureClient;
import net.satisfy.bloomingnature.core.entity.ModBoatEntity;

public class BloomingNatureClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BloomingNatureClient.preInitClient();
        BloomingNatureClient.initClient();
        registerBoatModels();
    }

    private void registerBoatModels() {
        for (ModBoatEntity.Type type : ModBoatEntity.Type.values()) {
            String modId = BloomingNature.MOD_ID;
            EntityModelLayerRegistry.registerModelLayer(new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(modId, type.getModelLocation()), "main"), BoatModel::createBodyModel);
            EntityModelLayerRegistry.registerModelLayer(new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(modId, type.getChestModelLocation()), "main"), ChestBoatModel::createBodyModel);
        }
    }
}
