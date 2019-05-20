package com.rhodojoel.brise.gem;

import java.util.*;

import com.google.common.base.Predicate;

import com.rhodojoel.brise.init.BriseItems;
import com.rhodojoel.brise.init.BriseSounds;
import mod.akrivus.kagic.entity.ai.*;
import mod.akrivus.kagic.entity.EntityGem;
import mod.akrivus.kagic.entity.gem.GemCuts;
import mod.akrivus.kagic.entity.gem.GemPlacements;
import mod.heimrarnadalr.kagic.util.Colors;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockGrass;
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
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class EntityBlackJade extends EntityGem {
	public static final HashMap<IBlockState, Double> BLACKJADE_YIELDS = new HashMap<IBlockState, Double>();

	public static final double BLACKJADE_DEFECTIVITY_MULTIPLIER = 1;
	public static final double BLACKJADE_DEPTH_THRESHOLD = 0;


	private static final int NUM_HAIRSTYLES = 5;

	public static final int SKIN_COLOR_BEGIN = 3487029;
	public static final int SKIN_COLOR_END = 5526612;
    public static final int HAIR_COLOR_BEGIN = 2171169;
    public static final int HAIR_COLOR_END = 1118481;
	public float SoundVolume;
	public int visionTicks;

	public EntityBlackJade(World worldIn) {
		super(worldIn);
		this.setSize(0.6F, 1.9F);
		this.isImmuneToFire = true;
		this.nativeColor = 15;
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

		this.droppedGemItem = BriseItems.BLACK_JADE_GEM;
		this.droppedCrackedGemItem = BriseItems.CRACKED_BLACK_JADE_GEM;

	}

	public boolean attackEntityAsMob(Entity entityIn) {
		((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.WITHER, 200));
		return super.attackEntityAsMob(entityIn);
	}

	public void onLivingUpdate() {
		/*if(this.world.getBlockState(this.getPosition().down()).getBlock() == Blocks.COAL_ORE){
			this.world.setBlockState(this.getPosition().down(), Blocks.OBSIDIAN.getDefaultState());
		}
		if(this.world.getBlockState(this.getPosition().down()).getBlock() == Blocks.COAL_BLOCK){
			this.world.setBlockState(this.getPosition().down(), Blocks.OBSIDIAN.getDefaultState());
		}*/
		if (this.visionTicks > 80 && !this.isDefective() && !(this.isDead || this.getHealth() <= 0.0F)) {
			this.CarrotsMuthaFucker();
			this.visionTicks = 0;
		}
		if (!this.onGround && this.motionY < 0.0D) {
			this.motionY *= 0.75D;
		}
		this.visionTicks += 1;
		super.onLivingUpdate();
	}

	public void CarrotsMuthaFucker(){
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
									entity.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 400, 1));
								}
							}
							else {
								entity.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 400, 1));
							}
						}
					}
					if (this.isOwner(entity)) {
						entity.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 400, 1));
					}
				}
			}
		}
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand){
		if (!this.world.isRemote) {
			if (hand == EnumHand.MAIN_HAND) {
				ItemStack stack = player.getHeldItemMainhand();
				if (this.isTamed()) {
					if (this.isOwner(player)) {
						if (this.isCoreItem(stack)) {
							return super.processInteract(player, hand);
						}
						else {
							//this.openGUI(player);
							if(stack == new ItemStack(Blocks.COAL_ORE) || stack == new ItemStack(Blocks.COAL_BLOCK)){
								stack.shrink(1);
								this.dropItem(new ItemStack(Blocks.OBSIDIAN).getItem(), 1);
							}
						}
					}
				}
			}
		}
		return super.processInteract(player, hand);
	}

	@Override
	public int generateGemColor() {
		return 1184274;
	}

    @Override
    protected int generateSkinColor() {
        ArrayList<Integer> skinColors = new ArrayList<Integer>();
        skinColors.add(EntityBlackJade.SKIN_COLOR_BEGIN);
        skinColors.add(EntityBlackJade.SKIN_COLOR_END);
        return Colors.arbiLerp(skinColors);
    }

	@Override
	protected int generateHairStyle() {
		return this.rand.nextInt(EntityBlackJade.NUM_HAIRSTYLES);
	}

    @Override
    protected int generateHairColor() {
        ArrayList<Integer> hairColors = new ArrayList<Integer>();
        hairColors.add(EntityBlackJade.HAIR_COLOR_BEGIN);
        hairColors.add(EntityBlackJade.HAIR_COLOR_END);
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
			return BriseSounds.JADE_DEATH;
	}
}

