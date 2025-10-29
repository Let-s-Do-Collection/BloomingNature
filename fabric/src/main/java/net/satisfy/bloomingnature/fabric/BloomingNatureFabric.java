package net.satisfy.bloomingnature.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.resources.ResourceLocation;
import net.satisfy.bloomingnature.BloomingNature;
import net.satisfy.bloomingnature.core.registry.CompostableRegistry;
import net.satisfy.bloomingnature.fabric.core.world.BloomingNatureFabricWorldgen;

import java.util.Optional;

public class BloomingNatureFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        BloomingNature.init();
        BloomingNature.commonInit();
        CompostableRegistry.init();
        BloomingNatureFabricWorldgen.init();

        Optional<ModContainer> modContainer = FabricLoader.getInstance().getModContainer(BloomingNature.MOD_ID);
        modContainer.ifPresent(container -> ResourceManagerHelper.registerBuiltinResourcePack(ResourceLocation.fromNamespaceAndPath(BloomingNature.MOD_ID, "bushy_leaves"), container, ResourcePackActivationType.NORMAL
        ));
    }
}
