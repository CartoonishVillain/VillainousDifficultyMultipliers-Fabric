package com.cartoonishvillain.vdm.mixin;

import com.cartoonishvillain.vdm.components.ComponentTicker;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityTick {
    @Inject(at = @At("TAIL"), method = "tick")
    private void vdmtickplayer(CallbackInfo ci){
        LivingEntity victim = ((LivingEntity) (Object) this);
        ComponentTicker.LivingTickMethod(victim);
    }
}
