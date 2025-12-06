package net.satisfy.bloomingnature.core.world.feature.configured.decoration;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;

public record GroundLitterConfiguration(int tries, int xzSpread, int ySpread, WeightedStateProvider stateProvider) implements FeatureConfiguration {
    public static final Codec<GroundLitterConfiguration> CODEC = RecordCodecBuilder.create(instance ->
        instance.group(
            Codec.intRange(1, 64).fieldOf("tries").forGetter(GroundLitterConfiguration::tries),
            Codec.intRange(1, 16).fieldOf("xz_spread").forGetter(GroundLitterConfiguration::xzSpread),
            Codec.intRange(1, 16).fieldOf("y_spread").forGetter(GroundLitterConfiguration::ySpread),
            WeightedStateProvider.CODEC.fieldOf("to_place").forGetter(GroundLitterConfiguration::stateProvider)
        ).apply(instance, GroundLitterConfiguration::new)
    );
}