package com.rhodojoel.brise.gem;

import java.util.*;

import com.google.common.base.Predicate;

import com.rhodojoel.brise.init.BriseItems;
import com.rhodojoel.brise.init.BriseSounds;
import mod.akrivus.kagic.entity.ai.*;
import mod.akrivus.kagic.entity.EntityGem;
import mod.akrivus.kagic.entity.gem.GemCuts;
import mod.akrivus.kagic.entity.gem.GemPlacements;
import mod.akrivus.kagic.init.KAGIC;
import mod.akrivus.kagic.init.ModBlocks;
import mod.akrivus.kagic.tileentity.TileEntityGalaxyPadCore;
import mod.akrivus.kagic.tileentity.TileEntityWarpPadCore;
import mod.heimrarnadalr.kagic.util.Colors;
import mod.heimrarnadalr.kagic.world.structure.Schematic;
import mod.heimrarnadalr.kagic.world.structure.StructureData;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import scala.ref.Reference;

public class EntityLavenderJade extends EntityGem {
    public static final HashMap<IBlockState, Double> LAVENDERJADE_YIELDS = new HashMap<IBlockState, Double>();

    public static final double LAVENDERJADE_DEFECTIVITY_MULTIPLIER = 1;
    public static final double LAVENDERJADE_DEPTH_THRESHOLD = 0;


    private static final int NUM_HAIRSTYLES = 5;

    public static final int SKIN_COLOR_BEGIN = 15383018;
    public static final int SKIN_COLOR_END = 13284081;
    public static final int HAIR_COLOR_BEGIN = 16305658;
    public static final int HAIR_COLOR_END = 15379434;
    public float SoundVolume;
    public int jumpTicks;

    public EntityLavenderJade(World worldIn) {
        super(worldIn);
        this.setSize(0.6F, 1.9F);
        this.isImmuneToFire = true;
        this.nativeColor = 2;
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

        this.droppedGemItem = BriseItems.LAVENDER_JADE_GEM;
        this.droppedCrackedGemItem = BriseItems.CRACKED_LAVENDER_JADE_GEM;

    }


    public boolean attackEntityAsMob(Entity entityIn) {
        ((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 200));
        return super.attackEntityAsMob(entityIn);
    }

    public void onLivingUpdate() {
        if (this.world.getBlockState(this.getPosition().down()).getBlock() instanceof BlockDirt && !this.isPrimary()) {
            this.world.setBlockState(this.getPosition().down(), Blocks.GRASS.getDefaultState());
        }
		//if(this.world.getBlockState(this.getPosition().down()).getBlock() instanceof BlockDirt || this.world.getBlockState(this.getPosition().down()).getBlock() instanceof BlockGrass && this.isPrimary()){
        //    this.world.setBlockState(this.getPosition().down(), Blocks.MYCELIUM.getDefaultState());
		//}
        if (this.jumpTicks > 80 && !this.isDefective() && !(this.isDead || this.getHealth() <= 0.0F)) {
            this.JumpyBoi();
            this.jumpTicks = 0;
        }
        this.jumpTicks += 1;
        super.onLivingUpdate();
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        if (!this.world.isRemote) {
            if (hand == EnumHand.MAIN_HAND) {
                ItemStack stack = player.getHeldItemMainhand();
                if (this.isTamed()) {
                    if (this.isOwner(player)) {
                        if (this.isCoreItem(stack)) {
                            return super.processInteract(player, hand);
                        } else {
                            if (stack.getItem() == new ItemStack(Blocks.GRASS).getItem() && this.isPrimary()) {
                                this.dropItem(new ItemStack(Blocks.MYCELIUM).getItem(), 1);
                                stack.shrink(1);
                            }
                        }
                    }
                }
            }
        }
        return super.processInteract(player, hand);
    }

	private void JumpyBoi() {
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
									entity.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 400, 1));
								}
							}
							else {
								entity.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 400, 1));
							}
						}
					}
					if (this.isOwner(entity)) {
						entity.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 400, 1));
					}
				}
			}
		}

	}

	public int generateGemColor() {
		return 14065878;
	}

    @Override
    protected int generateSkinColor() {
        ArrayList<Integer> skinColors = new ArrayList<Integer>();
        skinColors.add(EntityLavenderJade.SKIN_COLOR_BEGIN);
        skinColors.add(EntityLavenderJade.SKIN_COLOR_END);
        return Colors.arbiLerp(skinColors);
    }

	@Override
	protected int generateHairStyle() {
		return this.rand.nextInt(EntityLavenderJade.NUM_HAIRSTYLES);
	}

    @Override
    protected int generateHairColor() {
        ArrayList<Integer> hairColors = new ArrayList<Integer>();
        hairColors.add(EntityLavenderJade.HAIR_COLOR_BEGIN);
        hairColors.add(EntityLavenderJade.HAIR_COLOR_END);
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

