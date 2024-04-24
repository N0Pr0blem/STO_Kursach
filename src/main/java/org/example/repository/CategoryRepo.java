package org.example.repository;

import org.example.demo.Category;
import org.example.demo.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface CategoryRepo extends JpaRepository<Category,Long> {
    Category findByName(String name);

    Category findByPath(String path);

}
