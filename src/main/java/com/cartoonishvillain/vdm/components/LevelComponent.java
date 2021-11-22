package com.cartoonishvillain.vdm.components;

import com.cartoonishvillain.vdm.VDM;
import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import net.minecraft.nbt.CompoundTag;

public class LevelComponent implements ComponentV3 {

    protected boolean aging = VDM.config.defaultMultipliers.aging;
    protected boolean blackeye = VDM.config.defaultMultipliers.blackeye;
    protected boolean cannon = VDM.config.defaultMultipliers.cannon;
    protected boolean fatigue = VDM.config.defaultMultipliers.fatigue;
    protected boolean karmicjustice = VDM.config.defaultMultipliers.karmicjustice;
    protected boolean shift = VDM.config.defaultMultipliers.shift;
    protected boolean softskin = VDM.config.defaultMultipliers.softskin;
    protected boolean venom = VDM.config.defaultMultipliers.venom;
    protected boolean hardened = VDM.config.defaultMultipliers.hardened;
    protected boolean anger = VDM.config.defaultMultipliers.anger;
    protected boolean unstable = VDM.config.defaultMultipliers.unstable;
    protected boolean vegetarian = VDM.config.defaultMultipliers.vegetarian;
    protected boolean flammable = VDM.config.defaultMultipliers.flammable;
    protected boolean wrong = VDM.config.defaultMultipliers.wrong;
    protected boolean inferno = VDM.config.defaultMultipliers.inferno;
    protected boolean eruptiveswarm = VDM.config.defaultMultipliers.eruptiveswarm;
    protected boolean kinetic = VDM.config.defaultMultipliers.kinetic;
    protected boolean undying = VDM.config.defaultMultipliers.undying;
    protected boolean fuelefficient = false; //VDM.config.defaultMultipliers.fuelefficient;
    protected boolean blacksmithing = VDM.config.defaultMultipliers.blacksmithing;
    protected boolean warranty = VDM.config.defaultMultipliers.warranty;
    protected boolean celebration = VDM.config.defaultMultipliers.celebration;
    protected boolean rested = VDM.config.defaultMultipliers.rested;
    protected boolean wild = VDM.config.defaultMultipliers.wild;
    protected boolean pandemic = VDM.config.defaultMultipliers.pandemic;
    private final Object provider;

    public LevelComponent(Object provider){
        this.provider = provider;
    }

    public boolean isFlammable() {
        return flammable;
    }

    public void setFlammable(boolean flammable) {
        this.flammable = flammable;
    }

    public boolean isAging() {
        return aging;
    }

    public void setAging(boolean aging) {
        this.aging = aging;
    }

    public boolean isBlackeye() {
        return blackeye;
    }

    public void setBlackeye(boolean blackeye) {
        this.blackeye = blackeye;
    }

    public boolean isCannon() {
        return cannon;
    }

    public void setCannon(boolean cannon) {
        this.cannon = cannon;
    }

    public boolean isFatigue() {
        return fatigue;
    }

    public void setFatigue(boolean fatigue) {
        this.fatigue = fatigue;
    }

    public boolean isKarmicjustice() {
        return karmicjustice;
    }

    public void setKarmicjustice(boolean karmicjustice) {
        this.karmicjustice = karmicjustice;
    }

    public boolean isShift() {
        return shift;
    }

    public void setShift(boolean shift) {
        this.shift = shift;
    }

    public boolean isSoftskin() {
        return softskin;
    }

    public void setSoftskin(boolean softskin) {
        this.softskin = softskin;
    }

    public boolean isVenom() {
        return venom;
    }

    public void setVenom(boolean venom) {
        this.venom = venom;
    }

    public boolean isHardened() {
        return hardened;
    }

    public void setHardened(boolean hardened) {
        this.hardened = hardened;
    }

    public boolean isAnger() {
        return anger;
    }

    public void setAnger(boolean anger) {
        this.anger = anger;
    }

    public boolean isUnstable() {
        return unstable;
    }

