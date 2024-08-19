package com.example.tech4good_server.domain.question.model.request;

import com.example.tech4good_server.global.model.enums.QuestionCategory;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionSearchRequest {
    private String question;

    private QuestionCategory category;
}
