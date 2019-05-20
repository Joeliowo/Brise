package com.rhodojoel.brise.blocks;

import com.rhodojoel.brise.init.BriseDimensions;
import com.rhodojoel.brise.misc.SchematicLoader;
import com.rhodojoel.brise.misc.TeleporterRooms;
import mod.akrivus.kagic.init.ModCreativeTabs;
import mod.akrivus.kagic.init.ModItems;
import mod.akrivus.kagic.items.ItemGem;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
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

import javax.annotation.Nullable;

public class BlockGemDoorExit extends Block {
    public boolean inDim;
    public BlockPos doorPos;
    public String gemm;
    protected static final AxisAlignedBB BLOCK_COLLISION_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D);

    public BlockGemDoorExit(String naem, boolean inDim) {
        super(Material.ROCK);
        this.setUnlocalizedName(naem);
        this.setResistance(4);
        this.setHardness(-1);
        if(!inDim) {
            this.setCreativeTab(ModCreativeTabs.CREATIVE_TAB_OTHER);
        }
        this.inDim = inDim;
    }

    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entity) {
        if(!worldIn.isRemote) {
            if(!this.inDim) {
                if (entity.getRidingEntity() == null && !entity.isBeingRidden() && entity instanceof EntityPlayerMP) {
                    EntityPlayerMP player = (EntityPlayerMP) entity;
                    if (player.isSneaking()) {
                        if (player.dimension == BriseDimensions.DIM_ROOMS_ID) {
                            player.mcServer.getPlayerList().transferPlayerToDimension(player, 0,
                                    new TeleporterRooms(player.mcServer.getWorld(0)));
                            player.attemptTeleport(doorPos.getX(), doorPos.getY(), doorPos.getZ());
                        }
                    }
                }
            }
            else{
                if(entity instanceof EntityPlayerMP) {
                    EntityPlayerMP player = (EntityPlayerMP) entity;
                    BlockPos i = this.WorldOffset(this.gemm).up();
                    player.attemptTeleport(i.getX(), i.getY(), i.getZ());
                }
            }
        }
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return BLOCK_COLLISION_AABB;
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
}
