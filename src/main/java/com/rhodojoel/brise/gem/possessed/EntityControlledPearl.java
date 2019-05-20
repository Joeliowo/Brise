package com.rhodojoel.brise.gem.possessed;

import com.rhodojoel.brise.entity.EntityControlledGem;
import mod.akrivus.kagic.entity.EntityGem;
import mod.akrivus.kagic.entity.gem.EntityPearl;
import mod.akrivus.kagic.entity.gem.GemCuts;
import mod.akrivus.kagic.entity.gem.GemPlacements;
import mod.akrivus.kagic.init.ModItems;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class EntityControlledPearl extends EntityGem {
    public static final ArrayList<ResourceLocation> PEARL_HAIR_STYLES = new ArrayList<ResourceLocation>();

    public EntityControlledPearl(World worldIn) {
        super(worldIn);
        this.setServitude(SERVE_WHITE_DIAMOND);
        this.setSize(0.6F, 1.9F);
        this.setUniformColor(0xFFFFFF);
        this.setInsigniaColor(0xFFFFFF);
        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.BACK_OF_HEAD);
        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.FOREHEAD);
        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.BACK);
        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.CHEST);
        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.BELLY);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 30.0F));
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata){
        return super.onInitialSpawn(difficulty, livingdata);
    }

    public void setPearl(EntityPearl pearl){
        this.setGemPlacement(pearl.getGemPlacement().id);
    }

    @Override
    public void onDeath(DamageSource source){
        super.onDeath(source);
        this.dropItem(ModItems.PEARL_GEM, 1);
    }

    @Override
    public int getHairStyle(){
        return 4;
    }

    @Override
    public int generateGemColor(){
        return 0xFFFFFF;
    }

    @Override
    public int generateSkinColor(){
        return 0xFFFFFF;
    }

    @Override
    public int generateHairColor(){
        return 0xFFFFFF;
    }

    @Override
    public int getInsigniaColor(){
        return 0xFFFFFF;
    }
}
