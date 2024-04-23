package org.example.service;

import org.example.demo.Job;
import org.example.repository.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {
    @Autowired
    JobRepo jobRepo;

    public ArrayList<Job> getAll() {
        ArrayList<Job> all = (ArrayList<Job>) jobRepo.findAll();
        return all;
    }

    public void add(Job job) {
        Job jobFromDB = jobRepo.findByName(job.getName());
        if (jobFromDB == null) {
            jobRepo.save(job);
        }
    }

    public void delete(Job job) {
        jobRepo.delete(job);
    }
    public ArrayList<Job> getAllUnChecked(){
        ArrayList<Job> all = getAll();
        ArrayList<Job> res = new ArrayList<>();
        for(Job job:all)
            if(!job.isChecked()) res.add(job);
        return res;
    }

    public void makeChecked(List<Job> jobs) {
        for(Job job:jobs){
            Job jobFromDB = jobRepo.findByName(job.getName());
            jobFromDB.setChecked(true);
            jobRepo.save(jobFromDB);
        }
    }
}
