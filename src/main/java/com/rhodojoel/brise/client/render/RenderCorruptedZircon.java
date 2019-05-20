package com.rhodojoel.brise.client.render;

import com.rhodojoel.brise.gem.corrupted.EntityCorruptedZircon;
import mod.akrivus.kagic.client.model.corrupted.ModelTongueMonster;
import mod.akrivus.kagic.client.render.RenderGemBase;
import mod.akrivus.kagic.client.render.layers.LayerGemPlacement;
import mod.akrivus.kagic.client.render.layers.LayerSkin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class RenderCorruptedZircon extends RenderGemBase<EntityCorruptedZircon> {
    public RenderCorruptedZircon() {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelTongueMonster(), 0.75F);


        this.addLayer(new LayerSkin(this, 0F, "corrupted/zircon"));
        this.addLayer(new LayerGemPlacement(this, "corrupted/zircon"));
		/*
		if (KAGIC.isBirthday()) {
			this.addLayer(new LayerBirthdayHat(this));
		} else if (KAGIC.isHalloween()) {
			this.addLayer(new LayerWitchHat(this));
		}*/
    }

    @Override
    protected void preRenderCallback(EntityCorruptedZircon tongueMonster, float partialTickTime) {
        GlStateManager.scale(1.5F, 1.5F, 1.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityCorruptedZircon tongueMonster) {
        return new ResourceLocation("brise:textures/entities/corrupted/zircon/zircon.png");
    }
}
