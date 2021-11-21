package com.cartoonishvillain.vdm;

import com.cartoonishvillain.vdm.mixin.DamageSourceInvoker;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.Entity;

public class Fatiguedamage extends DamageSource {
    public Fatiguedamage(String p_i1566_1_) {
        super(p_i1566_1_);
    }

    public static DamageSource causeFatigueDamage(Entity entity){
        return ((DamageSourceInvoker) new EntityDamageSource("fatigue", entity)).VDMinvokeBypassArmor().setMagic();
    }
}
