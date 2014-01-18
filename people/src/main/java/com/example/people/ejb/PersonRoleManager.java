package com.example.people.ejb;

import com.example.people.entities.Person;
import com.example.people.entities.Role;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

public interface PersonRoleManager extends Serializable {
    void createPerson(Person person);
    void addPersonToRoles(Person person, Set<Role> roles);
    Person getPerson(int id);

    void createRole(Role role);
    Role getRole(int id);
    List<Role> getAllRoles();
    void deleteRole(int id);
}
