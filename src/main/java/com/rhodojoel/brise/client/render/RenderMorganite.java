package com.rhodojoel.brise.client.render;

import com.rhodojoel.brise.client.model.ModelJellybean;
import com.rhodojoel.brise.client.model.ModelPeppaPig;
import com.rhodojoel.brise.gem.EntityJellybean;
import com.rhodojoel.brise.gem.EntityLavenderJade;
import com.rhodojoel.brise.gem.EntityMorganite;
import mod.akrivus.kagic.client.model.ModelAquamarine;
import mod.akrivus.kagic.client.model.ModelPearl;
import mod.akrivus.kagic.client.render.layers.*;
import mod.akrivus.kagic.client.render.RenderGemBase;
import mod.akrivus.kagic.init.KAGIC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class RenderMorganite extends RenderGemBase<EntityMorganite> {
	public RenderMorganite() {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelAquamarine(), 0.5F);
        this.addLayer(new LayerSkin(this));
		this.addLayer(new LayerNoDyeOverlay(this));
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
	protected void preRenderCallback(EntityMorganite entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(0.65F, 0.65F, 0.65F);
	}
	@Override
	protected ResourceLocation getEntityTexture(EntityMorganite entity) {
		return new ResourceLocation("brise:textures/entities/morganite/morganite.png");
	}
}