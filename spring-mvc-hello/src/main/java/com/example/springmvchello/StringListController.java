package com.example.springmvchello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/stringlist")
public class StringListController {
    
    @Autowired
    private StringList list;
    
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(StringListEntry newItem, Model model, BindingResult result) {
        list.addItem(newItem.getText());
        return "redirect:/stringlist/all";
    }
    
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("items", list.getItems());
        model.addAttribute("newItem", new StringListEntry());
        return "stringlist";
    }
}
