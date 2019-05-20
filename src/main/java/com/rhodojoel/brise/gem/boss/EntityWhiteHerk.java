package com.rhodojoel.brise.gem.boss;

import com.google.common.base.Predicate;
import com.ibm.icu.text.SymbolTable;
import com.rhodojoel.brise.gem.possessed.*;
import com.rhodojoel.brise.init.BriseItems;
import com.rhodojoel.brise.init.BriseSounds;
import mod.akrivus.kagic.entity.EntityGem;
import mod.akrivus.kagic.entity.ai.EntityAIStay;
import mod.akrivus.kagic.entity.gem.*;
import mod.akrivus.kagic.init.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import org.glassfish.grizzly.ThreadCache;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EntityWhiteHerk extends EntityGem {
    private BossInfoServer healthBar = new BossInfoServer(this.getDisplayName(), BossInfo.Color.WHITE, BossInfo.Overlay.PROGRESS);
    private int stealTicks = 0;
    public List<EntityGem> possessed = new ArrayList<>();
    public boolean speechDone = false;

    public EntityWhiteHerk(World worldIn) {
        super(worldIn);
        this.setSize(1.5F, 5.7F);;
        this.setCutPlacement(GemCuts.FACETED, GemPlacements.FOREHEAD);

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
                return input != null && !(input instanceof EntityWhiteHerk);
            }
        }));

        // Apply entity attributes.
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(500D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
        this.droppedGemItem = null;
        this.droppedCrackedGemItem = null;
    }

    /*********************************************************
     * Methods related to loading.                           *
     *********************************************************/
    public void writeEntityToNBT(NBTTagCompound compound) {
        compound.setBoolean("speechDone", this.speechDone);
        super.writeEntityToNBT(compound);
    }
    public void readEntityFromNBT(NBTTagCompound compound) {
        this.speechDone = compound.getBoolean("speechDone");
        super.readEntityFromNBT(compound);
    }

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
        if (this.stealTicks > 200 && !this.isDefective() && !(this.isDead || this.getHealth() <= 0.0F)) {
            this.startPossessingLikeAGiantWoman();
            this.stealTicks = 0;
        }
        this.stealTicks += 1;
        if(this.getHealth() > 0 && this.getHealth() < 30 && !this.speechDone){
            AxisAlignedBB axisalignedbb = (new AxisAlignedBB(this.posX, this.posY, this.posZ, (this.posX + 1), (this.posY + 1), (this.posZ + 1))).grow(16.0, (double) this.world.getHeight(), 16.0);
            List<EntityLivingBase> list = this.world.<EntityLivingBase>getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);
            for(EntityLivingBase base : list){
                base.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 600, 100));
            }
            this.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 100));
            for(EntityPlayer player : world.playerEntities){
                player.sendMessage(new TextComponentString("Are you really making the wisest decision?" +
                        " I'm sure you've noticed a trend with my sisters." +
                        " When our form is destabilized, it is destroyed forever." +
                        " We are mere husks. Memories and thoughts floating around forever in a twisted palace of crystal." +
                        " Slaves to our own gemstones, the very things that give us life." +
                        " And you would feel no guilt in sending us to that permanent damnation?" +
                        " To be frank, I don't think you would. You haven't shown mercy thus far, have you?" +
                        " I will release you in 30 seconds. The choice is yours..."));
                continue;
            }
            this.speechDone = true;
        }
        super.onLivingUpdate();
    }

    private void startPossessingLikeAGiantWoman() {
        if (!this.world.isRemote) {
            if(this.possessed.size() > 0) {
                this.playSound(BriseSounds.WHITE_POSSESS, this.getSoundVolume(), this.getSoundPitch());
            }
            AxisAlignedBB axisalignedbb = (new AxisAlignedBB(this.posX, this.posY, this.posZ, (this.posX + 1), (this.posY + 1), (this.posZ + 1))).grow(16.0, (double) this.world.getHeight(), 16.0);
            List<EntityLivingBase> list = this.world.<EntityLivingBase>getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);
            int i = 0;
            boolean b = true;
            if(this.possessed.size() != 0) {
                for (EntityLivingBase base : this.possessed) {
                    if (base.getHealth() > 0F) {
                        i++;
                    }
                }
            }
            if(i > 4){
                b = false;
            }
            if(b) {
                for (EntityLivingBase entity : list) {
                    if (!entity.isDead || entity.getHealth() > 0.0F) {
                        if(entity instanceof EntityPlayer){
                            if(entity.getHeldItemOffhand().getItem() == Items.SHIELD || entity.getHeldItemMainhand().getItem() == Items.SHIELD){
                                this.playSound(BriseSounds.WHITE_SHIELD, this.getSoundVolume(), this.getSoundPitch());
                            }
                        }
                        else if (entity instanceof EntityGem) {
                            EntityGem gem = (EntityGem) entity;
                            if (gem instanceof EntityPearl) {
                                EntityControlledPearl pearl = new EntityControlledPearl(this.world);
                                EntityPearl porl = (EntityPearl) gem;
                                pearl.setPosition(gem.posX, gem.posY, gem.posZ);
                                this.world.spawnEntity(pearl);
                                pearl.onInitialSpawn(this.world.getDifficultyForLocation(this.getPosition()), null);
                                pearl.setPearl(porl);
                                gem.setDead();
                                this.playSound(BriseSounds.WHITE_POSSESS_SPEECH, this.getSoundVolume(), this.getSoundPitch());
                            }
                            else if (gem instanceof EntityRuby) {
                                EntityPossessedRuby ruby = new EntityPossessedRuby(this.world);
                                EntityRuby rub = (EntityRuby) gem;
                                ruby.setPosition(gem.posX, gem.posY, gem.posZ);
                                this.world.spawnEntity(ruby);
                                ruby.onInitialSpawn(this.world.getDifficultyForLocation(this.getPosition()), null);
                                ruby.setRuby(rub);
                                gem.setDead();
                                this.playSound(BriseSounds.WHITE_POSSESS_SPEECH, this.getSoundVolume(), this.getSoundPitch());
                            }
                            else if (gem instanceof EntityAgate) {
                                EntityPossessedAgate ruby = new EntityPossessedAgate(this.world);
                                EntityAgate rub = (EntityAgate) gem;
                                ruby.setPosition(gem.posX, gem.posY, gem.posZ);
                                this.world.spawnEntity(ruby);
                                ruby.onInitialSpawn(this.world.getDifficultyForLocation(this.getPosition()), null);
                                ruby.setRuby(rub);
                                gem.setDead();
                                this.playSound(BriseSounds.WHITE_POSSESS_SPEECH, this.getSoundVolume(), this.getSoundPitch());
                            }
                            else if (gem instanceof EntityJasper || gem instanceof EntityAmethyst || gem instanceof EntityCarnelian
                                    || gem instanceof EntityCitrine) {
                                EntityPossessedQuartz ruby = new EntityPossessedQuartz(this.world);
                                EntityQuartzSoldier rub = (EntityQuartzSoldier) gem;
                                ruby.setPosition(gem.posX, gem.posY, gem.posZ);
                                this.world.spawnEntity(ruby);
                                ruby.onInitialSpawn(this.world.getDifficultyForLocation(this.getPosition()), null);
                                ruby.setRuby(rub);
                                gem.setDead();
                                this.playSound(BriseSounds.WHITE_POSSESS_SPEECH, this.getSoundVolume(), this.getSoundPitch());
                            }
                            else{
                                entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 200));
                                entity.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 200));
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
        this.dropItem(BriseItems.WHITE_GEM, 1);
        for(int i = 0; i < 10; i++){
            EntityJasper jasper = new EntityJasper(this.world);
            jasper.setPosition(this.posX, this.posY, this.posZ);
            jasper.onInitialSpawn(this.world.getDifficultyForLocation(this.getPosition()), null);
            this.world.spawnEntity(jasper);
        }
        if (!this.world.isRemote) {
            AxisAlignedBB axisalignedbb = (new AxisAlignedBB(this.posX, this.posY, this.posZ, (this.posX + 1), (this.posY + 1), (this.posZ + 1))).grow(16.0, (double) this.world.getHeight(), 16.0);
            List<EntityGem> list = this.world.<EntityGem>getEntitiesWithinAABB(EntityGem.class, axisalignedbb);
            if(true) {
                for (EntityLivingBase entity : list) {
                    if (!entity.isDead || entity.getHealth() > 0.0F) {
                        if (entity instanceof EntityGem) {
                            EntityGem gem = (EntityGem) entity;
                            if (gem instanceof EntityPearl) {
                                EntityControlledPearl pearl = new EntityControlledPearl(this.world);
                                EntityPearl porl = (EntityPearl) gem;
                                pearl.setPosition(gem.posX, gem.posY, gem.posZ);
                                this.world.spawnEntity(pearl);
                                pearl.onInitialSpawn(this.world.getDifficultyForLocation(this.getPosition()), null);
                                pearl.setPearl(porl);
                                gem.setDead();
                            }
                            else if (gem instanceof EntityRuby) {
                                EntityPossessedRuby ruby = new EntityPossessedRuby(this.world);
                                EntityRuby rub = (EntityRuby) gem;
                                ruby.setPosition(gem.posX, gem.posY, gem.posZ);
                                this.world.spawnEntity(ruby);
                                ruby.onInitialSpawn(this.world.getDifficultyForLocation(this.getPosition()), null);
                                ruby.setRuby(rub);
                                gem.setDead();
                            }
                            else if (gem instanceof EntityAgate) {
                                EntityPossessedAgate ruby = new EntityPossessedAgate(this.world);
                                EntityAgate rub = (EntityAgate) gem;
                                ruby.setPosition(gem.posX, gem.posY, gem.posZ);
                                this.world.spawnEntity(ruby);
                                ruby.onInitialSpawn(this.world.getDifficultyForLocation(this.getPosition()), null);
                                ruby.setRuby(rub);
                                gem.setDead();
                                this.playSound(BriseSounds.WHITE_POSSESS_SPEECH, this.getSoundVolume(), this.getSoundPitch());
                            }
                            else if (gem instanceof EntityJasper || gem instanceof EntityAmethyst || gem instanceof EntityCarnelian
                                    || gem instanceof EntityCitrine) {
                                EntityPossessedQuartz ruby = new EntityPossessedQuartz(this.world);
                                EntityQuartzSoldier rub = (EntityQuartzSoldier) gem;
                                ruby.setPosition(gem.posX, gem.posY, gem.posZ);
                                this.world.spawnEntity(ruby);
                                ruby.onInitialSpawn(this.world.getDifficultyForLocation(this.getPosition()), null);
                                ruby.setRuby(rub);
                                gem.setDead();
                                this.playSound(BriseSounds.WHITE_POSSESS_SPEECH, this.getSoundVolume(), this.getSoundPitch());
                            }
                            else{
                                entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 200, 2));
                                entity.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 200, 3));
                            }
                        }
                    }
                }
            }
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
    public int generateHairStyle(){
        return 0;
    }

    /*********************************************************
     * Methods related to sounds.                            *
     *********************************************************/
    protected SoundEvent getAmbientSound() {
        return BriseSounds.WHITE_LIVING;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return BriseSounds.WHITE_HURT;
    }
}