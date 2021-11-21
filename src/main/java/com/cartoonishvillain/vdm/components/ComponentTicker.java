package com.cartoonishvillain.vdm.components;

import com.cartoonishvillain.vdm.Fatiguedamage;
import com.cartoonishvillain.vdm.RandomAttackDecider;
import com.cartoonishvillain.vdm.goals.CrossbowAngerManagement;
import com.cartoonishvillain.vdm.goals.RangedAngerManagment;
import com.cartoonishvillain.vdm.mixin.*;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.ServerStatsCounter;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.*;

import static com.cartoonishvillain.vdm.components.ComponentStarter.ENTITYINSTANCE;
import static com.cartoonishvillain.vdm.components.ComponentStarter.LEVELINSTANCE;

public class ComponentTicker {

    //TODO: LivingUpdateEvent, FurnaceFuelBurnTimeEvent,
    // AnvilRepairEvent, PlayerDestroyItemEvent, Finish Using Item, WorldTick, PlayerWakeUpEvent, Chat event

    public static void SpawnMultipliers(Entity entity){
        if(entity instanceof LivingEntity && !entity.level.isClientSide){
            LevelComponent h = LEVELINSTANCE.get(entity.level.getLevelData());

            seedRetaliation((LivingEntity) entity);

            if(h.isHardened() && entity instanceof Monster) Hardened((LivingEntity) entity);

            if(h.isAnger()) Anger((LivingEntity) entity);

            if(h.isUnstable() && (entity.getType().equals(EntityType.GHAST) || entity.getType().equals(EntityType.CREEPER))) Unstable((LivingEntity) entity);

            seedWrong((LivingEntity) entity);
        }

    }



    public static void Aging(LivingEntity victim1, LivingEntity victim2){
        EntityComponent h = ENTITYINSTANCE.get(victim1);
        h.setAge(h.getAge()+1);
        agecheck(h.getAge(), victim1);
        h = ENTITYINSTANCE.get(victim2);
        h.setAge(h.getAge()+1);
        agecheck(h.getAge(), victim2);
    }

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

    public static void PlayerTickMethod(Player victim){
        LevelComponent h = LEVELINSTANCE.get(victim.level.getLevelData());
        if(!victim.level.isClientSide) {
            if (h.isFatigue()) Fatigue(victim);

            shoutTicksCount(victim);
        }
    }

    public static void LivingDeathMultipliers(LivingEntity victim, DamageSource damageSource, CallbackInfo ci){
        LevelComponent h = LEVELINSTANCE.get(victim.level.getLevelData());
        if(!victim.level.isClientSide) {
            if (victim instanceof Player && h.isUndying()){
                ci.cancel();
                undying((Player) victim);
            }

            if(victim.getType().equals(EntityType.CREEPER) && h.isCannon()){
                Cannon((Creeper) victim, damageSource);
            }
        }
    }

    public static void LivingHealthMultipliers(LivingEntity victim, float f, CallbackInfo ci){
        LevelComponent h = LEVELINSTANCE.get(victim.level.getLevelData());
        if(h.isBlackeye() && victim instanceof Player player){
            EntityComponent i = ENTITYINSTANCE.get(victim);
            if(i.getBlackEyeStatus()) ci.cancel();
        }
    }

