package com.nikita.placementportal.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "applications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Application {

    @Id
    private String id;

    private String jobId;
    private String studentId;
    private String resumeUrl;
    private String status;
    private LocalDateTime appliedAt;
}