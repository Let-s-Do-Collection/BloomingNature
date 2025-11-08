package net.satisfy.bloomingnature.core.world.feature.placed;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class RemovedPlacedFeatures {
    public static final ResourceKey<PlacedFeature> FLOWER_DEFAULT = registerMCKey("flower_default");
    public static final ResourceKey<PlacedFeature> LAVA_LAKE_UNDERGROUND = registerMCKey("lake_lava_underground");
    public static final ResourceKey<PlacedFeature> LAVA_LAKE_SURFACE = registerMCKey("lake_lava_surface");
    public static final ResourceKey<PlacedFeature> TREES_PLAINS = registerMCKey("trees_plains");
    public static final ResourceKey<PlacedFeature> FLOWER_PLAINS = registerMCKey("flower_plains");
    public static final ResourceKey<PlacedFeature> PATCH_TALL_GRASS_2 = registerMCKey("patch_tall_grass_2");
    public static final ResourceKey<PlacedFeature> PATCH_GRASS_PLAINS = registerMCKey("patch_grass_plain");
    public static final ResourceKey<PlacedFeature> PATCH_SUNFLOWER = registerMCKey("patch_sunflower");
    public static final ResourceKey<PlacedFeature> TREES_WATER = registerMCKey("trees_water");
    public static final ResourceKey<PlacedFeature> BIRCH_TALL = registerMCKey("birch_tall");
    public static final ResourceKey<PlacedFeature> PATCH_GRASS_FOREST = registerMCKey("patch_grass_forest");
    public static final ResourceKey<PlacedFeature> FOREST_FLOWERS = registerMCKey("forest_flowers");
    public static final ResourceKey<PlacedFeature> TREES_BIRCH = registerMCKey("trees_birch");
    public static final ResourceKey<PlacedFeature> TREES_BIRCH_AND_OAK = registerMCKey("trees_birch_and_oak");
    public static final ResourceKey<PlacedFeature> TREES_FLOWER_FOREST = registerMCKey("trees_flower_forest");
    public static final ResourceKey<PlacedFeature> FLOWER_FLOWER_FOREST = registerMCKey("flower_flower_forest");
    public static final ResourceKey<PlacedFeature> FLOWER_FOREST_FLOWERS = registerMCKey("flower_forest_flowers");
    public static final ResourceKey<PlacedFeature> TREES_CHERRY = registerMCKey("trees_cherry");
    public static final ResourceKey<PlacedFeature> FLOWER_CHERRY = registerMCKey("flower_cherry");
    public static final ResourceKey<PlacedFeature> TREES_SNOWY = registerMCKey("trees_snowy");
    public static final ResourceKey<PlacedFeature> SNOWY_TAIGA_TREES = registerMCKey("trees_taiga");
    public static final ResourceKey<PlacedFeature> TREES_SWAMP = registerMCKey("trees_swamp");
    public static final ResourceKey<PlacedFeature> FLOWER_SWAMP = registerMCKey("flower_swamp");
    public static final ResourceKey<PlacedFeature> PATCH_GRASS_NORMAL = registerMCKey("patch_grass_normal");
    public static final ResourceKey<PlacedFeature> PATCH_LARGE_FERN = registerMCKey("patch_large_fern");
    public static final ResourceKey<PlacedFeature> TREES_TAIGA = registerMCKey("trees_taiga");
    public static final ResourceKey<PlacedFeature> PATCH_GRASS_TAIGA_2 = registerMCKey("patch_grass_taiga_2");
    public static final ResourceKey<PlacedFeature> TREES_OLD_GROWTH_PINE_TAIGA = registerMCKey("trees_old_growth_pine_taiga");
    public static final ResourceKey<PlacedFeature> PATCH_GRASS_TAIGA = registerMCKey("patch_grass_taiga");
    public static final ResourceKey<PlacedFeature> DARK_FOREST_VEGETATION = registerMCKey("dark_forest_vegetation");
    public static final ResourceKey<PlacedFeature> TREES_SAVANNA = registerMCKey("trees_savanna");
    public static final ResourceKey<PlacedFeature> PATCH_GRASS_SAVANNA = registerMCKey("patch_grass_savanna");
    public static final ResourceKey<PlacedFeature> BROWN_MUSHROOM_NORMAL = registerMCKey("brown_mushroom_normal");
    public static final ResourceKey<PlacedFeature> RED_MUSHROOM_NORMAL = registerMCKey("red_mushroom_normal");
    public static final ResourceKey<PlacedFeature> FLOWER_WARM = registerMCKey("flower_warm");
    public static final ResourceKey<PlacedFeature> TREES_JUNGLE = registerMCKey("trees_jungle");
    public static final ResourceKey<PlacedFeature> BAMBOO_LIGHT = registerMCKey("bamboo_light");
    public static final ResourceKey<PlacedFeature> PATCH_GRASS_JUNGLE = registerMCKey("patch_grass_jungle");
    public static final ResourceKey<PlacedFeature> TREES_SPARSE_JUNGLE = registerMCKey("trees_sparse_jungle");

    public static ResourceKey<PlacedFeature> registerMCKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.withDefaultNamespace(name));
    }
}