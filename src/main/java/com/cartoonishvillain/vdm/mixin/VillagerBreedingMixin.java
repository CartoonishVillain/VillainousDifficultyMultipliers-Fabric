package com.cartoonishvillain.vdm.mixin;

import com.cartoonishvillain.vdm.components.ComponentTicker;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.npc.Villager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Villager.class)
public class VillagerBreedingMixin {
    @Inject(at = @At("TAIL"), method = "getBreedOffspring(Lnet/minecraft/server/level/ServerLevel; Lnet/minecraft/world/entity/AgeableMob;)Lnet/minecraft/world/entity/npc/Villager;")
    private void vdmVillagerBreed(ServerLevel serverLevel, AgeableMob ageableMob, CallbackInfoReturnable<Villager> cir){
        LivingEntity livingEntity = ((LivingEntity) (Object) this);
        ComponentTicker.Aging(livingEntity, ageableMob);
    }
}
