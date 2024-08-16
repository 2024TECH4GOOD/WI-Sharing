package com.example.tech4good_server.global.model.enums;

import lombok.Getter;

@Getter
public enum Role {

    ADMIN("0", "ROLE_ADMIN"),// BackOffice 전체 Admin 관리 (개발팀)
    FARM("100", "ROLE_SENIOR"), // 시니어
    YOUTH("200", "ROLE_YOUTH"); // 자립 준비 청년

    private final String value;
    private final String name;

    Role(String value, String name) {
        this.value = value;
        this.name = name;
    }

}