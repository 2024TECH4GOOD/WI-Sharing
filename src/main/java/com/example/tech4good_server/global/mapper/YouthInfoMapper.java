package com.example.tech4good_server.global.mapper;

import com.example.tech4good_server.global.model.entity.YouthInfo;
import com.example.tech4good_server.global.model.vo.YouthInfoVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface YouthInfoMapper extends EntityMapper<YouthInfoVo, YouthInfo> {
    YouthInfoMapper INSTANCE = Mappers.getMapper(YouthInfoMapper.class);
}

