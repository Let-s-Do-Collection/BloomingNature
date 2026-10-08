package net.satisfy.bloomingnature.neoforge.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import java.util.LinkedHashMap;
import java.util.Map;
import net.satisfy.bloomingnature.core.config.BloomingNatureConfig;

public final class BloomingNatureNeoForgeConfig {
    private static final ModConfigSpec.BooleanValue BANNER_GIVE_EFFECT;
    private static final ModConfigSpec.IntValue BANNER_RADIUS;
    private static final ModConfigSpec.IntValue BANNER_AMPLIFIER;
    private static final ModConfigSpec.BooleanValue GARDENER_ENABLED;
    private static final ModConfigSpec.BooleanValue GARDENER_BRINGS_CAMEL;
    private static final ModConfigSpec.IntValue GARDENER_MIN_DELAY;
    private static final ModConfigSpec.IntValue GARDENER_MAX_DELAY;
    private static final ModConfigSpec.IntValue GARDENER_VISIT_DURATION;
    private static final ModConfigSpec.BooleanValue FIREFLIES_ENABLED;
    private static final ModConfigSpec.BooleanValue FALLING_LEAVES_ENABLED;
    private static final ModConfigSpec.IntValue FALLING_LEAVES_DENSITY;
    private static final ModConfigSpec.BooleanValue FOG_ENABLED;
    private static final ModConfigSpec.IntValue FOG_START;
    private static final ModConfigSpec.BooleanValue FOG_MORNING;
    private static final ModConfigSpec.IntValue FOG_MORNING_END;
    private static final ModConfigSpec.BooleanValue FOG_RAIN;
    private static final ModConfigSpec.IntValue FOG_RAIN_END;
    private static final ModConfigSpec.BooleanValue FOG_HOLLOWS;
    private static final ModConfigSpec.IntValue FOG_HOLLOW_END;
    private static final Map<String, ModConfigSpec.BooleanValue> FOG_BIOMES = new LinkedHashMap<>();
    public static final ModConfigSpec COMMON;
    private static final Map<String, ModConfigSpec.BooleanValue> BIOMES = new LinkedHashMap<>();
    public static final ModConfigSpec STARTUP;

