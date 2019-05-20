package com.rhodojoel.brise.client.render;

import com.rhodojoel.brise.client.model.ModelMercury;
import com.rhodojoel.brise.gem.EntityMercury;
import mod.akrivus.kagic.client.model.ModelRuby;
import mod.akrivus.kagic.client.render.RenderGemBase;
import mod.akrivus.kagic.client.render.layers.*;
import mod.akrivus.kagic.init.KAGIC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class RenderMercury extends RenderGemBase<EntityMercury> {
	public RenderMercury() {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelRuby(), 0.5F);
        this.addLayer(new LayerSkin(this));
        this.addLayer(new LayerUniform(this));
	    this.addLayer(new LayerInsignia(this));
        this.addLayer(new LayerGemPlacement(this));
		if (KAGIC.isBirthday()) {
			this.addLayer(new LayerBirthdayHat(this));
		} else if (KAGIC.isHalloween()) {
			this.addLayer(new LayerWitchHat(this));
		} else if (KAGIC.isChristmas()) {
			this.addLayer(new LayerSantaHat(this));
		}
    }	
	@Override
	protected ResourceLocation getEntityTexture(EntityMercury entity) {
		return new ResourceLocation("brise:textures/entities/mercury/mercury.png");
	}
}