package com.example.springmvchello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StringListController {
    
    @Autowired
    private StringList list;
    
    @RequestMapping("/stringlist-add")
    public String add(@RequestParam(value="name", required=true) String name, Model model) {
        list.add(name);
        return list(model);
    }
    
    @RequestMapping("/stringlist")
    public String list(Model model) {
        model.addAttribute("items", list.getItems());
        return "stringlist";
    }
}
