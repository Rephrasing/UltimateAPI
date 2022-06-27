package com.github.rephrasing.ultimateapi.guis;


import org.bukkit.entity.Player;

public abstract class SubGUI extends AbstractGUI {

    private final GUI mainGUI;

    public SubGUI(Player player, int size, GUI main) {
        super(player, size);
        this.mainGUI = main;
    }


    public SubGUI(Player player, String name, int size, GUI main) {
        super(player, name, size);
        this.mainGUI = main;
    }

    public GUI getMainGUI() {
        return mainGUI;
    }
}
