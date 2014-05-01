package com.example.customannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyTest {
    String name() default "";
    MyTestState state() default MyTestState.ACTIVE;
    Class<? extends Throwable> expected() default None.class;
    
    static class None extends Throwable {
    }
}
