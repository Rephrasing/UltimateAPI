package com.github.rephrasing.ultimateapi.guis;

import com.github.rephrasing.ultimateapi.util.Utils;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.*;

@Getter
public abstract class UltimateGUI implements InventoryHolder {

    @Setter
    private String name;
    private final int size;

    private final HashMap<Integer, ItemStack> guiItems = new HashMap<>();
    private final List<UltimateSubGUI> subGUIs;

    public UltimateGUI(String name, int size, UltimateSubGUI... guis) {
        this.subGUIs = Arrays.asList(guis);
        this.name = name;
        this.size = size;
    }

    public UltimateGUI(String name, int size) {
        this.subGUIs = new ArrayList<>();
        this.name = name;
        this.size = size;
    }

    public abstract void handle(InventoryClickEvent event);

    public void setItem(int slot, ItemStack item) {
        Validate.isTrue(slot > size - 1, "Tried setting an item in an out of bounds index! value: " + slot + ", gui size: " + size);
        guiItems.put(slot, item);
    }

    public void addItem(ItemStack item) {
        int max = Collections.max(guiItems.keySet());
        Validate.isTrue(max > size - 1, "Tried adding an item to a full GUI! index: " + max + ", gui size: " + size);
        setItem(max, item);
    }

    @Nullable
    public ItemStack getItem(int slot) {
        return guiItems.get(slot);
    }

    public void removeItem(int slot) {
        guiItems.remove(slot);
    }


    @Override
    public Inventory getInventory() {
        Inventory inv = Bukkit.createInventory(this, size, Utils.format(name));

        for (int i : guiItems.keySet()) {
            inv.setItem(i, guiItems.get(i));
        }
        return inv;
    }
}
