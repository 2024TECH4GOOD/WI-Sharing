package com.example.tech4good_server.global.mapper;

import com.example.tech4good_server.global.model.entity.Question;
import com.example.tech4good_server.global.model.vo.QuestionVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuestionMapper extends EntityMapper<QuestionVo, Question> {
    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);
}

