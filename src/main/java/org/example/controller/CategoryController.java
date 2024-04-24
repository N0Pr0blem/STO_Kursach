package org.example.controller;

import org.example.demo.Category;
import org.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/{path}")
    public String categoryPage(@PathVariable String path, Model model) {
        Category category = categoryService.getByPath(path);
        model.addAttribute("category",category);
        model.addAttribute("categories",categoryService.getAll());
        return "category";
    }
}
