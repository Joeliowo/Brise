package com.rhodojoel.brise.client.render;

import com.rhodojoel.brise.client.model.ModelPossessedAgate;
import com.rhodojoel.brise.gem.boss.EntityYellowHerk;
import com.rhodojoel.brise.gem.possessed.EntityPossessedAgate;
import mod.akrivus.kagic.client.model.ModelQuartz;
import mod.akrivus.kagic.client.render.RenderGemBase;
import mod.akrivus.kagic.client.render.layers.*;
import mod.akrivus.kagic.init.KAGIC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class RenderControlledAgate extends RenderGemBase<EntityPossessedAgate> {
	public RenderControlledAgate() {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelPossessedAgate(), 0.5F);
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
	protected ResourceLocation getEntityTexture(EntityPossessedAgate entity) {
		return new ResourceLocation("brise:textures/entities/pos_agate/quartz.png");
	}
}