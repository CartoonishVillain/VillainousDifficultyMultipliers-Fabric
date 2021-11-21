package com.cartoonishvillain.vdm.mixin;

import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Set;

@Mixin(RangedAttackGoal.class)
public interface RangedAttackGoalAccessor
{
    @Accessor("attackTime")
    int vdmGetRangedAttackTime();

    @Accessor("attackTime")
    void vdmSetRangedAttackTime(int time);
}
