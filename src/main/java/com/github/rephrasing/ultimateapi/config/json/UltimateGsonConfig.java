package com.github.rephrasing.ultimateapi.config.json;

import com.github.rephrasing.ultimateapi.config.json.adapters.UltimateGsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

@Getter
public abstract class UltimateGsonConfig<T> {

    private final File file;

    private final Gson gson;
    private UltimateGsonTypeAdapter<?>[] adapters;

    public UltimateGsonConfig(File file) {
        this.file = file;
        this.gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
    }

    public UltimateGsonConfig(File file, UltimateGsonTypeAdapter<?>... adapters) {
        this.file = file;
        this.adapters = adapters;

        GsonBuilder builder = new GsonBuilder();
        for (UltimateGsonTypeAdapter<?> adapter : adapters) {
            builder.registerTypeAdapter(adapter.getTypeClass(), adapter);
        }
        this.gson = builder.setPrettyPrinting().serializeNulls().create();
    }


    @SneakyThrows
    public void set(T value) {
        FileWriter writer = new FileWriter(file);
        writer.write(gson.toJson(value));
        writer.close();
    }

    @SneakyThrows
    public T get(Class<T> clazz) {
        gson.fromJson(new FileReader(file), clazz);
        return null;
    }

}
