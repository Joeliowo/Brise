package com.rhodojoel.brise.blocks;

import com.rhodojoel.brise.init.BriseBlocks;
import com.rhodojoel.brise.init.BriseDimensions;
import com.rhodojoel.brise.init.BriseItems;
import com.rhodojoel.brise.items.ItemComby;
import com.rhodojoel.brise.misc.SchematicLoader;
import com.rhodojoel.brise.misc.TeleporterRooms;
import mod.akrivus.kagic.init.ModCreativeTabs;
import mod.akrivus.kagic.init.ModItems;
import mod.akrivus.kagic.items.ItemGem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockQuartz;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class BlockGemDoorEnter extends Block {
    protected static final AxisAlignedBB BLOCK_COLLISION_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D);

    public BlockGemDoorEnter(String naem) {
        super(Material.ROCK);
        this.setUnlocalizedName(naem);
        this.setResistance(4);
        this.setHardness(1);
        this.setCreativeTab(ModCreativeTabs.CREATIVE_TAB_OTHER);
    }

    public boolean VerifyValid(BlockPos pos, World world){
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (j == 0 && i == 0) {
                    continue;
                }
                IBlockState state = world.getBlockState(pos.add(i, 0, j));
                if (state.getBlock() != Blocks.QUARTZ_BLOCK || state.getValue(BlockQuartz.VARIANT) != BlockQuartz.EnumType.LINES_Y) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            ItemStack gemm = player.getHeldItemMainhand();
            Item gem = gemm.getItem();
            SchematicLoader.findExit(this.Room(gem));
        }
        return true;
    }

    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entity) {
        if(!worldIn.isRemote) {
            if (this.VerifyValid(pos, worldIn)) {
                if (entity.getRidingEntity() == null && !entity.isBeingRidden() && entity instanceof EntityPlayerMP) {
                    EntityPlayerMP player = (EntityPlayerMP) entity;
                    if (this.Room(player.getHeldItemMainhand().getItem()) != "nada" && player.isSneaking()) {
                        ItemStack gemm = player.getHeldItemMainhand();
                        Item gem = gemm.getItem();
                        if (player.isSneaking()) {
                            BlockPos owo = new BlockPos(-this.Offset(this.Room(gem)).getX(), -this.Offset(this.Room(gem)).getY(), -this.Offset(this.Room(gem)).getZ());
                            BlockPos i = this.WorldOffset(this.Room(gem)).add(owo);
                            SchematicLoader.transferToDimension(player, i.up());
                            EntityPlayerMP pl = (EntityPlayerMP) player.mcServer.getWorld(BriseDimensions.DIM_ROOMS_ID).playerEntities
                                    .get(player.mcServer.getWorld(BriseDimensions.DIM_ROOMS_ID).playerEntities.indexOf(player));
                            SchematicLoader.teleportPlayer(pl, i.up());
                            if (player.mcServer.getWorld(BriseDimensions.DIM_ROOMS_ID).getBlockState(i).getBlock() != Blocks.AIR) {
                                BlockGemDoorExit exit = (BlockGemDoorExit) player.mcServer.getWorld(BriseDimensions.DIM_ROOMS_ID).getBlockState(i).getBlock();
                                exit.doorPos = pos.up();
                                System.out.println("Teleporting to Room");
                                SchematicLoader.teleportPlayer(pl, i.up());
                            } else {
                                System.out.println("Generating Room");
                                player.sendMessage(new TextComponentString(
                                        "Crossdimensional transference is pretty complicated! This may take a few minutes."));
                                SchematicLoader.generateSchematic(this.Room(gem),
                                        i, player, pos.up(),
                                        player.mcServer.getWorld(BriseDimensions.DIM_ROOMS_ID), BriseBlocks.EXIT_BLOCK);
                                player.sendMessage(new TextComponentString(
                                        "To avoid dimensional tearing, please do not leave the Room until over a minute has passed. " +
                                                "(This only applies to your entering of a room for the first time. " +
                                                "Also, please leave the room before logging out!"));
                            }
                        }
                    }
                }
            }
        }
    }

    public String Room(Item gem){
        if(true){
            if((gem == ModItems.AMETHYST_GEM) || (gem == ModItems.JASPER_GEM)
                    || (gem == ModItems.BIGGS_JASPER_GEM) || (gem == ModItems.BRUNEAU_JASPER_GEM)
                    || (gem == ModItems.FLAME_JASPER_GEM) || (gem == ModItems.GREEN_JASPER_GEM)
                    || (gem == ModItems.CANDY_CANE_JASPER_GEM) || (gem == ModItems.PURPLE_JASPER_GEM)
                    || (gem == ModItems.PICTURE_JASPER_GEM) || (gem == ModItems.NOREENA_JASPER_GEM)
                    || (gem == ModItems.OCEAN_JASPER_GEM) || (gem == ModItems.CARNELIAN_GEM)
                    || (gem == ModItems.CITRINE_GEM) || (gem == ModItems.AMETRINE_GEM)
                    || (gem == ModItems.AGATE_GEM) || (gem == ModItems.BLACK_AGATE_GEM)
                    || (gem == ModItems.BLUE_AGATE_GEM) || (gem == ModItems.BROWN_AGATE_GEM)
                    || (gem == ModItems.CYAN_AGATE_GEM) || (gem == ModItems.GRAY_AGATE_GEM)
                    || (gem == ModItems.GREEN_AGATE_GEM) || (gem == ModItems.LIME_AGATE_GEM)
                    || (gem == ModItems.MAGENTA_AGATE_GEM) || (gem == ModItems.ORANGE_AGATE_GEM)
                    || (gem == ModItems.PINK_AGATE_GEM) || (gem == ModItems.PURPLE_AGATE_GEM)
                    || (gem == ModItems.RED_AGATE_GEM) || (gem == ModItems.WHITE_AGATE_GEM)
                    || (gem == ModItems.YELLOW_AGATE_GEM) || (gem == ModItems.HOLLY_BLUE_AGATE_GEM)
                    || (gem == ModItems.LIGHT_BLUE_AGATE_GEM) || (gem == ModItems.LIGHT_GRAY_AGATE_GEM)) {
                return "quartz";
            }
            else if((gem == ModItems.PEARL_GEM) || (gem == ModItems.BLACK_PEARL_GEM)
                    || (gem == ModItems.BLUE_PEARL_GEM) || (gem == ModItems.BROWN_PEARL_GEM)
                    || (gem == ModItems.CYAN_PEARL_GEM) || (gem == ModItems.GRAY_PEARL_GEM)
                    || (gem == ModItems.GREEN_PEARL_GEM) || (gem == ModItems.PINK_PEARL_GEM)
                    || (gem == ModItems.LIME_PEARL_GEM) || (gem == ModItems.MAGENTA_PEARL_GEM)
                    || (gem == ModItems.WHITE_PEARL_GEM) || (gem == ModItems.YELLOW_PEARL_GEM)
                    || (gem == ModItems.PURPLE_PEARL_GEM) || (gem == ModItems.ORANGE_PEARL_GEM)
                    || (gem == ModItems.RED_PEARL_GEM) || (gem == ModItems.LIGHT_BLUE_PEARL_GEM)
                    || (gem == ModItems.LIGHT_GRAY_PEARL_GEM)) {
                return "pearl";
            }
            else if((gem == ModItems.ZIRCON_GEM) || (gem == ModItems.BLACK_ZIRCON_GEM)
                    || (gem == ModItems.BLUE_ZIRCON_GEM) || (gem == ModItems.BROWN_ZIRCON_GEM)
                    || (gem == ModItems.CYAN_ZIRCON_GEM) || (gem == ModItems.GRAY_ZIRCON_GEM)
                    || (gem == ModItems.GREEN_ZIRCON_GEM) || (gem == ModItems.PINK_ZIRCON_GEM)
                    || (gem == ModItems.LIME_ZIRCON_GEM) || (gem == ModItems.MAGENTA_ZIRCON_GEM)
                    || (gem == ModItems.WHITE_ZIRCON_GEM) || (gem == ModItems.YELLOW_ZIRCON_GEM)
                    || (gem == ModItems.PURPLE_ZIRCON_GEM) || (gem == ModItems.ORANGE_ZIRCON_GEM)
                    || (gem == ModItems.RED_ZIRCON_GEM) || (gem == ModItems.LIGHT_BLUE_ZIRCON_GEM)
                    || (gem == ModItems.LIGHT_GRAY_ZIRCON_GEM)) {
                return "zircon";
            }
            else if((gem == ModItems.SAPPHIRE_GEM) || (gem == ModItems.BLACK_SAPPHIRE_GEM)
                    || (gem == ModItems.BLUE_SAPPHIRE_GEM) || (gem == ModItems.GREEN_SAPPHIRE_GEM)
                    || (gem == ModItems.ORANGE_SAPPHIRE_GEM) || (gem == ModItems.PINK_SAPPHIRE_GEM)
                    || (gem == ModItems.PURPLE_SAPPHIRE_GEM) || (gem == ModItems.WHITE_SAPPHIRE_GEM)
                    || (gem == ModItems.YELLOW_SAPPHIRE_GEM) || (gem == ModItems.PADPARADSCHA_GEM)) {
                return "sapphire";
            }
            else if(gem == ModItems.RUTILE_GEM || gem == ModItems.TWIN_RUTILE_GEM){
                return "rutile";
            }
            else if(gem == ModItems.TOPAZ_GEM || gem == ModItems.BLUE_TOPAZ_GEM){
                return "topaz";
            }
            else if(gem == ModItems.LAPIS_LAZULI_GEM){
                return "lapis";
            }
            else if(gem == ModItems.RUBY_GEM){
                return "ruby";
            }
            else if(gem == ModItems.AQUAMARINE_GEM || gem == BriseItems.MORGANITE_GEM){
                return "aquamarine";
            }
            else if(gem == ModItems.BISMUTH_GEM){
                return "bismuth";
            }
            else if(gem == ModItems.HESSONITE_GEM){
                return "hessonite";
            }
            else if(gem == ModItems.PERIDOT_GEM){
                return "peridot";
            }
            else if(gem == ModItems.ROSE_QUARTZ_GEM){
                return "rose_quartz";
            }
            else if(gem == BriseItems.PEZZ_GEM){
                return "pezz";
            }
            else if(gem == BriseItems.JELLYBEAN_GEM){
                return "pebble";
            }
            else if(gem instanceof ItemComby){
                return "comby";
            }
            else if(gem == BriseItems.BLACK_JADE_GEM || gem == BriseItems.LAVENDER_JADE_GEM || gem == BriseItems.RED_JADE_GEM
                    || gem == BriseItems.WHITE_JADE_GEM || gem == BriseItems.YELLOW_JADE_GEM){
                return "jade";
            }
            else{
                if(gem instanceof ItemGem){
                    return "misc";
                }
                else{
                    return "nada";
                }
            }
        }
        return "quartz";
    }

    public BlockPos Offset(String room){
        if(room == "quartz"){
            return new BlockPos(3, 1, 35);
        }
        if(room == "lapis"){
            return new BlockPos(41, 4, 28);
        }
        if(room == "pearl"){
            return new BlockPos(18, 6, 3);
        }
        if(room == "aquamarine" || room == "morganite"){
            return new BlockPos(2, 1, 10);
        }
        if(room == "bismuth"){
            return new BlockPos(14, 8, 38);
        }
        if(room == "comby"){
            return new BlockPos(6, 1, 12);
        }
        if(room == "hessonite"){
            return new BlockPos(3, 3, 42);
        }
        if(room == "jade"){
            return new BlockPos(34, 3, 34);
        }
        if(room == "misc"){
            return new BlockPos(15, 4, 31);
        }
        if(room == "pebble"){
            return new BlockPos(3, 2, 13);
        }
        if(room == "peridot"){
            return new BlockPos(14, 2, 35);
        }
        if(room == "pezz"){
            return new BlockPos(38, 2, 16);
        }
        if(room == "rose_quartz"){
            return new BlockPos(15, 5, 15);
        }
        if(room == "ruby"){
            return new BlockPos(17, 4, 3);
        }
        if(room == "rutile"){
            return new BlockPos(24, 9, 3);
        }
        if(room == "sapphire"){
            return new BlockPos(26, 3, 7);
        }
        if(room == "topaz"){
            return new BlockPos(34, 2, 27);
        }
        if(room == "zircon"){
            return new BlockPos(43, 2, 20);
        }
        return new BlockPos(0, 0, 0);
    }

    public BlockPos WorldOffset(String room){
        if(room == "quartz"){
            return new BlockPos(100, 100, 100);
        }
        if(room == "lapis"){
            return new BlockPos(200, 90, 200);
        }
        if(room == "pearl"){
            return new BlockPos(300, 80, 300);
        }
        if(room == "aquamarine" || room == "morganite"){
            return new BlockPos(400, 70, 400);
        }
        if(room == "bismuth"){
            return new BlockPos(500, 60, 500);
        }
        if(room == "comby"){
            return new BlockPos(600, 50, 600);
        }
        if(room == "hessonite"){
            return new BlockPos(700, 40, 700);
        }
        if(room == "jade"){
            return new BlockPos(800, 30, 800);
        }
        if(room == "misc"){
            return new BlockPos(900, 20, 900);
        }
        if(room == "pebble"){
            return new BlockPos(1000, 10, 1000);
        }
        if(room == "peridot"){
            return new BlockPos(1100, 0, 1100);
        }
        if(room == "pezz"){
            return new BlockPos(1200, 10, 1200);
        }
        if(room == "rose_quartz"){
            return new BlockPos(1300, 20, 1300);
        }
        if(room == "ruby"){
            return new BlockPos(1400, 30, 1400);
        }
        if(room == "rutile"){
            return new BlockPos(1500, 40, 1500);
        }
        if(room == "sapphire"){
            return new BlockPos(1600, 50, 1600);
        }
        if(room == "topaz"){
            return new BlockPos(1700, 60, 1700);
        }
        if(room == "zircon"){
            return new BlockPos(1800, 70, 1800);
        }
        return new BlockPos(0, 0, 0);
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return BLOCK_COLLISION_AABB;
    }
}
