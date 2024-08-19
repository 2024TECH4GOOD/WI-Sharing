package com.example.tech4good_server.global.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YouthInfoVo {
    // 희망 진로
    private String careerPath;

    // 상담 희망 내용
    private String desiredConsultation;

    // 희망 지역 (시)
    private String city;

    // 희망 지역 (동)
    private String district;
}
