package satisfy.bloomingnature.registry;


import de.cristelknight.doapi.DoApiCommonEP;
import de.cristelknight.doapi.terraform.boat.TerraformBoatType;
import de.cristelknight.doapi.terraform.boat.item.TerraformBoatItemHelper;
import de.cristelknight.doapi.terraform.sign.TerraformSignHelper;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.Block;
import satisfy.bloomingnature.util.BloomingNatureIdentifier;

public class BoatsAndSignsRegistry {
    public static ResourceLocation ASPEN_BOAT_TYPE = BloomingNatureIdentifier.of("aspen");
    public static ResourceLocation LARCH_BOAT_TYPE = BloomingNatureIdentifier.of("larch");
    public static ResourceLocation SWAMP_OAK_BOAT_TYPE = BloomingNatureIdentifier.of("swamp_oak");
    public static ResourceLocation BAOBAB_BOAT_TYPE = BloomingNatureIdentifier.of("baobab");
    public static ResourceLocation FAN_PALM_BOAT_TYPE = BloomingNatureIdentifier.of("fan_palm");
    public static ResourceLocation FIR_BOAT_TYPE = BloomingNatureIdentifier.of("fir");
    public static ResourceLocation SWAMP_CYPRESS_BOAT_TYPE = BloomingNatureIdentifier.of("swamp_cypress");
    public static ResourceLocation CHESTNUT_BOAT_TYPE = BloomingNatureIdentifier.of("chestnut");
    public static ResourceLocation EBONY_BOAT_TYPE = BloomingNatureIdentifier.of("ebony");

    public static final ResourceLocation ASPEN_SIGN_TEXTURE = BloomingNatureIdentifier.of("entity/signs/aspen");
    public static final ResourceLocation LARCH_SIGN_TEXTURE = BloomingNatureIdentifier.of("entity/signs/larch");
    public static final ResourceLocation SWAMP_OAK_SIGN_TEXTURE = BloomingNatureIdentifier.of("entity/signs/swamp_oak");
    public static final ResourceLocation BAOBAB_SIGN_TEXTURE = BloomingNatureIdentifier.of("entity/signs/baobab");
    public static final ResourceLocation FAN_PALM_SIGN_TEXTURE = BloomingNatureIdentifier.of("entity/signs/fan_palm");
    public static final ResourceLocation FIR_SIGN_TEXTURE = BloomingNatureIdentifier.of("entity/signs/fir");
    public static final ResourceLocation SWAMP_CYPRESS_SIGN_TEXTURE = BloomingNatureIdentifier.of("entity/signs/swamp_cypress");
    public static final ResourceLocation CHESTNUT_SIGN_TEXTURE = BloomingNatureIdentifier.of("entity/signs/chestnut");
    public static final ResourceLocation EBONY_SIGN_TEXTURE = BloomingNatureIdentifier.of("entity/signs/ebony");

