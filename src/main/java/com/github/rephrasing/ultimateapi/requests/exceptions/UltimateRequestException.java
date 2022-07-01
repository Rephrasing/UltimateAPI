package com.github.rephrasing.ultimateapi.requests.exceptions;

import com.github.rephrasing.ultimateapi.UltimateAPI;

public class UltimateRequestException extends Throwable {

    public UltimateRequestException() {}

    public UltimateRequestException(String message) {
        UltimateAPI.getInstance().getPlugin().getLogger().severe(message);
    }
}
