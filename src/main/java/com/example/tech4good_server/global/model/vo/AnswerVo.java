package com.example.tech4good_server.global.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerVo {
    private Integer answerSeq;

    private Integer questionSeq;

    private Integer userSeq;

    private String name;

    private String answer;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;
}
