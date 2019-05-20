package com.rhodojoel.brise.entity;

import mod.akrivus.kagic.entity.EntityGem;
import net.minecraft.world.World;

public class EntityControlledGem extends EntityGem {
    public EntityControlledGem(World worldIn) {
        super(worldIn);
        this.setServitude(SERVE_WHITE_DIAMOND);
    }

    @Override
    public int generateGemColor(){
        return 0xFFFFFF;
    }

    @Override
    public int generateSkinColor(){
        return 0xFFFFFF;
    }

    @Override
    public int generateHairColor(){
        return 0xFFFFFF;
    }
}
