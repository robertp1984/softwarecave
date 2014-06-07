package com.example.springhibernate;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PersonList implements Serializable {
    private static final long serialVersionUID = 324589274837L;

    @Autowired
    private SessionFactory sessionFactory;
    
    @Transactional
    public void addPerson(Person person) {
        sessionFactory.getCurrentSession().save(person);
    }
    
    @Transactional
    public List<Person> getAll() {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("Person.selectAll");
        return (List<Person>) query.list();
    }
}
