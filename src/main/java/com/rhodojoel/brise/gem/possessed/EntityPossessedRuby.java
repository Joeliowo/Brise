package com.rhodojoel.brise.gem.possessed;

import mod.akrivus.kagic.entity.EntityGem;
import mod.akrivus.kagic.entity.gem.EntityPearl;
import mod.akrivus.kagic.entity.gem.EntityRuby;
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

public class EntityPossessedRuby extends EntityGem {

    public EntityPossessedRuby(World worldIn) {
        super(worldIn);
        this.setServitude(SERVE_WHITE_DIAMOND);
        this.setSize(0.7F, 1.2F);
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
        this.setCutPlacement(GemCuts.SQUARE, GemPlacements.BACK_OF_HEAD);
        this.setCutPlacement(GemCuts.SQUARE, GemPlacements.FOREHEAD);
        this.setCutPlacement(GemCuts.SQUARE, GemPlacements.LEFT_EYE);
        this.setCutPlacement(GemCuts.SQUARE, GemPlacements.RIGHT_EYE);
        this.setCutPlacement(GemCuts.SQUARE, GemPlacements.LEFT_CHEEK);
        this.setCutPlacement(GemCuts.SQUARE, GemPlacements.RIGHT_CHEEK);
        this.setCutPlacement(GemCuts.SQUARE, GemPlacements.LEFT_SHOULDER);
        this.setCutPlacement(GemCuts.SQUARE, GemPlacements.RIGHT_SHOULDER);
        this.setCutPlacement(GemCuts.SQUARE, GemPlacements.LEFT_HAND);
        this.setCutPlacement(GemCuts.SQUARE, GemPlacements.RIGHT_HAND);
        this.setCutPlacement(GemCuts.SQUARE, GemPlacements.BACK);
        this.setCutPlacement(GemCuts.SQUARE, GemPlacements.CHEST);
        this.setCutPlacement(GemCuts.SQUARE, GemPlacements.BELLY);
        this.setCutPlacement(GemCuts.SQUARE, GemPlacements.LEFT_THIGH);
        this.setCutPlacement(GemCuts.SQUARE, GemPlacements.RIGHT_THIGH);
        this.setCutPlacement(GemCuts.SQUARE, GemPlacements.LEFT_KNEE);
        this.setCutPlacement(GemCuts.SQUARE, GemPlacements.RIGHT_KNEE);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(160D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 30.0F));
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata){
        return super.onInitialSpawn(difficulty, livingdata);
    }

    public void setRuby(EntityRuby ruby){
        this.setGemPlacement(ruby.getGemPlacement().id);
    }

    @Override
    public void onDeath(DamageSource source){
        super.onDeath(source);
        this.dropItem(ModItems.RUBY_GEM, 1);
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
