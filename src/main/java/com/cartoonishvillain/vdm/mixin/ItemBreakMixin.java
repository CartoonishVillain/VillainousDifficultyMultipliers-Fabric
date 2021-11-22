package com.cartoonishvillain.vdm.mixin;

import com.cartoonishvillain.vdm.components.ComponentTicker;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public class ItemBreakMixin {

    @Inject(at = @At("HEAD"), method = "hurtAndBreak")
    private <T extends LivingEntity> void vdmWarrantyPolicy(int i, T livingEntity, Consumer<T> consumer, CallbackInfo ci){
        ItemStack stack = ((ItemStack) (Object) this);
        ComponentTicker.Warranty(i, livingEntity, consumer, stack, ci);

    }

}
