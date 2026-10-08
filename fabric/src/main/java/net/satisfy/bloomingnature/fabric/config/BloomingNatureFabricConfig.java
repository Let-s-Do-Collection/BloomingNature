package net.satisfy.bloomingnature.fabric.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.satisfy.bloomingnature.core.config.BloomingNatureConfig;

@Config(name = "bloomingnature")
@Config.Gui.Background("minecraft:textures/block/moss_block.png")
public class BloomingNatureFabricConfig implements ConfigData {
    @ConfigEntry.Gui.CollapsibleObject
    public Effects effects = new Effects();

    @ConfigEntry.Gui.CollapsibleObject
    public Gardener gardener = new Gardener();

    @ConfigEntry.Gui.CollapsibleObject
    public Misc misc = new Misc();

    @ConfigEntry.Gui.CollapsibleObject
    public Fog fog = new Fog();

    @ConfigEntry.Gui.CollapsibleObject
    public Biomes biomes = new Biomes();

    public static class Effects {
        @ConfigEntry.Gui.Tooltip
        public boolean bannerGiveEffect = true;
        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.BoundedDiscrete(min = 1, max = 32)
        public int bannerRadius = 8;
        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.BoundedDiscrete(min = 0, max = 4)
        public int bannerAmplifier = 1;
    }

    public static class Gardener {
        @ConfigEntry.Gui.Tooltip
        public boolean gardenerEnabled = true;
        @ConfigEntry.Gui.Tooltip
        public boolean gardenerBringsCamel = true;
        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.BoundedDiscrete(min = 1200, max = 240000)
        public int gardenerMinDelay = 24000;
        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.BoundedDiscrete(min = 1200, max = 480000)
        public int gardenerMaxDelay = 60000;
        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.BoundedDiscrete(min = 1200, max = 240000)
        public int gardenerVisitDuration = 48000;
    }

    public static class Misc {
        @ConfigEntry.Gui.Tooltip
        public boolean firefliesEnabled = true;
        @ConfigEntry.Gui.Tooltip
        public boolean fallingLeavesEnabled = true;
        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.BoundedDiscrete(min = 10, max = 300)
        public int fallingLeavesDensity = 100;
    }

    public static class Fog {
        @ConfigEntry.Gui.Tooltip
        public boolean enabled = true;
        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
        public int startPercent = 0;
        @ConfigEntry.Gui.Tooltip
        public boolean morning = true;
        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.BoundedDiscrete(min = 1, max = 100)
        public int morningEndPercent = 45;
        @ConfigEntry.Gui.Tooltip
        public boolean rain = true;
        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.BoundedDiscrete(min = 1, max = 100)
        public int rainEndPercent = 30;
        @ConfigEntry.Gui.Tooltip
        public boolean hollows = true;
        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.BoundedDiscrete(min = 1, max = 100)
        public int hollowEndPercent = 20;
        @ConfigEntry.Gui.Tooltip
        public boolean fogFen = true;
        @ConfigEntry.Gui.Tooltip
        public boolean fogMarshland = true;
        @ConfigEntry.Gui.Tooltip
        public boolean fogSwamp = true;
        @ConfigEntry.Gui.Tooltip
        public boolean fogMangroveSwamp = true;
        @ConfigEntry.Gui.Tooltip
        public boolean fogRiver = true;
        @ConfigEntry.Gui.Tooltip
        public boolean fogFrozenRiver = true;
        @ConfigEntry.Gui.Tooltip
        public boolean fogColdRiver = true;
        @ConfigEntry.Gui.Tooltip
        public boolean fogJungleRiver = true;
        @ConfigEntry.Gui.Tooltip
        public boolean fogForest = true;
        @ConfigEntry.Gui.Tooltip
        public boolean fogFlowerForest = true;
        @ConfigEntry.Gui.Tooltip
        public boolean fogBirchForest = true;
        @ConfigEntry.Gui.Tooltip
        public boolean fogOldGrowthBirchForest = true;
        @ConfigEntry.Gui.Tooltip
        public boolean fogDarkForest = true;
        @ConfigEntry.Gui.Tooltip
        public boolean fogCherryGrove = true;
        @ConfigEntry.Gui.Tooltip
        public boolean fogTaiga = true;
        @ConfigEntry.Gui.Tooltip
        public boolean fogOldGrowthSpruceTaiga = true;
        @ConfigEntry.Gui.Tooltip
        public boolean fogOldGrowthPineTaiga = true;
        @ConfigEntry.Gui.Tooltip
        public boolean fogAspenForest = true;
        @ConfigEntry.Gui.Tooltip
        public boolean fogLarchForest = true;
        @ConfigEntry.Gui.Tooltip
        public boolean fogHighlandWoods = true;
        @ConfigEntry.Gui.Tooltip
        public boolean fogFlowerGlade = true;
        @ConfigEntry.Gui.Tooltip
        public boolean fogGoldenGlade = true;
        @ConfigEntry.Gui.Tooltip
        public boolean fogForestEdge = true;
    }

