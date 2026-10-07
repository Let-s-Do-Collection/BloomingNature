package net.satisfy.bloomingnature.core.config;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.satisfy.bloomingnature.BloomingNature;

import java.util.HashSet;
import java.util.Set;

public final class BloomingNatureConfig {
    public static boolean bannerGiveEffect = true;
    public static int bannerRadius = 8;
    public static int bannerAmplifier = 1;

    public static boolean gardenerEnabled = true;
    public static boolean gardenerBringsCamel = true;
    public static int gardenerMinDelay = 24000;
    public static int gardenerMaxDelay = 60000;
    public static int gardenerVisitDuration = 48000;

    public static boolean firefliesEnabled = true;

    public static final Set<String> disabledBiomes = new HashSet<>();

    private BloomingNatureConfig() {
    }

    public static boolean isBiomeEnabled(ResourceKey<Biome> biome) {
        return !BloomingNature.MOD_ID.equals(biome.location().getNamespace()) || !disabledBiomes.contains(biome.location().getPath());
    }

    public static void setBiomeEnabled(String biome, boolean enabled) {
        if (enabled) {
            disabledBiomes.remove(biome);
        } else {
            disabledBiomes.add(biome);
        }
    }
}
