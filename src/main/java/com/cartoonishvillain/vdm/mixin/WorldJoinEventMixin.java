package com.cartoonishvillain.vdm.mixin;

import com.cartoonishvillain.vdm.components.ComponentTicker;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerLevel.class)
public class WorldJoinEventMixin {
    @Inject(at = @At("TAIL"), method = "addFreshEntity")
    private void vdmattackplayer(Entity entity, CallbackInfoReturnable<Boolean> cir){
        ComponentTicker.SpawnMultipliers(entity);
    }
}
