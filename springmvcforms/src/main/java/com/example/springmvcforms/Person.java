package com.example.springmvcforms;

import java.io.Serializable;

public class Person implements Serializable {
    private static final long serialVersionUID = 3297423984732894L;
    
    private String firstName;
    private String lastName;
    private int age;

    public Person() {
        firstName = "";
        lastName = "";
        age = 0;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
