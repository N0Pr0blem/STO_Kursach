package org.example.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.example.demo.Category;
import org.example.demo.Job;
import org.example.exception.DuplicateException;
import org.example.exception.InputException;
import org.example.service.CategoryService;
import org.example.service.JobService;
import org.example.service.OwnerService;
import org.example.service.TaskService;
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
    @Autowired
    OwnerService ownerService;
    Logger logger = LogManager.getLogger(TaskService.class);

    @GetMapping()
    public String mainPage() {
        return "redirect:/admin/service";
    }

    @GetMapping("/service")
    public String servicePage(Model model) {
        ArrayList<Job> jobs = jobService.getAll();
        if (!jobs.isEmpty()) {
            model.addAttribute("jobs", jobs);
        } else {
            model.addAttribute("message", "Список услуг пуст");
        }
        return "admin_job";
    }

    @GetMapping("/service/add")
    public String serviceAddPage(Model model) {
        return "admin_job_add";
    }

    @PostMapping("/service/add")
    public String serviceAdd(
            String name,
            String cost,
            String warranty,
            String workTime,
            String image,
            Model model
    ) throws DuplicateException, InputException {
        try {
            jobService.add(name, cost, warranty, workTime, image);
            return "redirect:/admin/service/add";
        } catch (DuplicateException | InputException ex) {
            model.addAttribute(ex.getMessage());
            return "admin_job_add";
        }

    }

    @PostMapping("/service/{job}/delete")
    public String serviceDelete(@PathVariable Job job, Model model) {
        categoryService.delete(job);
        return "redirect:/admin/service";
    }

    @GetMapping("/service/{job}/edit")
    public String serviceEditPage(@PathVariable Job job, Model model) {
        model.addAttribute("job", job);
        return "admin_job_edit";
    }

    @PostMapping("/service/{job}/edit")
    public String serviceEdit(
            @PathVariable Job job,
            @RequestParam String name,
            @RequestParam String cost,
            @RequestParam String warranty,
            @RequestParam String workTime,
            @RequestParam String image,
            @RequestParam String newImage,
            @RequestParam boolean isChecked,
            Model model
    ) throws InputException, DuplicateException {
        try {
            jobService.update(job, name, cost, warranty, workTime, image, newImage, isChecked);
            return "redirect:/admin/service";
        } catch (InputException | DuplicateException ex) {
            model.addAttribute("message", ex.getMessage());
            model.addAttribute("job", job);
            return "admin_job_edit";
        }
    }

    @GetMapping("/category")
    public String categoryPage(Model model) {
        ArrayList<Category> categories = categoryService.getAll();
        if (!categories.isEmpty()) {
            model.addAttribute("categories", categories);
        } else {
            model.addAttribute("message", "Список категорий пуст");
        }
        return "admin_category";
    }

    @GetMapping("/category/add")
    public String categoryAddPage(Model model) {
        model.addAttribute("jobs", jobService.getAllUnChecked());
        return "admin_category_add";
    }

    @PostMapping("/category/add")
    public String categoryAdd(
            @RequestParam("categoryJobs") List<Job> selectedJobs,
            @RequestParam String name,
            Model model) {
        try {
            categoryService.add(name, selectedJobs);
            return "redirect:/admin/category/add";
        } catch (DuplicateException | InputException ex) {
            model.addAttribute("message", ex.getMessage());
        }
        model.addAttribute("jobs", jobService.getAllUnChecked());
        return "admin_category_add";
    }

    @GetMapping("/category/{category}/edit")
    public String categoryEditPage(@PathVariable Category category, Model model) {
        model.addAttribute("jobs", jobService.getAllUnChecked());
        model.addAttribute("category", category);
        return "admin_category_edit";
    }

    @PostMapping("/category/{category}/edit")
    public String categoryEdit(
            @RequestParam("categoryJobs") List<Job> selectedJobs,
            @PathVariable Category category,
            @RequestParam String name,
            Model model) {
        try {
            logger.info(selectedJobs.size());
            categoryService.edit(category, name, selectedJobs);
            return "redirect:/admin/category";
        } catch (InputException ex) {
            model.addAttribute("message", ex.getMessage());
            model.addAttribute("jobs", jobService.getAllUnChecked());
            model.addAttribute("category", category);
            return "admin_category_edit";
        }
    }

    @PostMapping("/category/{category}/delete")
    public String categoryDelete(@PathVariable Category category, Model model) {
        categoryService.delete(category);
        return "redirect:/admin/category";
    }

    @GetMapping("/history")
    public String historyPage(Model model) {
        model.addAttribute("owners", ownerService.getAll());
        return "admin_history";
    }
}
