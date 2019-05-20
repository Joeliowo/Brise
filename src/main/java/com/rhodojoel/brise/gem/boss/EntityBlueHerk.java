package com.rhodojoel.brise.gem.boss;

import com.google.common.base.Predicate;
import com.rhodojoel.brise.gem.possessed.EntityControlledPearl;
import com.rhodojoel.brise.init.BriseItems;
import mod.akrivus.kagic.entity.EntityGem;
import mod.akrivus.kagic.entity.ai.EntityAIStay;
import mod.akrivus.kagic.entity.gem.EntityPearl;
import mod.akrivus.kagic.entity.gem.GemCuts;
import mod.akrivus.kagic.entity.gem.GemPlacements;
import mod.akrivus.kagic.init.ModSounds;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class EntityBlueHerk extends EntityGem {
    private BossInfoServer healthBar = new BossInfoServer(this.getDisplayName(), BossInfo.Color.BLUE, BossInfo.Overlay.PROGRESS);
    private int sadTicks = 0;

    public EntityBlueHerk(World worldIn) {
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
                return input != null && !(input instanceof EntityBlueHerk);
            }
        }));

        // Apply entity attributes.
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(270.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
        this.droppedGemItem = null;
        this.droppedCrackedGemItem = null;
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

    public void onLivingUpdate() {
        if (this.sadTicks > 200 && !this.isDefective() && !(this.isDead || this.getHealth() <= 0.0F)) {
            this.startCryingLikeAnEmo();
            this.sadTicks = 0;
        }
        this.sadTicks += 1;
        super.onLivingUpdate();
    }

    private void startCryingLikeAnEmo() {
        if (!this.world.isRemote) {
            AxisAlignedBB axisalignedbb = (new AxisAlignedBB(this.posX, this.posY, this.posZ, (this.posX + 1), (this.posY + 1), (this.posZ + 1))).grow(16.0, (double) this.world.getHeight(), 16.0);
            List<EntityLivingBase> list = this.world.<EntityLivingBase>getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);
            for (EntityLivingBase entity : list) {
                if (!entity.isDead || entity.getHealth() > 0.0F) {
                    entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 200));
                    entity.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 200));
                    entity.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 200));
                    entity.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 200));
                }
            }
        }
    }

    /*********************************************************
     * Methods related to entity death.                      *
     *********************************************************/
    @Override
    public void onDeath(DamageSource source){
        this.dropItem(BriseItems.BLUE_GEM, 1);
        if (!this.world.isRemote) {
            AxisAlignedBB axisalignedbb = (new AxisAlignedBB(this.posX, this.posY, this.posZ, (this.posX + 1), (this.posY + 1), (this.posZ + 1))).grow(16.0, (double) this.world.getHeight(), 16.0);
            List<EntityLivingBase> list = this.world.<EntityLivingBase>getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);
            for (EntityLivingBase entity : list) {
                if (!entity.isDead || entity.getHealth() > 0.0F) {
                    entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 12000));
                    entity.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 12000));
                    entity.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 12000));
                    entity.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 12000));
                }
            }
        }
    }

    @Override
    public int generateGemColor(){
        return 0x3D81BF;
    }

    @Override
    public int generateSkinColor(){
        return 0x5784D9;
    }

    @Override
    public int generateHairColor(){
        return 0x151748;
    }

    @Override
    public int generateHairStyle(){
        return 0;
    }

    /*********************************************************
     * Methods related to sounds.                            *
     *********************************************************/
    protected SoundEvent getAmbientSound() {
        return ModSounds.BLUE_DIAMOND_LIVING;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.BLUE_DIAMOND_HURT;
    }

    protected SoundEvent getDeathSound() {
        return ModSounds.BLUE_DIAMOND_DEATH;
    }
}