package com.cartoonishvillain.vdm.mixin;

import net.minecraft.world.entity.ai.goal.RangedCrossbowAttackGoal;
import net.minecraft.world.entity.monster.Ghast;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Ghast.class)
public interface GhastAccessor
{
    @Accessor("explosionPower")
    void vdmSetExplosive(int i);
}
