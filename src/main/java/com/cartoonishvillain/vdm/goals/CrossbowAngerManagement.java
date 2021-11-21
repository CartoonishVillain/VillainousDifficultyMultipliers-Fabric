package com.cartoonishvillain.vdm.goals;

import com.cartoonishvillain.vdm.mixin.CrossbowRangedAttackGoalAccessor;
import net.minecraft.world.entity.ai.goal.RangedCrossbowAttackGoal;
import net.minecraft.world.entity.monster.Monster;

public class CrossbowAngerManagement extends RangedCrossbowAttackGoal {
    public CrossbowAngerManagement(Monster p_i50322_1_, double p_i50322_2_, float p_i50322_4_) {
        super(p_i50322_1_, p_i50322_2_, p_i50322_4_);
    }

    @Override
    public void tick() {
        super.tick();
        int delay = 0;
        try {
            delay = ((CrossbowRangedAttackGoalAccessor) this).vdmGetRangedAttackDelay();
            if (delay > 13) {
                delay = 13;
            }
            ((CrossbowRangedAttackGoalAccessor) this).vdmSetRangedAttackDelay(delay);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
