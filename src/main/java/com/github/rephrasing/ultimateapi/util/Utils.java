package com.github.rephrasing.ultimateapi.util;

import org.bukkit.ChatColor;

public class Utils {

    public static String format(String toColorize) {
        return ChatColor.translateAlternateColorCodes('&', toColorize);
    }

}
