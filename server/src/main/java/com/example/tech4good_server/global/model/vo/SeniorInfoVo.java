package com.example.tech4good_server.global.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeniorInfoVo {
    // 경력 사항
    private String career;

    // 범죄 경력 확인 동의서
    private String criminalRecordCheck;

    // 사전 교육 영상 수료서
    private String educationalCertificate;
}
