package satisfy.bloomingnature.registry;

import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import satisfy.bloomingnature.BloomingNature;
import satisfy.bloomingnature.entity.BisonEntity;
import satisfy.bloomingnature.entity.BoarEntity;
import satisfy.bloomingnature.entity.DeerEntity;
import satisfy.bloomingnature.entity.MossySheepEntity;
import satisfy.bloomingnature.entity.SheepEntity;
import satisfy.bloomingnature.entity.MuddyPigEntity;
import satisfy.bloomingnature.entity.OwlEntity;
import satisfy.bloomingnature.entity.PelicanEntity;
import satisfy.bloomingnature.entity.RaccoonEntity;
import satisfy.bloomingnature.entity.RedWolfEntity;
import satisfy.bloomingnature.entity.SquirrelEntity;
import satisfy.bloomingnature.entity.TermiteEntity;
import satisfy.bloomingnature.entity.TurkeyEntity;
import satisfy.bloomingnature.entity.WanderingGardenerEntity;
import satisfy.bloomingnature.util.BloomingNatureIdentifier;

import java.util.function.Supplier;

public class EntityRegistry {
    private static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BloomingNature.MOD_ID, Registries.ENTITY_TYPE);


    public static final RegistrySupplier<EntityType<WanderingGardenerEntity>> WANDERING_GARDENER = create("wandering_gardener",
            () -> EntityType.Builder.of(WanderingGardenerEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 1.95f)
                    .clientTrackingRange(10)
                    .build(BloomingNatureIdentifier.of("wandering_gardener").toString()));

    public static final RegistrySupplier<EntityType<RedWolfEntity>> RED_WOLF = create("red_wolf",
            () -> EntityType.Builder.of(RedWolfEntity::new, MobCategory.CREATURE)
                    .sized(0.4f, 1.5f)
                    .clientTrackingRange(10)
                    .build(BloomingNatureIdentifier.of("red_wolf").toString()));

    public static final RegistrySupplier<EntityType<PelicanEntity>> PELICAN = create("pelican",
            () -> EntityType.Builder.of(PelicanEntity::new, MobCategory.CREATURE)
                    .sized(0.9f, 1.3f)
                    .build(BloomingNatureIdentifier.of("pelican").toString())
    );

    public static final RegistrySupplier<EntityType<RaccoonEntity>> RACCOON = create("raccoon",
            () -> EntityType.Builder.of(RaccoonEntity::new, MobCategory.CREATURE)
                    .sized(0.9f, 1.3f)
                    .build(BloomingNatureIdentifier.of("raccoon").toString())
    );

    public static final RegistrySupplier<EntityType<MossySheepEntity>> MOSSY_SHEEP = create("mossy_sheep",
            () -> EntityType.Builder.of(MossySheepEntity::new, MobCategory.CREATURE)
                    .sized(0.9f, 1.3f)
                    .build(BloomingNatureIdentifier.of( "mossy_sheep").toString())
    );

    public static final RegistrySupplier<EntityType<SquirrelEntity>> SQUIRREL = create("squirrel",
            () -> EntityType.Builder.of(SquirrelEntity::new, MobCategory.CREATURE)
                    .sized(0.9f, 1.3f)
                    .build(BloomingNatureIdentifier.of("raccoon").toString())
    );

    public static final RegistrySupplier<EntityType<MuddyPigEntity>> MUDDY_PIG = create("muddy_pig",
            () -> EntityType.Builder.of(MuddyPigEntity::new, MobCategory.CREATURE)
                    .sized(0.9f, 1.3f)
                    .build(BloomingNatureIdentifier.of("muddy_pig").toString())
    );

    public static final RegistrySupplier<EntityType<TurkeyEntity>> TURKEY = create("turkey",
            () -> EntityType.Builder.of(TurkeyEntity::new, MobCategory.CREATURE)
                    .sized(0.9f, 1.3f)
                    .build(BloomingNatureIdentifier.of("turkey").toString())
    );

    public static final RegistrySupplier<EntityType<DeerEntity>> DEER = create("deer",
            () -> EntityType.Builder.of(DeerEntity::new, MobCategory.CREATURE)
                    .build(BloomingNatureIdentifier.of("deer").toString())
    );

    public static final RegistrySupplier<EntityType<OwlEntity>> OWL = create("owl",
            () -> EntityType.Builder.of(OwlEntity::new, MobCategory.CREATURE)
                    .build(BloomingNatureIdentifier.of("owl").toString())
    );

    public static final RegistrySupplier<EntityType<TermiteEntity>> TERMITE = create("termite",
            () -> EntityType.Builder.of(TermiteEntity::new, MobCategory.MONSTER)
                    .build(BloomingNatureIdentifier.of("termite").toString())
    );

    public static final RegistrySupplier<EntityType<BoarEntity>> BOAR = create("boar",
            () -> EntityType.Builder.of(BoarEntity::new, MobCategory.CREATURE)
                    .build(BloomingNatureIdentifier.of("boar").toString())
    );

    public static final RegistrySupplier<EntityType<BisonEntity>> BISON = create("bison",
            () -> EntityType.Builder.of(BisonEntity::new, MobCategory.CREATURE)
                    .build(BloomingNatureIdentifier.of("bison").toString())
    );



    public static <T extends EntityType<?>> RegistrySupplier<T> create(final String path, final Supplier<T> type) {
        return ENTITY_TYPES.register(BloomingNatureIdentifier.of(path), type);
    }

    public static void init() {
        BloomingNature.LOGGER.debug("Registering Entities for " + BloomingNature.MOD_ID);
        ENTITY_TYPES.register();
        EntityAttributeRegistry.register(WANDERING_GARDENER, WanderingGardenerEntity::createMobAttributes);
        EntityAttributeRegistry.register(MOSSY_SHEEP, SheepEntity::createMobAttributes);
        EntityAttributeRegistry.register(DEER, DeerEntity::createMobAttributes);
        EntityAttributeRegistry.register(PELICAN, PelicanEntity::createMobAttributes);
        EntityAttributeRegistry.register(RED_WOLF, RedWolfEntity::createMobAttributes);
        EntityAttributeRegistry.register(RACCOON, RaccoonEntity::createMobAttributes);
        EntityAttributeRegistry.register(SQUIRREL, SquirrelEntity::createMobAttributes);
        EntityAttributeRegistry.register(MUDDY_PIG, MuddyPigEntity::createMobAttributes);
        EntityAttributeRegistry.register(OWL, OwlEntity::createMobAttributes);
        EntityAttributeRegistry.register(BOAR, BoarEntity::createMobAttributes);
        EntityAttributeRegistry.register(TERMITE, TermiteEntity::createMobAttributes);
        EntityAttributeRegistry.register(BISON, BisonEntity::createMobAttributes);
        EntityAttributeRegistry.register(TURKEY, TurkeyEntity::createMobAttributes);
    }
}
