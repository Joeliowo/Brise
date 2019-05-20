package com.rhodojoel.brise.init;

import com.rhodojoel.brise.entity.EntityDestabalizer;
import com.rhodojoel.brise.entity.EntityPeppaPig;
import com.rhodojoel.brise.gem.*;
import com.rhodojoel.brise.gem.boss.EntityBlueHerk;
import com.rhodojoel.brise.gem.boss.EntityPinkHerk;
import com.rhodojoel.brise.gem.boss.EntityWhiteHerk;
import com.rhodojoel.brise.gem.boss.EntityYellowHerk;
import com.rhodojoel.brise.gem.corrupted.EntityCorruptedZircon;
import com.rhodojoel.brise.gem.possessed.*;
import mod.akrivus.kagic.init.KAGIC;
import mod.akrivus.kagic.init.ModEntities;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.crash.CrashReport;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;

import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

import static mod.akrivus.kagic.init.ModEntities.GENERATE_FACTORIES_INSTEAD_OF_INSTANCES;

public class BriseGems {
	private static int currentID = 0;

	public static void register() {

		ModEntities.registerExternalGem("brise", "lavenderjade", EntityLavenderJade.class, "com/rhodojoel/brise/client/render/RenderLavenderJade", 14524637, 10040012, false);
		ModEntities.registerExternalGem("brise", "redjade", EntityRedJade.class, "com/rhodojoel/brise/client/render/RenderRedJade", 9109504, 10824234, false);
        ModEntities.registerExternalGem("brise", "yellowjade", EntityYellowJade.class, "com/rhodojoel/brise/client/render/RenderYellowJade", 16580533, 16711310, false);
        ModEntities.registerExternalGem("brise", "blackjade", EntityBlackJade.class, "com/rhodojoel/brise/client/render/RenderBlackJade", 8421504, 6908265, false);
        ModEntities.registerExternalGem("brise", "whitejade", EntityWhiteJade.class, "com/rhodojoel/brise/client/render/RenderWhiteJade", 16775930, 16121850, false);
		ModEntities.registerExternalGem("brise", "jellybean", EntityJellybean.class, "com/rhodojoel/brise/client/render/RenderJellybean", 7694460, 10063510, false);
		ModEntities.registerExternalGem("brise", "morganite", EntityMorganite.class, "com/rhodojoel/brise/client/render/RenderMorganite", 15050719, 14848476, false);
		ModEntities.registerExternalGem("brise", "pezz", EntityPezz.class, "com/rhodojoel/brise/client/render/RenderPezz", 16338556, 16339324, false);
		//ModEntities.registerExternalGem("brise", "fluorite", EntityFluorite.class, "com/rhodojoel/brise/client/render/RenderFluorite", 16420862, 7336957, false);
		//ModEntities.registerExternalGem("brise", "mercury", EntityMercury.class, "com/rhodojoel/brise/client/render/RenderMercury", 2171169, 3487029, false);
		//ModEntities.registerExternalGem("brise", "sunstone", EntitySunstone.class, "com/rhodojoel/brise/client/render/RenderSunstone", 16767023, 2892, false);
		//ModEntities.registerExternalGem("brise", "sunsetsodalite", EntitySunsetSodalite.class, "com/rhodojoel/brise/client/render/RenderSunsetSodalite", 16580533, 16711310, false);
		ModEntities.registerExternalGem("brise", "white_herk", EntityWhiteHerk.class, "com/rhodojoel/brise/client/render/RenderWhiteHerk", 16580533, 16711310, false);
		ModEntities.registerExternalGem("brise", "yellow_herk", EntityYellowHerk.class, "com/rhodojoel/brise/client/render/RenderYellowHerk", 16580533, 16711310, false);
		ModEntities.registerExternalGem("brise", "blue_herk", EntityBlueHerk.class, "com/rhodojoel/brise/client/render/RenderBlueHerk", 16580533, 16711310, false);
		ModEntities.registerExternalGem("brise", "pink_herk", EntityPinkHerk.class, "com/rhodojoel/brise/client/render/RenderPinkHerk", 16580533, 16711310, false);
		//ModEntities.registerExternalGem("brise", "pink_herk", EntitySunsetSodalite.class, "com/rhodojoel/brise/client/render/RenderSunsetSodalite", 16580533, 16711310, false);
        ModEntities.registerExternalGem("brise","pos_pearl", EntityControlledPearl.class, "com/rhodojoel/brise/client/render/RenderControlledPearl", 0xFFFFFF, 0xFFFFFF, false);
		ModEntities.registerExternalGem("brise","pos_ruby", EntityPossessedRuby.class, "com/rhodojoel/brise/client/render/RenderControlledRuby", 0xFFFFFF, 0xFFFFFF, false);
		ModEntities.registerExternalGem("brise","pos_lapis", EntityControlledLapis.class, "com/rhodojoel/brise/client/render/RenderControlledLapis", 0xFFFFFF, 0xFFFFFF, false);
		//ModEntities.registerExternalGem("brise","pos_jasper", EntityPossessedJasper.class, "com/rhodojoel/brise/client/render/RenderControlledJasper", 0xFFFFFF, 0xFFFFFF, false);
		ModEntities.registerExternalGem("brise","pos_quartz", EntityPossessedQuartz.class, "com/rhodojoel/brise/client/render/RenderControlledQuartz", 0xFFFFFF, 0xFFFFFF, false);
		ModEntities.registerExternalGem("brise","pos_agate", EntityPossessedAgate.class, "com/rhodojoel/brise/client/render/RenderControlledAgate", 0xFFFFFF, 0xFFFFFF, false);
		//registerMob("peppa", EntityPeppaPig.class, 0xFFFFFF, 0xFFFFFF);
		registerGemAddons();
		registerEntity("beam", EntityDestabalizer.class);
    }
	public static void registerCor() {
		ModEntities.registerExternalGem("brise", "corzir", EntityCorruptedZircon.class, "com/rhodojoel/brise/client/render/RenderCorruptedZircon", 3421236, 2830132, false);
	}
	public static void registerGemAddons() {
		EntityControlledPearl.PEARL_HAIR_STYLES.add(new ResourceLocation("brise:textures/entities/pos_pearl/hair/blue.png"));
		EntityControlledPearl.PEARL_HAIR_STYLES.add(new ResourceLocation("brise:textures/entities/pos_pearl/hair/brows.png"));
		EntityControlledPearl.PEARL_HAIR_STYLES.add(new ResourceLocation("brise:textures/entities/pos_pearl/hair/curly.png"));
		EntityControlledPearl.PEARL_HAIR_STYLES.add(new ResourceLocation("brise:textures/entities/pos_pearl/hair/blue.png"));
		EntityControlledPearl.PEARL_HAIR_STYLES.add(new ResourceLocation("brise:textures/entities/pos_pearl/hair/brows.png"));
		EntityControlledPearl.PEARL_HAIR_STYLES.add(new ResourceLocation("brise:textures/entities/pos_pearl/hair/curly.png"));
		EntityControlledPearl.PEARL_HAIR_STYLES.add(new ResourceLocation("brise:textures/entities/pos_pearl/hair/messy.png"));
		EntityControlledPearl.PEARL_HAIR_STYLES.add(new ResourceLocation("brise:textures/entities/pos_pearl/hair/mop.png"));
		EntityControlledPearl.PEARL_HAIR_STYLES.add(new ResourceLocation("brise:textures/entities/pos_pearl/hair/sides.png"));
		EntityControlledPearl.PEARL_HAIR_STYLES.add(new ResourceLocation("brise:textures/entities/pos_pearl/hair/swoop.png"));
		EntityControlledPearl.PEARL_HAIR_STYLES.add(new ResourceLocation("brise:textures/entities/pos_pearl/hair/yellow.png"));
	}

