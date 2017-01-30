package com.example.periodictable.ws.admin;

import com.example.periodictable.ElementAlreadyExistsException;
import com.example.periodictable.ElementCategoryNotFoundException;
import com.example.periodictable.ElementNotFoundException;
import com.example.periodictable.PeriodicTableService;
import com.example.periodictable.ws.common.Element;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@WebService(name = "PeriodicTableAdmin", targetNamespace = "http://www.example.org/periodictableadmin/")
public class PeriodicTableAdminWS implements PeriodicTableAdmin {

    @Autowired
    private PeriodicTableService periodicTableService;
    
    @Autowired
    private DozerBeanMapper mapper;
    
    @PostConstruct
    protected void init() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    
    @Override
    @WebMethod(action = "http://www.example.org/periodictableadmin/addElement")
    @WebResult(name = "addedElement", targetNamespace = "")
    @RequestWrapper(localName = "addElement", targetNamespace = "http://www.example.org/periodictableadmin/", className = "com.example.periodictable.ws.admin.AddElement")
    @ResponseWrapper(localName = "addElementResponse", targetNamespace = "http://www.example.org/periodictableadmin/", className = "com.example.periodictable.ws.admin.AddElementResponse")
    public Element addElement(
        @WebParam(name = "element", targetNamespace = "")
        Element element)
        throws ElementAlreadyExists_Exception, ElementCategoryNotFound_Exception {
        try {
            com.example.periodictable.Element elem = mapper.map(element, com.example.periodictable.Element.class);
            com.example.periodictable.Element addedElem = periodicTableService.addElement(elem);
            return mapper.map(addedElem, Element.class);
        } catch (ElementCategoryNotFoundException ex) {
            ElementCategoryNotFound faultInfo = new ElementCategoryNotFound();
            faultInfo.setMessage(ex.getMessage());
            throw new ElementCategoryNotFound_Exception(ex.getMessage(), faultInfo);
        } catch (ElementAlreadyExistsException ex) {
            ElementAlreadyExists faultInfo = new ElementAlreadyExists();
            faultInfo.setMessage(ex.getMessage());
            throw new ElementAlreadyExists_Exception(ex.getMessage(), faultInfo);
        }
        
    }

    @Override
    @WebMethod(action = "http://www.example.org/periodictableadmin/updateElement")
    @RequestWrapper(localName = "updateElement", targetNamespace = "http://www.example.org/periodictableadmin/", className = "com.example.periodictable.ws.admin.UpdateElement")
    @ResponseWrapper(localName = "updateElementResponse", targetNamespace = "http://www.example.org/periodictableadmin/", className = "com.example.periodictable.ws.admin.UpdateElementResponse")
    public void updateElement(
        @WebParam(name = "element", targetNamespace = "")
        Element element)
        throws ElementCategoryNotFound_Exception, com.example.periodictable.ws.admin.ElementNotFound_Exception {
        try {
            com.example.periodictable.Element elem = mapper.map(element, com.example.periodictable.Element.class);
            periodicTableService.updateElement(elem);
        } catch (ElementCategoryNotFoundException e) {
            ElementCategoryNotFound faultInfo = new ElementCategoryNotFound();
            faultInfo.setMessage(e.getMessage());
            throw new ElementCategoryNotFound_Exception(e.getMessage(), faultInfo);
        } catch (ElementNotFoundException e) {
            ElementNotFound faultInfo = new ElementNotFound();
            faultInfo.setMessage(e.getMessage());
            throw new com.example.periodictable.ws.admin.ElementNotFound_Exception(e.getMessage(), faultInfo);
        }
    }

    @Override
    @WebMethod(action = "http://www.example.org/periodictableadmin/removeElement")
    @RequestWrapper(localName = "removeElement", targetNamespace = "http://www.example.org/periodictableadmin/", className = "com.example.periodictable.ws.admin.RemoveElement")
    @ResponseWrapper(localName = "removeElementResponse", targetNamespace = "http://www.example.org/periodictableadmin/", className = "com.example.periodictable.ws.admin.RemoveElementResponse")
    public void removeElement(
        @WebParam(name = "id", targetNamespace = "")
        int id)
        throws ElementNotFound_Exception {
        try {
            periodicTableService.removeElement(id);
        } catch (ElementNotFoundException e) {
            ElementNotFound faultInfo = new ElementNotFound();
            faultInfo.setMessage(e.getMessage());
            throw new ElementNotFound_Exception(e.getMessage(), faultInfo);
        }
    }
}
