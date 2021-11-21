package com.cartoonishvillain.vdm.mixin;

import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.ai.goal.RangedBowAttackGoal;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Set;

@Mixin(GoalSelector.class)
public interface AvailableGoalsAccessor
{
    @Accessor("availableGoals")
    Set<WrappedGoal> vdmGetAvailableGoals();
}
