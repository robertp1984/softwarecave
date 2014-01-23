package com.example.people.ejb;

import com.example.people.entities.Person;
import com.example.people.entities.Role;
import java.util.List;
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
        setPersonRoles(person, roleIds);
        em.persist(person);
    }
    
    private void setPersonRoles(Person person, int[] roleIds) {
        person.clearRoles();
        for (int roleId : roleIds) {
            Role role = getRole(roleId);
            person.addRole(role);
        }
    }
    
    @Override
    public List<Person> getAllPersons() {
        TypedQuery<Person> query = em.createNamedQuery("selectAllPersons", Person.class);
        return query.getResultList();
    }
    
    @Override
    public void deletePerson(int id) {
        Person person = getPerson(id);
        if (person != null)
            em.remove(person);
    }
    
    @Override
    public void updatePerson(int id, String firstName, String lastName, int[] roleIds) {
        Person person = em.find(Person.class, id);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        setPersonRoles(person, roleIds);
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
        TypedQuery<Role> query = em.createNamedQuery("selectAllRoles", Role.class);
        return query.getResultList();
    }

    @Override
    public void deleteRole(int id) {
        Role role = getRole(id);
        if (role != null)
            em.remove(role);
    }
    
    @Override
    public long getPersonCountBelongingToRole(int roleId) {
        TypedQuery<Long> query = em.createNamedQuery("countPersonsForRole", Long.class);
        query.setParameter("roleId", roleId);
        return query.getSingleResult();
    }
}
