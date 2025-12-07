package net.satisfy.bloomingnature.core.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;

public class FlammableBlockRegistry {

    public static void init() {
        addFlammable(5, 20,
                ObjectRegistry.BAOBAB_PLANKS.get(), ObjectRegistry.BAOBAB_SLAB.get(), ObjectRegistry.BAOBAB_STAIRS.get(), ObjectRegistry.BAOBAB_FENCE.get(),
                ObjectRegistry.BAOBAB_FENCE_GATE.get(),
                ObjectRegistry.SWAMP_OAK_PLANKS.get(), ObjectRegistry.SWAMP_OAK_SLAB.get(), ObjectRegistry.SWAMP_OAK_STAIRS.get(),
                ObjectRegistry.SWAMP_OAK_FENCE.get(), ObjectRegistry.SWAMP_OAK_FENCE_GATE.get(),
                ObjectRegistry.SWAMP_CYPRESS_PLANKS.get(), ObjectRegistry.SWAMP_CYPRESS_SLAB.get(), ObjectRegistry.SWAMP_CYPRESS_STAIRS.get(),
                ObjectRegistry.SWAMP_CYPRESS_FENCE.get(), ObjectRegistry.SWAMP_CYPRESS_FENCE_GATE.get(),
                ObjectRegistry.LARCH_PLANKS.get(), ObjectRegistry.LARCH_SLAB.get(), ObjectRegistry.LARCH_STAIRS.get(),
                ObjectRegistry.LARCH_FENCE.get(), ObjectRegistry.LARCH_FENCE_GATE.get(),
                ObjectRegistry.FIR_PLANKS.get(), ObjectRegistry.FIR_SLAB.get(), ObjectRegistry.FIR_STAIRS.get(),
                ObjectRegistry.FIR_FENCE.get(), ObjectRegistry.FIR_FENCE_GATE.get(),
                ObjectRegistry.CHESTNUT_PLANKS.get(), ObjectRegistry.CHESTNUT_SLAB.get(), ObjectRegistry.CHESTNUT_STAIRS.get(),
                ObjectRegistry.CHESTNUT_FENCE.get(), ObjectRegistry.CHESTNUT_FENCE_GATE.get(),
                ObjectRegistry.FAN_PALM_PLANKS.get(), ObjectRegistry.FAN_PALM_SLAB.get(), ObjectRegistry.FAN_PALM_STAIRS.get(),
                ObjectRegistry.FAN_PALM_FENCE.get(), ObjectRegistry.FAN_PALM_FENCE_GATE.get(),
                ObjectRegistry.ASPEN_PLANKS.get(), ObjectRegistry.ASPEN_SLAB.get(), ObjectRegistry.ASPEN_STAIRS.get(),
                ObjectRegistry.ASPEN_FENCE.get(), ObjectRegistry.ASPEN_FENCE_GATE.get(),
                ObjectRegistry.EBONY_PLANKS.get(), ObjectRegistry.EBONY_SLAB.get(), ObjectRegistry.EBONY_STAIRS.get(),
                ObjectRegistry.EBONY_FENCE.get(), ObjectRegistry.EBONY_FENCE_GATE.get(),
                ObjectRegistry.CACTUS_PLANKS.get(), ObjectRegistry.CACTUS_SLAB.get(), ObjectRegistry.CACTUS_STAIRS.get(),
                ObjectRegistry.CACTUS_FENCE.get(), ObjectRegistry.CACTUS_FENCE_GATE.get(),
                ObjectRegistry.CYPRESS_PLANKS.get(), ObjectRegistry.CYPRESS_SLAB.get(), ObjectRegistry.CYPRESS_STAIRS.get(),
                ObjectRegistry.CYPRESS_FENCE.get(), ObjectRegistry.CYPRESS_FENCE_GATE.get()
        );

        addFlammable(5, 5,
                ObjectRegistry.SWAMP_OAK_LOG.get(), ObjectRegistry.SWAMP_OAK_WOOD.get(), ObjectRegistry.STRIPPED_SWAMP_OAK_LOG.get(), ObjectRegistry.STRIPPED_SWAMP_OAK_WOOD.get(),
                ObjectRegistry.SWAMP_CYPRESS_LOG.get(), ObjectRegistry.SWAMP_CYPRESS_WOOD.get(), ObjectRegistry.STRIPPED_SWAMP_CYPRESS_LOG.get(), ObjectRegistry.STRIPPED_SWAMP_CYPRESS_WOOD.get(),
                ObjectRegistry.LARCH_LOG.get(), ObjectRegistry.LARCH_WOOD.get(), ObjectRegistry.STRIPPED_LARCH_LOG.get(), ObjectRegistry.STRIPPED_LARCH_WOOD.get(),
                ObjectRegistry.FIR_LOG.get(), ObjectRegistry.FIR_WOOD.get(), ObjectRegistry.STRIPPED_FIR_LOG.get(), ObjectRegistry.STRIPPED_FIR_WOOD.get(),
                ObjectRegistry.CHESTNUT_LOG.get(), ObjectRegistry.CHESTNUT_WOOD.get(), ObjectRegistry.STRIPPED_CHESTNUT_LOG.get(), ObjectRegistry.STRIPPED_CHESTNUT_WOOD.get(),
                ObjectRegistry.FAN_PALM_LOG.get(), ObjectRegistry.FAN_PALM_WOOD.get(), ObjectRegistry.STRIPPED_FAN_PALM_LOG.get(), ObjectRegistry.STRIPPED_FAN_PALM_WOOD.get(),
                ObjectRegistry.ASPEN_LOG.get(), ObjectRegistry.ASPEN_WOOD.get(), ObjectRegistry.STRIPPED_ASPEN_LOG.get(), ObjectRegistry.STRIPPED_ASPEN_WOOD.get(),
                ObjectRegistry.EBONY_LOG.get(), ObjectRegistry.EBONY_WOOD.get(), ObjectRegistry.STRIPPED_EBONY_LOG.get(), ObjectRegistry.STRIPPED_EBONY_WOOD.get(),
                ObjectRegistry.BAOBAB_LOG.get(), ObjectRegistry.BAOBAB_WOOD.get(), ObjectRegistry.STRIPPED_BAOBAB_LOG.get(), ObjectRegistry.STRIPPED_BAOBAB_WOOD.get(),
                ObjectRegistry.CYPRESS_LOG.get(), ObjectRegistry.CYPRESS_WOOD.get(), ObjectRegistry.STRIPPED_CYPRESS_LOG.get(), ObjectRegistry.STRIPPED_CYPRESS_WOOD.get()
        );

        addFlammable(30, 60,
                ObjectRegistry.BAOBAB_LEAVES.get(),
                ObjectRegistry.SWAMP_OAK_LEAVES.get(),
                ObjectRegistry.SWAMP_CYPRESS_LEAVES.get(),
                ObjectRegistry.LARCH_LEAVES.get(),
                ObjectRegistry.FIR_LEAVES.get(),
                ObjectRegistry.CHESTNUT_LEAVES.get(),
                ObjectRegistry.FAN_PALM_LEAVES.get(),
                ObjectRegistry.ASPEN_LEAVES.get(),
                ObjectRegistry.EBONY_LEAVES.get(),
                ObjectRegistry.ORANGE_LEAVES.get(),
                ObjectRegistry.CYPRESS_LEAVES.get(),
                ObjectRegistry.BLOOMING_OAK_LEAVES.get()
        );
    }

    public static void addFlammable(int burnOdd, int igniteOdd, Block... blocks) {
        FireBlock fireBlock = (FireBlock) Blocks.FIRE;
        for (Block block : blocks) {
            fireBlock.setFlammable(block, burnOdd, igniteOdd);
        }
    }
}
