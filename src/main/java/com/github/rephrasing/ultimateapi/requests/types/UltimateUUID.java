package com.github.rephrasing.ultimateapi.requests.types;

import com.github.rephrasing.ultimateapi.requests.UltimateRequestKeyType;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class UltimateUUID implements UltimateRequestKeyType<UUID> {

    @NotNull
    private final UUID uuid;

    public UltimateUUID(@NotNull UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public UUID getJavaType() {
        return uuid;
    }
}
