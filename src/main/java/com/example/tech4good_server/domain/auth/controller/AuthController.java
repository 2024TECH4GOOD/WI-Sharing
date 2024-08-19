package com.example.tech4good_server.domain.auth.controller;


import com.example.tech4good_server.domain.auth.model.request.LoginRequest;
import com.example.tech4good_server.domain.auth.model.request.SeniorRegisterRequest;
import com.example.tech4good_server.domain.auth.model.response.TokenResponse;
import com.example.tech4good_server.domain.auth.model.request.YouthRegisterRequest;
import com.example.tech4good_server.domain.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
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
@RequestMapping(value = "/api/auth")
@Tag(name = "Auth", description = "회원가입, 로그인 관련 컨트롤러")
/**
 * 회원가입, 로그인 관련 Controller
 **/
public class AuthController {

    private final AuthService authService;

    /**
     * 로그인 Controller
     */
    @Operation(summary = "로그인", description = """
             아이디, 비밀번호로 로그인 한다.
            """)
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> doLogin(HttpServletResponse response, LoginRequest loginRequest) {
        return new ResponseEntity<>(authService.login(response, loginRequest), HttpStatus.OK);
    }

    /**
     * id 유효성 검사
     */
    @Operation(summary = "아이디 유효성 검사", description = """
             중복 아이디가 있는지 체크한다.
             사용 가능하면 true, 중복된 아이디가 있으면 false 반환
            """)
    @GetMapping("/validate")
    public ResponseEntity<Boolean> idValidation(String id) {
        return new ResponseEntity<>(authService.idValidation(id), HttpStatus.OK);
    }


    /**
     * 청년 회원 가입 요청 Controller
     */
    @Operation(summary = "자립 준비 청년 회원 가입 요청", description = """
             청년 회원 가입 한다.
            """)
    @PostMapping("/register/youth")
    public  ResponseEntity<TokenResponse> youthRegister(HttpServletResponse response, @RequestBody YouthRegisterRequest youthRegisterRequest) {
        return  new ResponseEntity<>(authService.youthRegister(response, youthRegisterRequest), HttpStatus.OK);
    }

    /**
     * 농가 회원 가입 요청 Controller
     */
    @Operation(summary = "시니어 회원 가입 요청", description = """
             시니어 회원 가입 한다.
            """)
    @PostMapping("/register/senior")
    public ResponseEntity<TokenResponse> farmerRegister(HttpServletResponse response, @RequestBody SeniorRegisterRequest seniorRegisterRequest) {
        return new ResponseEntity<>(authService.seniorRegister(response, seniorRegisterRequest), HttpStatus.OK);

    }

}

