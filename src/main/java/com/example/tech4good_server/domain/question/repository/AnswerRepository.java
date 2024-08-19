package com.example.tech4good_server.domain.question.repository;

import com.example.tech4good_server.global.model.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    List<Answer> findAnswerByQuestionSeq(Integer questionSeq);
}
