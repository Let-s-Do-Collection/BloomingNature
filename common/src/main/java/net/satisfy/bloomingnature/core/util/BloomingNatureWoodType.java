package net.satisfy.bloomingnature.core.util;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.satisfy.bloomingnature.BloomingNature;
import net.minecraft.resources.ResourceLocation;

public class BloomingNatureWoodType {
    public static final ResourceLocation ASPEN_ID = BloomingNature.identifier("aspen");
    public static final ResourceLocation BAOBAB_ID = BloomingNature.identifier("baobab");
    public static final ResourceLocation LARCH_ID = BloomingNature.identifier("larch");
    public static final ResourceLocation EBONY_ID = BloomingNature.identifier("ebony");
    public static final ResourceLocation CHESTNUT_ID = BloomingNature.identifier("chestnut");
    public static final ResourceLocation SWAMP_OAK_ID = BloomingNature.identifier("swamp_oak");
    public static final ResourceLocation SWAMP_CYPRESS_ID = BloomingNature.identifier("swamp_cypress");
    public static final ResourceLocation FAN_PALM_ID = BloomingNature.identifier("fan_palm");
    public static final ResourceLocation FIR_ID = BloomingNature.identifier("fir");
    public static final ResourceLocation CACTUS_ID = BloomingNature.identifier("cactus");
    public static final ResourceLocation CYPRESS_ID = BloomingNature.identifier("cypress");

    public static final WoodType ASPEN = register(ASPEN_ID);
    public static final WoodType BAOBAB = register(BAOBAB_ID);
    public static final WoodType LARCH = register(LARCH_ID);
    public static final WoodType EBONY = register(EBONY_ID);
    public static final WoodType CHESTNUT = register(CHESTNUT_ID);
    public static final WoodType SWAMP_OAK = register(SWAMP_OAK_ID);
    public static final WoodType SWAMP_CYPRESS = register(SWAMP_CYPRESS_ID);
    public static final WoodType FAN_PALM = register(FAN_PALM_ID);
    public static final WoodType FIR = register(FIR_ID);
    public static final WoodType CACTUS = register(CACTUS_ID);
    public static final WoodType CYPRESS = register(CYPRESS_ID);

    private static WoodType register(ResourceLocation id) {
        return WoodType.register(new WoodType(id.toString(), BlockSetType.OAK));
    }
}
