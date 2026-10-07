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
import net.satisfy.bloomingnature.core.entity.WanderingGardenerEntity;
import net.satisfy.bloomingnature.platform.PlatformHelper;
import net.satisfy.foundation.banner.CompletionistBannerEntity;
import net.satisfy.foundation.wood.*;

import java.util.HashSet;
import java.util.function.Supplier;

public class EntityTypeRegistry {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(BloomingNature.MOD_ID, Registries.BLOCK_ENTITY_TYPE);
    private static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BloomingNature.MOD_ID, Registries.ENTITY_TYPE);

    public static final RegistrySupplier<EntityType<WanderingGardenerEntity>> WANDERING_GARDENER = registerEntityType("wandering_gardener", () -> EntityType.Builder.of(WanderingGardenerEntity::new, MobCategory.CREATURE).sized(0.6f, 1.95f).clientTrackingRange(10).build(BloomingNature.identifier("wandering_gardener").toString()));
    public static final Supplier<EntityType<WoodBoat>> MOD_BOAT = PlatformHelper.registerBoatType("mod_boat", WoodBoat::new, MobCategory.MISC, 1.375F, 0.5625F, 10);
    public static final Supplier<EntityType<WoodBoat>> MOD_CHEST_BOAT = PlatformHelper.<WoodBoat>registerBoatType("mod_chest_boat", WoodChestBoat::new, MobCategory.MISC, 1.375F, 0.5625F, 10);

    public static final RegistrySupplier<BlockEntityType<CompletionistBannerEntity>> BLOOMINGNATURE_BANNER = registerBlockEntity("bloomingnature_banner", () -> BlockEntityType.Builder.of(CompletionistBannerEntity::new, ObjectRegistry.BLOOMINGNATURE_BANNER.get(), ObjectRegistry.BLOOMINGNATURE_WALL_BANNER.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<SunflowerBlockEntity>> SUNFLOWER = registerBlockEntity("sunflower", () -> BlockEntityType.Builder.of(SunflowerBlockEntity::new, ObjectRegistry.SUNFLOWER.get()).build(null)
    );
    public static final RegistrySupplier<BlockEntityType<WoodSignBlockEntity>> MOD_SIGN = BLOCK_ENTITY_TYPES.register("mod_sign", () -> BlockEntityType.Builder.of(
            (pos, state) -> new WoodSignBlockEntity(EntityTypeRegistry.MOD_SIGN.get(), pos, state),
            WoodSet.all().stream().flatMap(set -> java.util.stream.Stream.of(set.sign.get(), set.wallSign.get())).toArray(Block[]::new)
    ).build(null));

    public static final RegistrySupplier<BlockEntityType<WoodHangingSignBlockEntity>> MOD_HANGING_SIGN = BLOCK_ENTITY_TYPES.register("mod_hanging_sign", () -> BlockEntityType.Builder.of(
            (pos, state) -> new WoodHangingSignBlockEntity(EntityTypeRegistry.MOD_HANGING_SIGN.get(), pos, state),
            WoodSet.all().stream().flatMap(set -> java.util.stream.Stream.of(set.hangingSign.get(), set.wallHangingSign.get())).toArray(Block[]::new)
    ).build(null));

    private static <T extends EntityType<?>> RegistrySupplier<T> registerEntityType(final String path, final Supplier<T> type) {
        return ENTITY_TYPES.register(BloomingNature.identifier(path), type);
    }

    private static <T extends BlockEntityType<?>> RegistrySupplier<T> registerBlockEntity(final String path, final Supplier<T> type) {
        return BLOCK_ENTITY_TYPES.register(BloomingNature.identifier(path), type);
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
