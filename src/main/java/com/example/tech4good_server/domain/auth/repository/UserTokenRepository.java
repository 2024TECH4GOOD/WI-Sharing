package com.example.tech4good_server.domain.auth.repository;

import com.example.tech4good_server.global.model.entity.USER_TOKEN;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTokenRepository extends MongoRepository<USER_TOKEN, String> {
}
