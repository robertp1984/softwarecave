package com.example.beanvalidationcustomconstraint;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = DayOfWeekValidator.class)
@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface DayOfWeek {
    String message() default "{com.example.beanvalidationcustomconstraint.DayOfWeek.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    DayOfWeekType[] value() default { };
    boolean ignoreCase() default false;
}
