package net.satisfy.bloomingnature.core.registry;

import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.satisfy.bloomingnature.BloomingNature;
import net.satisfy.bloomingnature.core.block.entity.*;
import net.satisfy.bloomingnature.core.entity.ModBoatEntity;
import net.satisfy.bloomingnature.core.entity.ModChestBoatEntity;
import net.satisfy.bloomingnature.core.entity.WanderingGardenerEntity;
import net.satisfy.bloomingnature.core.util.BloomingNatureIdentifier;
import net.satisfy.bloomingnature.platform.PlatformHelper;

import java.util.HashSet;
import java.util.function.Supplier;

public class EntityTypeRegistry {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(BloomingNature.MOD_ID, Registries.BLOCK_ENTITY_TYPE);
    private static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BloomingNature.MOD_ID, Registries.ENTITY_TYPE);

    public static final RegistrySupplier<EntityType<WanderingGardenerEntity>> WANDERING_GARDENER = registerEntityType("wandering_gardener", () -> EntityType.Builder.of(WanderingGardenerEntity::new, MobCategory.CREATURE).sized(0.6f, 1.95f).clientTrackingRange(10).build(BloomingNatureIdentifier.of("wandering_gardener").toString()));
    public static final Supplier<EntityType<ModBoatEntity>> MOD_BOAT = PlatformHelper.registerBoatType("mod_boat", ModBoatEntity::new, MobCategory.MISC, 1.375F, 0.5625F, 10);
    public static final Supplier<EntityType<ModChestBoatEntity>> MOD_CHEST_BOAT = PlatformHelper.registerBoatType("mod_chest_boat", ModChestBoatEntity::new, MobCategory.MISC, 1.375F, 0.5625F, 10);

    public static final RegistrySupplier<BlockEntityType<StorageBlockEntity>> STORAGE_ENTITY = registerBlockEntity("storage", () -> BlockEntityType.Builder.of(StorageBlockEntity::new, StorageTypeRegistry.registerBlocks(new HashSet<>()).toArray(new Block[0])).build(null));
    public static final RegistrySupplier<BlockEntityType<FlowerPotBigBlockEntity>> FLOWER_POT_BIG_ENTITY = registerBlockEntity("flower_pot_big", () -> BlockEntityType.Builder.of(FlowerPotBigBlockEntity::new, StorageTypeRegistry.registerBlocks(new HashSet<>()).toArray(new Block[0])).build(null));
    public static final RegistrySupplier<BlockEntityType<CompletionistBannerEntity>> BLOOMINGNATURE_BANNER = registerBlockEntity("bloomingnature_banner", () -> BlockEntityType.Builder.of(CompletionistBannerEntity::new, ObjectRegistry.BLOOMINGNATURE_BANNER.get(), ObjectRegistry.BLOOMINGNATURE_WALL_BANNER.get()).build(null));

    public static final RegistrySupplier<BlockEntityType<ModSignBlockEntity>> MOD_SIGN = BLOCK_ENTITY_TYPES.register("mod_sign", () -> BlockEntityType.Builder.of(
            ModSignBlockEntity::new,
            ObjectRegistry.LARCH_SIGN.get(), ObjectRegistry.LARCH_WALL_SIGN.get(),
            ObjectRegistry.ASPEN_SIGN.get(), ObjectRegistry.ASPEN_WALL_SIGN.get(),
            ObjectRegistry.BAOBAB_SIGN.get(), ObjectRegistry.BAOBAB_WALL_SIGN.get(),
            ObjectRegistry.EBONY_SIGN.get(), ObjectRegistry.EBONY_WALL_SIGN.get(),
            ObjectRegistry.CHESTNUT_SIGN.get(), ObjectRegistry.CHESTNUT_WALL_SIGN.get(),
            ObjectRegistry.SWAMP_OAK_SIGN.get(), ObjectRegistry.SWAMP_OAK_WALL_SIGN.get(),
            ObjectRegistry.SWAMP_CYPRESS_SIGN.get(), ObjectRegistry.SWAMP_CYPRESS_WALL_SIGN.get(),
            ObjectRegistry.FAN_PALM_SIGN.get(), ObjectRegistry.FAN_PALM_WALL_SIGN.get(),
            ObjectRegistry.FIR_SIGN.get(), ObjectRegistry.FIR_WALL_SIGN.get(),
            ObjectRegistry.CACTUS_SIGN.get(), ObjectRegistry.CACTUS_WALL_SIGN.get()
    ).build(null));

    public static final RegistrySupplier<BlockEntityType<ModHangingSignBlockEntity>> MOD_HANGING_SIGN = BLOCK_ENTITY_TYPES.register("mod_hanging_sign", () -> BlockEntityType.Builder.of(
            ModHangingSignBlockEntity::new,
            ObjectRegistry.LARCH_HANGING_SIGN.get(), ObjectRegistry.LARCH_WALL_HANGING_SIGN.get(),
            ObjectRegistry.ASPEN_HANGING_SIGN.get(), ObjectRegistry.ASPEN_WALL_HANGING_SIGN.get(),
            ObjectRegistry.BAOBAB_HANGING_SIGN.get(), ObjectRegistry.BAOBAB_WALL_HANGING_SIGN.get(),
            ObjectRegistry.EBONY_HANGING_SIGN.get(), ObjectRegistry.EBONY_WALL_HANGING_SIGN.get(),
            ObjectRegistry.CHESTNUT_HANGING_SIGN.get(), ObjectRegistry.CHESTNUT_WALL_HANGING_SIGN.get(),
            ObjectRegistry.SWAMP_OAK_HANGING_SIGN.get(), ObjectRegistry.SWAMP_OAK_WALL_HANGING_SIGN.get(),
            ObjectRegistry.SWAMP_CYPRESS_HANGING_SIGN.get(), ObjectRegistry.SWAMP_CYPRESS_WALL_HANGING_SIGN.get(),
            ObjectRegistry.FAN_PALM_HANGING_SIGN.get(), ObjectRegistry.FAN_PALM_WALL_HANGING_SIGN.get(),
            ObjectRegistry.FIR_HANGING_SIGN.get(), ObjectRegistry.FIR_WALL_HANGING_SIGN.get(),
            ObjectRegistry.CACTUS_HANGING_SIGN.get(), ObjectRegistry.CACTUS_WALL_HANGING_SIGN.get()
    ).build(null));

    private static <T extends EntityType<?>> RegistrySupplier<T> registerEntityType(final String path, final Supplier<T> type) {
        return ENTITY_TYPES.register(BloomingNatureIdentifier.of(path), type);
    }

    private static <T extends BlockEntityType<?>> RegistrySupplier<T> registerBlockEntity(final String path, final Supplier<T> type) {
        return BLOCK_ENTITY_TYPES.register(BloomingNatureIdentifier.of(path), type);
    }

    static void registerAttributes() {
        EntityAttributeRegistry.register(WANDERING_GARDENER, WanderingGardenerEntity::createMobAttributes);
    }

    public static void init() {
        ENTITY_TYPES.register();
        BLOCK_ENTITY_TYPES.register();
        registerAttributes();
    }
}