    public static void Cannon(Creeper creeperEntity, DamageSource damageSource){
        boolean loot = true;
        if (damageSource.getEntity() == creeperEntity){loot = false;} //creeper naturally exploded. No loot!
        if (creeperEntity.level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)){loot = false;}
        Explosion.BlockInteraction explosion$mode = creeperEntity.level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING) ? Explosion.BlockInteraction.DESTROY : Explosion.BlockInteraction.NONE;
        float f = creeperEntity.isPowered() ? 2.0F : 1.0F;
        Vec3 vector3d = new Vec3(creeperEntity.getX(), creeperEntity.getY(), creeperEntity.getZ());
        creeperEntity.level.explode(creeperEntity, creeperEntity.getX(), creeperEntity.getY(), creeperEntity.getZ(), (float)3 * f, explosion$mode);
        creeperEntity.remove(Entity.RemovalReason.KILLED);
        //Phase 2 - artificial loot table.
        if(loot){
            Entity aggressor = damageSource.getEntity();
            if(aggressor instanceof Player || aggressor instanceof Wolf){
                ExperienceOrb experienceOrbEntity = new ExperienceOrb(aggressor.level, creeperEntity.getX(), creeperEntity.getY(), creeperEntity.getZ(), 5);
                ItemEntity itemEntity = new ItemEntity(EntityType.ITEM, aggressor.level);
                int maxgun = 2;
                if(aggressor instanceof Player){
                    Map<Enchantment, Integer> map =  EnchantmentHelper.getEnchantments(((Player) aggressor).getMainHandItem());
                    if(map.containsKey(Enchantments.MOB_LOOTING)){
                        maxgun = 2 + map.get(Enchantments.MOB_LOOTING);
                    }
                }
                Random random = new Random();
                int gunpowderamount = random.nextInt(maxgun+1); // accounting for 0. I don't think it shows up in randoms.
                itemEntity.setItem(new ItemStack(Items.GUNPOWDER, gunpowderamount));
                experienceOrbEntity.setPos(vector3d.x, vector3d.y, vector3d.z);
                itemEntity.setPos(vector3d.x, vector3d.y, vector3d.z);
                aggressor.level.addFreshEntity(experienceOrbEntity);
                aggressor.level.addFreshEntity(itemEntity);
            }
            else if(aggressor instanceof Skeleton){
                ItemEntity itemEntity = new ItemEntity(EntityType.ITEM, aggressor.level);
                Random random = new Random();
                int gunpowderamount = random.nextInt(3);
                itemEntity.setItem(new ItemStack(Items.GUNPOWDER, gunpowderamount));
                ItemEntity Disc = new ItemEntity(EntityType.ITEM, aggressor.level);
                Disc.setItem(new ItemStack(MusicDisc(), 1));
                itemEntity.setPos(vector3d.x, vector3d.y, vector3d.z);
                Disc.setPos(vector3d.x, vector3d.y, vector3d.z);
                aggressor.level.addFreshEntity(itemEntity);
                aggressor.level.addFreshEntity(Disc);
            }else if(aggressor instanceof Creeper && ((Creeper) aggressor).canDropMobsSkull()){
                ItemEntity itemEntity = new ItemEntity(EntityType.ITEM, aggressor.level);
                Random random = new Random();
                int gunpowderamount = random.nextInt(3);
                itemEntity.setItem(new ItemStack(Items.GUNPOWDER, gunpowderamount));
                ItemEntity Skull = new ItemEntity(EntityType.ITEM, aggressor.level);
                Skull.setItem(new ItemStack(Items.CREEPER_HEAD, 1));
                itemEntity.setPos(vector3d.x, vector3d.y, vector3d.z);
                Skull.setPos(vector3d.x, vector3d.y, vector3d.z);
                aggressor.level.addFreshEntity(itemEntity);
                aggressor.level.addFreshEntity(Skull);

            }
        }
    }

    public static void undying(Player victim){
        victim.setHealth(victim.getMaxHealth());
    }

    public static void shoutTicksCount(Player victim){
        EntityComponent h = ENTITYINSTANCE.get(victim);
        if(h.getShoutTicks() > 0) h.setShoutTicks(h.getShoutTicks() - 1);
    }

    public static void Fatigue(Player victim){
        ServerStatsCounter serverstatisticsmanager = ((ServerPlayer)victim).getStats();
        int sleeptime = Mth.clamp(serverstatisticsmanager.getValue(Stats.CUSTOM.get(Stats.TIME_SINCE_REST)), 1, Integer.MAX_VALUE);
        Random random = new Random();
        int chance = random.nextInt(50000);
        if(sleeptime > 72000 & sleeptime < 84000){
            if(chance <= 12){
                victim.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60*20, 0));
                victim.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60*20, 0));
            }
        }else if(sleeptime >= 84000 && sleeptime <= 96000){
            if(chance <= 10){
                victim.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 60*20, 0));
                victim.addEffect(new MobEffectInstance(MobEffects.HUNGER, 60*20, 0));
            } if(chance <= 15){
                victim.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 45*20, 1));
                victim.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60*20, 1));
                victim.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60*20, 1));
            }
        }else if(sleeptime > 96000 && sleeptime <= 132000){
            if(chance <= 10){
                victim.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 70*20, 0));
                victim.addEffect(new MobEffectInstance(MobEffects.HUNGER, 70*20, 0));
            }if(chance <= 13){
                victim.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 45*20, 0));
            }
            if(chance <= 17){
                victim.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 45*20, 2));
                victim.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60*20, 2));
                victim.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60*20, 2));
            }
        }else if(sleeptime > 132000){
            if (chance < 49000) victim.hurt(Fatiguedamage.causeFatigueDamage(victim), 1);
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

    public static void seedWrong(LivingEntity e){
        EntityType eType = e.getType();
        if(eType == EntityType.ENDERMAN || eType == EntityType.ZOMBIFIED_PIGLIN || eType == EntityType.WOLF || eType == EntityType.BEE || eType == EntityType.LLAMA){
            Random random = new Random();
            int chance = random.nextInt(30);
            if(chance <= 1) {
                EntityComponent h = ENTITYINSTANCE.get(e);
                h.setWrongStatus(true);
            }
        }

    }

    public static void Unstable(LivingEntity entity){
        if(entity.getType().equals(EntityType.GHAST)){
            Ghast ghast = (Ghast) entity;
            ((GhastAccessor) ghast).vdmSetExplosive(5);
        } else if(entity.getType().equals(EntityType.CREEPER)){
            Creeper creeper = (Creeper) entity;
            ((CreeperAccessor) creeper).vdmCSetExplosive(5);
        }
    }

    public static void Anger(LivingEntity entity){
        if (entity.getType().equals(EntityType.SKELETON) || entity.getType().equals(EntityType.STRAY)) {
            AbstractSkeleton abstractSkeletonEntity = (AbstractSkeleton) entity;
            try {
                RangedBowAttackGoal<AbstractSkeleton> rangedBowAttackGoal = ((SkeletonAccessor) abstractSkeletonEntity).vdmGetBowGoal();
                if (abstractSkeletonEntity.level.getDifficulty() != Difficulty.HARD)
                    rangedBowAttackGoal.setMinAttackInterval(30);
                else rangedBowAttackGoal.setMinAttackInterval(15);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

        }
        if(entity.getType().equals(EntityType.PILLAGER)){
            Pillager pillagerEntity = (Pillager) entity;
            GoalSelector pillagerGoalSelector = ((LivingGoalAccessor) pillagerEntity).vdmGetMobGoalSelector();
            Set<WrappedGoal> prioritizedGoals = ((AvailableGoalsAccessor) pillagerGoalSelector).vdmGetAvailableGoals();
            Goal toremove = null;
            for(WrappedGoal prioritizedGoal : prioritizedGoals){
                if(prioritizedGoal.getGoal() instanceof RangedCrossbowAttackGoal){
                    toremove = prioritizedGoal.getGoal();
                    if(toremove != null) break;
                }
            }
            if(toremove != null){
                pillagerGoalSelector.removeGoal(toremove);
                pillagerGoalSelector.addGoal(3, new CrossbowAngerManagement(pillagerEntity, 1.5D, 8.0F));
            }
        }if(entity.getType().equals(EntityType.WITCH)){
            Witch witchEntity = (Witch) entity;
            GoalSelector witchGoalSelector = ((LivingGoalAccessor) witchEntity).vdmGetMobGoalSelector();
            Set<WrappedGoal> prioritizedGoals = ((AvailableGoalsAccessor) witchGoalSelector).vdmGetAvailableGoals();
            Goal toremove = null;
            for(WrappedGoal prioritizedGoal : prioritizedGoals) {
                if (prioritizedGoal.getGoal() instanceof RangedAttackGoal) {
                    toremove = prioritizedGoal.getGoal();
                    if (toremove != null) break;
                }
            }
            if(toremove != null){
                witchGoalSelector.removeGoal(toremove);
                witchGoalSelector.addGoal(3, new RangedAngerManagment(witchEntity, 1.0D, 60, 10.0F));
            }
        }

    }

    public static void Hardened(LivingEntity entity){
        float health = (entity).getHealth() * 0.5f;
        AttributeInstance modifiableAttributeInstance = (entity).getAttribute(Attributes.MAX_HEALTH);
        if (modifiableAttributeInstance == null) {
            return;
        }
        if(modifiableAttributeInstance.getModifiers().size() == 0) {
            modifiableAttributeInstance.addTransientModifier(new AttributeModifier(UUID.fromString("D6F0BA2-1186-46AC-B896-C61C5CEE99CC"), "Hardened health boost", health, AttributeModifier.Operation.ADDITION));
            (entity).setHealth((entity).getHealth() * 1.5f);
        }
    }

    public static void seedRetaliation(LivingEntity entity){
        EntityType eType = entity.getType();
        if(eType == EntityType.PIG || eType == EntityType.SHEEP || eType == EntityType.COW || eType == EntityType.MOOSHROOM || eType == EntityType.CHICKEN){
            Random random = new Random();
            int chance = random.nextInt(20);
            EntityComponent h = new EntityComponent(entity);
            h.setRetaliationStatus(true);
        }
    }
}
