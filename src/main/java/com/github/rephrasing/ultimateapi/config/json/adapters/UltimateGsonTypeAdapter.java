package com.github.rephrasing.ultimateapi.config.json.adapters;

import com.google.gson.*;
import org.jetbrains.annotations.NotNull;

public abstract class UltimateGsonTypeAdapter<Type> implements JsonSerializer<Type>, JsonDeserializer<Type> {

    @NotNull
    public abstract Class<Type> getTypeClass();

}
