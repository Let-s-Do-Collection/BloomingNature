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
    }
}
