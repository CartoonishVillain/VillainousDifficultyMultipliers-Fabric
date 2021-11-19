package com.cartoonishvillain.vdm.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class CommandManager {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher){
        LiteralCommandNode<CommandSourceStack> commander = dispatcher.register(Commands.literal("vdm").then(com.cartoonishvillain.vdm.commands.helpCommand.register(dispatcher)).then(com.cartoonishvillain.vdm.commands.listCommand.register(dispatcher)));    }
}
