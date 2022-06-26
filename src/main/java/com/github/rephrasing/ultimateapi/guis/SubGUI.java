package com.github.rephrasing.ultimateapi.guis;


import org.bukkit.entity.Player;

public class SubGUI extends AbstractGUI {

    private final GUI mainGUI;

    public SubGUI(Player player, int size, GUI mainGUI) {
        super(player, size);
        this.mainGUI = mainGUI;
    }

    public SubGUI(Player player, String name, int size, GUI mainGUI) {
        super(player, name, size);
        this.mainGUI = mainGUI;
    }


    public GUI getMainGUI() {
        return mainGUI;
    }
}
