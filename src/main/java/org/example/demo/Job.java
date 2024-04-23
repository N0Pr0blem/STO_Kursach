package org.example.demo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Double cost;
    private int warranty;
    private int workTime;
    private String image;
    @Column(name = "is_checked", columnDefinition = "BOOLEAN DEFAULT false")
    private boolean isChecked;

    public Job() {
    }

    public Job(String name, Double cost, int warranty, int workTime, String image,boolean isChecked) {
        this.name = name;
        this.cost = cost;
        this.warranty = warranty;
        this.workTime = workTime;
        this.image = image;
        this.isChecked = isChecked;
    }
}