    public void setUnstable(boolean unstable) {
        this.unstable = unstable;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public boolean isWrong() {
        return wrong;
    }

    public void setWrong(boolean wrong) {
        this.wrong = wrong;
    }

    public boolean isInferno() {
        return inferno;
    }

    public void setInferno(boolean inferno) {
        this.inferno = inferno;
    }

    public boolean isEruptiveswarm() {
        return eruptiveswarm;
    }

    public void setEruptiveswarm(boolean eruptiveswarm) {
        this.eruptiveswarm = eruptiveswarm;
    }

    public boolean isKinetic() {
        return kinetic;
    }

    public void setKinetic(boolean kinetic) {
        this.kinetic = kinetic;
    }

    public boolean isUndying() {
        return undying;
    }

    public void setUndying(boolean undying) {
        this.undying = undying;
    }

    public boolean isFuelefficient() {
        return fuelefficient;
    }

    public void setFuelefficient(boolean fuelefficient) {
        this.fuelefficient = fuelefficient;
    }

    public boolean isBlacksmithing() {
        return blacksmithing;
    }

    public void setBlacksmithing(boolean blacksmithing) {
        this.blacksmithing = blacksmithing;
    }

    public boolean isWarranty() {
        return warranty;
    }

    public void setWarranty(boolean warranty) {
        this.warranty = warranty;
    }

    public boolean isCelebration() {
        return celebration;
    }

    public void setCelebration(boolean celebration) {
        this.celebration = celebration;
    }

    public boolean isRested() {
        return rested;
    }

    public void setRested(boolean rested) {
        this.rested = rested;
    }

    public boolean isWild() {
        return wild;
    }

    public void setWild(boolean wild) {
        this.wild = wild;
    }

    public boolean isPandemic() {
        return pandemic;
    }

    public void setPandemic(boolean pandemic) {
        this.pandemic = pandemic;
    }

    @Override
    public void readFromNbt(CompoundTag tag) {
         aging = tag.getBoolean("aging");
         blackeye = tag.getBoolean("blackeye");
         cannon = tag.getBoolean("cannon");
         fatigue = tag.getBoolean("fatigue");
         karmicjustice = tag.getBoolean("karmicjustice");
         shift = tag.getBoolean("shift");
         softskin = tag.getBoolean("softskin");
         venom = tag.getBoolean("venom");
         hardened = tag.getBoolean("hardened");
         anger = tag.getBoolean("anger");
         flammable = tag.getBoolean("flammable");
         unstable = tag.getBoolean("unstable");
         vegetarian = tag.getBoolean("vegetarian");
         wrong = tag.getBoolean("wrong");
         inferno = tag.getBoolean("inferno");
         eruptiveswarm = tag.getBoolean("eruptiveswarm");
         kinetic = tag.getBoolean("kinetic");
         undying = tag.getBoolean("undying");
         fuelefficient = tag.getBoolean("fuelefficient");
         blacksmithing = tag.getBoolean("blacksmithing");
         warranty = tag.getBoolean("warranty");
         celebration = tag.getBoolean("celebration");
         rested = tag.getBoolean("rested");
         wild = tag.getBoolean("wild");
         pandemic = tag.getBoolean("pandemic");
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        tag.putBoolean("aging", aging);
        tag.putBoolean("blackeye", blackeye);
        tag.putBoolean("cannon", cannon);
        tag.putBoolean("fatigue", fatigue);
        tag.putBoolean("karmicjustice", karmicjustice);
        tag.putBoolean("shift", shift);
        tag.putBoolean("softskin", softskin);
        tag.putBoolean("venom", venom);
        tag.putBoolean("hardened", hardened);
        tag.putBoolean("anger", anger);
        tag.putBoolean("flammable", flammable);
        tag.putBoolean("unstable", unstable);
        tag.putBoolean("vegetarian", vegetarian);
        tag.putBoolean("wrong", wrong);
        tag.putBoolean("inferno", inferno);
        tag.putBoolean("eruptiveswarm", eruptiveswarm);
        tag.putBoolean("kinetic", kinetic);
        tag.putBoolean("undying", undying);
        tag.putBoolean("fuelefficient", fuelefficient);
        tag.putBoolean("blacksmithing", blacksmithing);
        tag.putBoolean("warranty", warranty);
        tag.putBoolean("celebration", celebration);
        tag.putBoolean("rested", rested);
        tag.putBoolean("wild", wild);
        tag.putBoolean("pandemic", pandemic);
    }
}
