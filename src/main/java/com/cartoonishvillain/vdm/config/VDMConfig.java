package com.cartoonishvillain.vdm.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name="vdm")
public class VDMConfig implements ConfigData {

    @ConfigEntry.Gui.CollapsibleObject
    public DefaultMultipliers defaultMultipliers = new DefaultMultipliers();

    @ConfigEntry.Gui.CollapsibleObject
    public MultiplierTweaks multiplierTweaks = new MultiplierTweaks();


    public static class DefaultMultipliers{
        @Comment("Config options only apply on world creation for the fabric port! For later enabling or disabling in existing worlds, use the commands! /vdm help for more info!")
        public boolean notice = false;
        @Comment("We all get old at some point. Upon breeding a finite number of times, animals and villagers will die of old age.")
        public boolean aging = false;
        @Comment("When you take damage, your ability to heal is nullified until you hit a monster or a player with a melee attack. Punch 'em in the chin!")
        public boolean blackeye = false;
        @Comment("Bombs away! Creepers always explode on death, regardless of method.")
        public boolean cannon = false;
        @Comment("Phantoms are a lame punishment for lack of sleep. Enjoy debuffs and eventual death by lack of sleep with this modifier!")
        public boolean fatigue = false;
        @Comment("There is a chance any animal usually farmed for food will explode violently when a player hits them. Tread Lightly.")
        public boolean karmicjustice = false;
        @Comment("!!!Temporarily Disabled in Code!!! Fighting the same zombies gets old. Zombies are converted to drowned, husks, or zombie villagers instantly, skeletons are converted to strays instantly, and creepers are always supercharged.")
        public boolean shift = false;
        @Comment("Your particularly soft skin makes you more vulnerable to damage. All incoming damage has an extra added to it.")
        public boolean softskin = false;
        @Comment("Cave spiders inflict poison for a brief moment on easy, and both wither and poison on normal and hard. Spiders also get the ability to poison you for a shorter amount of time.")
        public boolean venom = false;
        @Comment("All hostile mobs have increased health")
        public boolean hardened = false;
        @Comment("Pillagers, Skeletons, Strays, and Witches all have significantly sped up attack rates.")
        public boolean anger = false;
        @Comment("Creeper explosions and Ghast fireballs have a larger explosion radius. For all your mass terrain destruction needs.")
        public boolean unstable = false;
        @Comment("Entities on fire will stay on fire until water is applied (or they have died)")
        public boolean flammable = false;
        @Comment("Eating meat makes you feel sick to your stomach. Whether or not it's for moral reasons, health reasons, or anything in between.")
        public boolean vegetarian = false;
        @Comment("That's just... Wrong - Some neutral mobs may have more aggression towards you.")
        public boolean wrong = false;
        @Comment("Burn! BURN! BUUURRRRN! - Fire damage does significantly more damage.")
        public boolean inferno = false;
        @Comment("Bees will explode violently upon stinging.")
        public boolean eruptiveswarm = false;

        @Comment("Future technology embedded into your skin allows you to store up kinetic energy from attacks to release on your foes on your next attack, adding up to 50 hearts of damage maximum.")
        public boolean kinetic = false;
        @Comment("When you are about to die, you're instantly brought back to full health. Allows you to get back into the fight immediately, but does still increase your death counters.")
        public boolean undying = false;
//        @Comment("Modern furnaces can get 4 times the use out of fuel.")
//        public boolean fuelefficient = false;
        @Comment("Stronger understanding of proper anvil usage makes you less likely to damage it.")
        public boolean blacksmithing = false;
        @Comment("Tools may be replaced when they are destroyed.")
        public boolean warranty = false;
        @Comment("Npc villagers may celebrate on any given day")
        public boolean celebration = false;
        @Comment("Resting works wonders to heal you...")
        public boolean rested = false;
        @Comment("Your attacks cause a surge of uncontrollable magic to manifest.. for better or worse.")
        public boolean wild = false;

        @Comment("All living entities that harm you have a chance to infect you with Immortuos Calyx, even if they aren't infected themselves")
        public boolean pandemic = false;
    }

    public static class MultiplierTweaks {
        @Comment("The amount of times a creature can breed before dying of old age with the aging multiplier enabled")
        public int maximumage = 4;
        @Comment("The health multiplier given to monsters when spawned while Hardened is enabled")
        public double hardenedboost = 1.5;
        @Comment("The multiplier given to damage taken by players when SoftSkin is enabled")
        public double damagemultiplier = 1.5;
        @Comment("Explosion size of creepers and ghast fireballs when Unstable is enabled")
        public int unstableexplosionsize = 5;
        @Comment("Explosion size of bees when Eruptive Swarm is enabled")
        public int eruptiveswarmsize = 4;
        @Comment("1 in X chance that a valid neutral mob will be effected by wrong when it is enabled.")
        public int wrongchance = 30;
        @Comment("1 in X chance that a valid passive mob will be effected by karmic justice when it is enabled.")
        public int karmicjusticechance = 20;
        @Comment("1 in X chance that an attack will invoke a wild effect when Wild is enabled.")
        public int wildchance = 6;
        @Comment("1 in X chance during the day to activate a celebration when it is enabled")
        public int celebrationchance = 9;
    }

}
