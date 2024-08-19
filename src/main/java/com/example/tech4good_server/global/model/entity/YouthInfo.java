package com.example.tech4good_server.global.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@Entity(name = "YOUTH_INFO")
public class YouthInfo {
    @Id
    private Integer userSeq;

    // 희망 진로
    private String careerPath;

    // 상담 희망 내용
    private String desiredConsultation;

    // 희망 지역 (시)
    private String city;

    // 희망 지역 (동)
    private String district;

}
