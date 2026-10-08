package net.satisfy.bloomingnature.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.satisfy.bloomingnature.client.BloomingNatureClient;
import net.satisfy.bloomingnature.client.particle.FallingLeafParticle;
import net.satisfy.bloomingnature.core.registry.ParticleRegistry;

public class BloomingNatureClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BloomingNatureClient.preInitClient();
        BloomingNatureClient.initClient();
        ParticleFactoryRegistry.getInstance().register(ParticleRegistry.FALLING_LEAF.get(), FallingLeafParticle.Provider::new);
    }
}
