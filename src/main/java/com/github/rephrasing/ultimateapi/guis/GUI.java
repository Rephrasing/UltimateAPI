package com.github.rephrasing.ultimateapi.guis;

import lombok.Getter;
import org.bukkit.entity.Player;
import java.util.List;

@Getter
public class GUI extends AbstractGUI {

    private final List<SubGUI> subGUIs;

    public GUI(Player player, int size, List<SubGUI> subGUIs) {
        super(player, size);
        this.subGUIs = subGUIs;
    }
    public GUI(Player player, String name, int size, List<SubGUI> subGUIs) {
        super(player, name, size);
        this.subGUIs = subGUIs;
    }

}