    static {
        ModConfigSpec.Builder common = new ModConfigSpec.Builder();
        common.push("effects");
        BANNER_GIVE_EFFECT = common.comment("Players near a placed Completionist Banner get its effect").define("bannerGiveEffect", true);
        BANNER_RADIUS = common.comment("Radius of the Completionist Banner effect").defineInRange("bannerRadius", 8, 1, 32);
        BANNER_AMPLIFIER = common.comment("Level of the Completionist Banner effect, 0 is level I").defineInRange("bannerAmplifier", 1, 0, 4);
        common.pop();
        common.push("gardener");
        GARDENER_ENABLED = common.comment("The Wandering Gardener visits players now and then").define("gardenerEnabled", true);
        GARDENER_BRINGS_CAMEL = common.comment("The Wandering Gardener arrives with a leashed camel").define("gardenerBringsCamel", true);
        GARDENER_MIN_DELAY = common.comment("Shortest time between two visits in ticks, 24000 ticks are one day").defineInRange("gardenerMinDelay", 24000, 1200, 240000);
        GARDENER_MAX_DELAY = common.comment("Longest time between two visits in ticks").defineInRange("gardenerMaxDelay", 60000, 1200, 480000);
        GARDENER_VISIT_DURATION = common.comment("How long the Wandering Gardener stays in ticks").defineInRange("gardenerVisitDuration", 48000, 1200, 240000);
        common.pop();
        common.push("misc");
        FIREFLIES_ENABLED = common.comment("Fireflies appear at night around lanterns, Wild Vines, Cattail, Reed and Flowering Lily Pads").define("firefliesEnabled", true);
        FALLING_LEAVES_ENABLED = common.comment("Leaves drift down from the trees").define("fallingLeavesEnabled", true);
        FALLING_LEAVES_DENSITY = common.comment("How many leaves fall, in percent").defineInRange("fallingLeavesDensity", 100, 10, 300);
        common.pop();
        common.push("fog");
        FOG_ENABLED = common.comment("Mist in damp and cold biomes, in the morning and when it rains").define("enabled", true);
        FOG_START = common.comment("Where the mist starts, in % of the render distance").defineInRange("startPercent", 0, 0, 100);
        FOG_MORNING = common.comment("Mist from late night until shortly after sunrise").define("morning", true);
        FOG_MORNING_END = common.comment("How far you can see in morning mist, in % of the render distance").defineInRange("morningEndPercent", 45, 1, 100);
        FOG_RAIN = common.comment("Mist while it rains").define("rain", true);
        FOG_RAIN_END = common.comment("How far you can see in rain mist, in % of the render distance").defineInRange("rainEndPercent", 30, 1, 100);
        FOG_HOLLOWS = common.comment("Thick mist gathers in hollows and low ground at night and in the morning").define("hollows", true);
        FOG_HOLLOW_END = common.comment("How far you can see in a misty hollow, in % of the render distance").defineInRange("hollowEndPercent", 20, 1, 100);
        FOG_BIOMES.put("bloomingnature:fen", common.comment("Thick mist over the cold peat bogs").define("fogFen", true));
        FOG_BIOMES.put("bloomingnature:marshland", common.comment("Mist rising from the reeds and pools").define("fogMarshland", true));
        FOG_BIOMES.put("minecraft:swamp", common.comment("Mist hanging over the murky water").define("fogSwamp", true));
        FOG_BIOMES.put("minecraft:mangrove_swamp", common.comment("Humid haze between the mangrove roots").define("fogMangroveSwamp", true));
        FOG_BIOMES.put("minecraft:river", common.comment("Mist rising from the water at dawn").define("fogRiver", true));
        FOG_BIOMES.put("minecraft:frozen_river", common.comment("Cold mist over the frozen river").define("fogFrozenRiver", true));
        FOG_BIOMES.put("bloomingnature:cold_river", common.comment("Mist over the cold rivers of snowy regions").define("fogColdRiver", true));
        FOG_BIOMES.put("bloomingnature:jungle_river", common.comment("Steamy haze over the jungle rivers").define("fogJungleRiver", true));
        FOG_BIOMES.put("minecraft:forest", common.comment("Light morning mist between the trees").define("fogForest", true));
        FOG_BIOMES.put("minecraft:flower_forest", common.comment("Soft mist over the flower meadows").define("fogFlowerForest", true));
        FOG_BIOMES.put("minecraft:birch_forest", common.comment("Pale mist between the birch trunks").define("fogBirchForest", true));
        FOG_BIOMES.put("minecraft:old_growth_birch_forest", common.comment("Mist between the tall birches").define("fogOldGrowthBirchForest", true));
        FOG_BIOMES.put("minecraft:dark_forest", common.comment("Gloomy mist under the dense canopy").define("fogDarkForest", true));
        FOG_BIOMES.put("minecraft:cherry_grove", common.comment("Pink-tinted haze among the blossoms").define("fogCherryGrove", true));
        FOG_BIOMES.put("minecraft:taiga", common.comment("Cool mist between the spruces").define("fogTaiga", true));
        FOG_BIOMES.put("minecraft:old_growth_spruce_taiga", common.comment("Thick mist in the ancient spruce forest").define("fogOldGrowthSpruceTaiga", true));
        FOG_BIOMES.put("minecraft:old_growth_pine_taiga", common.comment("Thick mist in the ancient pine forest").define("fogOldGrowthPineTaiga", true));
        FOG_BIOMES.put("bloomingnature:aspen_forest", common.comment("Light mist between the aspens").define("fogAspenForest", true));
        FOG_BIOMES.put("bloomingnature:larch_forest", common.comment("Cold mist between the larches").define("fogLarchForest", true));
        FOG_BIOMES.put("bloomingnature:highland_woods", common.comment("Low clouds drifting through the woods").define("fogHighlandWoods", true));
        FOG_BIOMES.put("bloomingnature:flower_glade", common.comment("Soft mist over the glade").define("fogFlowerGlade", true));
        FOG_BIOMES.put("bloomingnature:golden_glade", common.comment("Golden morning haze over the glade").define("fogGoldenGlade", true));
        FOG_BIOMES.put("bloomingnature:forest_edge", common.comment("Mist creeping out of the forest").define("fogForestEdge", true));
        common.pop();
        COMMON = common.build();

        ModConfigSpec.Builder startup = new ModConfigSpec.Builder();
        startup.comment("Biomes placed in new chunks. Changes need a restart and only affect new chunks").push("biomes");
        BIOMES.put("forest_edge", startup.define("forestEdge", true));
        BIOMES.put("flower_glade", startup.define("flowerGlade", true));
        BIOMES.put("brushland", startup.define("brushland", true));
        BIOMES.put("cypress_fields", startup.define("cypressFields", true));
        BIOMES.put("golden_glade", startup.define("goldenGlade", true));
        BIOMES.put("baobab_savanna", startup.define("baobabSavanna", true));
        BIOMES.put("desert_oasis", startup.define("desertOasis", true));
        BIOMES.put("desert_river", startup.define("desertRiver", true));
        BIOMES.put("jungle_river", startup.define("jungleRiver", true));
        BIOMES.put("cold_river", startup.define("coldRiver", true));
        BIOMES.put("cold_grassland", startup.define("coldGrassland", true));
        BIOMES.put("larch_forest", startup.define("larchForest", true));
        BIOMES.put("marshland", startup.define("marshland", true));
        BIOMES.put("aspen_forest", startup.define("aspenForest", true));
        BIOMES.put("fen", startup.define("fen", true));
        BIOMES.put("highland_woods", startup.define("highlandWoods", true));
        startup.pop();
        STARTUP = startup.build();
    }

