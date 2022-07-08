package com.github.rephrasing.ultimateapi.commands.tab;

import com.github.rephrasing.ultimateapi.commands.UltimateCommand;
import com.github.rephrasing.ultimateapi.commands.senders.UltimateCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public abstract class UltimateTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String alias, String[] args) {
        if (!(command instanceof UltimateCommand)) return null;
        return onTabComplete(new UltimateCommandSender(sender), (UltimateCommand) command, alias, args);
    }

    public abstract List<String> onTabComplete(UltimateCommandSender sender, UltimateCommand command, String alias, String[] args);
}
