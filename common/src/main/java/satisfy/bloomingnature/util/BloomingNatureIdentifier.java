package satisfy.bloomingnature.util;

import net.minecraft.resources.ResourceLocation;
import satisfy.bloomingnature.BloomingNature;

@SuppressWarnings("unused")
public class BloomingNatureIdentifier {

    public static ResourceLocation of(String path) {
        return ResourceLocation.fromNamespaceAndPath(BloomingNature.MOD_ID, path);
    }

    public static String asString(String path) {
        return (BloomingNature.MOD_ID + ":" + path);
    }
}
