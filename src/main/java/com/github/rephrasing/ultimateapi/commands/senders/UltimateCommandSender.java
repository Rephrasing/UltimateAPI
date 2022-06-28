package com.github.rephrasing.ultimateapi.commands.senders;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class UltimateCommandSender {

    private final CommandSender is;

    public UltimateCommandSender(CommandSender sender) {
        if (!(sender instanceof ConsoleCommandSender) && !(sender instanceof Player)) throw new IllegalStateException("Attempted to create UltimateCommandSender from unsupported type");
        this.is = sender;

    }

    public void sendMessage(String message) {
        is.sendMessage(message);
    }

    public Player asPlayer() {
        return (Player) is;
    }
}
