package com.nikita.placementportal.controller;

import com.nikita.placementportal.dto.job.JobRequestDTO;
import com.nikita.placementportal.entity.Job;
import com.nikita.placementportal.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/{id}")
    public Job getJobById(@PathVariable String id) {
        return jobService.getJobById(id);
    }

    @PostMapping
    public Job createJob(@RequestBody JobRequestDTO request) {
        return jobService.createJob(request);
    }

    @PutMapping("/{id}")
    public Job updateJob(
            @PathVariable String id,
            @RequestBody JobRequestDTO request
    ) {
        return jobService.updateJob(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteJob(@PathVariable String id) {

        jobService.deleteJob(id);

        return "Job deleted successfully";
    }
}