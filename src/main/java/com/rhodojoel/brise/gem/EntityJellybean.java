package com.rhodojoel.brise.gem;

import java.awt.*;
import java.util.*;

import com.google.common.base.Predicate;

import com.rhodojoel.brise.gem.ai.EntityAIMoveGemToBlock;
import com.rhodojoel.brise.init.BriseItems;
import com.rhodojoel.brise.init.BriseSounds;
import mod.akrivus.kagic.entity.EntityGem;
import mod.akrivus.kagic.entity.ai.*;
import mod.akrivus.kagic.entity.gem.GemCuts;
import mod.akrivus.kagic.entity.gem.GemPlacements;
import mod.akrivus.kagic.init.ModBlocks;
import mod.akrivus.kagic.init.ModSounds;
import mod.heimrarnadalr.kagic.util.Colors;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import java.util.ArrayList;
import java.util.Random;

public class EntityJellybean extends EntityGem implements IInventoryChangedListener {

    public int hairColor;
    boolean wool = false;
    boolean leather = false;
    boolean string = false;

    Random rand = new Random();

    public InventoryBasic gemStorage;
    public InvWrapper gemStorageHandler;

    private static final int NUM_HAIRSTYLES = 9;
    public boolean decidedName;
    public String naem;
    public int chestTicks = 80;
    public int checkTicks = 40;
    public int makeTicks = 100;
    public boolean finnaGetThatChest = false;
    public boolean finnaMugYou = true;

