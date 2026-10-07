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
