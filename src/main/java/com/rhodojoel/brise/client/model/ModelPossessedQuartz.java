package com.rhodojoel.brise.client.model;

import mod.akrivus.kagic.client.model.ModelGem;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

/**
 * quartz - pezz
 * Created using Tabula 7.0.0
 */
public class ModelPossessedQuartz extends ModelGem {
    public ModelRenderer hair;
    public ModelRenderer Body;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer head;
    public ModelRenderer arm1pt1;
    public ModelRenderer arm2pt1;
    //public ModelRenderer hairnubback;
    //public ModelRenderer harinub1;
    //public ModelRenderer hairnub2;
    //public ModelRenderer hairnubtop;
    public ModelRenderer arm1pt2;
    public ModelRenderer shape9;

    public ModelPossessedQuartz() {
        super(0, 0, 64, 64, false, 7);
        this.leg1 = new ModelRenderer(this, 0, 16);
        this.leg1.setRotationPoint(0.0F, 16.0F, 1.0F);
        this.leg1.addBox(0.0F, 0.0F, 0.0F, 4, 12, 4, 0.0F);
        this.leg2 = new ModelRenderer(this, 0, 32);
        this.leg2.setRotationPoint(6.0F, 16.0F, 1.0F);
        this.leg2.addBox(0.0F, 0.0F, 0.0F, 4, 12, 4, 0.0F);
        this.arm2pt1 = new ModelRenderer(this, 48, 40);
        this.arm2pt1.setRotationPoint(8.0F, 3.5F, 1.0F);
        this.arm2pt1.addBox(0.0F, 0.0F, 0.0F, 4, 8, 4, 0.0F);
        this.setRotateAngle(arm2pt1, 0.0F, 0.0F, -1.0471975511965976F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(1.0F, -8.0F, -1.0F);
        this.head.addBox(0.0F, 0.0F, 0.0F, 8, 8, 8, 0.0F);
        this.arm1pt2 = new ModelRenderer(this, 48, 28);
        this.arm1pt2.setRotationPoint(0.9F, 5.5F, 0.0F);
        this.arm1pt2.addBox(0.0F, 0.0F, 0.0F, 4, 8, 4, 0.0F);
        this.setRotateAngle(arm1pt2, 0.0F, 0.0F, 0.6806784082777886F);
        this.Body = new ModelRenderer(this, 16, 16);
        this.Body.setRotationPoint(-5.0F, -4.0F, -3.0F);
        this.Body.addBox(0.0F, 0.0F, 0.0F, 10, 16, 6, 0.0F);
        this.arm1pt1 = new ModelRenderer(this, 48, 16);
        this.arm1pt1.setRotationPoint(0.0F, 0.0F, 1.0F);
        this.arm1pt1.addBox(0.0F, 0.0F, 0.0F, 4, 8, 4, 0.0F);
        this.setRotateAngle(arm1pt1, 0.0F, 0.0F, 1.0471975511965976F);
        this.hair = new ModelRenderer(this, 32, 0);
        this.hair.setRotationPoint(-4.5F, -12.5F, -4.5F);
        this.hair.addBox(0.0F, 0.0F, 0.0F, 8, 8, 8, 1.1F);
        this.shape9 = new ModelRenderer(this, 48, 52);
        this.shape9.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.shape9.addBox(0.0F, 0.0F, 0.0F, 4, 8, 4, 0.0F);
        this.setRotateAngle(shape9, 0.0F, 0.0F, -0.6806784082777886F);
        this.Body.addChild(this.leg1);
        this.Body.addChild(this.leg2);
        this.Body.addChild(this.arm2pt1);
        this.Body.addChild(this.head);
        this.arm1pt1.addChild(this.arm1pt2);
        this.Body.addChild(this.arm1pt1);
        this.arm2pt1.addChild(this.shape9);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Body.render(f5);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.hair.offsetX, this.hair.offsetY, this.hair.offsetZ);
        GlStateManager.translate(this.hair.rotationPointX * f5, this.hair.rotationPointY * f5, this.hair.rotationPointZ * f5);
        GlStateManager.scale(1.1D, 1.1D, 1.1D);
        GlStateManager.translate(-this.hair.offsetX, -this.hair.offsetY, -this.hair.offsetZ);
        GlStateManager.translate(-this.hair.rotationPointX * f5, -this.hair.rotationPointY * f5, -this.hair.rotationPointZ * f5);
        this.hair.render(f5);
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
