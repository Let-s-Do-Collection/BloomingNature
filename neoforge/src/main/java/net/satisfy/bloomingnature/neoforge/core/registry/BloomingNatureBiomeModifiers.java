package net.satisfy.bloomingnature.neoforge.core.registry;

import com.mojang.serialization.MapCodec;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.satisfy.bloomingnature.BloomingNature;
import net.satisfy.bloomingnature.neoforge.core.world.biome.BloomingNatureBiomeEffectsModifier;

import java.util.function.Supplier;

public final class BloomingNatureBiomeModifiers {
    public static final DeferredRegister<MapCodec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS =
            DeferredRegister.create(NeoForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, BloomingNature.MOD_ID);

    public static final Supplier<MapCodec<BloomingNatureBiomeEffectsModifier>> BIOME_EFFECTS =
            BIOME_MODIFIER_SERIALIZERS.register("biome_effects", () -> BloomingNatureBiomeEffectsModifier.CODEC);
}