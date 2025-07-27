package satisfy.bloomingnature.neoforge;

import net.neoforged.fml.loading.FMLPaths;

import java.nio.file.Path;

public class BloomingNatureExpectPlatformImpl {
    public static Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }
}
