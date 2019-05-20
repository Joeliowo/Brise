package com.rhodojoel.brise.init;

import com.google.common.base.Predicate;
import com.google.common.eventbus.Subscribe;
import com.rhodojoel.brise.entity.EntityMultiFusionGem;
import com.rhodojoel.brise.gem.*;
import com.rhodojoel.brise.gem.ai.EntityAICorruptGems;
import com.rhodojoel.brise.gem.ai.EntityAIGemTwins;
import mod.akrivus.kagic.blocks.BlockRoseTears;
import mod.akrivus.kagic.entity.EntityFusionGem;
import mod.akrivus.kagic.entity.EntityGem;
import mod.akrivus.kagic.entity.ai.EntityAIFollowGem;
import mod.akrivus.kagic.entity.ai.EntityAIProtectionFuse;
import mod.akrivus.kagic.entity.gem.*;
import mod.akrivus.kagic.entity.gem.corrupted.EntityCorruptedRoseQuartz;
import mod.akrivus.kagic.entity.gem.fusion.EntityRhodonite;
import mod.akrivus.kagic.event.GemSpawnEvent;
import mod.akrivus.kagic.init.ModBlocks;
import mod.akrivus.kagic.init.ModEntities;
import net.minecraft.block.BlockButtonStone;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFollow;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderItemInFrameEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.awt.*;
import java.util.Random;


@Mod.EventBusSubscriber
public class BriseEvents {
    public BriseEvents() {
    }

    public static void register() {
        MinecraftForge.EVENT_BUS.register(new com.rhodojoel.brise.init.BriseEvents());
    }

    public boolean GemTwins(GemSpawnEvent event){
        EntityPlayer closestPlayer = event.world.getClosestPlayer(event.pos.getX(), event.pos.getY(), event.pos.getZ(), 100.0D, false);
        //for (int x = -4; x <= 4; x++) {
            //for (true) {
                //for (true) {
                    if (true) {
                        Random rand = new Random();
                        if (rand.nextInt(56) == 6) {
                            EntityGem pebble = new EntityJellybean(event.world);
                            event.world.spawnEntity(pebble);
                            pebble.onInitialSpawn(event.world.getDifficultyForLocation(event.pos), null);
                            pebble.setPosition(event.pos.getX(), event.pos.getY(), event.pos.getZ());
                            pebble.world.destroyBlock(event.pos, false);
                            //pebble.tasks.addTask(6, new EntityAIGemTwins(pebble, 0.5D, event.gemSpawned));
                            return true;
                        }
                    }
                //}
            //}
        //}
        return false;
    }

    @SubscribeEvent
    public void GemEmergeEvent(GemSpawnEvent event) {
        GemTwins(event);
    }

    @SubscribeEvent
    public void OnGemSpawn(EntityJoinWorldEvent gem){
        if(gem.getEntity() instanceof EntityGem) {
            if(((EntityGem) gem.getEntity()).getGemPlacement() != GemPlacements.MOUTH) {
                if (gem.getEntity() instanceof EntityBlackJade) {
                    EntityBlackJade bj = (EntityBlackJade) gem.getEntity();
                    bj.playSound(BriseSounds.JADE_INTRO, bj.SoundVolume, bj.getSoundPitch());
                }
                if (gem.getEntity() instanceof EntityLavenderJade) {
                    EntityLavenderJade bj = (EntityLavenderJade) gem.getEntity();
                    bj.playSound(BriseSounds.JADE_INTRO, bj.SoundVolume, bj.getSoundPitch());
                }
                if (gem.getEntity() instanceof EntityRedJade) {
                    EntityRedJade bj = (EntityRedJade) gem.getEntity();
                    bj.playSound(BriseSounds.JADE_INTRO, bj.SoundVolume, bj.getSoundPitch());
                }
                if (gem.getEntity() instanceof EntityWhiteJade) {
                    EntityWhiteJade bj = (EntityWhiteJade) gem.getEntity();
                    bj.playSound(BriseSounds.JADE_INTRO, bj.SoundVolume, bj.getSoundPitch());
                }
                if (gem.getEntity() instanceof EntityYellowJade) {
                    EntityYellowJade bj = (EntityYellowJade) gem.getEntity();
                    bj.playSound(BriseSounds.JADE_INTRO, bj.SoundVolume, bj.getSoundPitch());
                }
            }
        }
        if(gem.getEntity() instanceof EntityYellowDiamond && BriseConfigs.enableCorruption){
            EntityYellowDiamond bj = (EntityYellowDiamond) gem.getEntity();
            bj.tasks.addTask(9, new EntityAICorruptGems(bj, 3D));
        }
        if(gem.getEntity() instanceof EntityBlueDiamond && BriseConfigs.enableCorruption){
            EntityBlueDiamond bj = (EntityBlueDiamond) gem.getEntity();
            bj.tasks.addTask(9, new EntityAICorruptGems(bj, 3D));
        }
        if(gem.getEntity() instanceof EntityRoseQuartz){
            ItemStack button = new ItemStack(Blocks.STONE_BUTTON);
            EntityRoseQuartz bj = (EntityRoseQuartz)gem.getEntity();
            bj.ROSE_RECIPES.put(button.getItem(), BriseItems.JELLYBEAN_GEM);
        }
    }

