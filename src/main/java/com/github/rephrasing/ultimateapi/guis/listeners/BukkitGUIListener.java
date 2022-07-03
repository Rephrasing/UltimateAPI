package com.github.rephrasing.ultimateapi.guis.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.InventoryHolder;

public class BukkitGUIListener implements Listener {

    @EventHandler
    private void onClick(InventoryClickEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();
        UltimateListenerHandler.getRegisteredListeners().forEach(listener -> {
            if (listener.getGUITypeClass().isInstance(holder)) listener.onGUIClickEvent(event, listener.getGUITypeClass().cast(holder));
        });
    }

    @EventHandler
    private void onClose(InventoryCloseEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();
        UltimateListenerHandler.getRegisteredListeners().forEach(listener -> {
            if (listener.getGUITypeClass().isInstance(holder)) listener.onGUICloseEvent(event, listener.getGUITypeClass().cast(holder));
        });
    }

    @EventHandler
    private void onOpen(InventoryOpenEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();
        UltimateListenerHandler.getRegisteredListeners().forEach(listener -> {
            if (listener.getGUITypeClass().isInstance(holder)) listener.onGUIOpenEvent(event, listener.getGUITypeClass().cast(holder));
        });
    }




}


