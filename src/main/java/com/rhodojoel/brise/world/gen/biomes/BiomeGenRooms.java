package com.rhodojoel.brise.world.gen.biomes;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;

import java.util.List;

public class BiomeGenRooms extends BriseBiomeGenBase {
    public BiomeGenRooms(BiomeProperties setupBiome) {
        super(setupBiome);
        this.fillerBlock = Blocks.AIR.getDefaultState();
        this.topBlock = Blocks.AIR.getDefaultState();
        this.mainFillerBlock = this.fillerBlock;
        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
    }

    public List<SpawnListEntry> getSpawnableList(EnumCreatureType creatureType) {
        switch(creatureType) {
            case MONSTER:
                return this.spawnableCreatureList;
            case CREATURE:
                return this.spawnableCreatureList;
            case WATER_CREATURE:
                return this.spawnableCreatureList;
            case AMBIENT:
                return this.spawnableCreatureList;
            default:
                return this.spawnableCreatureList;
        }
    }
}
