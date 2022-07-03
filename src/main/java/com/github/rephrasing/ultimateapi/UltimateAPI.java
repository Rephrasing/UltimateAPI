package com.github.rephrasing.ultimateapi;

import com.github.rephrasing.ultimateapi.commands.UltimateCommandHandler;
import com.github.rephrasing.ultimateapi.guis.listeners.UltimateListenerHandler;
import com.github.rephrasing.ultimateapi.util.Utils;
import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;


public class UltimateAPI {

    public static UltimateAPI instance;

    @Getter
    private static final UltimateLogger ultimateLogger = new UltimateLogger();

    public UltimateAPI(@NotNull JavaPlugin plugin) {
        if (plugin == null) throw new IllegalArgumentException("Attempted to initiate UltimateAPI but found a null plugin was provided.");
        new UltimatePlugin(plugin);
        if (instance != null) throw new IllegalArgumentException("Attempted to initiate UltimateAPI twice! (Likely conducted outside of this plugin)");
        instance = this;

        UltimateCommandHandler.registerAll();
        UltimateListenerHandler.registerAll();
        getUltimateLogger().info("Initiated UltimateAPI by " + plugin.getName());
    }

    public static UltimateAPI getInstance() {
        if (instance == null) throw new IllegalArgumentException("Attempted to use UltimateAPI without initiating it");
        return instance;
    }

    public void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(Utils.colorize("&8[&f" + UltimatePlugin.getInstance().getJavaPlugin().getName() + "&8] &7" + message));
    }
}
