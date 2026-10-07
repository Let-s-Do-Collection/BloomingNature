package net.satisfy.bloomingnature.core.spawn;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.level.saveddata.SavedData;

public class GardenerVisitData extends SavedData {
    public static final String ID = "bloomingnature_gardener";
    private static final String NEXT_VISIT = "NextVisit";

    private long nextVisit = -1L;

    public static SavedData.Factory<GardenerVisitData> factory() {
        return new SavedData.Factory<>(GardenerVisitData::new, GardenerVisitData::load, DataFixTypes.SAVED_DATA_MAP_DATA);
    }

    private static GardenerVisitData load(CompoundTag tag, HolderLookup.Provider registries) {
        GardenerVisitData data = new GardenerVisitData();
        data.nextVisit = tag.contains(NEXT_VISIT) ? tag.getLong(NEXT_VISIT) : -1L;
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider registries) {
        tag.putLong(NEXT_VISIT, nextVisit);
        return tag;
    }

    public long getNextVisit() {
        return nextVisit;
    }

    public void setNextVisit(long gameTime) {
        this.nextVisit = gameTime;
        setDirty();
    }
}
