package com.nikita.placementportal.service;

import com.nikita.placementportal.dto.interview.InterviewRequestDTO;
import com.nikita.placementportal.entity.Interview;
import com.nikita.placementportal.entity.Job;
import com.nikita.placementportal.repository.InterviewRepository;
import com.nikita.placementportal.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InterviewService {

    private final InterviewRepository interviewRepository;
    private final JobRepository jobRepository;
    private final NotificationService notificationService;

    public Interview scheduleInterview(InterviewRequestDTO request, String recruiterId) {
        List<Interview> existing = interviewRepository.findByStudentIdAndStatus(request.getStudentId(), "SCHEDULED");
        for (Interview interview : existing) {
            LocalDateTime start = interview.getScheduledAt();
            LocalDateTime end = start.plusMinutes(interview.getDurationMinutes());
            if (!request.getScheduledAt().isBefore(start) && request.getScheduledAt().isBefore(end)) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "Student already has a " + interview.getRoundName() + " interview at " + interview.getScheduledAt()
                );
            }
        }

        Interview saved = interviewRepository.save(Interview.builder()
                .studentId(request.getStudentId())
                .jobId(request.getJobId())
                .recruiterId(recruiterId)
                .scheduledAt(request.getScheduledAt())
                .durationMinutes(request.getDurationMinutes())
                .roundName(request.getRoundName())
                .meetLink(request.getMeetLink())
                .status("SCHEDULED")
                .build());
        String jobTitle = jobRepository.findById(request.getJobId()).map(Job::getTitle).orElse("a job");
        notificationService.create(request.getStudentId(), "Interview scheduled for " + jobTitle, "INTERVIEW_SCHEDULED");
        return saved;
    }

    public List<Interview> upcoming(String studentId) {
        return interviewRepository.findByStudentIdAndStatusAndScheduledAtAfterOrderByScheduledAtAsc(studentId, "SCHEDULED", LocalDateTime.now());
    }

    public void cancel(String id) {
        interviewRepository.findById(id).ifPresent(interview -> {
            interview.setStatus("CANCELLED");
            interviewRepository.save(interview);
            notificationService.create(interview.getStudentId(), "Interview cancelled", "INTERVIEW_CANCELLED");
        });
    }

    public Interview complete(String id) {
        Interview interview = interviewRepository.findById(id).orElseThrow();
        interview.setStatus("COMPLETED");
        return interviewRepository.save(interview);
    }
}
