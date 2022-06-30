package com.github.rephrasing.ultimateapi.guis.items;

import com.github.rephrasing.ultimateapi.util.UltimateStringUtils;
import org.bukkit.Material;

public class UltimateMaterialWrapper {

    public static String nameOf(Material material) {
        String materialName = material.name();

        if (materialName.contains("_")) {
            String[] split = materialName.split("_");

            StringBuilder builder = new StringBuilder();
            for (String inSplit : split) {
                builder.append(UltimateStringUtils.uppercaseFirstLetter(inSplit.toLowerCase())).append(" ");
            }

            return builder.substring(0, builder.length() - 1);
        }
        return UltimateStringUtils.uppercaseFirstLetter(materialName.toLowerCase());
    }


}
