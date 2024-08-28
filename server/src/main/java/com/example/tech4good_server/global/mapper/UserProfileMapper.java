package com.example.tech4good_server.global.mapper;

import com.example.tech4good_server.global.model.entity.UserInfo;
import com.example.tech4good_server.global.model.vo.UserProfileVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserProfileMapper extends EntityMapper<UserProfileVo, UserInfo> {
    UserProfileMapper INSTANCE = Mappers.getMapper(UserProfileMapper.class);
}
