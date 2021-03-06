package com.rhodojoel.brise.gem;

import java.util.*;

import com.google.common.base.Predicate;

import com.rhodojoel.brise.init.BriseItems;
import com.rhodojoel.brise.init.BriseSounds;
import mod.akrivus.kagic.entity.ai.*;
import mod.akrivus.kagic.entity.EntityGem;
import mod.akrivus.kagic.entity.gem.GemCuts;
import mod.akrivus.kagic.entity.gem.GemPlacements;
import mod.akrivus.kagic.init.ModSounds;
import mod.heimrarnadalr.kagic.util.Colors;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class EntityMorganite extends EntityGem implements IAnimals {
    public static final HashMap<IBlockState, Double> MORGANITE_YIELDS = new HashMap<IBlockState, Double>();

    public static final double MORGANITE_DEFECTIVITY_MULTIPLIER = 1;
    public static final double MORGANITE_DEPTH_THRESHOLD = 0;


    private static final int NUM_HAIRSTYLES = 1;

    public static final int SKIN_COLOR_BEGIN = 16704474;
    public static final int SKIN_COLOR_END = 16244953;
    public static final int HAIR_COLOR_BEGIN = 15048322;
    public static final int HAIR_COLOR_END = 13274227;
    public int morgTicks;

    public EntityMorganite(World worldIn) {
        super(worldIn);
        this.setSize(0.4F, 0.8F);
        this.nativeColor = 6;
        this.setServitude(EntityGem.SERVE_REBELLION);

        this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.BACK_OF_HEAD);
        this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.FOREHEAD);
        this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.LEFT_EYE);
        this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.RIGHT_EYE);
        this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.LEFT_CHEEK);
        this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.RIGHT_CHEEK);
        this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.BACK);
        this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.CHEST);
        this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.BELLY);
        this.setCutPlacement(GemCuts.TINY, GemPlacements.MOUTH);

        this.stayAI = new EntityAIStay(this);
        this.tasks.addTask(2, new EntityAIFollowDiamond(this, 1.0D));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));


        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
        this.droppedGemItem = BriseItems.MORGANITE_GEM;
        this.droppedCrackedGemItem = BriseItems.CRACKED_MORGANITE_GEM;

    }

    public void onLivingUpdate() {
        if (!this.onGround && this.motionY < 0.0D) {
            this.motionY *= 0.75D;
        }
        if (this.morgTicks > 80 && !this.isDefective() && !(this.isDead || this.getHealth() <= 0.0F)) {
            this.MorganiteHealing();
            this.morgTicks = 0;
        }
        if(this.morgTicks % 10 == 0 && this.isPrimary()){
            this.world.spawnParticle(EnumParticleTypes.HEART, (double) this.posX - 0.5 + Math.random(), (double) this.posY + Math.random(), (double) this.posZ - 0.5 + Math.random(), 0.0D, 0.0D, 0.0D, new int[0]);
        }
        this.morgTicks += 1;
        super.onLivingUpdate();
    }

    public void MorganiteHealing() {
        if (!this.world.isRemote) {
            Random rand = new Random();
            AxisAlignedBB axisalignedbb = (new AxisAlignedBB(this.posX, this.posY, this.posZ, (this.posX + 1), (this.posY + 1), (this.posZ + 1))).grow(16.0, (double) this.world.getHeight(), 16.0);
            List<EntityLivingBase> list = this.world.<EntityLivingBase>getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);
            for (EntityLivingBase entity : list) {
                if (!entity.isDead || entity.getHealth() > 0.0F) {
                    if (entity instanceof EntityGem) {
                        EntityGem gem = (EntityGem) entity;
                        if (this.getServitude() == gem.getServitude()) {
                            if (this.getServitude() == EntityGem.SERVE_HUMAN) {
                                if (this.isOwnerId(gem.getOwnerId())) {
                                    entity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 400, 1));
                                }
                            }
                            else {
                                entity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 400, 1));
                            }
                        }
                    }
                    if (this.isOwner(entity)) {
                        entity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 400, 1));
                    }
                }
            }
        }
    }

    @Override
    protected int generateGemColor() {
        return 16766684;
    }

    @Override
    protected int generateSkinColor() {
        ArrayList<Integer> skinColors = new ArrayList<Integer>();
        skinColors.add(EntityMorganite.SKIN_COLOR_BEGIN);
        skinColors.add(EntityMorganite.SKIN_COLOR_END);
        return Colors.arbiLerp(skinColors);
    }

    @Override
    protected int generateHairStyle() {
        return this.rand.nextInt(EntityMorganite.NUM_HAIRSTYLES);
    }

    @Override
    protected int generateHairColor() {
        ArrayList<Integer> hairColors = new ArrayList<Integer>();
        hairColors.add(EntityMorganite.HAIR_COLOR_BEGIN);
        hairColors.add(EntityMorganite.HAIR_COLOR_END);
        return Colors.arbiLerp(hairColors);
    }


    protected SoundEvent getHurtSound(DamageSource source) {

        if(this.getGemPlacement() == GemPlacements.MOUTH){
            return null;
        }
        else return ModSounds.AQUAMARINE_HURT;
    }
    protected SoundEvent getObeySound() {
        if(this.getGemPlacement() == GemPlacements.MOUTH){
            return null;
        }
        else
        return ModSounds.AQUAMARINE_OBEY;
    }
    protected SoundEvent getDeathSound() {
        if(this.getGemPlacement() == GemPlacements.MOUTH){
            return null;
        }
        else
        return ModSounds.AQUAMARINE_DEATH;
    }
}