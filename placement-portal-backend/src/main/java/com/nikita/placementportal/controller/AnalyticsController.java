package com.nikita.placementportal.controller;

import com.nikita.placementportal.entity.Application;
import com.nikita.placementportal.entity.Job;
import com.nikita.placementportal.repository.ApplicationRepository;
import com.nikita.placementportal.repository.JobRepository;
import com.nikita.placementportal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/analytics")
@RequiredArgsConstructor
public class AnalyticsController {
    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    @GetMapping
    public Map<String, Object> analytics() {
        List<Application> applications = applicationRepository.findAll();
        Map<String, Long> funnel = applications.stream()
                .collect(Collectors.groupingBy(Application::getStatus, LinkedHashMap::new, Collectors.counting()));
        Map<String, Long> topCompanies = jobRepository.findAll().stream()
                .collect(Collectors.groupingBy(Job::getCompanyName, LinkedHashMap::new, Collectors.counting()));

        return Map.of(
                "totalStudents", userRepository.findAll().stream().filter(user -> "STUDENT".equalsIgnoreCase(user.getRole())).count(),
                "placed", funnel.getOrDefault("OFFERED", 0L),
                "placementRate", applications.isEmpty() ? 0 : Math.round(funnel.getOrDefault("OFFERED", 0L) * 100.0 / applications.size()),
                "applicationFunnel", funnel,
                "topCompanies", topCompanies
        );
    }

    @GetMapping("/export")
    public ResponseEntity<String> export() {
        String csv = "metric,value\n" + analytics().entrySet().stream()
                .map(entry -> entry.getKey() + "," + entry.getValue())
                .collect(Collectors.joining("\n"));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=placement-analytics.csv")
                .contentType(MediaType.TEXT_PLAIN)
                .body(csv);
    }
}
