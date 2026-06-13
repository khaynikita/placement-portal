package com.nikita.placementportal.repository;

import com.nikita.placementportal.entity.Interview;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface InterviewRepository extends MongoRepository<Interview, String> {
    List<Interview> findByStudentIdAndStatus(String studentId, String status);
    List<Interview> findByStudentIdAndStatusAndScheduledAtAfterOrderByScheduledAtAsc(String studentId, String status, LocalDateTime now);
}
