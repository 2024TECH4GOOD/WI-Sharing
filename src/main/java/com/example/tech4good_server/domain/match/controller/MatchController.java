package com.example.tech4good_server.domain.match.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/match")
@Tag(name = "Match", description = "멘토 매칭 컨트롤러")
/**
 * 멘토 매칭 Controller
 **/
public class MatchController {
}
