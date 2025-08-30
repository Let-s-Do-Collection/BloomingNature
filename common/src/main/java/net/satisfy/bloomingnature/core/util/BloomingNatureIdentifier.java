package net.satisfy.bloomingnature.core.util;

import net.minecraft.resources.ResourceLocation;
import net.satisfy.bloomingnature.BloomingNature;

public class BloomingNatureIdentifier  {
    public static ResourceLocation of(String path) {
        return ResourceLocation.fromNamespaceAndPath(BloomingNature.MOD_ID, path);
    }

    public static String asString(String path) {
        return (BloomingNature.MOD_ID + ":" + path);
    }
}