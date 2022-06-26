package com.github.rephrasing.ultimateapi.guis.items;

import com.github.rephrasing.ultimateapi.Util;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class ItemCollection {

    private final ItemStack instance;
    private final Material type;
    private final int count;

    private String name;
    private List<String> lore;
    private HashMap<Enchantment, Integer> enchantments;
    private List<ItemFlag> itemFlags;

    public ItemCollection(Material type) {
        this.type = type;
        this.instance = new ItemStack(type, 1);
        this.count = 1;
    }
    public ItemCollection(Material type, int count) {
        if (count > 64)
            throw new IndexOutOfBoundsException("Cannot create a org.bukkit.inventory.ItemStack with count larger than 64");
        this.type = type;
        this.instance = new ItemStack(type, count);
        this.count = count;
    }


    public ItemCollection(String name, Material type) {
        this.type = type;
        this.name = name;
        this.instance = new ItemStack(type, 1);
        this.count = 1;
    }
    public ItemCollection(String name, Material type, int count) {
        if (count > 64)
            throw new IndexOutOfBoundsException("Cannot create a org.bukkit.inventory.ItemStack with count larger than 64");
        this.type = type;
        this.name = name;
        this.instance = new ItemStack(type, count);
        this.count = count;
    }


    public ItemCollection(String name, Material type, List<ItemFlag> flags) {
        this.type = type;
        this.name = name;
        this.instance = new ItemStack(type, 1);
        this.itemFlags = flags;
        this.count = 1;
    }
    public ItemCollection(String name, Material type, int count, List<ItemFlag> flags) {
        if (count > 64)
            throw new IndexOutOfBoundsException("Cannot create a org.bukkit.inventory.ItemStack with count larger than 64");
        this.type = type;
        this.name = name;
        this.instance = new ItemStack(type, count);
        this.count = count;
        this.itemFlags = flags;
    }

    public void setName(String name) {
        String colorized = Util.colorize(name);
        ItemMeta meta = getInstance().getItemMeta();
        meta.setDisplayName(colorized);
        getInstance().setItemMeta(meta);
    }

    public void setLore(List<String> lore) {
        List<String> colorizedLore = new ArrayList<>();
        for (String noColor : lore) {
            colorizedLore.add(Util.colorize(noColor));
        }

        ItemMeta meta = getInstance().getItemMeta();
        meta.setLore(colorizedLore);
        getInstance().setItemMeta(meta);
    }

    public void addLore(String name) {
        if (lore == null) lore = new ArrayList<>();
        lore.add(Util.colorize(name));
        ItemMeta meta = getInstance().getItemMeta();
        meta.setLore(lore);
        getInstance().setItemMeta(meta);
    }

    public void setEnchantments(HashMap<Enchantment, Integer> enchants) {
        ItemMeta meta = getInstance().getItemMeta();
        for (Map.Entry<Enchantment, Integer> entry : enchants.entrySet()) {
            meta.addEnchant(entry.getKey(), entry.getValue(), true);
        }
        getInstance().setItemMeta(meta);
    }

    public void addEnchant(Enchantment enchantment, int level) {
        ItemMeta meta = getInstance().getItemMeta();
        meta.addEnchant(enchantment, level, true);
        getInstance().setItemMeta(meta);
    }

    public void setItemFlags(List<ItemFlag> itemFlags) {
        ItemMeta meta = getInstance().getItemMeta();
        for (ItemFlag flag : itemFlags) {
            meta.addItemFlags(flag);
        }
        getInstance().setItemMeta(meta);
    }

    public void addItemFlag(ItemFlag flag) {
        ItemMeta meta = getInstance().getItemMeta();
        meta.addItemFlags(flag);
        getInstance().setItemMeta(meta);
    }


}
