package com.cartoonishvillain.vdm.components;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

public class EntityComponent implements ComponentV3, AutoSyncedComponent {
    protected boolean blackEye = false;
    protected float kineticBuildup = 0f;
    protected int shout = 0;
    protected boolean retaliation = false;
    protected boolean wrong = false;
    protected int age = 0;
    private final Object provider;

    public EntityComponent(Object provider){
        this.provider = provider;
    }

    public boolean getRetaliationStatus() {
        return retaliation;
    }

    public void setRetaliationStatus(boolean set) {
        retaliation = set;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean getWrongStatus() {return wrong;}

    public void setWrongStatus(boolean set) {this.wrong = set;}

    public boolean getBlackEyeStatus() {
        return blackEye;
    }

    public float getKineticBuildup() {return kineticBuildup;}

    public void setBlackEyeStatus(boolean set) {
        blackEye = set;
    }

    public void setKineticBuildup(float damage) {kineticBuildup += damage;
        if(kineticBuildup > 100f) kineticBuildup = 100;}

    public int getShoutTicks() {
        return shout;
    }

    public void setShoutTicks(int ticks) {
        shout = ticks;
        ComponentStarter.ENTITYINSTANCE.sync(this.provider);
    }

    public void sync(){
        ComponentStarter.ENTITYINSTANCE.sync(this.provider);
    }

    @Override
    public void readFromNbt(CompoundTag tag) {
        blackEye = tag.getBoolean("blackeyestatus");
        kineticBuildup = tag.getFloat("kineticbuildup");
        shout = tag.getInt("shoutticks");
        retaliation = tag.getBoolean("retaliation");
        age = tag.getInt("age");
        wrong = tag.getBoolean("wrong");
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        tag.putBoolean("retaliation", retaliation);
        tag.putInt("age", age);
        tag.putBoolean("wrong", wrong);
        tag.putBoolean("blackeyestatus", blackEye);
        tag.putFloat("kineticbuildup", kineticBuildup);
        tag.putInt("shoutticks", shout);
    }

    @Override
    public void writeSyncPacket(FriendlyByteBuf buf, ServerPlayer recipient) {
        buf.writeInt(this.shout);
    }

    @Override
    public void applySyncPacket(FriendlyByteBuf buf) {
        this.shout = buf.readInt();
    }
}
