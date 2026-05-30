package com.nikita.placementportal.dto.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationResponseDTO {

    private String id;

    private String jobId;
    private String jobTitle;

    private String studentId;
    private String studentName;

    private String status;

    private String resumeUrl;

    private LocalDateTime appliedAt;

}