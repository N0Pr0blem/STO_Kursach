package org.example.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.example.demo.*;
import org.example.repository.CarRepo;
import org.example.repository.OwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    CarRepo carRepo;
    @Autowired
    OwnerRepo ownerRepo;
    Logger logger = LogManager.getLogger(TaskService.class);
    public void create(String name, String phone, String number, Brand brand, Job job) {
        Car car = new Car(brand,number);
        logger.info(car.getBrand());
        logger.info(brand);
        carRepo.save(car);
        List<Car> cars = new ArrayList<Car>();
        List<Job> jobs = new ArrayList<Job>();
        cars.add(car);
        jobs.add(job);
        Owner owner = new Owner(name,phone,cars,jobs);
        ownerRepo.save(owner);
    }
}
