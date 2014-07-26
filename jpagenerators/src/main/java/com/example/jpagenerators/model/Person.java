package com.example.jpagenerators.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "JPAGEN_PERSON")
@NamedQuery(name = "Person.selectAll",
        query = "select o from Person o order by o.id")
public class Person implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,
            generator = "personGen")
    @TableGenerator(name = "personGen",
            table = "JPAGEN_GENERATORS",
            pkColumnName = "NAME",
            pkColumnValue = "JPAGEN_PERSON_GEN",
            valueColumnName = "VALUE")
    private long id;

    @Column(name = "NAME")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;
    
    protected Person() {
    }

    public Person(String name, Address address) {
        id = 0;
        this.name = name;
        this.address = address;
    }
    
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
