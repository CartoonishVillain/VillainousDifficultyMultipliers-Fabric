package com.cartoonishvillain.vdm.mixin;

import com.cartoonishvillain.vdm.components.LevelComponent;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.cartoonishvillain.vdm.components.ComponentStarter.LEVELINSTANCE;

@Mixin(Item.class)
public class VegetarianMixin {
    @Inject(at = @At("HEAD"), method = "finishUsingItem")
    private void finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity, CallbackInfoReturnable<ItemStack> cir){

        if(!level.isClientSide){
            LevelComponent component = LEVELINSTANCE.get(level.getLevelData());
            if(component.isVegetarian() && itemStack.isEdible() && itemStack.getItem().getFoodProperties() != null && itemStack.getItem().getFoodProperties().isMeat() && livingEntity instanceof Player){
                livingEntity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 15*20, 0));
                livingEntity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 30*20, 1));
                livingEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 15*20, 0));
                livingEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 30*20, 0));
                livingEntity.sendMessage(new TranslatableComponent("status.villainousdifficultymultipliers.vegetarian").withStyle(ChatFormatting.RED), livingEntity.getUUID());
            }
        }
    }
}
