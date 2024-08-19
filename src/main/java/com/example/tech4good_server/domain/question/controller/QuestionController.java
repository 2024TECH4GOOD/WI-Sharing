package com.example.tech4good_server.domain.question.controller;

import com.example.tech4good_server.domain.question.model.request.QuestionRequest;
import com.example.tech4good_server.domain.question.model.request.QuestionSearchRequest;
import com.example.tech4good_server.domain.question.model.response.QuestionDetailResponse;
import com.example.tech4good_server.domain.question.model.response.QuestionListResponse;
import com.example.tech4good_server.domain.question.model.response.QuestionResponse;
import com.example.tech4good_server.domain.question.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public QuestionResponse question(@RequestBody QuestionRequest questionRequest) {
        return questionService.question(questionRequest);
    }

    /**
     * 질문 상세 보기 Controller
     */
    @Operation(summary = "질문 상세 보기", description = """
             질문과 질문에 대한 답변 List 조회
            """)
    @GetMapping("/{questionSeq}")
    public QuestionDetailResponse getQuestionDetail(@PathVariable("questionSeq") Integer questionSeq){
        return questionService.getQuestionDetail(questionSeq);
    }

    /**
     * 질문 List 보기 Controller
     */
    @Operation(summary = "질문 List 보기", description = """
             질문 List 조회
            """)
    @GetMapping("/list")
    public QuestionListResponse getQuestionList(@Valid QuestionSearchRequest questionSearchRequest){
        return questionService.getQuestionList(questionSearchRequest);
    }

}
