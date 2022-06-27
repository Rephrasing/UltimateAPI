package com.github.rephrasing.ultimateapi.util;

import org.bukkit.ChatColor;

public class Util {

    public static String colorize(String toColorize) {
        return ChatColor.translateAlternateColorCodes('&', toColorize);
    }

}
