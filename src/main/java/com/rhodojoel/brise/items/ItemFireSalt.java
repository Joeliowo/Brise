package com.rhodojoel.brise.items;

import mod.akrivus.kagic.init.ModCreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.world.World;

public class ItemFireSalt extends ItemFood {

    public ItemFireSalt(int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood);
        this.setUnlocalizedName("fire_salt");
        this.setCreativeTab(ModCreativeTabs.CREATIVE_TAB_OTHER);
        this.setMaxStackSize(1);
    }

    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        if (!worldIn.isRemote)
        {
            if (true)
            {
                player.setFire(300);
                player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 6000, 1));
                player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 6000, 0));
                player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 6000, 3));
            }
        }
    }
}