    public static final RegistrySupplier<Block> ASPEN_SIGN = ObjectRegistry.registerWithoutItem("aspen_sign", () -> TerraformSignHelper.getSign(ASPEN_SIGN_TEXTURE));
    public static final RegistrySupplier<Block> ASPEN_WALL_SIGN = ObjectRegistry.registerWithoutItem("aspen_wall_sign", () -> TerraformSignHelper.getWallSign(ASPEN_SIGN_TEXTURE));
    public static final RegistrySupplier<Item> ASPEN_SIGN_ITEM = ObjectRegistry.registerItem("aspen_sign", () -> new SignItem(ObjectRegistry.getSettings().stacksTo(16), ASPEN_SIGN.get(), ASPEN_WALL_SIGN.get()));
    public static final RegistrySupplier<Block> LARCH_SIGN = ObjectRegistry.registerWithoutItem("larch_sign", () -> TerraformSignHelper.getSign(LARCH_SIGN_TEXTURE));
    public static final RegistrySupplier<Block> LARCH_WALL_SIGN = ObjectRegistry.registerWithoutItem("larch_wall_sign", () -> TerraformSignHelper.getWallSign(LARCH_SIGN_TEXTURE));
    public static final RegistrySupplier<Item> LARCH_SIGN_ITEM = ObjectRegistry.registerItem("larch_sign", () -> new SignItem(ObjectRegistry.getSettings().stacksTo(16), LARCH_SIGN.get(), LARCH_WALL_SIGN.get()));
    public static final RegistrySupplier<Block> SWAMP_OAK_SIGN = ObjectRegistry.registerWithoutItem("swamp_oak_sign", () -> TerraformSignHelper.getSign(SWAMP_OAK_SIGN_TEXTURE));
    public static final RegistrySupplier<Block> SWAMP_OAK_WALL_SIGN = ObjectRegistry.registerWithoutItem("swamp_oak_wall_sign", () -> TerraformSignHelper.getWallSign(SWAMP_OAK_SIGN_TEXTURE));
    public static final RegistrySupplier<Item> SWAMP_OAK_SIGN_ITEM = ObjectRegistry.registerItem("swamp_oak_sign", () -> new SignItem(ObjectRegistry.getSettings().stacksTo(16), SWAMP_OAK_SIGN.get(), SWAMP_OAK_WALL_SIGN.get()));
    public static final RegistrySupplier<Block> BAOBAB_SIGN = ObjectRegistry.registerWithoutItem("baobab_sign", () -> TerraformSignHelper.getSign(BAOBAB_SIGN_TEXTURE));
    public static final RegistrySupplier<Block> BAOBAB_WALL_SIGN = ObjectRegistry.registerWithoutItem("baobab_wall_sign", () -> TerraformSignHelper.getWallSign(BAOBAB_SIGN_TEXTURE));
    public static final RegistrySupplier<Item> BAOBAB_SIGN_ITEM = ObjectRegistry.registerItem("baobab_sign", () -> new SignItem(ObjectRegistry.getSettings().stacksTo(16), BAOBAB_SIGN.get(), BAOBAB_WALL_SIGN.get()));
    public static final RegistrySupplier<Block> FAN_PALM_SIGN = ObjectRegistry.registerWithoutItem("fan_palm_sign", () -> TerraformSignHelper.getSign(FAN_PALM_SIGN_TEXTURE));
    public static final RegistrySupplier<Block> FAN_PALM_WALL_SIGN = ObjectRegistry.registerWithoutItem("fan_palm_wall_sign", () -> TerraformSignHelper.getWallSign(FAN_PALM_SIGN_TEXTURE));
    public static final RegistrySupplier<Item> FAN_PALM_SIGN_ITEM = ObjectRegistry.registerItem("fan_palm_sign", () -> new SignItem(ObjectRegistry.getSettings().stacksTo(16), FAN_PALM_SIGN.get(), FAN_PALM_WALL_SIGN.get()));
    public static final RegistrySupplier<Block> FIR_SIGN = ObjectRegistry.registerWithoutItem("fir_sign", () -> TerraformSignHelper.getSign(FIR_SIGN_TEXTURE));
    public static final RegistrySupplier<Block> FIR_WALL_SIGN = ObjectRegistry.registerWithoutItem("fir_wall_sign", () -> TerraformSignHelper.getWallSign(FIR_SIGN_TEXTURE));
    public static final RegistrySupplier<Item> FIR_SIGN_ITEM = ObjectRegistry.registerItem("fir_sign", () -> new SignItem(ObjectRegistry.getSettings().stacksTo(16), FIR_SIGN.get(), FIR_WALL_SIGN.get()));
      public static final RegistrySupplier<Block> CHESTNUT_SIGN = ObjectRegistry.registerWithoutItem("chestnut_sign", () -> TerraformSignHelper.getSign(CHESTNUT_SIGN_TEXTURE));
    public static final RegistrySupplier<Block> CHESTNUT_WALL_SIGN = ObjectRegistry.registerWithoutItem("chestnut_wall_sign", () -> TerraformSignHelper.getWallSign(CHESTNUT_SIGN_TEXTURE));
    public static final RegistrySupplier<Item> CHESTNUT_SIGN_ITEM = ObjectRegistry.registerItem("chestnut_sign", () -> new SignItem(ObjectRegistry.getSettings().stacksTo(16), CHESTNUT_SIGN.get(), CHESTNUT_WALL_SIGN.get()));
    public static final RegistrySupplier<Block> EBONY_SIGN = ObjectRegistry.registerWithoutItem("ebony_sign", () -> TerraformSignHelper.getSign(EBONY_SIGN_TEXTURE));
    public static final RegistrySupplier<Block> EBONY_WALL_SIGN = ObjectRegistry.registerWithoutItem("ebony_wall_sign", () -> TerraformSignHelper.getWallSign(EBONY_SIGN_TEXTURE));
    public static final RegistrySupplier<Item> EBONY_SIGN_ITEM = ObjectRegistry.registerItem("ebony_sign", () -> new SignItem(ObjectRegistry.getSettings().stacksTo(16), EBONY_SIGN.get(), EBONY_WALL_SIGN.get()));
    public static final RegistrySupplier<Block> SWAMP_CYPRESS_SIGN = ObjectRegistry.registerWithoutItem("swamp_cypress_sign", () -> TerraformSignHelper.getSign(SWAMP_CYPRESS_SIGN_TEXTURE));
    public static final RegistrySupplier<Block> SWAMP_CYPRESS_WALL_SIGN = ObjectRegistry.registerWithoutItem("swamp_cypress_wall_sign", () -> TerraformSignHelper.getWallSign(SWAMP_CYPRESS_SIGN_TEXTURE));
    public static final RegistrySupplier<Item> SWAMP_CYPRESS_SIGN_ITEM = ObjectRegistry.registerItem("swamp_cypress_sign", () -> new SignItem(ObjectRegistry.getSettings().stacksTo(16), SWAMP_CYPRESS_SIGN.get(), SWAMP_CYPRESS_WALL_SIGN.get()));

