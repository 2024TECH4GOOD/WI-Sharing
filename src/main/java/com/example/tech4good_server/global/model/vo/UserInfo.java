package com.example.tech4good_server.global.model.vo;

import com.example.tech4good_server.global.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private String userId;

    // id
    private String id;

    // pw
    private String password;

    // 프로필 이미지
    private String profileUrl;

    // 청년, 시니어
    private Role role;
}
