package com.nikita.placementportal.repository;

import com.nikita.placementportal.entity.Job;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobRepository extends MongoRepository<Job, String> {
}