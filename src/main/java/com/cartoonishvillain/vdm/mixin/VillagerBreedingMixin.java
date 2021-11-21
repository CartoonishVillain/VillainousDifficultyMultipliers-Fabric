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

@Mixin(Villager.class)
public class VillagerBreedingMixin {
    @Inject(at = @At("TAIL"), method = "getBreedOffspring*")
    private void vdmAnimalBreed(ServerLevel serverLevel, AgeableMob ageableMob, CallbackInfo ci){
        LivingEntity livingEntity = ((LivingEntity) (Object) this);
        ComponentTicker.Aging(livingEntity, ageableMob);
    }
}
