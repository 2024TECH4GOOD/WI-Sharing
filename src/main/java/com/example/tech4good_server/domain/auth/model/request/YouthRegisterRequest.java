package com.example.tech4good_server.domain.auth.model.request;

import com.example.tech4good_server.domain.auth.model.vo.UserRegisterVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YouthRegisterRequest {
    UserRegisterVo userInfo;
}