    public static class Biomes {
        @ConfigEntry.Gui.Tooltip
        public boolean forestEdge = true;
        @ConfigEntry.Gui.Tooltip
        public boolean flowerGlade = true;
        @ConfigEntry.Gui.Tooltip
        public boolean brushland = true;
        @ConfigEntry.Gui.Tooltip
        public boolean cypressFields = true;
        @ConfigEntry.Gui.Tooltip
        public boolean goldenGlade = true;
        @ConfigEntry.Gui.Tooltip
        public boolean baobabSavanna = true;
        @ConfigEntry.Gui.Tooltip
        public boolean desertOasis = true;
        @ConfigEntry.Gui.Tooltip
        public boolean desertRiver = true;
        @ConfigEntry.Gui.Tooltip
        public boolean jungleRiver = true;
        @ConfigEntry.Gui.Tooltip
        public boolean coldRiver = true;
        @ConfigEntry.Gui.Tooltip
        public boolean coldGrassland = true;
        @ConfigEntry.Gui.Tooltip
        public boolean larchForest = true;
        @ConfigEntry.Gui.Tooltip
        public boolean marshland = true;
        @ConfigEntry.Gui.Tooltip
        public boolean aspenForest = true;
        @ConfigEntry.Gui.Tooltip
        public boolean fen = true;
        @ConfigEntry.Gui.Tooltip
        public boolean highlandWoods = true;
    }

