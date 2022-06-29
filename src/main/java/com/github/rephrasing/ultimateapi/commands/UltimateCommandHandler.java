package com.github.rephrasing.ultimateapi.commands;

import com.github.rephrasing.ultimateapi.UltimateAPI;
import com.github.rephrasing.ultimateapi.commands.annotations.UltimateCommandParams;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;

/**
 * Represents an UltimateCommand Registerer (Automatically registers every instance of {@link UltimateCommand})
 *
 * <p><b>This Class must not be used anywhere other than the backend of {@link UltimateAPI}</b></p>
 */
public class UltimateCommandHandler {

    public static void registerAll() {
        int registeredCount = 0;
        for (Class<? extends UltimateCommand> aClass : new Reflections().getSubTypesOf(UltimateCommand.class)) {
            try {
                UltimateAPI.getInstance().getPluginCommandMap().register(aClass.getAnnotation(UltimateCommandParams.class).name(), aClass.getConstructor().newInstance());
                UltimateAPI.getInstance().getPlugin().getLogger().info("Registered Ultimate Command " + aClass.getAnnotation(UltimateCommandParams.class).name());
                registeredCount++;
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        if (registeredCount > 0) UltimateAPI.getInstance().getPlugin().getLogger().info(String.format("Successfully registered %s Ultimate Command(s)", registeredCount));
    }

}
