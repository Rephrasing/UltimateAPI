package com.github.rephrasing.ultimateapi.commands.annotations;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Represents an UltimateCommand Parameters
 * @see com.github.rephrasing.ultimateapi.commands.UltimateCommand
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface UltimateCommandParams {

    String name();
    String aliases() default "";
}
