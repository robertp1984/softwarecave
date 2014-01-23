package com.example.people.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "PEOPLE_ROLE")
@NamedQuery(name = "selectAllRoles", query = "select o from Role o order by o.id")
public class Role implements Serializable {

    private static final long serialVersionUID = 238947623894234L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String description;

    protected Role() {
    }

    public Role(String name, String description) {
        id = 0;
        this.name = name;
        this.description = description;
    }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name) ^ Objects.hashCode(description);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;

        final Role other = (Role) obj;
        return Objects.equals(this.name, other.name)
                && Objects.equals(this.description, other.description);
    }
    
}