    public void apply() {
        BloomingNatureConfig.bannerGiveEffect = effects.bannerGiveEffect;
        BloomingNatureConfig.bannerRadius = effects.bannerRadius;
        BloomingNatureConfig.bannerAmplifier = effects.bannerAmplifier;
        BloomingNatureConfig.gardenerEnabled = gardener.gardenerEnabled;
        BloomingNatureConfig.gardenerBringsCamel = gardener.gardenerBringsCamel;
        BloomingNatureConfig.gardenerMinDelay = gardener.gardenerMinDelay;
        BloomingNatureConfig.gardenerMaxDelay = gardener.gardenerMaxDelay;
        BloomingNatureConfig.gardenerVisitDuration = gardener.gardenerVisitDuration;
        BloomingNatureConfig.firefliesEnabled = misc.firefliesEnabled;
        BloomingNatureConfig.fallingLeavesEnabled = misc.fallingLeavesEnabled;
        BloomingNatureConfig.fallingLeavesDensity = misc.fallingLeavesDensity;
        BloomingNatureConfig.fogEnabled = fog.enabled;
        BloomingNatureConfig.fogStartPercent = fog.startPercent;
        BloomingNatureConfig.fogMorning = fog.morning;
        BloomingNatureConfig.fogMorningEndPercent = Math.max(fog.morningEndPercent, fog.startPercent + 1);
        BloomingNatureConfig.fogRain = fog.rain;
        BloomingNatureConfig.fogRainEndPercent = Math.max(fog.rainEndPercent, fog.startPercent + 1);
        BloomingNatureConfig.fogHollows = fog.hollows;
        BloomingNatureConfig.fogHollowEndPercent = Math.max(fog.hollowEndPercent, fog.startPercent + 1);
        BloomingNatureConfig.fogBiomes.put("bloomingnature:fen", fog.fogFen);
        BloomingNatureConfig.fogBiomes.put("bloomingnature:marshland", fog.fogMarshland);
        BloomingNatureConfig.fogBiomes.put("minecraft:swamp", fog.fogSwamp);
        BloomingNatureConfig.fogBiomes.put("minecraft:mangrove_swamp", fog.fogMangroveSwamp);
        BloomingNatureConfig.fogBiomes.put("minecraft:river", fog.fogRiver);
        BloomingNatureConfig.fogBiomes.put("minecraft:frozen_river", fog.fogFrozenRiver);
        BloomingNatureConfig.fogBiomes.put("bloomingnature:cold_river", fog.fogColdRiver);
        BloomingNatureConfig.fogBiomes.put("bloomingnature:jungle_river", fog.fogJungleRiver);
        BloomingNatureConfig.fogBiomes.put("minecraft:forest", fog.fogForest);
        BloomingNatureConfig.fogBiomes.put("minecraft:flower_forest", fog.fogFlowerForest);
        BloomingNatureConfig.fogBiomes.put("minecraft:birch_forest", fog.fogBirchForest);
        BloomingNatureConfig.fogBiomes.put("minecraft:old_growth_birch_forest", fog.fogOldGrowthBirchForest);
        BloomingNatureConfig.fogBiomes.put("minecraft:dark_forest", fog.fogDarkForest);
        BloomingNatureConfig.fogBiomes.put("minecraft:cherry_grove", fog.fogCherryGrove);
        BloomingNatureConfig.fogBiomes.put("minecraft:taiga", fog.fogTaiga);
        BloomingNatureConfig.fogBiomes.put("minecraft:old_growth_spruce_taiga", fog.fogOldGrowthSpruceTaiga);
        BloomingNatureConfig.fogBiomes.put("minecraft:old_growth_pine_taiga", fog.fogOldGrowthPineTaiga);
        BloomingNatureConfig.fogBiomes.put("bloomingnature:aspen_forest", fog.fogAspenForest);
        BloomingNatureConfig.fogBiomes.put("bloomingnature:larch_forest", fog.fogLarchForest);
        BloomingNatureConfig.fogBiomes.put("bloomingnature:highland_woods", fog.fogHighlandWoods);
        BloomingNatureConfig.fogBiomes.put("bloomingnature:flower_glade", fog.fogFlowerGlade);
        BloomingNatureConfig.fogBiomes.put("bloomingnature:golden_glade", fog.fogGoldenGlade);
        BloomingNatureConfig.fogBiomes.put("bloomingnature:forest_edge", fog.fogForestEdge);
        BloomingNatureConfig.setBiomeEnabled("forest_edge", biomes.forestEdge);
        BloomingNatureConfig.setBiomeEnabled("flower_glade", biomes.flowerGlade);
        BloomingNatureConfig.setBiomeEnabled("brushland", biomes.brushland);
        BloomingNatureConfig.setBiomeEnabled("cypress_fields", biomes.cypressFields);
        BloomingNatureConfig.setBiomeEnabled("golden_glade", biomes.goldenGlade);
        BloomingNatureConfig.setBiomeEnabled("baobab_savanna", biomes.baobabSavanna);
        BloomingNatureConfig.setBiomeEnabled("desert_oasis", biomes.desertOasis);
        BloomingNatureConfig.setBiomeEnabled("desert_river", biomes.desertRiver);
        BloomingNatureConfig.setBiomeEnabled("jungle_river", biomes.jungleRiver);
        BloomingNatureConfig.setBiomeEnabled("cold_river", biomes.coldRiver);
        BloomingNatureConfig.setBiomeEnabled("cold_grassland", biomes.coldGrassland);
        BloomingNatureConfig.setBiomeEnabled("larch_forest", biomes.larchForest);
        BloomingNatureConfig.setBiomeEnabled("marshland", biomes.marshland);
        BloomingNatureConfig.setBiomeEnabled("aspen_forest", biomes.aspenForest);
        BloomingNatureConfig.setBiomeEnabled("fen", biomes.fen);
        BloomingNatureConfig.setBiomeEnabled("highland_woods", biomes.highlandWoods);
    }
}
