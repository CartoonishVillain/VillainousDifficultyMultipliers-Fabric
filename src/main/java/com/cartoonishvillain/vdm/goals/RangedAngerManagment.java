package com.cartoonishvillain.vdm.goals;

import com.cartoonishvillain.vdm.mixin.RangedAttackGoalAccessor;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;

public class RangedAngerManagment extends RangedAttackGoal {
    public RangedAngerManagment(RangedAttackMob p_i1649_1_, double p_i1649_2_, int p_i1649_4_, float p_i1649_5_) {
        super(p_i1649_1_, p_i1649_2_, p_i1649_4_, p_i1649_5_);
    }

    @Override
    public void tick() {
        super.tick();
        int delay = 0;
        try {
            delay = ((RangedAttackGoalAccessor) this).vdmGetRangedAttackTime();
            if (delay > 25) {
                delay = 25;
            }
            ((RangedAttackGoalAccessor) this).vdmSetRangedAttackTime(delay);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
