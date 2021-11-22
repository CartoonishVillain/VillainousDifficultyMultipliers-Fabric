package com.cartoonishvillain.vdm.mixin;

import com.cartoonishvillain.vdm.VDM;
import com.cartoonishvillain.vdm.components.ComponentTicker;
import com.cartoonishvillain.vdm.components.LevelComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

import static com.cartoonishvillain.vdm.components.ComponentStarter.LEVELINSTANCE;

@Mixin(AnvilBlock.class)
public class AnvilRepairMixin {
    @Inject(at = @At("HEAD"), method = "damage", cancellable = true)
    private static void vdmAnvilRepair(BlockState blockState, CallbackInfoReturnable<BlockState> cir){
        ComponentTicker.blackSmithing(blockState, cir);
    }
}
