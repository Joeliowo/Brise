package com.rhodojoel.brise.items;

import mod.akrivus.kagic.entity.EntityGem;
import mod.akrivus.kagic.init.ModConfigs;
import mod.akrivus.kagic.init.ModCreativeTabs;
import mod.akrivus.kagic.items.ItemGem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemGemDust extends Item {
    public String gem;

    public ItemGemDust(String type) {
        super();
        this.setUnlocalizedName("gem_dust_" + type);
        this.setCreativeTab(ModCreativeTabs.CREATIVE_TAB_OTHER);
        this.setMaxStackSize(64);
        this.gem = type;
    }

    public void setData(EntityGem host, ItemStack stack) {
        stack.setTagCompound(host.writeToNBT(new NBTTagCompound()));
        stack.getTagCompound().setString("name", host.getName());
    }

    public void clearData(ItemStack itemStackIn) {
        itemStackIn.setTagCompound(new NBTTagCompound());
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
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