package com.rhodojoel.brise.gem.possessed;

import mod.akrivus.kagic.entity.EntityGem;
import mod.akrivus.kagic.entity.gem.EntityLapisLazuli;
import mod.akrivus.kagic.entity.gem.EntityPearl;
import mod.akrivus.kagic.entity.gem.GemCuts;
import mod.akrivus.kagic.entity.gem.GemPlacements;
import mod.akrivus.kagic.init.ModItems;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class EntityControlledLapis extends EntityGem {

    public EntityControlledLapis(World worldIn) {
        super(worldIn);
        this.setServitude(SERVE_WHITE_DIAMOND);
        this.setSize(0.6F, 1.9F);
        this.setUniformColor(0xFFFFFF);
        this.setInsigniaColor(0xFFFFFF);
        this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.BACK_OF_HEAD);
        this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.FOREHEAD);
        this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.LEFT_EYE);
        this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.RIGHT_EYE);
        this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.BACK);
        this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.CHEST);
        this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.BELLY);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(130.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 30.0F));
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata){
        return super.onInitialSpawn(difficulty, livingdata);
    }

    public void setPearl(EntityLapisLazuli lapis){
        this.setGemPlacement(lapis.getGemPlacement().id);
    }

    @Override
    public void onDeath(DamageSource source){
        super.onDeath(source);
        this.dropItem(ModItems.LAPIS_LAZULI_GEM, 1);
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
