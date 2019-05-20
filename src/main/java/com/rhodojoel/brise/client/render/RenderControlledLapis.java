package com.rhodojoel.brise.client.render;

import com.rhodojoel.brise.client.model.ModelPossessedLapis;
import com.rhodojoel.brise.client.model.ModelPossessedPearl;
import com.rhodojoel.brise.client.render.layers.LayerControlledPearlHair;
import com.rhodojoel.brise.gem.possessed.EntityControlledLapis;
import com.rhodojoel.brise.gem.possessed.EntityControlledPearl;
import mod.akrivus.kagic.client.render.RenderGemBase;
import mod.akrivus.kagic.client.render.layers.*;
import mod.akrivus.kagic.init.KAGIC;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class RenderControlledLapis extends RenderGemBase<EntityControlledLapis> {

	public RenderControlledLapis() {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelPossessedLapis(), 0.5F);
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
	protected ResourceLocation getEntityTexture(EntityControlledLapis entity) {
		return new ResourceLocation("brise:textures/entities/pos_lapis/lapis_lazuli.png");
	}
}