package com.cartoonishvillain.vdm.mixin;

import com.cartoonishvillain.vdm.components.ComponentTicker;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class LivingDamageMixinPlayer {
    @Inject(at = @At("TAIL"), method = "actuallyHurt")
    private void vdmattackplayer(DamageSource damageSource, float f, CallbackInfo ci){
        LivingEntity victim = ((LivingEntity) (Object) this);
        ComponentTicker.LivingDamageMultipliers(victim, damageSource, f);

    }
}
