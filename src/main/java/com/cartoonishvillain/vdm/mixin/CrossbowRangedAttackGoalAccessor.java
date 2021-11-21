package com.cartoonishvillain.vdm.mixin;

import net.minecraft.world.entity.ai.goal.RangedCrossbowAttackGoal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(RangedCrossbowAttackGoal.class)
public interface CrossbowRangedAttackGoalAccessor
{
    @Accessor("attackDelay")
    int vdmGetRangedAttackDelay();

    @Accessor("attackDelay")
    void vdmSetRangedAttackDelay(int time);
}
