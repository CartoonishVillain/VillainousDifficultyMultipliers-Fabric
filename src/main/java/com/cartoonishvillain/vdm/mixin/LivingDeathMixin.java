package com.cartoonishvillain.vdm.mixin;

import com.cartoonishvillain.vdm.components.ComponentTicker;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingDeathMixin {
    @Inject(at = @At("HEAD"), method = "die")
    private void vdmattack(DamageSource damageSource, CallbackInfo ci){
        LivingEntity victim = ((LivingEntity) (Object) this);
        ComponentTicker.LivingDeathMultipliers(victim, damageSource, ci);

    }
}
