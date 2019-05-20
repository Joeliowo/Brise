package com.rhodojoel.brise.gem.ai;

import com.rhodojoel.brise.gem.corrupted.EntityCorruptedZircon;
//import com.rhodojoel.brise.init.BriseConfigs;
import com.rhodojoel.brise.init.BriseConfigs;
import com.rhodojoel.brise.misc.Duo;
import mod.akrivus.amalgam.init.Amalgam;
import mod.akrivus.kagic.entity.EntityCorruptedGem;
import mod.akrivus.kagic.entity.EntityGem;
import mod.akrivus.kagic.entity.gem.*;
import mod.akrivus.kagic.entity.gem.corrupted.*;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.Loader;

import java.util.*;

public class EntityAICorruptGems extends EntityAIBase {
    private final EntityGem alignedGem;
    private final double movementSpeed;
    private EntityGem toCorrupt;

    public EntityAICorruptGems(EntityGem alignedGem, double speed) {
        this.alignedGem = alignedGem;
        this.movementSpeed = speed;
        this.setMutexBits(3);
    }

    public static boolean isCorruptable(EntityGem gem){
        return gem instanceof EntityAmethyst || gem instanceof EntityCarnelian || gem instanceof EntityJasper || gem instanceof EntityRoseQuartz || gem instanceof EntityZircon ||
                (Loader.isModLoaded("amalgam") && gem instanceof mod.akrivus.amalgam.gem.EntityTourmaline);
    }

    public static EntityGem corruptedCounterpart(EntityGem gem){
        if(gem instanceof EntityAmethyst){
            return new EntityCorruptedAmethyst(gem.world);
        }
        else if(gem instanceof EntityCarnelian){
            return new EntityCorruptedCarnelian(gem.world);
        }
        else if (gem instanceof EntityRoseQuartz){
            return new EntityCorruptedRoseQuartz(gem.world);
        }
        else if (gem instanceof EntityJasper){
            EntityCorruptedJasper cor = new EntityCorruptedJasper(gem.world);
            cor.setSkinColor(gem.getSkinColor());
            return cor;
        }
        else if (gem instanceof EntityZircon){
            EntityCorruptedZircon zircon = new EntityCorruptedZircon(gem.world);
            zircon.setColor(gem.getInsigniaColor());
            return zircon;
        }
        else if (Loader.isModLoaded("amalgam")){
            if(gem instanceof mod.akrivus.amalgam.gem.EntityTourmaline) {
                return new EntityCorruptedWatermelonTourmaline(gem.world);
            }
            else {
                return null;
            }
        }
        else{
            return new EntityCorruptedWatermelonTourmaline(gem.world);
        }
    }

    @Override
    public boolean shouldExecute() {
        if (this.alignedGem.ticksExisted % BriseConfigs.rateOfCorruptionInMultiples == 0) {
            List<EntityGem> list = this.alignedGem.world.<EntityGem>getEntitiesWithinAABB(EntityGem.class, this.alignedGem.getEntityBoundingBox().grow(24.0D, 8.0D, 24.0D));
            double distance = Double.MAX_VALUE;
            for (EntityGem gem : list) {
               if (isCorruptable(gem) && gem.getServitude() != EntityGem.SERVE_BLUE_DIAMOND && gem.getServitude() != EntityGem.SERVE_YELLOW_DIAMOND) {
                   EntityLightningBolt bolt = new EntityLightningBolt(this.alignedGem.world, gem.posX, gem.posY, gem.posZ, true);
                  this.alignedGem.world.addWeatherEffect(bolt);
                    double newDistance = this.alignedGem.getDistanceSq(gem);
                    if (newDistance <= distance) {
                        distance = newDistance;
                      this.toCorrupt = gem;
                       EntityGem gemmm = corruptedCounterpart(this.toCorrupt);
                        gemmm.setPosition(this.toCorrupt.posX, this.toCorrupt.posY, this.toCorrupt.posZ);
                      this.alignedGem.world.spawnEntity(gemmm);
                        gemmm.onInitialSpawn(this.alignedGem.world.getDifficultyForLocation(this.toCorrupt.getPosition()), null);
                        this.toCorrupt.setDead();
                    }
                }
            }
            return this.toCorrupt != null && this.alignedGem.getNavigator().getPathToEntityLiving(this.toCorrupt) != null;
        }
        return false;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return this.toCorrupt != null
                && !this.toCorrupt.isDead
                && !this.toCorrupt.isTamed()
                /*&& this.alignedGem.canEntityBeSeen(this.toCorrupt)*/
                && !this.alignedGem.getNavigator().noPath();
    }

    @Override
    public void startExecuting() {
        this.alignedGem.getLookHelper().setLookPositionWithEntity(this.toCorrupt, 30.0F, 30.0F);
        this.toCorrupt.moveToBlockPosAndAngles(new BlockPos(this.alignedGem), this.toCorrupt.rotationYaw, this.toCorrupt.rotationPitch);
    }

    @Override
    public void resetTask() {
        this.alignedGem.getNavigator().clearPath();
        this.toCorrupt = null;
    }

    @Override
    public void updateTask() {
        if (this.alignedGem.getDistanceSq(this.toCorrupt) > this.alignedGem.width * 3) {
            this.alignedGem.getNavigator().tryMoveToEntityLiving(this.toCorrupt, this.movementSpeed);
        }
    }
}