    public static final ResourceLocation ASPEN_HANGING_SIGN_TEXTURE = BloomingNatureIdentifier.of("entity/signs/hanging/aspen");
    public static final ResourceLocation ASPEN_HANGING_SIGN_GUI_TEXTURE = BloomingNatureIdentifier.of("textures/gui/hanging_signs/aspen");
    public static final ResourceLocation LARCH_HANGING_SIGN_TEXTURE = BloomingNatureIdentifier.of("entity/signs/hanging/larch");
    public static final ResourceLocation LARCH_HANGING_SIGN_GUI_TEXTURE = BloomingNatureIdentifier.of("textures/gui/hanging_signs/larch");
    public static final ResourceLocation SWAMP_OAK_HANGING_SIGN_TEXTURE = BloomingNatureIdentifier.of("entity/signs/hanging/swamp_oak");
    public static final ResourceLocation SWAMP_OAK_HANGING_SIGN_GUI_TEXTURE = BloomingNatureIdentifier.of("textures/gui/hanging_signs/swamp_oak");
    public static final ResourceLocation BAOBAB_HANGING_SIGN_TEXTURE = BloomingNatureIdentifier.of("entity/signs/hanging/baobab");
    public static final ResourceLocation BAOBAB_HANGING_SIGN_GUI_TEXTURE = BloomingNatureIdentifier.of("textures/gui/hanging_signs/baobab");
    public static final ResourceLocation FAN_PALM_HANGING_SIGN_TEXTURE = BloomingNatureIdentifier.of("entity/signs/hanging/fan_palm");
    public static final ResourceLocation FAN_PALM_HANGING_SIGN_GUI_TEXTURE = BloomingNatureIdentifier.of("textures/gui/hanging_signs/fan_palm");
    public static final ResourceLocation FIR_HANGING_SIGN_TEXTURE = BloomingNatureIdentifier.of("entity/signs/hanging/fir");
    public static final ResourceLocation FIR_HANGING_SIGN_GUI_TEXTURE = BloomingNatureIdentifier.of("textures/gui/hanging_signs/fir");
    public static final ResourceLocation CHESTNUT_HANGING_SIGN_TEXTURE = BloomingNatureIdentifier.of("entity/signs/hanging/chestnut");
    public static final ResourceLocation CHESTNUT_HANGING_SIGN_GUI_TEXTURE = BloomingNatureIdentifier.of("textures/gui/hanging_signs/chestnut");
    public static final ResourceLocation EBONY_HANGING_SIGN_TEXTURE = BloomingNatureIdentifier.of("entity/signs/hanging/ebony");
    public static final ResourceLocation EBONY_HANGING_SIGN_GUI_TEXTURE = BloomingNatureIdentifier.of("textures/gui/hanging_signs/ebony");
    public static final ResourceLocation SWAMP_CYPRESS_HANGING_SIGN_TEXTURE = BloomingNatureIdentifier.of("entity/signs/hanging/swamp_cypress");
    public static final ResourceLocation SWAMP_CYPRESS_HANGING_SIGN_GUI_TEXTURE = BloomingNatureIdentifier.of("textures/gui/hanging_signs/swamp_cypress");


