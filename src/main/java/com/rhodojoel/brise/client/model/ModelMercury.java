package com.rhodojoel.brise.client.model;

import mod.akrivus.kagic.client.model.ModelGem;
import mod.akrivus.kagic.init.KAGIC;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelMercury extends ModelGem {
	public ModelRenderer Chest;
	public ModelRenderer Abdomen;
	public ModelRenderer LeftShoulder;
	public ModelRenderer RightShoulder;
	public ModelRenderer LowerAbdomen;
	public ModelRenderer Neck;
	public ModelRenderer LeftLeg;
	public ModelRenderer RightLeg;
	public ModelRenderer Head;
	public ModelRenderer BottomHat;
	public ModelRenderer TopHat;
	public ModelRenderer LeftArm;
	public ModelRenderer RightArm;

    public ModelMercury() {
        super(0.0F, 0.0F, 64, 64, false, 7F);
		this.Head = new ModelRenderer(this, 25, 10);
		this.Head.mirror = true;
		this.Head.setRotationPoint(0.0F, -5.4F, 0.0F);
		this.Head.addBox(-3.5F, -3.5F, -3.5F, 7, 7, 7, 0.0F);
		this.Neck = new ModelRenderer(this, 25, 24);
		this.Neck.mirror = true;
		this.Neck.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Neck.addBox(-2.0F, -2.0F, -2.0F, 4, 2, 4, 0.0F);
		this.Chest = new ModelRenderer(this, 15, 30);
		this.Chest.mirror = true;
		this.Chest.setRotationPoint(0.0F, -0.5F, 0.0F);
		this.Chest.addBox(-4.5F, -1.0F, -4.0F, 9, 8, 8, 0.0F);
		this.RightArm = new ModelRenderer(this, 2, 32);
		this.RightArm.mirror = true;
		this.RightArm.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.RightArm.addBox(-3.45F, 4.0F, -0.8F, 3, 10, 3, 0.0F);
		this.LeftShoulder = new ModelRenderer(this, 41, 24);
		this.LeftShoulder.mirror = true;
		this.LeftShoulder.setRotationPoint(4.0F, -2.0F, 0.0F);
		this.LeftShoulder.addBox(0.0F, 0.0F, -1.25F, 4, 4, 4, 0.0F);
		this.LeftLeg = new ModelRenderer(this, 45, 46);
		this.LeftLeg.mirror = true;
		this.LeftLeg.setRotationPoint(0.0F, 10.6F, 0.0F);
		this.LeftLeg.addBox(0.5F, 0.0F, -1.9F, 3, 14, 4, 0.0F);
		this.BottomHat = new ModelRenderer(this, 0, 0);
		this.BottomHat.mirror = true;
		this.BottomHat.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.BottomHat.addBox(-4.0F, -3.5F, -4.0F, 8, 1, 8, 0.0F);
		this.LeftArm = new ModelRenderer(this, 49, 32);
		this.LeftArm.mirror = true;
		this.LeftArm.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.LeftArm.addBox(0.75F, 4.0F, -0.8F, 3, 10, 3, 0.0F);
		this.LowerAbdomen = new ModelRenderer(this, 19, 55);
		this.LowerAbdomen.mirror = true;
		this.LowerAbdomen.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.LowerAbdomen.addBox(-3.5F, 9.0F, -3.0F, 7, 2, 6, 0.0F);
		this.Abdomen = new ModelRenderer(this, 20, 46);
		this.Abdomen.mirror = true;
		this.Abdomen.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Abdomen.addBox(-3.0F, 7.0F, -3.0F, 6, 3, 6, 0.0F);
		this.RightShoulder = new ModelRenderer(this, 7, 24);
		this.RightShoulder.mirror = true;
		this.RightShoulder.setRotationPoint(-4.4F, -2.0F, 0.0F);
		this.RightShoulder.addBox(-3.75F, 0.0F, -1.25F, 4, 4, 4, 0.0F);
		this.TopHat = new ModelRenderer(this, 0, 9);
		this.TopHat.mirror = true;
		this.TopHat.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.TopHat.addBox(-3.0F, -9.0F, -3.0F, 6, 6, 6, 0.0F);
		this.RightLeg = new ModelRenderer(this, 5, 46);
		this.RightLeg.mirror = true;
		this.RightLeg.setRotationPoint(0.0F, 10.6F, 0.0F);
		this.RightLeg.addBox(-3.5F, 0.0F, -1.9F, 3, 14, 4, 0.0F);
		this.Neck.addChild(this.Head);
		this.Abdomen.addChild(this.Neck);
		this.RightShoulder.addChild(this.RightArm);
		this.Chest.addChild(this.LeftShoulder);
		this.LowerAbdomen.addChild(this.LeftLeg);
		this.Head.addChild(this.BottomHat);
		this.LeftShoulder.addChild(this.LeftArm);
		this.Abdomen.addChild(this.LowerAbdomen);
		this.Chest.addChild(this.Abdomen);
		this.Chest.addChild(this.RightShoulder);
		this.BottomHat.addChild(this.TopHat);
		this.LowerAbdomen.addChild(this.RightLeg);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		this.Chest.render(f5);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y,
			float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3,
			float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.Head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.Head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.LeftLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.RightLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.RightShoulder.rotateAngleX = MathHelper.cos(f * 0.6662F
				+ (float) Math.PI)
				* 2.0F * f1 * 0.5F;
		this.LeftShoulder.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F
				* f1 * 0.5F;
	}
}
