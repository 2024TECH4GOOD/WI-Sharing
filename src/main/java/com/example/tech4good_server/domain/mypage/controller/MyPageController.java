package com.example.tech4good_server.domain.mypage.controller;

import com.example.tech4good_server.domain.mypage.model.request.PwRequest;
import com.example.tech4good_server.domain.mypage.model.request.SeniorOnboardingRequest;
import com.example.tech4good_server.domain.mypage.model.request.YouthOnboardingRequest;
import com.example.tech4good_server.domain.mypage.model.response.*;
import com.example.tech4good_server.domain.mypage.service.MyPageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/my")
@Tag(name = "MyPage", description = "마이페이지 컨트롤러")
/**
 * 마이 페이지 Controller
 **/
public class MyPageController {
    private final MyPageService myPageService;

    /**
     * 로그인한 사용자의 프로필 정보 조회 Controller
     */
    @Operation(summary = "로그인 한 사용자의 프로필 정보 조회", description = """
             로그인 한 사용자의 프로필 정보 조회 (자립준비청년, 시니어 공통 사용)
            """)
    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> profileInfo(){
        return new ResponseEntity<>(myPageService.profileInfo(), HttpStatus.OK);
    }

    /**
     * 비밀번호 변경
     */
    @Operation(summary = "비밀번호 변경", description = """
             로그인한 사용자의 비밀번호 변경
            """)
    @PostMapping("/update")
    public ResponseEntity<Void> updatePassword(@RequestBody PwRequest request) {
        myPageService.updatePassword(request.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 자립 준비 청년 마이 페이지 Controller
     */
    @Operation(summary = "자립 준비 청년 마이 페이지 확인", description = """
             로그인 한 자립 준비 청년의 정보 확인
            """)
    @GetMapping("/youth/info")
    public ResponseEntity<YouthMyInfoResponse> youthMyInfo() {
        return new ResponseEntity<>(myPageService.youthMyInfo(), HttpStatus.OK);
    }

    /**
     * 자립 준비 청년 온보딩 수정 Controller
     */
    @Operation(summary = "자립 준비 청년 온보딩 다시 하기", description = """
             로그인 한 자립 준비 청년 온보딩 다시 하기
            """)
    @PutMapping("/youth/onboarding")
    public ResponseEntity<YouthOnboardingResponse> youthOnboarding(@RequestBody YouthOnboardingRequest request) {
        return new ResponseEntity<>(myPageService.youthOnboarding(request), HttpStatus.OK);
    }

    /**
     * 시니어 마이 페이지 Controller
     */
    @Operation(summary = "시니어 마이 페이지 확인", description = """
             로그인 한 시니어의 정보 확인
            """)
    @GetMapping("/senior/info")
    public ResponseEntity<SeniorMyInfoResponse> seniorMyInfo() {
        return new ResponseEntity<>(myPageService.seniorMyInfo(), HttpStatus.OK);
    }

    /**
     * 시니어 온보딩 수정 Controller
     */
    @Operation(summary = "시니어 온보딩 다시 하기", description = """
             로그인 한 시니어 온보딩 다시 하기
            """)
    @PutMapping("/senior/onboarding")
    public ResponseEntity<SeniorOnboardingResponse> youthOnboarding(@RequestBody SeniorOnboardingRequest request) {
        return new ResponseEntity<>(myPageService.seniorOnboarding(request), HttpStatus.OK);
    }

}
