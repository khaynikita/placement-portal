package com.nikita.placementportal.controller;

import com.nikita.placementportal.dto.job.StudentJobDTO;
import com.nikita.placementportal.entity.User;
import com.nikita.placementportal.repository.JobRepository;
import com.nikita.placementportal.service.MatchScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/student/jobs")
@RequiredArgsConstructor
public class StudentJobController {

    private final JobRepository jobRepository;
    private final MatchScoreService matchScoreService;

    @GetMapping
    public List<StudentJobDTO> getMatchedJobs(Authentication authentication) {
        User student = (User) authentication.getPrincipal();

        return jobRepository.findAll()
                .stream()
                .filter(job -> job.getStatus() == null || "OPEN".equalsIgnoreCase(job.getStatus()))
                .map(job -> new StudentJobDTO(job, matchScoreService.calculateMatch(student, job)))
                .sorted(Comparator.comparing(dto -> -dto.getMatch().getScore()))
                .toList();
    }
}
