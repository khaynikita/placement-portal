package com.nikita.placementportal.repository;

import com.nikita.placementportal.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}