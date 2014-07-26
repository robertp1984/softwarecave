package com.example.jpagenerators.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "JPAGEN_ADDRESS")
public class Address implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "addressGen")
    @SequenceGenerator(name = "addressGen",
            sequenceName = "JPAGEN_ADDRESS_SEQ")
    private long id;
    
    @Column(name = "CITY")
    private String city;
    
    @Column(name = "STREET")
    private String street;

    protected Address() {
    }
    
    public Address(String city, String street) {
        this.city = city;
        this.street = street;
    }

    public long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
