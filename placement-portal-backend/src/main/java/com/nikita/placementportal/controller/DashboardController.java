package com.nikita.placementportal.controller;

import com.nikita.placementportal.entity.Application;
import com.nikita.placementportal.entity.Job;
import com.nikita.placementportal.entity.User;
import com.nikita.placementportal.repository.ApplicationRepository;
import com.nikita.placementportal.repository.JobRepository;
import com.nikita.placementportal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DashboardController {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    @GetMapping("/student/dashboard/stats")
    public Map<String, Object> getStudentStats(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        List<Application> applications = applicationRepository.findByStudentId(user.getId());
        Map<String, Long> byStatus = applications.stream()
                .collect(Collectors.groupingBy(Application::getStatus, Collectors.counting()));

        return Map.of(
                "totalApplied", applications.size(),
                "pending", byStatus.getOrDefault("APPLIED", 0L) + byStatus.getOrDefault("PENDING", 0L),
                "shortlisted", byStatus.getOrDefault("SHORTLISTED", 0L),
                "rejected", byStatus.getOrDefault("REJECTED", 0L),
                "offered", byStatus.getOrDefault("OFFERED", 0L),
                "upcomingInterviews", List.of()
        );
    }

    @GetMapping("/recruiter/dashboard/stats")
    public Map<String, Object> getRecruiterStats() {
        List<Job> jobs = jobRepository.findAll();
        List<Application> applications = applicationRepository.findAll();

        return Map.of(
                "activeJobs", jobs.stream().filter(job -> "OPEN".equalsIgnoreCase(job.getStatus())).count(),
                "totalApplicants", applications.size(),
                "pendingReviews", applications.stream().filter(app -> "APPLIED".equalsIgnoreCase(app.getStatus()) || "PENDING".equalsIgnoreCase(app.getStatus())).count(),
                "scheduledInterviews", applications.stream().filter(app -> "SHORTLISTED".equalsIgnoreCase(app.getStatus())).count(),
                "recentApplications", applications.stream().limit(5).toList()
        );
    }

    @GetMapping("/admin/dashboard/stats")
    public Map<String, Object> getAdminStats() {
        List<User> users = userRepository.findAll();
        List<Application> applications = applicationRepository.findAll();
        long offered = applications.stream().filter(app -> "OFFERED".equalsIgnoreCase(app.getStatus())).count();
        long students = users.stream().filter(user -> "STUDENT".equalsIgnoreCase(user.getRole())).count();

        return Map.of(
                "totalStudents", students,
                "totalRecruiters", users.stream().filter(user -> "RECRUITER".equalsIgnoreCase(user.getRole())).count(),
                "totalAdmins", users.stream().filter(user -> "ADMIN".equalsIgnoreCase(user.getRole())).count(),
                "totalJobs", jobRepository.count(),
                "placementRate", students == 0 ? 0 : Math.round((offered * 100.0) / students)
        );
    }
}
