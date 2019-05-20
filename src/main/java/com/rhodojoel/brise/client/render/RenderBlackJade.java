package com.rhodojoel.brise.client.render;

import com.rhodojoel.brise.gem.EntityBlackJade;
import mod.akrivus.kagic.client.model.ModelPearl;
import mod.akrivus.kagic.client.render.layers.*;
import mod.akrivus.kagic.client.render.RenderGemBase;
import mod.akrivus.kagic.init.KAGIC;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class RenderBlackJade extends RenderGemBase<EntityBlackJade> {
	public RenderBlackJade() {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelPearl (), 0.5F);
        this.addLayer(new LayerPearlItem(this));
        this.addLayer(new LayerSkin(this));
        this.addLayer(new LayerUniform(this));
        this.addLayer(new LayerInsignia(this));
        this.addLayer(new LayerHair(this));
        this.addLayer(new LayerGemPlacement(this));
		this.addLayer(new LayerVisor(this));
		if (KAGIC.isBirthday()) {
			this.addLayer(new LayerBirthdayHat(this));
		} else if (KAGIC.isHalloween()) {
			this.addLayer(new LayerWitchHat(this));
		} else if (KAGIC.isChristmas()) {
			this.addLayer(new LayerSantaHat(this));
		}
    }	
	@Override
	protected ResourceLocation getEntityTexture(EntityBlackJade entity) {
		return new ResourceLocation("brise:textures/entities/blackjade/blackjade.png");
	}
}