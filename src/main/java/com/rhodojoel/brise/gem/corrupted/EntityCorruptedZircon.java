package com.rhodojoel.brise.gem.corrupted;

import com.rhodojoel.brise.entity.EntityBriseCorruptedGem;
import com.rhodojoel.brise.init.BriseItems;
import mod.akrivus.kagic.entity.gem.GemCuts;
import mod.akrivus.kagic.entity.gem.GemPlacements;
import mod.akrivus.kagic.init.ModItems;
import mod.akrivus.kagic.init.ModSounds;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class EntityCorruptedZircon extends EntityBriseCorruptedGem {

    public EntityCorruptedZircon(World world) {
        super(world);
        this.setSize(0.9F, 2.1F);

        this.setCutPlacement(GemCuts.PILLOW, GemPlacements.FOREHEAD);
        //this.setCutPlacement(GemCuts.PILLOW, GemPlacements.BACK);
        //this.setCutPlacement(GemCuts.PILLOW, GemPlacements.LEFT_THIGH);
        //this.setCutPlacement(GemCuts.PILLOW, GemPlacements.RIGHT_THIGH);

        // Apply entity attributes.
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.75D);

        this.droppedGemItem = BriseItems.CORZIR_GEM;
        this.droppedCrackedGemItem = BriseItems.CRACKED_CORZIR_GEM;
    }

    public void setColor(int ic) {
        this.setInsigniaColor(ic);
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
        int color = this.rand.nextInt(16);
        this.itemDataToGemData(color);
        return super.onInitialSpawn(difficulty, livingdata);
    }

    @Override
    public void itemDataToGemData(int data) {
        this.setInsigniaColor(data);
        this.nativeColor = this.getInsigniaColor();
        this.setUniformColor(data);
        this.setSkinColor(this.generateSkinColor());
        this.setGemColor(this.generateGemColor());
        if (data == 14) {
            this.setCustomNameTag(new TextComponentTranslation("entity.kagic.zircon_14.name").getUnformattedComponentText());
        } else {
            this.setCustomNameTag(new TextComponentTranslation("entity.kagic.zircon.name").getUnformattedComponentText());
        }
    }

    @Override
    public void onDeath(DamageSource cause) {
        switch (this.getInsigniaColor()) {
            case 0:
                this.droppedGemItem = BriseItems.CORZIR_0_GEM;
                this.droppedCrackedGemItem = BriseItems.CRACKED_CORZIR_0_GEM;
                break;
            case 1:
                this.droppedGemItem = BriseItems.CORZIR_1_GEM;
                this.droppedCrackedGemItem = BriseItems.CRACKED_CORZIR_1_GEM;
                break;
            case 2:
                this.droppedGemItem = BriseItems.CORZIR_2_GEM;
                this.droppedCrackedGemItem = BriseItems.CRACKED_CORZIR_2_GEM;
                break;
            case 3:
                this.droppedGemItem = BriseItems.CORZIR_3_GEM;
                this.droppedCrackedGemItem = BriseItems.CRACKED_CORZIR_3_GEM;
                break;
            case 4:
                this.droppedGemItem = BriseItems.CORZIR_4_GEM;
                this.droppedCrackedGemItem = BriseItems.CRACKED_CORZIR_4_GEM;
                break;
            case 5:
                this.droppedGemItem = BriseItems.CORZIR_5_GEM;
                this.droppedCrackedGemItem = BriseItems.CRACKED_CORZIR_5_GEM;
                break;
            case 6:
                this.droppedGemItem = BriseItems.CORZIR_6_GEM;
                this.droppedCrackedGemItem = BriseItems.CRACKED_CORZIR_6_GEM;
                break;
            case 7:
                this.droppedGemItem = BriseItems.CORZIR_7_GEM;
                this.droppedCrackedGemItem = BriseItems.CRACKED_CORZIR_7_GEM;
                break;
            case 8:
                this.droppedGemItem = BriseItems.CORZIR_8_GEM;
                this.droppedCrackedGemItem = BriseItems.CORZIR_8_GEM;
                break;
            case 9:
                this.droppedGemItem = BriseItems.CORZIR_9_GEM;
                this.droppedCrackedGemItem = BriseItems.CRACKED_CORZIR_9_GEM;
                break;
            case 10:
                this.droppedGemItem = BriseItems.CORZIR_10_GEM;
                this.droppedCrackedGemItem = BriseItems.CRACKED_CORZIR_10_GEM;
                break;
            case 11:
                this.droppedGemItem = BriseItems.CORZIR_11_GEM;
                this.droppedCrackedGemItem = BriseItems.CRACKED_CORZIR_11_GEM;
                break;
            case 12:
                this.droppedGemItem = BriseItems.CORZIR_12_GEM;
                this.droppedCrackedGemItem = BriseItems.CRACKED_CORZIR_12_GEM;
                break;
            case 13:
                this.droppedGemItem = BriseItems.CORZIR_13_GEM;
                this.droppedCrackedGemItem = BriseItems.CRACKED_CORZIR_13_GEM;
                break;
            case 14:
                this.droppedGemItem = BriseItems.CORZIR_14_GEM;
                this.droppedCrackedGemItem = BriseItems.CRACKED_CORZIR_14_GEM;
                break;
            case 15:
                this.droppedGemItem = BriseItems.CORZIR_15_GEM;
                this.droppedCrackedGemItem = BriseItems.CRACKED_CORZIR_15_GEM;
                break;
            default:
                this.droppedGemItem = BriseItems.CORZIR_GEM;
                this.droppedCrackedGemItem = BriseItems.CRACKED_CORZIR_GEM;
        }
        super.onDeath(cause);
    }

    /*********************************************************
     * Methods related to sounds.							*
     *********************************************************/
    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.TONGUE_MONSTER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.TONGUE_MONSTER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.TONGUE_MONSTER_DEATH;
    }

    /*********************************************************
     * Methods related to rendering.                         *
     *********************************************************/
    @Override
    protected int generateSkinColor() {
        int colorIndex = this.getInsigniaColor();
        EnumDyeColor color = EnumDyeColor.values()[colorIndex];
        int colorValue = 0;
        try {
            colorValue = ReflectionHelper.getPrivateValue(EnumDyeColor.class, color, "colorValue", "field_193351_w", "w");
        } catch (Exception e) {}
        return colorValue;
    }

    protected int generateGemColor() {
        switch (this.getInsigniaColor()) {
            case 0:
                return 0xFFFFFF;
            case 1:
                return 0xCB7226;
            case 2:
                return 0xAE48D4;
            case 3:
                return 0x215493;
            case 4:
                return 0xFEFE4C;
            case 5:
                return 0x469300;
            case 6:
                return 0xE8759B;
            case 7:
                return 0x939393;
            case 8:
                return 0x8F8F8F;
            case 9:
                return 0x6699B3;
            case 10:
                return 0x7B3BAE;
            case 11:
                return 0x3B54BA;
            case 12:
                return 0x4E341B;
            case 13:
                return 0x4C6519;
            case 14:
                return 0x963030;
            case 15:
                return 0x333333;
        }
        return 0x074464;
    }
}
