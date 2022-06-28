package com.github.rephrasing.ultimateapi;

import com.github.rephrasing.ultimateapi.commands.UltimateCommandHandler;
import com.github.rephrasing.ultimateapi.util.Util;
import lombok.SneakyThrows;
import org.bukkit.command.CommandSender;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

public class UltimateAPI {

    private final JavaPlugin plugin;
    private final SimpleCommandMap pluginCommandMap;

    public static UltimateAPI instance;

    @SneakyThrows
    public UltimateAPI(JavaPlugin plugin) {
        this.plugin = plugin;
        instance = this;


        PluginManager manager = plugin.getServer().getPluginManager();
        SimplePluginManager simple = (SimplePluginManager) manager;
        Field commandMapField = simple.getClass().getDeclaredField("commandMap");
        commandMapField.setAccessible(true);
        pluginCommandMap = (SimpleCommandMap) commandMapField.get(simple);

        UltimateCommandHandler.registerAll();
        plugin.getLogger().info("Initiated UltimateAPI");
    }

    public static UltimateAPI getInstance() {
        if (instance == null) throw new IllegalArgumentException("Attempted to use UltimateAPI without initiating it");
        return instance;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public SimpleCommandMap getPluginCommandMap() {
        return pluginCommandMap;
    }

    public void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(Util.colorize("&8[&f" + getPlugin().getName() + "&8] &7" + message));
    }
}