    public static final RegistrySupplier<Block> ASPEN_HANGING_SIGN = ObjectRegistry.registerWithoutItem("aspen_hanging_sign", () -> TerraformSignHelper.getHangingSign(ASPEN_HANGING_SIGN_TEXTURE, ASPEN_HANGING_SIGN_GUI_TEXTURE));
    public static final RegistrySupplier<Block> ASPEN_WALL_HANGING_SIGN = ObjectRegistry.registerWithoutItem("aspen_wall_hanging_sign", () -> TerraformSignHelper.getWallHangingSign(ASPEN_HANGING_SIGN_TEXTURE, ASPEN_HANGING_SIGN_GUI_TEXTURE));
    public static final RegistrySupplier<Item> ASPEN_HANGING_SIGN_ITEM = ObjectRegistry.registerItem("aspen_hanging_sign", () -> new HangingSignItem(ASPEN_HANGING_SIGN.get(), ASPEN_WALL_HANGING_SIGN.get(), ObjectRegistry.getSettings().stacksTo(16)));
    public static final RegistrySupplier<Block> LARCH_HANGING_SIGN = ObjectRegistry.registerWithoutItem("larch_hanging_sign", () -> TerraformSignHelper.getHangingSign(LARCH_HANGING_SIGN_TEXTURE, LARCH_HANGING_SIGN_GUI_TEXTURE));
    public static final RegistrySupplier<Block> LARCH_WALL_HANGING_SIGN = ObjectRegistry.registerWithoutItem("larch_wall_hanging_sign", () -> TerraformSignHelper.getWallHangingSign(LARCH_HANGING_SIGN_TEXTURE, LARCH_HANGING_SIGN_GUI_TEXTURE));
    public static final RegistrySupplier<Item> LARCH_HANGING_SIGN_ITEM = ObjectRegistry.registerItem("larch_hanging_sign", () -> new HangingSignItem(LARCH_HANGING_SIGN.get(), LARCH_WALL_HANGING_SIGN.get(), ObjectRegistry.getSettings().stacksTo(16)));
    public static final RegistrySupplier<Block> SWAMP_OAK_HANGING_SIGN = ObjectRegistry.registerWithoutItem("swamp_oak_hanging_sign", () -> TerraformSignHelper.getHangingSign(SWAMP_OAK_HANGING_SIGN_TEXTURE, SWAMP_OAK_HANGING_SIGN_GUI_TEXTURE));
    public static final RegistrySupplier<Block> SWAMP_OAK_WALL_HANGING_SIGN = ObjectRegistry.registerWithoutItem("swamp_oak_wall_hanging_sign", () -> TerraformSignHelper.getWallHangingSign(SWAMP_OAK_HANGING_SIGN_TEXTURE, SWAMP_OAK_HANGING_SIGN_GUI_TEXTURE));
    public static final RegistrySupplier<Item> SWAMP_OAK_HANGING_SIGN_ITEM = ObjectRegistry.registerItem("swamp_oak_hanging_sign", () -> new HangingSignItem(SWAMP_OAK_HANGING_SIGN.get(), SWAMP_OAK_WALL_HANGING_SIGN.get(), ObjectRegistry.getSettings().stacksTo(16)));
    public static final RegistrySupplier<Block> BAOBAB_HANGING_SIGN = ObjectRegistry.registerWithoutItem("baobab_hanging_sign", () -> TerraformSignHelper.getHangingSign(BAOBAB_HANGING_SIGN_TEXTURE, BAOBAB_HANGING_SIGN_GUI_TEXTURE));
    public static final RegistrySupplier<Block> BAOBAB_WALL_HANGING_SIGN = ObjectRegistry.registerWithoutItem("baobab_wall_hanging_sign", () -> TerraformSignHelper.getWallHangingSign(BAOBAB_HANGING_SIGN_TEXTURE, BAOBAB_HANGING_SIGN_GUI_TEXTURE));
    public static final RegistrySupplier<Item> BAOBAB_HANGING_SIGN_ITEM = ObjectRegistry.registerItem("baobab_hanging_sign", () -> new HangingSignItem(BAOBAB_HANGING_SIGN.get(), BAOBAB_WALL_HANGING_SIGN.get(), ObjectRegistry.getSettings().stacksTo(16)));
    public static final RegistrySupplier<Block> FAN_PALM_HANGING_SIGN = ObjectRegistry.registerWithoutItem("fan_palm_hanging_sign", () -> TerraformSignHelper.getHangingSign(FAN_PALM_HANGING_SIGN_TEXTURE, FAN_PALM_HANGING_SIGN_GUI_TEXTURE));
    public static final RegistrySupplier<Block> FAN_PALM_WALL_HANGING_SIGN = ObjectRegistry.registerWithoutItem("fan_palm_wall_hanging_sign", () -> TerraformSignHelper.getWallHangingSign(FAN_PALM_HANGING_SIGN_TEXTURE, FAN_PALM_HANGING_SIGN_GUI_TEXTURE));
    public static final RegistrySupplier<Item> FAN_PALM_HANGING_SIGN_ITEM = ObjectRegistry.registerItem("fan_palm_hanging_sign", () -> new HangingSignItem(FAN_PALM_HANGING_SIGN.get(), FAN_PALM_WALL_HANGING_SIGN.get(), ObjectRegistry.getSettings().stacksTo(16)));
    public static final RegistrySupplier<Block> FIR_HANGING_SIGN = ObjectRegistry.registerWithoutItem("fir_hanging_sign", () -> TerraformSignHelper.getHangingSign(FIR_HANGING_SIGN_TEXTURE, FIR_HANGING_SIGN_GUI_TEXTURE));
    public static final RegistrySupplier<Block> FIR_WALL_HANGING_SIGN = ObjectRegistry.registerWithoutItem("fir_wall_hanging_sign", () -> TerraformSignHelper.getWallHangingSign(FIR_HANGING_SIGN_TEXTURE, FIR_HANGING_SIGN_GUI_TEXTURE));
    public static final RegistrySupplier<Item> FIR_HANGING_SIGN_ITEM = ObjectRegistry.registerItem("fir_hanging_sign", () -> new HangingSignItem(FIR_HANGING_SIGN.get(), FIR_WALL_HANGING_SIGN.get(), ObjectRegistry.getSettings().stacksTo(16)));
    public static final RegistrySupplier<Block> CHESTNUT_HANGING_SIGN = ObjectRegistry.registerWithoutItem("chestnut_hanging_sign", () -> TerraformSignHelper.getHangingSign(CHESTNUT_HANGING_SIGN_TEXTURE, CHESTNUT_HANGING_SIGN_GUI_TEXTURE));
    public static final RegistrySupplier<Block> CHESTNUT_WALL_HANGING_SIGN = ObjectRegistry.registerWithoutItem("chestnut_wall_hanging_sign", () -> TerraformSignHelper.getWallHangingSign(CHESTNUT_HANGING_SIGN_TEXTURE, CHESTNUT_HANGING_SIGN_GUI_TEXTURE));
    public static final RegistrySupplier<Item> CHESTNUT_HANGING_SIGN_ITEM = ObjectRegistry.registerItem("chestnut_hanging_sign", () -> new HangingSignItem(CHESTNUT_HANGING_SIGN.get(), CHESTNUT_WALL_HANGING_SIGN.get(), ObjectRegistry.getSettings().stacksTo(16)));
    public static final RegistrySupplier<Block> EBONY_HANGING_SIGN = ObjectRegistry.registerWithoutItem("ebony_hanging_sign", () -> TerraformSignHelper.getHangingSign(EBONY_HANGING_SIGN_TEXTURE, EBONY_HANGING_SIGN_GUI_TEXTURE));
    public static final RegistrySupplier<Block> EBONY_WALL_HANGING_SIGN = ObjectRegistry.registerWithoutItem("ebony_wall_hanging_sign", () -> TerraformSignHelper.getWallHangingSign(EBONY_HANGING_SIGN_TEXTURE, EBONY_HANGING_SIGN_GUI_TEXTURE));
    public static final RegistrySupplier<Item> EBONY_HANGING_SIGN_ITEM = ObjectRegistry.registerItem("ebony_hanging_sign", () -> new HangingSignItem(EBONY_HANGING_SIGN.get(), EBONY_WALL_HANGING_SIGN.get(), ObjectRegistry.getSettings().stacksTo(16)));
    public static final RegistrySupplier<Block> SWAMP_CYPRESS_HANGING_SIGN = ObjectRegistry.registerWithoutItem("swamp_cypress_hanging_sign", () -> TerraformSignHelper.getHangingSign(SWAMP_CYPRESS_HANGING_SIGN_TEXTURE, SWAMP_CYPRESS_HANGING_SIGN_GUI_TEXTURE));
    public static final RegistrySupplier<Block> SWAMP_CYPRESS_WALL_HANGING_SIGN = ObjectRegistry.registerWithoutItem("swamp_cypress_wall_hanging_sign", () -> TerraformSignHelper.getWallHangingSign(SWAMP_CYPRESS_HANGING_SIGN_TEXTURE, SWAMP_CYPRESS_HANGING_SIGN_GUI_TEXTURE));
    public static final RegistrySupplier<Item> SWAMP_CYPRESS_HANGING_SIGN_ITEM = ObjectRegistry.registerItem("swamp_cypress_hanging_sign", () -> new HangingSignItem(SWAMP_CYPRESS_HANGING_SIGN.get(), SWAMP_CYPRESS_WALL_HANGING_SIGN.get(), ObjectRegistry.getSettings().stacksTo(16)));



