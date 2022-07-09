package com.github.rephrasing.ultimateapi.guis.listeners;

import com.github.rephrasing.ultimateapi.guis.UltimateGUI;
import com.github.rephrasing.ultimateapi.guis.UltimateSubGUI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

public class BukkitGUIListener implements Listener {

    @EventHandler
    private void onClick(InventoryClickEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();
        if (holder instanceof UltimateGUI) {
            ((UltimateGUI) holder).handle(event);
            return;
        }
        if (holder instanceof UltimateSubGUI) {
            ((UltimateSubGUI) holder).handle(event);
        }
    }

}


