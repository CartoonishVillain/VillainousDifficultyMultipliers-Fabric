package com.cartoonishvillain.vdm.commands;

import com.cartoonishvillain.vdm.VDM;
import com.cartoonishvillain.vdm.components.LevelComponent;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static com.cartoonishvillain.vdm.components.ComponentStarter.LEVELINSTANCE;

public class activeListCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher){
         dispatcher.register(Commands.literal("vdm").then(Commands.literal("activelist")
                .requires(cs -> cs.hasPermission(0))
                .executes(context -> {
                    return run(context.getSource());
                })));
    }

    public static int run(CommandSourceStack context) throws CommandSyntaxException {
        AtomicInteger count = new AtomicInteger(0);
        LevelComponent h = LEVELINSTANCE.get(context.getLevel().getLevelData());
        AtomicReference<String> string = new AtomicReference<>("");
            if(h.isAging()) {string.set(string.get() + ChatFormatting.RED + "Aging, "); count.set(count.get()+1);}
            if(h.isAnger()) {string.set(string.get() + ChatFormatting.RED + "Anger, "); count.set(count.get()+1);}
            if(h.isBlackeye()) {string.set(string.get() + ChatFormatting.RED + "Black Eye, "); count.set(count.get()+1);}
            if(h.isBlacksmithing()) {string.set(string.get() + ChatFormatting.BLUE + "Blacksmithing, "); count.set(count.get()+1);}
            if(h.isCannon()) {string.set(string.get() + ChatFormatting.RED + "Cannon, "); count.set(count.get()+1);}
            if(h.isCelebration()) {string.set(string.get() + ChatFormatting.BLUE + "Celebration, "); count.set(count.get()+1);}
            if(h.isEruptiveswarm()) {string.set(string.get() + ChatFormatting.RED + "Eruptive Swarm, "); count.set(count.get()+1);}
            if(h.isFatigue()) {string.set(string.get() + ChatFormatting.RED + "Fatigue, "); count.set(count.get()+1);}
            if(h.isFlammable()) {string.set(string.get() + ChatFormatting.RED + "Flammable, "); count.set(count.get()+1);}
            if(h.isFuelefficient()) {string.set(string.get() + ChatFormatting.BLUE + "Fuel Efficient, "); count.set(count.get()+1);}
            if(h.isHardened()) {string.set(string.get() + ChatFormatting.RED + "Hardened, "); count.set(count.get()+1);}
            if(h.isInferno()) {string.set(string.get() + ChatFormatting.RED + "Inferno, "); count.set(count.get()+1);}
            if(h.isKarmicjustice()) {string.set(string.get() + ChatFormatting.RED + "Karmic Justice, "); count.set(count.get()+1);}
            if(h.isKinetic()) {string.set(string.get() + ChatFormatting.BLUE + "Kinetic, "); count.set(count.get()+1);}
            if(h.isPandemic()) {string.set(string.get() + checkForSupport("Pandemic, ", VDM.isCalyxLoaded, true)); count.set(count.get()+1);}
            if(h.isRested()) {string.set(string.get() + ChatFormatting.BLUE + "Rested, "); count.set(count.get()+1);}
//            if(h.isShift()) {string.set(string.get() + ChatFormatting.RED + "Shift, "); count.set(count.get()+1);}
            if(h.isSoftskin()) {string.set(string.get() + ChatFormatting.RED + "Soft Skin, "); count.set(count.get()+1);}
            if(h.isUndying()) {string.set(string.get() + ChatFormatting.BLUE + "Undying, "); count.set(count.get()+1);}
            if(h.isUnstable()) {string.set(string.get() + ChatFormatting.RED + "Unstable, "); count.set(count.get()+1);}
            if(h.isVegetarian()) {string.set(string.get() + ChatFormatting.RED + "Vegetarian, "); count.set(count.get()+1);}
            if(h.isVenom()) {string.set(string.get() + ChatFormatting.RED + "Venom, "); count.set(count.get()+1);}
            if(h.isWarranty()) {string.set(string.get() + ChatFormatting.BLUE + "Warranty, "); count.set(count.get()+1);}
            if(h.isWild()) {string.set(string.get() + ChatFormatting.BLUE + "Wild, "); count.set(count.get()+1);}
            if(h.isWrong()) {string.set(string.get() + ChatFormatting.RED + "Wrong, "); count.set(count.get()+1);}


        String finalString = string.toString();
        if(finalString.length() > 0){
        finalString = finalString.substring(0, finalString.length()-2);
        context.sendSuccess(new TranslatableComponent("info.villainousdifficultymultipiers.count", count.get()), false);
        context.sendSuccess(new TextComponent(finalString), false);}
        else{context.sendSuccess(new TranslatableComponent("info.villainousdifficultymultipliers.none"), false);}


        return 0;
    }
    private static String checkForSupport(String name, boolean modNeeded, boolean IncreasingMultiplier){
        if(modNeeded){
            if(IncreasingMultiplier) return ChatFormatting.RED + name;
            else return ChatFormatting.BLUE + name;
        }
        else return ChatFormatting.GRAY + name;
    }
}
