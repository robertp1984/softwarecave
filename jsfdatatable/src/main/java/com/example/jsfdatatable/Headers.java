package com.example.jsfdatatable;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class Headers {

    private List<HeaderEntry> entries;
    
    @PostConstruct
    public void init() {
        entries = new ArrayList<>();
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        
        Enumeration<String> namesIt = request.getHeaderNames();
        while (namesIt.hasMoreElements()) {
            String name = namesIt.nextElement();
            Enumeration<String> valueIt = request.getHeaders(name);
            while (valueIt.hasMoreElements()) {
                String value = valueIt.nextElement();
                entries.add(new HeaderEntry(name, value));
            }
        }
    }
    
    public List<HeaderEntry> getEntries() {
        return entries;
    }
}
