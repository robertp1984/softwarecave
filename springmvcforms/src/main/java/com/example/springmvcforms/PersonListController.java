package com.example.springmvcforms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/personlist")
public class PersonListController {
    
    @Autowired
    private PersonList list;
    
    @RequestMapping(method = RequestMethod.POST)
    public String add(@ModelAttribute(value = "newPerson") Person newPerson, BindingResult result, Model model) {
        if (result.hasErrors())
            return "personlist";
        
        list.addPerson(newPerson);
        return "redirect:/personlist";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("list", list.getAll());
        model.addAttribute("newPerson", new Person());
        return "personlist";
    }
}
