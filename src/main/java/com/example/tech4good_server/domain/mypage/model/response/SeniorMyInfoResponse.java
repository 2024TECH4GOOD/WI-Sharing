package com.example.tech4good_server.domain.mypage.model.response;

import com.example.tech4good_server.global.model.vo.SeniorInfoVo;
import com.example.tech4good_server.global.model.vo.UserInfoVo;
import lombok.Data;

@Data
public class SeniorMyInfoResponse {
    private UserInfoVo userInfo;
    private SeniorInfoVo seniorInfo;

}
