package com.example.jpagenerators.view;

import com.example.jpagenerators.model.PersonDAO;
import com.example.jpagenerators.model.Address;
import com.example.jpagenerators.model.Person;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class PersonBean {
    @EJB
    private PersonDAO personDAO;

    private Person newPerson;
    private List<Person> allPerson;
    
    public Person getNewPerson() {
        if (newPerson == null)
            newPerson = new Person("", new Address("", ""));
        return newPerson;
    }
    
    public String addNewPerson() {
        personDAO.addPerson(newPerson);
        newPerson = null;
        allPerson = null;
        return "";
    }
    
    public List<Person> getAllPerson() {
        if (allPerson == null)
            allPerson = personDAO.getAllPerson();
        return allPerson;
    }
}
