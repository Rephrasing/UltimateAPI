package com.github.rephrasing.ultimateapi;

import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.logging.Logger;

public class UltimatePlugin {

    @Getter
    private static UltimatePlugin instance;
    @Getter
    private final JavaPlugin javaPlugin;
    @Getter
    private final SimpleCommandMap simpleCommandMap;

    @SneakyThrows
    protected UltimatePlugin(@NotNull JavaPlugin plugin) {
        if (instance != null) throw new IllegalArgumentException("Tried to initiate an UltimatePlugin but another plugin is already using Ultimate API");
        instance = this;
        this.javaPlugin = plugin;
        PluginManager manager = plugin.getServer().getPluginManager();
        SimplePluginManager simple = (SimplePluginManager) manager;
        Field commandMapField = simple.getClass().getDeclaredField("commandMap");
        commandMapField.setAccessible(true);
        this.simpleCommandMap = (SimpleCommandMap) commandMapField.get(simple);
    }

    public Logger getLogger() {
        return javaPlugin.getLogger();
    }


}
