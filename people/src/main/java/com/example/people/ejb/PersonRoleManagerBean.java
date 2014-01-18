package com.example.people.ejb;

import com.example.people.entities.Person;
import com.example.people.entities.Role;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateful
public class PersonRoleManagerBean implements PersonRoleManager {
    
    private static final long serialVersionUID = 234732489234234L;
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Person getPerson(int id) {
        return em.find(Person.class, id);
    }

    @Override
    public void createPersonWithRoles(String firstName, String lastName, int[] roleIds) {
        Person person = new Person(firstName, lastName);
        for (int roleId : roleIds) {
            Role role = em.find(Role.class, roleId);
            person.addRole(role);
        }
        em.persist(person);
    }
    
    @Override
    public void addPersonToRoles(Person person, Set<Role> roles) {
        person.addRoles(roles);
    }
    
    public List<Person> getAllPersons() {
        TypedQuery<Person> query = em.createNamedQuery("findAllPerson", Person.class);
        return query.getResultList();
    }
    
    public void deletePerson(int id) {
        Person person = em.find(Person.class, id);
        if (person != null)
            em.remove(person);
    }
    
    @Override
    public void createRole(String name, String description) {
        em.persist(new Role(name, description));
    }
    
    @Override
    public Role getRole(int id) {
        return em.find(Role.class, id);
    }

    @Override
    public List<Role> getAllRoles() {
        TypedQuery<Role> query = em.createNamedQuery("findAllRoles", Role.class);
        return query.getResultList();
    }

    @Override
    public void deleteRole(int id) {
        Role role = em.find(Role.class, id);
        if (role != null)
            em.remove(role);
    }
}
