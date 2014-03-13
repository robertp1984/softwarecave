package com.example.springjpa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePageController {

    @RequestMapping("/")
    String showHome() {
        return "redirect:/personlist";
    }
}
