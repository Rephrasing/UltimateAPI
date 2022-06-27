package com.github.rephrasing.ultimateapi;

import org.bukkit.plugin.java.JavaPlugin;

public class UltimateAPI {

    private final JavaPlugin plugin;
    public static UltimateAPI instance;

    public UltimateAPI(JavaPlugin plugin) {
        this.plugin = plugin;
        instance = this;
        plugin.getLogger().info("Initiated UltimateAPI");
    }

    public static UltimateAPI getInstance() {
        if (instance == null) throw new IllegalArgumentException("Attempted to use UltimateAPI without initiating it");
        return instance;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }
}
