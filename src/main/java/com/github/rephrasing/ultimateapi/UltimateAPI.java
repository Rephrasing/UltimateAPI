package com.github.rephrasing.ultimateapi;

import com.github.rephrasing.ultimateapi.commands.UltimateCommandHandler;
import com.github.rephrasing.ultimateapi.util.Utils;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.Validate;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class UltimateAPI {

    public static UltimateAPI instance;

    @Getter
    private static final UltimateLogger ultimateLogger = new UltimateLogger();

    @Setter
    private boolean usePluginPrefix = false;

    public UltimateAPI(@NotNull JavaPlugin plugin) {
        Validate.notNull(plugin, "Attempted to initiate UltimateAPI but a null plugin was provided.");
        Validate.isTrue(instance == null, "Attempted to initiate UltimateAPI twice! (Likely conducted outside of this plugin)");
        new UltimatePlugin(plugin);
        instance = this;

        UltimateCommandHandler.registerAll();
        getUltimateLogger().info("Initiated UltimateAPI by " + plugin.getName());
    }

    public static UltimateAPI getInstance() {
        if (instance == null) throw new IllegalArgumentException("Attempted to use UltimateAPI without initiating it");
        return instance;
    }

    public void sendMessage(CommandSender sender, String message) {
        String newMessage = usePluginPrefix ? "&8[&f" + UltimatePlugin.getInstance().getJavaPlugin().getName() + "&8] &7" + message : message;
        sender.sendMessage(Utils.format(newMessage));
    }
}
