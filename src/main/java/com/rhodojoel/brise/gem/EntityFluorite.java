package com.rhodojoel.brise.gem;

import java.util.*;


import com.rhodojoel.brise.gem.ai.EntityAIMoveGemToBlock;
import com.rhodojoel.brise.init.BriseItems;
import com.rhodojoel.brise.init.BriseSounds;
import mod.akrivus.kagic.entity.ai.*;
import mod.akrivus.kagic.entity.EntityGem;
import mod.akrivus.kagic.entity.gem.GemCuts;
import mod.akrivus.kagic.entity.gem.GemPlacements;
import mod.akrivus.kagic.init.ModBlocks;
import mod.akrivus.kagic.init.ModItems;
import mod.heimrarnadalr.kagic.util.Colors;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IInventoryChangedListener;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.InvWrapper;

import static net.minecraft.block.BlockStoneBrick.VARIANT;

public class EntityFluorite extends EntityGem implements IInventoryChangedListener {
	public static final HashMap<IBlockState, Double> FLUORITE_YIELDS = new HashMap<IBlockState, Double>();

	public static final double FLUORITE_DEFECTIVITY_MULTIPLIER = 1;
	public static final double FLUORITE_DEPTH_THRESHOLD = 0;


	private static final int NUM_HAIRSTYLES = 1;

	public static final int SKIN_COLOR_BEGIN = 7204817 ;
	public static final int SKIN_COLOR_END = 5948587;
    public static final int HAIR_COLOR_BEGIN = 11336703 ;
    public static final int HAIR_COLOR_END = 7198683;
	public float SoundVolume;
	public int visionTicks;

	public InventoryBasic gemStorage;
	public InvWrapper gemStorageHandler;

	public EntityFluorite(World worldIn) {
		super(worldIn);
		this.setSize(0.6F, 1.9F);
		this.seePastDoors();

		setCutPlacement(GemCuts.TRIANGULAR, GemPlacements.FOREHEAD);
		setCutPlacement(GemCuts.TRIANGULAR, GemPlacements.LEFT_EYE);
		setCutPlacement(GemCuts.TRIANGULAR, GemPlacements.RIGHT_EYE);
		setCutPlacement(GemCuts.TRIANGULAR, GemPlacements.BACK);
		setCutPlacement(GemCuts.TRIANGULAR, GemPlacements.CHEST);
		setCutPlacement(GemCuts.TRIANGULAR, GemPlacements.BELLY);
		setCutPlacement(GemCuts.TINY, GemPlacements.MOUTH);

		this.stayAI = new EntityAIStay(this);
		this.tasks.addTask(1, new EntityAICommandGems(this, 0.6D));
		this.tasks.addTask(3, new EntityAIFollowDiamond(this, 1.0D));
		this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0F));
		this.tasks.addTask(3, new EntityAIPickUpItems(this, 0.5D));
		this.tasks.addTask(9, new AICleanUp(this, 0.5D));

		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);

		//this.droppedGemItem = BriseItems.FLUORITE_GEM;
		//this.droppedCrackedGemItem = BriseItems.CRACKED_FLUORITE_GEM;

	}

	public IBlockState getCleanCounter(BlockPos pos){
		Block block = this.world.getBlockState(pos).getBlock();
		if(block instanceof BlockStoneBrick){
			return Blocks.STONEBRICK.getDefaultState();
		}
		else if(block == Blocks.MOSSY_COBBLESTONE){
			return Blocks.COBBLESTONE.getDefaultState();
		}
		else {
			return Blocks.AIR.getDefaultState();
		}
	}

	public boolean isToClean(BlockPos pos){
		Block block = this.world.getBlockState(pos).getBlock();
		return (block instanceof BlockStoneBrick && block.getMetaFromState(block.getDefaultState()) != BlockStoneBrick.EnumType.byMetadata(BlockStoneBrick.DEFAULT_META).getMetadata())
				|| block == Blocks.MOSSY_COBBLESTONE;
	}

	public boolean isToBreak(BlockPos pos){
		Block block = this.world.getBlockState(pos).getBlock();
		return (this.isPrimary() && this.world.getBlockState(pos).getMaterial() == ModBlocks.DRAINED) || block == Blocks.VINE
				|| block == Blocks.WEB;
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		if(true) {
			if (!this.world.isRemote) {
				if (hand == EnumHand.MAIN_HAND) {
					ItemStack stack = player.getHeldItemMainhand();
					if (this.isTamed()) {
						if (this.isOwner(player)) {
							if (this.isCoreItem(stack)) {
								return super.processInteract(player, hand);
							} else {
								this.openGUI(player);
							}
						}
					}
				}
			}
			return super.processInteract(player, hand);
		}
		else{
			return false;
		}
	}

	public void openGUI(EntityPlayer playerEntity) {
		if (!this.world.isRemote && this.isTamed()) {
			this.gemStorage.setCustomName(this.getName());
			playerEntity.displayGUIChest(this.gemStorage);
		}
	}

	protected void updateEquipmentIfNeeded(EntityItem itementity) {
		ItemStack itemstack = itementity.getItem();
		ItemStack itemstack1 = this.gemStorage.addItem(itemstack);
		if (itemstack1.isEmpty()) {
			itementity.setDead();
		}
		else {
			itemstack.setCount(itemstack1.getCount());
		}
	}

	public void writeEntityToNBT(NBTTagCompound compound) {
		NBTTagList nbttaglist = new NBTTagList();
		for (int i = 0; i < this.gemStorage.getSizeInventory(); ++i) {
			ItemStack itemstack = this.gemStorage.getStackInSlot(i);
			NBTTagCompound nbttagcompound = new NBTTagCompound();
			nbttagcompound.setByte("slot", (byte)i);
			itemstack.writeToNBT(nbttagcompound);
			nbttaglist.appendTag(nbttagcompound);
		}
		super.writeEntityToNBT(compound);
	}
	public void readEntityFromNBT(NBTTagCompound compound) {
		NBTTagList nbttaglist = compound.getTagList("items", 10);
		this.initGemStorage();
		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound.getByte("slot") & 255;
			if (j >= 0 && j < this.gemStorage.getSizeInventory()) {
				this.gemStorage.setInventorySlotContents(j, new ItemStack(nbttagcompound));
			}
		}
		super.readEntityFromNBT(compound);
	}

	public void onInventoryChanged(IInventory inventory) {
		ItemStack firstItem = this.gemStorage.getStackInSlot(0);
		this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, firstItem);
	}

	public InventoryBasic getInventory() {
		return this.gemStorage;
	}

	private void initGemStorage() {
		InventoryBasic gemstorage = this.gemStorage;
		this.gemStorage = new InventoryBasic("gemStorage", false, 45);
		if (gemstorage != null) {
			gemstorage.removeInventoryChangeListener(this);
			for (int i = 0; i < this.gemStorage.getSizeInventory(); ++i) {
				ItemStack itemstack = gemstorage.getStackInSlot(i);
				this.gemStorage.setInventorySlotContents(i, itemstack.copy());
			}
		}
		this.gemStorage.addInventoryChangeListener(this);
		this.gemStorageHandler = new InvWrapper(this.gemStorage);
		this.setCanPickUpLoot(this.isTamed());
	}

	@Override
	public int generateGemColor() {
		return 16777215;
	}

    @Override
    protected int generateSkinColor() {
        ArrayList<Integer> skinColors = new ArrayList<Integer>();
        skinColors.add(EntityFluorite.SKIN_COLOR_BEGIN);
        skinColors.add(EntityFluorite.SKIN_COLOR_END);
        return Colors.arbiLerp(skinColors);
    }

	@Override
	protected int generateHairStyle() {
		return this.rand.nextInt(EntityFluorite.NUM_HAIRSTYLES);
	}

    @Override
    protected int generateHairColor() {
        ArrayList<Integer> hairColors = new ArrayList<Integer>();
        hairColors.add(EntityFluorite.HAIR_COLOR_BEGIN);
        hairColors.add(EntityFluorite.HAIR_COLOR_END);
        return Colors.arbiLerp(hairColors);
    }


