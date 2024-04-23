package org.example.repository;

import org.example.demo.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BrandRepo extends JpaRepository<Brand,Long> {
}
