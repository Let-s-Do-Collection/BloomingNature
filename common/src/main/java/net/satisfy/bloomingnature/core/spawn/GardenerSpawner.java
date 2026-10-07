package net.satisfy.bloomingnature.core.spawn;

import dev.architectury.event.events.common.TickEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.satisfy.bloomingnature.BloomingNature;
import net.satisfy.bloomingnature.core.config.BloomingNatureConfig;
import net.satisfy.bloomingnature.core.entity.WanderingGardenerEntity;
import net.satisfy.bloomingnature.core.registry.EntityTypeRegistry;

public final class GardenerSpawner {
    public static final TagKey<Biome> VISIT_BIOMES = TagKey.create(Registries.BIOME, BloomingNature.identifier("gardener_visits"));

    private static final int CHECK_INTERVAL = 600;
    private static final int SPAWN_RANGE = 48;
    private static final int SPAWN_ATTEMPTS = 20;
    private static final int WANDER_RADIUS = 16;

    private GardenerSpawner() {
    }

    public static void init() {
        TickEvent.SERVER_LEVEL_POST.register(GardenerSpawner::tick);
    }

    private static void tick(ServerLevel level) {
        if (level.dimension() != Level.OVERWORLD || level.getGameTime() % CHECK_INTERVAL != 0 || !BloomingNatureConfig.gardenerEnabled || !level.getGameRules().getBoolean(GameRules.RULE_DO_TRADER_SPAWNING)) {
            return;
        }
        GardenerVisitData data = level.getDataStorage().computeIfAbsent(GardenerVisitData.factory(), GardenerVisitData.ID);
        long now = level.getGameTime();
        if (data.getNextVisit() < 0) {
            data.setNextVisit(now + delay(level.random));
            return;
        }
        if (now < data.getNextVisit() || !level.isDay()) {
            return;
        }
        if (visit(level)) {
            data.setNextVisit(now + BloomingNatureConfig.gardenerVisitDuration + delay(level.random));
        }
    }

    private static int delay(RandomSource random) {
        int min = BloomingNatureConfig.gardenerMinDelay;
        return min + random.nextInt(Math.max(1, BloomingNatureConfig.gardenerMaxDelay - min));
    }

    private static boolean visit(ServerLevel level) {
        ServerPlayer player = level.getRandomPlayer();
        if (player == null) {
            return false;
        }
        BlockPos playerPos = player.blockPosition();
        BlockPos meeting = level.getPoiManager().find(type -> type.is(PoiTypes.MEETING), pos -> true, playerPos, SPAWN_RANGE, PoiManager.Occupancy.ANY).orElse(playerPos);
        BlockPos spawnPos = findSpawnPosition(level, meeting);
        if (spawnPos == null) {
            return false;
        }
        WanderingGardenerEntity gardener = EntityTypeRegistry.WANDERING_GARDENER.get().spawn(level, spawnPos, MobSpawnType.EVENT);
        if (gardener == null) {
            return false;
        }
        gardener.setDespawnDelay(BloomingNatureConfig.gardenerVisitDuration);
        gardener.setWanderTarget(meeting);
        gardener.restrictTo(meeting, WANDER_RADIUS);
        return true;
    }

    private static BlockPos findSpawnPosition(ServerLevel level, BlockPos around) {
        for (int i = 0; i < SPAWN_ATTEMPTS; i++) {
            int x = around.getX() + level.random.nextInt(SPAWN_RANGE * 2) - SPAWN_RANGE;
            int z = around.getZ() + level.random.nextInt(SPAWN_RANGE * 2) - SPAWN_RANGE;
            if (!level.hasChunk(x >> 4, z >> 4)) {
                continue;
            }
            BlockPos pos = new BlockPos(x, level.getHeight(Heightmap.Types.WORLD_SURFACE, x, z), z);
            if (isVisitBiome(level, pos) && SpawnPlacements.isSpawnPositionOk(EntityType.WANDERING_TRADER, level, pos) && hasEnoughSpace(level, pos)) {
                return pos;
            }
        }
        return null;
    }

    private static boolean isVisitBiome(ServerLevel level, BlockPos pos) {
        Holder<Biome> biome = level.getBiome(pos);
        return biome.is(VISIT_BIOMES) && !biome.is(BiomeTags.WITHOUT_WANDERING_TRADER_SPAWNS);
    }

    private static boolean hasEnoughSpace(ServerLevel level, BlockPos pos) {
        for (BlockPos check : BlockPos.betweenClosed(pos, pos.offset(1, 2, 1))) {
            if (!level.getBlockState(check).getCollisionShape(level, check).isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
