package com.cartoonishvillain.vdm.components;

import com.cartoonishvillain.vdm.RandomAttackDecider;
import com.cartoonishvillain.vdm.VDM;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

import static com.cartoonishvillain.vdm.components.ComponentStarter.ENTITYINSTANCE;
import static com.cartoonishvillain.vdm.components.ComponentStarter.LEVELINSTANCE;

public class ComponentTicker {

    //TODO: PlayerTickEvent, LivingDeathEvent, LivingHealEvent, BabyEntitySpawnEvent, EntityJoinWorldEvent, LivingUpdateEvent, FurnaceFuelBurnTimeEvent,
    // AnvilRepairEvent, PlayerDestroyItemEvent, Finish Using Item, WorldTick, PlayerWakeUpEvent, Chat event

    public static void LivingDamageMultipliers(LivingEntity victim, DamageSource source, float Amount){
        if(!victim.level.isClientSide){
            //TODO: PANDEMIC
            LevelComponent h = LEVELINSTANCE.get(victim.level.getLevelData());

            if(h.isSoftskin() && victim instanceof Player) softSkin((Player)victim, source, Amount);

            if(h.isBlackeye() && victim instanceof Player) BlackEyeApplication((Player) victim);

            if(h.isBlackeye() && source.getEntity() instanceof Player) BlackEyeRemoval(victim, source);

            if(h.isVenom() && source.getEntity() instanceof LivingEntity) Venom(victim, (LivingEntity) source.getEntity());

            if(h.isKarmicjustice() && source.getEntity() instanceof Player) Retaliate(victim);

            if(h.isKinetic()) Kinetic(victim, source, Amount);

            if(h.isWild() && source.getEntity() instanceof Player player) Wild(victim, player, source);

            if(h.isInferno() && source.equals(DamageSource.ON_FIRE)) Inferno(victim, Amount);

            if(h.isEruptiveswarm() && source.getEntity() != null && source.getEntity().getType().equals(EntityType.BEE)) Eruptive((Bee) source.getEntity());
        }
    }

    public static void Eruptive(Bee aggressor){
        aggressor.level.explode(aggressor, aggressor.getX(), aggressor.getY(), aggressor.getZ(), 4, Explosion.BlockInteraction.NONE);

    }

    public static void Inferno(LivingEntity victim, float amount){
        victim.hurt(DamageSource.IN_FIRE, amount*4);
    }

    public static void Wild(LivingEntity victim, Player aggressor, DamageSource damageSource){
        if(!damageSource.isExplosion() && !damageSource.isFire() && aggressor != victim) {
            if(victim.getRandom().nextInt(100) < 15){
                aggressor.sendMessage(new TranslatableComponent("info.villainousdifficultymultipliers.wild").withStyle(ChatFormatting.DARK_PURPLE), UUID.randomUUID());
                RandomAttackDecider.Activate(victim.level, aggressor, victim);
            }
        }
    }

    public static void Kinetic(LivingEntity victim, DamageSource damageSource, float Amount) {
        if (victim instanceof Player) {
            float damage = Amount;
            if (damageSource.isFire()) {
            } else if (damageSource.isProjectile()) {
                damage = damage * 1.2f;
            } else if (damageSource.isMagic()) {
                damage = damage * 0.5f;
            } else if (damageSource.isExplosion()) {
                damage = damage * 2f;
            } else {
                damage = damage * 1.5f;
            }

            EntityComponent h = ENTITYINSTANCE.get(victim);
            h.setKineticBuildup(damage);
        }

        if(damageSource.getEntity() instanceof Player aggressor){
            EntityComponent h = ENTITYINSTANCE.get(aggressor);
            float bonusDamage = h.getKineticBuildup();
            victim.hurt(DamageSource.FLY_INTO_WALL, bonusDamage);
            h.setKineticBuildup(h.getKineticBuildup() * -1);
        }
    }

