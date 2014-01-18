package com.example.people.ejb.mbean;

import com.example.people.ejb.PersonRoleManager;
import com.example.people.entities.Person;
import com.example.people.entities.Role;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

@Named
@SessionScoped
public class PersonList implements Serializable {
    
    private static final long serialVersionUID = 3247324324234L;
    
    @EJB
    private PersonRoleManager manager;
    
    private int id;
    private String firstName;
    private String lastName;
    private int[] roleIds;

    private boolean editing;

    public PersonList() {
        id = 0;
        firstName = "";
        lastName = "";
        roleIds = new int[0];
        editing = false;
    }

    public boolean isEditing() {
        return editing;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(int[] roleIds) {
        this.roleIds = roleIds;
    }

    public SelectItem[] getAllRoleItems() {
        List<Role> roles = manager.getAllRoles();

        SelectItem[] result = new SelectItem[roles.size()];
        int i = 0;
        for (Role role : roles) {
            result[i] = new SelectItem(role.getId(), role.getName());
            i++;
        }
        return result;
    }
    
    public String add() {
        if (!firstName.isEmpty() && !lastName.isEmpty()) {
            manager.createPersonWithRoles(firstName, lastName, roleIds);
        }
        return backToList();
    }
    
    public String backToList() {
        clear();
        return "person_list";
    }
    
    public List<Person> getAllPersons() {
        return manager.getAllPersons();
    }
    
    public String deletePerson(int id) {
        manager.deletePerson(id);
        return "";
    }

    public String showDetails(int id) {
        Person person = manager.getPerson(id);
        this.id = id;
        firstName = person.getFirstName();
        lastName = person.getLastName();
        roleIds = person.getRoleIds();
        editing = false;
        return "person_details";
    }
    
    public String startEditing() {
        editing = true;
        return "";
    }
    
    public String saveChanges() {
        manager.updatePerson(id, firstName, lastName, roleIds);
        showDetails(id);
        return "";
    }
    
    public String abortChanges() {
        editing = false;
        return "";
    }

    private void clear() {
        editing = false;
        firstName = lastName = "";
        roleIds = new int[0];
    }
}
