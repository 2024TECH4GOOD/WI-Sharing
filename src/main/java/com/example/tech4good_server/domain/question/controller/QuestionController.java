package com.example.tech4good_server.domain.question.controller;

import com.example.tech4good_server.domain.question.model.request.QuestionRequest;
import com.example.tech4good_server.domain.question.model.request.QuestionSearchRequest;
import com.example.tech4good_server.domain.question.model.response.QuestionDetailResponse;
import com.example.tech4good_server.domain.question.model.response.QuestionListResponse;
import com.example.tech4good_server.domain.question.model.response.QuestionResponse;
import com.example.tech4good_server.domain.question.service.QuestionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/question")
@Tag(name = "Question", description = "Q&A 컨트롤러")
/**
 * Q&A  관련 Controller
 **/
public class QuestionController {
    private final QuestionService questionService;

    /**
     * 질문 하기 Controller
     */
    @Operation(summary = "질문 하기", description = """
             로그인 한 자립 준비 청년 질문 올리기
            """)
    @PostMapping("")
    public ResponseEntity<QuestionResponse> question(@RequestBody QuestionRequest questionRequest) throws JsonProcessingException {
        return new ResponseEntity<>(questionService.question(questionRequest), HttpStatus.OK);
    }

    /**
     * 질문 상세 보기 Controller
     */
    @Operation(summary = "질문 상세 보기", description = """
             질문과 질문에 대한 답변 List 조회
            """)
    @GetMapping("/{questionSeq}")
    public  ResponseEntity<QuestionDetailResponse> getQuestionDetail(@PathVariable("questionSeq") Integer questionSeq){
        return new ResponseEntity<>(questionService.getQuestionDetail(questionSeq), HttpStatus.OK);
    }

    /**
     * 질문 List 보기 Controller
     */
    @Operation(summary = "질문 List 보기", description = """
             질문 List 조회
            """)
    @GetMapping("/list")
    public ResponseEntity<QuestionListResponse> getQuestionList(@Valid QuestionSearchRequest questionSearchRequest){
        return new ResponseEntity<>(questionService.getQuestionList(questionSearchRequest), HttpStatus.OK);
    }

}
