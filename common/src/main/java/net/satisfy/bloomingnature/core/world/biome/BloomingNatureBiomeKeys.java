package net.satisfy.bloomingnature.core.world.biome;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.satisfy.bloomingnature.BloomingNature;

public final class BloomingNatureBiomeKeys {
    public static final ResourceKey<Biome> FOREST_EDGE = ResourceKey.create(Registries.BIOME, BloomingNature.identifier("forest_edge"));
    public static final ResourceKey<Biome> FLOWER_GLADE = ResourceKey.create(Registries.BIOME, BloomingNature.identifier("flower_glade"));
    public static final ResourceKey<Biome> BRUSHLANDS = ResourceKey.create(Registries.BIOME, BloomingNature.identifier("brushland"));
    public static final ResourceKey<Biome> CYPRESS_FIELDS = ResourceKey.create(Registries.BIOME, BloomingNature.identifier("cypress_fields"));
    public static final ResourceKey<Biome> GOLDEN_GLADE = ResourceKey.create(Registries.BIOME, BloomingNature.identifier("golden_glade"));
    public static final ResourceKey<Biome> BAOBAB_SAVANNA = ResourceKey.create(Registries.BIOME, BloomingNature.identifier("baobab_savanna"));
    public static final ResourceKey<Biome> DESERT_OASIS = ResourceKey.create(Registries.BIOME, BloomingNature.identifier("desert_oasis"));
    public static final ResourceKey<Biome> DESERT_RIVER = ResourceKey.create(Registries.BIOME, BloomingNature.identifier("desert_river"));
    public static final ResourceKey<Biome> JUNGLE_RIVER = ResourceKey.create(Registries.BIOME, BloomingNature.identifier("jungle_river"));
    public static final ResourceKey<Biome> COLD_RIVER = ResourceKey.create(Registries.BIOME, BloomingNature.identifier("cold_river"));
    public static final ResourceKey<Biome> COLD_GRASSLAND = ResourceKey.create(Registries.BIOME, BloomingNature.identifier("cold_grassland"));
    public static final ResourceKey<Biome> LARCH_FOREST = ResourceKey.create(Registries.BIOME, BloomingNature.identifier("larch_forest"));
    public static final ResourceKey<Biome> MARSHLAND = ResourceKey.create(Registries.BIOME, BloomingNature.identifier("marshland"));

    private BloomingNatureBiomeKeys() {
    }
}
