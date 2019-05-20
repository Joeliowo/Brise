package com.rhodojoel.brise.gem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.rhodojoel.brise.gem.ai.EntityAIConvertRebels;
import com.rhodojoel.brise.init.BriseItems;
import com.rhodojoel.brise.init.BriseSounds;
import mod.akrivus.kagic.entity.EntityGem;
import mod.akrivus.kagic.entity.ai.*;
import mod.akrivus.kagic.entity.gem.GemCuts;
import mod.akrivus.kagic.entity.gem.GemPlacements;
import mod.akrivus.kagic.init.ModSounds;
import mod.heimrarnadalr.kagic.util.Colors;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityPezz extends EntityGem implements IAnimals {
    public static final HashMap<IBlockState, Double> PEZZ_YIELDS = new HashMap<IBlockState, Double>();
    public static final double PEZZ_DEFECTIVITY_MULTIPLIER = 1;

    private static final int SKIN_COLOR_BEGIN = 0xED8799;
    private static final int SKIN_COLOR_END = 0xD93D92 ;

    private static final int NUM_HAIRSTYLES = 1;

    private static final int HAIR_COLOR_BEGIN = 0xCC3962;
    private static final int HAIR_COLOR_END = 0x8E1857;

    public boolean decidedName;
    public String naem;

    public EntityPezz(World world) {
        super(world);
        this.nativeColor = 6;
        this.setSize(0.9F, 2.3F);
        this.isSoldier = true;

        Random rand = new Random();
        if (this.isDefective()) {
            this.naem = "Raspberry Beryl";
            if (rand.nextBoolean()) {
                this.naem = "Raspberyl";
            }
        }
        else {
            this.naem = "Pezzottaite";
        }
        this.setCustomNameTag(this.naem);

        this.setCutPlacement(GemCuts.FACETED, GemPlacements.FOREHEAD);
        this.setCutPlacement(GemCuts.FACETED, GemPlacements.RIGHT_EYE);
        this.setCutPlacement(GemCuts.FACETED, GemPlacements.LEFT_EYE);
        this.setCutPlacement(GemCuts.FACETED, GemPlacements.CHEST);
        this.setCutPlacement(GemCuts.FACETED, GemPlacements.BACK);
        this.setCutPlacement(GemCuts.TINY, GemPlacements.BELLY);
        this.setCutPlacement(GemCuts.TINY, GemPlacements.MOUTH);

        // Apply entity AI
        this.stayAI = new EntityAIStay(this);
        this.tasks.addTask(1, new EntityAIFollowDiamond(this, 1.0D));
        this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 0.414D, 32.0F));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIStandGuard(this, 0.6D));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0F));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityMob.class, 16.0F));
        this.tasks.addTask(5, new EntityAILookIdle(this));
        this.tasks.addTask(9, new EntityAIConvertRebels(this, 0.1D));

        // Apply targeting
        this.targetTasks.addTask(1, new EntityAIDiamondHurtByTarget(this));

        // Apply entity attributes
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(150.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
        this.droppedGemItem = BriseItems.PEZZ_GEM;
        this.droppedCrackedGemItem = BriseItems.CRACKED_PEZZ_GEM;
    }

        @Override
        public void whenDefective() {
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80.0D);
            this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
            this.setSize(0.72F, 1.61F);
        }


    @Override
    public void whenPrimary() {
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(180.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10.0D);
        this.setSize(0.9F, 3.0F);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if(this.getGemPlacement() == GemPlacements.MOUTH){
            return null;
        }
        else{
            return BriseSounds.PEZZ_LIVING;
        }
    }

    @Override
    protected SoundEvent getDeathSound() {
        if(this.getGemPlacement() == GemPlacements.MOUTH){
            return null;
        }
        else
        return  BriseSounds.PEZZ_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        if(this.getGemPlacement() == GemPlacements.MOUTH){
            return null;
        }
        else
        return  BriseSounds.PEZZ_HURT;
    }

    @Override
    protected SoundEvent getObeySound() {
        if(this.getGemPlacement() == GemPlacements.MOUTH){
            return null;
        }
        else
        return  BriseSounds.PEZZ_OBEY;
    }

    /*********************************************************
     * Methods related to rendering.						 *
     *********************************************************/
    @Override
    protected int generateSkinColor() {
        ArrayList<Integer> skinColors = new ArrayList<Integer>();
        skinColors.add(EntityPezz.SKIN_COLOR_BEGIN);
        skinColors.add(EntityPezz.SKIN_COLOR_END);
        return Colors.arbiLerp(skinColors);
    }

    @Override
    protected int generateHairStyle() {
        return this.rand.nextInt(EntityPezz.NUM_HAIRSTYLES);
    }

    @Override
    protected int generateGemColor(){
        return 0xE24479;
    }

    @Override
    protected int generateHairColor() {
        ArrayList<Integer> hairColors = new ArrayList<Integer>();
        hairColors.add(EntityPezz.HAIR_COLOR_BEGIN);
        hairColors.add(EntityPezz.HAIR_COLOR_END);
        return Colors.arbiLerp(hairColors);
    }
}
