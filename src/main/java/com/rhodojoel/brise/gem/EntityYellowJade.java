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
import net.minecraft.block.BlockFlower;
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
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IInventoryChangedListener;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemSaddle;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.InvWrapper;

public class EntityYellowJade extends EntityGem implements  IInventoryChangedListener, INpc {
	public static final HashMap<IBlockState, Double> YELLOWJADE_YIELDS = new HashMap<IBlockState, Double>();

	public static final double YELLOWJADE_DEFECTIVITY_MULTIPLIER = 1;
	public static final double YELLOWJADE_DEPTH_THRESHOLD = 0;


    public InventoryBasic gemStorage;
    public InvWrapper gemStorageHandler;

	private static final int NUM_HAIRSTYLES = 5;

	public static final int SKIN_COLOR_BEGIN = 16580554;
	public static final int SKIN_COLOR_END = 16580499;
    public static final int HAIR_COLOR_BEGIN = 16777069;
    public static final int HAIR_COLOR_END = 15724391;
	public float SoundVolume;
	public int hasteTicks;

	public EntityYellowJade(World worldIn) {
		super(worldIn);
		this.setSize(0.6F, 1.9F);
		this.isImmuneToFire = true;
		this.nativeColor = 4;
        this.initGemStorage();
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

		this.droppedGemItem = BriseItems.YELLOW_JADE_GEM;
		this.droppedCrackedGemItem = BriseItems.CRACKED_YELLOW_JADE_GEM;

	}

    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
        return super.onInitialSpawn(difficulty, livingdata);
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
        compound.setTag("items", nbttaglist);
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
    public void openGUI(EntityPlayer playerEntity) {
        if (!this.world.isRemote && this.isTamed()) {
            this.gemStorage.setCustomName(this.getName());
            playerEntity.displayGUIChest(this.gemStorage);
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
                            if(stack == new ItemStack(Blocks.GOLD_ORE)){
                                if(this.isDefective()){
                                    stack.shrink(1);
                                    this.dropItem(Items.GOLD_NUGGET, 1);
                                }
                                else if(this.isPrimary()){
                                    stack.shrink(1);
                                    this.dropItem(new ItemStack(Blocks.GOLD_BLOCK).getItem(), 1);
                                }
                                else{
                                    stack.shrink(1);
                                    this.dropItem(Items.GOLD_INGOT, 1);
                                }
                            }
                        }
                    }
                }
            }
        }
        return super.processInteract(player, hand);
    }

	public int generateGemColor() {
		return 14866833;
	}

	public boolean attackEntityAsMob(Entity entityIn) {
        ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 200));
		return super.attackEntityAsMob(entityIn);
	}

    public void onLivingUpdate() {
        if (!this.onGround && this.motionY < 0.0D) {
            this.motionY *= 0.75D;
        }
        /*if(this.world.getBlockState(this.getPosition().down()) == Blocks.GOLD_ORE.getDefaultState()) {
            if(!isPrimary() && !isDefective()) {
                this.world.destroyBlock(this.getPosition().down(), false);
                this.gemStorage.addItem(new ItemStack(Items.GOLD_INGOT));
            }
            if(isPrimary()){
                this.world.destroyBlock(this.getPosition().down(), false);
                this.gemStorage.addItem(new ItemStack(Blocks.GOLD_BLOCK));
            }
            if(isDefective()){
                this.world.destroyBlock(this.getPosition().down(), false);
                this.gemStorage.addItem(new ItemStack(Items.GOLD_NUGGET));
            }
        }*/
        if (this.hasteTicks > 80 && !this.isDefective() && !(this.isDead || this.getHealth() <= 0.0F)) {
            this.HasteTheFuckUp();
            this.hasteTicks = 0;
        }
        this.hasteTicks += 1;
        super.onLivingUpdate();
    }

    public void HasteTheFuckUp(){if (!this.world.isRemote) {
        AxisAlignedBB axisalignedbb = (new AxisAlignedBB(this.posX, this.posY, this.posZ, (this.posX + 1), (this.posY + 1), (this.posZ + 1))).grow(16.0, (double) this.world.getHeight(), 16.0);
        List<EntityLivingBase> list = this.world.<EntityLivingBase>getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);
        for (EntityLivingBase entity : list) {
            if (!entity.isDead || entity.getHealth() > 0.0F) {
                if (entity instanceof EntityGem) {
                    EntityGem gem = (EntityGem) entity;
                    if (this.getServitude() == gem.getServitude()) {
                        if (this.getServitude() == EntityGem.SERVE_HUMAN) {
                            if (this.isOwnerId(gem.getOwnerId())) {
                                entity.addPotionEffect(new PotionEffect(MobEffects.HASTE, 400, 1));
                            }
                        }
                        else {
                            entity.addPotionEffect(new PotionEffect(MobEffects.HASTE, 400, 1));
                        }
                    }
                }
                if (this.isOwner(entity)) {
                    entity.addPotionEffect(new PotionEffect(MobEffects.HASTE, 400, 1));
                }
            }
        }
    }
    }

    @Override
    protected int generateSkinColor() {
        ArrayList<Integer> skinColors = new ArrayList<Integer>();
        skinColors.add(EntityYellowJade.SKIN_COLOR_BEGIN);
        skinColors.add(EntityYellowJade.SKIN_COLOR_END);
        return Colors.arbiLerp(skinColors);
    }

	@Override
	protected int generateHairStyle() {
		return this.rand.nextInt(EntityYellowJade.NUM_HAIRSTYLES);
	}

    @Override
    protected int generateHairColor() {
        ArrayList<Integer> hairColors = new ArrayList<Integer>();
        hairColors.add(EntityYellowJade.HAIR_COLOR_BEGIN);
        hairColors.add(EntityYellowJade.HAIR_COLOR_END);
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

