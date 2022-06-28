package com.github.rephrasing.ultimateapi.config.yaml;

import lombok.SneakyThrows;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public abstract class UltimateYamlConfig {

    private final File file;
    private YamlConfiguration config;

    UltimateYamlConfig(File file) {
        this.file = file;
        this.config = YamlConfiguration.loadConfiguration(file);
    }


    public void reloadConfig() {
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    @SneakyThrows
    public void save() {
        this.config.save(file);
    }

    public void set(String key, Object object) {
        this.config.set(key, object);
        save();
    }

    public Object get(String key) {
        return this.config.get(key);
    }
}
