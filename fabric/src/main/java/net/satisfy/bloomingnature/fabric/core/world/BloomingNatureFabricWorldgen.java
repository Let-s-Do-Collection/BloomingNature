package net.satisfy.bloomingnature.fabric.core.world;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.satisfy.bloomingnature.BloomingNature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.stream.Stream;

public final class BloomingNatureFabricWorldgen {
    private static final Logger LOGGER = LoggerFactory.getLogger(BloomingNatureFabricWorldgen.class);
    private static final String MODIFIER_DIRECTORY = "data/" + BloomingNature.MOD_ID + "/neoforge/biome_modifier";

    private BloomingNatureFabricWorldgen() {
    }

    public static void init() {
        FabricLoader.getInstance().getModContainer(BloomingNature.MOD_ID)
                .flatMap(container -> container.findPath(MODIFIER_DIRECTORY))
                .ifPresent(BloomingNatureFabricWorldgen::load);
    }

    private static void load(Path directory) {
        try (Stream<Path> files = Files.list(directory)) {
            files.filter(path -> path.getFileName().toString().endsWith(".json")).sorted().forEach(BloomingNatureFabricWorldgen::apply);
        } catch (IOException exception) {
            LOGGER.error("Could not read biome modifiers", exception);
        }
    }

    private static void apply(Path file) {
        String name = file.getFileName().toString();
        try (Reader reader = Files.newBufferedReader(file)) {
            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
            String type = json.get("type").getAsString();
            Predicate<BiomeSelectionContext> biomes = selector(json.get("biomes").getAsString());
            String id = name.substring(0, name.length() - ".json".length());
            switch (type) {
                case "neoforge:add_features" -> addFeatures(id, biomes, json);
                case "neoforge:remove_features" -> removeFeatures(id, biomes, json);
                case "bloomingnature:biome_effects" -> effects(id, biomes, json);
                default -> LOGGER.warn("Unknown biome modifier type {} in {}", type, name);
            }
        } catch (Exception exception) {
            LOGGER.error("Could not apply biome modifier {}", name, exception);
        }
    }

    private static Predicate<BiomeSelectionContext> selector(String biomes) {
        if (biomes.startsWith("#")) {
            return BiomeSelectors.tag(TagKey.create(Registries.BIOME, ResourceLocation.parse(biomes.substring(1))));
        }
        return BiomeSelectors.includeByKey(ResourceKey.<Biome>create(Registries.BIOME, ResourceLocation.parse(biomes)));
    }

    private static void addFeatures(String id, Predicate<BiomeSelectionContext> biomes, JsonObject json) {
        GenerationStep.Decoration step = step(json.get("step").getAsString());
        List<ResourceKey<PlacedFeature>> features = features(json);
        BiomeModifications.create(BloomingNature.identifier(id)).add(ModificationPhase.ADDITIONS, biomes, context -> {
            for (ResourceKey<PlacedFeature> feature : features) {
                context.getGenerationSettings().addFeature(step, feature);
            }
        });
    }

    private static void removeFeatures(String id, Predicate<BiomeSelectionContext> biomes, JsonObject json) {
        List<GenerationStep.Decoration> steps = new ArrayList<>();
        if (json.get("steps").isJsonArray()) {
            json.getAsJsonArray("steps").forEach(element -> steps.add(step(element.getAsString())));
        } else {
            steps.add(step(json.get("steps").getAsString()));
        }
        List<ResourceKey<PlacedFeature>> features = features(json);
        BiomeModifications.create(BloomingNature.identifier(id)).add(ModificationPhase.REMOVALS, biomes, context -> {
            for (GenerationStep.Decoration step : steps) {
                for (ResourceKey<PlacedFeature> feature : features) {
                    context.getGenerationSettings().removeFeature(step, feature);
                }
            }
        });
    }

    private static void effects(String id, Predicate<BiomeSelectionContext> biomes, JsonObject json) {
        BiomeModifications.create(BloomingNature.identifier(id)).add(ModificationPhase.ADDITIONS, biomes, context -> {
            if (json.has("grass_color")) {
                context.getEffects().setGrassColor(json.get("grass_color").getAsInt());
            }
            if (json.has("foliage_color")) {
                context.getEffects().setFoliageColor(json.get("foliage_color").getAsInt());
            }
            if (json.has("water_color")) {
                context.getEffects().setWaterColor(json.get("water_color").getAsInt());
            }
            if (json.has("water_fog_color")) {
                context.getEffects().setWaterFogColor(json.get("water_fog_color").getAsInt());
            }
        });
    }

    private static List<ResourceKey<PlacedFeature>> features(JsonObject json) {
        List<ResourceKey<PlacedFeature>> features = new ArrayList<>();
        json.getAsJsonArray("features").forEach(element -> features.add(ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.parse(element.getAsString()))));
        return features;
    }

    private static GenerationStep.Decoration step(String name) {
        return GenerationStep.Decoration.valueOf(name.toUpperCase(Locale.ROOT));
    }
}
