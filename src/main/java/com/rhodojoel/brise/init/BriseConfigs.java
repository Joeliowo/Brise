package com.rhodojoel.brise.init;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.LangKey;

@Config(modid = "brise")
@LangKey("brise.config.title")
public class BriseConfigs {
    public static boolean enableCorruption = false;
    public static int rateOfCorruptionInMultiples = 100;
    public static boolean AprilFools = false;
}
