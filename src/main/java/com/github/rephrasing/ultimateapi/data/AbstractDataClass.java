package com.github.rephrasing.ultimateapi.data;


import java.util.HashMap;
import java.util.UUID;

public abstract class AbstractDataClass {

    private final UUID uuid = UUID.randomUUID();

    public UUID getUniqueId() {
        return uuid;
    }

    public abstract HashMap<String, Object> encode();
    public abstract <T extends AbstractDataClass> T decode(HashMap<String, Object> serializedHash);
}
