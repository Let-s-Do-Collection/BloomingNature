package net.satisfy.bloomingnature.core.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.satisfy.bloomingnature.BloomingNature;

public final class ParticleRegistry {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(BloomingNature.MOD_ID, Registries.PARTICLE_TYPE);

    public static final RegistrySupplier<SimpleParticleType> FALLING_LEAF = PARTICLE_TYPES.register("falling_leaf", () -> new SimpleParticleType(false) {});

    private ParticleRegistry() {
    }

    public static void init() {
        PARTICLE_TYPES.register();
    }
}
