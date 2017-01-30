package com.example.periodictableweb;

import com.example.periodictable.ws.common.Element;
import com.example.periodictable.ws.access.ElementNotFound_Exception;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class PeriodicTableController {
    
    private final Logger logger = LoggerFactory.getLogger(PeriodicTableController.class);
    
    @Autowired
    private PeriodicTableService periodicTableService;
            
    @RequestMapping(value = "/elements")
    @ResponseStatus(HttpStatus.OK)
    public List<Element> getElements() {
        logger.info("Entering getElements()");
        List<Element> result = periodicTableService.getElements();
        logger.info("Got results: " + result);
        return result;
    }
    
    @RequestMapping(value = "/elements/atomicNumber/{atomicNumber}")
    @ResponseStatus(HttpStatus.OK)
    public Element getElementByAtomicNumber(@PathVariable(value = "atomicNumber") int atomicNumber) throws ElementNotFound_Exception {
        logger.info("Entering getElementByAtomicNumber({})", atomicNumber);
        Element result = periodicTableService.getElementByAtomicNumber(atomicNumber);
        logger.info("Found element by atomic number {}", result);
        return result;
    }
    
    
}
