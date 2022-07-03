package com.github.rephrasing.ultimateapi.guis.listeners;

import com.github.rephrasing.ultimateapi.guis.AbstractUltimateGUI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.InventoryHolder;

public abstract class UltimateGUIListener<GUI extends AbstractUltimateGUI> implements Listener {


    public abstract void onGUIClickEvent(GUI gui, InventoryClickEvent event);
    public abstract void onGUICloseEvent(GUI gui, InventoryCloseEvent event);
    public abstract void onGUIOpenEvent(GUI gui, InventoryOpenEvent event);

    public abstract Class<GUI> getGUITypeClass();


    @EventHandler
    private void onClick(InventoryClickEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();
        if (!getGUITypeClass().isInstance(holder)) return;
        GUI gui = (GUI) holder;
        onGUIClickEvent(gui, event);
    }

    @EventHandler
    private void onClose(InventoryCloseEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();
        if (!getGUITypeClass().isInstance(holder)) return;
        GUI gui = (GUI) holder;
        onGUICloseEvent(gui, event);
    }

    @EventHandler
    private void onOpen(InventoryOpenEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();
        if (!getGUITypeClass().isInstance(holder)) return;
        GUI gui = (GUI) holder;
        onGUIOpenEvent(gui, event);
    }

}
