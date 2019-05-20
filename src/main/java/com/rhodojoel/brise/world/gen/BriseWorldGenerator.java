package com.rhodojoel.brise.world.gen;

import com.rhodojoel.brise.world.structure.TempleRoomTest;
import mod.akrivus.kagic.init.ModConfigs;
import mod.heimrarnadalr.kagic.world.structure.RuinStructure;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.ArrayList;
import java.util.Random;

public class BriseWorldGenerator implements IWorldGenerator {
    private ArrayList<RuinStructure> ruins = new ArrayList<RuinStructure>();
    private ArrayList<Integer> ruinDimensions = new ArrayList<Integer>();

    public BriseWorldGenerator() {
        this.ruins.add(new TempleRoomTest("temple"));
        this.ruins.add(new TempleRoomTest("great_north_pad"));
        this.ruinDimensions.add(0);
        for (String dimS : ModConfigs.ruinDimensions.split(",")) {
            Integer dim = Integer.getInteger(dimS);
            if (dim != null) {
                this.ruinDimensions.add(dim.intValue());
            }
        }
    }

    @Override
    public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (this.ruinDimensions.contains(world.provider.getDimension())) {
            for (RuinStructure ruin : ruins) {
                runGenerator(ruin, world, rand, chunkX, chunkZ, 1);
            }
        }
    }

    private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn) {
        for (int i = 0; i < chancesToSpawn; ++i) {
            int x = chunk_X * 16 + rand.nextInt(16);// + 8;
            int z = chunk_Z * 16 + rand.nextInt(16);// + 8;
            int y = world.getHeight(x, z);
            generator.generate(world, rand, world.getTopSolidOrLiquidBlock(new BlockPos(x, y, z)));
        }
    }
}