    private BloomingNatureNeoForgeConfig() {
    }

    public static void applyStartup() {
        BIOMES.forEach((biome, value) -> BloomingNatureConfig.setBiomeEnabled(biome, value.get()));
    }

    public static void applyCommon() {
        BloomingNatureConfig.bannerGiveEffect = BANNER_GIVE_EFFECT.get();
        BloomingNatureConfig.bannerRadius = BANNER_RADIUS.get();
        BloomingNatureConfig.bannerAmplifier = BANNER_AMPLIFIER.get();
        BloomingNatureConfig.gardenerEnabled = GARDENER_ENABLED.get();
        BloomingNatureConfig.gardenerBringsCamel = GARDENER_BRINGS_CAMEL.get();
        BloomingNatureConfig.gardenerMinDelay = GARDENER_MIN_DELAY.get();
        BloomingNatureConfig.gardenerMaxDelay = GARDENER_MAX_DELAY.get();
        BloomingNatureConfig.gardenerVisitDuration = GARDENER_VISIT_DURATION.get();
        BloomingNatureConfig.firefliesEnabled = FIREFLIES_ENABLED.get();
        BloomingNatureConfig.fallingLeavesEnabled = FALLING_LEAVES_ENABLED.get();
        BloomingNatureConfig.fallingLeavesDensity = FALLING_LEAVES_DENSITY.get();
        BloomingNatureConfig.fogEnabled = FOG_ENABLED.get();
        BloomingNatureConfig.fogStartPercent = FOG_START.get();
        BloomingNatureConfig.fogMorning = FOG_MORNING.get();
        BloomingNatureConfig.fogMorningEndPercent = Math.max(FOG_MORNING_END.get(), FOG_START.get() + 1);
        BloomingNatureConfig.fogRain = FOG_RAIN.get();
        BloomingNatureConfig.fogRainEndPercent = Math.max(FOG_RAIN_END.get(), FOG_START.get() + 1);
        BloomingNatureConfig.fogHollows = FOG_HOLLOWS.get();
        BloomingNatureConfig.fogHollowEndPercent = Math.max(FOG_HOLLOW_END.get(), FOG_START.get() + 1);
        FOG_BIOMES.forEach((biome, value) -> BloomingNatureConfig.fogBiomes.put(biome, value.get()));
    }
}
