package com.example.tech4good_server.domain.mypage.repository;

import com.example.tech4good_server.global.model.entity.YouthInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YouthInfoRepository extends JpaRepository<YouthInfo, Integer> {
    YouthInfo findByUserSeq(Integer userSeq);
}
