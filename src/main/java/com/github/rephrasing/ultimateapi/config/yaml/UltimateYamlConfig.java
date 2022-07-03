package com.github.rephrasing.ultimateapi.config.yaml;

import com.github.rephrasing.ultimateapi.UltimatePlugin;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public abstract class UltimateYamlConfig {

    private final File file;
    private YamlConfiguration config;

    UltimateYamlConfig(String fileName) {
        String yamlName = fileName.endsWith(".yml") || fileName.endsWith(".yaml") ? fileName : fileName + ".yml";
        this.file = new File(UltimatePlugin.getInstance().getJavaPlugin().getDataFolder(), yamlName);
        this.config = YamlConfiguration.loadConfiguration(file);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            UltimatePlugin.getInstance().getJavaPlugin().saveResource(yamlName, false);
        }
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
