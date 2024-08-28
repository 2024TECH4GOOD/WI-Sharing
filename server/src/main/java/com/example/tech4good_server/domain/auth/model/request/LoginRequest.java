package com.example.tech4good_server.domain.auth.model.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String id;
    private String pw;
}
