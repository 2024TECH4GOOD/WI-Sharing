package com.example.tech4good_server.domain.match.controller;

import com.example.tech4good_server.global.component.ai.AIComponent;
import com.example.tech4good_server.global.model.vo.MentorInfoVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    private final AIComponent aiComponent;


    @Operation(summary = "매칭 된 멘토 조회 (3명)", description = """
             희망하는 컨셉 받아 매칭 된 멘토 조회 (3명)
            """)
    @GetMapping("")
    public ResponseEntity<List<MentorInfoVo>> getMatchedMentor(Integer concept) throws JsonProcessingException {
        return new ResponseEntity<>(aiComponent.getMatchedMentor(concept), HttpStatus.OK);
    }

}
