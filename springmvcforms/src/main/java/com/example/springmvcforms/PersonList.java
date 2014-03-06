package com.example.springmvcforms;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PersonList implements Serializable {
    private static final long serialVersionUID = 324589274837L;
    
    private List<Person> list = new ArrayList<>();
    
    public void addPerson(Person person) {
        list.add(person);
    }
    
    public List<Person> getAll() {
        return Collections.unmodifiableList(list);
    }
}
