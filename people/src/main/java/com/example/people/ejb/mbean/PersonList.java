package com.example.people.ejb.mbean;

import com.example.people.ejb.PersonRoleManager;
import com.example.people.entities.Person;
import com.example.people.entities.Role;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Set;
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
    
    private String firstName;
    private String lastName;
    private int[] roleIds;

    // details support
    private int id;
    private Set<Role> roles;
    private boolean editing;

    public PersonList() {
        firstName = "";
        lastName = "";
        roleIds = new int[0];
        id = 0;
        roles = Collections.emptySet();
        editing = false;
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
        List<Role> allRoles = manager.getAllRoles();

        SelectItem[] result = new SelectItem[allRoles.size()];
        int i = 0;
        for (Role role : allRoles) {
            result[i] = new SelectItem(role.getId(), role.getName());
            i++;
        }
        return result;
    }
    
    public boolean isEditing() {
        return editing;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String add() {
        if (!firstName.isEmpty() && !lastName.isEmpty()) {
            manager.createPersonWithRoles(firstName, lastName, roleIds);
        }
        return backToList();
    }
    
    public String backToList() {
        clear();
        return "back";
    }
    
    public List<Person> getAllPersons() {
        return manager.getAllPersons();
    }
    
    public String deletePerson(int id) {
        manager.deletePerson(id);
        return null;
    }

    public String showDetails(int id) {
        Person person = manager.getPerson(id);
        firstName = person.getFirstName();
        lastName = person.getLastName();
        roleIds = person.getRoleIds();
        this.id = id;
        roles = person.getRoles();
        editing = false;
        return "persondetails";
    }
    
    public String startEditing() {
        editing = true;
        return null;
    }
    
    public String saveChanges() {
        manager.updatePerson(id, firstName, lastName, roleIds);
        showDetails(id);
        return null;
    }
    
    public String abortChanges() {
        showDetails(id);
        return null;
    }

    private void clear() {
        firstName = lastName = "";
        roleIds = new int[0];
        id = 0;
        roles = Collections.emptySet();
        editing = false;
    }
}