    public static RegistrySupplier<Item> ASPEN_BOAT = TerraformBoatItemHelper.registerBoatItem(ObjectRegistry.ITEMS, "aspen_boat", ASPEN_BOAT_TYPE, false);
    public static RegistrySupplier<Item> ASPEN_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ObjectRegistry.ITEMS, "aspen_chest_boat", ASPEN_BOAT_TYPE, true);
    public static RegistrySupplier<Item> LARCH_BOAT = TerraformBoatItemHelper.registerBoatItem(ObjectRegistry.ITEMS, "larch_boat", LARCH_BOAT_TYPE, false);
    public static RegistrySupplier<Item> LARCH_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ObjectRegistry.ITEMS, "larch_chest_boat", LARCH_BOAT_TYPE, true);
    public static RegistrySupplier<Item> SWAMP_OAK_BOAT = TerraformBoatItemHelper.registerBoatItem(ObjectRegistry.ITEMS, "swamp_oak_boat", SWAMP_OAK_BOAT_TYPE, false);
    public static RegistrySupplier<Item> SWAMP_OAK_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ObjectRegistry.ITEMS, "swamp_oak_chest_boat", SWAMP_OAK_BOAT_TYPE, true);
    public static RegistrySupplier<Item> BAOBAB_BOAT = TerraformBoatItemHelper.registerBoatItem(ObjectRegistry.ITEMS, "baobab_boat", BAOBAB_BOAT_TYPE, false);
    public static RegistrySupplier<Item> BAOBAB_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ObjectRegistry.ITEMS, "baobab_chest_boat", BAOBAB_BOAT_TYPE, true);
    public static RegistrySupplier<Item> FAN_PALM_BOAT = TerraformBoatItemHelper.registerBoatItem(ObjectRegistry.ITEMS, "fan_palm_boat", FAN_PALM_BOAT_TYPE, false);
    public static RegistrySupplier<Item> FAN_PALM_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ObjectRegistry.ITEMS, "fan_palm_chest_boat", FAN_PALM_BOAT_TYPE, true);
    public static RegistrySupplier<Item> FIR_BOAT = TerraformBoatItemHelper.registerBoatItem(ObjectRegistry.ITEMS, "fir_boat", FIR_BOAT_TYPE, false);
    public static RegistrySupplier<Item> FIR_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ObjectRegistry.ITEMS, "fir_chest_boat", FIR_BOAT_TYPE, true);
    public static RegistrySupplier<Item> SWAMP_CYPRESS_BOAT = TerraformBoatItemHelper.registerBoatItem(ObjectRegistry.ITEMS, "swamp_cypress_boat", SWAMP_CYPRESS_BOAT_TYPE, false);
    public static RegistrySupplier<Item> SWAMP_CYPRESS_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ObjectRegistry.ITEMS, "swamp_cypress_chest_boat", SWAMP_CYPRESS_BOAT_TYPE, true);
    public static RegistrySupplier<Item> CHESTNUT_BOAT = TerraformBoatItemHelper.registerBoatItem(ObjectRegistry.ITEMS, "chestnut_boat", CHESTNUT_BOAT_TYPE, false);
    public static RegistrySupplier<Item> CHESTNUT_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ObjectRegistry.ITEMS, "chestnut_chest_boat", CHESTNUT_BOAT_TYPE, true);
    public static RegistrySupplier<Item> EBONY_BOAT = TerraformBoatItemHelper.registerBoatItem(ObjectRegistry.ITEMS, "ebony_boat", EBONY_BOAT_TYPE, false);
    public static RegistrySupplier<Item> EBONY_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ObjectRegistry.ITEMS, "ebony_chest_boat", EBONY_BOAT_TYPE, true);


    public static void init() {
        DoApiCommonEP.registerBoatType(LARCH_BOAT_TYPE, new TerraformBoatType.Builder().item(LARCH_BOAT).chestItem(LARCH_CHEST_BOAT).build());
        DoApiCommonEP.registerBoatType(ASPEN_BOAT_TYPE, new TerraformBoatType.Builder().item(ASPEN_BOAT).chestItem(ASPEN_CHEST_BOAT).build());
        DoApiCommonEP.registerBoatType(SWAMP_CYPRESS_BOAT_TYPE, new TerraformBoatType.Builder().item(SWAMP_CYPRESS_BOAT).chestItem(SWAMP_CYPRESS_CHEST_BOAT).build());
        DoApiCommonEP.registerBoatType(SWAMP_OAK_BOAT_TYPE, new TerraformBoatType.Builder().item(SWAMP_OAK_BOAT).chestItem(SWAMP_OAK_CHEST_BOAT).build());
        DoApiCommonEP.registerBoatType(FIR_BOAT_TYPE, new TerraformBoatType.Builder().item(FIR_BOAT).chestItem(FIR_CHEST_BOAT).build());
        DoApiCommonEP.registerBoatType(BAOBAB_BOAT_TYPE, new TerraformBoatType.Builder().item(BAOBAB_BOAT).chestItem(BAOBAB_CHEST_BOAT).build());
        DoApiCommonEP.registerBoatType(FAN_PALM_BOAT_TYPE, new TerraformBoatType.Builder().item(FAN_PALM_BOAT).chestItem(FAN_PALM_CHEST_BOAT).build());
        DoApiCommonEP.registerBoatType(CHESTNUT_BOAT_TYPE, new TerraformBoatType.Builder().item(CHESTNUT_BOAT).chestItem(CHESTNUT_CHEST_BOAT).build());
        DoApiCommonEP.registerBoatType(EBONY_BOAT_TYPE, new TerraformBoatType.Builder().item(EBONY_BOAT).chestItem(EBONY_CHEST_BOAT).build());

    }
}
