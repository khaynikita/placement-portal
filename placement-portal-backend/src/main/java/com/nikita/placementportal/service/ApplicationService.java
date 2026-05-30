package com.nikita.placementportal.service;

import com.nikita.placementportal.dto.application.ApplyJobDTO;
import com.nikita.placementportal.entity.Application;
import com.nikita.placementportal.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public Application applyToJob(ApplyJobDTO request) {

        Application application = Application.builder()
                .jobId(request.getJobId())
                .studentId(request.getStudentId())
                .resumeUrl(request.getResumeUrl())
                .status("APPLIED")
                .appliedAt(LocalDateTime.now())
                .build();

        return applicationRepository.save(application);
    }

    public List<Application> getMyApplications(String studentId) {
        return applicationRepository.findByStudentId(studentId);
    }
}