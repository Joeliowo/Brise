package com.rhodojoel.brise.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;

public class BriseSounds {

    public static final SoundEvent JADE_HURT = new SoundEvent(new ResourceLocation("brise:entities.jade.hurt"));
    public static final SoundEvent JADE_DEATH = new SoundEvent(new ResourceLocation("brise:entities.jade.death"));
    public static final SoundEvent JADE_OBEY = new SoundEvent(new ResourceLocation("brise:entities.jade.obey"));
    public static final SoundEvent JADE_INTRO = new SoundEvent(new ResourceLocation("brise:entities.jade.intro"));

    public static final SoundEvent PEBBLE_HURT = new SoundEvent(new ResourceLocation("brise:entities.pebble.hurt"));
    public static final SoundEvent PEBBLE_OBEY = new SoundEvent(new ResourceLocation("brise:entities.pebble.obey"));
    public static final SoundEvent PEBBLE_DEATH = new SoundEvent(new ResourceLocation("brise:entities.pebble.death"));
    public static final SoundEvent PEBBLE_PAPER = new SoundEvent(new ResourceLocation("brise:entities.pebble.paper"));

    public static final SoundEvent PEZZ_HURT = new SoundEvent(new ResourceLocation("brise:entities.pezz.hurt"));
    public static final SoundEvent PEZZ_OBEY = new SoundEvent(new ResourceLocation("brise:entities.pezz.obey"));
    public static final SoundEvent PEZZ_DEATH = new SoundEvent(new ResourceLocation("brise:entities.pezz.death"));
    public static final SoundEvent PEZZ_LIVING = new SoundEvent(new ResourceLocation("brise:entities.pezz.living"));

    public static final SoundEvent WHITE_HURT = new SoundEvent(new ResourceLocation("brise:entities.white_herk.hurt"));
    public static final SoundEvent WHITE_POSSESS = new SoundEvent(new ResourceLocation("brise:entities.white_herk.convertsound"));
    public static final SoundEvent WHITE_POSSESS_SPEECH = new SoundEvent(new ResourceLocation("brise:entities.white_herk.convertspeech"));
    public static final SoundEvent WHITE_LIVING = new SoundEvent(new ResourceLocation("brise:entities.white_herk.living"));
    public static final SoundEvent WHITE_SHIELD = new SoundEvent(new ResourceLocation("brise:entities.white_herk.shield"));

    //public static final SoundEvent FLUORITE_HURT = new SoundEvent(new ResourceLocation("brise:entities.fluorite.hurt"));
    //public static final SoundEvent FLUORITE_OBEY = new SoundEvent(new ResourceLocation("brise:entities.fluorite.obey"));
   // public static final SoundEvent FLUORITE_DEATH = new SoundEvent(new ResourceLocation("brise:entities.fluorite.death"));




    public static void register(RegistryEvent.Register<SoundEvent> event) {

        registerSound(JADE_HURT, new ResourceLocation("brise:entities.jade.hurt"), event);
        registerSound(JADE_DEATH, new ResourceLocation("brise:entities.jade.death"), event);
        registerSound(JADE_OBEY, new ResourceLocation("brise:entities.jade.obey"), event);
        registerSound(JADE_INTRO, new ResourceLocation("brise:entities.jade.intro"), event);

        registerSound(PEBBLE_HURT, new ResourceLocation("brise:entities.pebble.hurt"), event);
        registerSound(PEBBLE_DEATH, new ResourceLocation("brise:entities.pebble.death"), event);
        registerSound(PEBBLE_OBEY, new ResourceLocation("brise:entities.pebble.obey"), event);
        registerSound(PEBBLE_PAPER, new ResourceLocation("brise:entities.pebble.paper"), event);

        registerSound(PEZZ_HURT, new ResourceLocation("brise:entities.pezz.hurt"), event);
        registerSound(PEZZ_DEATH, new ResourceLocation("brise:entities.pezz.death"), event);
        registerSound(PEZZ_OBEY, new ResourceLocation("brise:entities.pezz.obey"), event);
        registerSound(PEZZ_LIVING, new ResourceLocation("brise:entities.pezz.living"), event);

        registerSound(WHITE_HURT, new ResourceLocation("brise:entities.white_herk.hurt"), event);
        registerSound(WHITE_LIVING, new ResourceLocation("brise:entities.white_herk.living"), event);
        registerSound(WHITE_POSSESS, new ResourceLocation("brise:entities.white_herk.convertsound"), event);
        registerSound(WHITE_POSSESS_SPEECH, new ResourceLocation("brise:entities.white_herk.convertspeech"), event);
        registerSound(WHITE_SHIELD, new ResourceLocation("brise:entities.white_herk.shield"), event);

       // registerSound(FLUORITE_HURT, new ResourceLocation("brise:entities.fluorite.hurt"), event);
       // registerSound(FLUORITE_DEATH, new ResourceLocation("brise:entities.fluorite.death"), event);
       // registerSound(FLUORITE_OBEY, new ResourceLocation("brise:entities.fluorite.obey"), event);
    }
    private static void registerSound(SoundEvent sound, ResourceLocation location, RegistryEvent.Register<SoundEvent> event) {
        sound.setRegistryName(location);
        event.getRegistry().register(sound);
    }
}
