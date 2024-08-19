package com.example.tech4good_server.domain.question.repository;

import com.example.tech4good_server.domain.question.model.request.QuestionSearchRequest;
import com.example.tech4good_server.global.model.entity.Question;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.tech4good_server.global.model.entity.QQuestion.question1;

@Repository
@RequiredArgsConstructor
public class QuestionQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Question> getQuestionList(QuestionSearchRequest request){
        BooleanBuilder builder = getBooleanBuilder(request);

        return queryFactory.selectFrom(question1)
                .where(builder)
                .orderBy(question1.createdAt.desc())
                .fetch();


    }

    private BooleanBuilder getBooleanBuilder(QuestionSearchRequest request) {
        BooleanBuilder builder = new BooleanBuilder();

        if(ObjectUtils.isNotEmpty(request.getQuestion())) {
            builder.and(question1.question.contains(request.getQuestion()));
        }

        if(ObjectUtils.isNotEmpty(request.getCategory())) {
            builder.and(question1.category.eq(request.getCategory()));
        }

        return builder;
    }
}
