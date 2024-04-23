package org.example.demo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToMany
    @JoinTable(name = "category_service", joinColumns = @JoinColumn(name = "category_id"), inverseJoinColumns = @JoinColumn(name = "service_id"))
    private List<Job> jobs;
    private String path;

    public Category() {
    }
    public Category(String name) {
        this.name = name;
    }
}
