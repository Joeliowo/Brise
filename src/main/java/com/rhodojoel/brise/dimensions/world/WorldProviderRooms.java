package com.rhodojoel.brise.dimensions.world;

import com.rhodojoel.brise.dimensions.chunk.ChunkProviderRooms;
import com.rhodojoel.brise.init.BriseBiomes;
import com.rhodojoel.brise.init.BriseDimensions;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class WorldProviderRooms extends WorldProvider {

    public WorldProviderRooms() {
        this.biomeProvider = new BiomeProviderSingle(BriseBiomes.BIOME_ROOMS);
    }

    public String getSaveFolder() {
        return "brise_rooms";
    }

    public boolean isBlockHighHumidity(BlockPos pos) {
        return false;
    }

    public void calculateInitialWeather() {
    }

    public IChunkGenerator createChunkGenerator() {
        return new ChunkProviderRooms(this.world);
    }

    public boolean canDoLightning(Chunk chunk) {
        return false;
    }

    public DimensionType getDimensionType() {
        return BriseDimensions.DIM_ROOMS;
    }

    public boolean isSurfaceWorld() {
        return false;
    }

    public boolean canCoordinateBeSpawn(int x, int z) {
        return false;
    }

    public void updateWeather() {
    }

    public float calculateCelestialAngle(long worldTime, float partialTicks) {
        return 0.0F;
    }

    @SideOnly(Side.CLIENT)
    public float[] calcSunriseSunsetColors(float celestialAngle, float partialTicks) {
        return new float[]{0.0F, 0.0F, 0.0F, 1.0F};
    }

    public boolean canRespawnHere() {
        return false;
    }

    public int getAverageGroundLevel() {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(int par1, int par2) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public boolean isSkyColored() {
        return false;
    }

    protected void generateLightBrightnessTable() {
        float f = 0.1F;

        for(int i = 0; i <= 15; ++i) {
            float f1 = 1.0F - (float)i / 15.0F;
            this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
        }

    }

    public boolean canDoRainSnowIce(Chunk chunk) {
        return false;
    }
}
