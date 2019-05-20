package com.rhodojoel.brise.blocks;

import mod.akrivus.kagic.init.ModCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class BlockRoomBlock extends Block {

    public BlockRoomBlock() {
        super(Material.CLAY);
        this.setResistance(4);
        this.setHardness(2);
        this.setUnlocalizedName("room_block");
        this.setCreativeTab(ModCreativeTabs.CREATIVE_TAB_OTHER);
    }
}
