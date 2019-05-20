package com.rhodojoel.brise.client.render;

import com.rhodojoel.brise.client.model.ModelTPoseTest;
import com.rhodojoel.brise.gem.boss.EntityBlueHerk;
import com.rhodojoel.brise.gem.boss.EntityYellowHerk;
import mod.akrivus.kagic.client.model.ModelQuartz;
import mod.akrivus.kagic.client.model.ModelYellowDiamond;
import mod.akrivus.kagic.client.render.RenderGemBase;
import mod.akrivus.kagic.client.render.layers.*;
import mod.akrivus.kagic.init.KAGIC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class RenderBlueHerk extends RenderGemBase<EntityBlueHerk> {
	public RenderBlueHerk() {
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
	protected ResourceLocation getEntityTexture(EntityBlueHerk entity) {
		return new ResourceLocation("brise:textures/entities/blue_herk/blue.png");
	}

	@Override
	protected void preRenderCallback(EntityBlueHerk pezz, float partialTickTime) {
		GlStateManager.scale(1.4, 1.4, 1.4);
	}
}