package com.example.tech4good_server.global.mapper;

import com.example.tech4good_server.global.model.entity.Answer;
import com.example.tech4good_server.global.model.vo.AnswerVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnswerMapper extends EntityMapper<AnswerVo, Answer> {
    AnswerMapper INSTANCE = Mappers.getMapper(AnswerMapper.class);
}


