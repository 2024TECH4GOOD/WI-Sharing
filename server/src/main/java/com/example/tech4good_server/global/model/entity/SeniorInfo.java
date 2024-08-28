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
@Entity(name = "SENIOR_INFO")
public class SeniorInfo {
    @Id
    private Integer userSeq;

    // 범죄 경력 확인 동의서
    private String criminalRecordCheck;

    // 사전 교육 영상 수료서
    private String educationalCertificate;

}
