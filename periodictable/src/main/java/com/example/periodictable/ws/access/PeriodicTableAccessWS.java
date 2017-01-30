package com.example.periodictable.ws.access;

import com.example.periodictable.ElementNotFoundException;
import com.example.periodictable.PeriodicTableService;
import com.example.periodictable.ws.common.Element;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@WebService(name = "PeriodicTableAccess", targetNamespace = "http://www.example.org/periodictableaccess/")
public class PeriodicTableAccessWS implements PeriodicTableAccess {
    
    private final Logger logger = LoggerFactory.getLogger(PeriodicTableAccessWS.class);
    
    @Autowired
    protected PeriodicTableService periodicTableService;
    
    @Autowired
    protected DozerBeanMapper mapper;
    
    @PostConstruct
    protected void init() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        logger.info("Injected " + periodicTableService);
    }
    
    @Override
    @WebMethod(action = "http://www.example.org/periodictableaccess/getAllElements")
    @WebResult(name = "elements", targetNamespace = "")
    @RequestWrapper(localName = "getAllElements", targetNamespace = "http://www.example.org/periodictableaccess/", className = "com.example.periodictable.ws.access.GetAllElements")
    @ResponseWrapper(localName = "getAllElementsResponse", targetNamespace = "http://www.example.org/periodictableaccess/", className = "com.example.periodictable.ws.access.GetAllElementsResponse")
    public List<Element> getAllElements() {
        logger.info("Entering: getAllElements()" + periodicTableService);
        List<com.example.periodictable.Element> elements = periodicTableService.getElements();
        
        List<Element> result = new ArrayList<>();
        for (com.example.periodictable.Element element : elements) {
            Element e = mapper.map(element, Element.class);
            result.add(e);
        }
        
        return result;
    }

    @Override
    @WebMethod(action = "http://www.example.org/periodictableaccess/getElementByAtomicNumber")
    @WebResult(name = "element", targetNamespace = "")
    @RequestWrapper(localName = "getElementByAtomicNumber", targetNamespace = "http://www.example.org/periodictableaccess/", className = "com.example.periodictable.ws.access.GetElementByAtomicNumber")
    @ResponseWrapper(localName = "getElementByAtomicNumberResponse", targetNamespace = "http://www.example.org/periodictableaccess/", className = "com.example.periodictable.ws.access.GetElementByAtomicNumberResponse")
    public Element getElementByAtomicNumber(
        @WebParam(name = "atomicNumber", targetNamespace = "")
        int atomicNumber)
        throws ElementNotFound_Exception {
        logger.info("Entering: getElementByAtomicNumber()" + periodicTableService);
        try {
            com.example.periodictable.Element element = periodicTableService.getElementByAtomicNumber(atomicNumber);        
            return mapper.map(element, Element.class);
        } catch (ElementNotFoundException e) {
            ElementNotFound faultInfo = new ElementNotFound();
            faultInfo.setMessage(String.valueOf(atomicNumber));
            faultInfo.setValue(atomicNumber);
            throw new ElementNotFound_Exception(e.getMessage(), faultInfo);
        }
    }
}
