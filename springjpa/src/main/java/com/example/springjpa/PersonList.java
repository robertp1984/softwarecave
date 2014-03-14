package com.example.springjpa;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PersonList implements Serializable {
    private static final long serialVersionUID = 324589274837L;

    @PersistenceContext
    private EntityManager em;
    
    @Transactional
    public void addPerson(Person person) {
        em.persist(person);
    }
    
    @Transactional
    public List<Person> getAll() {
        TypedQuery<Person> query = em.createNamedQuery("Person.selectAll", Person.class);
        return query.getResultList();
    }
}
