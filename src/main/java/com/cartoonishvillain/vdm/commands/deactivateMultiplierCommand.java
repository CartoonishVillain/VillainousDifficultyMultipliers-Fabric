package com.cartoonishvillain.vdm.commands;


import com.cartoonishvillain.vdm.VDM;
import com.cartoonishvillain.vdm.components.LevelComponent;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.MinecraftServer;

import java.util.UUID;

import static com.cartoonishvillain.vdm.components.ComponentStarter.LEVELINSTANCE;

public class deactivateMultiplierCommand {
    public static void register(CommandDispatcher<CommandSourceStack> source) {
        source.register(Commands.literal("vdm")
                .then(Commands.literal("deactivate").requires(requirement -> {
                    return requirement.hasPermission(2);
                }).then(Commands.literal("anger").executes(context -> {
                    return deactivate(context.getSource(), "anger");
                })).then(Commands.literal("blackeye").executes(context -> {
                    return deactivate(context.getSource(), "blackeye");
                })).then(Commands.literal("cannon").executes(context -> {
                    return deactivate(context.getSource(), "cannon");
                })).then(Commands.literal("venom").executes(context -> {
                    return deactivate(context.getSource(), "venom");
                })).then(Commands.literal("karmicjustice").executes(context -> {
                    return deactivate(context.getSource(), "karmicjustice");
                })).then(Commands.literal("aging").executes(context -> {
                    return deactivate(context.getSource(), "aging");
                })).then(Commands.literal("softskin").executes(context -> {
                    return deactivate(context.getSource(), "softskin");
                })).then(Commands.literal("fatigue").executes(context -> {
                    return deactivate(context.getSource(), "fatigue");
                })).then(Commands.literal("hardened").executes(context -> {
                    return deactivate(context.getSource(), "hardened");
                })).then(Commands.literal("hardened").executes(context -> {
                    return deactivate(context.getSource(), "hardened");
                })).then(Commands.literal("unstable").executes(context -> {
                    return deactivate(context.getSource(), "unstable");
                })).then(Commands.literal("kinetic").executes(context -> {
                    return deactivate(context.getSource(), "kinetic");
                })).then(Commands.literal("undying").executes(context -> {
                    return deactivate(context.getSource(), "undying");
                })).then(Commands.literal("fuelefficient").executes(context -> {
                    return deactivate(context.getSource(), "fuelefficient");
                })).then(Commands.literal("blacksmithing").executes(context -> {
                    return deactivate(context.getSource(), "blacksmithing");
                })).then(Commands.literal("warranty").executes(context -> {
                    return deactivate(context.getSource(), "warranty");
                })).then(Commands.literal("flammable").executes(context -> {
                    return deactivate(context.getSource(), "flammable");
                })).then(Commands.literal("vegetarian").executes(context -> {
                    return deactivate(context.getSource(), "vegetarian");
                })).then(Commands.literal("wrong").executes(context -> {
                    return deactivate(context.getSource(), "wrong");
                })).then(Commands.literal("pandemic").executes(context -> {
                    return deactivate(context.getSource(), "pandemic");
                })).then(Commands.literal("wild").executes(context -> {
                    return deactivate(context.getSource(), "wild");
                })).then(Commands.literal("rested").executes(context -> {
                    return deactivate(context.getSource(), "rested");
                })).then(Commands.literal("celebration").executes(context -> {
                    return deactivate(context.getSource(), "celebration");
                })).then(Commands.literal("heroic").executes(context -> {
                    return deactivate(context.getSource(), "heroic");
                })).then(Commands.literal("keystothecity").executes(context -> {
                    return deactivate(context.getSource(), "keystothecity");
                })).then(Commands.literal("inferno").executes(context -> {
                    return deactivate(context.getSource(), "inferno");
                })).then(Commands.literal("eruptiveswarm").executes(context -> {
                    return deactivate(context.getSource(), "eruptiveswarm");
                }))));

        //Temporarily Removed
//        .then(Commands.literal("shift").executes(context -> {
//        return deactivate(context.getSource(), "shift");
//        }))
    }


