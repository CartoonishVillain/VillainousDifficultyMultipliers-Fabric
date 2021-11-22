package com.cartoonishvillain.vdm.mixin;

import com.cartoonishvillain.vdm.components.ComponentTicker;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class PlayerSleepMixin {
    @Inject(at = @At("HEAD"), method = "stopSleepInBed")
    private void vdmWakeUp(boolean bl, boolean bl2, CallbackInfo ci){
        Player player = ((Player) (Object) this);
        ComponentTicker.wakeUpEvent(player, bl, bl2);
    }
}
