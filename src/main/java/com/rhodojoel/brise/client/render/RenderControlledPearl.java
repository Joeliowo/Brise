package com.rhodojoel.brise.client.render;

import com.rhodojoel.brise.client.model.ModelPossessedPearl;
import com.rhodojoel.brise.client.render.layers.LayerControlledPearlHair;
import com.rhodojoel.brise.gem.possessed.EntityControlledPearl;
import mod.akrivus.kagic.client.render.RenderGemBase;
import mod.akrivus.kagic.client.render.layers.*;
import mod.akrivus.kagic.init.KAGIC;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class RenderControlledPearl extends RenderGemBase<EntityControlledPearl> {

	public RenderControlledPearl() {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelPossessedPearl(), 0.5F);
        this.addLayer(new LayerControlledPearlHair(this));
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
	protected ResourceLocation getEntityTexture(EntityControlledPearl entity) {
		return new ResourceLocation("brise:textures/entities/pos_pearl/pearl_0.png");
	}
}