package com.rhodojoel.brise.init;

import com.rhodojoel.brise.world.gen.biomes.BiomeGenRooms;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.lang.reflect.Field;

@Mod.EventBusSubscriber(modid = "brise")
public class BriseBiomes {
    public static Biome BIOME_ROOMS = add(new BiomeGenRooms((new Biome.BiomeProperties("Rooms")).setBaseHeight(0.0F).setHeightVariation(0.05F).setTemperature(6.0F).setRainDisabled()), "rooms");

    public BriseBiomes() {
    }

    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> e) throws IllegalAccessException {
        IForgeRegistry<Biome> reg = e.getRegistry();
        Field[] var2 = BriseBiomes.class.getDeclaredFields();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Field bField = var2[var4];
            Biome biome = (Biome)bField.get((Object)null);
            reg.register(biome);
        }

        addTypes(BIOME_ROOMS, 0, BiomeManager.BiomeType.DESERT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.MESA);
    }

    private static Biome add(Biome biome, String name) {
        biome.setRegistryName("thedalekmod", name);
        return biome;
    }

    private static void addTypes(Biome biome, int weight, BiomeManager.BiomeType biomeType, BiomeDictionary.Type... types) {
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addBiome(biomeType, new BiomeManager.BiomeEntry(biome, weight));
    }
}
