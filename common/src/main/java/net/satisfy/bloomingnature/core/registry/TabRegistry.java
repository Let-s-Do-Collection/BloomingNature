package net.satisfy.bloomingnature.core.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.satisfy.bloomingnature.BloomingNature;

import java.util.List;

@SuppressWarnings("unused")
public class TabRegistry {
    public static final DeferredRegister<CreativeModeTab> BLOOMINGNATURE_TABS = DeferredRegister.create(BloomingNature.MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static final RegistrySupplier<CreativeModeTab> BLOOMINGNATURE_TAB = BLOOMINGNATURE_TABS.register("bloomingnature", () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 1)
            .icon(() -> new ItemStack(ObjectRegistry.TWIGS.get()))
            .title(Component.translatable("creative_tab.bloomingnature"))
            .displayItems((parameters, out) -> {
                acceptTimber(out);
                acceptStone(out);
                acceptMeadow(out);
            })
            .build());

    public static void acceptTimber(CreativeModeTab.Output output) {
        for (WoodSet set : List.of(ObjectRegistry.LARCH, ObjectRegistry.BAOBAB, ObjectRegistry.ASPEN, ObjectRegistry.SWAMP_OAK, ObjectRegistry.SWAMP_CYPRESS,
                ObjectRegistry.FAN_PALM, ObjectRegistry.EBONY, ObjectRegistry.CHESTNUT, ObjectRegistry.FIR, ObjectRegistry.CACTUS, ObjectRegistry.CYPRESS)) {
            acceptWood(output, set);
            if (set == ObjectRegistry.SWAMP_OAK) {
                output.accept(ObjectRegistry.ORANGE_LEAVES.get());
                output.accept(ObjectRegistry.BLOOMING_OAK_SAPLING.get());
                output.accept(ObjectRegistry.BLOOMING_OAK_LEAVES.get());
            }
        }
    }

    private static void acceptWood(CreativeModeTab.Output output, WoodSet set) {
        if (set.log != null) {
            output.accept(set.log.get());
            output.accept(set.wood.get());
            output.accept(set.strippedLog.get());
            output.accept(set.strippedWood.get());
        }
        output.accept(set.planks.get());
        output.accept(set.stairs.get());
        output.accept(set.slab.get());
        output.accept(set.fence.get());
        output.accept(set.fenceGate.get());
        output.accept(set.door.get());
        output.accept(set.trapdoor.get());
        output.accept(set.window.get());
        output.accept(set.pressurePlate.get());
        output.accept(set.button.get());
        if (set.leaves != null) {
            output.accept(set.leaves.get());
        }
        if (set.sapling != null) {
            output.accept(set.sapling.get());
        }
        output.accept(set.signItem.get());
        output.accept(set.hangingSignItem.get());
        output.accept(set.boat.get());
        output.accept(set.chestBoat.get());
    }

