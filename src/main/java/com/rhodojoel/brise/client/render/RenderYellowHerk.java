package com.rhodojoel.brise.client.render;

import com.rhodojoel.brise.client.render.layers.LayerPezzItem;
import com.rhodojoel.brise.gem.EntityPezz;
import com.rhodojoel.brise.gem.boss.EntityYellowHerk;
import mod.akrivus.kagic.client.model.ModelQuartz;
import mod.akrivus.kagic.client.model.fusions.ModelRhodonite;
import mod.akrivus.kagic.client.render.RenderGemBase;
import mod.akrivus.kagic.client.render.layers.*;
import mod.akrivus.kagic.init.KAGIC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class RenderYellowHerk extends RenderGemBase<EntityYellowHerk> {
	public RenderYellowHerk() {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelQuartz(), 0.5F);
        this.addLayer(new LayerSkin(this));
        this.addLayer(new LayerHair(this));
        this.addLayer(new LayerGemPlacement(this));
		this.addLayer(new LayerNoDyeOverlay(this));
		if (KAGIC.isBirthday()) {
			this.addLayer(new LayerBirthdayHat(this));
		} else if (KAGIC.isHalloween()) {
			this.addLayer(new LayerWitchHat(this));
		} else if (KAGIC.isChristmas()) {
			this.addLayer(new LayerSantaHat(this));
		}
    }	
	@Override
	protected ResourceLocation getEntityTexture(EntityYellowHerk entity) {
		return new ResourceLocation("brise:textures/entities/yellow_herk/yellow.png");
	}
	@Override
	protected void preRenderCallback(EntityYellowHerk pezz, float partialTickTime) {
		GlStateManager.scale(1.4, 1.4, 1.4);
	}
}