package com.rhodojoel.brise.gem;

import java.util.*;

import com.google.common.base.Predicate;

import com.rhodojoel.brise.init.BriseItems;
import com.rhodojoel.brise.init.BriseSounds;
import mod.akrivus.kagic.entity.ai.*;
import mod.akrivus.kagic.entity.EntityGem;
import mod.akrivus.kagic.entity.gem.GemCuts;
import mod.akrivus.kagic.entity.gem.GemPlacements;
import mod.akrivus.kagic.init.ModBlocks;
import mod.heimrarnadalr.kagic.util.Colors;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
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
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class EntityWhiteJade extends EntityGem {
	public static final HashMap<IBlockState, Double> WHITEJADE_YIELDS = new HashMap<IBlockState, Double>();

	public static final double WHITEJADE_DEFECTIVITY_MULTIPLIER = 1;
	public static final double WHITEJADE_DEPTH_THRESHOLD = 0;


	private static final int NUM_HAIRSTYLES = 5;

	public static final int SKIN_COLOR_BEGIN = 16777215;
	public static final int SKIN_COLOR_END = 16775930;
    public static final int HAIR_COLOR_BEGIN = 14869218;
    public static final int HAIR_COLOR_END = 13421772;
	public float SoundVolume;
	public int speedTicks;

	public EntityWhiteJade(World worldIn) {
		super(worldIn);
		this.setSize(0.6F, 1.9F);
		this.isImmuneToFire = true;
		this.nativeColor = 0;
		this.seePastDoors();
		this.canTalk = true;
		this.SoundVolume = this.getSoundVolume();
		this.isSoldier = true;

		setCutPlacement(GemCuts.CABOCHON, GemPlacements.BACK_OF_HEAD);
		setCutPlacement(GemCuts.CABOCHON, GemPlacements.FOREHEAD);
		setCutPlacement(GemCuts.CABOCHON, GemPlacements.LEFT_EYE);
		setCutPlacement(GemCuts.CABOCHON, GemPlacements.RIGHT_EYE);
		setCutPlacement(GemCuts.TINY, GemPlacements.LEFT_CHEEK);
		setCutPlacement(GemCuts.TINY, GemPlacements.RIGHT_CHEEK);
		setCutPlacement(GemCuts.CABOCHON, GemPlacements.BACK);
		setCutPlacement(GemCuts.CABOCHON, GemPlacements.CHEST);
		setCutPlacement(GemCuts.CABOCHON, GemPlacements.BELLY);
		setCutPlacement(GemCuts.TINY, GemPlacements.MOUTH);

		this.stayAI = new EntityAIStay(this);
		this.tasks.addTask(1, new EntityAICommandGems(this, 0.6D));
		this.tasks.addTask(3, new EntityAIFollowDiamond(this, 1.0D));
		this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0F));

		this.targetTasks.addTask(1, new EntityAIDiamondHurtByTarget(this));
		this.targetTasks.addTask(2, new EntityAIDiamondHurtTarget(this));
		this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget<EntityLiving>(this, EntityLiving.class, 10, true, false, new Predicate<EntityLiving>() {
			public boolean apply(EntityLiving input) {
				return input != null && IMob.VISIBLE_MOB_SELECTOR.apply(input);
			}
		}));

		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(150.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6D);

		this.droppedGemItem = BriseItems.WHITE_JADE_GEM;
		this.droppedCrackedGemItem = BriseItems.CRACKED_WHITE_JADE_GEM;

	}

	public int generateGemColor() {
		return 15724527;
	}

    @Override
    protected int generateSkinColor() {
        ArrayList<Integer> skinColors = new ArrayList<Integer>();
        skinColors.add(EntityWhiteJade.SKIN_COLOR_BEGIN);
        skinColors.add(EntityWhiteJade.SKIN_COLOR_END);
        return Colors.arbiLerp(skinColors);
    }

    public boolean attackEntityAsMob(Entity entityIn) {
        ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 200));
        return super.attackEntityAsMob(entityIn);
    }

	public void onLivingUpdate() {
		super.onLivingUpdate();
		BlockPos pos = new BlockPos(this.posX, this.posY, this.posZ);
		BlockPos pos2 = this.getPosition().down();
		if (!this.onGround && this.motionY < 0.0D) {
			this.motionY *= 0.75D;
		}
		if (!this.world.isRemote)
		{
			int i = MathHelper.floor(this.posX);
			int j = MathHelper.floor(this.posY);
			int k = MathHelper.floor(this.posZ);

			if (!this.world.getGameRules().getBoolean("mobGriefing"))
			{
				return;
			}

			for (int l = 0; l < 4; ++l)
			{
				i = MathHelper.floor(this.posX + (double)((float)(l % 2 * 2 - 1) * 0.25F));
				j = MathHelper.floor(this.posY);
				k = MathHelper.floor(this.posZ + (double)((float)(l / 2 % 2 * 2 - 1) * 0.25F));
				BlockPos blockpos = new BlockPos(i, j, k);

				if (this.world.getBlockState(blockpos).getMaterial() == Material.AIR && this.world.getBlockState(pos2).getMaterial() != Material.AIR)
				{
					this.world.setBlockState(blockpos, Blocks.SNOW_LAYER.getDefaultState());
				}
			}
		}
		if (this.speedTicks > 80 && !this.isDefective() && !(this.isDead || this.getHealth() <= 0.0F)) {
			this.SpeedTheFuckUp();
			this.speedTicks = 0;
		}
		this.speedTicks += 1;
	}

    public void SpeedTheFuckUp(){
	    if (!this.world.isRemote) {
        AxisAlignedBB axisalignedbb = (new AxisAlignedBB(this.posX, this.posY, this.posZ, (this.posX + 1), (this.posY + 1), (this.posZ + 1))).grow(16.0, (double) this.world.getHeight(), 16.0);
        List<EntityLivingBase> list = this.world.<EntityLivingBase>getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);
        for (EntityLivingBase entity : list) {
            if (!entity.isDead || entity.getHealth() > 0.0F) {
                if (entity instanceof EntityGem) {
                    EntityGem gem = (EntityGem) entity;
                    if (this.getServitude() == gem.getServitude()) {
                        if (this.getServitude() == EntityGem.SERVE_HUMAN) {
                            if (this.isOwnerId(gem.getOwnerId())) {
                                entity.addPotionEffect(new PotionEffect(MobEffects.SPEED, 400, 1));
                            }
                        }
                        else {
                            entity.addPotionEffect(new PotionEffect(MobEffects.SPEED, 400, 1));
                        }
                    }
                }
                if (this.isOwner(entity)) {
                    entity.addPotionEffect(new PotionEffect(MobEffects.SPEED, 400, 1));
                }
            }
        }
    }
    }

	@Override
	protected int generateHairStyle() {
		return this.rand.nextInt(EntityWhiteJade.NUM_HAIRSTYLES);
	}

    @Override
    protected int generateHairColor() {
        ArrayList<Integer> hairColors = new ArrayList<Integer>();
        hairColors.add(EntityWhiteJade.HAIR_COLOR_BEGIN);
        hairColors.add(EntityWhiteJade.HAIR_COLOR_END);
        return Colors.arbiLerp(hairColors);
    }


	protected SoundEvent getHurtSound(DamageSource source) {
		if(this.getGemPlacement() == GemPlacements.MOUTH){
			return null;
		}
		else
		return BriseSounds.JADE_HURT;
	}

	protected SoundEvent getObeySound() {
		if(this.getGemPlacement() == GemPlacements.MOUTH){
			return null;
		}
		else
		return BriseSounds.JADE_OBEY;
	}

	protected SoundEvent getDeathSound(){
		if(this.getGemPlacement() == GemPlacements.MOUTH){
			return null;
		}
		else
		return BriseSounds.JADE_DEATH;}
}

