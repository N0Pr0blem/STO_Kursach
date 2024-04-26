package org.example.service;

import org.example.demo.Category;
import org.example.demo.Job;
import org.example.exception.DuplicateException;
import org.example.exception.InputException;
import org.example.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    JobService jobService;

    public ArrayList<Category> getAll() {
        return (ArrayList<Category>) categoryRepo.findAll();
    }

    public void add(String name, List<Job> jobs) throws DuplicateException, InputException {
        Category categoryFromDB = categoryRepo.findByName(name);
        if (!name.isEmpty() && name.length() < 15) {
            if (categoryFromDB == null) {
                Category category = new Category(name);
                category.setPath(new PathGenerator().rusToLatin(category.getName()));
                category.setJobs(jobs);
                jobService.makeChecked(jobs);
                categoryRepo.save(category);
            } else throw new DuplicateException("Категория уже существует");
        } else throw new InputException("Неверное название");
    }

    public void delete(Category category) {
        for (Job job : category.getJobs()) job.setChecked(false);
        categoryRepo.delete(category);
    }

    public Category getByPath(String path) {
        return categoryRepo.findByPath(path);
    }

    public void delete(Job job) {
        ArrayList<Category> categories = findByJob(job);
        if (!categories.isEmpty()) {
            for (Category category : categories) {
                category.getJobs().remove(job);
                categoryRepo.save(category);
            }
        }
        jobService.delete(job);
    }

    private ArrayList<Category> findByJob(Job job) {
        ArrayList<Category> all = (ArrayList<Category>) categoryRepo.findAll();
        ArrayList<Category> res = new ArrayList<>();
        for (Category category : all)
            if (category.getJobs().contains(job))
                res.add(category);
        return res;
    }

    public void edit(Category category, String name, List<Job> selectedJobs) throws InputException {
        if (!name.isEmpty() && name.length() < 15) {
            category.setPath(new PathGenerator().rusToLatin(name));
            category.setJobsChecked(false);
            category.setJobs(selectedJobs);
            category.setJobsChecked(true);
            category.setName(name);
            categoryRepo.save(category);
        } else throw new InputException("Неверное название");

    }
}
