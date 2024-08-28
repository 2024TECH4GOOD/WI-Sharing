package com.example.tech4good_server.domain.question.model.response;

import com.example.tech4good_server.global.model.vo.AnswerVo;
import com.example.tech4good_server.global.model.vo.QuestionVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDetailResponse {
    QuestionVo question;
    List<AnswerVo> answerList;
}
