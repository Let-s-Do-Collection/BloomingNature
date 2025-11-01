package net.satisfy.bloomingnature.fabric.core.world;

import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.satisfy.bloomingnature.BloomingNature;
import net.satisfy.bloomingnature.core.world.feature.placed.PlacedFeatures;
import net.satisfy.bloomingnature.core.world.feature.placed.RemovedPlacedFeatures;

import java.util.function.Predicate;

public final class BloomingNatureFabricWorldgen {

    public static void init() {
        registerFeatureAdditions();
        registerFeatureRemovals();
    }

    public static void registerFeatureAdditions() {
        BiomeModification world = BiomeModifications.create(ResourceLocation.fromNamespaceAndPath(BloomingNature.MOD_ID, "world_features"));
        Predicate<BiomeSelectionContext> plains = BiomeSelectors.includeByKey(Biomes.PLAINS);
        Predicate<BiomeSelectionContext> river = BiomeSelectors.includeByKey(Biomes.RIVER);
        Predicate<BiomeSelectionContext> forest = BiomeSelectors.includeByKey(Biomes.FOREST);
        Predicate<BiomeSelectionContext> flowerForest = BiomeSelectors.includeByKey(Biomes.FLOWER_FOREST);
        Predicate<BiomeSelectionContext> birchForest = BiomeSelectors.includeByKey(Biomes.BIRCH_FOREST);
        Predicate<BiomeSelectionContext> oldgrowthbirchForest = BiomeSelectors.includeByKey(Biomes.OLD_GROWTH_BIRCH_FOREST);
        Predicate<BiomeSelectionContext> savanna = BiomeSelectors.includeByKey(Biomes.SAVANNA);
        Predicate<BiomeSelectionContext> savannaPlateau = BiomeSelectors.includeByKey(Biomes.SAVANNA_PLATEAU);
        Predicate<BiomeSelectionContext> cherryGrove = BiomeSelectors.includeByKey(Biomes.CHERRY_GROVE);

        world.add(ModificationPhase.ADDITIONS, plains, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.REGULAR_GRASS_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, plains, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.PLAINS_FLOWER_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, plains, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.PLAINS_TREES_CHECKED));
        world.add(ModificationPhase.ADDITIONS, plains, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.PLAINS_TREES_GROUP_CHECKED));
        world.add(ModificationPhase.ADDITIONS, plains, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_BOULDER_PLACED));
        world.add(ModificationPhase.ADDITIONS, plains, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_SLABS_PLACED));
        world.add(ModificationPhase.ADDITIONS, plains, ctx -> ctx.getEffects().setGrassColor(11063154));
        world.add(ModificationPhase.ADDITIONS, plains, ctx -> ctx.getEffects().setFoliageColor(7386187));

        world.add(ModificationPhase.ADDITIONS, river, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.BLUFF_GRASS_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, river, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SPARSE_GRASS_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, river, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.RIVER_FLOWER_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, river, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.RIVER_TREES_CHECKED));
        world.add(ModificationPhase.ADDITIONS, river, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.RIVER_REED_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, river, ctx -> ctx.getEffects().setGrassColor(11063154));
        world.add(ModificationPhase.ADDITIONS, river, ctx -> ctx.getEffects().setFoliageColor(7386187));

        world.add(ModificationPhase.ADDITIONS, forest, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.DENSE_WILD_GRASS_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, forest, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.FOREST_FLOWER_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, forest, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.OAK_TREE_FALLEN_PLACED));
        world.add(ModificationPhase.ADDITIONS, forest, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.FOREST_TREES_CHECKED));
        world.add(ModificationPhase.ADDITIONS, forest, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_BOULDER_PLACED));
        world.add(ModificationPhase.ADDITIONS, forest, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_SLABS_PLACED));
        world.add(ModificationPhase.ADDITIONS, forest, ctx -> ctx.getEffects().setGrassColor(10799444));
        world.add(ModificationPhase.ADDITIONS, forest, ctx -> ctx.getEffects().setFoliageColor(7386187));

        world.add(ModificationPhase.ADDITIONS, flowerForest, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.DENSE_WILD_GRASS_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, flowerForest, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.FLOWER_FOREST_FLOWER_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, flowerForest, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.FLOWER_FOREST_TREES_CHECKED));
        world.add(ModificationPhase.ADDITIONS, flowerForest, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_BOULDER_PLACED));
        world.add(ModificationPhase.ADDITIONS, flowerForest, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_SLABS_PLACED));
        world.add(ModificationPhase.ADDITIONS, flowerForest, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.OAK_TREE_FALLEN_PLACED));

        world.add(ModificationPhase.ADDITIONS, cherryGrove, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_BOULDER_PLACED));
        world.add(ModificationPhase.ADDITIONS, cherryGrove, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_SLABS_PLACED));
        world.add(ModificationPhase.ADDITIONS, cherryGrove, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.CHERRY_GROVE_FLOWER_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, cherryGrove, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.CHERRY_GROVE_TREES_CHECKED));
        world.add(ModificationPhase.ADDITIONS, cherryGrove, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.BLUFF_GRASS_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, cherryGrove, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.REGULAR_GRASS_PATCH_PLACED));

        world.add(ModificationPhase.ADDITIONS, birchForest, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.REGULAR_GRASS_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, birchForest, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.BIRCH_FOREST_TREES_CHECKED));
        world.add(ModificationPhase.ADDITIONS, birchForest, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.BIRCH_FOREST_FLOWER_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, birchForest, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_BOULDER_PLACED));
        world.add(ModificationPhase.ADDITIONS, birchForest, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_SLABS_PLACED));
        world.add(ModificationPhase.ADDITIONS, birchForest, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.BIRCH_TREE_FALLEN_PLACED));
        world.add(ModificationPhase.ADDITIONS, birchForest, ctx -> ctx.getEffects().setGrassColor(10799444));
        world.add(ModificationPhase.ADDITIONS, birchForest, ctx -> ctx.getEffects().setFoliageColor(8567370));

        world.add(ModificationPhase.ADDITIONS, oldgrowthbirchForest, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SUNGRASS_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, oldgrowthbirchForest, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.OLD_GROWTH_BIRCH_FOREST_TREES_CHECKED));
        world.add(ModificationPhase.ADDITIONS, oldgrowthbirchForest, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.OLD_GROWTH_BIRCH_FOREST_FLOWER_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, oldgrowthbirchForest, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_BOULDER_PLACED));
        world.add(ModificationPhase.ADDITIONS, oldgrowthbirchForest, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_SLABS_PLACED));
        world.add(ModificationPhase.ADDITIONS, oldgrowthbirchForest, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.BIRCH_TREE_FALLEN_PLACED));
        world.add(ModificationPhase.ADDITIONS, oldgrowthbirchForest, ctx -> ctx.getEffects().setGrassColor(14406505));
        world.add(ModificationPhase.ADDITIONS, oldgrowthbirchForest, ctx -> ctx.getEffects().setFoliageColor(7386187));

        world.add(ModificationPhase.ADDITIONS, savanna, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.RED_OAT_GRASS_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, savanna, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.DENSE_GRASS_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, savanna, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SAVANNA_TREES_CHECKED));
        world.add(ModificationPhase.ADDITIONS, savanna, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.GRANITE_BOULDERS_PLACED));
        world.add(ModificationPhase.ADDITIONS, savanna, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.GRANITE_SLABS_PLACED));
        world.add(ModificationPhase.ADDITIONS, savanna, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.PACKED_MUD_DISK_PLACED));
        world.add(ModificationPhase.ADDITIONS, savanna, ctx -> ctx.getEffects().setGrassColor(15259000));
        world.add(ModificationPhase.ADDITIONS, savanna, ctx -> ctx.getEffects().setFoliageColor(10399058));

        world.add(ModificationPhase.ADDITIONS, savannaPlateau, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.DENSE_GRASS_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, savannaPlateau, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SAVANNA_TREES_CHECKED));
        world.add(ModificationPhase.ADDITIONS, savannaPlateau, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.GRANITE_BOULDERS_PLACED));
        world.add(ModificationPhase.ADDITIONS, savannaPlateau, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.GRANITE_SLABS_PLACED));
        world.add(ModificationPhase.ADDITIONS, savannaPlateau, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.PACKED_MUD_DISK_PLACED));
        world.add(ModificationPhase.ADDITIONS, savannaPlateau, ctx -> ctx.getEffects().setGrassColor(15259000));
        world.add(ModificationPhase.ADDITIONS, savannaPlateau, ctx -> ctx.getEffects().setFoliageColor(10399058));

    }

    public static void registerFeatureRemovals() {
        BiomeModification world = BiomeModifications.create(ResourceLocation.fromNamespaceAndPath(BloomingNature.MOD_ID, "world_features_removals"));

        Predicate<BiomeSelectionContext> overworld = BiomeSelectors.tag(TagKey.create(Registries.BIOME, BloomingNature.identifier("overworld")));
        Predicate<BiomeSelectionContext> plains = BiomeSelectors.tag(TagKey.create(Registries.BIOME, BloomingNature.identifier("plains")));
        Predicate<BiomeSelectionContext> sunflower_plains = BiomeSelectors.tag(TagKey.create(Registries.BIOME, BloomingNature.identifier("sunflower_plains")));
        Predicate<BiomeSelectionContext> river = BiomeSelectors.tag(TagKey.create(Registries.BIOME, BloomingNature.identifier("river")));
        Predicate<BiomeSelectionContext> old_growth_birch = BiomeSelectors.tag(TagKey.create(Registries.BIOME, BloomingNature.identifier("aspen")));
        Predicate<BiomeSelectionContext> birch = BiomeSelectors.tag(TagKey.create(Registries.BIOME, BloomingNature.identifier("birch")));
        Predicate<BiomeSelectionContext> forest = BiomeSelectors.tag(TagKey.create(Registries.BIOME, BloomingNature.identifier("forest")));
        Predicate<BiomeSelectionContext> flower_forest = BiomeSelectors.tag(TagKey.create(Registries.BIOME, BloomingNature.identifier("flower_forest")));
        Predicate<BiomeSelectionContext> cherry_grove = BiomeSelectors.tag(TagKey.create(Registries.BIOME, BloomingNature.identifier("cherry_grove")));
        Predicate<BiomeSelectionContext> snowy_plains = BiomeSelectors.tag(TagKey.create(Registries.BIOME, BloomingNature.identifier("snowy_plains")));
        Predicate<BiomeSelectionContext> snowy_taiga = BiomeSelectors.tag(TagKey.create(Registries.BIOME, BloomingNature.identifier("snowy_taiga")));
        Predicate<BiomeSelectionContext> dark_forest = BiomeSelectors.tag(TagKey.create(Registries.BIOME, BloomingNature.identifier("dark_forest")));
        Predicate<BiomeSelectionContext> taiga = BiomeSelectors.tag(TagKey.create(Registries.BIOME, BloomingNature.identifier("taiga")));
        Predicate<BiomeSelectionContext> old_growth_spruce_taiga = BiomeSelectors.tag(TagKey.create(Registries.BIOME, BloomingNature.identifier("old_growth_spruce_taiga")));
        Predicate<BiomeSelectionContext> old_growth_pine_taiga = BiomeSelectors.tag(TagKey.create(Registries.BIOME, BloomingNature.identifier("old_growth_pine_taiga")));
        Predicate<BiomeSelectionContext> savanna = BiomeSelectors.tag(TagKey.create(Registries.BIOME, BloomingNature.identifier("savanna")));
        Predicate<BiomeSelectionContext> savanna_plateau = BiomeSelectors.tag(TagKey.create(Registries.BIOME, BloomingNature.identifier("savanna_plateau")));
        Predicate<BiomeSelectionContext> jungle = BiomeSelectors.tag(TagKey.create(Registries.BIOME, BloomingNature.identifier("jungle")));
        Predicate<BiomeSelectionContext> sparse_jungle = BiomeSelectors.tag(TagKey.create(Registries.BIOME, BloomingNature.identifier("sparse_jungle")));
        Predicate<BiomeSelectionContext> swamp = BiomeSelectors.tag(TagKey.create(Registries.BIOME, BloomingNature.identifier("swamp")));

        world.add(ModificationPhase.REMOVALS, overworld, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.LAVA_LAKE_SURFACE));
        world.add(ModificationPhase.REMOVALS, overworld, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.LAVA_LAKE_UNDERGROUND));
        world.add(ModificationPhase.REMOVALS, plains, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.TREES_PLAINS));
        world.add(ModificationPhase.REMOVALS, plains, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.FLOWER_PLAINS));
        world.add(ModificationPhase.REMOVALS, sunflower_plains, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.PATCH_TALL_GRASS_2));
        world.add(ModificationPhase.REMOVALS, sunflower_plains, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.SUNFLOWER_PLAINS_GRASS));
        world.add(ModificationPhase.REMOVALS, river, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.TREES_WATER));
        world.add(ModificationPhase.REMOVALS, river, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.FLOWER_DEFAULT));
        world.add(ModificationPhase.REMOVALS, old_growth_birch, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.FLOWER_DEFAULT));
        world.add(ModificationPhase.REMOVALS, old_growth_birch, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.FOREST_FLOWERS));
        world.add(ModificationPhase.REMOVALS, old_growth_birch, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.PATCH_GRASS_FOREST));
        world.add(ModificationPhase.REMOVALS, old_growth_birch, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.BIRCH_TALL));
        world.add(ModificationPhase.REMOVALS, birch, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.FLOWER_DEFAULT));
        world.add(ModificationPhase.REMOVALS, birch, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.FOREST_FLOWERS));
        world.add(ModificationPhase.REMOVALS, birch, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.TREES_BIRCH));
        world.add(ModificationPhase.REMOVALS, forest, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.FOREST_FLOWERS));
        world.add(ModificationPhase.REMOVALS, forest, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.TREES_BIRCH_AND_OAK));
        world.add(ModificationPhase.REMOVALS, flower_forest, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.FLOWER_FOREST_FLOWERS));
        world.add(ModificationPhase.REMOVALS, flower_forest, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.FLOWER_FLOWER_FOREST));
        world.add(ModificationPhase.REMOVALS, flower_forest, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.TREES_FLOWER_FOREST));
        world.add(ModificationPhase.REMOVALS, cherry_grove, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.FLOWER_CHERRY));
        world.add(ModificationPhase.REMOVALS, cherry_grove, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.TREES_CHERRY));
        world.add(ModificationPhase.REMOVALS, snowy_plains, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.FLOWER_DEFAULT));
        world.add(ModificationPhase.REMOVALS, snowy_plains, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.TREES_SNOWY));
        world.add(ModificationPhase.REMOVALS, snowy_taiga, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.SNOWY_TAIGA_TREES));
        world.add(ModificationPhase.REMOVALS, dark_forest, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.DARK_FOREST_VEGETATION));
        world.add(ModificationPhase.REMOVALS, taiga, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.TREES_TAIGA));
        world.add(ModificationPhase.REMOVALS, taiga, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.PATCH_LARGE_FERN));
        world.add(ModificationPhase.REMOVALS, taiga, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.PATCH_GRASS_TAIGA_2));
        world.add(ModificationPhase.REMOVALS, taiga, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.FLOWER_DEFAULT));
        world.add(ModificationPhase.REMOVALS, old_growth_spruce_taiga, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.TREES_OLD_GROWTH_PINE_TAIGA));
        world.add(ModificationPhase.REMOVALS, old_growth_spruce_taiga, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.PATCH_LARGE_FERN));
        world.add(ModificationPhase.REMOVALS, old_growth_spruce_taiga, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.PATCH_GRASS_TAIGA));
        world.add(ModificationPhase.REMOVALS, old_growth_spruce_taiga, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.FLOWER_DEFAULT));
        world.add(ModificationPhase.REMOVALS, old_growth_pine_taiga, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.TREES_OLD_GROWTH_PINE_TAIGA));
        world.add(ModificationPhase.REMOVALS, old_growth_pine_taiga, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.PATCH_LARGE_FERN));
        world.add(ModificationPhase.REMOVALS, old_growth_pine_taiga, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.PATCH_GRASS_TAIGA));
        world.add(ModificationPhase.REMOVALS, old_growth_pine_taiga, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.FLOWER_DEFAULT));
        world.add(ModificationPhase.REMOVALS, savanna, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.TREES_SAVANNA));
        world.add(ModificationPhase.REMOVALS, savanna, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.PATCH_GRASS_SAVANNA));
        world.add(ModificationPhase.REMOVALS, savanna, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.BROWN_MUSHROOM_NORMAL));
        world.add(ModificationPhase.REMOVALS, savanna, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.RED_MUSHROOM_NORMAL));
        world.add(ModificationPhase.REMOVALS, savanna, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.FLOWER_WARM));
        world.add(ModificationPhase.REMOVALS, savanna_plateau, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.TREES_SAVANNA));
        world.add(ModificationPhase.REMOVALS, savanna_plateau, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.PATCH_GRASS_SAVANNA));
        world.add(ModificationPhase.REMOVALS, savanna_plateau, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.BROWN_MUSHROOM_NORMAL));
        world.add(ModificationPhase.REMOVALS, savanna_plateau, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.RED_MUSHROOM_NORMAL));
        world.add(ModificationPhase.REMOVALS, savanna_plateau, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.FLOWER_WARM));
        world.add(ModificationPhase.REMOVALS, jungle, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.TREES_JUNGLE));
        world.add(ModificationPhase.REMOVALS, jungle, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.FLOWER_WARM));
        world.add(ModificationPhase.REMOVALS, jungle, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.PATCH_GRASS_JUNGLE));
        world.add(ModificationPhase.REMOVALS, jungle, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.BAMBOO_LIGHT));
        world.add(ModificationPhase.REMOVALS, sparse_jungle, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.TREES_SPARSE_JUNGLE));
        world.add(ModificationPhase.REMOVALS, sparse_jungle, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.PATCH_GRASS_JUNGLE));
        world.add(ModificationPhase.REMOVALS, sparse_jungle, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.FLOWER_WARM));
        world.add(ModificationPhase.REMOVALS, swamp, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.TREES_SWAMP));
        world.add(ModificationPhase.REMOVALS, swamp, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.FLOWER_SWAMP));
        world.add(ModificationPhase.REMOVALS, swamp, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.PATCH_GRASS_NORMAL));
    }
}