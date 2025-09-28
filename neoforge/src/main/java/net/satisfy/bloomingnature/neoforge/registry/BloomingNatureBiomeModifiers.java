package net.satisfy.bloomingnature.neoforge.registry;

import com.mojang.serialization.MapCodec;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.satisfy.bloomingnature.BloomingNature;


public class BloomingNatureBiomeModifiers {

    public static DeferredRegister<MapCodec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS =
            DeferredRegister.create(NeoForgeRegistries.BIOME_MODIFIER_SERIALIZERS, BloomingNature.MOD_ID);
}