    public static void acceptStone(CreativeModeTab.Output output) {
        output.accept(ObjectRegistry.TRAVERTIN.get());
        output.accept(ObjectRegistry.TRAVERTIN_SLAB.get());
        output.accept(ObjectRegistry.TRAVERTIN_STAIRS.get());
        output.accept(ObjectRegistry.TRAVERTIN_WALL.get());
        output.accept(ObjectRegistry.CRACKED_TRAVERTIN_BRICKS.get());
        output.accept(ObjectRegistry.MOSSY_TRAVERTIN.get());
        output.accept(ObjectRegistry.MOSSY_TRAVERTIN_SLAB.get());
        output.accept(ObjectRegistry.MOSSY_TRAVERTIN_STAIRS.get());
        output.accept(ObjectRegistry.MOSSY_TRAVERTIN_WALL.get());
        output.accept(ObjectRegistry.MOSSY_CHISELED_TRAVERTIN.get());
        output.accept(ObjectRegistry.CHISELED_TRAVERTIN.get());
        output.accept(ObjectRegistry.TRAVERTIN_BRICKS.get());
        output.accept(ObjectRegistry.TRAVERTIN_BRICK_SLAB.get());
        output.accept(ObjectRegistry.TRAVERTIN_BRICK_STAIRS.get());
        output.accept(ObjectRegistry.TRAVERTIN_BRICK_WALL.get());
        output.accept(ObjectRegistry.MOSSY_TRAVERTIN_BRICKS.get());
        output.accept(ObjectRegistry.MOSSY_TRAVERTIN_BRICK_SLAB.get());
        output.accept(ObjectRegistry.MOSSY_TRAVERTIN_BRICK_STAIRS.get());
        output.accept(ObjectRegistry.MOSSY_TRAVERTIN_BRICK_WALL.get());
        output.accept(ObjectRegistry.COBBLED_TRAVERTIN.get());
        output.accept(ObjectRegistry.COBBLED_TRAVERTIN_SLAB.get());
        output.accept(ObjectRegistry.COBBLED_TRAVERTIN_STAIRS.get());
        output.accept(ObjectRegistry.COBBLED_TRAVERTIN_WALL.get());
        output.accept(ObjectRegistry.MOSSY_COBBLED_TRAVERTIN.get());
        output.accept(ObjectRegistry.MOSSY_COBBLED_TRAVERTIN_SLAB.get());
        output.accept(ObjectRegistry.MOSSY_COBBLED_TRAVERTIN_STAIRS.get());
        output.accept(ObjectRegistry.MOSSY_COBBLED_TRAVERTIN_WALL.get());
        output.accept(ObjectRegistry.MARLSTONE.get());
        output.accept(ObjectRegistry.MARLSTONE_SLAB.get());
        output.accept(ObjectRegistry.MARLSTONE_STAIRS.get());
        output.accept(ObjectRegistry.MARLSTONE_WALL.get());
        output.accept(ObjectRegistry.CRACKED_MARLSTONE_BRICKS.get());
        output.accept(ObjectRegistry.MOSSY_MARLSTONE.get());
        output.accept(ObjectRegistry.MOSSY_MARLSTONE_SLAB.get());
        output.accept(ObjectRegistry.MOSSY_MARLSTONE_STAIRS.get());
        output.accept(ObjectRegistry.MOSSY_MARLSTONE_WALL.get());
        output.accept(ObjectRegistry.MOSSY_CHISELED_MARLSTONE.get());
        output.accept(ObjectRegistry.CHISELED_MARLSTONE.get());
        output.accept(ObjectRegistry.MARLSTONE_BRICKS.get());
        output.accept(ObjectRegistry.MARLSTONE_BRICK_SLAB.get());
        output.accept(ObjectRegistry.MARLSTONE_BRICK_STAIRS.get());
        output.accept(ObjectRegistry.MARLSTONE_BRICK_WALL.get());
        output.accept(ObjectRegistry.MOSSY_MARLSTONE_BRICKS.get());
        output.accept(ObjectRegistry.MOSSY_MARLSTONE_BRICK_SLAB.get());
        output.accept(ObjectRegistry.MOSSY_MARLSTONE_BRICK_STAIRS.get());
        output.accept(ObjectRegistry.MOSSY_MARLSTONE_BRICK_WALL.get());
        output.accept(ObjectRegistry.COBBLED_MARLSTONE.get());
        output.accept(ObjectRegistry.COBBLED_MARLSTONE_SLAB.get());
        output.accept(ObjectRegistry.COBBLED_MARLSTONE_STAIRS.get());
        output.accept(ObjectRegistry.COBBLED_MARLSTONE_WALL.get());
        output.accept(ObjectRegistry.MOSSY_COBBLED_MARLSTONE.get());
        output.accept(ObjectRegistry.MOSSY_COBBLED_MARLSTONE_SLAB.get());
        output.accept(ObjectRegistry.MOSSY_COBBLED_MARLSTONE_STAIRS.get());
        output.accept(ObjectRegistry.MOSSY_COBBLED_MARLSTONE_WALL.get());
        output.accept(ObjectRegistry.SLATE.get());
        output.accept(ObjectRegistry.SLATE_SLAB.get());
        output.accept(ObjectRegistry.SLATE_STAIRS.get());
        output.accept(ObjectRegistry.SLATE_WALL.get());
        output.accept(ObjectRegistry.CRACKED_SLATE_BRICKS.get());
        output.accept(ObjectRegistry.MOSSY_SLATE.get());
        output.accept(ObjectRegistry.MOSSY_SLATE_SLAB.get());
        output.accept(ObjectRegistry.MOSSY_SLATE_STAIRS.get());
        output.accept(ObjectRegistry.MOSSY_SLATE_WALL.get());
        output.accept(ObjectRegistry.MOSSY_CHISELED_SLATE.get());
        output.accept(ObjectRegistry.CHISELED_SLATE.get());
        output.accept(ObjectRegistry.SLATE_BRICKS.get());
        output.accept(ObjectRegistry.SLATE_BRICK_SLAB.get());
        output.accept(ObjectRegistry.SLATE_BRICK_STAIRS.get());
        output.accept(ObjectRegistry.SLATE_BRICK_WALL.get());
        output.accept(ObjectRegistry.MOSSY_SLATE_BRICKS.get());
        output.accept(ObjectRegistry.MOSSY_SLATE_BRICK_SLAB.get());
        output.accept(ObjectRegistry.MOSSY_SLATE_BRICK_STAIRS.get());
        output.accept(ObjectRegistry.MOSSY_SLATE_BRICK_WALL.get());
        output.accept(ObjectRegistry.COBBLED_SLATE.get());
        output.accept(ObjectRegistry.COBBLED_SLATE_SLAB.get());
        output.accept(ObjectRegistry.COBBLED_SLATE_STAIRS.get());
        output.accept(ObjectRegistry.COBBLED_SLATE_WALL.get());
        output.accept(ObjectRegistry.MOSSY_COBBLED_SLATE.get());
        output.accept(ObjectRegistry.MOSSY_COBBLED_SLATE_SLAB.get());
        output.accept(ObjectRegistry.MOSSY_COBBLED_SLATE_STAIRS.get());
        output.accept(ObjectRegistry.MOSSY_COBBLED_SLATE_WALL.get());
        output.accept(ObjectRegistry.LATERIT.get());
        output.accept(ObjectRegistry.LATERIT_SLAB.get());
        output.accept(ObjectRegistry.LATERIT_STAIRS.get());
        output.accept(ObjectRegistry.LATERIT_WALL.get());
        output.accept(ObjectRegistry.CRACKED_LATERIT_BRICKS.get());
        output.accept(ObjectRegistry.MOSSY_LATERIT_STONE.get());
        output.accept(ObjectRegistry.MOSSY_LATERIT_SLAB.get());
        output.accept(ObjectRegistry.MOSSY_LATERIT_STAIRS.get());
        output.accept(ObjectRegistry.MOSSY_LATERIT_WALL.get());
        output.accept(ObjectRegistry.MOSSY_CHISELED_LATERIT.get());
        output.accept(ObjectRegistry.CHISELED_LATERIT.get());
        output.accept(ObjectRegistry.LATERIT_BRICKS.get());
        output.accept(ObjectRegistry.LATERIT_BRICK_SLAB.get());
        output.accept(ObjectRegistry.LATERIT_BRICK_STAIRS.get());
        output.accept(ObjectRegistry.LATERIT_BRICK_WALL.get());
        output.accept(ObjectRegistry.MOSSY_LATERIT_BRICKS.get());
        output.accept(ObjectRegistry.MOSSY_LATERIT_BRICK_SLAB.get());
        output.accept(ObjectRegistry.MOSSY_LATERIT_BRICK_STAIRS.get());
        output.accept(ObjectRegistry.MOSSY_LATERIT_BRICK_WALL.get());
        output.accept(ObjectRegistry.COBBLED_LATERIT.get());
        output.accept(ObjectRegistry.COBBLED_LATERIT_SLAB.get());
        output.accept(ObjectRegistry.COBBLED_LATERIT_STAIRS.get());
        output.accept(ObjectRegistry.COBBLED_LATERIT_WALL.get());
        output.accept(ObjectRegistry.MOSSY_COBBLED_LATERIT.get());
        output.accept(ObjectRegistry.MOSSY_COBBLED_LATERIT_SLAB.get());
        output.accept(ObjectRegistry.MOSSY_COBBLED_LATERIT_STAIRS.get());
        output.accept(ObjectRegistry.MOSSY_COBBLED_LATERIT_WALL.get());
        output.accept(ObjectRegistry.MUSHROOM_BRICKS.get());
        output.accept(ObjectRegistry.MUSHROOM_BRICK_SLAB.get());
        output.accept(ObjectRegistry.MUSHROOM_BRICK_STAIRS.get());
        output.accept(ObjectRegistry.MUSHROOM_BRICK_WALL.get());
        output.accept(ObjectRegistry.BROWN_MUSHROOM_BRICKS.get());
        output.accept(ObjectRegistry.BROWN_MUSHROOM_BRICK_SLAB.get());
        output.accept(ObjectRegistry.BROWN_MUSHROOM_BRICK_STAIRS.get());
        output.accept(ObjectRegistry.BROWN_MUSHROOM_BRICK_WALL.get());
    }

