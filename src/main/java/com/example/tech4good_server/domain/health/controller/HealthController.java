package com.example.tech4good_server.domain.health.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HealthController {
    @GetMapping("/healthcheck")
    public ResponseEntity<String> healthcheck() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}
