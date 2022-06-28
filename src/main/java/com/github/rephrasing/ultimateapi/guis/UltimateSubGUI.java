package com.github.rephrasing.ultimateapi.guis;


import org.bukkit.entity.Player;

public abstract class UltimateSubGUI extends AbstractUltimateGUI {

    private final UltimateGUI mainGUI;

    public UltimateSubGUI(Player player, int size, UltimateGUI main) {
        super(player, size);
        this.mainGUI = main;
    }


    public UltimateSubGUI(Player player, String name, int size, UltimateGUI main) {
        super(player, name, size);
        this.mainGUI = main;
    }

    public UltimateGUI getMainGUI() {
        return mainGUI;
    }
}
