package com.github.rephrasing.ultimateapi.guis.items;

import lombok.Getter;
import org.bukkit.enchantments.Enchantment;

@Getter
public class EnchantmentsWrapper {

    public static String nameOf(Enchantment enchantment) {

        switch (enchantment.getId()) {
            case 0: return "Protection";
            case 1: return "Fire Protection";
            case 2: return "Feather Falling";
            case 3: return "Blast Protection";
            case 4: return "Projectile Protection";
            case 5: return "Respiration";
            case 6: return "Aqua Affinity";
            case 7: return "Thorns";
            case 8: return "Depth Strider";

            case 16: return "Sharpness";
            case 17: return "Smite";
            case 18: return "Bane of Arthropods";
            case 19: return "Knockback";
            case 20: return "Fire Aspect";
            case 21: return "Looting";

            case 32: return "Efficiency";
            case 33: return "Silk touch";
            case 34: return "Unbreaking";
            case 35: return "Fortune";

            case 48: return "Power";
            case 49: return "Punch";
            case 50: return "Flame";
            case 51: return "Infinity";

            case 61: return "Luck of the Sea";
            case 62: return "Lure";
        }
        return null;
    }

    public static Enchantment valueOf(String value) {
        if (value.equalsIgnoreCase("protection")) {
            return Enchantment.PROTECTION_ENVIRONMENTAL;
        }
        if (value.equalsIgnoreCase("fire protection")) {
            return Enchantment.PROTECTION_FIRE;
        }
        if (value.equalsIgnoreCase("feather falling")) {
            return Enchantment.PROTECTION_FALL;
        }
        if (value.equalsIgnoreCase("blast protection")) {
            return Enchantment.PROTECTION_EXPLOSIONS;
        }
        if (value.equalsIgnoreCase("projectile protection")) {
            return Enchantment.PROTECTION_PROJECTILE;
        }
        if (value.equalsIgnoreCase("respiration")) {
            return Enchantment.OXYGEN;
        }
        if (value.equalsIgnoreCase("aqua affinity")) {
            return Enchantment.WATER_WORKER;
        }
        if (value.equalsIgnoreCase("thorns")) {
            return Enchantment.THORNS;
        }
        if (value.equalsIgnoreCase("sharpness")) {
            return Enchantment.DAMAGE_ALL;
        }
        if (value.equalsIgnoreCase("smite")) {
            return Enchantment.DAMAGE_UNDEAD;
        }
        if (value.equalsIgnoreCase("bane of arthropods")) {
            return Enchantment.DAMAGE_ARTHROPODS;
        }
        if (value.equalsIgnoreCase("knockback")) {
            return Enchantment.KNOCKBACK;
        }
        if (value.equalsIgnoreCase("fire aspect")) {
            return Enchantment.FIRE_ASPECT;
        }
        if (value.equalsIgnoreCase("looting")) {
            return Enchantment.LOOT_BONUS_MOBS;
        }
        if (value.equalsIgnoreCase("efficiency")) {
            return Enchantment.DIG_SPEED;
        }
        if (value.equalsIgnoreCase("silk touch")) {
            return Enchantment.SILK_TOUCH;
        }
        if (value.equalsIgnoreCase("unbreaking")) {
            return Enchantment.DURABILITY;
        }
        if (value.equalsIgnoreCase("fortune")) {
            return Enchantment.LOOT_BONUS_BLOCKS;
        }
        if (value.equalsIgnoreCase("power")) {
            return Enchantment.ARROW_DAMAGE;
        }
        if (value.equalsIgnoreCase("punch")) {
            return Enchantment.ARROW_KNOCKBACK;
        }
        if (value.equalsIgnoreCase("flame")) {
            return Enchantment.ARROW_FIRE;
        }
        if (value.equalsIgnoreCase("infinity")) {
            return Enchantment.ARROW_INFINITE;
        }
        if (value.equalsIgnoreCase("luck of the sea")) {
            return Enchantment.LUCK;
        }
        if (value.equalsIgnoreCase("lure")) {
            return Enchantment.LURE;
        }
        return null;
    }

}
