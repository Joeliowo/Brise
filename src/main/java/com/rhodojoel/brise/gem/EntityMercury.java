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

public class EntityMercury extends EntityGem implements IAnimals {
    public static final HashMap<IBlockState, Double> MERCURY_YIELDS = new HashMap<IBlockState, Double>();

    public static final double MERCURY_DEFECTIVITY_MULTIPLIER = 1;
    public static final double MERCURY_DEPTH_THRESHOLD = 0;


    private static final int NUM_HAIRSTYLES = 1;

    public static final int SKIN_COLOR_BEGIN = 10526880;
    public static final int SKIN_COLOR_END = 9013641;
    public int mercTicks;

    public EntityMercury(World worldIn) {
        super(worldIn);
        this.setSize(0.9F, 1.9F);
        this.nativeColor = 8;

        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.BACK_OF_HEAD);
        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.LEFT_EYE);
        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.RIGHT_EYE);
        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.BACK);
        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.CHEST);
        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.BELLY);

        this.stayAI = new EntityAIStay(this);
        this.tasks.addTask(2, new EntityAIFollowDiamond(this, 1.0D));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));


        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(85.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);

        this.droppedGemItem = BriseItems.MERCURY_GEM;
        this.droppedCrackedGemItem = BriseItems.CRACKED_MERCURY_GEM;



    }

    public void onLivingUpdate() {
        if (!this.onGround && this.motionY < 0.0D) {
            this.motionY *= 0.75D;
        }
        if (this.mercTicks > 80 && !this.isDefective() && !(this.isDead || this.getHealth() <= 0.0F)) {
            this.MercuryPoison();
            this.mercTicks = 0;
        }
        this.mercTicks += 1;
        super.onLivingUpdate();
    }

    @Override
    public void whenDefective() {
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
        this.setSize(0.9F, 1.0F);
    }


    @Override
    public void whenPrimary() {
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10.0D);
        this.setSize(0.9F, 2.5F);
    }

    public void MercuryPoison() {
        if (!this.world.isRemote && this.isDefective()) {
            Random rand = new Random();
            AxisAlignedBB axisalignedbb = (new AxisAlignedBB(this.posX, this.posY, this.posZ, (this.posX + 1), (this.posY + 1), (this.posZ + 1))).grow(16.0, (double) this.world.getHeight(), 16.0);
            List<EntityLivingBase> list = this.world.<EntityLivingBase>getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);
            for (EntityLivingBase entity : list) {
                if (!entity.isDead || entity.getHealth() > 0.0F) {
                    if (entity instanceof EntityGem) {
                        EntityGem gem = (EntityGem) entity;
                        if (this.getServitude() == gem.getServitude()) {
                                if (this.isOwnerId(gem.getOwnerId())) {
                                    entity.addPotionEffect(new PotionEffect(MobEffects.POISON, 400, 1));
                                }
                            }
                            else {
                                entity.addPotionEffect(new PotionEffect(MobEffects.POISON, 400, 1));
                            }
                        }
                    }
                    if (this.isOwner(entity)) {
                        entity.addPotionEffect(new PotionEffect(MobEffects.POISON, 400, 1));
                    }
                }
            }
        }

    @Override
    protected int generateGemColor() {
        return 16777215;
    }

    @Override
    protected int generateSkinColor() {
        ArrayList<Integer> skinColors = new ArrayList<Integer>();
        skinColors.add(EntityMercury.SKIN_COLOR_BEGIN);
        skinColors.add(EntityMercury.SKIN_COLOR_END);
        return Colors.arbiLerp(skinColors);
    }


}