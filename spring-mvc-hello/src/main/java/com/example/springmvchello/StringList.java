package com.example.springmvchello;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class StringList implements Serializable {
    private List<String> items = new ArrayList<>();
    
    public void add(String item) {
        items.add(item);
    }
    
    public List<String> getItems() {
        return Collections.unmodifiableList(items);
    }
}
