package com.rhodojoel.brise.items;

import com.rhodojoel.brise.entity.EntityDestabalizer;
import mod.akrivus.kagic.init.ModCreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemDestabalizerCannon extends ItemBow {

    public ItemDestabalizerCannon(String color) {
        super();
        this.setUnlocalizedName(color + "_destabalizer_cannon");
        this.setCreativeTab(ModCreativeTabs.CREATIVE_TAB_OTHER);
        this.setMaxStackSize(1);
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
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if (!playerIn.capabilities.isCreativeMode)
        {
            itemstack.shrink(1);
        }
        if (!worldIn.isRemote)
        {
            EntityDestabalizer des = new EntityDestabalizer(worldIn, playerIn, -playerIn.posX, -playerIn.posY, -playerIn.posZ, 0);
            worldIn.spawnEntity(des);
        }

        playerIn.addStat(StatList.getObjectUseStats(this));
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }
}
