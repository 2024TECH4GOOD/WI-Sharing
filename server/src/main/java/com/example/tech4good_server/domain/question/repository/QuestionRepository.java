package com.example.tech4good_server.domain.question.repository;

import com.example.tech4good_server.global.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{
    Question findQuestionByQuestionSeq(Integer questionSeq);
}
