package com.example.tech4good_server.domain.auth.mapper;

import com.example.tech4good_server.domain.auth.model.vo.UserRegisterVo;
import com.example.tech4good_server.global.mapper.EntityMapper;
import com.example.tech4good_server.global.model.entity.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRegisterMapper extends EntityMapper<UserRegisterVo, UserInfo> {
    UserRegisterMapper INSTANCE = Mappers.getMapper(UserRegisterMapper.class);
}
