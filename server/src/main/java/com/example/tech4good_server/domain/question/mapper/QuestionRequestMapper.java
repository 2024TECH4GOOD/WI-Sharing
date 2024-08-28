package com.example.tech4good_server.domain.question.mapper;

import com.example.tech4good_server.domain.question.model.request.QuestionRequest;
import com.example.tech4good_server.global.mapper.EntityMapper;
import com.example.tech4good_server.global.model.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuestionRequestMapper extends EntityMapper<QuestionRequest, Question> {
    QuestionRequestMapper INSTANCE = Mappers.getMapper(QuestionRequestMapper.class);
}

