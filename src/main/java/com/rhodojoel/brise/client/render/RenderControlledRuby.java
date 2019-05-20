package com.rhodojoel.brise.client.render;

import com.rhodojoel.brise.client.model.ModelDressGem;
import com.rhodojoel.brise.client.model.ModelPossessedRuby;
import com.rhodojoel.brise.gem.EntityFluorite;
import com.rhodojoel.brise.gem.possessed.EntityPossessedRuby;
import mod.akrivus.kagic.client.render.RenderGemBase;
import mod.akrivus.kagic.client.render.layers.*;
import mod.akrivus.kagic.entity.gem.EntityRuby;
import mod.akrivus.kagic.init.KAGIC;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class RenderControlledRuby extends RenderGemBase<EntityPossessedRuby> {
	public RenderControlledRuby() {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelPossessedRuby(), 0.5F);
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
	protected ResourceLocation getEntityTexture(EntityPossessedRuby entity) {
		return new ResourceLocation("brise:textures/entities/pos_ruby/ruby.png");
	}
}