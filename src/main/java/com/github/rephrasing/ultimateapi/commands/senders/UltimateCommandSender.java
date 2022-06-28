package com.github.rephrasing.ultimateapi.commands.senders;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

public class UltimateCommandSender {

    private final CommandSender is;

    public UltimateCommandSender(CommandSender sender) {
        if (!(sender instanceof ConsoleCommandSender) && !(sender instanceof Player)) throw new IllegalStateException("Attempted to create UltimateCommandSender from unsupported type");
        this.is = sender;

    }

    public void sendMessage(String message) {
        is.sendMessage(message);
    }

    public Optional<Player> asPlayer() {
        if (isPlayer()) return Optional.of((Player) is);
        return Optional.empty();
    }

    public boolean isConsole() {
        return is instanceof ConsoleCommandSender;
    }

    public boolean isPlayer() {
        return is instanceof Player;
    }

}
