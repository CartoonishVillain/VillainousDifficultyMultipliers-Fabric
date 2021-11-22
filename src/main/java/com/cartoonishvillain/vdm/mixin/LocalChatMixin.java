package com.cartoonishvillain.vdm.mixin;

import com.cartoonishvillain.vdm.ShoutPacket;
import com.cartoonishvillain.vdm.components.EntityComponent;
import io.netty.buffer.Unpooled;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.cartoonishvillain.vdm.components.ComponentStarter.ENTITYINSTANCE;

@Mixin(LocalPlayer.class)
public class LocalChatMixin {
    @Inject(at = @At("HEAD"), method = "chat", cancellable = true)
    private void vdmShoutChat(String string, CallbackInfo ci){
        Player player = ((LocalPlayer) (Object) this);

        if(player != null){
            String name = player.getName().getString();
            String format = "<" + name + "> ";
            if (!(string.charAt(0) == '/')) {
                EntityComponent entityComponent = ENTITYINSTANCE.get(player);
                if(entityComponent.getShoutTicks() > 0){
                    ShoutPacket.encodeAndSend(format, string, new FriendlyByteBuf(Unpooled.buffer()));
                    ci.cancel();
                }
            }
        }
    }
}