    public EntityJellybean(World worldIn) {
        super(worldIn);
        this.nativeColor = rand.nextInt(16);
        this.setInsigniaColor(rand.nextInt(16));
        this.setSize(0.3F, 0.5F);
        this.initGemStorage();
        Random rand = new Random();
        if (!this.decidedName) {
            if (rand.nextInt(21) == 1) {
                this.setCustomNameTag("Jellybean");
                this.naem = "Jellybean";
                if (rand.nextBoolean()) {
                    this.setCustomNameTag("Jughead");
                    this.naem = "Jughead";
                }
            } else if (rand.nextInt(21) == 2) {
                this.setCustomNameTag("Plebble");
                this.naem = "Plebble";
            } else {
                this.setCustomNameTag("Pebble");
                this.naem = "Pebble";
            }
        }
        this.decidedName = true;
        this.setCustomNameTag(this.naem);
        //Define valid gem cuts and placements
        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.LEFT_EYE);
        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.RIGHT_EYE);
        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.NOSE);
        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.MOUTH);
        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.CHEST);
        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.BACK_OF_HEAD);
        // Apply entity AI.
        this.stayAI = new EntityAIStay(this);
        this.tasks.addTask(1, new EntityAIFollowDiamond(this, 1.0D));
        this.tasks.addTask(2, new EntityAISitStill(this, 1.0D));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0F));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityMob.class, 16.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));
        this.tasks.addTask(9, new AIStealChest(this, 0.5D));

        // Apply entity attributes.
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);

        this.droppedGemItem = BriseItems.JELLYBEAN_GEM;
        this.droppedCrackedGemItem = BriseItems.CRACKED_JELLYBEAN_GEM;

        // Register entity data.
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

    public boolean attackEntityAsMob(Entity entity){
        if(this.finnaMugYou) {
            if (entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) entity;
                if(player.getHeldItemMainhand() instanceof ItemStack && player.getHeldItemMainhand().getItem() != Items.AIR) {
                    ItemStack stack = player.getHeldItemMainhand();
                    ItemStack heldItem = stack.copy();
                    if(this.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND) instanceof ItemStack) {
                        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, heldItem);
                    }
                    else{
                        this.gemStorage.addItem(heldItem);
                    }
                    if (!player.capabilities.isCreativeMode) {
                        stack.shrink(1);
                    }
                    player.sendMessage(new TextComponentString("Your Pebble has stolen your " + this.getHeldItemMainhand().getDisplayName()));
                    this.tasks.addTask(8, new EntityAIAvoidEntity<EntityPlayer>(this, EntityPlayer.class, new Predicate<EntityPlayer>() {
                        public boolean apply(EntityPlayer input) {
                                return true;
                            }
                    }, 3.0F, 0.7D, 0.9D));
                    this.finnaMugYou = false;
                }
            }
        }
        return super.attackEntityAsMob(entity);
    }

    public void onLivingUpdate() {
        if (this.chestTicks > 80 && !this.isDefective() && !(this.isDead || this.getHealth() <= 0.0F)) {
            this.finnaGetThatChest = true;
            this.chestTicks = 0;
        }
        this.chestTicks += 1;
        if (this.checkTicks > 40 && !this.isDefective() && !(this.isDead || this.getHealth() <= 0.0F)) {
            if(this.world.getBlockState(this.getPosition().down()).getBlock() == Blocks.CHEST) {
                this.CheckForChest(this.getPosition().down());
            }
            this.checkTicks = 0;
        }
        this.checkTicks += 1;
        if (this.makeTicks > 100 && !this.isDefective() && !(this.isDead || this.getHealth() <= 0.0F)) {
            this.AttemptCraft();
            this.makeTicks = 0;
        }
        this.makeTicks += 1;
        super.onLivingUpdate();
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        if(!player.isSneaking()) {
            if (!this.world.isRemote) {
                if (hand == EnumHand.MAIN_HAND) {
                    ItemStack stack = player.getHeldItemMainhand();
                    if (this.isTamed()) {
                        if (this.isOwner(player)) {
                            if (this.isCoreItem(stack)) {
                                return super.processInteract(player, hand);
                            } else {
                                if (stack.getItem() == Items.PAPER) {
                                    this.dropItem(BriseItems.PEBBLE_PICTURE, 1);
                                    stack.shrink(1);
                                    this.playSound(BriseSounds.PEBBLE_PAPER, this.getSoundVolume(), this.pitch);
                                }
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

    public void AttemptCraft(){
        for(int i = 0; i < this.gemStorage.getSizeInventory(); i++){
            if(this.gemStorage.getStackInSlot(i).getItem() == Items.LEATHER){
                this.gemStorage.decrStackSize(i, 1);
                this.leather = true;
            }
            if(this.gemStorage.getStackInSlot(i).getItem() == Items.STRING){
                this.gemStorage.decrStackSize(i, 1);
                this.string = true;
            }
            if(this.gemStorage.getStackInSlot(i).getItem() == new ItemStack(Blocks.WOOL).getItem()){
                this.gemStorage.decrStackSize(i, 1);
                this.wool = true;
            }
        }
        if(this.wool && this.leather && this.string){
            this.entityDropItem(GenerateRandomArmor(), 1);
            this.leather = false;
            this.string = false;
            this.wool = false;
        }
    }

    public int GenerateColor(){
        Random rand = new Random();
        return ((rand.nextInt(256) << 16) + (rand.nextInt(256) << 8) + rand.nextInt(256));
    }

    public ItemStack GenerateRandomArmor() {
        Random rand = new Random();
        if((rand.nextInt(4) + 1) == 1){
            ItemArmor stack = (Items.LEATHER_BOOTS);
            stack.setColor(new ItemStack(stack), GenerateColor());
            return new ItemStack(stack, 1, 14);
        }
        else if((rand.nextInt(4) + 1) == 2){
            ItemArmor stack = (Items.LEATHER_LEGGINGS);
            stack.setColor(new ItemStack(stack), GenerateColor());
            return new ItemStack(stack, 1, 14);
        }
        else if((rand.nextInt(4) + 1) == 3){
            ItemArmor stack = (Items.LEATHER_CHESTPLATE);
            stack.setColor(new ItemStack(stack), GenerateColor());
            return new ItemStack(stack, 1, 14);
        }
        else if((rand.nextInt(4) + 1) == 4){
            ItemArmor stack = (Items.LEATHER_HELMET);
            stack.setColor(new ItemStack(stack), GenerateColor());
            return new ItemStack(stack, 1, 14);
        }
        else{
            ItemArmor stack = (Items.LEATHER_HELMET);
            return new ItemStack(stack, 1, 14);
        }
    }

    public void CheckForChest(BlockPos pos){
        int amount = 0;
        BlockChest chest = (BlockChest)this.world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ())).getBlock();
        ILockableContainer inv = chest.getLockableContainer(this.world, new BlockPos(pos.getX(), pos.getY(), pos.getZ()));
        for(int i = 0; i < inv.getSizeInventory(); i++){
            if(inv.getStackInSlot(i) instanceof ItemStack){
                amount++;
            }
        }
        if(amount > 0) {
            StealFromChests(pos.getX(), pos.getY(), pos.getZ(), inv);
        }
    }

    public boolean isChestContainingContent(BlockPos pos){
        int amount = 0;
        BlockChest chest = (BlockChest)this.world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ())).getBlock();
        ILockableContainer inv = chest.getLockableContainer(this.world, new BlockPos(pos.getX(), pos.getY(), pos.getZ()));
        for(int i = 0; i < inv.getSizeInventory(); i++){
            if(inv.getStackInSlot(i).getItem() == Items.LEATHER || inv.getStackInSlot(i).getItem() == Items.STRING || inv.getStackInSlot(i).getItem() == new ItemStack(Blocks.WOOL).getItem()){
                amount++;
            }
        }
        if(amount > 0) {
            return true;
        }
        else if(amount == 0){
            return false;
        }
        else{
            return false;
        }
    }

    public void StealFromChests(double x, double y, double z, ILockableContainer inv){
        for(int i = 0; i < inv.getSizeInventory(); i++){
            ItemStack itemstack = inv.getStackInSlot(i);
            if(itemstack.getItem() == Items.LEATHER || itemstack.getItem() == new ItemStack(Blocks.WOOL).getItem() || itemstack.getItem() == Items.STRING){
                this.gemStorage.addItem(itemstack);
                inv.removeStackFromSlot(i);
            }
        }
        this.finnaGetThatChest = false;
        this.setAttackTarget(null);
    }

    public void onDeath(DamageSource cause){
        if(!this.world.isRemote){
            if(this.getHeldItemMainhand() != ItemStack.EMPTY || this.getHeldItemMainhand() != new ItemStack(Blocks.AIR)){
                this.entityDropItem(this.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND), 1);
                this.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).shrink(this.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).getCount());
            }
            for(int i = 0; i < this.gemStorage.getSizeInventory(); i++){
                this.entityDropItem(this.gemStorage.getStackInSlot(i), 1);
                this.gemStorage.getStackInSlot(i).shrink(this.gemStorage.getStackInSlot(i).getCount());
            }
        }
        super.onDeath(cause);
    }

    protected int generateGemColor() {
        return 16777215;
    }

    @Override
    public int generateSkinColor() {
        ArrayList<Integer> skinColors = new ArrayList<Integer>();

        skinColors.add(14920383);
        skinColors.add(12958936);
        skinColors.add(12756422);
        skinColors.add(13477575);
        skinColors.add(13350831);
        skinColors.add(11512265);
        skinColors.add(11908298);
        skinColors.add(12830675);

        this.hairColor = Colors.arbiLerp(skinColors);
        return this.hairColor;
    }

    @Override
    protected int generateHairStyle() {
        return this.rand.nextInt(EntityJellybean.NUM_HAIRSTYLES);
    }

    @Override
    protected int generateHairColor() {
        return this.hairColor;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        if(this.getGemPlacement() == GemPlacements.MOUTH){
            return null;
        }
        else
        return BriseSounds.PEBBLE_HURT;
    }

    @Override
    protected SoundEvent getObeySound() {
        if(this.getGemPlacement() == GemPlacements.MOUTH){
            return null;
        }
        else
        return BriseSounds.PEBBLE_OBEY;
    }

    @Override
    protected SoundEvent getDeathSound() {
        if(this.getGemPlacement() == GemPlacements.MOUTH){
            return null;
        }
        else
        return BriseSounds.PEBBLE_DEATH;
    }

    public static class AIStealChest extends EntityAIMoveGemToBlock {
        private final EntityJellybean gem;
        private final World world;
        private int delay = 0;

        public AIStealChest(EntityJellybean gem, double speed) {
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
            if (this.gem.getDistanceSq(this.destinationBlock) < 4) {
                if(this.gem.world.getBlockState(this.destinationBlock) == Blocks.CHEST.getDefaultState()) {
                    if(this.gem.isChestContainingContent(this.destinationBlock)) {
                        this.gem.CheckForChest(this.destinationBlock);
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
                if(this.gem.world.getBlockState(pos) == Blocks.CHEST.getDefaultState()){
                    if(this.gem.isChestContainingContent(pos)) {
                        return this.hasAir(pos);
                    }
                    else{
                        return false;
                    }
                }
                else{
                    return false;
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
