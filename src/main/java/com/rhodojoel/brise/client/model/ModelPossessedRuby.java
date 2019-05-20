package com.rhodojoel.brise.client.model;

import mod.akrivus.kagic.client.model.ModelGem;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Ruby - pezz
 * Created using Tabula 7.0.0
 */
public class ModelPossessedRuby extends ModelGem {
    public ModelRenderer Body;
    public ModelRenderer Head;
    public ModelRenderer arm1;
    public ModelRenderer Leg1;
    public ModelRenderer Leg2;
    public ModelRenderer arm2;
    public ModelRenderer arm1pt2;
    public ModelRenderer arm2pt2;

    public ModelPossessedRuby() {
        super(0, 0, 64, 64, false, 7);
        this.Leg1 = new ModelRenderer(this, 0, 36);
        this.Leg1.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.Leg1.addBox(0.0F, 0.0F, 0.0F, 4, 8, 4, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(-2.0F, -11.9F, -2.0F);
        this.Head.addBox(0.0F, 0.0F, 0.0F, 12, 12, 8, 0.0F);
        this.Leg2 = new ModelRenderer(this, 20, 32);
        this.Leg2.setRotationPoint(4.0F, 0.0F, 0.0F);
        this.Leg2.addBox(0.0F, 8.0F, 0.0F, 4, 8, 4, 0.0F);
        this.Body = new ModelRenderer(this, 20, 20);
        this.Body.setRotationPoint(-4.0F, 8.0F, -2.0F);
        this.Body.addBox(0.0F, 0.0F, 0.0F, 8, 8, 4, 0.0F);
        this.arm1pt2 = new ModelRenderer(this, 40, 8);
        this.arm1pt2.setRotationPoint(-2.9F, -1.8F, 0.0F);
        this.arm1pt2.addBox(0.0F, 0.0F, 0.0F, 5, 4, 4, 0.0F);
        this.setRotateAngle(arm1pt2, 0.0F, 0.0F, 0.4363323129985824F);
        this.arm2pt2 = new ModelRenderer(this, 0, 28);
        this.arm2pt2.setRotationPoint(3.3F, 0.4F, 0.0F);
        this.arm2pt2.addBox(0.0F, 0.0F, 0.0F, 5, 4, 4, 0.0F);
        this.setRotateAngle(arm2pt2, 0.0F, 0.0F, -0.4363323129985824F);
        this.arm1 = new ModelRenderer(this, 40, 0);
        this.arm1.setRotationPoint(-5.0F, 0.0F, 0.0F);
        this.arm1.addBox(0.0F, 0.0F, 0.0F, 5, 4, 4, 0.0F);
        this.setRotateAngle(arm1, 0.0F, 0.0F, -0.27052603405912107F);
        this.arm2 = new ModelRenderer(this, 0, 20);
        this.arm2.setRotationPoint(8.2F, -1.3F, 0.0F);
        this.arm2.addBox(0.0F, 0.0F, 0.0F, 5, 4, 4, 0.0F);
        this.setRotateAngle(arm2, 0.0F, 0.0F, 0.27052603405912107F);
        this.Body.addChild(this.Leg1);
        this.Body.addChild(this.Head);
        this.Body.addChild(this.Leg2);
        this.arm1.addChild(this.arm1pt2);
        this.arm2.addChild(this.arm2pt2);
        this.Body.addChild(this.arm1);
        this.Body.addChild(this.arm2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Body.render(f5);
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
