package com.github.rephrasing.ultimateapi.commands.exceptions;

import com.github.rephrasing.ultimateapi.UltimateAPI;

/**
 * Represents an UltimateCommand Exception
 *
 * <p>Thrown if something goes wrong in the process of initiating/handling an UltimateCommand</p>
 */
public class UltimateCommandException extends Throwable {

    protected UltimateCommandException(String message) {
        UltimateAPI.getUltimateLogger().severe(message);
    }

    protected UltimateCommandException() {}

}
