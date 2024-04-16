package org.example.repository;

import org.example.demo.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepo extends JpaRepository<Service,Long> {

}
