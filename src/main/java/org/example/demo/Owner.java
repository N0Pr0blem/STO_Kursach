package org.example.demo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String phone;
    @ManyToMany
    @JoinTable(
            name = "owner_car",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "owner_id")
    )
    private List<Car> cars;
    @ManyToMany
    @JoinTable(
            name = "tasks",
            joinColumns = @JoinColumn(name = "owner_id"),
            inverseJoinColumns = @JoinColumn(name = "job_id")
    )
    private List<Job> jobs;

    public Owner() {
    }

    public Owner(String name, String phone, List<Car> cars, List<Job> jobs) {
        this.name = name;
        this.phone = phone;
        this.cars = cars;
        this.jobs = jobs;
    }
}
