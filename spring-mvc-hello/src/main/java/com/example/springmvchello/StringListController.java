package com.example.springmvchello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StringListController {
    
    @Autowired
    private StringList list;
    
    @RequestMapping("/stringlist-add")
    public String add(StringListEntry newItem, Model model, BindingResult result) {
        list.addItem(newItem.getText());
        return "redirect:stringlist";
    }
    
    @RequestMapping("/stringlist")
    public String list(Model model) {
        model.addAttribute("items", list.getItems());
        model.addAttribute("newItem", new StringListEntry());
        return "stringlist";
    }
}
