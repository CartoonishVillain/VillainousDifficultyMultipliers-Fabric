package com.cartoonishvillain.vdm.components;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import net.minecraft.nbt.CompoundTag;

public class WorldComponent implements ComponentV3 {
    protected boolean isNight = false;
    protected boolean celebration = false;

    private final Object provider;

    public WorldComponent(Object provider){
        this.provider = provider;
    }


    public boolean getCelebrationStatus() {
        return celebration;
    }


    public void setCelebrationStatus(boolean status) {
        celebration = status;
    }


    public boolean isNight() {
        return isNight;
    }


    public void setisNight(boolean status) {
        isNight = status;
    }

    @Override
    public void readFromNbt(CompoundTag tag) {
        celebration = tag.getBoolean("celebration");
        isNight = tag.getBoolean("night");
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        tag.putBoolean("celebration", celebration);
        tag.putBoolean("night", isNight);
    }
}
