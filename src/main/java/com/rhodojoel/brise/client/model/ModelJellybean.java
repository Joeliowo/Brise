package com.rhodojoel.brise.client.model;

import mod.akrivus.kagic.client.model.ModelGem;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Jellybean - Rhodonite
 * Created using Tabula 7.0.0
 */
public class ModelJellybean extends ModelGem {
    public ModelRenderer BipedDress;
    public ModelRenderer BipedLeftArm;
    public ModelRenderer BipedRightArm;
    public ModelRenderer BipedTorso;
    public ModelRenderer BipedHead;
    public ModelRenderer BipedHair;
    public ModelRenderer BipedHair2;
    public ModelRenderer BipedHair3;
    public ModelRenderer BipedHair4;
    public ModelRenderer BipedHair5;
    public ModelRenderer BipedHair6;
    public ModelRenderer BipedGemBackHead;
    public ModelRenderer BipedGemNose;
    public ModelRenderer BipedGemMouth;
    public ModelRenderer BipedGemRightEye;
    public ModelRenderer BipedGemLeftEye;
    public ModelRenderer BipedGemChest;

    public ModelJellybean() {
        super(0.0F, 0.0F, 64, 32, false, 7F);
        this.BipedGemBackHead = new ModelRenderer(this, 0, 11);
        this.BipedGemBackHead.setRotationPoint(-1.5F, 9.0F, -0.5F);
        this.BipedGemBackHead.addBox(0.0F, 0.0F, 0.0F, 3, 3, 3, 0.0F);
        this.BipedGemMouth = new ModelRenderer(this, 52, 7);
        this.BipedGemMouth.setRotationPoint(-1.5F, 10.0F, -2.5F);
        this.BipedGemMouth.addBox(0.0F, 0.0F, 0.0F, 3, 3, 3, 0.0F);
        this.BipedHead = new ModelRenderer(this, 20, 11);
        this.BipedHead.setRotationPoint(-3.5F, 8.0F, -1.5F);
        this.BipedHead.addBox(0.0F, 0.0F, 0.0F, 7, 5, 3, 0.0F);
        this.BipedRightArm = new ModelRenderer(this, 48, 0);
        this.BipedRightArm.setRotationPoint(-4.5F, 13.0F, -1.0F);
        this.BipedRightArm.addBox(0.0F, 0.0F, 0.0F, 2, 5, 2, 0.0F);
        this.BipedTorso = new ModelRenderer(this, 24, 0);
        this.BipedTorso.setRotationPoint(-2.5F, 13.0F, -1.5F);
        this.BipedTorso.addBox(0.0F, 0.0F, 0.0F, 5, 5, 3, 0.0F);
        this.BipedHair4 = new ModelRenderer(this, 32, 26);
        this.BipedHair4.setRotationPoint(3.5F, 8.0F, -1.5F);
        this.BipedHair4.addBox(0.0F, 0.0F, 0.0F, 1, 3, 3, 0.0F);
        this.BipedGemLeftEye = new ModelRenderer(this, 9, 18);
        this.BipedGemLeftEye.setRotationPoint(0.5F, 9.5F, -2.5F);
        this.BipedGemLeftEye.addBox(0.0F, 0.0F, 0.0F, 3, 3, 1, 0.0F);
        this.BipedHair5 = new ModelRenderer(this, 40, 23);
        this.BipedHair5.setRotationPoint(-4.5F, 10.0F, -1.5F);
        this.BipedHair5.addBox(0.0F, 0.0F, 0.0F, 1, 3, 3, 0.0F);
        this.BipedHair6 = new ModelRenderer(this, 48, 26);
        this.BipedHair6.setRotationPoint(3.5F, 10.0F, -1.5F);
        this.BipedHair6.addBox(0.0F, 0.0F, 0.0F, 1, 3, 3, 0.0F);
        this.BipedHair = new ModelRenderer(this, 0, 28);
        this.BipedHair.setRotationPoint(-3.5F, 7.0F, -1.5F);
        this.BipedHair.addBox(0.0F, 0.0F, 0.0F, 3, 1, 3, 0.0F);
        this.BipedGemNose = new ModelRenderer(this, 40, 8);
        this.BipedGemNose.setRotationPoint(-1.5F, 9.0F, -2.5F);
        this.BipedGemNose.addBox(0.0F, 0.0F, 0.0F, 3, 3, 3, 0.0F);
        this.BipedHair3 = new ModelRenderer(this, 24, 23);
        this.BipedHair3.setRotationPoint(-4.5F, 8.0F, -1.5F);
        this.BipedHair3.addBox(0.0F, 0.0F, 0.0F, 1, 3, 3, 0.0F);
        this.BipedHair2 = new ModelRenderer(this, 12, 28);
        this.BipedHair2.setRotationPoint(0.5F, 7.0F, -1.5F);
        this.BipedHair2.addBox(0.0F, 0.0F, 0.0F, 3, 1, 3, 0.0F);
        this.BipedGemRightEye = new ModelRenderer(this, 0, 18);
        this.BipedGemRightEye.setRotationPoint(-3.5F, 8.5F, -2.5F);
        this.BipedGemRightEye.addBox(0.0F, 0.0F, 0.0F, 3, 3, 1, 0.0F);
        this.BipedDress = new ModelRenderer(this, 0, 0);
        this.BipedDress.setRotationPoint(-3.5F, 17.8F, -2.5F);
        this.BipedDress.addBox(0.0F, 0.0F, 0.0F, 7, 6, 5, 0.0F);
        this.BipedGemChest = new ModelRenderer(this, 52, 13);
        this.BipedGemChest.setRotationPoint(-1.5F, 13.5F, -2.5F);
        this.BipedGemChest.addBox(0.0F, 0.0F, 0.0F, 3, 3, 3, 0.0F);
        this.BipedLeftArm = new ModelRenderer(this, 40, 0);
        this.BipedLeftArm.setRotationPoint(2.5F, 13.0F, -1.0F);
        this.BipedLeftArm.addBox(0.0F, 0.0F, 0.0F, 2, 5, 2, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.BipedGemBackHead.render(f5);
        this.BipedGemMouth.render(f5);
        this.BipedHead.render(f5);
        this.BipedRightArm.render(f5);
        this.BipedTorso.render(f5);
        this.BipedHair4.render(f5);
        this.BipedGemLeftEye.render(f5);
        this.BipedHair5.render(f5);
        this.BipedHair6.render(f5);
        this.BipedHair.render(f5);
        this.BipedGemNose.render(f5);
        this.BipedHair3.render(f5);
        this.BipedHair2.render(f5);
        this.BipedGemRightEye.render(f5);
        this.BipedDress.render(f5);
        this.BipedGemChest.render(f5);
        this.BipedLeftArm.render(f5);
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
