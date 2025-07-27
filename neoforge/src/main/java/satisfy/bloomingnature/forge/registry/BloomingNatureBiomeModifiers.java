package satisfy.bloomingnature.forge.registry;

import com.mojang.serialization.Codec;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import satisfy.bloomingnature.BloomingNature;
import satisfy.bloomingnature.forge.world.AddAnimalsBiomeModifier;

public class BloomingNatureBiomeModifiers {

    public static DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, BloomingNature.MOD_ID);

    public static RegistryObject<Codec<AddAnimalsBiomeModifier>> ADD_ANIMALS_CODEC = BIOME_MODIFIER_SERIALIZERS.register("add_animals", () -> Codec.unit(AddAnimalsBiomeModifier::new));
}

