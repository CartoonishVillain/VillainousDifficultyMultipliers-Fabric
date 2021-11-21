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

public class activateMultiplierCommand {
    public static void register(CommandDispatcher<CommandSourceStack> source) {
        source.register(Commands.literal("vdm")
                .then(Commands.literal("activate").requires(requirement -> {
                    return requirement.hasPermission(2);
                }).then(Commands.literal("anger").executes(context -> {
                    return activate(context.getSource(), "anger");
                })).then(Commands.literal("blackeye").executes(context -> {
                    return activate(context.getSource(), "blackeye");
                })).then(Commands.literal("cannon").executes(context -> {
                    return activate(context.getSource(), "cannon");
                })).then(Commands.literal("venom").executes(context -> {
                    return activate(context.getSource(), "venom");
                })).then(Commands.literal("karmicjustice").executes(context -> {
                    return activate(context.getSource(), "karmicjustice");
                })).then(Commands.literal("aging").executes(context -> {
                    return activate(context.getSource(), "aging");
                })).then(Commands.literal("softskin").executes(context -> {
                    return activate(context.getSource(), "softskin");
                })).then(Commands.literal("fatigue").executes(context -> {
                    return activate(context.getSource(), "fatigue");
                })).then(Commands.literal("hardened").executes(context -> {
                    return activate(context.getSource(), "hardened");
                })).then(Commands.literal("hardened").executes(context -> {
                    return activate(context.getSource(), "hardened");
                })).then(Commands.literal("unstable").executes(context -> {
                    return activate(context.getSource(), "unstable");
                })).then(Commands.literal("kinetic").executes(context -> {
                    return activate(context.getSource(), "kinetic");
                })).then(Commands.literal("undying").executes(context -> {
                    return activate(context.getSource(), "undying");
                }))
                        //Can't figure out how to change values. TODO: Fix this the issue mentioned before this.
//                        .then(Commands.literal("fuelefficient").executes(context -> {
//                    return activate(context.getSource(), "fuelefficient");
//                }))
                        .then(Commands.literal("blacksmithing").executes(context -> {
                    return activate(context.getSource(), "blacksmithing");
                })).then(Commands.literal("warranty").executes(context -> {
                    return activate(context.getSource(), "warranty");
                })).then(Commands.literal("flammable").executes(context -> {
                    return activate(context.getSource(), "flammable");
                })).then(Commands.literal("vegetarian").executes(context -> {
                    return activate(context.getSource(), "vegetarian");
                })).then(Commands.literal("wrong").executes(context -> {
                    return activate(context.getSource(), "wrong");
                })).then(Commands.literal("pandemic").executes(context -> {
                    return activate(context.getSource(), "pandemic");
                })).then(Commands.literal("wild").executes(context -> {
                    return activate(context.getSource(), "wild");
                })).then(Commands.literal("rested").executes(context -> {
                    return activate(context.getSource(), "rested");
                })).then(Commands.literal("celebration").executes(context -> {
                    return activate(context.getSource(), "celebration");
                })).then(Commands.literal("heroic").executes(context -> {
                    return activate(context.getSource(), "heroic");
                })).then(Commands.literal("keystothecity").executes(context -> {
                    return activate(context.getSource(), "keystothecity");
                })).then(Commands.literal("inferno").executes(context -> {
                    return activate(context.getSource(), "inferno");
                })).then(Commands.literal("eruptiveswarm").executes(context -> {
                    return activate(context.getSource(), "eruptiveswarm");
                }))));

        //temporarily removed.
//        .then(Commands.literal("shift").executes(context -> {
//            return activate(context.getSource(), "shift");
//        }))
    }


    private static int activate(CommandSourceStack context, String string){
        LevelComponent h = LEVELINSTANCE.get(context.getLevel().getLevelData());
        switch (string){
            case "inferno":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.inferno"));
                h.setInferno(true);
                break;
            case "eruptiveswarm":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.eruptiveswarm"));
                h.setEruptiveswarm(true);
                break;
            case "blackeye":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.blackeye"));
                h.setBlackeye(true);
                break;
            case "cannon":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.cannon"));
                h.setCannon(true);
                break;
            case "venom":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.venom"));
                h.setVenom(true);
                break;
            case "shift":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.shift"));
                h.setShift(true);
                break;
            case "karmicjustice":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.karmicjustice"));
                h.setKarmicjustice(true);
                break;
            case "aging":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.aging"));
                h.setAging(true);
                break;
            case "softskin":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.softskin"));
                h.setSoftskin(true);
                break;
            case "fatigue":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.fatigue"));
                h.setFatigue(true);
                break;
            case "hardened":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.hardened"));
                h.setHardened(true);
                break;
            case "anger":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.anger"));
                h.setAnger(true);
                break;
            case "unstable":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.unstable"));
                h.setUnstable(true);
                break;
            case "kinetic":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.kinetic"));
                h.setKinetic(true);
                break;
            case "undying":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.undying"));
                h.setUndying(true);
                break;
            case "fuelefficient":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.fuelefficient"));
                h.setFuelefficient(true);
                break;
            case "blacksmithing":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.blacksmithing"));
                h.setBlacksmithing(true);
                break;
            case "warranty":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.warranty"));
                h.setWarranty(true);
                break;
            case "flammable":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.flammable"));
                h.setFlammable(true);
                break;
            case "vegetarian":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.vegetarian"));
                h.setVegetarian(true);
                break;
            case "wrong":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.wrong"));
                h.setWrong(true);
                break;
            case "pandemic":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.pandemic"));
                h.setPandemic(true);
                break;
            case "wild":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.wild"));
                h.setWild(true);
                break;
            case "rested":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.rested"));
                h.setRested(true);
                break;
            case "celebration":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.celebration"));
                h.setCelebration(true);
                break;
            case "heroic":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.allwarning").withStyle(ChatFormatting.RED, ChatFormatting.BOLD));
                broadcast(context.getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.allon"));
                h.setBlackeye(true);
                h.setCannon(true);
                h.setVenom(true);
                h.setShift(true);
                h.setKarmicjustice(true);
                h.setAging(true);
                h.setSoftskin(true);
                h.setFatigue(true);
                h.setHardened(true);
                h.setAnger(true);
                h.setUnstable(true);
                h.setFlammable(true);
                h.setWrong(true);
                h.setVegetarian(true);
                h.setPandemic(true);
                h.setInferno(true);
                h.setEruptiveswarm(true);
                break;
            case "keystothecity":
                broadcast(context.getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.keysallwarning").withStyle(ChatFormatting.BLUE, ChatFormatting.BOLD));
                broadcast(context.getLevel().getServer(), new TranslatableComponent("activation.villainousdifficultymultipliers.keysallon"));
                h.setKinetic(true);
                h.setUndying(true);
                h.setFuelefficient(true);
                h.setBlacksmithing(true);
                h.setWarranty(true);
                h.setWild(true);
                h.setRested(true);
                h.setCelebration(true);
                break;
            default:
                context.sendFailure(new TranslatableComponent("activation.villainousdifficultymultipliers.invalid").withStyle(ChatFormatting.RED));
                context.sendFailure(new TranslatableComponent("error.villainousdifficultymultipliers.listmultipliers"));
                break;
        }
        return 0;
    }

    private static void broadcast(MinecraftServer server, Component translationTextComponent){
        server.getPlayerList().broadcastMessage(translationTextComponent, ChatType.CHAT, UUID.randomUUID());
    }
}