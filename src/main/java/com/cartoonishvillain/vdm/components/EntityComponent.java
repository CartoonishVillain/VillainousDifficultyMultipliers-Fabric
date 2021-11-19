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
    private final Object provider;

    public EntityComponent(Object provider){
        this.provider = provider;
    }

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
        //TODO: SYNC
    }

    public void sync(){
        //TODO: SYNC
    }

    @Override
    public void readFromNbt(CompoundTag tag) {

    }

    @Override
    public void writeToNbt(CompoundTag tag) {

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
