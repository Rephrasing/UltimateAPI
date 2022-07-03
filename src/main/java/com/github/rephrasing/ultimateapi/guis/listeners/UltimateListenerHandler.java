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
        int registeredCount = 0;
        for (Class<? extends AbstractGUIListener> aClass : new Reflections().getSubTypesOf(AbstractGUIListener.class)) {
            try {
                JavaPlugin plugin = UltimatePlugin.getInstance().getJavaPlugin();
                AbstractGUIListener listener = aClass.newInstance();
                plugin.getServer().getPluginManager().registerEvents(listener,plugin);
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
