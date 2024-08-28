package com.example.tech4good_server.global.mapper;

import com.example.tech4good_server.global.component.data.DataProcessComponent;
import com.example.tech4good_server.global.model.entity.UserInfo;
import com.example.tech4good_server.global.model.vo.UserInfoVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserInfoMapper extends EntityMapper<UserInfoVo, UserInfo> {

    @Mapping(target = "personality", expression = "java(dataProcessComponent.convertListToString(vo.getPersonality()))")
    @Mapping(target = "interest", expression = "java(dataProcessComponent.convertListToString(vo.getInterest()))")
    @Mapping(target = "hobby", expression = "java(dataProcessComponent.convertListToString(vo.getHobby()))")
    UserInfo toEntity(UserInfoVo vo);

    @Mapping(target = "personality", expression = "java(dataProcessComponent.convertStringToList(entity.getPersonality()))")
    @Mapping(target = "interest", expression = "java(dataProcessComponent.convertStringToList(entity.getInterest()))")
    @Mapping(target = "hobby", expression = "java(dataProcessComponent.convertStringToList(entity.getHobby()))")
    UserInfoVo toDto(UserInfo entity);

    DataProcessComponent dataProcessComponent = new DataProcessComponent();
}
