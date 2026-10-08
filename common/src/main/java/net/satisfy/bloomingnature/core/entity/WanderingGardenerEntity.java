package net.satisfy.bloomingnature.core.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import org.jetbrains.annotations.Nullable;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.satisfy.bloomingnature.core.registry.FloraRegistry;
import net.satisfy.bloomingnature.core.config.BloomingNatureConfig;
import net.satisfy.bloomingnature.core.registry.ObjectRegistry;
import net.satisfy.bloomingnature.core.util.BloomingNatureGeneralUtil;

import java.util.HashMap;

public class WanderingGardenerEntity extends WanderingTrader {
    public static final HashMap<Integer, VillagerTrades.ItemListing[]> TRADES = createTrades();

    private static final double CAMEL_SEARCH_RADIUS = 12.0D;
    private static final int CAMEL_SPAWN_ATTEMPTS = 16;
    private static final int CAMEL_SPAWN_RANGE = 4;

    private boolean needsCamel;

    public WanderingGardenerEntity(EntityType<? extends WanderingGardenerEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        this.needsCamel = spawnType == MobSpawnType.EVENT && BloomingNatureConfig.gardenerBringsCamel;
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.needsCamel && this.level() instanceof ServerLevel serverLevel) {
            this.needsCamel = false;
            spawnCamel(serverLevel);
        }
    }

    @Override
    public void remove(RemovalReason reason) {
        if (reason == RemovalReason.DISCARDED && this.level() instanceof ServerLevel serverLevel) {
            for (Camel camel : serverLevel.getEntitiesOfClass(Camel.class, this.getBoundingBox().inflate(CAMEL_SEARCH_RADIUS), camel -> camel.getLeashHolder() == this)) {
                camel.discard();
            }
        }
        super.remove(reason);
    }

    private void spawnCamel(ServerLevel level) {
        BlockPos pos = findCamelPosition(level);
        if (pos == null) {
            return;
        }
        Camel camel = EntityType.CAMEL.spawn(level, pos, MobSpawnType.EVENT);
        if (camel != null) {
            camel.setLeashedTo(this, true);
        }
    }

    private BlockPos findCamelPosition(ServerLevel level) {
        BlockPos origin = this.blockPosition();
        for (int i = 0; i < CAMEL_SPAWN_ATTEMPTS; i++) {
            int x = origin.getX() + this.random.nextInt(CAMEL_SPAWN_RANGE * 2 + 1) - CAMEL_SPAWN_RANGE;
            int z = origin.getZ() + this.random.nextInt(CAMEL_SPAWN_RANGE * 2 + 1) - CAMEL_SPAWN_RANGE;
            BlockPos pos = level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, new BlockPos(x, origin.getY(), z));
            if (Math.abs(pos.getY() - origin.getY()) > 2 || !level.getFluidState(pos.below()).isEmpty() || !level.getBlockState(pos.below()).isFaceSturdy(level, pos.below(), Direction.UP)) {
                continue;
            }
            if (level.noCollision(EntityType.CAMEL.getSpawnAABB(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D))) {
                return pos;
            }
        }
        return null;
    }

    private static HashMap<Integer, VillagerTrades.ItemListing[]> createTrades() {
        HashMap<Integer, VillagerTrades.ItemListing[]> trades = new HashMap<>();
        trades.put(1, new VillagerTrades.ItemListing[]{
                new BloomingNatureGeneralUtil.BloomingNatureVillagerUtil.SellItemFactory(ObjectRegistry.JOE_PYE.get(), 2, 4, 3, 15),
                new BloomingNatureGeneralUtil.BloomingNatureVillagerUtil.SellItemFactory(ObjectRegistry.CATTAIL.get(), 2, 4, 3, 15),
                new BloomingNatureGeneralUtil.BloomingNatureVillagerUtil.SellItemFactory(ObjectRegistry.REED.get(), 2, 4, 3, 15),
                new BloomingNatureGeneralUtil.BloomingNatureVillagerUtil.SellItemFactory(ObjectRegistry.HYSSOP.get(), 2, 4, 3, 15),
                new BloomingNatureGeneralUtil.BloomingNatureVillagerUtil.SellItemFactory(ObjectRegistry.MOUNTAIN_SNOWBELL.get(), 2, 4, 3, 15),
                new BloomingNatureGeneralUtil.BloomingNatureVillagerUtil.SellItemFactory(ObjectRegistry.CARDINAL.get(), 2, 4, 3, 15),
                new BloomingNatureGeneralUtil.BloomingNatureVillagerUtil.SellItemFactory(ObjectRegistry.MOUNTAIN_LAUREL.get(), 2, 4, 3, 15),
                new BloomingNatureGeneralUtil.BloomingNatureVillagerUtil.SellItemFactory(ObjectRegistry.BIRD_OF_PARADISE.get(), 2, 4, 3, 15),
                new BloomingNatureGeneralUtil.BloomingNatureVillagerUtil.SellItemFactory(ObjectRegistry.WHITE_ORCHID.get(), 2, 4, 3, 15),
                new BloomingNatureGeneralUtil.BloomingNatureVillagerUtil.SellItemFactory(ObjectRegistry.DAPHNE.get(), 2, 4, 3, 15),
                new BloomingNatureGeneralUtil.BloomingNatureVillagerUtil.SellItemFactory(ObjectRegistry.BOTTLEBRUSHES.get(), 2, 4, 3, 15),
                new BloomingNatureGeneralUtil.BloomingNatureVillagerUtil.SellItemFactory(ObjectRegistry.BLUEBELL.get(), 2, 4, 3, 15),
                new BloomingNatureGeneralUtil.BloomingNatureVillagerUtil.SellItemFactory(ObjectRegistry.BEGONIE.get(), 2, 4, 3, 15),
                new BloomingNatureGeneralUtil.BloomingNatureVillagerUtil.SellItemFactory(ObjectRegistry.GOATSBEARD.get(), 2, 4, 3, 15),
                new BloomingNatureGeneralUtil.BloomingNatureVillagerUtil.SellItemFactory(ObjectRegistry.GENISTEAE.get(), 2, 4, 3, 15),
                new BloomingNatureGeneralUtil.BloomingNatureVillagerUtil.SellItemFactory(ObjectRegistry.FOXGLOVE_WHITE.get(), 2, 4, 3, 15),
                new BloomingNatureGeneralUtil.BloomingNatureVillagerUtil.SellItemFactory(ObjectRegistry.FOXGLOVE_PINK.get(), 2, 4, 3, 15),
                new BloomingNatureGeneralUtil.BloomingNatureVillagerUtil.SellItemFactory(ObjectRegistry.FREESIA_YELLOW.get(), 2, 4, 3, 15),
                new BloomingNatureGeneralUtil.BloomingNatureVillagerUtil.SellItemFactory(ObjectRegistry.FREESIA_PINK.get(), 2, 4, 3, 15),
                new BloomingNatureGeneralUtil.BloomingNatureVillagerUtil.SellItemFactory(ObjectRegistry.LUPINE_BLUE.get(), 2, 4, 3, 15),
                new BloomingNatureGeneralUtil.BloomingNatureVillagerUtil.SellItemFactory(ObjectRegistry.LUPINE_PURPLE.get(), 2, 4, 3, 15),
                new BloomingNatureGeneralUtil.BloomingNatureVillagerUtil.SellItemFactory(ObjectRegistry.DRY_BUSH.get(), 2, 4, 3, 15),
                new BloomingNatureGeneralUtil.BloomingNatureVillagerUtil.SellItemFactory(ObjectRegistry.DRY_GRASS.get(), 2, 4, 3, 15),
                new BloomingNatureGeneralUtil.BloomingNatureVillagerUtil.SellItemFactory(ObjectRegistry.DRY_BUSH_TALL.get(), 2, 4, 3, 15),
                new BloomingNatureGeneralUtil.BloomingNatureVillagerUtil.SellItemFactory(FloraRegistry.JUNGLE_FERN.get(), 2, 4, 3, 15),
                new BloomingNatureGeneralUtil.BloomingNatureVillagerUtil.SellItemFactory(FloraRegistry.WILD_VINES.get(), 2, 4, 3, 15),
                new BloomingNatureGeneralUtil.BloomingNatureVillagerUtil.SellItemFactory(FloraRegistry.FLOWERING_LILY_PAD.get(), 2, 4, 3, 15),
                new BloomingNatureGeneralUtil.BloomingNatureVillagerUtil.SellItemFactory(ObjectRegistry.PAMPAS_GRASS.get(), 2, 4, 3, 15),
                new BloomingNatureGeneralUtil.BloomingNatureVillagerUtil.SellItemFactory(Blocks.SUNFLOWER, 3, 2, 10, 15)
        });
        return trades;
    }

    @Override
    protected void updateTrades() {
        if (this.offers == null) {
            this.offers = new MerchantOffers();
        }
        this.addOffersFromItemListings(this.offers, TRADES.get(1), 8);
    }
}