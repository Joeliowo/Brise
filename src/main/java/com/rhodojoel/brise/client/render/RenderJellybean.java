package com.rhodojoel.brise.client.render;

import com.rhodojoel.brise.client.model.ModelJellybean;
import com.rhodojoel.brise.client.render.layers.LayerJellyItem;
import com.rhodojoel.brise.gem.EntityJellybean;
import com.rhodojoel.brise.gem.EntityLavenderJade;
import mod.akrivus.kagic.client.model.ModelPearl;
import mod.akrivus.kagic.client.render.layers.*;
import mod.akrivus.kagic.client.render.RenderGemBase;
import mod.akrivus.kagic.init.KAGIC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class RenderJellybean extends RenderGemBase<EntityJellybean> {
	public RenderJellybean() {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelJellybean(), 0.25F);
        this.addLayer(new LayerSkin(this));
        this.addLayer(new LayerUniform(this));
        this.addLayer(new LayerInsignia(this));
        this.addLayer(new LayerHair(this));
        this.addLayer(new LayerGemPlacement(this));
		this.addLayer(new LayerVisor(this));
		this.addLayer(new LayerJellyItem(this));
		if (KAGIC.isBirthday()) {
			this.addLayer(new LayerBirthdayHat(this));
		} else if (KAGIC.isHalloween()) {
			this.addLayer(new LayerWitchHat(this));
		} else if (KAGIC.isChristmas()) {
			this.addLayer(new LayerSantaHat(this));
		}
    }
	@Override
	protected void preRenderCallback(EntityJellybean jellybean, float partialTickTime) {
        GlStateManager.scale(0.5F, 0.5F, 0.5F);
    }

	@Override
	protected ResourceLocation getEntityTexture(EntityJellybean entity) {
		return new ResourceLocation("brise:textures/entities/jellybean/jellybean.png");
	}
}