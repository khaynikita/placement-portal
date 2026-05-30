package com.nikita.placementportal.dto.job;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobResponseDTO {

    private String id;

    private String title;
    private String companyName;
    private String location;
    private String salary;
    private String jobType;

    private String description;
    private String requirements;
    private String experienceRequired;

    private List<String> skills;

    private String postedBy;

    private LocalDateTime createdAt;

    private String status;

}