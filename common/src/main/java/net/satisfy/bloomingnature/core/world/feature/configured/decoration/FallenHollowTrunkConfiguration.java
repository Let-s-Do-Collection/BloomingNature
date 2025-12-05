package net.satisfy.bloomingnature.core.world.feature.configured.decoration;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record FallenHollowTrunkConfiguration(int length, BlockState log, BlockState moss, boolean brown, boolean red, boolean vegetation) implements FeatureConfiguration {
    public static final Codec<FallenHollowTrunkConfiguration> CODEC = RecordCodecBuilder.create(i -> i.group(
                    Codec.intRange(1, 128).fieldOf("length").forGetter(FallenHollowTrunkConfiguration::length),
                    BlockState.CODEC.fieldOf("log_block").forGetter(FallenHollowTrunkConfiguration::log),
                    BlockState.CODEC.fieldOf("moss_block").forGetter(FallenHollowTrunkConfiguration::moss),
                    Codec.BOOL.fieldOf("enable_brown_mushroom").forGetter(FallenHollowTrunkConfiguration::brown),
                    Codec.BOOL.fieldOf("enable_red_mushroom").forGetter(FallenHollowTrunkConfiguration::red),
                    Codec.BOOL.fieldOf("enable_vegetation").forGetter(FallenHollowTrunkConfiguration::vegetation)
            ).apply(i, FallenHollowTrunkConfiguration::new)
    );
}