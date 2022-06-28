package com.github.rephrasing.ultimateapi.commands.exceptions;

import com.github.rephrasing.ultimateapi.UltimateAPI;

public class CommandException extends Throwable {

    protected CommandException(String message) {
        UltimateAPI.getInstance().getPlugin().getLogger().severe(message);
    }

    protected CommandException() {}

}
