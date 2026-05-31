package com.nikita.placementportal.dto.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationResponseDTO {

    private String id;

    private String jobId;
    private String jobTitle;
    private String companyName;
    private String location;
    private String salary;
    private String jobType;
    private String experienceRequired;
    private List<String> skills;

    private String studentId;
    private String studentName;
    private String email;
    private String phone;

    private String status;

    private String resumeUrl;
    private String coverLetter;

    private LocalDateTime appliedAt;

}
