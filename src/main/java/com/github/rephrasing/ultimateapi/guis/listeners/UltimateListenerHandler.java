package com.github.rephrasing.ultimateapi.guis.listeners;

import com.github.rephrasing.ultimateapi.UltimateAPI;
import com.github.rephrasing.ultimateapi.UltimatePlugin;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;

public class UltimateListenerHandler {

    @Getter
    private static final List<AbstractGUIListener> registeredListeners = new ArrayList<>();

    public static void registerAll() {
        JavaPlugin plugin = UltimatePlugin.getInstance().getJavaPlugin();
        plugin.getServer().getPluginManager().registerEvents(new BukkitGUIListener(),plugin);
        int registeredCount = 0;
        for (Class<? extends AbstractGUIListener> aClass : new Reflections().getSubTypesOf(AbstractGUIListener.class)) {
            try {
                AbstractGUIListener listener = aClass.newInstance();
                registeredListeners.add(listener);
                UltimateAPI.getUltimateLogger().info("Registered Ultimate GUI Listener " + aClass.getSimpleName() + " for " + UltimatePlugin.getInstance().getJavaPlugin().getName());
                registeredCount++;
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (registeredCount > 0) UltimateAPI.getUltimateLogger().info(String.format("Successfully registered %s Ultimate GUI Listener(s) for %s", registeredCount, UltimatePlugin.getInstance().getJavaPlugin().getName()));
    }
}
