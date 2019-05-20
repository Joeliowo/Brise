package com.rhodojoel.brise.items;

import mod.akrivus.kagic.entity.EntityGem;
import mod.akrivus.kagic.init.ModCreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemDoll extends Item {
    public ItemDoll() {
        super();
        this.setUnlocalizedName("doll");
        this.setCreativeTab(ModCreativeTabs.CREATIVE_TAB_OTHER);
        this.setMaxStackSize(1);
    }
    @Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            ITextComponent message = new TextComponentTranslation(String.format("command.brise.doll"));
            message.getStyle().setColor(TextFormatting.DARK_GREEN);
            playerIn.sendMessage(message);
            if(worldIn.getWorldTime() > 13000L){
                playerIn.setHealth(0);
                return EnumActionResult.SUCCESS;
            }
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.PASS;
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem entity) {
        entity.isDead = false;
        entity.setEntityInvulnerable(true);
        entity.extinguish();
        return false;
    }
}