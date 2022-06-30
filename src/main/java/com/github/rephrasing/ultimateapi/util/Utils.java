package com.github.rephrasing.ultimateapi.util;

import org.bukkit.ChatColor;

public class Utils {

    public static String colorize(String toColorize) {
        return ChatColor.translateAlternateColorCodes('&', toColorize);
    }

}
