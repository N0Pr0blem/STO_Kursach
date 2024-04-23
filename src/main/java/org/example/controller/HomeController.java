package org.example.controller;

import org.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    CategoryService categoryService;
    @GetMapping("/")
    public String redirectToHomePage(){
        return "redirect:/home";
    }
    @GetMapping("/home")
    public String homePage(Model model){
        model.addAttribute("categories",categoryService.getAll());
        return "home";
    }
    @GetMapping("/demo")
    public String demoPage(){
        return "demo";
    }
}
