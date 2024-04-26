package org.example.controller;

import org.example.demo.Brand;
import org.example.demo.Job;
import org.example.exception.InputException;
import org.example.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/task")
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping("/{job}")
    public String newTaskPage(@PathVariable Job job, Model model) {
        model.addAttribute("job", job);
        return "task_creator";
    }

    @PostMapping("/{job}")
    public String newTask(
            @RequestParam String name,
            @RequestParam String phone,
            @RequestParam String number,
            @RequestParam Brand brand,
            @PathVariable Job job,
            Model model
    ) throws InputException {

        try {
            taskService.create(name, phone, number, brand, job);
            return "redirect:/home";
        } catch (InputException ex) {
            model.addAttribute("message", ex.getMessage());
            model.addAttribute("job", job);
            return "task_creator";
        }
    }

}
