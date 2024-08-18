package com.example.tech4good_server.domain.auth.repository;

import com.example.tech4good_server.global.model.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findUserInfoById(String id);
}
