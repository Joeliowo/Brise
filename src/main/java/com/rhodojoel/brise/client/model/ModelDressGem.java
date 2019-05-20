package com.rhodojoel.brise.client.model;


import mod.akrivus.kagic.client.model.ModelGem;
import mod.akrivus.kagic.init.KAGIC;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelDressGem extends ModelGem {
	private ModelRenderer bipedNose;
	private ModelRenderer bipedDress;
	private ModelRenderer bipedLeftShoulder;
	private ModelRenderer bipedRightShoulder;

	public ModelDressGem() {
		super(0.0F, 0.0F, 64, 64, false, -1F);
		// bipedHead.
		this.bipedHead = new ModelRenderer(this, 0, 0);
		this.bipedHead.addBox(-4F, -8F, -4F, 8, 8, 8);
		this.bipedHead.setRotationPoint(0F, 0F, 0F);
		if (KAGIC.isHalloween() || KAGIC.isBirthday() || KAGIC.isChristmas()) {
			this.bipedHead.addChild(this.witchHat);
		}
		
		// Hair.
		this.bipedHeadwear = new ModelRenderer(this, 32, 0);
		this.bipedHeadwear.addBox(-4F, -8F, -4F, 8, 8, 8, 1.1F);
		this.bipedHeadwear.setRotationPoint(0F, 0F, 0F);
		// Nose.
		this.bipedNose = new ModelRenderer(this, 36, 16);
		this.bipedNose.addBox(-0.5F, -3F, -6F, 1, 1, 2);
		this.bipedNose.setRotationPoint(0F, 0F, 0F);
		// bipedBody.
		this.bipedBody = new ModelRenderer(this, 8, 16);
		this.bipedBody.addBox(-3F, 0F, -2F, 6, 12, 4);
		this.bipedBody.setRotationPoint(0F, 0F, 0F);
		// Dress.
		this.bipedDress = new ModelRenderer(this, 40, 20);
		this.bipedDress.addBox(-3.5F, 7.6F, -2.5F, 7, 17, 5);
		this.bipedDress.setRotationPoint(0F, 0F, 0F);
        // Right arm.
		this.bipedRightArm = new ModelRenderer(this, 0, 16);
		this.bipedRightArm.addBox(0F, 0F, -1F, 2, 12, 2);
		this.bipedRightArm.setRotationPoint(0F, 0F, 0F);
		 // Right shoulder.
		this.bipedRightShoulder = new ModelRenderer(this, 0, 37);
		this.bipedRightShoulder.addBox(-0.4F, -1F, -1.6F, 3, 4, 3);
		this.bipedRightShoulder.setRotationPoint(0F, 0F, 0F);
		// Left arm.
		this.bipedLeftArm = new ModelRenderer(this, 0, 16);
		this.bipedLeftArm.addBox(-2F, 0F, -1F, 2, 12, 2);
		this.bipedLeftArm.setRotationPoint(0F, 0F, 0F);
		this.bipedLeftArm.mirror = true;
		// Left shoulder.
				this.bipedLeftShoulder = new ModelRenderer(this, 0, 37);
				this.bipedLeftShoulder.addBox(-2.5F, -1F, -1.6F, 3, 4, 3);
				this.bipedLeftShoulder.setRotationPoint(0F, 0F, 0F);
		// Right leg.
		this.bipedRightLeg = new ModelRenderer(this, 28, 16);
		this.bipedRightLeg.addBox(1F, 0F, -1F, 2, 12, 2);
		this.bipedRightLeg.setRotationPoint(0F, 12F, 0F);
		// Left leg.
		this.bipedLeftLeg = new ModelRenderer(this, 28, 16);
		this.bipedLeftLeg.addBox(-3F, 0F, -1F, 2, 12, 2);
		this.bipedLeftLeg.setRotationPoint(0F, 12F, 0F);
	}
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		this.bipedHead.render(scale);
		this.bipedHeadwear.render(scale);
		this.bipedNose.render(scale);
		this.bipedBody.render(scale);
		this.bipedDress.render(scale);
		this.bipedRightArm.render(scale);
		this.bipedRightShoulder.render(scale);
		this.bipedLeftArm.render(scale);
		this.bipedLeftShoulder.render(scale);
		this.bipedRightLeg.render(scale);
		this.bipedLeftLeg.render(scale);
	}
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
		super.copyModelAngles(this.bipedHead, this.bipedNose);
    	    super.copyModelAngles(this.bipedBody, this.bipedDress);
    	    super.copyModelAngles(this.bipedRightArm, this.bipedRightShoulder);
    	    super.copyModelAngles(this.bipedLeftArm, this.bipedLeftShoulder);
    			this.bipedBody.rotateAngleY = 0.0F;
    			/*this.bipedRightArm.rotationPointZ = 0.0F;
    			this.bipedRightArm.rotationPointX = -5.0F;
    			this.bipedLeftArm.rotationPointZ = 0.0F;
    			this.bipedLeftArm.rotationPointX = 5.0F;*/
    			float f = 1.0F;
    			if (f < 1.0F)
    			{
    				f = 1.0F;
    			}

    			this.bipedRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.7F * limbSwingAmount / f;
    			this.bipedLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 0.7F * limbSwingAmount / f;
    			this.bipedRightLeg.rotateAngleY = 0.0F;
    			this.bipedLeftLeg.rotateAngleY = 0.0F;
    			this.bipedRightLeg.rotateAngleZ = 0.0F;
    			this.bipedLeftLeg.rotateAngleZ = 0.0F;

    			if (this.isRiding)
    			{
    				this.bipedRightLeg.rotateAngleX = -1.4137167F;
    				this.bipedRightLeg.rotateAngleY = ((float)Math.PI / 10F);
    				this.bipedRightLeg.rotateAngleZ = 0.07853982F;
    				this.bipedLeftLeg.rotateAngleX = -1.4137167F;
    				this.bipedLeftLeg.rotateAngleY = -((float)Math.PI / 10F);
    				this.bipedLeftLeg.rotateAngleZ = -0.07853982F;
    			}

    			 
    		}
    	
	}
