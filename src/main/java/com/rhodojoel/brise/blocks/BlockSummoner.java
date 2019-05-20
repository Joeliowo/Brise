package com.rhodojoel.brise.blocks;

import com.rhodojoel.brise.gem.boss.EntityBlueHerk;
import com.rhodojoel.brise.gem.boss.EntityPinkHerk;
import com.rhodojoel.brise.gem.boss.EntityWhiteHerk;
import com.rhodojoel.brise.gem.boss.EntityYellowHerk;
import com.rhodojoel.brise.init.BriseDimensions;
import com.rhodojoel.brise.init.BriseEvents;
import com.rhodojoel.brise.init.BriseItems;
import com.rhodojoel.brise.items.ItemHerkGem;
import com.rhodojoel.brise.misc.TeleporterRooms;
import mod.akrivus.kagic.entity.EntityGem;
import mod.akrivus.kagic.init.ModCreativeTabs;
import mod.akrivus.kagic.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class BlockSummoner extends Block {
    protected static final AxisAlignedBB BLOCK_COLLISION_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.3D, 1.0D);

    public BlockSummoner(String naem) {
        super(Material.IRON);
        this.setUnlocalizedName(naem);
        this.setResistance(4);
        this.setHardness(2);
        this.setCreativeTab(ModCreativeTabs.CREATIVE_TAB_OTHER);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ){
        AxisAlignedBB axisalignedbb = (new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(), (pos.getX() + 1), (pos.getY() + 1), (pos.getZ() + 1))).grow(16.0, (double) worldIn.getHeight(), 16.0);
        List<EntityItem> list = worldIn.<EntityItem>getEntitiesWithinAABB(EntityItem.class, axisalignedbb);
        boolean item1 = false;
        boolean item2 = false;
        boolean item3 = false;
        String herk = "";
        for(EntityItem item : list){
            if(true){
                IBlockState block = worldIn.getBlockState(pos.down());
                if(block.getBlock() == Blocks.PINK_GLAZED_TERRACOTTA){
                    if(item.getItem().getItem() == Items.DIAMOND){
                        item1 = true;
                    }
                    if(item.getItem().getItem() == ModItems.ROSE_QUARTZ_GEM){
                        item2 = true;
                    }
                    if(item.getItem().getItem() == Items.GHAST_TEAR){
                        item3 = true;
                    }
                    herk = "pink";
                }
                else if(block.getBlock() == Blocks.LAPIS_BLOCK){
                    if(item.getItem().getItem() == BriseItems.PINK_GEM){
                        item1 = true;
                    }
                    if(true){
                        item2 = true;
                    }
                    if(true){
                        item3 = true;
                    }
                    herk = "blue";
                }
                else if(block.getBlock() == Blocks.GOLD_ORE){
                    if(item.getItem().getItem() == BriseItems.BLUE_GEM){
                        item1 = true;
                    }
                    if(item.getItem().getItem() == BriseItems.PINK_GEM){
                        item2 = true;
                    }
                    if(true){
                        item3 = true;
                    }
                    herk = "yellow";
                }
                else if(block.getBlock() == Blocks.IRON_BLOCK){
                    if(item.getItem().getItem() == BriseItems.PINK_GEM){
                        item1 = true;
                    }
                    if(item.getItem().getItem() == BriseItems.BLUE_GEM){
                        item2 = true;
                    }
                    if(item.getItem().getItem() == BriseItems.YELLOW_GEM){
                        item3 = true;
                    }
                    herk = "white";
                }
            }
        }
        if(item1 && item2 && item3){
            for(EntityItem item : list){
                if(item.getItem().getItem() instanceof ItemHerkGem){
                    continue;
                }
                item.setDead();
            }
            if(herk == "pink") {
                EntityItem pink = new EntityItem(worldIn);
                pink.setItem(new ItemStack(BriseItems.PINK_HERK_GEM));
                pink.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
                worldIn.spawnEntity(pink);
            }
            else if(herk == "blue") {
                EntityItem pink = new EntityItem(worldIn);
                pink.setItem(new ItemStack(BriseItems.BLUE_HERK_GEM));
                worldIn.spawnEntity(pink);
            }
            else if(herk == "yellow") {
                EntityItem pink = new EntityItem(worldIn);
                pink.setItem(new ItemStack(BriseItems.YELLOW_HERK_GEM));
                worldIn.spawnEntity(pink);
            }
            else if(herk == "white") {
                EntityItem pink = new EntityItem(worldIn);
                pink.setItem(new ItemStack(BriseItems.WHITE_HERK_GEM));
                worldIn.spawnEntity(pink);
            }
        }
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return true;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return BLOCK_COLLISION_AABB;
    }

    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos)
    {
        return BLOCK_COLLISION_AABB.offset(pos);
    }
}
