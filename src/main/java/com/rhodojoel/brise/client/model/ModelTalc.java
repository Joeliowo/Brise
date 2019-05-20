package com.rhodojoel.brise.client.model;

import mod.akrivus.kagic.client.model.ModelGem;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

/**
 * ModelTalc - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelTalc extends ModelGem {
    public double[] modelScale = new double[] { 0.8D, 0.8D, 0.8D };
    public ModelRenderer Body;
    public ModelRenderer Head;
    public ModelRenderer Backofhead;

    public ModelTalc() {
        super(0.0F, 0.0F, 64, 32, false, 7F);
        this.Body = new ModelRenderer(this, 0, 0);
        this.Body.setRotationPoint(0.0F, 11.9F, 0.0F);
        this.Body.addBox(-4.0F, -5.9F, -3.0F, 8, 14, 6, 0.0F);
        this.Backofhead = new ModelRenderer(this, 28, 15);
        this.Backofhead.setRotationPoint(0.0F, -4.3F, 1.8F);
        this.Backofhead.addBox(-3.0F, -3.1F, -0.5F, 6, 6, 2, 0.0F);
        this.Head = new ModelRenderer(this, 28, 0);
        this.Head.setRotationPoint(0.0F, -5.9F, 0.0F);
        this.Head.addBox(-5.0F, -9.9F, -2.7F, 10, 10, 5, 0.0F);
        this.Head.addChild(this.Backofhead);
        this.Body.addChild(this.Head);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        GlStateManager.pushMatrix();
        GlStateManager.scale(1D / modelScale[0], 1D / modelScale[1], 1D / modelScale[2]);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.Body.offsetX, this.Body.offsetY, this.Body.offsetZ);
        GlStateManager.translate(this.Body.rotationPointX * f5, this.Body.rotationPointY * f5, this.Body.rotationPointZ * f5);
        GlStateManager.scale(1.0D, 0.9D, 1.0D);
        GlStateManager.translate(-this.Body.offsetX, -this.Body.offsetY, -this.Body.offsetZ);
        GlStateManager.translate(-this.Body.rotationPointX * f5, -this.Body.rotationPointY * f5, -this.Body.rotationPointZ * f5);
        this.Body.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.popMatrix();
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
