package com.github.rephrasing.ultimateapi.commands.exceptions;

import com.github.rephrasing.ultimateapi.UltimateAPI;

/**
 * Represents an UltimateCommand Exception
 *
 * <p>Thrown if something goes wrong in the process of initiating/handling an UltimateCommand</p>
 */
public class CommandException extends Throwable {

    protected CommandException(String message) {
        UltimateAPI.getUltimateLogger().severe(message);
    }

    protected CommandException() {}

}
