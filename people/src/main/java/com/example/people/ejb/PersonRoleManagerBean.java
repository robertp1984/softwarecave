package com.example.people.ejb;

import com.example.people.entities.Person;
import com.example.people.entities.Role;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateful
public class PersonRoleManagerBean implements PersonRoleManager {
    
    private static final long serialVersionUID = 234732489234234L;
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void createPerson(Person person) {
        em.persist(person);
    }

    @Override
    public Person getPerson(int id) {
        return em.find(Person.class, id);
    }
    
    @Override
    public void addPersonToRoles(Person person, Set<Role> roles) {
        person.addRoles(roles);
    }

    @Override
    public void createRole(Role role) {
        em.persist(role);
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
