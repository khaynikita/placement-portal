package com.nikita.placementportal.dto.interview;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InterviewRequestDTO {
    private String studentId;
    private String jobId;
    private LocalDateTime scheduledAt;
    private int durationMinutes;
    private String roundName;
    private String meetLink;
}
