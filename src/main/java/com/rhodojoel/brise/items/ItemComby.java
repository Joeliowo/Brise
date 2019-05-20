package com.rhodojoel.brise.items;

import mod.akrivus.kagic.entity.EntityGem;
import mod.akrivus.kagic.entity.gem.EntityPearl;
import mod.akrivus.kagic.entity.gem.EntityZircon;
import mod.akrivus.kagic.init.ModCreativeTabs;
import mod.akrivus.kagic.init.ModEntities;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemComby extends Item {
    public EnumDyeColor dye;

    public ItemComby(String name, EnumDyeColor dyee) {
        super();
        this.setUnlocalizedName("comby" + name);
        this.setCreativeTab(ModCreativeTabs.CREATIVE_TAB_OTHER);
        this.setMaxStackSize(1);
        this.dye = dyee;
    }
    @Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        return EnumActionResult.PASS;
    }

    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand)
    {
        if (target instanceof EntitySheep){
            EntitySheep entitysheep = (EntitySheep)target;
            EnumDyeColor enumdyecolor = this.dye;

            if (!entitysheep.getSheared() && entitysheep.getFleeceColor() != enumdyecolor)
            {
                entitysheep.setFleeceColor(enumdyecolor);
            }

            return true;
        }
        else if(target instanceof EntityGem){
            EntityGem gem = (EntityGem)target;
            try {
                if(gem.getClass().getField((gem.getName() + "_hair_styles").toUpperCase()).get(null) != null) {
                    if (true) {
                        if(gem instanceof EntityPearl || gem instanceof EntityZircon) {
                            ArrayList<ResourceLocation> stylesp = (ArrayList<ResourceLocation>) gem.getClass().getField((gem.getName() + "_hair_styles").toUpperCase()).get(null);;
                            int hair = gem.getHairStyle() + 1;
                            if (hair >= stylesp.size() || hair < 0) {
                                hair = 0;
                            }
                            gem.setHairStyle(hair);
                        }
                        else{
                            HashMap<Integer, ResourceLocation> styles = (HashMap<Integer, ResourceLocation>) gem.getClass().getField((gem.getName() + "_hair_styles").toUpperCase()).get(null);
                            int hair = gem.getHairStyle() + 1;
                            if (hair >= styles.size() || hair < 0) {
                                hair = 0;
                            }
                        }
                        return true;
                    }
                }
            }
            catch (NoSuchFieldException e) {
                e.printStackTrace();
                return false;
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem entity) {
        entity.isDead = false;
        entity.setEntityInvulnerable(true);
        entity.extinguish();
        return false;
    }
}
