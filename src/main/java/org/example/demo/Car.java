package org.example.demo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Brand brand;
    private Integer number;

    public Car() {
    }

    public Car(Brand brand, Integer number) {
        this.brand = brand;
        this.number = number;
    }
}
