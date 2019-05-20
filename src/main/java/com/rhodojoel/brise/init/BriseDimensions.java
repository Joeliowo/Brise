package com.rhodojoel.brise.init;

import com.rhodojoel.brise.dimensions.world.WorldProviderRooms;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class BriseDimensions {
    public static DimensionType DIM_ROOMS;
    public static final int DIM_ROOMS_ID = 01100010;

    public BriseDimensions() {
    }

    public static void init() {
        DIM_ROOMS = DimensionType.register("ROOMS", "_rooms", DIM_ROOMS_ID, WorldProviderRooms.class, false);
        DimensionManager.registerDimension(DIM_ROOMS_ID, DIM_ROOMS);
    }
}