    private static int deactivate(CommandSourceStack context, String string){
        LevelComponent h = LEVELINSTANCE.get(context.getLevel().getLevelData());
        switch (string){
            case "inferno":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.inferno"));
                h.setInferno(false);
                break;
            case "eruptiveswarm":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.eruptiveswarm"));
                h.setEruptiveswarm(false);
                break;
            case "blackeye":
            case "black_eye":
            case "black eye":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.blackeye"));
                h.setBlackeye(false);
                break;
            case "cannon":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.cannon"));
                h.setCannon(false);
            case "venom":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.venom"));
                h.setVenom(false);
                break;
            case "shift":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.shift"));
               h.setShift(false);
                break;
            case "karmicjustice":
            case "karmic_justice":
            case "karmic justice":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.karmicjustice"));
                h.setKarmicjustice(false);
                break;
            case "aging":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.aging"));
               h.setAging(false);
                break;
            case "softskin":
            case "soft_skin":
            case "soft skin":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.softskin"));
                h.setSoftskin(false);
                break;
            case "fatigue":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.fatigue"));
                h.setFatigue(false);
                break;
            case "hardened":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.hardened"));
                h.setHardened(false);
                break;
            case "anger":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.anger"));
                h.setAnger(false);
                break;
            case "unstable":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.unstable"));
                h.setUnstable(false);
                break;
            case "kinetic":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.kinetic"));
                h.setKinetic(false);
                break;
            case "undying":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.undying"));
                h.setUndying(false);
                break;
            case "flammable":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.flammable"));
                h.setFlammable(false);
                break;
            case "fuelefficient":
            case "fuel_efficient":
            case "fuel efficient":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.fuelefficient"));
                h.setFuelefficient(false);
                break;
            case "blacksmithing":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.blacksmithing"));
                h.setBlacksmithing(false);
                break;
            case "warranty":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.warranty"));
                h.setWarranty(false);
                break;
            case "vegetarian":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.vegetarian"));
                h.setVegetarian(false);
                break;
            case "wrong":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.wrong"));
                h.setWrong(false);
                break;
            case "pandemic":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.pandemic"));
                h.setPandemic(false);
                break;
            case "celebration":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.celebration"));
                h.setCelebration(false);
                break;
            case "rested":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.rested"));
                h.setRested(false);
                break;
            case "wild":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.wild"));
                h.setWild(false);
                break;
            case "heroic":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.allon"));
                h.setBlackeye(false);
                h.setCannon(false);
                h.setVenom(false);
                h.setShift(false);
                h.setKarmicjustice(false);
                h.setAging(false);
                h.setSoftskin(false);
                h.setFatigue(false);
                h.setHardened(false);
                h.setAnger(false);
                h.setUnstable(false);
                h.setFlammable(false);
                h.setWrong(false);
                h.setVegetarian(false);
                h.setPandemic(false);
                h.setInferno(false);
                h.setEruptiveswarm(false);
                break;
            case "keystothecity":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("deactivation.villainousdifficultymultipliers.keysallon"));
                h.setKinetic(false);
                h.setUndying(false);
                h.setFuelefficient(false);
                h.setBlacksmithing(false);
                h.setWarranty(false);
                h.setWild(false);
                h.setRested(false);
                h.setCelebration(false);
                break;
            default:
                context.sendSuccess(new TranslatableComponent("deactivation.villainousdifficultymultipliers.invalid").withStyle(ChatFormatting.RED), false);
                context.sendSuccess(new TranslatableComponent("error.villainousdifficultymultipliers.listmultipliers"), false);
                break;
        }
        return 0;
    }

    private static void broadcast(MinecraftServer server, Component translationTextComponent){
        server.getPlayerList().broadcastMessage(translationTextComponent, ChatType.CHAT, UUID.randomUUID());
    }
}
