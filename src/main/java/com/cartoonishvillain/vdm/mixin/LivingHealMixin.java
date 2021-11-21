package com.cartoonishvillain.vdm.mixin;

import com.cartoonishvillain.vdm.components.ComponentTicker;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingHealMixin {
    @Inject(at = @At("HEAD"), method = "heal", cancellable = true)
    private void vdmattack(float f, CallbackInfo ci){
        LivingEntity victim = ((LivingEntity) (Object) this);
        ComponentTicker.LivingHealthMultipliers(victim, f, ci);

    }
}
