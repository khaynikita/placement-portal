package com.nikita.placementportal.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "jobs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {

    @Id
    private String id;

    private String title;
    private String companyName;
    private String location;
    private String salary;
    private String jobType;
    private String description;
    private String requirements;
    private String experienceRequired;
    private Double cgpaCutoff;
    private List<String> skills;
    private String postedBy;
    private LocalDateTime createdAt;
    private String status;
}
