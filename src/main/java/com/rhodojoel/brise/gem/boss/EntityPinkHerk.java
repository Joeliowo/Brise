package com.rhodojoel.brise.gem.boss;

import com.google.common.base.Predicate;
import com.rhodojoel.brise.init.BriseItems;
import mod.akrivus.kagic.entity.EntityGem;
import mod.akrivus.kagic.entity.ai.EntityAIStay;
import mod.akrivus.kagic.entity.gem.GemCuts;
import mod.akrivus.kagic.entity.gem.GemPlacements;
import mod.akrivus.kagic.entity.pepo.EntityMelon;
import mod.akrivus.kagic.init.ModSounds;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.*;

public class EntityPinkHerk extends EntityGem {
    private BossInfoServer healthBar = new BossInfoServer(this.getDisplayName(), BossInfo.Color.PINK, BossInfo.Overlay.PROGRESS);
    private int sadTicks = 0;
    public List<EntityLivingBase> summoned = new ArrayList<EntityLivingBase>();

    public EntityPinkHerk(World worldIn) {
        super(worldIn);
        this.setSize(0.9F, 3.1F);
        this.setCutPlacement(GemCuts.FACETED, GemPlacements.BELLY);

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
                return input != null && !(input instanceof EntityPinkHerk);
            }
        }));

        // Apply entity attributes.
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
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
        if (this.sadTicks > 200 && !this.isDefective() && !(this.isDead || this.getHealth() <= 0.0F) && this.getHealth() < this.getMaxHealth()) {
            this.startCryingLikeAnEmo();
            this.sadTicks = 0;
        }
        if(this.ticksExisted % 400 == 0){
            this.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100));
        }
        this.sadTicks += 1;
        super.onLivingUpdate();
    }

    private void startCryingLikeAnEmo() {
        if (!this.world.isRemote) {
            int i = 0;
            boolean b = true;
            if(this.summoned.size() != 0) {
                for (EntityLivingBase base : this.summoned) {
                    if (base.getHealth() > 0F) {
                        i++;
                    }
                }
            }
            if(i > 4){
                b = false;
            }
            if(b) {
                for (int x = -5; x < 6; x++) {
                    for (int z = -5; z < 6; z++) {
                        EntityMelon pepo = new EntityMelon(this.world);
                        pepo.setPosition(this.posX + x, this.posY, this.posZ + z);
                        EntityZombie zombie = new EntityZombie(this.world);
                        zombie.setPosition(this.posX + x, this.posY, this.posZ + z);
                        EntitySkeleton skeleton = new EntitySkeleton(this.world);
                        skeleton.setPosition(this.posX + x, this.posY, this.posZ + z);
                        if (this.rand.nextInt(5) == 2) {
                            if (this.rand.nextBoolean()) {
                                this.world.spawnEntity(pepo);
                                this.summoned.add(pepo);
                            } else {
                                if (this.rand.nextBoolean()) {
                                    this.world.spawnEntity(zombie);
                                    this.summoned.add(zombie);
                                } else {
                                    this.world.spawnEntity(skeleton);
                                    this.summoned.add(skeleton);
                                }
                            }
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
        this.dropItem(BriseItems.PINK_GEM, 1);
        if (!this.world.isRemote) {
            for(int x = -10; x < 11; x++){
                for(int z = -10; z < 11; z++){
                    EntityMelon pepo = new EntityMelon(this.world);
                    pepo.setPosition(this.posX + x, this.posY, this.posZ + z);
                    EntityZombie zombie = new EntityZombie(this.world);
                    zombie.setPosition(this.posX + x, this.posY, this.posZ + z);
                    EntitySkeleton skeleton = new EntitySkeleton(this.world);
                    skeleton.setPosition(this.posX + x, this.posY, this.posZ + z);
                    if(this.rand.nextInt(5) == 2){
                        if(this.rand.nextBoolean()){
                            this.world.spawnEntity(pepo);
                        }
                        else{
                            if(this.rand.nextBoolean()){
                                this.world.spawnEntity(zombie);
                            }
                            else{
                                this.world.spawnEntity(skeleton);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public int generateSkinColor(){
        return 0xFFDBE3;
    }

    @Override
    public int generateHairColor(){
        return 0xF28CBD;
    }

    @Override
    public int generateHairStyle(){
        return 0;
    }

    /*********************************************************
     * Methods related to sounds.                            *
     *********************************************************/

    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.ROSE_QUARTZ_HURT;
    }

    protected SoundEvent getDeathSound() {
        return ModSounds.ROSE_QUARTZ_DEATH;
    }
}