package com.cartoonishvillain.vdm;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

import java.nio.charset.Charset;

public class ShoutPacket {

    public static void encodeAndSend(String name, String message, FriendlyByteBuf buffer){
        buffer.writeInt(name.length());
        buffer.writeCharSequence(name, Charset.defaultCharset());
        buffer.writeInt(message.length());
        buffer.writeCharSequence(message, Charset.defaultCharset());
        ClientPlayNetworking.send(new ResourceLocation("vdm", "chatpacket"), buffer);
    }
}
