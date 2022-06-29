package com.github.rephrasing.ultimateapi.commands.annotations;

import com.github.rephrasing.ultimateapi.commands.UltimateCommandSource;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Represents an UltimateCommand Settings
 *
 * @see com.github.rephrasing.ultimateapi.commands.UltimateCommand
 * @see UltimateCommandSource
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface UltimateCommandSettings {

    String permission() default "";
    UltimateCommandSource source() default UltimateCommandSource.ANY;

}
