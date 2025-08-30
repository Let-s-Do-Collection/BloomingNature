package net.satisfy.bloomingnature.core.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.satisfy.bloomingnature.core.registry.EntityTypeRegistry;
import net.satisfy.bloomingnature.core.registry.ObjectRegistry;
import net.satisfy.bloomingnature.core.util.BloomingNatureIdentifier;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ModBoatEntity extends Boat {

    private static final EntityDataAccessor<Integer> WOOD_TYPE = SynchedEntityData.defineId(ModBoatEntity.class, EntityDataSerializers.INT);

    public ModBoatEntity(EntityType<? extends Boat> type, Level level) {
        super(type, level);
        this.blocksBuilding = true;
    }

    public ModBoatEntity(Level level, double x, double y, double z) {
        this(EntityTypeRegistry.MOD_BOAT.get(), level);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(WOOD_TYPE, 0);
    }


    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        if (pCompound.contains("Type", 8)) {
            this.setWoodType(Type.byName(pCompound.getString("Type")));
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putString("Type", this.getWoodType().getName());
    }

    public Type getWoodType() {
        return Type.byId(this.entityData.get(WOOD_TYPE));
    }

    public void setWoodType(Type type) {
        this.entityData.set(WOOD_TYPE, type.ordinal());
    }

    @Override
    public @NotNull Item getDropItem() {
        return this.getWoodType().getItem().get();
    }

    @Override
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity serverEntity) {
        return new ClientboundAddEntityPacket(this, serverEntity);
    }

    public enum Type {
        ASPEN("aspen", ObjectRegistry.ASPEN_BOAT, ObjectRegistry.ASPEN_CHEST_BOAT),
        BAOBAB("baobab", ObjectRegistry.BAOBAB_BOAT, ObjectRegistry.BAOBAB_CHEST_BOAT),
        LARCH("larch", ObjectRegistry.LARCH_BOAT, ObjectRegistry.LARCH_CHEST_BOAT),
        EBONY("ebony", ObjectRegistry.EBONY_BOAT, ObjectRegistry.EBONY_CHEST_BOAT),
        CHESTNUT("chestnut", ObjectRegistry.CHESTNUT_BOAT, ObjectRegistry.CHESTNUT_CHEST_BOAT),
        SWAMP_OAK("swamp_oak", ObjectRegistry.SWAMP_OAK_BOAT, ObjectRegistry.SWAMP_OAK_CHEST_BOAT),
        SWAMP_CYPRESS("swamp_cypress", ObjectRegistry.SWAMP_CYPRESS_BOAT, ObjectRegistry.SWAMP_CYPRESS_CHEST_BOAT),
        FAN_PALM("fan_palm", ObjectRegistry.FAN_PALM_BOAT, ObjectRegistry.FAN_PALM_CHEST_BOAT),
        FIR("fir", ObjectRegistry.FIR_BOAT, ObjectRegistry.FIR_CHEST_BOAT),
        CACTUS("cactus", ObjectRegistry.CACTUS_BOAT, ObjectRegistry.CACTUS_CHEST_BOAT);

        private final String name;
        private final Supplier<Item> item;
        private final Supplier<Item> chestItem;

        Type(String name, Supplier<Item> boatItem, Supplier<Item> chestBoatItem) {
            this.name = name;
            this.item = boatItem;
            this.chestItem = chestBoatItem;
        }

        public ResourceLocation getTexture(boolean hasChest) {
            if (hasChest) {
                return BloomingNatureIdentifier.of("textures/entity/chest_boat/" + name + ".png");
            }
            return BloomingNatureIdentifier.of("textures/entity/boat/" + name + ".png");
        }

        public String getModelLocation() {
            return "boat/" + name;
        }

        public String getChestModelLocation() {
            return "chest_boat/" + name;
        }

        public String getName() {
            return this.name;
        }

        public Supplier<Item> getItem() {
            return item;
        }

        public Supplier<Item> getChestItem() {
            return chestItem;
        }

        public static Type byId(int id) {
            Type[] values = values();
            if (id < 0 || id >= values.length) {
                id = 0;
            }

            return values[id];
        }

        public static Type byName(String name) {
            Type[] values = values();

            for (Type value : values) {
                if (value.getName().equals(name)) {
                    return value;
                }
            }

            return values[0];
        }
    }
}