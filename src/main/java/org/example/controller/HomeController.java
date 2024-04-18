package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String redirectToHomePage(){
        return "redirect:/home";
    }
    @GetMapping("/home")
    public String homePage(){
        return "home";
    }
    @GetMapping("/demo")
    public String demoPage(){
        return "demo";
    }
}
