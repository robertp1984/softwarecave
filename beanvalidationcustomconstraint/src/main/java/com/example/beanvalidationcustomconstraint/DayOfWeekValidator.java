package com.example.beanvalidationcustomconstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DayOfWeekValidator implements ConstraintValidator<DayOfWeek, String> {
    private DayOfWeekType[] allowedTypes;
    private boolean ignoreCase;
    
    @Override
    public void initialize(DayOfWeek constraint) {
        allowedTypes = constraint.value();
        ignoreCase = constraint.ignoreCase();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null)
            return false;
        
        for (DayOfWeekType type : allowedTypes) {
            switch (type) {
                case WORKWEEK:
                    if (isWorkWeek(value))
                        return true;
                    break;
                case WEEKEND:
                    if (isWeekEnd(value))
                        return true;
            }
        }
        return false;
    }

    private boolean isWorkWeek(String value) {
        return equalsDay(value, "Monday") || equalsDay(value, "Tuesday")
                || equalsDay(value, "Wednesday") || equalsDay(value, "Thursday")
                || equalsDay(value, "Friday");
    }

    private boolean isWeekEnd(String value) {
        return equalsDay(value, "Saturday") || equalsDay(value, "Sunday");
    }

    private boolean equalsDay(String value1, String value2) {
        return ignoreCase ? value1.equalsIgnoreCase(value2) : value1.equals(value2);
    }

}
