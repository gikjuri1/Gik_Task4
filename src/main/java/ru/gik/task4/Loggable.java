package ru.gik.task4;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface Loggable {
    String value() default "C:/temp/logs.log";
}
