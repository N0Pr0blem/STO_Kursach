package org.example.service;

import org.apache.commons.lang3.math.NumberUtils;
import org.example.demo.Job;
import org.example.exception.DuplicateException;
import org.example.exception.InputException;
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

    public void add(String name, String cost, String warranty, String workTime, String image) throws DuplicateException, InputException {

        if (name.isEmpty() || name.length() > 15) {
            throw new InputException("Неверный формат у названия");
        } else if (cost.isEmpty() || !NumberUtils.isCreatable(cost)) {
            throw new InputException("Неверный формат у цены");
        } else if (warranty.isEmpty() || !NumberUtils.isCreatable(warranty)) {
            throw new InputException("Неверный формат у гарантии");
        } else if (workTime.isEmpty() || !NumberUtils.isCreatable(workTime)) {
            throw new InputException("Неверный формат у времени работы");
        } else if (image.isEmpty()) {
            throw new InputException("Неверный формат у времени работы");
        } else {
            Job jobFromDB = jobRepo.findByName(name);
            if (jobFromDB == null) {
                jobRepo.save(new Job(name, Double.parseDouble(cost), Integer.parseInt(warranty), Integer.parseInt(workTime), image, false));
            } else throw new DuplicateException("Услуга с таким именем уже существует");
        }
    }


    public ArrayList<Job> getAllUnChecked() {
        ArrayList<Job> all = getAll();
        ArrayList<Job> res = new ArrayList<>();
        for (Job job : all)
            if (!job.isChecked()) res.add(job);
        return res;
    }

    public void makeChecked(List<Job> jobs) {
        for (Job job : jobs) {
            Job jobFromDB = jobRepo.findByName(job.getName());
            jobFromDB.setChecked(true);
            jobRepo.save(jobFromDB);
        }
    }

    public void update(Job job, String name, String cost, String warranty, String workTime, String image, String newImage, boolean isChecked) throws InputException, DuplicateException {
        if (name.isEmpty() || name.length() > 15) {
            throw new InputException("Неверный формат у названия");
        } else if (cost.isEmpty() || !NumberUtils.isCreatable(cost)) {
            throw new InputException("Неверный формат у цены");
        } else if (warranty.isEmpty() || !NumberUtils.isCreatable(warranty)) {
            throw new InputException("Неверный формат у гарантии");
        } else if (workTime.isEmpty() || !NumberUtils.isCreatable(workTime)) {
            throw new InputException("Неверный формат у времени работы");
        } else if (image.isEmpty()) {
            throw new InputException("Неверный формат у времени работы");
        } else {
            Job jobFromDB = jobRepo.findByName(name);
            if (jobFromDB == null) {
                jobFromDB.setName(name);
                jobFromDB.setCost(Double.parseDouble(cost));
                jobFromDB.setWarranty(Integer.parseInt(warranty));
                jobFromDB.setWorkTime(Integer.parseInt(workTime));
                if (newImage.isEmpty()) jobFromDB.setImage(image);
                else jobFromDB.setImage(newImage);
                jobFromDB.setChecked(isChecked);
                jobRepo.save(jobFromDB);
            } else throw new DuplicateException("Услуга с таким именем уже существует");
        }
    }

    public void delete(Job job) {
        jobRepo.delete(job);
    }

}
