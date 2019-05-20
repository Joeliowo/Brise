package com.rhodojoel.brise.gem.boss;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import com.rhodojoel.brise.init.BriseGems;
import com.rhodojoel.brise.init.BriseItems;
import mod.akrivus.kagic.entity.EntityGem;
import mod.akrivus.kagic.entity.ai.EntityAIAlignGems;
import mod.akrivus.kagic.entity.ai.EntityAIDiamondHurtByTarget;
import mod.akrivus.kagic.entity.ai.EntityAIDiamondHurtTarget;
import mod.akrivus.kagic.entity.ai.EntityAIStay;
import mod.akrivus.kagic.entity.gem.EntityPearl;
import mod.akrivus.kagic.entity.gem.GemCuts;
import mod.akrivus.kagic.entity.gem.GemPlacements;
import mod.akrivus.kagic.init.ModItems;
import mod.akrivus.kagic.init.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityYellowHerk extends EntityGem {
    private BossInfoServer healthBar = new BossInfoServer(this.getDisplayName(), BossInfo.Color.YELLOW, BossInfo.Overlay.PROGRESS);

    public EntityYellowHerk(World worldIn) {
        super(worldIn);
        this.setSize(0.9F, 3.1F);
        this.setCutPlacement(GemCuts.FACETED, GemPlacements.CHEST);

        // Boss stuff.
        this.experienceValue = 6000;
        this.isImmuneToFire = true;
        this.isDiamond = true;

        // Apply entity AI.
        this.stayAI = new EntityAIStay(this);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(4, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));

        // Apply targeting.
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget<EntityLivingBase>(this, EntityLivingBase.class, 10, true, false, new Predicate<EntityLivingBase>() {
            public boolean apply(EntityLivingBase input) {
                return input != null && !(input instanceof EntityYellowHerk);
            }
        }));

        // Apply entity attributes.
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(250.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(2.0D);
        this.droppedGemItem = BriseItems.YELLOW_HERK_GEM;
        this.droppedCrackedGemItem = BriseItems.CRACKED_YELLOW_HERK_GEM;
    }

    /*********************************************************
     * Methods related to loading.                           *
     *********************************************************/

    /*********************************************************
     * Methods related to combat.                            *
     *********************************************************/
    protected void updateAITasks() {
        super.updateAITasks();
        this.healthBar.setPercent(this.getHealth() / this.getMaxHealth());
    }

    public void addTrackingPlayer(EntityPlayerMP player) {super.addTrackingPlayer(player);
        this.healthBar.addPlayer(player);
    }

    public void removeTrackingPlayer(EntityPlayerMP player) {super.removeTrackingPlayer(player);
        this.healthBar.removePlayer(player);
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        this.setServitude(EntityGem.SERVE_HOSTILE);
        this.setAttackAI();
        return super.onInitialSpawn(difficulty, livingdata);
    }

    @Override
    public void onLivingUpdate(){
        super.onLivingUpdate();
        if(this.ticksExisted % 60 == 0){
            AxisAlignedBB axisalignedbb = (new AxisAlignedBB(this.posX, this.posY, this.posZ, (this.posX + 1), (this.posY + 1), (this.posZ + 1))).grow(16.0, (double) this.world.getHeight(), 16.0);
            List<EntityLivingBase> list = this.world.<EntityLivingBase>getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);
            for(EntityLivingBase base : list) {
                if(base == this){
                    continue;
                }
                if(this.rand.nextInt(7) == 2) {
                    EntityLightningBolt bolt = new EntityLightningBolt(this.world, base.posX, base.posY, base.posZ, false);
                    if(!base.isBurning()){
                        this.world.spawnEntity(bolt);
                        if(base instanceof EntityGem){
                            if(base.getHealth() == 1F){
                                base.setHealth(0F);
                            }
                            base.setHealth(1F);
                        }
                    }
                }
            }
        }
    }

    /*********************************************************
     * Methods related to entity death.                      *
     *********************************************************/
    @Override
    public void onDeath(DamageSource source){
        AxisAlignedBB axisalignedbb = (new AxisAlignedBB(this.posX, this.posY, this.posZ, (this.posX + 1), (this.posY + 1), (this.posZ + 1))).grow(16.0, (double) this.world.getHeight(), 16.0);
        List<EntityLivingBase> list = this.world.<EntityLivingBase>getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);
        for(EntityLivingBase base : list) {
            if(true) {
                EntityLightningBolt bolt = new EntityLightningBolt(this.world, base.posX, base.posY, base.posZ, false);
                if(!base.isBurning()){
                    this.world.spawnEntity(bolt);
                    if(base instanceof EntityPlayer){
                        base.setHealth(1);
                    }
                    else{
                        base.setHealth(0);
                    }
                }
            }
        }
        this.dropItem(BriseItems.YELLOW_GEM, 1);
    }

    @Override
    public int generateGemColor(){
        return 0xFDFE5A;
    }

    @Override
    public int generateSkinColor(){
        return 0xFBFF6D;
    }

    @Override
    public int generateHairColor(){
        return 0xF4FF01;
    }

    @Override
    public int generateHairStyle(){
        return 0;
    }

    /*********************************************************
     * Methods related to sounds.                            *
     *********************************************************/
    protected SoundEvent getAmbientSound() {
        return ModSounds.YELLOW_DIAMOND_LIVING;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.YELLOW_DIAMOND_HURT;
    }

    protected SoundEvent getDeathSound() {
        return ModSounds.YELLOW_DIAMOND_DEATH;
    }
}