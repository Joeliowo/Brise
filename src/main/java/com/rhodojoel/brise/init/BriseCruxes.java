package com.rhodojoel.brise.init;

import java.util.Iterator;
import java.util.Set;

import mod.akrivus.kagic.init.ModBlocks;
import mod.akrivus.kagic.init.ModEntities;
import com.rhodojoel.brise.gem.*;
import net.minecraft.init.Blocks;

public class BriseCruxes {
    public static void register() {

        {
            {

                EntityLavenderJade.LAVENDERJADE_YIELDS.put(ModBlocks.PINK_SANDSTONE.getDefaultState(), 10.99);
                ModEntities.registerWithOreDictionary(EntityLavenderJade.LAVENDERJADE_YIELDS, "Jade");

                EntityRedJade.REDJADE_YIELDS.put(Blocks.RED_SANDSTONE.getDefaultState(), 10.99);
                ModEntities.registerWithOreDictionary(EntityRedJade.REDJADE_YIELDS, "Jade");

                EntityYellowJade.YELLOWJADE_YIELDS.put(Blocks.HAY_BLOCK.getDefaultState(), 10.99);
                ModEntities.registerWithOreDictionary(EntityYellowJade.YELLOWJADE_YIELDS, "Jade");

                EntityBlackJade.BLACKJADE_YIELDS.put(Blocks.BEDROCK.getDefaultState(), 10.99);
                ModEntities.registerWithOreDictionary(EntityBlackJade.BLACKJADE_YIELDS, "Jade");

                EntityWhiteJade.WHITEJADE_YIELDS.put(Blocks.BONE_BLOCK.getDefaultState(), 15.99);
                ModEntities.registerWithOreDictionary(EntityWhiteJade.WHITEJADE_YIELDS, "Jade");

                EntityMorganite.MORGANITE_YIELDS.put(Blocks.EMERALD_ORE.getDefaultState(), 0.33);
                EntityMorganite.MORGANITE_YIELDS.put(Blocks.EMERALD_BLOCK.getDefaultState(), 0.55);
                EntityMorganite.MORGANITE_YIELDS.put(Blocks.PINK_GLAZED_TERRACOTTA.getDefaultState(), 5.99);
                ModEntities.registerWithOreDictionary(EntityMorganite.MORGANITE_YIELDS, "Morganite");
                ModEntities.registerWithOreDictionary(EntityMorganite.MORGANITE_YIELDS, "Beryl");

                EntityPezz.PEZZ_YIELDS.put(Blocks.MYCELIUM.getDefaultState(), 5.99);
                ModEntities.registerWithOreDictionary(EntityPezz.PEZZ_YIELDS, "Pezzottaite");
                ModEntities.registerWithOreDictionary(EntityPezz.PEZZ_YIELDS, "Beryl");

                EntitySunstone.SUNSTONE_YIELDS.put(Blocks.GLOWSTONE.getDefaultState(), 10.99);
                ModEntities.registerWithOreDictionary(EntitySunstone.SUNSTONE_YIELDS, "Sunstone");

                EntitySunsetSodalite.SUNSETSODALITE_YIELDS.put(Blocks.SEA_LANTERN.getDefaultState(), 10.99);
                ModEntities.registerWithOreDictionary(EntitySunsetSodalite.SUNSETSODALITE_YIELDS, "Moonstone");
                ModEntities.registerWithOreDictionary(EntitySunsetSodalite.SUNSETSODALITE_YIELDS, "Sodalite");
            }
        }
    }
}




