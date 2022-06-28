package com.github.rephrasing.ultimateapi.commands;

import com.github.rephrasing.ultimateapi.UltimateAPI;
import com.github.rephrasing.ultimateapi.commands.annotations.UltimateCommandParams;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;

public class UltimateCommandHandler {

    public static void registerAll() {
        for (Class<? extends UltimateCommand> aClass : new Reflections().getSubTypesOf(UltimateCommand.class)) {
            try {
                UltimateAPI.getInstance().getPluginCommandMap().register(aClass.getAnnotation(UltimateCommandParams.class).name(), aClass.getConstructor().newInstance());
                UltimateAPI.getInstance().getPlugin().getLogger().info("Registered Ultimate Command " + aClass.getAnnotation(UltimateCommandParams.class).name());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

}
