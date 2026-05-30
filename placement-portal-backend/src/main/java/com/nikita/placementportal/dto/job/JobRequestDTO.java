package com.nikita.placementportal.dto.job;

import lombok.Data;

import java.util.List;

@Data
public class JobRequestDTO {

    private String title;
    private String companyName;
    private String location;
    private String salary;
    private String jobType;
    private String description;
    private String requirements;
    private String experienceRequired;

    private List<String> skills;

}