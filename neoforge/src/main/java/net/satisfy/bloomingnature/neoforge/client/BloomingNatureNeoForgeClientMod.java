package net.satisfy.bloomingnature.neoforge.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.satisfy.bloomingnature.BloomingNature;

@Mod(value = BloomingNature.MOD_ID, dist = Dist.CLIENT)
public class BloomingNatureNeoForgeClientMod {
    public BloomingNatureNeoForgeClientMod(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
}
