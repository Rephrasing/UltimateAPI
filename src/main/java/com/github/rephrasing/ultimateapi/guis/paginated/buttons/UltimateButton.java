package com.github.rephrasing.ultimateapi.guis.paginated.buttons;

import org.bukkit.inventory.ItemStack;

public class UltimateButton {

    private ButtonListener listener;
    private final ItemStack itemStack;


    public UltimateButton(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public void setListener(ButtonListener listener) {
        this.listener = listener;
    }

    public ButtonListener getListener() {
        return listener;
    }

    public ItemStack getItem() {
        return itemStack;
    }
}
