package org.example.service;

import org.example.demo.Owner;
import org.example.repository.OwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OwnerService {
    @Autowired
    OwnerRepo ownerRepo;
    public ArrayList<Owner> getAll() {
        return (ArrayList<Owner>) ownerRepo.findAll();
    }
}
