package com.rhodojoel.brise.init;

import com.rhodojoel.brise.items.*;
import mod.akrivus.kagic.init.ModItems;
import mod.akrivus.kagic.items.ItemGem;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemDye;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.RegistryEvent;

public class BriseItems {
	public static final ItemGem LAVENDER_JADE_GEM = new ItemGem("lavenderjade");
	public static final ItemGem CRACKED_LAVENDER_JADE_GEM = new ItemGem("lavenderjade", true);
	public static final ItemGem RED_JADE_GEM = new ItemGem("redjade");
	public static final ItemGem CRACKED_RED_JADE_GEM = new ItemGem("redjade", true);
	public static final ItemGem YELLOW_JADE_GEM = new ItemGem("yellowjade");
	public static final ItemGem CRACKED_YELLOW_JADE_GEM = new ItemGem("yellowjade", true);
    public static final ItemGem BLACK_JADE_GEM = new ItemGem("blackjade");
    public static final ItemGem CRACKED_BLACK_JADE_GEM = new ItemGem("blackjade", true);
    public static final ItemGem WHITE_JADE_GEM = new ItemGem("whitejade");
    public static final ItemGem CRACKED_WHITE_JADE_GEM = new ItemGem("whitejade", true);
	public static final ItemGem JELLYBEAN_GEM = new ItemGem("jellybean");
	public static final ItemGem CRACKED_JELLYBEAN_GEM = new ItemGem("jellybean", true);
	public static final ItemGem MORGANITE_GEM = new ItemGem("morganite");
	public static final ItemGem CRACKED_MORGANITE_GEM = new ItemGem("morganite", true);
    public static final ItemGem PEZZ_GEM = new ItemGem("pezz");
    public static final ItemGem CRACKED_PEZZ_GEM = new ItemGem("pezz", true);
    public static final ItemGem MERCURY_GEM = new ItemGem("mercury");
    public static final ItemGem CRACKED_MERCURY_GEM = new ItemGem("mercury", true);
    public static final ItemGem SUNSTONE_GEM = new ItemGem("sunstone");
    public static final ItemGem CRACKED_SUNSTONE_GEM = new ItemGem("sunstone", true);
    public static final ItemGem SUNSETSODALITE_GEM = new ItemGem("sunsetsodalite");
    public static final ItemGem CRACKED_SUNSETSODALITE_GEM = new ItemGem("sunsetsodalite", true);

    public static final ItemGem WHITE_HERK_GEM = new ItemGem("white_herk");
    public static final ItemGem CRACKED_WHITE_HERK_GEM = new ItemGem("white_herk", true);
    public static final ItemGem YELLOW_HERK_GEM = new ItemGem("yellow_herk");
    public static final ItemGem CRACKED_YELLOW_HERK_GEM = new ItemGem("yellow_herk", true);
    public static final ItemGem BLUE_HERK_GEM = new ItemGem("blue_herk");
    public static final ItemGem CRACKED_BLUE_HERK_GEM = new ItemGem("blue_herk", true);
    public static final ItemGem PINK_HERK_GEM = new ItemGem("pink_herk");
    public static final ItemGem CRACKED_PINK_HERK_GEM = new ItemGem("pink_herk", true);
    //public static final ItemGem FLUORITE_GEM = new ItemGem("fluorite");
    //public static final ItemGem CRACKED_FLUORITE_GEM = new ItemGem("fluorite", true);

