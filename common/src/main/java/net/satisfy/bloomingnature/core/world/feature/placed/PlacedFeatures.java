package net.satisfy.bloomingnature.core.world.feature.placed;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.satisfy.bloomingnature.BloomingNature;

public class PlacedFeatures {
    public static final ResourceKey<PlacedFeature> PLAINS_FLOWER_PATCH_PLACED = registerPlacedFeature("flowers/plains_flower_patch_placed");
    public static final ResourceKey<PlacedFeature> PLAINS_TREES_CHECKED = registerPlacedFeature("trees/plains_trees_checked");
    public static final ResourceKey<PlacedFeature> PLAINS_TREES_GROUP_CHECKED = registerPlacedFeature("trees/plains_trees_group_checked");
    public static final ResourceKey<PlacedFeature> RIVER_TREES_CHECKED = registerPlacedFeature("trees/river_trees_checked");
    public static final ResourceKey<PlacedFeature> RIVER_FLOWER_PATCH_PLACED = registerPlacedFeature("flowers/river_flower_patch_placed");
    public static final ResourceKey<PlacedFeature> RIVER_REED_PATCH_PLACED = registerPlacedFeature("vegetation/river_reed_patch_placed");
    public static final ResourceKey<PlacedFeature> FOREST_TREES_CHECKED = registerPlacedFeature("trees/forest_trees_checked");
    public static final ResourceKey<PlacedFeature> FOREST_FLOWER_PATCH_PLACED = registerPlacedFeature("flowers/forest_flower_patch_placed");

    public static final ResourceKey<PlacedFeature> FLOWER_FOREST_FLOWER_PATCH_PLACED = registerPlacedFeature("flowers/flower_forest_flower_patch_placed");
    public static final ResourceKey<PlacedFeature> FLOWER_FOREST_TREES_CHECKED = registerPlacedFeature("trees/flower_forest_trees_checked");

    public static final ResourceKey<PlacedFeature> DENSE_GRASS_PATCH_PLACED = registerPlacedFeature("grass/dense_grass_patch_placed");
    public static final ResourceKey<PlacedFeature> REGULAR_GRASS_PATCH_PLACED = registerPlacedFeature("grass/regular_grass_patch_placed");
    public static final ResourceKey<PlacedFeature> SPARSE_GRASS_PATCH_PLACED = registerPlacedFeature("grass/sparse_grass_patch_placed");
    public static final ResourceKey<PlacedFeature> BLUFF_GRASS_PATCH_PLACED = registerPlacedFeature("grass/bluff_grass_patch_placed");
    public static final ResourceKey<PlacedFeature> DENSE_WILD_GRASS_PATCH_PLACED = registerPlacedFeature("grass/dense_wild_grass_patch_placed");

    public static final ResourceKey<PlacedFeature> OAK_TREE_FALLEN_PLACED = registerPlacedFeature("fallen_trees/oak_tree_fallen_placed");

    public static final ResourceKey<PlacedFeature> STONE_BOULDER_PLACED = registerPlacedFeature("temperate/stone_boulder_placed");
    public static final ResourceKey<PlacedFeature> STONE_SLABS_PLACED = registerPlacedFeature("temperate/stone_slabs_placed");

    public static ResourceKey<PlacedFeature> registerPlacedFeature(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, BloomingNature.identifier(name));
    }
}
