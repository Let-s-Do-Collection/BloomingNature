package net.satisfy.bloomingnature.core.util;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.satisfy.bloomingnature.BloomingNature;

public class BloomingNatureWoodType {
    public static final WoodType ASPEN = register("aspen");
    public static final WoodType BAOBAB = register("baobab");
    public static final WoodType LARCH = register("larch");
    public static final WoodType EBONY = register("ebony");
    public static final WoodType CHESTNUT = register("chestnut");
    public static final WoodType SWAMP_OAK = register("swamp_oak");
    public static final WoodType SWAMP_CYPRESS = register("swamp_cypress");
    public static final WoodType FAN_PALM = register("fan_palm");
    public static final WoodType FIR = register("fir");
    public static final WoodType CACTUS = register("cactus");
    public static final WoodType CYPRESS = register("cypress");

    private static WoodType register(String name) {
        return WoodType.register(new WoodType(BloomingNature.MOD_ID + ":" + name, BlockSetType.OAK));
    }
}
