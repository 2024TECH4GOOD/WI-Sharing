package com.example.tech4good_server.domain.mypage.model.response;

import com.example.tech4good_server.global.model.vo.UserInfoVo;
import com.example.tech4good_server.global.model.vo.YouthInfoVo;
import lombok.Data;

@Data
public class YouthMyInfoResponse {
    private UserInfoVo userInfo;
    private YouthInfoVo youthInfo;
}
