package com.rhodojoel.brise.client.render;

import com.rhodojoel.brise.client.model.ModelSunstone;
import com.rhodojoel.brise.client.render.layers.LayerPezzItem;
import com.rhodojoel.brise.client.render.layers.LayerSunstoneItem;
import com.rhodojoel.brise.gem.EntityPezz;
import com.rhodojoel.brise.gem.EntitySunstone;
import mod.akrivus.amalgam.client.model.ModelEmerald;
import mod.akrivus.amalgam.client.render.layers.LayerEmeraldItem;
import mod.akrivus.amalgam.gem.EntityEmerald;
import mod.akrivus.kagic.client.model.fusions.ModelRhodonite;
import mod.akrivus.kagic.client.render.RenderGemBase;
import mod.akrivus.kagic.client.render.layers.*;
import mod.akrivus.kagic.init.KAGIC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.ResourceLocation;

public class RenderSunstone extends RenderGemBase<EntitySunstone> {
    public RenderSunstone() {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelSunstone(), 0.5F);
        this.addLayer(new LayerSunstoneItem(this));
        this.addLayer(new LayerSkin(this));
        this.addLayer(new LayerUniform(this));
        this.addLayer(new LayerInsignia(this));
        this.addLayer(new LayerVisor(this));
        this.addLayer(new LayerHair(this));
        this.addLayer(new LayerNoDyeOverlay(this));
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
        protected ResourceLocation getEntityTexture (EntitySunstone entity){
            return new ResourceLocation("brise:textures/entities/sunstone/sunstone.png");
        }

    @Override
    protected void preRenderCallback(EntitySunstone sunstone, float partialTickTime) {
        if (sunstone.isDefective()) {
            GlStateManager.scale(0.72F, 0.65F, 0.72F);
        } else if (sunstone.isPrimary()) {
            GlStateManager.scale(1.2F, 1.2F, 1.2F);
        }
    }
}