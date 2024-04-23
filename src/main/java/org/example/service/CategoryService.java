package org.example.service;

import org.example.demo.Category;
import org.example.demo.Job;
import org.example.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    JobService jobService;
    public ArrayList<Category> getAll() {
        return (ArrayList<Category>)categoryRepo.findAll();
    }

    public void add(Category category, List<Job> jobs) {
        Category categoryFromDB = categoryRepo.findByName(category.getName());
        if (categoryFromDB == null) {
            category.setPath(new PathGenerator().rusToLatin(category.getName()));
            category.setJobs(jobs);
            jobService.makeChecked(jobs);
            categoryRepo.save(category);
        }

    }

    public void delete(Category category) {
        categoryRepo.delete(category);
    }

    public Category getByPath(String path) {
        return categoryRepo.findByPath(path);
    }
}
