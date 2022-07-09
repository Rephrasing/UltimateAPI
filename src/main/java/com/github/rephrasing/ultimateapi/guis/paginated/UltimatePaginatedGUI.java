package com.github.rephrasing.ultimateapi.guis.paginated;

import com.github.rephrasing.ultimateapi.UltimatePlugin;
import com.github.rephrasing.ultimateapi.guis.items.UltimateItemBuilder;
import com.github.rephrasing.ultimateapi.guis.paginated.buttons.PaginatedListener;
import com.github.rephrasing.ultimateapi.guis.paginated.buttons.UltimateButton;
import com.github.rephrasing.ultimateapi.util.Utils;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.Collections;
import java.util.HashMap;

public class UltimatePaginatedGUI implements InventoryHolder {

    private static PaginatedListener listener;
    private final HashMap<Integer, UltimateButton> items = new HashMap<>();
    private final HashMap<Integer, UltimateButton> toolbarItems = new HashMap<>();
    private int currentPage = 0;

    private String name;

    public UltimatePaginatedGUI(String name) {
        this.name = Utils.format(name);
        prepare();
    }

    public void setName(String name) {
        this.name = Utils.format(name);
    }

    public String getName() {
        return name;
    }

    public void addButton(UltimateButton button) {
        int slot = 0;

        if (!items.isEmpty()) {

            int highestSlot = -1;
            for (int itemSlot : items.keySet()) {
                if (itemSlot > highestSlot) highestSlot = itemSlot;
            }
            slot = highestSlot;
        }

        items.put(slot, button);
    }

    public void setButton(int slot, UltimateButton button) {
        items.put(slot, button);
    }

    public void removeButton(int slot) {
        items.remove(slot);
    }

    public UltimateButton getButton(int slot) {
        if (slot < 45) return items.get(slot);
        return toolbarItems.get(slot - 45);
    }

    public void setToolbarButton(int slot, UltimateButton button) {
        Validate.isTrue(slot < 0 || slot > 8, "Cannot set a toolbar button in a non-toolbar slot (0-8)");
        toolbarItems.put(slot, button);
    }

    public void removeToolbarButton(int slot) {
        Validate.isTrue(slot < 0 || slot > 8, "Cannot remove a toolbar button from a non-toolbar slot (0-8)");
        toolbarItems.remove(slot);
    }

    private boolean nextPage() {
        if (currentPage < getFinalPage()) {
            currentPage++;
            return true;
        }
        return false;
    }

    private boolean previousPage() {
        if (currentPage > 0) {
            currentPage--;
            return true;
        }
        return false;
    }

    private int getFinalPage(){
        // Get the highest slot number.
        int slot = 0;
        for(int nextSlot : items.keySet()){
            if(nextSlot > slot){
                slot = nextSlot;
            }
        }

        // Add one to make the math easier.
        double highestSlot = slot + 1;

        // Divide by 45 and round up to get the page number.
        // Then subtract one to convert it to an index.
        return (int) Math.ceil(highestSlot / (double) 45) - 1;
    }


    public void refreshInventory(HumanEntity player) {
        player.closeInventory();
        player.openInventory(getInventory());
    }


    @Override
    public Inventory getInventory() {
        Inventory inv = Bukkit.createInventory(this, (getFinalPage() > 0) ? 54 : 45, name);

        UltimateButton backButton = new UltimateButton(UltimateItemBuilder.start(Material.ARROW).name("&fPrevious page").build());
        UltimateButton pageIndicator = new UltimateButton(
                UltimateItemBuilder.start(Material.NAME_TAG)
                .name("&7Current page: " + (currentPage + 1))
                .lore("&fMax pages: " + (getFinalPage() + 1))
                .build()
        );

        UltimateButton nextButton = new UltimateButton(UltimateItemBuilder.start(Material.ARROW).name("&fNext page").build());

        backButton.setListener(event -> {
            event.setCancelled(true);
            UltimatePaginatedGUI menu = (UltimatePaginatedGUI) event.getClickedInventory().getHolder();

            if (!menu.previousPage()) {
                new UltimateItemBuilder(backButton.getItem()).lore("&cNo previous pages available");
            }
            refreshInventory(event.getWhoClicked());
        });

        nextButton.setListener(event -> {
            event.setCancelled(true);
            UltimatePaginatedGUI menu = (UltimatePaginatedGUI) event.getClickedInventory().getHolder();

            if (!menu.nextPage()) {
                new UltimateItemBuilder(backButton.getItem()).lore("&cNo next pages available");
            }
            refreshInventory(event.getWhoClicked());

        });

        if (currentPage > 0) {
            toolbarItems.put(3, backButton);
        }
        if (getFinalPage() > 0) {
            toolbarItems.put(4, pageIndicator);
        }
        if (currentPage < getFinalPage()) {
            toolbarItems.put(5, nextButton);
        }

        int counter = 0;
        for (int key = (currentPage * 45); key <= Collections.max(items.keySet()); key++) {
            if (counter >= 45) break;

            if (items.containsKey(key)) {
                inv.setItem(counter, items.get(key).getItem());
            }
            counter++;
        }

        for (int toolbarItem : toolbarItems.keySet()) {
            int rawSlot = toolbarItem + 45;
            inv.setItem(rawSlot, toolbarItems.get(toolbarItem).getItem());
        }

        return inv;
    }

    public void prepare() {
        if (listener == null) {
            listener = new PaginatedListener();
            UltimatePlugin.getInstance().getJavaPlugin().getServer().getPluginManager().registerEvents(listener, UltimatePlugin.getInstance().getJavaPlugin());
        }
    }


}


