package net.satisfy.bloomingnature.core.config;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.satisfy.bloomingnature.BloomingNature;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
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
    public static boolean fallingLeavesEnabled = true;
    public static int fallingLeavesDensity = 100;
    public static boolean fogEnabled = true;
    public static int fogStartPercent = 0;
    public static boolean fogMorning = true;
    public static int fogMorningEndPercent = 45;
    public static boolean fogRain = true;
    public static int fogRainEndPercent = 30;
    public static boolean fogHollows = true;
    public static int fogHollowEndPercent = 20;
    public static final Map<String, Boolean> fogBiomes = new LinkedHashMap<>();

    static {
        fogBiomes.put("bloomingnature:fen", true);
        fogBiomes.put("bloomingnature:marshland", true);
        fogBiomes.put("minecraft:swamp", true);
        fogBiomes.put("minecraft:mangrove_swamp", true);
        fogBiomes.put("minecraft:river", true);
        fogBiomes.put("minecraft:frozen_river", true);
        fogBiomes.put("bloomingnature:cold_river", true);
        fogBiomes.put("bloomingnature:jungle_river", true);
        fogBiomes.put("minecraft:forest", true);
        fogBiomes.put("minecraft:flower_forest", true);
        fogBiomes.put("minecraft:birch_forest", true);
        fogBiomes.put("minecraft:old_growth_birch_forest", true);
        fogBiomes.put("minecraft:dark_forest", true);
        fogBiomes.put("minecraft:cherry_grove", true);
        fogBiomes.put("minecraft:taiga", true);
        fogBiomes.put("minecraft:old_growth_spruce_taiga", true);
        fogBiomes.put("minecraft:old_growth_pine_taiga", true);
        fogBiomes.put("bloomingnature:aspen_forest", true);
        fogBiomes.put("bloomingnature:larch_forest", true);
        fogBiomes.put("bloomingnature:highland_woods", true);
        fogBiomes.put("bloomingnature:flower_glade", true);
        fogBiomes.put("bloomingnature:golden_glade", true);
        fogBiomes.put("bloomingnature:forest_edge", true);
    }

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