    public static final ItemGem CORZIR_GEM = new ItemGem("corzir");
    public static final ItemGem CRACKED_CORZIR_GEM = new ItemGem("corzir", true);
    public static final ItemGem CORZIR_0_GEM = new ItemGem("corzir_0");
    public static final ItemGem CRACKED_CORZIR_0_GEM = new ItemGem("corzir_0", true);
    public static final ItemGem CORZIR_1_GEM = new ItemGem("corzir_1");
    public static final ItemGem CRACKED_CORZIR_1_GEM = new ItemGem("corzir_1", true);
    public static final ItemGem CORZIR_2_GEM = new ItemGem("corzir_2");
    public static final ItemGem CRACKED_CORZIR_2_GEM = new ItemGem("corzir_2", true);
    public static final ItemGem CORZIR_3_GEM = new ItemGem("corzir_3");
    public static final ItemGem CRACKED_CORZIR_3_GEM = new ItemGem("corzir_3", true);
    public static final ItemGem CORZIR_4_GEM = new ItemGem("corzir_4");
    public static final ItemGem CRACKED_CORZIR_4_GEM = new ItemGem("corzir_4", true);
    public static final ItemGem CORZIR_5_GEM = new ItemGem("corzir_5");
    public static final ItemGem CRACKED_CORZIR_5_GEM = new ItemGem("corzir_5", true);
    public static final ItemGem CORZIR_6_GEM = new ItemGem("corzir_6");
    public static final ItemGem CRACKED_CORZIR_6_GEM = new ItemGem("corzir_6", true);
    public static final ItemGem CORZIR_7_GEM = new ItemGem("corzir_7");
    public static final ItemGem CRACKED_CORZIR_7_GEM = new ItemGem("corzir_7", true);
    public static final ItemGem CORZIR_8_GEM = new ItemGem("corzir_8");
    public static final ItemGem CRACKED_CORZIR_8_GEM = new ItemGem("corzir_8", true);
    public static final ItemGem CORZIR_9_GEM = new ItemGem("corzir_9");
    public static final ItemGem CRACKED_CORZIR_9_GEM = new ItemGem("corzir_9", true);
    public static final ItemGem CORZIR_10_GEM = new ItemGem("corzir_10");
    public static final ItemGem CRACKED_CORZIR_10_GEM = new ItemGem("corzir_10", true);
    public static final ItemGem CORZIR_11_GEM = new ItemGem("corzir_11");
    public static final ItemGem CRACKED_CORZIR_11_GEM = new ItemGem("corzir_11", true);
    public static final ItemGem CORZIR_12_GEM = new ItemGem("corzir_12");
    public static final ItemGem CRACKED_CORZIR_12_GEM = new ItemGem("corzir_12", true);
    public static final ItemGem CORZIR_13_GEM = new ItemGem("corzir_13");
    public static final ItemGem CRACKED_CORZIR_13_GEM = new ItemGem("corzir_13", true);
    public static final ItemGem CORZIR_14_GEM = new ItemGem("corzir_14");
    public static final ItemGem CRACKED_CORZIR_14_GEM = new ItemGem("corzir_14", true);
    public static final ItemGem CORZIR_15_GEM = new ItemGem("corzir_15");
    public static final ItemGem CRACKED_CORZIR_15_GEM = new ItemGem("corzir_15", true);

    public static final ItemComby COMBY_WHITE = new ItemComby("0", GetDyeColor(0));
    public static final ItemComby COMBY_ORANGE = new ItemComby("1", GetDyeColor(1));
    public static final ItemComby COMBY_MAGENTA = new ItemComby("2", GetDyeColor(2));
    public static final ItemComby COMBY_LIGHT_BLUE = new ItemComby("3", GetDyeColor(3));
    public static final ItemComby COMBY_YELLOW = new ItemComby("4", GetDyeColor(4));
    public static final ItemComby COMBY_LIME = new ItemComby("5", GetDyeColor(5));
    public static final ItemComby COMBY_PINK = new ItemComby("6", GetDyeColor(6));
    public static final ItemComby COMBY_GREY = new ItemComby("7", GetDyeColor(7));
    public static final ItemComby COMBY_SILVER = new ItemComby("8", GetDyeColor(8));
    public static final ItemComby COMBY_CYAN = new ItemComby("9", GetDyeColor(9));
    public static final ItemComby COMBY_PURPLE = new ItemComby("10", GetDyeColor(10));
    public static final ItemComby COMBY_BLUE = new ItemComby("11", GetDyeColor(11));
    public static final ItemComby COMBY_BROWN = new ItemComby("12", GetDyeColor(12));
    public static final ItemComby COMBY_GREEN = new ItemComby("13", GetDyeColor(13));
    public static final ItemComby COMBY_RED = new ItemComby("14", GetDyeColor(14));
    public static final ItemComby COMBY_BLACK = new ItemComby("15", GetDyeColor(15));

    public static final ItemPebblePicture PEBBLE_PICTURE = new ItemPebblePicture();
    public static final ItemDoll DOLL = new ItemDoll();
    public static final ItemFireSalt FIRE_SALT = new ItemFireSalt(1, 1, false);
    public static final ItemDiamondCommunicator DIAMOND_COMMUNICATOR = new ItemDiamondCommunicator();
    public static final ItemLapisMirror LAPIS_MIRROR = new ItemLapisMirror();
    public static final Item DESTABALIZER_BEAM = new Item().setUnlocalizedName("destabalizer_beam");

