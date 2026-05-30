package com.nikita.placementportal.repository;

import com.nikita.placementportal.entity.Application;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ApplicationRepository extends MongoRepository<Application, String> {

    List<Application> findByStudentId(String studentId);
}