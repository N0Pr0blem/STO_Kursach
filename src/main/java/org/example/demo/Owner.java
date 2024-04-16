package org.example.demo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String phone;
    @OneToOne
    private Car car;

    public Owner() {
    }

    public Owner(String name, String phone, Car car) {
        this.name = name;
        this.phone = phone;
        this.car = car;
    }
}
