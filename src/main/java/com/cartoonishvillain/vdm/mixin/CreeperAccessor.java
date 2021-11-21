package com.cartoonishvillain.vdm.mixin;

import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Creeper.class)
public interface CreeperAccessor
{
    @Accessor("explosionRadius")
    void vdmCSetExplosive(int i);
}
