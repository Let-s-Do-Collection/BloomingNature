package net.satisfy.bloomingnature.fabric.core.world;

import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
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

    static Predicate<BiomeSelectionContext> overworld = BiomeSelectors.tag(TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath("minecraft","overworld")));
    static Predicate<BiomeSelectionContext> plains = BiomeSelectors.includeByKey(Biomes.PLAINS);
    static Predicate<BiomeSelectionContext> sunflowerPlains = BiomeSelectors.includeByKey(Biomes.SUNFLOWER_PLAINS  );
    static Predicate<BiomeSelectionContext> river = BiomeSelectors.includeByKey(Biomes.RIVER);
    static Predicate<BiomeSelectionContext> oldGrowthBirchForest = BiomeSelectors.includeByKey(Biomes.OLD_GROWTH_BIRCH_FOREST);
    static Predicate<BiomeSelectionContext> birchForest = BiomeSelectors.includeByKey(Biomes.BIRCH_FOREST);
    static Predicate<BiomeSelectionContext> forest = BiomeSelectors.includeByKey(Biomes.FOREST);
    static Predicate<BiomeSelectionContext> flowerForest = BiomeSelectors.includeByKey(Biomes.FLOWER_FOREST);
    static Predicate<BiomeSelectionContext> cherryGrove = BiomeSelectors.includeByKey(Biomes.CHERRY_GROVE);
    static Predicate<BiomeSelectionContext> snowyPlains= BiomeSelectors.includeByKey(Biomes.SNOWY_PLAINS);
    static Predicate<BiomeSelectionContext> snowyTaiga = BiomeSelectors.includeByKey(Biomes.SNOWY_TAIGA);
    static Predicate<BiomeSelectionContext> darkForest = BiomeSelectors.includeByKey(Biomes.DARK_FOREST);
    static Predicate<BiomeSelectionContext> taiga = BiomeSelectors.includeByKey(Biomes.TAIGA);
    static Predicate<BiomeSelectionContext> oldGrowthSpruceTaiga= BiomeSelectors.includeByKey(Biomes.OLD_GROWTH_SPRUCE_TAIGA);
    static Predicate<BiomeSelectionContext> oldGrowthPineTaiga = BiomeSelectors.includeByKey(Biomes.OLD_GROWTH_PINE_TAIGA);
    static Predicate<BiomeSelectionContext> savanna = BiomeSelectors.includeByKey(Biomes.SAVANNA);
    static Predicate<BiomeSelectionContext> savannaPlateau = BiomeSelectors.includeByKey(Biomes.SAVANNA_PLATEAU);
    static Predicate<BiomeSelectionContext> jungle = BiomeSelectors.includeByKey(Biomes.JUNGLE);
    static Predicate<BiomeSelectionContext> sparseJungle = BiomeSelectors.includeByKey(Biomes.SPARSE_JUNGLE);
    static Predicate<BiomeSelectionContext> swamp = BiomeSelectors.includeByKey(Biomes.SWAMP);
    static Predicate<BiomeSelectionContext> desert = BiomeSelectors.includeByKey(Biomes.DESERT);
    static Predicate<BiomeSelectionContext> beach = BiomeSelectors.includeByKey(Biomes.BEACH);
    static Predicate<BiomeSelectionContext> stonyShore = BiomeSelectors.includeByKey(Biomes.STONY_SHORE);
    static Predicate<BiomeSelectionContext> bambooJungle = BiomeSelectors.includeByKey(Biomes.BAMBOO_JUNGLE);


    public static void registerFeatureAdditions() {
        BiomeModification world = BiomeModifications.create(ResourceLocation.fromNamespaceAndPath(BloomingNature.MOD_ID, "world_features"));

        world.add(ModificationPhase.ADDITIONS, plains, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.REGULAR_GRASS_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, plains, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.PLAINS_FLOWER_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, plains, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.PLAINS_TREES_CHECKED));
        world.add(ModificationPhase.ADDITIONS, plains, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.PLAINS_TREES_GROUP_CHECKED));
        world.add(ModificationPhase.ADDITIONS, plains, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_BOULDER_PLACED));
        world.add(ModificationPhase.ADDITIONS, plains, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_SLABS_PLACED));
        world.add(ModificationPhase.ADDITIONS, plains, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_PILE_PLACED));
        world.add(ModificationPhase.ADDITIONS, plains, context -> context.getEffects().setGrassColor(11063154));
        world.add(ModificationPhase.ADDITIONS, plains, context -> context.getEffects().setFoliageColor(7386187));

        world.add(ModificationPhase.ADDITIONS, river, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.BLUFF_GRASS_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, river, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SPARSE_GRASS_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, river, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.RIVER_FLOWER_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, river, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.RIVER_TREES_CHECKED));
        world.add(ModificationPhase.ADDITIONS, river, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_BOULDER_PLACED));
        world.add(ModificationPhase.ADDITIONS, river, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_SLABS_PLACED));
        world.add(ModificationPhase.ADDITIONS, river, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.RIVER_REED_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, river, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.RIVER_SEAGRASS_PLACED));
        world.add(ModificationPhase.ADDITIONS, river, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_PILE_PLACED));
        world.add(ModificationPhase.ADDITIONS, river, context -> context.getEffects().setGrassColor(11063154));
        world.add(ModificationPhase.ADDITIONS, river, context -> context.getEffects().setFoliageColor(7386187));

        world.add(ModificationPhase.ADDITIONS, sunflowerPlains, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SUNFLOWER_PLAINS_SUNFLOWER_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, sunflowerPlains, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SUNFLOWER_PLAINS_FLOWER_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, sunflowerPlains, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SPARSE_GRASS_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, sunflowerPlains, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.PLAINS_TREES_CHECKED));
        world.add(ModificationPhase.ADDITIONS, sunflowerPlains, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_BOULDER_PLACED));
        world.add(ModificationPhase.ADDITIONS, sunflowerPlains, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_SLABS_PLACED));
        world.add(ModificationPhase.ADDITIONS, sunflowerPlains, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_PILE_PLACED));
        world.add(ModificationPhase.ADDITIONS, sunflowerPlains, context -> context.getEffects().setGrassColor(10799444));
        world.add(ModificationPhase.ADDITIONS, sunflowerPlains, context -> context.getEffects().setFoliageColor(7386187));

        world.add(ModificationPhase.ADDITIONS, forest, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.DENSE_WILD_GRASS_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, forest, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.FOREST_FLOWER_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, forest, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.OAK_TREE_FALLEN_PLACED));
        world.add(ModificationPhase.ADDITIONS, forest, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.FOREST_TREES_CHECKED));
        world.add(ModificationPhase.ADDITIONS, forest, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_BOULDER_PLACED));
        world.add(ModificationPhase.ADDITIONS, forest, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_SLABS_PLACED));
        world.add(ModificationPhase.ADDITIONS, forest, context -> context.getEffects().setGrassColor(10799444));
        world.add(ModificationPhase.ADDITIONS, forest, context -> context.getEffects().setFoliageColor(7386187));

        world.add(ModificationPhase.ADDITIONS, flowerForest, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.DENSE_WILD_GRASS_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, flowerForest, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.FLOWER_FOREST_FLOWER_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, flowerForest, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.FLOWER_FOREST_TREES_CHECKED));
        world.add(ModificationPhase.ADDITIONS, flowerForest, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_BOULDER_PLACED));
        world.add(ModificationPhase.ADDITIONS, flowerForest, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_SLABS_PLACED));
        world.add(ModificationPhase.ADDITIONS, flowerForest, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.OAK_TREE_FALLEN_PLACED));

        world.add(ModificationPhase.ADDITIONS, cherryGrove, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_BOULDER_PLACED));
        world.add(ModificationPhase.ADDITIONS, cherryGrove, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_SLABS_PLACED));
        world.add(ModificationPhase.ADDITIONS, cherryGrove, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.CHERRY_GROVE_FLOWER_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, cherryGrove, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.CHERRY_GROVE_TREES_CHECKED));
        world.add(ModificationPhase.ADDITIONS, cherryGrove, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.BLUFF_GRASS_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, cherryGrove, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.REGULAR_GRASS_PATCH_PLACED));

        world.add(ModificationPhase.ADDITIONS, birchForest, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.REGULAR_GRASS_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, birchForest, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.BIRCH_FOREST_TREES_CHECKED));
        world.add(ModificationPhase.ADDITIONS, birchForest, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.BIRCH_FOREST_FLOWER_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, birchForest, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_BOULDER_PLACED));
        world.add(ModificationPhase.ADDITIONS, birchForest, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_SLABS_PLACED));
        world.add(ModificationPhase.ADDITIONS, birchForest, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.BIRCH_TREE_FALLEN_PLACED));
        world.add(ModificationPhase.ADDITIONS, birchForest, context -> context.getEffects().setGrassColor(10799444));
        world.add(ModificationPhase.ADDITIONS, birchForest, context -> context.getEffects().setFoliageColor(8567370));

        world.add(ModificationPhase.ADDITIONS, oldGrowthBirchForest, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SUNGRASS_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, oldGrowthBirchForest, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.OLD_GROWTH_BIRCH_FOREST_TREES_CHECKED));
        world.add(ModificationPhase.ADDITIONS, oldGrowthBirchForest, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.OLD_GROWTH_BIRCH_FOREST_FLOWER_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, oldGrowthBirchForest, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_BOULDER_PLACED));
        world.add(ModificationPhase.ADDITIONS, oldGrowthBirchForest, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_SLABS_PLACED));
        world.add(ModificationPhase.ADDITIONS, oldGrowthBirchForest, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.BIRCH_TREE_FALLEN_PLACED));
        world.add(ModificationPhase.ADDITIONS, oldGrowthBirchForest, context -> context.getEffects().setGrassColor(14406505));
        world.add(ModificationPhase.ADDITIONS, oldGrowthBirchForest, context -> context.getEffects().setFoliageColor(7386187));

        world.add(ModificationPhase.ADDITIONS, savanna, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.RED_OAT_GRASS_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, savanna, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.DENSE_GRASS_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, savanna, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SAVANNA_TREES_CHECKED));
        world.add(ModificationPhase.ADDITIONS, savanna, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.GRANITE_BOULDERS_PLACED));
        world.add(ModificationPhase.ADDITIONS, savanna, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.GRANITE_SLABS_PLACED));
        world.add(ModificationPhase.ADDITIONS, savanna, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.PACKED_MUD_DISK_PLACED));
        world.add(ModificationPhase.ADDITIONS, savanna, context -> context.getEffects().setGrassColor(15259000));
        world.add(ModificationPhase.ADDITIONS, savanna, context -> context.getEffects().setFoliageColor(10399058));

        world.add(ModificationPhase.ADDITIONS, savannaPlateau, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.DENSE_GRASS_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, savannaPlateau, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SAVANNA_TREES_CHECKED));
        world.add(ModificationPhase.ADDITIONS, savannaPlateau, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.GRANITE_BOULDERS_PLACED));
        world.add(ModificationPhase.ADDITIONS, savannaPlateau, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.GRANITE_SLABS_PLACED));
        world.add(ModificationPhase.ADDITIONS, savannaPlateau, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.PACKED_MUD_DISK_PLACED));
        world.add(ModificationPhase.ADDITIONS, savannaPlateau, context -> context.getEffects().setGrassColor(15259000));
        world.add(ModificationPhase.ADDITIONS, savannaPlateau, context -> context.getEffects().setFoliageColor(10399058));

        world.add(ModificationPhase.ADDITIONS, desert, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SAND_LAYER_0_PLACED));
        world.add(ModificationPhase.ADDITIONS, desert, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SAND_LAYER_1_PLACED));
        world.add(ModificationPhase.ADDITIONS, desert, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SAND_LAYER_2_PLACED));
        world.add(ModificationPhase.ADDITIONS, desert, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SLATE_BOULDER_PLACED));
        world.add(ModificationPhase.ADDITIONS, desert, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SLATE_SLABS_PLACED));
        world.add(ModificationPhase.ADDITIONS, desert, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.CACTUS_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, desert, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.ARID_VEGETATION_PATCH_PLACED));

        world.add(ModificationPhase.ADDITIONS, beach, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SAND_LAYER_0_PLACED));
        world.add(ModificationPhase.ADDITIONS, beach, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SAND_LAYER_1_PLACED));
        world.add(ModificationPhase.ADDITIONS, beach, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SAND_LAYER_2_PLACED));
        world.add(ModificationPhase.ADDITIONS, beach, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.DRY_GRASS_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, beach, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_PILE_PLACED));

        world.add(ModificationPhase.ADDITIONS, stonyShore, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_PILE_PLACED));

        world.add(ModificationPhase.ADDITIONS, darkForest, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.DENSE_WILD_GRASS_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, darkForest, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.DARK_FOREST_FLOWER_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, darkForest, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.DARK_FOREST_TREES_CHECKED));
        world.add(ModificationPhase.ADDITIONS, darkForest, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.DARK_OAK_TREE_FALLEN_PLACED));
        world.add(ModificationPhase.ADDITIONS, darkForest, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_BOULDER_PLACED));
        world.add(ModificationPhase.ADDITIONS, darkForest, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_SLABS_PLACED));
        world.add(ModificationPhase.ADDITIONS, darkForest, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_PILE_PLACED));
        world.add(ModificationPhase.ADDITIONS, darkForest, context -> context.getEffects().setGrassColor(6975545));
        world.add(ModificationPhase.ADDITIONS, darkForest, context -> context.getEffects().setFoliageColor(10399058));

        world.add(ModificationPhase.ADDITIONS, jungle, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.JUNGLE_TREES_CHECKED));
        world.add(ModificationPhase.ADDITIONS, jungle, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.LATERIT_SLABS_PLACED));
        world.add(ModificationPhase.ADDITIONS, jungle, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.WATER_PUDDLE_PLACED));
        world.add(ModificationPhase.ADDITIONS, jungle, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.JUNGLE_FLOWER_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, jungle, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SILKGRASS_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, jungle, context -> context.getEffects().setGrassColor(8174674));
        world.add(ModificationPhase.ADDITIONS, jungle, context -> context.getEffects().setFoliageColor(7516981));

        world.add(ModificationPhase.ADDITIONS, sparseJungle, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SPARSE_JUNGLE_TREES_CHECKED));
        world.add(ModificationPhase.ADDITIONS, sparseJungle, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.WATER_PUDDLE_PLACED));
        world.add(ModificationPhase.ADDITIONS, sparseJungle, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SPARSE_JUNGLE_FLOWER_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, sparseJungle, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_SLABS_PLACED));
        world.add(ModificationPhase.ADDITIONS, sparseJungle, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_BOULDER_PLACED));
        world.add(ModificationPhase.ADDITIONS, sparseJungle, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.RIVER_REED_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, sparseJungle, context -> context.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SILKGRASS_PATCH_PLACED));
        world.add(ModificationPhase.ADDITIONS, sparseJungle, context -> context.getEffects().setGrassColor(8174674));
        world.add(ModificationPhase.ADDITIONS, sparseJungle, context -> context.getEffects().setFoliageColor(7516981));

        world.add(ModificationPhase.ADDITIONS, bambooJungle, context -> context.getEffects().setGrassColor(8174674));
        world.add(ModificationPhase.ADDITIONS, bambooJungle, context -> context.getEffects().setFoliageColor(7516981));
    }

    public static void registerFeatureRemovals() {
        BiomeModification world = BiomeModifications.create(ResourceLocation.fromNamespaceAndPath(BloomingNature.MOD_ID, "world_features_removals"));

        world.add(ModificationPhase.REMOVALS, overworld, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.LAVA_LAKE_SURFACE));
        world.add(ModificationPhase.REMOVALS, overworld, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.LAVA_LAKE_UNDERGROUND));
        world.add(ModificationPhase.REMOVALS, plains, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.TREES_PLAINS));
        world.add(ModificationPhase.REMOVALS, plains, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.FLOWER_PLAINS));
        world.add(ModificationPhase.REMOVALS, sunflowerPlains, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.PATCH_TALL_GRASS_2));
        world.add(ModificationPhase.REMOVALS, sunflowerPlains, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.PATCH_GRASS_PLAINS));
        world.add(ModificationPhase.REMOVALS, sunflowerPlains, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.PATCH_SUNFLOWER));
        world.add(ModificationPhase.REMOVALS, river, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.TREES_WATER));
        world.add(ModificationPhase.REMOVALS, river, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.FLOWER_DEFAULT));
        world.add(ModificationPhase.REMOVALS, oldGrowthBirchForest, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.FLOWER_DEFAULT));
        world.add(ModificationPhase.REMOVALS, oldGrowthBirchForest, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.FOREST_FLOWERS));
        world.add(ModificationPhase.REMOVALS, oldGrowthBirchForest, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.PATCH_GRASS_FOREST));
        world.add(ModificationPhase.REMOVALS, oldGrowthBirchForest, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.BIRCH_TALL));
        world.add(ModificationPhase.REMOVALS, birchForest, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.FLOWER_DEFAULT));
        world.add(ModificationPhase.REMOVALS, birchForest, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.FOREST_FLOWERS));
        world.add(ModificationPhase.REMOVALS, birchForest, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.TREES_BIRCH));
        world.add(ModificationPhase.REMOVALS, forest, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.FOREST_FLOWERS));
        world.add(ModificationPhase.REMOVALS, forest, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.TREES_BIRCH_AND_OAK));
        world.add(ModificationPhase.REMOVALS, flowerForest, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.FLOWER_FOREST_FLOWERS));
        world.add(ModificationPhase.REMOVALS, flowerForest, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.FLOWER_FLOWER_FOREST));
        world.add(ModificationPhase.REMOVALS, flowerForest, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.TREES_FLOWER_FOREST));
        world.add(ModificationPhase.REMOVALS, cherryGrove, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.FLOWER_CHERRY));
        world.add(ModificationPhase.REMOVALS, cherryGrove, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.TREES_CHERRY));
        world.add(ModificationPhase.REMOVALS, snowyPlains, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.FLOWER_DEFAULT));
        world.add(ModificationPhase.REMOVALS, snowyPlains, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.TREES_SNOWY));
        world.add(ModificationPhase.REMOVALS, snowyTaiga, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.SNOWY_TAIGA_TREES));
        world.add(ModificationPhase.REMOVALS, darkForest, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.DARK_FOREST_VEGETATION));
        world.add(ModificationPhase.REMOVALS, taiga, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.TREES_TAIGA));
        world.add(ModificationPhase.REMOVALS, taiga, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.PATCH_LARGE_FERN));
        world.add(ModificationPhase.REMOVALS, taiga, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.PATCH_GRASS_TAIGA_2));
        world.add(ModificationPhase.REMOVALS, taiga, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.FLOWER_DEFAULT));
        world.add(ModificationPhase.REMOVALS, oldGrowthSpruceTaiga, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.TREES_OLD_GROWTH_PINE_TAIGA));
        world.add(ModificationPhase.REMOVALS, oldGrowthSpruceTaiga, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.PATCH_LARGE_FERN));
        world.add(ModificationPhase.REMOVALS, oldGrowthSpruceTaiga, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.PATCH_GRASS_TAIGA));
        world.add(ModificationPhase.REMOVALS, oldGrowthSpruceTaiga, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.FLOWER_DEFAULT));
        world.add(ModificationPhase.REMOVALS, oldGrowthPineTaiga, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.TREES_OLD_GROWTH_PINE_TAIGA));
        world.add(ModificationPhase.REMOVALS, oldGrowthPineTaiga, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.PATCH_LARGE_FERN));
        world.add(ModificationPhase.REMOVALS, oldGrowthPineTaiga, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.PATCH_GRASS_TAIGA));
        world.add(ModificationPhase.REMOVALS, oldGrowthPineTaiga, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.FLOWER_DEFAULT));
        world.add(ModificationPhase.REMOVALS, savanna, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.TREES_SAVANNA));
        world.add(ModificationPhase.REMOVALS, savanna, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.PATCH_GRASS_SAVANNA));
        world.add(ModificationPhase.REMOVALS, savanna, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.BROWN_MUSHROOM_NORMAL));
        world.add(ModificationPhase.REMOVALS, savanna, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.RED_MUSHROOM_NORMAL));
        world.add(ModificationPhase.REMOVALS, savanna, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.FLOWER_WARM));
        world.add(ModificationPhase.REMOVALS, savannaPlateau, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.TREES_SAVANNA));
        world.add(ModificationPhase.REMOVALS, savannaPlateau, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.PATCH_GRASS_SAVANNA));
        world.add(ModificationPhase.REMOVALS, savannaPlateau, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.BROWN_MUSHROOM_NORMAL));
        world.add(ModificationPhase.REMOVALS, savannaPlateau, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.RED_MUSHROOM_NORMAL));
        world.add(ModificationPhase.REMOVALS, savannaPlateau, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.FLOWER_WARM));
        world.add(ModificationPhase.REMOVALS, jungle, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.TREES_JUNGLE));
        world.add(ModificationPhase.REMOVALS, jungle, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.FLOWER_WARM));
        world.add(ModificationPhase.REMOVALS, jungle, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.PATCH_GRASS_JUNGLE));
        world.add(ModificationPhase.REMOVALS, jungle, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.BAMBOO_LIGHT));
        world.add(ModificationPhase.REMOVALS, sparseJungle, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.TREES_SPARSE_JUNGLE));
        world.add(ModificationPhase.REMOVALS, sparseJungle, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.PATCH_GRASS_JUNGLE));
        world.add(ModificationPhase.REMOVALS, sparseJungle, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.FLOWER_WARM));
        world.add(ModificationPhase.REMOVALS, swamp, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.TREES_SWAMP));
        world.add(ModificationPhase.REMOVALS, swamp, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.FLOWER_SWAMP));
        world.add(ModificationPhase.REMOVALS, swamp, context -> context.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, RemovedPlacedFeatures.PATCH_GRASS_NORMAL));
    }
}