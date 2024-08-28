package com.example.tech4good_server.domain.question.model.response;

import com.example.tech4good_server.global.model.vo.QuestionVo;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponse {
    QuestionVo question;
}
