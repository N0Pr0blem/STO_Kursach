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
    @Enumerated(EnumType.STRING)
    private Brand brand;
    private String number;

    public Car() {
    }

    public Car(Brand brand, String number) {
        this.brand = brand;
        this.number = number;
    }
}
