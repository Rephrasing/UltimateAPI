package com.github.rephrasing.ultimateapi.commands;

import com.github.rephrasing.ultimateapi.UltimateAPI;
import com.github.rephrasing.ultimateapi.UltimatePlugin;
import com.github.rephrasing.ultimateapi.commands.annotations.UltimateCommandParams;
import com.github.rephrasing.ultimateapi.commands.annotations.UltimateCommandSettings;
import com.github.rephrasing.ultimateapi.commands.senders.UltimateCommandSender;
import com.github.rephrasing.ultimateapi.commands.tab.UltimateTabCompleter;
import lombok.Setter;
import org.apache.commons.lang.Validate;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents an UltimateCommand
 * <p>This API is made to ease the process of creating a command in spigot (plugin.yml, registering it, etc.)</p>
 *
 * <p><b>This class must be annotated with {@link UltimateCommandParams} & {@link UltimateCommandSettings}</b></p>
 *
 * <p>{@link UltimateCommandParams} The parameters for the command (name, aliases)</p>
 * {@link UltimateCommandSettings} The settings for the command (source, permission)
 *
 */
public abstract class UltimateCommand extends Command implements PluginIdentifiableCommand {

    private final UltimateCommandParams params;
    private final UltimateCommandSettings settings;

    @Setter
    private UltimateTabCompleter ultimateTabCompleter;

    protected UltimateCommand() {
        super("");
        this.params = getClass().getAnnotation(UltimateCommandParams.class);
        this.settings = getClass().getAnnotation(UltimateCommandSettings.class);

        if (params == null || settings == null) throw new CommandException("Cannot create an UltimateCommand implementation without the required Annotations");
        if (params.name() == null) throw new CommandException("Cannot create an UltimateCommand implementation without a name for the command");
        setName(params.name());
        if (params.aliases().split(",").length > 0) setAliases(Arrays.asList(params.aliases().split(",")));

        if (!settings.permission().isEmpty()) setPermission(settings.permission());
    }

    protected UltimateCommand(UltimateTabCompleter ultimateTabCompleter) {
        this();
        this.ultimateTabCompleter = ultimateTabCompleter;
    }

    public abstract String execute(@NotNull UltimateCommandSender sender, @NotNull String[] args);

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!getPlugin().isEnabled()) return false;
        if (!matches(commandLabel)) return false;

        if (settings.source() == UltimateCommandSource.CONSOLE && sender instanceof Player) {
            UltimateAPI.getInstance().sendMessage(sender, "&cYou cannot use this command in-game");
            return true;
        }
        if (settings.source() == UltimateCommandSource.IN_GAME && sender instanceof ConsoleCommandSender) {
            UltimateAPI.getInstance().sendMessage(sender, "&cYou cannot use this command in console");
            return true;
        }

        if (!testPermission(sender)) return true;

        String result = this.execute(new UltimateCommandSender(sender), args);
        if (result != null) UltimateAPI.getInstance().sendMessage(sender, result);
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        Validate.notNull(sender, "Sender cannot be null");
        Validate.notNull(args, "Arguments cannot be null");
        Validate.notNull(alias, "Alias cannot be null");

        if (ultimateTabCompleter != null) return ultimateTabCompleter.onTabComplete(sender, this, alias, args);
        return new ArrayList<>();
    }

    private boolean matches(String label) {
        if (params.aliases().split(",").length < 1) return getName().equalsIgnoreCase(label);

        if (params.aliases().split(",").length > 0) {

            for (String alias : params.aliases().split(",")) {
                if (alias.equalsIgnoreCase(label) || getName().equalsIgnoreCase(label)) return true;
            }
        }
        return false;
    }

    @Override
    public Plugin getPlugin() {
        return UltimatePlugin.getInstance().getJavaPlugin();
    }
}
