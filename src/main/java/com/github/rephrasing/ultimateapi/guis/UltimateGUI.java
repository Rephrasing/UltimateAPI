package com.github.rephrasing.ultimateapi.guis;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class UltimateGUI extends AbstractUltimateGUI {

    private final List<UltimateSubGUI> subGUIs;

    public UltimateGUI(Player player, int size, List<UltimateSubGUI> subGUIs) {
        super(player, size);
        this.subGUIs = subGUIs;
    }
    public UltimateGUI(Player player, String name, int size, List<UltimateSubGUI> subGUIs) {
        super(player, name, size);
        this.subGUIs = subGUIs;
    }


    public UltimateGUI(Player player, int size) {
        super(player, size);
        subGUIs = new ArrayList<>();
    }

    public UltimateGUI(Player player, String name, int size) {
        super(player, name, size);
        subGUIs = new ArrayList<>();
    }

}
