package com.example.tech4good_server.global.mapper;

import com.example.tech4good_server.global.model.entity.UserInfo;
import com.example.tech4good_server.global.model.vo.UserInfoVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserInfoMapper extends EntityMapper<UserInfoVo, UserInfo> {
    UserInfoMapper INSTANCE = Mappers.getMapper(UserInfoMapper.class);
}
