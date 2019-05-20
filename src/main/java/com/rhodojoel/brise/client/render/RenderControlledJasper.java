package com.rhodojoel.brise.client.render;

import com.rhodojoel.brise.client.model.ModelPossessedQuartz;
import com.rhodojoel.brise.gem.possessed.EntityPossessedJasper;
import com.rhodojoel.brise.gem.possessed.EntityPossessedQuartz;
import mod.akrivus.kagic.client.render.RenderGemBase;
import mod.akrivus.kagic.client.render.layers.LayerBirthdayHat;
import mod.akrivus.kagic.client.render.layers.LayerGemPlacement;
import mod.akrivus.kagic.client.render.layers.LayerSantaHat;
import mod.akrivus.kagic.client.render.layers.LayerWitchHat;
import mod.akrivus.kagic.init.KAGIC;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class RenderControlledJasper extends RenderGemBase<EntityPossessedJasper> {
	public RenderControlledJasper() {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelPossessedQuartz(), 0.5F);
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
	protected ResourceLocation getEntityTexture(EntityPossessedJasper entity) {
		return new ResourceLocation("brise:textures/entities/pos_jassper/quartz.png");
	}
}