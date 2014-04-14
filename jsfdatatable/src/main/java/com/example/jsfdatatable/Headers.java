package com.example.jsfdatatable;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class Headers {

    public List<HeaderEntry> getEntries() {
        List<HeaderEntry> result = new ArrayList<>();
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        
        Enumeration<String> namesIt = request.getHeaderNames();
        while (namesIt.hasMoreElements()) {
            String name = namesIt.nextElement();
            Enumeration<String> valueIt = request.getHeaders(name);
            while (valueIt.hasMoreElements()) {
                String value = valueIt.nextElement();
                result.add(new HeaderEntry(name, value));
            }
        }
        return result;
    }
}
