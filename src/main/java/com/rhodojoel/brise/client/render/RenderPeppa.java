package com.rhodojoel.brise.client.render;

import com.rhodojoel.brise.client.model.ModelPeppaPig;
import com.rhodojoel.brise.entity.EntityPeppaPig;
import mod.akrivus.kagic.client.model.ModelPepo;
import mod.akrivus.kagic.entity.pepo.EntityMelon;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerArrow;
import net.minecraft.util.ResourceLocation;

public class RenderPeppa extends RenderLivingBase<EntityPeppaPig> {
    public RenderPeppa() {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelPeppaPig(), 0.25F);
        this.addLayer(new LayerArrow(this));
    }

    protected void preRenderCallback(EntityPeppaPig entitylivingbaseIn, float partialTickTime) {

    }

    protected ResourceLocation getEntityTexture(EntityPeppaPig entity) {
        return new ResourceLocation("brise:textures/entities/peppa/peppa.png");
    }
}