    public static void acceptMeadow(CreativeModeTab.Output output) {
        output.accept(ObjectRegistry.MARSH_BLOCK.get());
        output.accept(ObjectRegistry.QUICKSAND.get());
        output.accept(ObjectRegistry.SAND.get());
        output.accept(ObjectRegistry.FOREST_MOSS.get());
        output.accept(ObjectRegistry.FOREST_MOSS_CARPET.get());
        output.accept(ObjectRegistry.FEN_MOSS.get());
        output.accept(ObjectRegistry.FEN_MOSS_CARPET.get());
        output.accept(ObjectRegistry.SUNGRASS.get());
        output.accept(ObjectRegistry.TALL_SUNGRASS.get());
        output.accept(ObjectRegistry.BLUFF_GRASS.get());
        output.accept(ObjectRegistry.TALL_BLUFF_GRASS.get());
        output.accept(ObjectRegistry.SILKGRASS.get());
        output.accept(ObjectRegistry.TALL_SILKGRASS.get());
        output.accept(ObjectRegistry.PAMPAS_GRASS.get());
        output.accept(ObjectRegistry.MOSSGRASS.get());
        output.accept(ObjectRegistry.RED_OAT_GRASS.get());
        output.accept(ObjectRegistry.TALL_RED_OAT_GRASS.get());
        output.accept(ObjectRegistry.DRY_BUSH.get());
        output.accept(ObjectRegistry.DRY_BUSH_TALL.get());
        output.accept(ObjectRegistry.DRY_GRASS.get());
        output.accept(ObjectRegistry.BOTTLEBRUSHES.get());
        output.accept(ObjectRegistry.BEGONIE.get());
        output.accept(ObjectRegistry.JOE_PYE.get());
        output.accept(ObjectRegistry.HYSSOP.get());
        output.accept(ObjectRegistry.CARDINAL.get());
        output.accept(ObjectRegistry.DAPHNE.get());
        output.accept(ObjectRegistry.GOLDEN_ROD.get());
        output.accept(ObjectRegistry.BIRD_OF_PARADISE.get());
        output.accept(ObjectRegistry.WHITE_ORCHID.get());
        output.accept(ObjectRegistry.BLUEBELL.get());
        output.accept(ObjectRegistry.GOATSBEARD.get());
        output.accept(ObjectRegistry.GENISTEAE.get());
        output.accept(ObjectRegistry.FORSYTHIA.get());
        output.accept(ObjectRegistry.ANEMONE.get());
        output.accept(ObjectRegistry.MOUNTAIN_LAUREL.get());
        output.accept(ObjectRegistry.TALL_MOUNTAIN_LAUREL.get());
        output.accept(ObjectRegistry.MOUNTAIN_SNOWBELL.get());
        output.accept(ObjectRegistry.MYOSOTIS.get());
        output.accept(ObjectRegistry.WILD_SUNFLOWER.get());
        output.accept(ObjectRegistry.FOXGLOVE_WHITE.get());
        output.accept(ObjectRegistry.FOXGLOVE_PINK.get());
        output.accept(ObjectRegistry.AMARYLLIS.get());
        output.accept(ObjectRegistry.GLADIOLUS.get());
        output.accept(ObjectRegistry.MUNSTEAD.get());
        output.accept(ObjectRegistry.DESERT_LILY.get());
        output.accept(ObjectRegistry.FREESIA_YELLOW.get());
        output.accept(ObjectRegistry.FREESIA_PINK.get());
        output.accept(ObjectRegistry.LUPINE_BLUE.get());
        output.accept(ObjectRegistry.TALL_LUPINE_BLUE.get());
        output.accept(ObjectRegistry.LUPINE_PURPLE.get());
        output.accept(ObjectRegistry.TALL_LUPINE_PURPLE.get());
        output.accept(ObjectRegistry.SUNFLOWER.get());
        output.accept(ObjectRegistry.SMALL_CACTUS.get());
        output.accept(ObjectRegistry.BARREL_CACTUS.get());
        output.accept(ObjectRegistry.PRICKLY_PEAR_CACTUS.get());
        output.accept(ObjectRegistry.REED.get());
        output.accept(ObjectRegistry.CATTAIL.get());
        output.accept(FloraRegistry.JUNGLE_FERN.get());
        output.accept(FloraRegistry.TALL_JUNGLE_FERN.get());
        output.accept(FloraRegistry.WILD_VINES.get());
        output.accept(FloraRegistry.FLOWERING_LILY_PAD_ITEM.get());
        output.accept(ObjectRegistry.FLOATING_LEAVES.get());
        output.accept(ObjectRegistry.PEBBLES.get());
        output.accept(ObjectRegistry.TWIGS.get());
        output.accept(ObjectRegistry.BLOOMINGNATURE_BANNER.get());
        output.accept(ObjectRegistry.WANDERING_GARDENER_SPAWN_EGG.get());
    }

    public static void init() {
        BLOOMINGNATURE_TABS.register();
    }
}
