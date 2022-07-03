package com.github.rephrasing.ultimateapi.guis.listeners;

import com.github.rephrasing.ultimateapi.guis.AbstractUltimateGUI;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public abstract class AbstractGUIListener implements Listener {


    public abstract void onGUIClickEvent(InventoryClickEvent event, AbstractUltimateGUI gui);
    public abstract void onGUICloseEvent(InventoryCloseEvent event, AbstractUltimateGUI gui);
    public abstract void onGUIOpenEvent(InventoryOpenEvent event, AbstractUltimateGUI gui);

    public abstract Class<? extends AbstractUltimateGUI> getGUITypeClass();

}
