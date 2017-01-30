package com.example.periodictableweb;

import com.example.periodictable.ws.access.ElementNotFound_Exception;
import com.example.periodictable.ws.access.PeriodicTableAccess;
import com.example.periodictable.ws.access.PeriodicTableAccess_Service;
import com.example.periodictable.ws.admin.ElementAlreadyExists_Exception;
import com.example.periodictable.ws.admin.ElementCategoryNotFound_Exception;
import com.example.periodictable.ws.admin.PeriodicTableAdmin;
import com.example.periodictable.ws.admin.PeriodicTableAdmin_Service;
import com.example.periodictable.ws.common.Element;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.xml.ws.BindingProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PeriodicTableService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    private PeriodicTableAccess periodicTableAccessPort;
    
    @Value("${periodicTableAccessWsUrl}")
    private String periodicTableAccessWsUrl;

    private PeriodicTableAdmin periodicTableAdminPort;
    
    @Value("${periodicTableAdminWsUrl}")
    private String periodicTableAdminWsUrl;

    @PostConstruct
    protected void init() {
        initAccessService();
        initAdminService();
    }

    private void initAccessService() {
        PeriodicTableAccess_Service service = new PeriodicTableAccess_Service();
        periodicTableAccessPort = service.getPeriodicTableAccessSOAP();
        
        BindingProvider bp = (BindingProvider)periodicTableAccessPort;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, periodicTableAccessWsUrl);
        
        logger.info("Using periodic table access WS URL: {}", periodicTableAccessWsUrl);
    }

    private void initAdminService() {
        PeriodicTableAdmin_Service service = new PeriodicTableAdmin_Service();
        periodicTableAdminPort = service.getPeriodicTableAdminPort();
        
        BindingProvider bp = (BindingProvider)periodicTableAdminPort;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, periodicTableAdminWsUrl);
        
        logger.info("Using periodic table admin WS URL: {}", periodicTableAdminWsUrl);
    }

    public List<Element> getElements() {
        List<Element> result = periodicTableAccessPort.getAllElements();
        return result;
    }
    
    public Element getElementByAtomicNumber(int atomicNumber) throws ElementNotFound_Exception {
        Element result = periodicTableAccessPort.getElementByAtomicNumber(atomicNumber);
        return result;
    }

    public Element addElement(Element elem) throws ElementAlreadyExists_Exception, ElementCategoryNotFound_Exception {
        return periodicTableAdminPort.addElement(elem);
    }

    public void updateElement(Element elem) throws ElementCategoryNotFound_Exception, com.example.periodictable.ws.admin.ElementNotFound_Exception {
        periodicTableAdminPort.updateElement(elem);
    }

    public void removeElement(int elementId) throws com.example.periodictable.ws.admin.ElementNotFound_Exception {
        periodicTableAdminPort.removeElement(elementId);
    }
}
