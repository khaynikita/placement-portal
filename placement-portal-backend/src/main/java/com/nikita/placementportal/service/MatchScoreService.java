package com.nikita.placementportal.service;

import com.nikita.placementportal.dto.job.MatchResultDTO;
import com.nikita.placementportal.entity.Job;
import com.nikita.placementportal.entity.User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Service
public class MatchScoreService {

    public MatchResultDTO calculateMatch(User student, Job job) {
        List<String> studentSkills = normalize(student.getSkills());
        List<String> jobSkills = normalize(job.getSkills());
        List<String> matched = jobSkills.stream()
                .filter(studentSkills::contains)
                .toList();
        List<String> missing = jobSkills.stream()
                .filter(skill -> !studentSkills.contains(skill))
                .toList();

        double skillScore = jobSkills.isEmpty() ? 1.0 : matched.size() / (double) jobSkills.size();
        double cutoff = job.getCgpaCutoff() == null || job.getCgpaCutoff() <= 0 ? 0 : job.getCgpaCutoff();
        double cgpa = student.getCgpa() == null ? 0 : student.getCgpa();
        boolean cgpaMet = cutoff == 0 || cgpa >= cutoff;
        double cgpaScore = cutoff == 0 ? 1.0 : Math.min(1.0, cgpa / cutoff);
        boolean projectRelevant = normalize(student.getProjects()).stream()
                .anyMatch(project -> jobSkills.stream().anyMatch(project::contains));
        int finalScore = (int) Math.round(skillScore * 50 + cgpaScore * 30 + (projectRelevant ? 20 : 0));

        return new MatchResultDTO(finalScore, matched, missing, cgpaMet, projectRelevant);
    }

    private List<String> normalize(List<String> values) {
        if (values == null) {
            return Collections.emptyList();
        }

        return values.stream()
                .filter(value -> value != null && !value.isBlank())
                .map(value -> value.trim().toLowerCase(Locale.ROOT))
                .toList();
    }
}
