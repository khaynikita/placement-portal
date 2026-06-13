package com.nikita.placementportal.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "interviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Interview {
    @Id
    private String id;
    private String studentId;
    private String jobId;
    private String recruiterId;
    private LocalDateTime scheduledAt;
    private int durationMinutes;
    private String roundName;
    private String meetLink;
    private String status;
}
