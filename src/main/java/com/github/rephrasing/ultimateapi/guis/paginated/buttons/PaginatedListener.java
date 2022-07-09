package com.github.rephrasing.ultimateapi.guis.paginated.buttons;

import com.github.rephrasing.ultimateapi.guis.paginated.UltimatePaginatedGUI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

public class PaginatedListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();
        if (!(holder instanceof UltimatePaginatedGUI)) return;

        UltimatePaginatedGUI gui = (UltimatePaginatedGUI) holder;
        UltimateButton button = gui.getButton(event.getSlot());

        if (button != null && button.getListener() != null) {
            button.getListener().onClick(event);
        }

    }


}
