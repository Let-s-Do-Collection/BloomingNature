package net.satisfy.bloomingnature.core.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.satisfy.bloomingnature.core.util.BloomingNatureIdentifier;

import java.util.Set;


public class StorageTypeRegistry {
    public static final ResourceLocation FLOWER_POT_BIG = BloomingNatureIdentifier.of("flower_pot_big");
    public static final ResourceLocation FLOWER_BOX = BloomingNatureIdentifier.of("flower_box");

    public static Set<Block> registerBlocks(Set<Block> blocks) {
        blocks.add(ObjectRegistry.FLOWER_POT_BIG.get());
        blocks.add(ObjectRegistry.FLOWER_BOX.get());

        return blocks;
    }
}