    public static final Item WHITE_GEM = new ItemHerkGem("white_gem", TextFormatting.WHITE, "This has gone on long enough!");
    public static final Item YELLOW_GEM = new ItemHerkGem("yellow_gem", TextFormatting.YELLOW, "The Perfect Empire.");
    public static final Item BLUE_GEM = new ItemHerkGem("blue_gem", TextFormatting.BLUE, "What's the use of feeling?");
    public static final Item PINK_GEM = new ItemHerkGem("pink_gem", TextFormatting.LIGHT_PURPLE, "Did she deserve it?");

    public static final ItemDestabalizerCannon BLUE_DESTABALIZER_CANNON = new ItemDestabalizerCannon("blue");

    public static final ItemGemDust RUBY_GEM_DUST = new ItemGemDust("ruby");
    public static final ItemGemDust LAPIS_GEM_DUST = new ItemGemDust("lapis");
    public static final ItemGemDust MISC_GEM_DUST = new ItemGemDust("misc");

    public static EnumDyeColor GetDyeColor(int comby){
        if (true) {
            switch (comby) {
                case 0:
                    return EnumDyeColor.WHITE;
                case 1:
                    return EnumDyeColor.ORANGE;
                case 2:
                    return EnumDyeColor.MAGENTA;
                case 3:
                    return EnumDyeColor.LIGHT_BLUE;
                case 4:
                    return EnumDyeColor.YELLOW;
                case 5:
                    return EnumDyeColor.LIME;
                case 6:
                    return EnumDyeColor.PINK;
                case 7:
                    return EnumDyeColor.GRAY;
                case 8:
                    return EnumDyeColor.SILVER;
                case 9:
                    return EnumDyeColor.CYAN;
                case 10:
                    return EnumDyeColor.PURPLE;
                case 11:
                    return EnumDyeColor.BLUE;
                case 12:
                    return EnumDyeColor.BROWN;
                case 13:
                    return EnumDyeColor.GREEN;
                case 14:
                    return EnumDyeColor.RED;
                case 15:
                    return EnumDyeColor.BLACK;
                default:
                    return EnumDyeColor.WHITE;
            }
        }
        else{
            return EnumDyeColor.WHITE;
        }
    }
	
