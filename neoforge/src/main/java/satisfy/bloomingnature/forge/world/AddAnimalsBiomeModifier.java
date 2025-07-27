package satisfy.bloomingnature.forge.world;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import satisfy.bloomingnature.forge.registry.BloomingNatureBiomeModifiers;
import satisfy.bloomingnature.registry.EntityRegistry;
import satisfy.bloomingnature.registry.TagsRegistry;

import java.util.HashSet;
import java.util.Set;

public class AddAnimalsBiomeModifier implements BiomeModifier {
    private static final Set<EntityType<?>> registeredEntities = new HashSet<>();

    private static <T extends Mob> void registerEntity(EntityType<T> type, SpawnPlacements.Type spawnType, Heightmap.Types heightmapType, SpawnPlacements.SpawnPredicate<T> predicate) {
        if (!registeredEntities.contains(type)) {
            SpawnPlacements.register(type, spawnType, heightmapType, predicate);
            registeredEntities.add(type);
        }
    }

    public static void registerEntities() {
        registerEntity(EntityRegistry.SQUIRREL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AmbientCreature::checkMobSpawnRules);
        registerEntity(EntityRegistry.TERMITE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AmbientCreature::checkMobSpawnRules);
        registerEntity(EntityRegistry.OWL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, AmbientCreature::checkMobSpawnRules);
        registerEntity(EntityRegistry.TURKEY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AmbientCreature::checkMobSpawnRules);
        registerEntity(EntityRegistry.RACCOON.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AmbientCreature::checkMobSpawnRules);
        registerEntity(EntityRegistry.PELICAN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AmbientCreature::checkMobSpawnRules);
        registerEntity(EntityRegistry.MUDDY_PIG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AmbientCreature::checkMobSpawnRules);
        registerEntity(EntityRegistry.DEER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AmbientCreature::checkMobSpawnRules);
        registerEntity(EntityRegistry.MOSSY_SHEEP.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AmbientCreature::checkMobSpawnRules);
        registerEntity(EntityRegistry.RED_WOLF.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AmbientCreature::checkMobSpawnRules);
        registerEntity(EntityRegistry.BOAR.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AmbientCreature::checkMobSpawnRules);
        registerEntity(EntityRegistry.BISON.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AmbientCreature::checkMobSpawnRules);
    }

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase.equals(Phase.ADD)) {
            registerEntities();

            addMobSpawn(builder, biome, TagsRegistry.SPAWNS_DEER, MobCategory.CREATURE, EntityRegistry.DEER.get(), 12, 2, 4);
            addMobSpawn(builder, biome, TagsRegistry.SPAWNS_DEER, MobCategory.CREATURE, EntityRegistry.DEER.get(), 12, 2, 4);
            addMobSpawn(builder, biome, BiomeTags.IS_BEACH, MobCategory.CREATURE, EntityRegistry.PELICAN.get(), 5, 3, 4);
            addMobSpawn(builder, biome, BiomeTags.HAS_WOODLAND_MANSION, MobCategory.CREATURE, EntityType.EVOKER, 4, 1, 2);
            addMobSpawn(builder, biome, BiomeTags.HAS_WOODLAND_MANSION, MobCategory.CREATURE, EntityType.VINDICATOR, 4, 1, 2);
            addMobSpawn(builder, biome, BiomeTags.HAS_WOODLAND_MANSION, MobCategory.CREATURE, EntityType.PILLAGER, 4, 1, 3);
            addMobSpawn(builder, biome, TagsRegistry.SPAWNS_MUDDY_PIG, MobCategory.CREATURE, EntityRegistry.MUDDY_PIG.get(), 8, 4, 5);
            addMobSpawn(builder, biome, TagsRegistry.SPAWNS_MOSSY_SHEEP, MobCategory.CREATURE, EntityRegistry.MOSSY_SHEEP.get(), 8, 3, 5);
            addMobSpawn(builder, biome, TagsRegistry.SPAWNS_RACCOON, MobCategory.CREATURE, EntityRegistry.RACCOON.get(), 8, 2, 3);
            addMobSpawn(builder, biome, TagsRegistry.SPAWNS_SQUIRREL, MobCategory.CREATURE, EntityRegistry.SQUIRREL.get(), 8, 2, 2);
            addMobSpawn(builder, biome, TagsRegistry.SPAWNS_RED_WOLF, MobCategory.CREATURE, EntityRegistry.RED_WOLF.get(), 10, 3, 4);
            addMobSpawn(builder, biome, TagsRegistry.SPAWNS_OWL, MobCategory.CREATURE, EntityRegistry.OWL.get(), 12, 3, 3);
            addMobSpawn(builder, biome, TagsRegistry.SPAWNS_BOAR, MobCategory.CREATURE, EntityRegistry.BOAR.get(), 14, 5, 5);
            addMobSpawn(builder, biome, TagsRegistry.SPAWNS_BISON, MobCategory.CREATURE, EntityRegistry.BISON.get(), 10, 3, 5);
            addMobSpawn(builder, biome, TagsRegistry.SPAWNS_TURKEY, MobCategory.CREATURE, EntityRegistry.TURKEY.get(), 12, 3, 5);
            addMobSpawn(builder, biome, TagsRegistry.SPAWNS_TURKEY, MobCategory.CREATURE, EntityRegistry.TURKEY.get(), 12, 3, 5);
            addMobSpawn(builder, biome, BiomeTags.IS_JUNGLE, MobCategory.CREATURE, EntityType.FROG, 8, 3, 4);
            addMobSpawn(builder, biome, BiomeTags.IS_SAVANNA, MobCategory.CREATURE, EntityRegistry.TERMITE.get(), 10, 3, 4);
        }
    }

    void addMobSpawn(ModifiableBiomeInfo.BiomeInfo.Builder builder, Holder<Biome> biome, TagKey<Biome> tag, MobCategory mobCategory, EntityType<?> entityType, int weight, int minGroupSize, int maxGroupSize) {
        if (biome.is(tag)) {
            builder.getMobSpawnSettings().addSpawn(mobCategory, new MobSpawnSettings.SpawnerData(entityType, weight, minGroupSize, maxGroupSize));
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return BloomingNatureBiomeModifiers.ADD_ANIMALS_CODEC.get();
    }
}
