package satisfy.bloomingnature.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import satisfy.bloomingnature.BloomingNature;
import satisfy.bloomingnature.util.BloomingNatureIdentifier;

public class SoundRegistry {
    public static final Registrar<SoundEvent> SOUND_EVENTS = DeferredRegister.create(BloomingNature.MOD_ID, Registries.SOUND_EVENT).getRegistrar();

    public static final RegistrySupplier<SoundEvent> DEER_AMBIENT = create("deer_ambient");
    public static final RegistrySupplier<SoundEvent> DEER_HURT = create("deer_hurt");
    public static final RegistrySupplier<SoundEvent> DEER_DEATH = create("deer_death");
    public static final RegistrySupplier<SoundEvent> RACCOON_AMBIENT = create("raccoon_ambient");
    public static final RegistrySupplier<SoundEvent> RACCOON_HURT = create("raccoon_hurt");
    public static final RegistrySupplier<SoundEvent> RACCOON_DEATH = create("raccoon_death");
    public static final RegistrySupplier<SoundEvent> SQUIRREL_AMBIENT = create("squirrel_ambient");
    public static final RegistrySupplier<SoundEvent> SQUIRREL_HURT = create("squirrel_hurt");
    public static final RegistrySupplier<SoundEvent> SQUIRREL_DEATH = create("squirrel_death");
    public static final RegistrySupplier<SoundEvent> OWL_AMBIENT = create("owl_ambient");
    public static final RegistrySupplier<SoundEvent> OWL_HURT = create("owl_hurt");
    public static final RegistrySupplier<SoundEvent> OWL_DEATH = create("owl_death");
    public static final RegistrySupplier<SoundEvent> BISON_AMBIENT = create("bison_ambient");
    public static final RegistrySupplier<SoundEvent> BISON_HURT = create("bison_hurt");
    public static final RegistrySupplier<SoundEvent> BISON_ANGRY = create("bison_angry");
    public static final RegistrySupplier<SoundEvent> BISON_DEATH = create("bison_death");
    public static final RegistrySupplier<SoundEvent> PELICAN_AMBIENT = create("pelican_ambient");
    public static final RegistrySupplier<SoundEvent> PELICAN_HURT = create("pelican_hurt");
    public static final RegistrySupplier<SoundEvent> PELICAN_DEATH = create("pelican_death");
    public static final RegistrySupplier<SoundEvent> TURKEY_AMBIENT = create("turkey_ambient");
    public static final RegistrySupplier<SoundEvent> TURKEY_HURT = create("turkey_hurt");
    public static final RegistrySupplier<SoundEvent> TURKEY_DEATH = create("turkey_death");
    public static final RegistrySupplier<SoundEvent> BOAR_AMBIENT = create("boar_ambient");
    public static final RegistrySupplier<SoundEvent> BOAR_HURT = create("boar_hurt");
    public static final RegistrySupplier<SoundEvent> BOAR_DEATH = create("boar_death");
    public static final RegistrySupplier<SoundEvent> RED_WOLF_AMBIENT = create("red_wolf_ambient");
    public static final RegistrySupplier<SoundEvent> RED_WOLF_HURT = create("red_wolf_hurt");
    public static final RegistrySupplier<SoundEvent> RED_WOLF_DEATH = create("red_wolf_death");
    public static final RegistrySupplier<SoundEvent> RED_WOLF_AGGRO = create("red_wolf_aggro");




    private static RegistrySupplier<SoundEvent> create(String name) {
        final ResourceLocation id = BloomingNatureIdentifier.of(name);
        return SOUND_EVENTS.register(id, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void init() {
        BloomingNature.LOGGER.debug("Registering Sounds for " + BloomingNature.MOD_ID);
    }
}