    @SubscribeEvent
    public void Update(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof EntityGem) {
            EntityGem gemy = (EntityGem) event.getEntity();
            if (gemy.getServitude() == EntityGem.SERVE_REBELLION) {
                gemy.setCustomNameTag(generateRebelName());
            }
        }
    }

    @SubscribeEvent
    public void ButtonToJellybean(ItemTossEvent event){
        ItemStack button = new ItemStack(Blocks.STONE_BUTTON);
        if(event.getEntityItem().getItem().getItem() == button.getItem()){
            if(event.getEntityItem().world.getBlockState(event.getEntityItem().getPosition()).getBlock() instanceof BlockRoseTears){
                event.getEntityItem().setDead();
                ItemStack bean = new ItemStack(BriseItems.JELLYBEAN_GEM);
                EntityItem healedGem = new EntityItem(event.getEntityItem().world, event.getEntityItem().posX, event.getEntityItem().posY + 0.5D, event.getEntityItem().posZ, bean);
                event.getEntityItem().world.spawnEntity(healedGem);
            }
        }
    }

    /*@SubscribeEvent
    public void SeedsToPepo(ItemTossEvent event){
        ItemStack button = new ItemStack(Items.MELON_SEEDS);
        if(event.getEntityItem().getItem().getItem() == button.getItem()){
            if(event.getEntityItem().world.getBlockState(event.getEntityItem().getPosition()).getBlock() instanceof BlockRoseTears){
                event.getEntityItem().setDead();
                ItemStack bean = new ItemStack(BriseItems.JELLYBEAN_GEM);
                EntityItem healedGem = new EntityItem(event.getEntityItem().world, event.getEntityItem().posX, event.getEntityItem().posY + 0.5D, event.getEntityItem().posZ, bean);
                event.getEntityItem().world.spawnEntity(healedGem);
            }
        }
    }*/

    public static String generateRebelName(){
        Random rand = new Random();
        switch (rand.nextInt(40)){
            default:
                return "Bone";
            case 0:
                return "Allium";
            case 1:
                return "Azure Bluet";
            case 2:
                return "Blaze Powder";
            case 3:
                return "Blaze Rod";
            case 4:
                return "Bone";
            case 5:
                return "Bonemeal";
            case 6:
                return "Brick";
            case 7:
                return "Charcoal";
            case 8:
                return "Chorus Fruit";
            case 9:
                return "Clay";
            case 10:
                return "Coal";
            case 11:
                return "Cocoa Bean";
            case 12:
                return "Daisy";
            case 13:
                return "Dandelion";
            case 14:
                return "Feather";
            case 15:
                return "Flint";
            case 16:
                return "Ghast Tear";
            case 17:
                return "Glistering Melon";
            case 18:
                return "Glowstone Dust";
            case 19:
                return "Gunpowder";
            case 20:
                return "Leather";
            case 21:
                return "Lilac";
            case 22:
                return "Lily Pad";
            case 23:
                return "Magma Cream";
            case 24:
                return "Mushroom";
            case 25:
                return "Nether Brick";
            case 26:
                return "Nether Wart";
            case 27:
                return "Orchid";
            case 28:
                return "Peony";
            case 29:
                return "Poppy";
            case 30:
                return "Prismarine Crystal";
            case 31:
                return "Prismarine Shard";
            case 32:
                return "Redstone Dust";
            case 33:
                return "Slimeball";
            case 34:
                return "Spider Eye";
            case 35:
                return "Stick";
            case 36:
                return "Sugar";
            case 37:
                return "Sugar Cane";
            case 38:
                return "Sunflower";
            case 39:
                return "Tulip";
        }
    }

    public String getColorFromSpecial(int special){
        switch (special){
            case 0:
                return "White";
            case 1:
                return "Orange";
            case 2:
                return "Magenta";
            case 3:
                return "Light Blue";
            case 4:
                return "Yellow";
            case 5:
                return "Lime";
            case 6:
                return "Pink";
            case 7:
                return "Gray";
            case 8:
                return "Light Gray";
            case 9:
                return "Cyan";
            case 10:
                return "Purple";
            case 11:
                return "Blue";
            case 12:
                return "Brown";
            case 13:
                return "Green";
            case 14:
                return "Red";
            case 15:
                return "Black";
            default:
                return "Blue";
        }
    }

    /*
    APRIL FOOLS STUFF
     */

    @SubscribeEvent
    public void Wolfi(EntityJoinWorldEvent event){
        Random rand = new Random();
        if(BriseConfigs.AprilFools){
            if(event.getEntity() instanceof EntityWolf){
                if(rand.nextBoolean()){
                    event.getEntity().setCustomNameTag("Wolfi");
                }
                else{
                    event.getEntity().setCustomNameTag("Egg");
                }
            }
        }
    }

    @SubscribeEvent
    public void BlueRuby(EntityJoinWorldEvent event){
        if(BriseConfigs.AprilFools){
            if(event.getEntity() instanceof EntitySapphire){
                event.getEntity().setCustomNameTag(this.getColorFromSpecial(((EntitySapphire) event.getEntity()).getSpecial()) + " Ruby");
            }
            else if(event.getEntity() instanceof EntityRuby){
                event.getEntity().setCustomNameTag("Red Sapphire");
            }
        }
    }

    @SubscribeEvent
    public void PinkDiamond(EntityJoinWorldEvent event){
        if(BriseConfigs.AprilFools){
            if(event.getEntity() instanceof EntityRoseQuartz){
                event.getEntity().setCustomNameTag("Pink Diamond");
            }
            else if(event.getEntity() instanceof EntityCorruptedRoseQuartz){
                event.getEntity().setCustomNameTag("Pink Lasagna");
            }
        }
    }

    @SubscribeEvent
    public void LoliBlueAgate(EntityJoinWorldEvent event){
        if(BriseConfigs.AprilFools){
            if(event.getEntity() instanceof EntityAgate){
                if(((EntityAgate) event.getEntity()).isHolly()) {
                    event.getEntity().setCustomNameTag("Loli Blue Agate");
                }
            }
        }
    }

    @SubscribeEvent
    public void PezzBeryl(EntityJoinWorldEvent event){
        if(BriseConfigs.AprilFools){
            if(event.getEntity() instanceof EntityPezz){
                event.getEntity().setCustomNameTag("Raspberry Beryl");
            }
        }
    }

    @SubscribeEvent
    public void CrossFusionNames(EntityJoinWorldEvent event){
        Random rand = new Random();
        if(BriseConfigs.AprilFools){
            if(event.getEntity() instanceof EntityFusionGem){
                if(rand.nextBoolean()) {
                    event.getEntity().setCustomNameTag("Dinnerbone");
                }
                else{
                    event.getEntity().setCustomNameTag("Grumm");
                }
            }
        }
    }

    @SubscribeEvent
    public void CatTheo(EntityJoinWorldEvent event){
        if(BriseConfigs.AprilFools){
            if(event.getEntity() instanceof EntityOcelot){
                event.getEntity().setCustomNameTag("msTheo");
            }
        }
    }

    @SubscribeEvent
    public void Jody(EntityJoinWorldEvent event){
        if(BriseConfigs.AprilFools){
            if(event.getEntity() instanceof EntityLapisLazuli){
                event.getEntity().setCustomNameTag("Jody");
            }
        }
    }

    @SubscribeEvent
    public void Teep(EntityJoinWorldEvent event){
        if(BriseConfigs.AprilFools){
            if(event.getEntity() instanceof EntityHorse){
                event.getEntity().setCustomNameTag("Teep");
            }
        }
    }

    @SubscribeEvent
    public void Rho(EntityJoinWorldEvent event){
        if(BriseConfigs.AprilFools){
            if(event.getEntity() instanceof EntityRhodonite){
                event.getEntity().setCustomNameTag("Rhowo");
            }
        }
    }
}