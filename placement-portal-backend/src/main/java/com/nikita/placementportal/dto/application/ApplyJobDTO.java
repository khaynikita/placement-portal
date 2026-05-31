package com.nikita.placementportal.dto.application;

import lombok.Data;

@Data
public class ApplyJobDTO {

    private String jobId;
    private String studentId;
    private String applicantName;
    private String email;
    private String phone;
    private String resumeUrl;
    private String coverLetter;

}
