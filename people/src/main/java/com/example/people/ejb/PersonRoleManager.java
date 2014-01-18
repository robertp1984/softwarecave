package com.example.people.ejb;

import com.example.people.entities.Person;
import com.example.people.entities.Role;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

public interface PersonRoleManager extends Serializable {
    void addPersonToRoles(Person person, Set<Role> roles);
    void createPersonWithRoles(String firstName, String lastName, int[] roleIds);
    Person getPerson(int id);
    List<Person> getAllPersons();
    void deletePerson(int id);
    void updatePerson(int id, String firstName, String lastName, int[] roleIds);

    void createRole(String name, String description);
    Role getRole(int id);
    List<Role> getAllRoles();
    void deleteRole(int id);
}
