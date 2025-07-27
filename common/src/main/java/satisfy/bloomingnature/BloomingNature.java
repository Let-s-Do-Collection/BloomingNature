package satisfy.bloomingnature;

import de.cristelknight.doapi.DoApiEP;
import dev.architectury.hooks.item.tool.AxeItemHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import satisfy.bloomingnature.registry.*;
import satisfy.bloomingnature.util.BloomingNatureIdentifier;

public class BloomingNature {
    public static final String MOD_ID = "bloomingnature";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static void init() {
        DataFixerRegistry.init();
        ObjectRegistry.init();
        BoatsAndSignsRegistry.init();
        EntityRegistry.init();
        TabRegistry.init();
        PlacerTypesRegistry.init();
        SoundRegistry.init();
        DoApiEP.registerBuiltInPack(BloomingNature.MOD_ID, BloomingNatureIdentifier.of("bushy_leaves"), false);
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

