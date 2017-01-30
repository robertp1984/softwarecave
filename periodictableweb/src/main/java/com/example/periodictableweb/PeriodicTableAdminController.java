package com.example.periodictableweb;

import com.example.periodictable.ws.common.Element;
import com.example.periodictable.ws.admin.ElementAlreadyExists_Exception;
import com.example.periodictable.ws.admin.ElementCategoryNotFound_Exception;
import com.example.periodictable.ws.admin.ElementNotFound_Exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/rest/admin")
public class PeriodicTableAdminController {
    
    private final Logger logger = LoggerFactory.getLogger(PeriodicTableAdminController.class);
    
    @Autowired
    private PeriodicTableService periodicTableService;
            
    @RequestMapping(value = "/elements", method = RequestMethod.POST)
    public ResponseEntity<Element> addElement(@RequestBody Element elem) throws ElementAlreadyExists_Exception, ElementCategoryNotFound_Exception {
        logger.info("Entering addElement()" + elem.getName());
        Element result = periodicTableService.addElement(elem);
        logger.info("Got results: " + result);
            
        UriComponents uriComponents = UriComponentsBuilder.fromUriString("/elements/atomicNumber/{atomicNumber}")
                .buildAndExpand(result.getAtomicNumber());
        return ResponseEntity.created(uriComponents.toUri()).body(result);
    }
    
    @RequestMapping(value ="elements/id/{elementId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateElement(@RequestBody Element elem) throws ElementCategoryNotFound_Exception, ElementNotFound_Exception {
        logger.info("Update element with id=" + elem.getId());
        periodicTableService.updateElement(elem);
    }
    
    @RequestMapping(value="elements/id/{elementId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeElement(@PathVariable int elementId) throws ElementNotFound_Exception {
        logger.info("Remove element with id=" + elementId);
        periodicTableService.removeElement(elementId);
    }
    
}
