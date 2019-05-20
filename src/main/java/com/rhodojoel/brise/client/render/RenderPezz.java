package com.rhodojoel.brise.client.render;

import com.rhodojoel.brise.client.render.layers.LayerPezzItem;
import com.rhodojoel.brise.gem.EntityPezz;
import mod.akrivus.kagic.client.model.fusions.ModelRhodonite;
import mod.akrivus.kagic.client.render.RenderGemBase;
import mod.akrivus.kagic.client.render.layers.*;
import mod.akrivus.kagic.init.KAGIC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.ResourceLocation;

public class RenderPezz extends RenderGemBase<EntityPezz> {
	public RenderPezz() {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelRhodonite(), 0.5F);
        this.addLayer(new LayerSkin(this));
        this.addLayer(new LayerUniform(this));
		this.addLayer(new LayerInsignia(this));
		this.addLayer(new LayerVisor(this));
        this.addLayer(new LayerHair(this));
        this.addLayer(new LayerGemPlacement(this));
        this.addLayer(new LayerPezzItem(this));
		if (KAGIC.isBirthday()) {
			this.addLayer(new LayerBirthdayHat(this));
		} else if (KAGIC.isHalloween()) {
			this.addLayer(new LayerWitchHat(this));
		} else if (KAGIC.isChristmas()) {
			this.addLayer(new LayerSantaHat(this));
		}
    }	
	@Override
	protected ResourceLocation getEntityTexture(EntityPezz entity) {
		return new ResourceLocation("brise:textures/entities/pezz/pezz.png");
	}
	@Override
	protected void preRenderCallback(EntityPezz pezz, float partialTickTime) {
		if (pezz.isDefective()) {
			GlStateManager.scale(0.72F, 0.65F, 0.72F);
		} else if (pezz.isPrimary()) {
			GlStateManager.scale(1.2F, 1.2F, 1.2F);
		}
	}
}