package net.satisfy.bloomingnature.neoforge.core.world.biome;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo;
import net.satisfy.bloomingnature.neoforge.core.registry.BloomingNatureBiomeModifiers;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public record BloomingNatureBiomeEffectsModifier(
        HolderSet<Biome> biomes,
        Optional<Integer> grassColor,
        Optional<Integer> foliageColor,
        Optional<Integer> waterColor,
        Optional<Integer> waterFogColor
) implements BiomeModifier {

    public static final MapCodec<BloomingNatureBiomeEffectsModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Biome.LIST_CODEC.fieldOf("biomes").forGetter(BloomingNatureBiomeEffectsModifier::biomes),
                    Codec.INT.optionalFieldOf("grass_color").forGetter(BloomingNatureBiomeEffectsModifier::grassColor),
                    Codec.INT.optionalFieldOf("foliage_color").forGetter(BloomingNatureBiomeEffectsModifier::foliageColor),
                    Codec.INT.optionalFieldOf("water_color").forGetter(BloomingNatureBiomeEffectsModifier::waterColor),
                    Codec.INT.optionalFieldOf("water_fog_color").forGetter(BloomingNatureBiomeEffectsModifier::waterFogColor)
            ).apply(instance, BloomingNatureBiomeEffectsModifier::new)
    );

    @Override
    public void modify(@NotNull Holder<Biome> biome, @NotNull Phase phase, ModifiableBiomeInfo.BiomeInfo.@NotNull Builder builder) {
        if (!biomes.contains(biome)) {
            return;
        }
        if (phase != Phase.BEFORE_EVERYTHING && phase != Phase.MODIFY) {
            return;
        }

        var effects = builder.getSpecialEffects();

        grassColor.ifPresent(effects::grassColorOverride);
        foliageColor.ifPresent(effects::foliageColorOverride);
        waterColor.ifPresent(effects::waterColor);
        waterFogColor.ifPresent(effects::waterFogColor);
    }

    @Override
    public @NotNull MapCodec<? extends BiomeModifier> codec() {
        return BloomingNatureBiomeModifiers.BIOME_EFFECTS.get();
    }
}