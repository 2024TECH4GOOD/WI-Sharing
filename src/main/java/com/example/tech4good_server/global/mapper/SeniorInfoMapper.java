package com.example.tech4good_server.global.mapper;

import com.example.tech4good_server.global.model.entity.SeniorInfo;
import com.example.tech4good_server.global.model.vo.SeniorInfoVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SeniorInfoMapper extends EntityMapper<SeniorInfoVo, SeniorInfo> {
    SeniorInfoMapper INSTANCE = Mappers.getMapper(SeniorInfoMapper.class);
}

