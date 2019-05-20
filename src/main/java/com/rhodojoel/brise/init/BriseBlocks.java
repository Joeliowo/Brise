package com.rhodojoel.brise.init;

//import com.rhodojoel.brise.blocks.BlockGemDoorEnter;
//import com.rhodojoel.brise.blocks.BlockGemDis;
import com.rhodojoel.brise.blocks.BlockGemDoorEnter;
import com.rhodojoel.brise.blocks.BlockGemDoorExit;
import com.rhodojoel.brise.blocks.BlockRoomBlock;
import com.rhodojoel.brise.blocks.BlockSummoner;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;

public class BriseBlocks {
   // public static final BlockGemDis GEM_DIS = new BlockGemDis(false);
   // public static final BlockGemDis LOADED_GEM_DIS = new BlockGemDis(true);
    public static final BlockGemDoorEnter ENTER_BLOCK = new BlockGemDoorEnter("enter_block");
    public static final BlockGemDoorExit EXIT_BLOCK = new BlockGemDoorExit("exit_block", false);
    public static final BlockGemDoorExit CHOOSE_BLOCK = new BlockGemDoorExit("choose_block", true);
    public static final BlockSummoner SUMMONER = new BlockSummoner("summon");
    public static final BlockRoomBlock ROOM_BLOCK = new BlockRoomBlock();


    public static void register(RegistryEvent.Register<Block> event){
        //registerBlock(GEM_DIS, new ResourceLocation("brise:gem_dis"), event);
        registerBlock(ENTER_BLOCK, new ResourceLocation("brise:enter_block"), event);
        registerBlock(EXIT_BLOCK, new ResourceLocation("brise:exit_block"), event);
        registerBlock(CHOOSE_BLOCK, new ResourceLocation("brise:choose_block"), event);
        registerBlock(SUMMONER, new ResourceLocation("brise:summon"), event);
        registerBlock(ROOM_BLOCK, new ResourceLocation("brise:room_block"), event);
    }

    public static void registerItems(RegistryEvent.Register<Item> event){
        //registerBlockItem(GEM_DIS, new ResourceLocation("brise:gem_dis"), event);
        registerBlockItem(ENTER_BLOCK, new ResourceLocation("brise:enter_block"), event);
        registerBlockItem(EXIT_BLOCK, new ResourceLocation("brise:exit_block"), event);
        registerBlockItem(CHOOSE_BLOCK, new ResourceLocation("brise:choose_block"), event);
        registerBlockItem(SUMMONER, new ResourceLocation("brise:summon"), event);
        registerBlockItem(ROOM_BLOCK, new ResourceLocation("brise:room_block"), event);
    }


    public static void registerBlock(Block block, ResourceLocation location, RegistryEvent.Register<Block> event) {
        block.setRegistryName(location);
        event.getRegistry().register(block);
    }

    public static void registerBlockItem(Block block, ResourceLocation location, RegistryEvent.Register<Item> event) {
        registerBlockItem(block, location, event, "");
    }

    public static void registerBlockItem(Block block, ResourceLocation location, RegistryEvent.Register<Item> event, String oredictName) {
        ItemBlock item = new ItemBlock(block);
        item.setRegistryName(location);
        event.getRegistry().register(item);

        if (!oredictName.isEmpty()) {
            OreDictionary.registerOre(oredictName, item);
        }

        if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
        }
    }
}
