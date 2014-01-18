package com.example.people.ejb.mbean;

import com.example.people.ejb.PersonRoleManager;
import com.example.people.entities.Role;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class RoleAdd implements Serializable {
    
    private static final long serialVersionUID = 234723894723432L;
    
    @EJB
    private PersonRoleManager manager;
    
    private String name;
    private String description;

    public RoleAdd() {
        name = "";
        description = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String add() {
        if (!name.isEmpty() && !description.isEmpty())
            manager.createRole(name, description);
        return backToList();
    }

    public String backToList() {
        name = description = "";
        return "role_list";
    }
}
