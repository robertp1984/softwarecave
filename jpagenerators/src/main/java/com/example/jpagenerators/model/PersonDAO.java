package com.example.jpagenerators.model;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class PersonDAO {
    
    @PersistenceContext
    private EntityManager em;
    
    public void addPerson(Person person) {
        em.persist(person);
    }
    
    public List<Person> getAllPerson() {
        TypedQuery<Person> query = em.createNamedQuery("Person.selectAll", Person.class);
        return query.getResultList();
    }
}
