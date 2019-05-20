package com.rhodojoel.brise.client.render;

import com.rhodojoel.brise.client.model.ModelDressGem;
import com.rhodojoel.brise.gem.EntityFluorite;
import mod.akrivus.kagic.client.model.ModelPearl;
import mod.akrivus.kagic.client.render.layers.*;
import mod.akrivus.kagic.client.render.RenderGemBase;
import mod.akrivus.kagic.init.KAGIC;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class RenderFluorite extends RenderGemBase<EntityFluorite> {
	public RenderFluorite() {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelDressGem(), 0.5F);
        this.addLayer(new LayerSkin(this));
        this.addLayer(new LayerInsignia(this));
        this.addLayer(new LayerHair(this));
        this.addLayer(new LayerNoDyeOverlay(this));
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
	protected ResourceLocation getEntityTexture(EntityFluorite entity) {
		return new ResourceLocation("brise:textures/entities/fluorite/fluorite.png");
	}
}