package com.example.tech4good_server.domain.mypage.model.request;

import com.example.tech4good_server.global.model.vo.UserInfoVo;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YouthOnboardingRequest {
    UserInfoVo userInfo;
}
