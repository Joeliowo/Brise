package com.rhodojoel.brise.client.model;

import mod.akrivus.kagic.client.model.ModelGem;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelEnderman - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
public class ModelTPoseTest extends ModelGem {
    public ModelRenderer field_178721_j;
    public ModelRenderer field_78116_c;
    public ModelRenderer field_78115_e;
    public ModelRenderer field_178724_i;
    public ModelRenderer field_178722_k;
    public ModelRenderer field_178720_f;
    public ModelRenderer field_178724_i_1;
    public ModelRenderer field_178724_i_2;
    public ModelRenderer field_178724_i_3;

    public ModelTPoseTest() {
        super(0.0F, 0.0F, 64, 32, false, -1F);
        this.field_178724_i_2 = new ModelRenderer(this, 56, 0);
        this.field_178724_i_2.mirror = true;
        this.field_178724_i_2.setRotationPoint(12.0F, 4.0F, 0.0F);
        this.field_178724_i_2.addBox(0.0F, 0.0F, 0.0F, 2, 19, 2, 0.0F);
        this.setRotateAngle(field_178724_i_2, 0.0F, 0.0F, -2.2165681500327987F);
        this.field_178721_j = new ModelRenderer(this, 56, 0);
        this.field_178721_j.setRotationPoint(-2.0F, -5.0F, 0.0F);
        this.field_178721_j.addBox(-1.0F, 0.0F, -1.0F, 2, 30, 2, 0.0F);
        this.field_78116_c = new ModelRenderer(this, 0, 0);
        this.field_78116_c.setRotationPoint(0.0F, -13.0F, -0.0F);
        this.field_78116_c.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.field_78115_e = new ModelRenderer(this, 32, 16);
        this.field_78115_e.setRotationPoint(0.0F, -14.0F, -0.0F);
        this.field_78115_e.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.field_178722_k = new ModelRenderer(this, 56, 0);
        this.field_178722_k.mirror = true;
        this.field_178722_k.setRotationPoint(2.0F, -5.0F, 0.0F);
        this.field_178722_k.addBox(-1.0F, 0.0F, -1.0F, 2, 30, 2, 0.0F);
        this.field_178720_f = new ModelRenderer(this, 0, 16);
        this.field_178720_f.setRotationPoint(0.0F, -13.0F, -0.0F);
        this.field_178720_f.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, -0.5F);
        this.field_178724_i = new ModelRenderer(this, 56, 0);
        this.field_178724_i.mirror = true;
        this.field_178724_i.setRotationPoint(5.0F, -12.0F, 0.0F);
        this.field_178724_i.addBox(-1.0F, -2.0F, -1.0F, 2, 19, 2, 0.0F);
        this.setRotateAngle(field_178724_i, 0.0F, 0.0F, -0.46652650905808424F);
        this.field_178724_i_3 = new ModelRenderer(this, 56, 0);
        this.field_178724_i_3.mirror = true;
        this.field_178724_i_3.setRotationPoint(-12.0F, 2.6F, 0.0F);
        this.field_178724_i_3.addBox(0.0F, 0.0F, 0.0F, 2, 19, 2, 0.0F);
        this.setRotateAngle(field_178724_i_3, 0.0F, 0.0F, 2.2165681500327987F);
        this.field_178724_i_1 = new ModelRenderer(this, 56, 0);
        this.field_178724_i_1.mirror = true;
        this.field_178724_i_1.setRotationPoint(-5.0F, -12.0F, 0.0F);
        this.field_178724_i_1.addBox(-1.0F, -2.0F, -1.0F, 2, 19, 2, 0.0F);
        this.setRotateAngle(field_178724_i_1, 0.0F, -3.001966313430247F, 0.46652650905808424F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.field_178724_i_2.render(f5);
        this.field_178721_j.render(f5);
        this.field_78116_c.render(f5);
        this.field_78115_e.render(f5);
        this.field_178722_k.render(f5);
        this.field_178720_f.render(f5);
        this.field_178724_i.render(f5);
        this.field_178724_i_3.render(f5);
        this.field_178724_i_1.render(f5);
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
