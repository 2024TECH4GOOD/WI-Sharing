package com.example.tech4good_server.domain.mypage.service;

import com.example.tech4good_server.domain.auth.repository.UserRepository;
import com.example.tech4good_server.domain.auth.service.AuthDelegate;
import com.example.tech4good_server.domain.mypage.model.request.SeniorOnboardingRequest;
import com.example.tech4good_server.domain.mypage.model.request.YouthOnboardingRequest;
import com.example.tech4good_server.domain.mypage.model.response.*;
import com.example.tech4good_server.domain.mypage.repository.SeniorInfoRepository;
import com.example.tech4good_server.global.mapper.SeniorInfoMapper;
import com.example.tech4good_server.global.mapper.UserInfoMapper;
import com.example.tech4good_server.global.mapper.UserProfileMapper;
import com.example.tech4good_server.global.model.entity.SeniorInfo;
import com.example.tech4good_server.global.model.entity.UserInfo;
import com.example.tech4good_server.global.security.LoginManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyPageService {
    private final SeniorInfoRepository seniorInfoRepository;
    private final UserRepository userRepository;
    private final UserInfoMapper userInfoMapper;
    private final SeniorInfoMapper seniorInfoMapper;
    private final UserProfileMapper userProfileMapper;
    private final AuthDelegate authDelegate;

    /**
     * BOTH
     */

    /**
     * 이름, 프로필 조회
     */
    public UserProfileResponse profileInfo(){
        UserProfileResponse userProfileResponse = new UserProfileResponse();

        UserInfo userInfo = LoginManager.getUserDetails();
        userProfileResponse.setUserProfile(userProfileMapper.toDto(userInfo));

        if (userInfo != null)
            userProfileResponse.setMentoringAgreement(userInfo.getMentoringAgreement());

        return userProfileResponse;
    }

    /**
     * 비밀번호 변경
     */
    public void updatePassword(String password){
        UserInfo userInfo = LoginManager.getUserDetails();

        assert userInfo != null;
        userInfo.setPassword(authDelegate.passwordEncoding(password));
        userRepository.save(userInfo);
    }

    /**
     * YOUTH
     */

    /**
     * 내 정보 조회
     */
    public YouthMyInfoResponse youthMyInfo(){
        YouthMyInfoResponse youthMyInfoResponse = new YouthMyInfoResponse();
        UserInfo userInfo = LoginManager.getUserDetails();
        youthMyInfoResponse.setUserInfo(userInfoMapper.toDto(userInfo));

        return youthMyInfoResponse;
    }

    /**
     * 온보딩 다시 하기
     */
    public YouthOnboardingResponse youthOnboarding(YouthOnboardingRequest request){
        YouthOnboardingResponse youthOnboardingResponse = new YouthOnboardingResponse();

        UserInfo userInfo = userInfoMapper.toEntity(request.getUserInfo());
        userInfo.updateUserInfo(userInfo);
        userRepository.save(userInfo);
        youthOnboardingResponse.setUserInfo(userInfoMapper.toDto(userInfo));

        return youthOnboardingResponse;
    }


    /**
     * SENIOR
     */

    public SeniorMyInfoResponse seniorMyInfo(){
        SeniorMyInfoResponse seniorMyInfoResponse = new SeniorMyInfoResponse();
        UserInfo userInfo = LoginManager.getUserDetails();
        seniorMyInfoResponse.setUserInfo(userInfoMapper.toDto(userInfo));

        assert userInfo != null;
        SeniorInfo seniorInfo = seniorInfoRepository.findByUserSeq(userInfo.getUserSeq());
        seniorMyInfoResponse.setSeniorInfo(seniorInfoMapper.toDto(seniorInfo));

        return seniorMyInfoResponse;
    }

    /**
     * 온보딩 다시 하기
     */
    public SeniorOnboardingResponse seniorOnboarding(SeniorOnboardingRequest request){
        SeniorOnboardingResponse seniorOnboardingResponse = new SeniorOnboardingResponse();
        Integer loginUserSeq = Objects.requireNonNull(LoginManager.getUserDetails()).getUserSeq();

        UserInfo userInfo = userInfoMapper.toEntity(request.getUserInfo());
        userInfo.updateUserInfo(userInfo);
        userRepository.save(userInfo);
        seniorOnboardingResponse.setUserInfo(userInfoMapper.toDto(userInfo));

        SeniorInfo seniorInfo = seniorInfoMapper.toEntity(request.getSeniorInfo());
        seniorInfo.setUserSeq(loginUserSeq);
        seniorInfoRepository.save(seniorInfo);
        seniorOnboardingResponse.setSeniorInfo(seniorInfoMapper.toDto(seniorInfo));

        return seniorOnboardingResponse;
    }


}
