package satisfy.bloomingnature.fabric;

import com.google.common.base.Preconditions;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.*;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import satisfy.bloomingnature.BloomingNature;
import satisfy.bloomingnature.registry.CompostableRegistry;
import satisfy.bloomingnature.registry.EntityRegistry;
import satisfy.bloomingnature.registry.TagsRegistry;
import satisfy.bloomingnature.util.BloomingNatureIdentifier;
import satisfy.bloomingnature.world.PlacedFeatures;

import java.util.List;
import java.util.function.Predicate;

public class BloomingNatureFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        BloomingNature.init();
        BloomingNature.commonInit();
        CompostableRegistry.init();
        addSpawns();
        //addBiomeModification(); - TODO fixme datapacks
    }

    void addSpawns() {
        addMobSpawn(TagsRegistry.SPAWNS_DEER, EntityRegistry.DEER.get(), 12, 2, 4);
        addMobSpawn(BiomeTags.IS_BEACH, EntityRegistry.PELICAN.get(), 5, 3, 4);
        addMobSpawn(BiomeTags.HAS_WOODLAND_MANSION, EntityType.EVOKER, 4, 1, 2);
        addMobSpawn(BiomeTags.HAS_WOODLAND_MANSION, EntityType.VINDICATOR, 4, 1, 2);
        addMobSpawn(BiomeTags.HAS_WOODLAND_MANSION, EntityType.PILLAGER, 4, 1, 3);
        addMobSpawn(TagsRegistry.SPAWNS_MUDDY_PIG, EntityRegistry.MUDDY_PIG.get(), 8, 4, 5);
        addMobSpawn(TagsRegistry.SPAWNS_MOSSY_SHEEP, EntityRegistry.MOSSY_SHEEP.get(), 8, 3, 5);
        addMobSpawn(TagsRegistry.SPAWNS_RACCOON, EntityRegistry.RACCOON.get(), 8, 2, 3);
        addMobSpawn(TagsRegistry.SPAWNS_SQUIRREL, EntityRegistry.SQUIRREL.get(), 8, 2, 2);
        addMobSpawn(TagsRegistry.SPAWNS_RED_WOLF, EntityRegistry.RED_WOLF.get(), 10, 3, 4);
        addMobSpawn(TagsRegistry.SPAWNS_OWL, EntityRegistry.OWL.get(), 12, 3, 3);
        addMobSpawn(TagsRegistry.SPAWNS_BOAR, EntityRegistry.BOAR.get(), 14, 5, 5);
        addMobSpawn(TagsRegistry.SPAWNS_BISON, EntityRegistry.BISON.get(), 10, 3, 5);
        addMobSpawn(TagsRegistry.SPAWNS_TURKEY, EntityRegistry.TURKEY.get(), 12, 3, 5);
        removeSpawn(BiomeTags.IS_SAVANNA, List.of(EntityType.SHEEP, EntityType.PIG, EntityType.CHICKEN, EntityType.COW));
        removeSpawn(ConventionalBiomeTags.SWAMP, List.of(EntityType.SHEEP, EntityType.PIG, EntityType.CHICKEN, EntityType.COW));
        removeSpawn(BiomeTags.IS_FOREST, List.of(EntityType.PIG, EntityType.CHICKEN));
        removeSpawn(BiomeTags.IS_JUNGLE, List.of(EntityType.PIG, EntityType.CHICKEN, EntityType.COW));
        addMobSpawn(BiomeTags.IS_JUNGLE, EntityType.FROG, 8, 3, 4);
        addMobSpawn(BiomeTags.IS_SAVANNA, EntityRegistry.TERMITE.get(), 10, 3, 4);

        SpawnPlacements.register(EntityRegistry.SQUIRREL.get(), SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AmbientCreature::checkMobSpawnRules);
        SpawnPlacements.register(EntityRegistry.TERMITE.get(), SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AmbientCreature::checkMobSpawnRules);
        SpawnPlacements.register(EntityRegistry.OWL.get(), SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, AmbientCreature::checkMobSpawnRules);
        SpawnPlacements.register(EntityRegistry.TURKEY.get(), SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AmbientCreature::checkMobSpawnRules);
        SpawnPlacements.register(EntityRegistry.RACCOON.get(), SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AmbientCreature::checkMobSpawnRules);
        SpawnPlacements.register(EntityRegistry.PELICAN.get(), SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AmbientCreature::checkMobSpawnRules);
        SpawnPlacements.register(EntityRegistry.MUDDY_PIG.get(), SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AmbientCreature::checkMobSpawnRules);
        SpawnPlacements.register(EntityRegistry.DEER.get(), SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AmbientCreature::checkMobSpawnRules);
        SpawnPlacements.register(EntityRegistry.MOSSY_SHEEP.get(), SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AmbientCreature::checkMobSpawnRules);
        SpawnPlacements.register(EntityRegistry.RED_WOLF.get(), SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AmbientCreature::checkMobSpawnRules);
        SpawnPlacements.register(EntityRegistry.BOAR.get(), SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AmbientCreature::checkMobSpawnRules);
        SpawnPlacements.register(EntityRegistry.BISON.get(), SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AmbientCreature::checkMobSpawnRules);
    }

    void addMobSpawn(TagKey<Biome> tag, EntityType<?> entityType, int weight, int minGroupSize, int maxGroupSize) {
        BiomeModifications.addSpawn(biomeSelector -> biomeSelector.hasTag(tag), MobCategory.CREATURE, entityType, weight, minGroupSize, maxGroupSize);
    }

    void removeSpawn(TagKey<Biome> tag, List<EntityType<?>> entityTypes) {
        entityTypes.forEach(entityType -> {
            ResourceLocation id = BuiltInRegistries.ENTITY_TYPE.getKey(entityType);
            Preconditions.checkState(BuiltInRegistries.ENTITY_TYPE.containsKey(id), "Unregistered entity type: %s", entityType);
            BiomeModifications.create(id).add(ModificationPhase.REMOVALS, biomeSelector -> biomeSelector.hasTag(tag), context -> context.getSpawnSettings().removeSpawnsOfEntityType(entityType));
        });
    }

    void addBiomeModification() {
        BiomeModification world = BiomeModifications.create(BloomingNatureIdentifier.of("world_features"));
        Predicate<BiomeSelectionContext> overworld = getBloomingNatureSelector("overworld");
        Predicate<BiomeSelectionContext> plains = getBloomingNatureSelector("plains");
        Predicate<BiomeSelectionContext> aspen = getBloomingNatureSelector("aspen");
        Predicate<BiomeSelectionContext> birch = getBloomingNatureSelector("birch");
        Predicate<BiomeSelectionContext> forest = getBloomingNatureSelector("forest");
        Predicate<BiomeSelectionContext> flower_forest = getBloomingNatureSelector("flower_forest");
        Predicate<BiomeSelectionContext> river = getBloomingNatureSelector("river");
        Predicate<BiomeSelectionContext> cherry_grove = getBloomingNatureSelector("cherry_grove");
        Predicate<BiomeSelectionContext> snowy_plains = getBloomingNatureSelector("snowy_plains");
        Predicate<BiomeSelectionContext> snowy_slopes = getBloomingNatureSelector("snowy_slopes");
        Predicate<BiomeSelectionContext> snowy_taiga = getBloomingNatureSelector("snowy_taiga");
        Predicate<BiomeSelectionContext> beach = getBloomingNatureSelector("beach");
        Predicate<BiomeSelectionContext> stony_shore = getBloomingNatureSelector("stony_shore");
        Predicate<BiomeSelectionContext> jungle = getBloomingNatureSelector("jungle");
        Predicate<BiomeSelectionContext> sparse_jungle = getBloomingNatureSelector("sparse_jungle");
        Predicate<BiomeSelectionContext> desert = getBloomingNatureSelector("desert");
        Predicate<BiomeSelectionContext> swamp = getBloomingNatureSelector("swamp");
        Predicate<BiomeSelectionContext> dark_forest = getBloomingNatureSelector("dark_forest");
        Predicate<BiomeSelectionContext> mangrove_swamp = getBloomingNatureSelector("mangrove_swamp");
        Predicate<BiomeSelectionContext> sunflower_plains = getBloomingNatureSelector("sunflower_plains");
        Predicate<BiomeSelectionContext> taiga = getBloomingNatureSelector("taiga");
        Predicate<BiomeSelectionContext> old_growth_spruce_taiga = getBloomingNatureSelector("old_growth_spruce_taiga");
        Predicate<BiomeSelectionContext> old_growth_pine_taiga = getBloomingNatureSelector("old_growth_pine_taiga");
        Predicate<BiomeSelectionContext> savanna = getBloomingNatureSelector("savanna");
        Predicate<BiomeSelectionContext> savanna_plateau = getBloomingNatureSelector("savanna_plateau");

        world.add(ModificationPhase.REMOVALS, overworld, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.LAVA_LAKE_SURFACE));
        world.add(ModificationPhase.REMOVALS, overworld, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.LAVA_LAKE_UNDERGROUND));
        world.add(ModificationPhase.REMOVALS, plains, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.PLAINS_FLOWERS));
        world.add(ModificationPhase.REMOVALS, plains, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.PLAINS_TREES));
        world.add(ModificationPhase.REMOVALS, aspen, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.ASPEN_FLOWERS));
        world.add(ModificationPhase.REMOVALS, aspen, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.FLOWER_DEFAULT));
        world.add(ModificationPhase.REMOVALS, aspen, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.ASPEN_TREES));
        world.add(ModificationPhase.REMOVALS, birch, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.BIRCH_FLOWERS));
        world.add(ModificationPhase.REMOVALS, birch, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.FLOWER_DEFAULT));
        world.add(ModificationPhase.REMOVALS, birch, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.BIRCH_TREES));
        world.add(ModificationPhase.REMOVALS, forest, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.FOREST_FLOWERS));
        world.add(ModificationPhase.REMOVALS, forest, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.FOREST_TREES));
        world.add(ModificationPhase.REMOVALS, flower_forest, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.FLOWER_FOREST_FLOWER));
        world.add(ModificationPhase.REMOVALS, flower_forest, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.FLOWER_FOREST_FLOWER_FLOWER));
        world.add(ModificationPhase.REMOVALS, river, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.RIVER_TREES));
        world.add(ModificationPhase.REMOVALS, river, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.RIVER_FLOWERS));
        world.add(ModificationPhase.REMOVALS, flower_forest, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.FLOWER_FOREST_TREES));
        world.add(ModificationPhase.REMOVALS, cherry_grove, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.CHERRY_TREES));
        world.add(ModificationPhase.REMOVALS, cherry_grove, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.CHERRY_FLOWERS));
        world.add(ModificationPhase.REMOVALS, snowy_plains, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SNOWY_PLAINS_TREES));
        world.add(ModificationPhase.REMOVALS, snowy_plains, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SNOWY_PLAINS_FLOWERS));
        world.add(ModificationPhase.REMOVALS, snowy_taiga, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SNOWY_TAIGA_TREES));
        world.add(ModificationPhase.REMOVALS, swamp, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SWAMP_TREES));
        world.add(ModificationPhase.REMOVALS, swamp, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SWAMP_FLOWERS));
        world.add(ModificationPhase.REMOVALS, swamp, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SWAMP_GRASS));
        world.add(ModificationPhase.REMOVALS, taiga, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.TAIGA_LARGE_FERN));
        world.add(ModificationPhase.REMOVALS, taiga, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.TAIGA_TREES));
        world.add(ModificationPhase.REMOVALS, taiga, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.TAIGA_FLOWERS));
        world.add(ModificationPhase.REMOVALS, taiga, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.TAIGA_GRASS));
        world.add(ModificationPhase.REMOVALS, old_growth_spruce_taiga, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.OLD_GROWTH_SPRUCE_TAIGA_LARGE_FERN));
        world.add(ModificationPhase.REMOVALS, old_growth_spruce_taiga, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.OLD_GROWTH_SPRUCE_TAIGA_TREES));
        world.add(ModificationPhase.REMOVALS, old_growth_spruce_taiga, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.OLD_GROWTH_SPRUCE_TAIGA_FLOWERS));
        world.add(ModificationPhase.REMOVALS, old_growth_spruce_taiga, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.OLD_GROWTH_SPRUCE_TAIGA_GRASS));
        world.add(ModificationPhase.REMOVALS, old_growth_pine_taiga, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.OLD_GROWTH_PINE_TAIGA_LARGE_FERN));
        world.add(ModificationPhase.REMOVALS, old_growth_pine_taiga, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.OLD_GROWTH_PINE_TAIGA_TREES));
        world.add(ModificationPhase.REMOVALS, old_growth_pine_taiga, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.OLD_GROWTH_PINE_TAIGA_FLOWERS));
        world.add(ModificationPhase.REMOVALS, old_growth_pine_taiga, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.OLD_GROWTH_PINE_TAIGA_GRASS));
        world.add(ModificationPhase.REMOVALS, savanna, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.TREES_SAVANNA));
        world.add(ModificationPhase.REMOVALS, savanna, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.PATCH_GRASS_SAVANNA));
        world.add(ModificationPhase.REMOVALS, savanna, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.BROWN_MUSHROOM_NORMAL));
        world.add(ModificationPhase.REMOVALS, savanna, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.RED_MUSHROOM_NORMAL));
        world.add(ModificationPhase.REMOVALS, savanna, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.FLOWER_WARM));
        world.add(ModificationPhase.REMOVALS, savanna_plateau, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.TREES_SAVANNA));
        world.add(ModificationPhase.REMOVALS, savanna_plateau, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.PATCH_GRASS_SAVANNA));
        world.add(ModificationPhase.REMOVALS, savanna_plateau, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.BROWN_MUSHROOM_NORMAL));
        world.add(ModificationPhase.REMOVALS, savanna_plateau, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.RED_MUSHROOM_NORMAL));
        world.add(ModificationPhase.REMOVALS, savanna_plateau, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.FLOWER_WARM));
        world.add(ModificationPhase.REMOVALS, dark_forest, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.DARK_FOREST_VEGETATION));
        world.add(ModificationPhase.REMOVALS, jungle, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.JUNGLE_FLOWERS));
        world.add(ModificationPhase.REMOVALS, jungle, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.JUNGLE_TREES));
        world.add(ModificationPhase.REMOVALS, jungle, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.JUNGLE_BAMBOO));
        world.add(ModificationPhase.REMOVALS, sparse_jungle, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SPARSE_JUNGLE_FLOWERS));
        world.add(ModificationPhase.REMOVALS, sparse_jungle, ctx -> ctx.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SPARSE_JUNGLE_TREES));

        world.add(ModificationPhase.ADDITIONS, beach, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.FAN_PALM_TREE_KEY));
        world.add(ModificationPhase.ADDITIONS, beach, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.BEACH_FLOWERS_KEY));
        world.add(ModificationPhase.ADDITIONS, desert, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.QUICKSAND_KEY));

        world.add(ModificationPhase.ADDITIONS, plains, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.PLAINS_BN_TREES));
        world.add(ModificationPhase.ADDITIONS, plains, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.PLAINS_BN_FLOWERS));
        world.add(ModificationPhase.ADDITIONS, plains, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.PLAINS_BN_GRASS));
        world.add(ModificationPhase.ADDITIONS, plains, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_BOULDER_KEY));
        world.add(ModificationPhase.ADDITIONS, plains, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_MOUND_KEY));
        world.add(ModificationPhase.ADDITIONS, plains, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.GRAVEL_BEACH_KEY));
        world.add(ModificationPhase.ADDITIONS, plains, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_SLABS_KEY));
        world.add(ModificationPhase.ADDITIONS, plains, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.TRAVERTIN_KEY));
        world.add(ModificationPhase.ADDITIONS, plains, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.PLAINS_BN_MOUND));

        world.add(ModificationPhase.ADDITIONS, aspen, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.ASPEN_BN_TREES));
        world.add(ModificationPhase.ADDITIONS, aspen, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.ASPEN_BN_FALLEN));
        world.add(ModificationPhase.ADDITIONS, aspen, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.ASPEN_BN_FLOWERS));
        world.add(ModificationPhase.ADDITIONS, aspen, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.ASPEN_BN_GRASS));
        world.add(ModificationPhase.ADDITIONS, aspen, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_SLABS_KEY));
        world.add(ModificationPhase.ADDITIONS, aspen, ctx -> ctx.getEffects().setGrassColor(14406505));

        world.add(ModificationPhase.ADDITIONS, birch, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.BIRCH_BN_TREES));
        world.add(ModificationPhase.ADDITIONS, birch, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.BIRCH_BN_FALLEN));
        world.add(ModificationPhase.ADDITIONS, birch, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.BIRCH_BN_FLOWERS));
        world.add(ModificationPhase.ADDITIONS, birch, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.BIRCH_BN_GRASS));
        world.add(ModificationPhase.ADDITIONS, birch, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_SLABS_KEY));
        world.add(ModificationPhase.ADDITIONS, birch, ctx -> ctx.getEffects().setGrassColor(10799444));
        world.add(ModificationPhase.ADDITIONS, birch, ctx -> ctx.getEffects().setFoliageColor(8567370));

        world.add(ModificationPhase.ADDITIONS, forest, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.FOREST_BN_TREES));
        world.add(ModificationPhase.ADDITIONS, forest, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.FOREST_BN_FALLEN));
        world.add(ModificationPhase.ADDITIONS, forest, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.FOREST_BN_FLOWERS));
        world.add(ModificationPhase.ADDITIONS, forest, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.FOREST_BN_GRASS));
        world.add(ModificationPhase.ADDITIONS, forest, ctx -> ctx.getEffects().setGrassColor(10799444));

        world.add(ModificationPhase.ADDITIONS, flower_forest, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.FLOWER_FOREST_BN_TREES));
        world.add(ModificationPhase.ADDITIONS, flower_forest, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.FLOWER_FOREST_BN_FALLEN));
        world.add(ModificationPhase.ADDITIONS, flower_forest, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.FLOWER_FOREST_BN_FLOWERS));
        world.add(ModificationPhase.ADDITIONS, flower_forest, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.FLOWER_FOREST_BN_GRASS));
        world.add(ModificationPhase.ADDITIONS, flower_forest, ctx -> ctx.getEffects().setGrassColor(10799444));

        world.add(ModificationPhase.ADDITIONS, river, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.RIVER_BN_TREES));
        world.add(ModificationPhase.ADDITIONS, river, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.PLAINS_BN_GRASS));
        world.add(ModificationPhase.ADDITIONS, river, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_BOULDER_KEY));
        world.add(ModificationPhase.ADDITIONS, river, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_MOUND_KEY));
        world.add(ModificationPhase.ADDITIONS, river, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.GRAVEL_BEACH_KEY));
        world.add(ModificationPhase.ADDITIONS, river, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_SLABS_KEY));
        world.add(ModificationPhase.ADDITIONS, river, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.TRAVERTIN_KEY));
        world.add(ModificationPhase.ADDITIONS, river, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.PLAINS_BN_MOUND));
        world.add(ModificationPhase.ADDITIONS, river, ctx -> ctx.getEffects().setGrassColor(11063154));
        world.add(ModificationPhase.ADDITIONS, river, ctx -> ctx.getEffects().setFoliageColor(10399058));

        world.add(ModificationPhase.ADDITIONS, cherry_grove, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.CHERRY_BN_TREES));
        world.add(ModificationPhase.ADDITIONS, cherry_grove, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.CHERRY_BN_FLOWERS));

        world.add(ModificationPhase.ADDITIONS, snowy_plains, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SNOWY_PLAINS_BN_TREES));
        world.add(ModificationPhase.ADDITIONS, snowy_plains, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_BOULDER_KEY));
        world.add(ModificationPhase.ADDITIONS, snowy_plains, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_MOUND_KEY));
        world.add(ModificationPhase.ADDITIONS, snowy_plains, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.GRAVEL_BEACH_KEY));
        world.add(ModificationPhase.ADDITIONS, snowy_plains, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_SLABS_KEY));

        world.add(ModificationPhase.ADDITIONS, snowy_slopes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SNOWY_SLOPES_BN_TREES));

        world.add(ModificationPhase.ADDITIONS, snowy_taiga, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SNOWY_TAIGA_BN_TREES));
        world.add(ModificationPhase.ADDITIONS, snowy_taiga, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_BOULDER_KEY));
        world.add(ModificationPhase.ADDITIONS, snowy_taiga, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_MOUND_KEY));
        world.add(ModificationPhase.ADDITIONS, snowy_taiga, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.GRAVEL_BEACH_KEY));
        world.add(ModificationPhase.ADDITIONS, snowy_taiga, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_SLABS_KEY));

        world.add(ModificationPhase.ADDITIONS, swamp, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SWAMP_WATER_BASIN));
        world.add(ModificationPhase.ADDITIONS, swamp, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SWAMP_MARSH_BASIN));
        world.add(ModificationPhase.ADDITIONS, swamp, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SWAMP_FLOATING_LEAVES));
        world.add(ModificationPhase.ADDITIONS, swamp, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SWAMP_MUD));
        world.add(ModificationPhase.ADDITIONS, swamp, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SWAMP_SWAMP_MUD));
        world.add(ModificationPhase.ADDITIONS, swamp, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SWAMP_MARSH));
        world.add(ModificationPhase.ADDITIONS, swamp, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SWAMP_VEGETATION));
        world.add(ModificationPhase.ADDITIONS, swamp, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SWAMP_CATTAILS));
        world.add(ModificationPhase.ADDITIONS, swamp, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SWAMP_REED));
        world.add(ModificationPhase.ADDITIONS, swamp, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SWAMP_BN_GRASS));
        world.add(ModificationPhase.ADDITIONS, swamp, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SWAMP_BN_FLOWERS));

        world.add(ModificationPhase.ADDITIONS, mangrove_swamp, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SWAMP_WATER_BASIN));
        world.add(ModificationPhase.ADDITIONS, mangrove_swamp, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SWAMP_MARSH_BASIN));
        world.add(ModificationPhase.ADDITIONS, mangrove_swamp, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SWAMP_FLOATING_LEAVES));
        world.add(ModificationPhase.ADDITIONS, mangrove_swamp, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SWAMP_MUD));
        world.add(ModificationPhase.ADDITIONS, mangrove_swamp, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SWAMP_SWAMP_MUD));
        world.add(ModificationPhase.ADDITIONS, mangrove_swamp, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SWAMP_MARSH));
        world.add(ModificationPhase.ADDITIONS, mangrove_swamp, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SWAMP_VEGETATION));
        world.add(ModificationPhase.ADDITIONS, mangrove_swamp, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SWAMP_CATTAILS));
        world.add(ModificationPhase.ADDITIONS, mangrove_swamp, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SWAMP_REED));

        world.add(ModificationPhase.ADDITIONS, sunflower_plains, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.PLAINS_BN_TREES));
        world.add(ModificationPhase.ADDITIONS, sunflower_plains, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.ASPEN_BN_FLOWERS));

        world.add(ModificationPhase.ADDITIONS, stony_shore, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_PILLARS_KEY));
        world.add(ModificationPhase.ADDITIONS, stony_shore, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_CLIFFS_KEY));
        world.add(ModificationPhase.ADDITIONS, stony_shore, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.COBBLESTONE_BEACH_KEY));
        world.add(ModificationPhase.ADDITIONS, stony_shore, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.COBBLESTONE_BEACH_MOSSY_KEY));

        world.add(ModificationPhase.ADDITIONS, taiga, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.TAIGA_SPRUCE_TREES));
        world.add(ModificationPhase.ADDITIONS, taiga, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.TAIGA_SPRUCE_FALLEN));

        world.add(ModificationPhase.ADDITIONS, taiga, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_BOULDER_KEY));
        world.add(ModificationPhase.ADDITIONS, taiga, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_MOUND_KEY));
        world.add(ModificationPhase.ADDITIONS, taiga, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.GRAVEL_BEACH_KEY));
        world.add(ModificationPhase.ADDITIONS, taiga, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_SLABS_KEY));
        world.add(ModificationPhase.ADDITIONS, taiga, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.TRAVERTIN_KEY));
        world.add(ModificationPhase.ADDITIONS, taiga, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.TAIGA_FOREST_MOSS));
        world.add(ModificationPhase.ADDITIONS, taiga, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.TAIGA_GRASS_PATCH));
        world.add(ModificationPhase.ADDITIONS, taiga, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.TAIGA_BN_FLOWERS));
        world.add(ModificationPhase.ADDITIONS, taiga, ctx -> ctx.getEffects().setGrassColor(11977352));

        world.add(ModificationPhase.ADDITIONS, old_growth_spruce_taiga, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.OLD_GROWTH_SPRUCE_TAIGA_BN_TREES));
        world.add(ModificationPhase.ADDITIONS, old_growth_spruce_taiga, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.TAIGA_SPRUCE_FALLEN));
        world.add(ModificationPhase.ADDITIONS, old_growth_spruce_taiga, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_BOULDER_KEY));
        world.add(ModificationPhase.ADDITIONS, old_growth_spruce_taiga, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_MOUND_KEY));
        world.add(ModificationPhase.ADDITIONS, old_growth_spruce_taiga, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.GRAVEL_BEACH_KEY));
        world.add(ModificationPhase.ADDITIONS, old_growth_spruce_taiga, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_SLABS_KEY));
        world.add(ModificationPhase.ADDITIONS, old_growth_spruce_taiga, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.TRAVERTIN_KEY));
        world.add(ModificationPhase.ADDITIONS, old_growth_spruce_taiga, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.TAIGA_FOREST_MOSS));
        world.add(ModificationPhase.ADDITIONS, old_growth_spruce_taiga, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.TAIGA_GRASS_PATCH));
        world.add(ModificationPhase.ADDITIONS, old_growth_spruce_taiga, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.TAIGA_BN_FLOWERS));

        world.add(ModificationPhase.ADDITIONS, old_growth_pine_taiga, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.LARCH_TREES));
        world.add(ModificationPhase.ADDITIONS, old_growth_pine_taiga, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.LARCH_FALLEN));
        world.add(ModificationPhase.ADDITIONS, old_growth_pine_taiga, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.LARCH_FLOWERS));
        world.add(ModificationPhase.ADDITIONS, old_growth_pine_taiga, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.STONE_SLABS_KEY));
        world.add(ModificationPhase.ADDITIONS, old_growth_pine_taiga, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.TAIGA_FOREST_MOSS));
        world.add(ModificationPhase.ADDITIONS, old_growth_pine_taiga, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.LARCH_GRASS_PATCH));
        world.add(ModificationPhase.ADDITIONS, old_growth_pine_taiga, ctx -> ctx.getEffects().setGrassColor(9286496));
        world.add(ModificationPhase.ADDITIONS, old_growth_pine_taiga, ctx -> ctx.getEffects().setFoliageColor(10399058));

        world.add(ModificationPhase.ADDITIONS, savanna, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SAVANNA_TREES));
        world.add(ModificationPhase.ADDITIONS, savanna, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.GRANITE_BOULDERS));
        world.add(ModificationPhase.ADDITIONS, savanna, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.GRANITE_SLABS));
        world.add(ModificationPhase.ADDITIONS, savanna, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.PACKED_MUD));
        world.add(ModificationPhase.ADDITIONS, savanna, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SAVANNA_VEGETATION));
        world.add(ModificationPhase.ADDITIONS, savanna, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SAVANNA_GRASS_PATCH));
        world.add(ModificationPhase.ADDITIONS, savanna, ctx -> ctx.getEffects().setGrassColor(15259000));
        world.add(ModificationPhase.ADDITIONS, savanna, ctx -> ctx.getEffects().setFoliageColor(10399058));

        world.add(ModificationPhase.ADDITIONS, savanna_plateau, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SAVANNA_PLATEAU_TREES));
        world.add(ModificationPhase.ADDITIONS, savanna_plateau, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.GRANITE_BOULDERS));
        world.add(ModificationPhase.ADDITIONS, savanna_plateau, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.GRANITE_SLABS));
        world.add(ModificationPhase.ADDITIONS, savanna_plateau, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.PACKED_MUD));
        world.add(ModificationPhase.ADDITIONS, savanna_plateau, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SAVANNA_VEGETATION));
        world.add(ModificationPhase.ADDITIONS, savanna_plateau, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SAVANNA_GRASS_PATCH));
        world.add(ModificationPhase.ADDITIONS, savanna_plateau, ctx -> ctx.getEffects().setGrassColor(15259000));

        world.add(ModificationPhase.ADDITIONS, dark_forest, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.DARK_FOREST_TREES));
        world.add(ModificationPhase.ADDITIONS, dark_forest, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.DARK_FOREST_GRASS_PATCH));
        world.add(ModificationPhase.ADDITIONS, dark_forest, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.DARK_FOREST_TREE_FALLEN));
        world.add(ModificationPhase.ADDITIONS, dark_forest, ctx -> ctx.getEffects().setGrassColor(6975545));

        world.add(ModificationPhase.ADDITIONS, jungle, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.FLOATING_LEAVES_KEY));
        world.add(ModificationPhase.ADDITIONS, jungle, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SAVANNA_VEGETATION));
        world.add(ModificationPhase.ADDITIONS, jungle, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.JUNGLE_FLOWERS_KEY));
        world.add(ModificationPhase.ADDITIONS, jungle, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.JUNGLE_BN_TREES));
        world.add(ModificationPhase.ADDITIONS, jungle, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SPARSE_JUNGLE_BN_GRASS));
        world.add(ModificationPhase.ADDITIONS, jungle, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SPARSE_JUNGLE_BN_PUDDLE));
        world.add(ModificationPhase.ADDITIONS, jungle, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SPARSE_JUNGLE_BN_MUD));
        world.add(ModificationPhase.ADDITIONS, jungle, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SPARSE_JUNGLE_LATERIT_BOULDER));
        world.add(ModificationPhase.ADDITIONS, jungle, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedFeatures.SPARSE_JUNGLE_ORE_LATERIT));
        world.add(ModificationPhase.ADDITIONS, jungle, ctx -> ctx.getEffects().setGrassColor(8970560));
        world.add(ModificationPhase.ADDITIONS, jungle, ctx -> ctx.getEffects().setFoliageColor(6337104));

        world.add(ModificationPhase.ADDITIONS, sparse_jungle, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.FLOATING_LEAVES_KEY));
        world.add(ModificationPhase.ADDITIONS, sparse_jungle, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SAVANNA_VEGETATION));
        world.add(ModificationPhase.ADDITIONS, sparse_jungle, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.JUNGLE_FLOWERS_KEY));
        world.add(ModificationPhase.ADDITIONS, sparse_jungle, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SPARSE_JUNGLE_BN_TREES));
        world.add(ModificationPhase.ADDITIONS, sparse_jungle, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SPARSE_JUNGLE_BN_GRASS));
        world.add(ModificationPhase.ADDITIONS, sparse_jungle, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SPARSE_JUNGLE_BN_PUDDLE));
        world.add(ModificationPhase.ADDITIONS, sparse_jungle, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SPARSE_JUNGLE_BN_MUD));
        world.add(ModificationPhase.ADDITIONS, sparse_jungle, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.SPARSE_JUNGLE_LATERIT_BOULDER));
        world.add(ModificationPhase.ADDITIONS, sparse_jungle, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedFeatures.SPARSE_JUNGLE_ORE_LATERIT));
        world.add(ModificationPhase.ADDITIONS, sparse_jungle, ctx -> ctx.getEffects().setGrassColor(8970560));
        world.add(ModificationPhase.ADDITIONS, sparse_jungle, ctx -> ctx.getEffects().setFoliageColor(6337104));


    }

    private static Predicate<BiomeSelectionContext> getBloomingNatureSelector(String path) {
        return BiomeSelectors.tag(TagKey.create(Registries.BIOME, BloomingNatureIdentifier.of(path)));
    }
}