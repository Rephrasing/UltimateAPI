package com.github.rephrasing.ultimateapi.guis;

import com.github.rephrasing.ultimateapi.Util;
import com.github.rephrasing.ultimateapi.guis.items.ItemCollection;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

@Getter
public abstract class AbstractGUI implements InventoryHolder {

    private final Inventory inventory;
    private final Player player;

    private String name;

    AbstractGUI(Player player, int size) {
        this.player = player;
        this.inventory = Bukkit.createInventory(this, size);
    }

    AbstractGUI(Player player, String name, int size) {
        this.player = player;
        this.name = name;
        this.inventory = Bukkit.createInventory(this, size, Util.colorize(name));
    }

    public void refreshGUI() {
        player.closeInventory();
        player.openInventory(inventory);
    }

    public void closeGUI() {
        player.closeInventory();
    }

    public void setItem(int slot, ItemCollection item) {
        inventory.setItem(slot, item.getInstance());
    }

    public void addItem(ItemCollection item) {
        inventory.setItem(inventory.firstEmpty(), item.getInstance());
    }

    public void removeItem(int slot) {
        ItemStack item = inventory.getItem(slot);
        if (item == null)
            throw new IllegalArgumentException("Cannot remove a null org.bukkit.inventory.ItemStack from a GUI");
        inventory.removeItem();
    }

}
