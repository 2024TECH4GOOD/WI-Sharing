package com.example.tech4good_server.global.model.vo;

import com.example.tech4good_server.global.model.enums.QuestionCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionVo {
    private Integer questionSeq;

    private Integer userSeq;

    private String name;

    private String question;

    private QuestionCategory category;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

}
