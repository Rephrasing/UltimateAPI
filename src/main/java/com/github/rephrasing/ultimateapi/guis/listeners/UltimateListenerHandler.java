package com.github.rephrasing.ultimateapi.guis.listeners;

import com.github.rephrasing.ultimateapi.UltimateAPI;
import com.github.rephrasing.ultimateapi.UltimatePlugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

public class UltimateListenerHandler {

    public static void registerAll() {
        int registeredCount = 0;
        for (Class<? extends UltimateGUIListener> aClass : new Reflections().getSubTypesOf(UltimateGUIListener.class)) {
            try {
                JavaPlugin plugin = UltimatePlugin.getInstance().getJavaPlugin();
                plugin.getServer().getPluginManager().registerEvents(aClass.newInstance(),plugin);
                UltimateAPI.getUltimateLogger().info("Registered Ultimate GUI Listener " + aClass.getSimpleName() + " for " + UltimatePlugin.getInstance().getJavaPlugin().getName());
                registeredCount++;
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (registeredCount > 0) UltimateAPI.getUltimateLogger().info(String.format("Successfully registered %s Ultimate GUI Listener(s) for %s", registeredCount, UltimatePlugin.getInstance().getJavaPlugin().getName()));
    }
}