	@SuppressWarnings({ "unchecked" })
	public static <T extends Entity> void registerEntity(String name, Class<T> entity) {
		EntityRegistry.registerModEntity(new ResourceLocation("brise." + name), entity, "brise." + name, currentID, KAGIC.instance, 256, 1, true);
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
			try {
				Class<Render<? extends T>> render = (Class<Render<? extends T>>) KAGIC.class.getClassLoader().loadClass("com/rhodojoel/brise/client/render/" + entity.getName().replaceAll(".+?Entity", "Render"));
				if (GENERATE_FACTORIES_INSTEAD_OF_INSTANCES) {
					IRenderFactory<T> factory = null;
					try {
						MethodHandles.Lookup lookup = MethodHandles.lookup();
						MethodHandle constructor = lookup.findConstructor(render, MethodType.methodType(void.class, String.class));
						MethodType type = constructor.type().changeReturnType(IRenderFactory.class);
						factory = (IRenderFactory<T>) LambdaMetafactory.metafactory(lookup, "getInstance", MethodType.methodType(IRenderFactory.class), type, constructor, type).getTarget().invokeExact();
					}
					catch (Throwable t) {
						CrashReport.makeCrashReport(t, "Something went wrong registering an entity.");
					}
					RenderingRegistry.registerEntityRenderingHandler(entity, factory);
				}
				else {
					RenderingRegistry.registerEntityRenderingHandler(entity, render.newInstance());
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		++currentID;
	}

	@SuppressWarnings({ "unchecked" })
	public static <T extends Entity> void registerMob(String name, Class<T> entity, int back, int fore) {
		EntityRegistry.registerModEntity(new ResourceLocation("brise." + name), entity, "brise." + name, currentID, KAGIC.instance, 256, 1, true, back, fore);
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
			try {
				Class<Render<? extends T>> render = (Class<Render<? extends T>>) KAGIC.class.getClassLoader().loadClass("com/rhodojoel/brise/client/render/" + entity.getName().replaceAll(".+?Entity", "Render"));
				if (GENERATE_FACTORIES_INSTEAD_OF_INSTANCES) {
					IRenderFactory<T> factory = null;
					try {
						MethodHandles.Lookup lookup = MethodHandles.lookup();
						MethodHandle constructor = lookup.findConstructor(render, MethodType.methodType(void.class, String.class));
						MethodType type = constructor.type().changeReturnType(IRenderFactory.class);
						factory = (IRenderFactory<T>) LambdaMetafactory.metafactory(lookup, "getInstance", MethodType.methodType(IRenderFactory.class), type, constructor, type).getTarget().invokeExact();
					}
					catch (Throwable t) {
						CrashReport.makeCrashReport(t, "Something went wrong registering a mob.");
					}
					RenderingRegistry.registerEntityRenderingHandler(entity, factory);
				}
				else {
					RenderingRegistry.registerEntityRenderingHandler(entity, render.newInstance());
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		++currentID;
	}
}