//	protected SoundEvent getHurtSound(DamageSource source) {
	//	if(this.getGemPlacement() == GemPlacements.MOUTH){
	//		return null;
	//	}
	//	else
	//	return BriseSounds.FLUORITE_HURT;
//	}

//	protected SoundEvent getObeySound() {
	//	if(this.getGemPlacement() == GemPlacements.MOUTH){
	//		return null;
	//	}
	//	else
	//	return BriseSounds.FLUORITE_OBEY;
//	}

//	protected SoundEvent getDeathSound(){
//		if(this.getGemPlacement() == GemPlacements.MOUTH){
//			return null;
	//	}
//		else
//			return BriseSounds.FLUORITE_DEATH;
//	}

	public static class AICleanUp extends EntityAIMoveGemToBlock {
		private final EntityFluorite gem;
		private final World world;
		private int delay = 0;

		public AICleanUp(EntityFluorite gem, double speed) {
			super(gem, speed, 8);
			this.gem = gem;
			this.world = gem.world;
		}
		@Override
		public boolean shouldExecute() {
			if (this.gem.isTamed()) {
				if (true) {
					if (this.delay > 5 + this.gem.getRNG().nextInt(5)) {
						this.runDelay = 0;
						this.delay = 0;
						return super.shouldExecute();
					}
					else {
						++this.delay;
					}
				}
			}
			return false;
		}
		@Override
		public boolean shouldContinueExecuting() {
			return super.shouldContinueExecuting() && !this.gem.getNavigator().noPath();
		}
		@Override
		public void startExecuting() {
			super.startExecuting();
		}
		@Override
		public void resetTask() {
			super.resetTask();
		}
		@Override
		public void updateTask() {
			super.updateTask();
			this.gem.getLookHelper().setLookPosition(this.destinationBlock.getX() + 0.5D, this.destinationBlock.getY() + 1, this.destinationBlock.getZ() + 0.5D, 10.0F, this.gem.getVerticalFaceSpeed());
			if (this.gem.getDistanceSq(this.destinationBlock) < 14) {
				if(this.gem.isToBreak(this.destinationBlock)) {
					this.world.destroyBlock(this.destinationBlock, false);
				}
				else{
					if(this.gem.isToClean(this.destinationBlock)) {
						IBlockState block = this.gem.getCleanCounter(this.destinationBlock);
						boolean cleaned = this.world.setBlockState(this.destinationBlock, block);
						if (cleaned) {
							this.world.playSound(null, this.gem.getPosition(), SoundEvents.BLOCK_SLIME_BREAK, SoundCategory.NEUTRAL, 1.0F, this.gem.getSoundPitch());
						}
					}
				}
				this.searchForDestination();
			}
		}

		@Override
		protected boolean shouldMoveTo(World world, BlockPos pos) {
			if (!world.isAirBlock(pos)) {
				IBlockState state = world.getBlockState(pos);
				Block block = state.getBlock();
				if (this.gem.isToBreak(pos) || this.gem.isToClean(pos)) {
					return this.hasAir(pos);
				}
			}
			return false;
		}
		protected boolean hasAir(BlockPos pos) {
			for (int x = -1; x < 1; ++x) {
				for (int y = -1; y < 1; ++y) {
					for (int z = -1; z < 1; ++z) {
						if (this.world.isAirBlock(pos.add(x, y, z))) {
							return true;
						}
					}
				}
			}
			return false;
		}
	}
}

