package com.example.tech4good_server.domain.auth.model.vo;

import com.example.tech4good_server.global.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterVo {
    // id
    private String id;

    // pw
    private String password;

    // 이름
    private String name;

    // 성별
    private String sex;

    // 나이
    private Integer age;

    // 생일
    private LocalDate birth;

    // 핸드폰 번호
    private String phoneNumber;

    // 프로필 이미지
    private String profileUrl;

    // 멘토링 계약서
    private String mentoringAgreement;

    // 거주지 (시)
    private String location;

    // 희망 직업
    private String career;

    // 성격
    private List<String> personality;

    // 관심사
    private List<String> interest;

    // 취미
    private List<String> hobby;

    // 자립 준비 청년, 시니어
    private Role role;
}
