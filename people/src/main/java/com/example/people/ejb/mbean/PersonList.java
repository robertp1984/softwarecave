package com.example.people.ejb.mbean;

import com.example.people.ejb.PersonRoleManager;
import com.example.people.entities.Person;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class PersonList implements Serializable {
    
    private static final long serialVersionUID = 234723894723432L;
    
    @EJB
    private PersonRoleManager manager;

    public List<Person> getAllPersons() {
        return manager.getAllPersons();
    }
    
    public String deletePerson(int id) {
        manager.deletePerson(id);
        return "";
    }

}
