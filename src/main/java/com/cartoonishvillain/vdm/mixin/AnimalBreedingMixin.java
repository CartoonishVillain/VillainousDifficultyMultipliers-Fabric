package com.cartoonishvillain.vdm.mixin;

import com.cartoonishvillain.vdm.components.ComponentTicker;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.animal.Animal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Animal.class)
public class AnimalBreedingMixin {
    @Inject(at = @At("TAIL"), method = "spawnChildFromBreeding")
    private void vdmAnimalBreed(ServerLevel serverLevel, Animal animal, CallbackInfo ci){
        Animal animal1 = ((Animal) (Object) this);
        ComponentTicker.Aging(animal1, animal);
    }
}
