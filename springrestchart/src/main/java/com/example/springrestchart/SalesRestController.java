package com.example.springrestchart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class SalesRestController {
    
    @Autowired
    private SalesProvider salesProvider;
    
    @RequestMapping(value = "/services/sales/{countryCode}", method = RequestMethod.GET,
            produces = "application/json")
    public @ResponseBody Sales getSales(@PathVariable("countryCode") String countryCode) {
        return salesProvider.getSales(countryCode);
    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CountryNotFoundException.class)
    public void countryNotFound() {
    }
}
