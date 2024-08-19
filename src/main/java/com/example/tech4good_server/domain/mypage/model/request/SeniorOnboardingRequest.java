package com.example.tech4good_server.domain.mypage.model.request;

import com.example.tech4good_server.global.model.vo.SeniorInfoVo;
import com.example.tech4good_server.global.model.vo.UserInfoVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeniorOnboardingRequest {
    UserInfoVo userInfo;
    SeniorInfoVo seniorInfo;
}
