package ru.romanov.marketplace.productservice.aop;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Logging {

    String value() default "";

    boolean logParams() default true;

    boolean logResult() default true;

    Level level() default Level.INFO;

    enum Level {
        DEBUG, INFO, WARN, ERROR
    }
}
