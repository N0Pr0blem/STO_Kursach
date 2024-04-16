package org.example.repository;

import org.example.demo.Car;
import org.example.demo.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepo extends JpaRepository<Owner,Long> {
}
