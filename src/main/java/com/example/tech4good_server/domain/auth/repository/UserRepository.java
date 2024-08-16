package com.example.tech4good_server.domain.auth.repository;

import com.example.tech4good_server.global.model.entity.USER_INFO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<USER_INFO, String> {
    Optional<USER_INFO> findUSER_INFOById(String id);
}
