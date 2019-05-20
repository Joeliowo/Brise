package com.rhodojoel.brise.items;

import mod.akrivus.kagic.init.ModCreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.Random;

public class ItemHerkGem extends Item {
    String message;
    TextFormatting color;

    public ItemHerkGem(String name, TextFormatting color, String message){
        super();
        this.setUnlocalizedName(name);
        this.setCreativeTab(ModCreativeTabs.CREATIVE_TAB_OTHER);
        this.setMaxStackSize(1);
        this.color = color;
        this.message = message;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        Random rand = new Random();
        if (!worldIn.isRemote) {
            ITextComponent message;
            if(rand.nextBoolean()){
                message = new TextComponentString("She's not coming back...");
            }
            else{
                message = new TextComponentString(this.message);
            }
            message.getStyle().setColor(this.color);
            playerIn.sendMessage(message);
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.PASS;
    }
}
