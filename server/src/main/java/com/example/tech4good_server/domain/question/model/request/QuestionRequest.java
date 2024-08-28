package com.example.tech4good_server.domain.question.model.request;

import com.example.tech4good_server.global.model.enums.QuestionCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequest {
    private String question;

    private QuestionCategory category;

}
