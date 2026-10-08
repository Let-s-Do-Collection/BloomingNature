package net.satisfy.bloomingnature.core.registry;

import net.satisfy.foundation.flammable.FoundationFlammables;

public class FlammableBlockRegistry {

    public static void init() {
        for (WoodSet set : WoodSet.all()) {
            set.forEachPlankBlock(block -> FoundationFlammables.wood(() -> block));
            set.forEachLogBlock(block -> FoundationFlammables.register(5, 5, () -> block));
            if (set.leaves != null) {
                FoundationFlammables.register(30, 60, set.leaves);
            }
        }
        FoundationFlammables.register(30, 60, ObjectRegistry.ORANGE_LEAVES, ObjectRegistry.BLOOMING_OAK_LEAVES);
        FoundationFlammables.apply();
    }
}
