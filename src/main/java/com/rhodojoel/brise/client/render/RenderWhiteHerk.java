package com.rhodojoel.brise.client.render;

import com.rhodojoel.brise.client.model.ModelWhiteHerk;
import com.rhodojoel.brise.gem.boss.EntityWhiteHerk;
import com.rhodojoel.brise.gem.boss.EntityYellowHerk;
import mod.akrivus.kagic.client.model.ModelQuartz;
import mod.akrivus.kagic.client.render.RenderGemBase;
import mod.akrivus.kagic.client.render.layers.*;
import mod.akrivus.kagic.init.KAGIC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class RenderWhiteHerk extends RenderGemBase<EntityWhiteHerk> {
	public RenderWhiteHerk() {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelWhiteHerk(), 0.5F);
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
	protected ResourceLocation getEntityTexture(EntityWhiteHerk entity) {
		return new ResourceLocation("brise:textures/entities/white_herk/white_herk.png");
	}
	@Override
	protected void preRenderCallback(EntityWhiteHerk pezz, float partialTickTime) {
		GlStateManager.scale(1.4, 1.4, 1.4);
	}
}