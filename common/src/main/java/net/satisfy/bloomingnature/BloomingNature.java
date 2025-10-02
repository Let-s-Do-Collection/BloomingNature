package net.satisfy.bloomingnature;

import dev.architectury.hooks.item.tool.AxeItemHooks;
import net.minecraft.resources.ResourceLocation;
import net.satisfy.bloomingnature.core.registry.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BloomingNature {
    public static final String MOD_ID = "bloomingnature";

    public static ResourceLocation identifier(String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }

    public static void init() {
        EntityTypeRegistry.init();
        ObjectRegistry.init();
        TabRegistry.init();
        PlacerTypesRegistry.init();
    }

    public static void commonInit() {
        FlammableBlockRegistry.init();
        AxeItemHooks.addStrippable(ObjectRegistry.ASPEN_LOG.get(), ObjectRegistry.STRIPPED_ASPEN_LOG.get());
        AxeItemHooks.addStrippable(ObjectRegistry.ASPEN_WOOD.get(), ObjectRegistry.STRIPPED_ASPEN_WOOD.get());
        AxeItemHooks.addStrippable(ObjectRegistry.BAOBAB_LOG.get(), ObjectRegistry.STRIPPED_BAOBAB_LOG.get());
        AxeItemHooks.addStrippable(ObjectRegistry.BAOBAB_WOOD.get(), ObjectRegistry.STRIPPED_BAOBAB_WOOD.get());
        AxeItemHooks.addStrippable(ObjectRegistry.SWAMP_CYPRESS_LOG.get(), ObjectRegistry.STRIPPED_SWAMP_CYPRESS_LOG.get());
        AxeItemHooks.addStrippable(ObjectRegistry.SWAMP_CYPRESS_WOOD.get(), ObjectRegistry.STRIPPED_SWAMP_CYPRESS_WOOD.get());
        AxeItemHooks.addStrippable(ObjectRegistry.SWAMP_OAK_LOG.get(), ObjectRegistry.STRIPPED_SWAMP_OAK_LOG.get());
        AxeItemHooks.addStrippable(ObjectRegistry.SWAMP_OAK_WOOD.get(), ObjectRegistry.STRIPPED_SWAMP_OAK_WOOD.get());
        AxeItemHooks.addStrippable(ObjectRegistry.FAN_PALM_LOG.get(), ObjectRegistry.STRIPPED_FAN_PALM_LOG.get());
        AxeItemHooks.addStrippable(ObjectRegistry.FAN_PALM_WOOD.get(), ObjectRegistry.STRIPPED_FAN_PALM_WOOD.get());
        AxeItemHooks.addStrippable(ObjectRegistry.FIR_LOG.get(), ObjectRegistry.STRIPPED_FIR_LOG.get());
        AxeItemHooks.addStrippable(ObjectRegistry.FIR_WOOD.get(), ObjectRegistry.STRIPPED_FIR_WOOD.get());
        AxeItemHooks.addStrippable(ObjectRegistry.LARCH_LOG.get(), ObjectRegistry.STRIPPED_LARCH_LOG.get());
        AxeItemHooks.addStrippable(ObjectRegistry.LARCH_WOOD.get(), ObjectRegistry.STRIPPED_LARCH_WOOD.get());
        AxeItemHooks.addStrippable(ObjectRegistry.EBONY_LOG.get(), ObjectRegistry.STRIPPED_EBONY_LOG.get());
        AxeItemHooks.addStrippable(ObjectRegistry.EBONY_WOOD.get(), ObjectRegistry.STRIPPED_EBONY_WOOD.get());
        AxeItemHooks.addStrippable(ObjectRegistry.CHESTNUT_LOG.get(), ObjectRegistry.STRIPPED_CHESTNUT_LOG.get());
        AxeItemHooks.addStrippable(ObjectRegistry.CHESTNUT_WOOD.get(), ObjectRegistry.STRIPPED_CHESTNUT_WOOD.get());
    }
}

