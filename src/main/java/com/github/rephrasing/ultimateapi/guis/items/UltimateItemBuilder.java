package com.github.rephrasing.ultimateapi.guis.items;

import com.github.rephrasing.ultimateapi.util.Utils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class UltimateItemBuilder {

    private final ItemStack itemStack;

    public UltimateItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public static UltimateItemBuilder start(Material material) {
        return new UltimateItemBuilder(new ItemStack(material));
    }

    public static UltimateItemBuilder start(Material material, short attributes) {
        return new UltimateItemBuilder(new ItemStack(material, attributes));
    }

    public UltimateItemBuilder name(String name) {
        ItemMeta stackMeta = itemStack.getItemMeta();
        stackMeta.setDisplayName(Utils.format(name));
        itemStack.setItemMeta(stackMeta);
        return this;
    }

    public UltimateItemBuilder amount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    public UltimateItemBuilder lore(String... lore) {
        for (int i = 0; i < lore.length; i++) {
            lore[i] = Utils.format(lore[i]);
        }
        ItemMeta stackMeta = itemStack.getItemMeta();
        stackMeta.setLore(Arrays.asList(lore));
        itemStack.setItemMeta(stackMeta);
        return this;
    }

    public UltimateItemBuilder lore(List<String> lore){
        for(int i = 0; i < lore.size(); i++){
            lore.set(i, Utils.format(lore.get(i)));
        }

        ItemMeta stackMeta = itemStack.getItemMeta();
        stackMeta.setLore(lore);
        itemStack.setItemMeta(stackMeta);
        return this;
    }

    public UltimateItemBuilder durability(short durability) {
        itemStack.setDurability(durability);
        return this;
    }

    public UltimateItemBuilder enchantment(Enchantment enchantment, int level) {
        ItemMeta stackMeta = itemStack.getItemMeta();
        stackMeta.addEnchant(enchantment, level, true);
        itemStack.setItemMeta(stackMeta);
        return this;
    }

    public UltimateItemBuilder enchantments(HashMap<Enchantment, Integer> enchantments) {
        ItemMeta stackMeta = itemStack.getItemMeta();
        enchantments.forEach((enchantment, integer) -> stackMeta.addEnchant(enchantment, integer, true));
        itemStack.setItemMeta(stackMeta);
        return this;
    }

    public UltimateItemBuilder itemFlags(ItemFlag... itemFlags) {
        ItemMeta stackMeta = itemStack.getItemMeta();
        stackMeta.addItemFlags(itemFlags);
        itemStack.setItemMeta(stackMeta);
        return this;
    }

    public UltimateItemBuilder itemFlags(List<ItemFlag> itemFlags) {
        ItemMeta stackMeta = itemStack.getItemMeta();
        itemFlags.forEach(stackMeta::addItemFlags);
        itemStack.setItemMeta(stackMeta);
        return this;
    }


    public ItemStack build() {
        return itemStack;
    }


}
