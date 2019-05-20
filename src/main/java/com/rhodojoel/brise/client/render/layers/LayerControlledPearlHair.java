package com.rhodojoel.brise.client.render.layers;

import com.rhodojoel.brise.client.model.ModelPossessedPearl;
import com.rhodojoel.brise.gem.possessed.EntityControlledPearl;
import mod.akrivus.kagic.client.render.RenderGemBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;

import mod.akrivus.kagic.client.model.ModelPearl;
import mod.akrivus.kagic.client.render.RenderGemBase;
import mod.akrivus.kagic.client.render.RenderHoloPearl;
import mod.akrivus.kagic.client.render.RenderPearl;
import mod.akrivus.kagic.entity.gem.EntityPearl;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;

public class LayerControlledPearlHair implements LayerRenderer<EntityControlledPearl> {
    private static final float OFFSET = .0f;
    private final RenderGemBase pearlRenderer;
    private final ModelPossessedPearl pearlModel = new ModelPossessedPearl();

    public LayerControlledPearlHair(RenderGemBase pearlRendererIn) {
        this.pearlRenderer = pearlRendererIn;
    }

    @Override
    public void doRenderLayer(EntityControlledPearl gem, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if (true) {
            this.pearlRenderer.bindTexture(EntityControlledPearl.PEARL_HAIR_STYLES.get(gem.getHairStyle()));
            int hair = gem.generateHairColor();
            float r = (float) ((hair & 16711680) >> 16) / 255f;
            float g = (float) ((hair & 65280) >> 8) / 255f;
            float b = (float) ((hair & 255) >> 0) / 255f;
            //KAGIC.instance.chatInfoMessage("Skin color is " + r + " , " + g + " , " + b);
            GlStateManager.color(r + this.OFFSET, g + this.OFFSET, b + this.OFFSET, 1f);
            this.pearlModel.setModelAttributes(this.pearlRenderer.getMainModel());
            this.pearlModel.render(gem, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        }
    }

    @Override
    public boolean shouldCombineTextures() {
        return true;
    }
}
