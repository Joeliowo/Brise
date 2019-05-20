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
import mod.heimrarnadalr.kagic.world.structure.Schematic;
import mod.heimrarnadalr.kagic.world.structure.StructureData;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.InvWrapper;

public class EntitySunsetSodalite extends EntityGem {
	public static final HashMap<IBlockState, Double> SUNSETSODALITE_YIELDS = new HashMap<IBlockState, Double>();
	public static final double SUNSETSODALITE_DEFECTIVITY_MULTIPLIER = 1;
	public static final double SUNSETSODALITE_DEPTH_THRESHOLD = 0;
	public static final HashMap<Integer, ResourceLocation> SUNSTONE_HAIR_STYLES = new HashMap<Integer, ResourceLocation>();

	private static final int SKIN_COLOR_BEGIN = 4021965;
	private static final int SKIN_COLOR_END = 2505599;

    private static final int HAIR_COLOR_BEGIN = 1385277;
    private static final int HAIR_COLOR_END = 1187381;

    private static final int NUM_HAIRSTYLES = 1;
	
	public EntitySunsetSodalite (World worldIn) {
		super(worldIn);
		this.nativeColor = 11;
		this.setSize(0.7F, 1.8F);
		this.isSoldier = true;
		
		//Define valid gem cuts and placements
		this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.FOREHEAD);
		this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.BACK);
		this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.CHEST);
		this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.BELLY);
		this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.LEFT_EYE);
		this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.RIGHT_EYE);
		this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.NOSE);
		this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.MOUTH);

        // Other entity AIs.
        this.tasks.addTask(4, new EntityAIFollowDiamond(this, 1.0D));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(6, new EntityAISitStill(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0F));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityMob.class, 16.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));

        // Apply targetting.
        this.targetTasks.addTask(1, new EntityAIDiamondHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIDiamondHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, false, new Class[0]));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget<EntityLiving>(this, EntityLiving.class, 10, true, false, new Predicate<EntityLiving>() {
            public boolean apply(EntityLiving input) {
                return input != null && IMob.VISIBLE_MOB_SELECTOR.apply(input);
            }
        }));

        // Apply targeting.
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget<EntityLiving>(this, EntityLiving.class, 10, true, false, new Predicate<EntityLiving>() {
            public boolean apply(EntityLiving input) {
                return input != null && IMob.VISIBLE_MOB_SELECTOR.apply(input);
            }
        }));
        
        // Apply entity attributes.
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(9.0D);
        this.droppedGemItem = BriseItems.SUNSETSODALITE_GEM;
		this.droppedCrackedGemItem = BriseItems.CRACKED_SUNSETSODALITE_GEM;
	}

	@Override
	public void whenDefective() {
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.5D);
        this.setSize(0.6F, 1.8F);
	}

	@Override
	public void whenPrimary() {
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(90.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(11.5D);
		this.setSize(1.1F, 2.2F);
	}

	/*********************************************************
	 * Methods related to rendering.                         *
	 *********************************************************/
	@Override
	protected int generateSkinColor() {
		ArrayList<Integer> skinColors = new ArrayList<Integer>();
		skinColors.add(EntitySunsetSodalite.SKIN_COLOR_BEGIN);
		skinColors.add(EntitySunsetSodalite.SKIN_COLOR_END);
		return Colors.arbiLerp(skinColors);
	}

    @Override
    protected int generateHairStyle() {
        return this.rand.nextInt(EntitySunsetSodalite.NUM_HAIRSTYLES);
    }

    @Override
    protected int generateHairColor() {
        ArrayList<Integer> hairColors = new ArrayList<Integer>();
        hairColors.add(EntitySunsetSodalite.HAIR_COLOR_BEGIN);
        hairColors.add(EntitySunsetSodalite.HAIR_COLOR_END);
        return Colors.arbiLerp(hairColors);
    }
}