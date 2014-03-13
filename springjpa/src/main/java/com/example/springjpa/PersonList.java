package com.example.springjpa;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PersonList implements Serializable {
    private static final long serialVersionUID = 324589274837L;

    @PersistenceContext
    private EntityManager em;
    
    private List<Person> list = new ArrayList<>();
    
    public void addPerson(Person person) {
        list.add(person);
        em.persist(person);
    }
    
    public List<Person> getAll() {
        return Collections.unmodifiableList(list);
    }
}
