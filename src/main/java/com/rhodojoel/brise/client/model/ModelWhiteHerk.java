package com.rhodojoel.brise.client.model;

import mod.akrivus.kagic.client.model.ModelGem;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelWhiteHerk - Joel
 * Created using Tabula 7.0.0
 */
public class ModelWhiteHerk extends ModelGem {
    public ModelRenderer Leg1;
    public ModelRenderer Leg2;
    public ModelRenderer Body1;
    public ModelRenderer Shawl;
    public ModelRenderer Body2;
    public ModelRenderer ShoulderPad1;
    public ModelRenderer ShoulderPad2;
    public ModelRenderer Arm1;
    public ModelRenderer Arm1_2;
    public ModelRenderer Arm2;
    public ModelRenderer Arm2_2;
    public ModelRenderer Cloak;
    public ModelRenderer Head;
    public ModelRenderer Neck;
    public ModelRenderer Hair1;
    public ModelRenderer Hair2;
    public ModelRenderer Hair3;
    public ModelRenderer Hair4;
    public ModelRenderer Hair5;

    public ModelWhiteHerk() {
        super(0, 0, 128, 160, false, 7);
        this.Arm1 = new ModelRenderer(this, 108, 0);
        this.Arm1.setRotationPoint(6.8F, -13.0F, -3.0F);
        this.Arm1.addBox(0.0F, 0.0F, 0.0F, 5, 11, 5, 0.0F);
        this.setRotateAngle(Arm1, 0.0F, 0.0F, -1.5184364492350666F);
        this.Hair3 = new ModelRenderer(this, 66, 128);
        this.Hair3.setRotationPoint(-6.0F, -32.0F, -6.0F);
        this.Hair3.addBox(0.0F, 0.0F, 0.0F, 12, 12, 12, 0.0F);
        this.setRotateAngle(Hair3, 0.7853981633974483F, 0.0F, 0.0F);
        this.Cloak = new ModelRenderer(this, 14, 66);
        this.Cloak.setRotationPoint(-27.5F, -13.5F, 1.1F);
        this.Cloak.addBox(0.0F, 0.0F, 0.0F, 56, 37, 1, 0.0F);
        this.setRotateAngle(Cloak, 0.23649211364523165F, 0.0F, 0.0F);
        this.Leg2 = new ModelRenderer(this, 37, 0);
        this.Leg2.setRotationPoint(1.0F, 1.0F, -4.0F);
        this.Leg2.addBox(0.0F, 0.0F, 0.0F, 7, 23, 7, 0.0F);
        this.Arm2 = new ModelRenderer(this, 85, 0);
        this.Arm2.setRotationPoint(-7.0F, -17.7F, -3.0F);
        this.Arm2.addBox(0.0F, 0.0F, 0.0F, 5, 11, 5, 0.0F);
        this.setRotateAngle(Arm2, 0.0F, 0.0F, 1.5184364492350666F);
        this.Neck = new ModelRenderer(this, 48, 122);
        this.Neck.setRotationPoint(-2.0F, -20.0F, -2.0F);
        this.Neck.addBox(0.0F, 0.0F, 0.0F, 4, 2, 4, 0.0F);
        this.ShoulderPad2 = new ModelRenderer(this, 94, 37);
        this.ShoulderPad2.setRotationPoint(-9.0F, -20.5F, -4.0F);
        this.ShoulderPad2.addBox(0.0F, 0.0F, 0.0F, 5, 3, 7, 0.0F);
        this.setRotateAngle(ShoulderPad2, 0.0F, 0.0F, 0.5235987755982988F);
        this.Shawl = new ModelRenderer(this, 0, 31);
        this.Shawl.setRotationPoint(-5.0F, -6.0F, -5.1F);
        this.Shawl.addBox(0.0F, 0.0F, 0.0F, 10, 24, 9, 0.0F);
        this.ShoulderPad1 = new ModelRenderer(this, 70, 37);
        this.ShoulderPad1.setRotationPoint(4.5F, -18.0F, -4.0F);
        this.ShoulderPad1.addBox(0.0F, 0.0F, 0.0F, 5, 3, 7, 0.0F);
        this.setRotateAngle(ShoulderPad1, 0.0F, 0.0F, -0.5235987755982988F);
        this.Hair2 = new ModelRenderer(this, 0, 128);
        this.Hair2.setRotationPoint(-6.0F, -32.0F, -6.0F);
        this.Hair2.addBox(0.0F, 0.0F, 0.0F, 12, 12, 12, 0.0F);
        this.setRotateAngle(Hair2, 0.0F, 5.235987755982989F, 0.0F);
        this.Arm1_2 = new ModelRenderer(this, 108, 17);
        this.Arm1_2.setRotationPoint(17.0F, -12.4F, -3.0F);
        this.Arm1_2.addBox(0.0F, 0.0F, 0.0F, 5, 12, 5, 0.0F);
        this.setRotateAngle(Arm1_2, 0.0F, 0.0F, -1.6231562043547265F);
        this.Hair1 = new ModelRenderer(this, 64, 104);
        this.Hair1.mirror = true;
        this.Hair1.setRotationPoint(6.0F, -32.0F, -6.0F);
        this.Hair1.addBox(0.0F, 0.0F, 0.0F, 12, 12, 12, 0.0F);
        this.setRotateAngle(Hair1, 0.0F, -0.5235987755982988F, 0.0F);
        this.Leg1 = new ModelRenderer(this, 0, 0);
        this.Leg1.setRotationPoint(-8.0F, 1.0F, -4.0F);
        this.Leg1.addBox(0.0F, 0.0F, 0.0F, 7, 23, 7, 0.0F);
        this.Arm2_2 = new ModelRenderer(this, 85, 17);
        this.Arm2_2.setRotationPoint(-17.0F, -17.1F, -3.0F);
        this.Arm2_2.addBox(0.0F, 0.0F, 0.0F, 5, 11, 5, 0.0F);
        this.setRotateAngle(Arm2_2, 0.0F, 0.0F, 1.6231562043547265F);
        this.Hair4 = new ModelRenderer(this, 38, 31);
        this.Hair4.mirror = true;
        this.Hair4.setRotationPoint(-6.0F, -32.0F, -6.0F);
        this.Hair4.addBox(0.0F, 0.0F, 0.0F, 8, 8, 8, 0.0F);
        this.setRotateAngle(Hair4, -2.705260340591211F, -2.705260340591211F, -0.7853981633974483F);
        this.Hair5 = new ModelRenderer(this, 41, 144);
        this.Hair5.setRotationPoint(11.0F, -27.0F, -2.0F);
        this.Hair5.addBox(0.0F, 0.0F, 0.0F, 8, 8, 8, 0.0F);
        this.setRotateAngle(Hair5, -2.6179938779914944F, 2.6179938779914944F, 0.7853981633974483F);
        this.Head = new ModelRenderer(this, 0, 104);
        this.Head.setRotationPoint(-6.0F, -32.0F, -6.0F);
        this.Head.addBox(0.0F, 0.0F, 0.0F, 12, 12, 12, 0.0F);
        this.Body2 = new ModelRenderer(this, 90, 48);
        this.Body2.setRotationPoint(-7.0F, -18.0F, -3.0F);
        this.Body2.addBox(0.0F, 0.0F, 0.0F, 14, 12, 5, 0.0F);
        this.Body1 = new ModelRenderer(this, 41, 51);
        this.Body1.setRotationPoint(-8.0F, -6.0F, -4.0F);
        this.Body1.addBox(0.0F, 0.0F, 0.0F, 16, 7, 7, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Arm1.render(f5);
        this.Hair3.render(f5);
        this.Cloak.render(f5);
        this.Leg2.render(f5);
        this.Arm2.render(f5);
        this.Neck.render(f5);
        this.ShoulderPad2.render(f5);
        this.Shawl.render(f5);
        this.ShoulderPad1.render(f5);
        this.Hair2.render(f5);
        this.Arm1_2.render(f5);
        this.Hair1.render(f5);
        this.Leg1.render(f5);
        this.Arm2_2.render(f5);
        this.Hair4.render(f5);
        this.Hair5.render(f5);
        this.Head.render(f5);
        this.Body2.render(f5);
        this.Body1.render(f5);
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
