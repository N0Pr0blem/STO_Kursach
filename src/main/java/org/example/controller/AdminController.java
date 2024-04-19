package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping()
    public String mainPage(){
        return "admin";
    }
    @GetMapping("/service")
    public String servicePage(Model model){
        return "admin_service";
    }
    @GetMapping("/category")
    public String categoryPage(Model model){
        return "admin_category";
    }
    @GetMapping("/history")
    public String historyPage(Model model){
        return "admin_history";
    }
}
