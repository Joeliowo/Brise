package com.rhodojoel.brise.client.model;

import mod.akrivus.kagic.client.model.ModelGem;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Peppa Pig - Joel
 * Created using Tabula 7.0.0
 */
public class ModelPeppaPig extends ModelGem {
    /*public ModelRenderer bipedBody;
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer bipedLeftLeg;
    public ModelRenderer bipedRightLeg;
    public ModelRenderer bipedHead;*/
    public ModelRenderer Nose;
    public ModelRenderer Ear1;
    public ModelRenderer Ear2;

    public ModelPeppaPig() {
        super(0, 0, 64, 32, false, 7);
        this.bipedRightLeg = new ModelRenderer(this, 52, 22);
        this.bipedRightLeg.setRotationPoint(1.0F, 17.0F, -1.5F);
        this.bipedRightLeg.addBox(0.0F, 0.0F, 0.0F, 3, 7, 3, 0.0F);
        this.Ear2 = new ModelRenderer(this, 2, 5);
        this.Ear2.setRotationPoint(6.0F, -1.4F, 0.0F);
        this.Ear2.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        this.bipedLeftLeg = new ModelRenderer(this, 52, 22);
        this.bipedLeftLeg.setRotationPoint(-4.0F, 17.0F, -1.5F);
        this.bipedLeftLeg.addBox(0.0F, 0.0F, 0.0F, 3, 7, 3, 0.0F);
        this.bipedHead = new ModelRenderer(this, 32, 0);
        this.bipedHead.setRotationPoint(-4.0F, -1.0F, -4.0F);
        this.bipedHead.addBox(0.0F, 0.0F, 0.0F, 8, 8, 8, 0.0F);
        this.Nose = new ModelRenderer(this, 12, 0);
        this.Nose.setRotationPoint(-2.0F, 0.0F, -10.0F);
        this.Nose.addBox(0.0F, 0.0F, 0.0F, 4, 4, 6, 0.0F);
        this.bipedRightArm = new ModelRenderer(this, 0, 0);
        this.bipedRightArm.setRotationPoint(-8.0F, 7.0F, -1.5F);
        this.bipedRightArm.addBox(0.0F, 0.0F, 0.0F, 3, 9, 3, 0.0F);
        this.bipedBody = new ModelRenderer(this, 0, 12);
        this.bipedBody.setRotationPoint(-5.0F, 7.0F, -5.0F);
        this.bipedBody.addBox(0.0F, 0.0F, 0.0F, 10, 10, 10, 0.0F);
        this.bipedLeftArm = new ModelRenderer(this, 0, 0);
        this.bipedLeftArm.setRotationPoint(5.0F, 7.0F, -1.5F);
        this.bipedLeftArm.addBox(0.0F, 0.0F, 0.0F, 3, 9, 3, 0.0F);
        this.Ear1 = new ModelRenderer(this, 0, 5);
        this.Ear1.setRotationPoint(-3.0F, 0.4F, 0.0F);
        this.Ear1.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        this.bipedHead.addChild(Ear1);
        this.bipedHead.addChild(Ear2);
        this.bipedHead.addChild(Nose);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.bipedRightLeg.render(f5);
        this.Ear2.render(f5);
        this.bipedLeftLeg.render(f5);
        this.bipedHead.render(f5);
        this.Nose.render(f5);
        this.bipedRightArm.render(f5);
        this.bipedBody.render(f5);
        this.bipedLeftArm.render(f5);
        this.Ear1.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
    }
}
