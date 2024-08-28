package com.example.tech4good_server.domain.mypage.repository;

import com.example.tech4good_server.global.model.entity.SeniorInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeniorInfoRepository extends JpaRepository<SeniorInfo, Integer> {
    SeniorInfo findByUserSeq(Integer userSeq);
}