    public static void Retaliate(LivingEntity victim){
        EntityType eType = victim.getType();
        if (eType == EntityType.PIG || eType == EntityType.SHEEP || eType == EntityType.COW || eType == EntityType.MOOSHROOM || eType == EntityType.CHICKEN){
            EntityComponent h = ENTITYINSTANCE.get(victim);
            if(h.getRetaliationStatus()){
                victim.level.explode(victim, victim.getX(), victim.getY(), victim.getZ(), 2f, Explosion.BlockInteraction.NONE);
                victim.remove(Entity.RemovalReason.KILLED);
            }
        }
    }

    public static void Venom(LivingEntity victim, LivingEntity aggressor){
        if(aggressor.getType() == EntityType.CAVE_SPIDER){
            Difficulty level = victim.level.getDifficulty();
            if (level == Difficulty.EASY) {
                victim.addEffect(new MobEffectInstance(MobEffects.POISON, 5 * 20, 0));
            } else if (level == Difficulty.NORMAL) {
                victim.addEffect(new MobEffectInstance(MobEffects.WITHER, 2 * 20, 0));
            } else if (level == Difficulty.HARD) {
                victim.addEffect(new MobEffectInstance(MobEffects.WITHER, 5 * 20, 0));
            }
        } else if(aggressor.getType() == EntityType.SPIDER){
            Difficulty level = victim.level.getDifficulty();
            if (level == Difficulty.EASY) {
                victim.addEffect(new MobEffectInstance(MobEffects.POISON, 2 * 20, 0));
            } else if (level == Difficulty.NORMAL) {
                victim.addEffect(new MobEffectInstance(MobEffects.POISON, 4 * 20, 0));
            } else if (level == Difficulty.HARD) {
                victim.addEffect(new MobEffectInstance(MobEffects.POISON, 6 * 20, 0));
            }
        }
    }

    public static void BlackEyeApplication(Player entity){
        EntityComponent h = ENTITYINSTANCE.get(entity);
        h.setBlackEyeStatus(true);
    }

    public static void BlackEyeRemoval(LivingEntity victim, DamageSource damageSource){
        Player aggressor = (Player) damageSource.getEntity();
        if(!damageSource.isExplosion() && !damageSource.isFire() && !damageSource.isMagic() && !damageSource.isProjectile() && aggressor != victim) {//damage source is (probably) melee
            if (victim instanceof Monster || victim instanceof Player) {
                EntityComponent h = ENTITYINSTANCE.get(aggressor);
                    h.setBlackEyeStatus(false);
            }
        }
    }

    public static void softSkin(Player entity, DamageSource source, float amount){
        if (!entity.isInvulnerableTo(source) && !source.isMagic()) {
            amount = (float) (amount*0.5);
            entity.setHealth(entity.getHealth() - amount);
        }


    }

    private static void agecheck(int age, LivingEntity livingEntity){
        if(age >= 4) livingEntity.remove(Entity.RemovalReason.DISCARDED);
    }

    private static Item MusicDisc(){
        ArrayList<Item> music = new ArrayList<Item>(Arrays.asList(Items.MUSIC_DISC_11, Items.MUSIC_DISC_13, Items.MUSIC_DISC_BLOCKS, Items.MUSIC_DISC_CAT, Items.MUSIC_DISC_CHIRP, Items.MUSIC_DISC_FAR, Items.MUSIC_DISC_MALL, Items.MUSIC_DISC_MELLOHI, Items.MUSIC_DISC_STAL, Items.MUSIC_DISC_STRAD, Items.MUSIC_DISC_WAIT, Items.MUSIC_DISC_WARD));
        Random random = new Random();
        int select = random.nextInt(music.size());
        if(select == music.size()) select--;
        return music.get(select);
    }


    private static void broadcast(MinecraftServer server, Component translationTextComponent){
        server.getPlayerList().broadcastMessage(translationTextComponent, ChatType.CHAT, UUID.randomUUID());
    }
}
