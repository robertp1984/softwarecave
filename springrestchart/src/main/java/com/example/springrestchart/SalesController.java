package com.example.springrestchart;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/sales")
public class SalesController {
    
    @Autowired
    private SalesProvider salesProvider;
    
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        
        List<Country> countries = salesProvider.getCountries();
        model.addAttribute("countries", countries);
        
        return "sales";
    }
}
