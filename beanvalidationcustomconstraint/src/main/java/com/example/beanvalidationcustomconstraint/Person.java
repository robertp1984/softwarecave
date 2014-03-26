package com.example.beanvalidationcustomconstraint;

import java.io.Serializable;
import javax.validation.constraints.Size;

public class Person implements Serializable {
    private static final long serialVersionUID = 3297423984732894L;
    
    @Size(min = 1, max = 20, message = "{firstNameInvalid}")
    private String firstName;
    @Size(min = 1, max = 40, message = "{lastNameInvalid}")
    private String lastName;

    @NotNull
    @DayOfWeek(value = DayOfWeekType.WEEKEND, ignoreCase = true)
    private String favouriteDayOfWeek;

    public Person() {
        firstName = "";
        lastName = "";
        favouriteDayOfWeek = "";
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFavouriteDayOfWeek() {
        return favouriteDayOfWeek;
    }

    public void setFavouriteDayOfWeek(String favouriteDayOfWeek) {
        this.favouriteDayOfWeek = favouriteDayOfWeek;
    }
    
}
