package com.nikita.placementportal.service;

import com.nikita.placementportal.dto.application.ApplyJobDTO;
import com.nikita.placementportal.dto.application.ApplicationResponseDTO;
import com.nikita.placementportal.entity.Application;
import com.nikita.placementportal.entity.Job;
import com.nikita.placementportal.repository.ApplicationRepository;
import com.nikita.placementportal.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final NotificationService notificationService;

    public Application applyToJob(ApplyJobDTO request) {

        Application application = Application.builder()
                .jobId(request.getJobId())
                .studentId(request.getStudentId())
                .applicantName(request.getApplicantName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .resumeUrl(request.getResumeUrl())
                .coverLetter(request.getCoverLetter())
                .status("APPLIED")
                .appliedAt(LocalDateTime.now())
                .build();

        return applicationRepository.save(application);
    }

    public List<ApplicationResponseDTO> getMyApplications(String studentId) {
        return applicationRepository.findByStudentId(studentId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<ApplicationResponseDTO> getAllApplications() {
        return applicationRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ApplicationResponseDTO updateStatus(String applicationId, String status) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        String normalizedStatus = status == null ? "PENDING" : status.trim().toUpperCase();

        application.setStatus(normalizedStatus);
        Application saved = applicationRepository.save(application);
        Job job = jobRepository.findById(saved.getJobId()).orElse(null);
        String jobTitle = job == null ? "a job" : job.getTitle();
        notificationService.create(
                saved.getStudentId(),
                "Your application for " + jobTitle + " is now " + normalizedStatus,
                "APPLICATION_UPDATE"
        );

        return toResponse(saved);
    }

    public ApplicationResponseDTO withdraw(String applicationId, String studentId) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        if (!studentId.equals(application.getStudentId())) {
            throw new RuntimeException("Application does not belong to this user");
        }

        application.setStatus("WITHDRAWN");

        return toResponse(applicationRepository.save(application));
    }

    private ApplicationResponseDTO toResponse(Application application) {
        Job job = jobRepository.findById(application.getJobId())
                .orElse(null);

        return new ApplicationResponseDTO(
                application.getId(),
                application.getJobId(),
                job == null ? "Job unavailable" : job.getTitle(),
                job == null ? "" : job.getCompanyName(),
                job == null ? "" : job.getLocation(),
                job == null ? "" : job.getSalary(),
                job == null ? "" : job.getJobType(),
                job == null ? "" : job.getExperienceRequired(),
                job == null ? List.of() : job.getSkills(),
                application.getStudentId(),
                application.getApplicantName(),
                application.getEmail(),
                application.getPhone(),
                application.getStatus(),
                application.getResumeUrl(),
                application.getCoverLetter(),
                application.getAppliedAt()
        );
    }
}
