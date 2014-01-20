package com.example.people.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "PEOPLE_PERSON")
@NamedQuery(name = "findAllPerson", query = "select o from Person o order by o.id")
public class Person implements Serializable {

    private static final long serialVersionUID = -2346897234623746L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @ManyToMany
    @JoinTable(name = "PEOPLE_PERSON_ROLE",
            joinColumns = @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"))
    private Set<Role> roles;

    protected Person() {
    }

    public Person(String firstName, String lastName) {
        id = 0;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = new HashSet<>();
    }
    
    public int getId() {
        return id;
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

    public Set<Role> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void clearRoles() {
        roles.clear();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(firstName)
                ^ Objects.hashCode(lastName)
                ^ Objects.hashCode(roles);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;

        final Person other = (Person) obj;
        return Objects.equals(firstName, other.firstName)
                && Objects.equals(lastName, other.lastName)
                && Objects.equals(roles, other.roles);
    }

    public int[] getRoleIds() {
        int[] result = new int[roles.size()];
        int i = 0;
        for (Role role : roles) {
            result[i] = role.getId();
            i++;
        }
        return result;
    }

}
