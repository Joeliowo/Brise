package com.rhodojoel.brise.gem.possessed;

import mod.akrivus.kagic.entity.EntityGem;
import mod.akrivus.kagic.entity.gem.*;
import mod.akrivus.kagic.init.ModItems;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityPossessedQuartz extends EntityGem {
    public String type;

    public EntityPossessedQuartz(World worldIn) {
        super(worldIn);
        this.setServitude(SERVE_WHITE_DIAMOND);
        this.setSize(0.9F, 2.3F);
        this.setUniformColor(0xFFFFFF);
        this.setInsigniaColor(0xFFFFFF);
        this.setCutPlacement(GemCuts.FACETED, GemPlacements.BACK_OF_HEAD);
        this.setCutPlacement(GemCuts.FACETED, GemPlacements.FOREHEAD);
        this.setCutPlacement(GemCuts.FACETED, GemPlacements.LEFT_EYE);
        this.setCutPlacement(GemCuts.FACETED, GemPlacements.RIGHT_EYE);
        this.setCutPlacement(GemCuts.FACETED, GemPlacements.LEFT_CHEEK);
        this.setCutPlacement(GemCuts.FACETED, GemPlacements.RIGHT_CHEEK);
        this.setCutPlacement(GemCuts.FACETED, GemPlacements.LEFT_SHOULDER);
        this.setCutPlacement(GemCuts.FACETED, GemPlacements.RIGHT_SHOULDER);
        this.setCutPlacement(GemCuts.FACETED, GemPlacements.LEFT_HAND);
        this.setCutPlacement(GemCuts.FACETED, GemPlacements.RIGHT_HAND);
        this.setCutPlacement(GemCuts.FACETED, GemPlacements.BACK);
        this.setCutPlacement(GemCuts.FACETED, GemPlacements.CHEST);
        this.setCutPlacement(GemCuts.FACETED, GemPlacements.BELLY);
        this.setCutPlacement(GemCuts.FACETED, GemPlacements.LEFT_THIGH);
        this.setCutPlacement(GemCuts.FACETED, GemPlacements.RIGHT_THIGH);
        this.setCutPlacement(GemCuts.FACETED, GemPlacements.LEFT_KNEE);
        this.setCutPlacement(GemCuts.FACETED, GemPlacements.RIGHT_KNEE);

        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.BACK_OF_HEAD);
        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.FOREHEAD);
        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.LEFT_EYE);
        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.RIGHT_EYE);
        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.LEFT_CHEEK);
        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.RIGHT_CHEEK);
        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.LEFT_SHOULDER);
        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.RIGHT_SHOULDER);
        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.LEFT_HAND);
        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.RIGHT_HAND);
        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.BACK);
        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.CHEST);
        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.BELLY);
        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.LEFT_THIGH);
        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.RIGHT_THIGH);
        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.LEFT_KNEE);
        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.RIGHT_KNEE);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(15D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 30.0F));
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata){
        return super.onInitialSpawn(difficulty, livingdata);
    }

    public void writeEntityToNBT(NBTTagCompound compound) {
        compound.setString("type", this.type);
        super.writeEntityToNBT(compound);
    }
    public void readEntityFromNBT(NBTTagCompound compound) {
        this.type = compound.getString("type");
        super.readEntityFromNBT(compound);
    }

    public void setRuby(EntityQuartzSoldier ruby){
        this.setGemPlacement(ruby.getGemPlacement().id);
        if(ruby instanceof EntityCarnelian){
            this.type = "carnelian";
        }
        else if(ruby instanceof EntityAmethyst) {
            this.type = "amethyst";
        }
        else if(ruby instanceof EntityCitrine) {
            this.type = "citrine";
        }
        else if(ruby instanceof EntityJasper) {
            this.type = "jasper";
        }
    }

    @Override
    public void onDeath(DamageSource source){
        super.onDeath(source);
        if(this.type == "carnelian"){
            this.dropItem(ModItems.CARNELIAN_GEM, 1);
        }
        else if(this.type == "amethyst") {
            this.dropItem(ModItems.AMETHYST_GEM, 1);
        }
        else if(this.type == "citrine") {
            this.dropItem(ModItems.CITRINE_GEM, 1);
        }
        else if(this.type == "jasper") {
            this.dropItem(ModItems.JASPER_GEM, 1);
        }
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
