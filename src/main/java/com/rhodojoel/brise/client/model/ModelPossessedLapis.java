package com.rhodojoel.brise.client.model;

import mod.akrivus.kagic.client.model.ModelGem;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Pearl - Pezzo
 * Created using Tabula 7.0.0
 */
public class ModelPossessedLapis extends ModelGem {
    public ModelRenderer Body;
    public ModelRenderer Hair;
    public ModelRenderer onearm;
    public ModelRenderer twoarm;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer Head;
    public ModelRenderer shape10;
    public ModelRenderer shape9;

    public ModelPossessedLapis() {
        super(0, 0, 64, 128, false, 7F);
        this.onearm = new ModelRenderer(this, 0, 16);
        this.onearm.setRotationPoint(0.0F, 0.0F, 1.0F);
        this.onearm.addBox(0.0F, 0.0F, 0.0F, 2, 7, 2, 0.0F);
        this.setRotateAngle(onearm, 0.0F, 0.0F, 1.3089969389957472F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(-1.0F, -8.0F, -2.0F);
        this.Head.addBox(0.0F, 0.0F, 0.0F, 8, 8, 8, 0.0F);
        this.shape9 = new ModelRenderer(this, 0, 34);
        this.shape9.setRotationPoint(1.7F, 6.0F, 0.0F);
        this.shape9.addBox(0.0F, 0.0F, 0.0F, 6, 2, 2, 0.0F);
        this.setRotateAngle(shape9, 0.0F, 0.0F, 1.0471975511965976F);
        this.leg1 = new ModelRenderer(this, 28, 16);
        this.leg1.setRotationPoint(0.0F, 12.0F, 1.0F);
        this.leg1.addBox(0.0F, 0.0F, 0.0F, 2, 12, 2, 0.0F);
        this.Body = new ModelRenderer(this, 8, 16);
        this.Body.setRotationPoint(-3.0F, 0.0F, -2.0F);
        this.Body.addBox(0.0F, 0.0F, 0.0F, 6, 12, 4, 0.0F);
        this.twoarm = new ModelRenderer(this, 0, 16);
        this.twoarm.setRotationPoint(5.5F, 2.0F, 1.0F);
        this.twoarm.addBox(0.0F, 0.0F, 0.0F, 2, 7, 2, 0.0F);
        this.setRotateAngle(twoarm, 0.0F, 0.0F, -1.3089969389957472F);
        this.Hair = new ModelRenderer(this, 32, 0);
        this.Hair.setRotationPoint(-4.0F, -8.0F, -4.0F);
        this.Hair.addBox(0.0F, 0.0F, 0.0F, 8, 8, 8, 0.5F);
        this.leg2 = new ModelRenderer(this, 28, 16);
        this.leg2.setRotationPoint(4.0F, 12.0F, 1.0F);
        this.leg2.addBox(0.0F, 0.0F, 0.0F, 2, 12, 2, 0.0F);
        this.shape10 = new ModelRenderer(this, 0, 34);
        this.shape10.setRotationPoint(-2.8F, 11.2F, 0.0F);
        this.shape10.addBox(0.0F, 0.0F, 0.0F, 6, 2, 2, 0.0F);
        this.setRotateAngle(shape10, 0.0F, 0.0F, -1.0471975511965976F);
        this.Body.addChild(this.onearm);
        this.Body.addChild(this.Head);
        this.twoarm.addChild(this.shape9);
        this.Body.addChild(this.leg1);
        this.Body.addChild(this.twoarm);
        this.Body.addChild(this.leg2);
        this.onearm.addChild(this.shape10);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Body.render(f5);
        this.Hair.render(f5);
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
