package org.example.controller;

import org.example.demo.Category;
import org.example.demo.Job;
import org.example.service.CategoryService;
import org.example.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    JobService jobService;
    @Autowired
    CategoryService categoryService;
    @GetMapping()
    public String mainPage(){
        return "admin";
    }
    @GetMapping("/service")
    public String servicePage(Model model){
        ArrayList<Job> jobs = jobService.getAll();
        if(!jobs.isEmpty()){
            model.addAttribute("jobs",jobs);
        }
        else{
            model.addAttribute("message","Список услуг пуст");
        }
        return "admin_job";
    }
    @GetMapping("/service/add")
    public String serviceAddPage(Model model){
        return "admin_job_add";
    }
    @PostMapping("/service/add")
    public String serviceAdd(Job job, Model model){
        jobService.add(job);
        return "redirect:/admin/service/add";
    }
    @PostMapping("/service/{job}/delete")
    public String serviceDelete(@PathVariable Job job, Model model){
        jobService.delete(job);
        return "redirect:/admin/service";
    }
    @GetMapping("/category")
    public String categoryPage(Model model){
        ArrayList<Category> categories = categoryService.getAll();
        if(!categories.isEmpty()){
            model.addAttribute("categories",categories);
        }
        else{
            model.addAttribute("message","Список категорий пуст");
        }
        return "admin_category";
    }
    @GetMapping("/category/add")
    public String categoryAddPage(Model model){
        model.addAttribute("jobs",jobService.getAllUnChecked());
        return "admin_category_add";
    }
    @PostMapping("/category/add")
    public String categoryAdd(
            @RequestParam("categoryJobs") List<Job> selectedJobs,
            Category category,
            Model model){
        categoryService.add(category,selectedJobs);
        return "redirect:/admin/category/add";
    }
    @PostMapping("/category/{category}/delete")
    public String categoryDelete(@PathVariable Category category, Model model){
        categoryService.delete(category);
        return "redirect:/admin/category";
    }
    @GetMapping("/history")
    public String historyPage(Model model){
        return "admin_history";
    }
}
