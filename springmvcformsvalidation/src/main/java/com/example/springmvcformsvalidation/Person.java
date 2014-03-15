package com.example.springmvcformsvalidation;

import java.io.Serializable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class Person implements Serializable {
    private static final long serialVersionUID = 3297423984732894L;
    
    @Size(min = 1, max = 20, message = "{firstNameInvalid}")
    private String firstName;
    @Size(min = 1, max = 40, message = "{lastNameInvalid}")
    private String lastName;
    @Min(value = 1, message = "{ageTooLow}")
    @Max(value = 100, message = "{ageTooHigh}")
    private Integer age;

    public Person() {
        firstName = "";
        lastName = "";
        age = null;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
