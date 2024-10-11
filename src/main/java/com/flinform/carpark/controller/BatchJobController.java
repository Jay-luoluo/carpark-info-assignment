package com.flinform.carpark.controller;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
@RestController
public class BatchJobController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job importCarParkJob;

    @PostMapping("/run-batch-job")
    public String runBatchJob() {
        try {
            jobLauncher.run(importCarParkJob, new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters());
            return "Batch job has been invoked!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Batch job failed: " + e.getMessage();
        }
    }
}