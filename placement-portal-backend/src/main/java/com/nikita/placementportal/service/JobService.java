package com.nikita.placementportal.service;
import com.nikita.placementportal.dto.job.JobRequestDTO;
import com.nikita.placementportal.entity.Job;
import com.nikita.placementportal.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job getJobById(String id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
    }

    public Job createJob(JobRequestDTO request) {

        Job job = Job.builder()
                .title(request.getTitle())
                .companyName(request.getCompanyName())
                .location(request.getLocation())
                .salary(request.getSalary())
                .jobType(request.getJobType())
                .description(request.getDescription())
                .requirements(request.getRequirements())
                .experienceRequired(request.getExperienceRequired())
                .skills(request.getSkills())
                .postedBy("Recruiter1")
                .createdAt(LocalDateTime.now())
                .status("OPEN")
                .build();

        return jobRepository.save(job);
    }

    public Job updateJob(String id, JobRequestDTO request) {

        Job existingJob = getJobById(id);

        existingJob.setTitle(request.getTitle());
        existingJob.setCompanyName(request.getCompanyName());
        existingJob.setLocation(request.getLocation());
        existingJob.setSalary(request.getSalary());
        existingJob.setJobType(request.getJobType());
        existingJob.setDescription(request.getDescription());
        existingJob.setRequirements(request.getRequirements());
        existingJob.setExperienceRequired(request.getExperienceRequired());
        existingJob.setSkills(request.getSkills());

        return jobRepository.save(existingJob);
    }

    public void deleteJob(String id) {
        jobRepository.deleteById(id);
    }
}