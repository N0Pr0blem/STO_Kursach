package org.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Double cost;
    private int warranty;
    private int workTime;

    public Service() {
    }

    public Service(String name, Double cost, int warranty, int workTime) {
        this.name = name;
        this.cost = cost;
        this.warranty = warranty;
        this.workTime = workTime;
    }
}
