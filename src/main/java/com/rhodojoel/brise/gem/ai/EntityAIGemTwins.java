package com.rhodojoel.brise.gem.ai;

import mod.akrivus.kagic.entity.EntityGem;
import mod.akrivus.kagic.init.ModItems;
import net.minecraft.entity.ai.EntityAIBase;

import java.util.List;

public class EntityAIGemTwins extends EntityAIBase {
    private final EntityGem gem;
    private final double movementSpeed;
    private EntityGem target;

    public EntityAIGemTwins(EntityGem gem, double speed, EntityGem twin) {
        this.gem = gem;
        this.movementSpeed = speed;
        this.target = twin;
        this.setMutexBits(3);
    }

    @Override
    public boolean shouldExecute() {
        return true;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return true;
    }

    @Override
    public void startExecuting() {
        this.gem.getNavigator().tryMoveToEntityLiving(this.target, this.movementSpeed);
    }

    @Override
    public void resetTask() {
        this.gem.getNavigator().clearPath();
        this.target = null;
    }

    @Override
    public void updateTask() {
        if (this.gem.getDistanceSq(this.target) > this.gem.width * 3) {
            this.gem.getNavigator().tryMoveToEntityLiving(this.target, this.movementSpeed);
        }
        else if (this.target.isSitting()) {
            this.target.setSitting(null, false);
        }
    }
}
