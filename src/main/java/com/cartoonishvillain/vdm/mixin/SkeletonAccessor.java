package com.cartoonishvillain.vdm.mixin;

import net.minecraft.world.entity.ai.goal.RangedBowAttackGoal;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractSkeleton.class)
public interface SkeletonAccessor
{
    @Accessor("bowGoal")
    RangedBowAttackGoal<AbstractSkeleton> vdmGetBowGoal();
}
