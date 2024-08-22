package com.example.tech4good_server.domain.health.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HealthController {
    @GetMapping("/healthcheck")
    public String healthcheck() {
        return "OK";
    }
}
