package com.rhodojoel.brise.init;

import com.rhodojoel.brise.items.ItemComby;
import com.rhodojoel.brise.world.gen.BriseWorldGenerator;
import mod.akrivus.kagic.init.KAGIC;
import mod.akrivus.kagic.init.ModBlocks;
import mod.akrivus.kagic.init.ModItems;
import mod.heimrarnadalr.kagic.world.GenEventCanceller;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Brise.MODID, version = Brise.VERSION, acceptedMinecraftVersions = Brise.MCVERSION, dependencies="after:kagic")
public class Brise {
    public static final String MODID = "brise";
    public static final String VERSION = "@version";
    public static final String MCVERSION = "@mcversion";
    @Instance
    public static Brise instance;
    public static BriseWorldGenerator generator;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        BriseEvents.register();
        BriseGems.register();
        BriseGems.registerCor();
        registerRecipes();
        Brise.generator = new BriseWorldGenerator();
        if (KAGIC.isAprilFools()) {
            BriseConfigs.AprilFools = true;
        }
        BriseDimensions.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        BriseCruxes.register();
        GameRegistry.registerWorldGenerator(generator, 50);
    }

    public static void registerRecipes() {
        registerCombyRecipes();
        registerBlockRecipes();
    }

    public static Item WhatComby(int comby) {
        if (true) {
            switch (comby) {
                case 0:
                    return BriseItems.COMBY_WHITE;
                case 1:
                    return BriseItems.COMBY_ORANGE;
                case 2:
                    return BriseItems.COMBY_MAGENTA;
                case 3:
                    return BriseItems.COMBY_LIGHT_BLUE;
                case 4:
                    return BriseItems.COMBY_YELLOW;
                case 5:
                    return BriseItems.COMBY_LIME;
                case 6:
                    return BriseItems.COMBY_PINK;
                case 7:
                    return BriseItems.COMBY_GREY;
                case 8:
                    return BriseItems.COMBY_SILVER;
                case 9:
                    return BriseItems.COMBY_CYAN;
                case 10:
                    return BriseItems.COMBY_PURPLE;
                case 11:
                    return BriseItems.COMBY_BLUE;
                case 12:
                    return BriseItems.COMBY_BROWN;
                case 13:
                    return BriseItems.COMBY_GREEN;
                case 14:
                    return BriseItems.COMBY_RED;
                case 15:
                    return BriseItems.COMBY_BLACK;
                default:
                    return BriseItems.COMBY_WHITE;
            }
        } else {
            return BriseItems.COMBY_WHITE;
        }
    }

    public static void registerCombyRecipes() {
        for (int i = 0; i < 16; i++) {
            GameRegistry.addShapedRecipe(
                    new ResourceLocation("brise:comby" + i),
                    new ResourceLocation("comby"),
                    new ItemStack(WhatComby(i)),
                    new Object[]{"012", "345", "678", Character.valueOf('0'), new ItemStack(Items.DYE, 1, 15 - i), Character.valueOf('1'),
                            Blocks.STONE_BUTTON, Character.valueOf('2'), new ItemStack(Items.DYE, 1, 15 - i), Character.valueOf('3'),
                            new ItemStack(Items.DYE, 1, 15 - i), Character.valueOf('4'), ModItems.ACTIVATED_GEM_SHARD, Character.valueOf('5'),
                            new ItemStack(Items.DYE, 1, 15 - i), Character.valueOf('6'), new ItemStack(Items.DYE, 1, 15 - i), Character.valueOf('7'),
                            Items.STICK, Character.valueOf('8'), new ItemStack(Items.DYE, 1, 15 - i),});

        }
    }

    public static void registerBlockRecipes() {
        ItemStack recStack = new ItemStack(BriseBlocks.SUMMONER, 1);
        GameRegistry.addShapedRecipe(
                new ResourceLocation("brise:summon"),
                new ResourceLocation("blocko"),
                recStack,
                new Object[]{"012", "345", "678", Character.valueOf('0'), Blocks.IRON_BLOCK, Character.valueOf('1'), Items.DIAMOND,
                        Character.valueOf('2'), Blocks.IRON_BLOCK, Character.valueOf('3'), Items.BLAZE_ROD, Character.valueOf('4'), Blocks.BEACON,
                        Character.valueOf('5'), Items.BLAZE_ROD, Character.valueOf('6'), Blocks.IRON_BLOCK, Character.valueOf('7'),
                        Items.DIAMOND, Character.valueOf('8'), Blocks.IRON_BLOCK,
                });
        ItemStack recStack2 = new ItemStack(BriseBlocks.ENTER_BLOCK, 1);
        GameRegistry.addShapedRecipe(
                new ResourceLocation("brise:enter_block"),
                new ResourceLocation("blocko"),
                recStack2,
                new Object[]{"012", "345", "678", Character.valueOf('0'), BriseBlocks.ROOM_BLOCK, Character.valueOf('1'), BriseItems.WHITE_GEM,
                        Character.valueOf('2'), BriseBlocks.ROOM_BLOCK, Character.valueOf('3'), BriseItems.YELLOW_GEM, Character.valueOf('4'), ModBlocks.GALAXY_PAD_CORE,
                        Character.valueOf('5'), BriseItems.BLUE_GEM, Character.valueOf('6'), BriseBlocks.ROOM_BLOCK, Character.valueOf('7'),
                        BriseItems.PINK_GEM, Character.valueOf('8'), BriseBlocks.ROOM_BLOCK,
                });
    }


        @Mod.EventBusSubscriber(modid = Brise.MODID)
        public static class RegistrationHandler {
            @SubscribeEvent
            public static void registerItems(RegistryEvent.Register<Item> event) {
                BriseItems.register(event);
                BriseBlocks.registerItems(event);
            }

            @SubscribeEvent
            public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
                BriseSounds.register(event);
            }

            @SubscribeEvent
            public static void registerBlocks(RegistryEvent.Register<Block> event) {
                BriseBlocks.register(event);
            }

            @SubscribeEvent
            public static void changeConfigs(ConfigChangedEvent.OnConfigChangedEvent event) {
                if (event.getModID().equals(Brise.MODID)) {
                    ConfigManager.sync(Brise.MODID, Config.Type.INSTANCE);
                }
            }
        }
    }