	public static void register(RegistryEvent.Register<Item> event) {
		ModItems.registerExternalGem(LAVENDER_JADE_GEM, CRACKED_LAVENDER_JADE_GEM, "brise", event);
        ModItems.registerExternalGem(RED_JADE_GEM, CRACKED_RED_JADE_GEM, "brise", event);
        ModItems.registerExternalGem(YELLOW_JADE_GEM, CRACKED_YELLOW_JADE_GEM, "brise", event);
        ModItems.registerExternalGem(BLACK_JADE_GEM, CRACKED_BLACK_JADE_GEM, "brise", event);
        ModItems.registerExternalGem(WHITE_JADE_GEM, CRACKED_WHITE_JADE_GEM, "brise", event);
		ModItems.registerExternalGem(JELLYBEAN_GEM, CRACKED_JELLYBEAN_GEM, "brise", event);
		ModItems.registerExternalGem(MORGANITE_GEM, CRACKED_MORGANITE_GEM, "brise", event);
        ModItems.registerExternalGem(PEZZ_GEM, CRACKED_PEZZ_GEM, "brise", event);
        ModItems.registerExternalGem(WHITE_HERK_GEM, CRACKED_WHITE_HERK_GEM, "brise", event);
        ModItems.registerExternalGem(YELLOW_HERK_GEM, CRACKED_YELLOW_HERK_GEM, "brise", event);
        ModItems.registerExternalGem(BLUE_HERK_GEM, CRACKED_BLUE_HERK_GEM, "brise", event);
        ModItems.registerExternalGem(PINK_HERK_GEM, CRACKED_PINK_HERK_GEM, "brise", event);
        //ModItems.registerExternalGem(MERCURY_GEM, CRACKED_MERCURY_GEM, "brise", event);
        //ModItems.registerExternalGem(SUNSTONE_GEM, CRACKED_SUNSTONE_GEM, "brise", event);
        //ModItems.registerExternalGem(SUNSETSODALITE_GEM, CRACKED_SUNSETSODALITE_GEM, "brise", event);
        //ModItems.registerExternalGem(FLUORITE_GEM, CRACKED_FLUORITE_GEM, "brise", event);

        ModItems.registerExternalGem(CORZIR_GEM, CRACKED_CORZIR_GEM, "brise", event);
        ModItems.registerExternalGem(CORZIR_0_GEM, CRACKED_CORZIR_0_GEM, "brise", event);
        ModItems.registerExternalGem(CORZIR_1_GEM, CRACKED_CORZIR_1_GEM, "brise", event);
        ModItems.registerExternalGem(CORZIR_2_GEM, CRACKED_CORZIR_2_GEM, "brise", event);
        ModItems.registerExternalGem(CORZIR_3_GEM, CRACKED_CORZIR_3_GEM, "brise", event);
        ModItems.registerExternalGem(CORZIR_4_GEM, CRACKED_CORZIR_4_GEM, "brise", event);
        ModItems.registerExternalGem(CORZIR_5_GEM, CRACKED_CORZIR_5_GEM, "brise", event);
        ModItems.registerExternalGem(CORZIR_6_GEM, CRACKED_CORZIR_6_GEM, "brise", event);
        ModItems.registerExternalGem(CORZIR_7_GEM, CRACKED_CORZIR_7_GEM, "brise", event);
        ModItems.registerExternalGem(CORZIR_8_GEM, CRACKED_CORZIR_8_GEM, "brise", event);
        ModItems.registerExternalGem(CORZIR_9_GEM, CRACKED_CORZIR_9_GEM, "brise", event);
        ModItems.registerExternalGem(CORZIR_10_GEM, CRACKED_CORZIR_10_GEM, "brise", event);
        ModItems.registerExternalGem(CORZIR_11_GEM, CRACKED_CORZIR_11_GEM, "brise", event);
        ModItems.registerExternalGem(CORZIR_12_GEM, CRACKED_CORZIR_12_GEM, "brise", event);
        ModItems.registerExternalGem(CORZIR_13_GEM, CRACKED_CORZIR_13_GEM, "brise", event);
        ModItems.registerExternalGem(CORZIR_14_GEM, CRACKED_CORZIR_14_GEM, "brise", event);
        ModItems.registerExternalGem(CORZIR_15_GEM, CRACKED_CORZIR_15_GEM, "brise", event);

        ModItems.registerExternalItem(DOLL, "brise", event);
        ModItems.registerExternalItem(PEBBLE_PICTURE, "brise", event);
        ModItems.registerExternalItem(FIRE_SALT, "brise", event);
        ModItems.registerExternalItem(DIAMOND_COMMUNICATOR, "brise", event);
        ModItems.registerExternalItem(LAPIS_MIRROR, "brise", event);
        //ModItems.registerExternalItem(DESTABALIZER_BEAM, "brise", event);
        ModItems.registerExternalItem(WHITE_GEM, "brise", event);
        ModItems.registerExternalItem(YELLOW_GEM, "brise", event);
        ModItems.registerExternalItem(BLUE_GEM, "brise", event);
        ModItems.registerExternalItem(PINK_GEM, "brise", event);

        //ModItems.registerExternalItem(BLUE_DESTABALIZER_CANNON, "brise", event);

        //ModItems.registerExternalItem(RUBY_GEM_DUST, "brise", event);
        //ModItems.registerExternalItem(LAPIS_GEM_DUST, "brise", event);
        //ModItems.registerExternalItem(MISC_GEM_DUST, "brise", event);

        ModItems.registerExternalItem(COMBY_BLACK, "brise", event);
        ModItems.registerExternalItem(COMBY_BLUE, "brise", event);
        ModItems.registerExternalItem(COMBY_BROWN, "brise", event);
        ModItems.registerExternalItem(COMBY_CYAN, "brise", event);
        ModItems.registerExternalItem(COMBY_GREEN, "brise", event);
        ModItems.registerExternalItem(COMBY_GREY, "brise", event);
        ModItems.registerExternalItem(COMBY_LIGHT_BLUE, "brise", event);
        ModItems.registerExternalItem(COMBY_LIME, "brise", event);
        ModItems.registerExternalItem(COMBY_MAGENTA, "brise", event);
        ModItems.registerExternalItem(COMBY_ORANGE, "brise", event);
        ModItems.registerExternalItem(COMBY_PINK, "brise", event);
        ModItems.registerExternalItem(COMBY_PURPLE, "brise", event);
        ModItems.registerExternalItem(COMBY_RED, "brise", event);
        ModItems.registerExternalItem(COMBY_SILVER, "brise", event);
        ModItems.registerExternalItem(COMBY_WHITE, "brise", event);
        ModItems.registerExternalItem(COMBY_YELLOW, "brise", event);
	}
}