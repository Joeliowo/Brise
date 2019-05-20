package com.rhodojoel.brise.world.structure;

import mod.heimrarnadalr.kagic.world.structure.RuinStructure;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;

import java.util.Random;

public class TempleRoomTest extends RuinStructure {

    public TempleRoomTest(String type) {
        super(type, 5, Blocks.DIRT.getDefaultState(), true, true);
        this.structures.add("/assets/brise/structures/temple.schematic");
        this.allowedBiomeTypes.add(BiomeDictionary.Type.BEACH);
        this.canContainAnyType = true;
    }


    @Override
    public boolean checkCorners(World world, BlockPos pos) {
        return true;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {
        if (rand.nextInt(500) != 0) {
            return false;
        }
        return super.generate(world, rand, pos.down(5));
    }
}
