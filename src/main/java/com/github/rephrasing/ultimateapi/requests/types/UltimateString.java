package com.github.rephrasing.ultimateapi.requests.types;

import com.github.rephrasing.ultimateapi.requests.UltimateRequestKeyType;
import org.jetbrains.annotations.NotNull;

public class UltimateString implements UltimateRequestKeyType<String> {

    @NotNull
    private final String string;

    public UltimateString(@NotNull String string) {
        this.string = string;
    }

    @Override
    public String getJavaType() {
        return string;
    }
}
