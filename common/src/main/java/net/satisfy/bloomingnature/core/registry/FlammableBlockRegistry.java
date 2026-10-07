package net.satisfy.bloomingnature.core.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;

public class FlammableBlockRegistry {

    public static void init() {
        for (WoodSet set : WoodSet.all()) {
            set.forEachPlankBlock(block -> addFlammable(5, 20, block));
            set.forEachLogBlock(block -> addFlammable(5, 5, block));
            if (set.leaves != null) {
                addFlammable(30, 60, set.leaves.get());
            }
        }
        addFlammable(30, 60, ObjectRegistry.ORANGE_LEAVES.get(), ObjectRegistry.BLOOMING_OAK_LEAVES.get());
    }

    public static void addFlammable(int burnOdd, int igniteOdd, Block... blocks) {
        FireBlock fireBlock = (FireBlock) Blocks.FIRE;
        for (Block block : blocks) {
            fireBlock.setFlammable(block, burnOdd, igniteOdd);
        }
    }
}
