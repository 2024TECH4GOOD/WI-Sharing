package com.example.tech4good_server.domain.auth.mapper;

import com.example.tech4good_server.global.mapper.EntityMapper;
import com.example.tech4good_server.global.model.entity.USER_INFO;
import com.example.tech4good_server.global.model.vo.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserInfoMapper extends EntityMapper<UserInfo, USER_INFO> {
    UserInfoMapper INSTANCE = Mappers.getMapper(UserInfoMapper.class);
}
