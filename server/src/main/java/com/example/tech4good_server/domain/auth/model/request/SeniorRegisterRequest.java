package com.example.tech4good_server.domain.auth.model.request;

import com.example.tech4good_server.domain.auth.model.vo.UserRegisterVo;
import com.example.tech4good_server.global.model.vo.SeniorInfoVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeniorRegisterRequest {
    UserRegisterVo userInfo;
    SeniorInfoVo seniorInfo;
}
