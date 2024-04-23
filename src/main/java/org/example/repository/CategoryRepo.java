package org.example.repository;

import org.example.demo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Long> {
    Category findByName(String name);

    Category findByPath(String path);
}
