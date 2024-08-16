package com.example.tech4good_server.domain.auth.service;

import com.example.tech4good_server.domain.auth.mapper.UserInfoMapper;
import com.example.tech4good_server.domain.auth.model.request.SeniorRegisterRequest;
import com.example.tech4good_server.domain.auth.model.request.YouthRegisterRequest;
import com.example.tech4good_server.domain.auth.repository.UserTokenRepository;
import com.example.tech4good_server.global.model.entity.USER_INFO;
import com.example.tech4good_server.domain.auth.model.request.LoginRequest;
import com.example.tech4good_server.domain.auth.model.request.TokenResponse;
import com.example.tech4good_server.domain.auth.repository.UserRepository;
import com.example.tech4good_server.global.model.entity.USER_TOKEN;
import com.example.tech4good_server.global.security.JwtTokenProvider;
import com.example.tech4good_server.global.util.DateUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

import static com.example.tech4good_server.global.constants.AuthConstants.REFRESH_TOKEN_STR;
import static com.example.tech4good_server.global.constants.MongoField.ROLE;
import static com.example.tech4good_server.global.util.DateUtil.ONE_MONTH_IN_MILLISECONDS;
import static javax.swing.Action.NAME;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserTokenRepository userTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserInfoMapper userInfoMapper;
    private final PasswordEncoder passwordEncoder;

    /**
     * 로그인
     */
    public TokenResponse login(HttpServletResponse response, LoginRequest request){
        USER_INFO userInfo = userRepository.findUSER_INFOById(request.getId())
                .filter(m -> passwordEncoder.matches(request.getPw(), m.getPassword()))
                .orElseThrow(IllegalArgumentException::new);

        return this.makeToken(response, userInfo);
    }

    /**
     * 청년 회원 가입 요청
     */
    public TokenResponse youthRegister(HttpServletResponse response, YouthRegisterRequest request) {
        long txTime = DateUtil.dateToEpochMilli(LocalDateTime.now());

        request.getUserInfo().setUserId("Y" + "_" + this.makeRandomValue() + "_"+"USER" + "_" + txTime);
        request.getUserInfo().setPassword(this.passwordEncoding(request.getUserInfo().getPassword()));

        USER_INFO userInfo = userRepository.save(userInfoMapper.toEntity(request.getUserInfo()));

        // TODO : MVP 확정 시 청년 정보 update

        return this.makeToken(response, userInfo);
    }

    /**
     * 시니어 회원 가입 요청
     */
    public TokenResponse seniorRegister(HttpServletResponse response, SeniorRegisterRequest request) {
        long txTime = DateUtil.dateToEpochMilli(LocalDateTime.now());

        request.getUserInfo().setUserId("S" + "_" +  this.makeRandomValue() +"_"+"USER" + "_" + txTime);
        request.getUserInfo().setPassword(this.passwordEncoding(request.getUserInfo().getPassword()));

        USER_INFO userInfo = userRepository.save(userInfoMapper.toEntity(request.getUserInfo()));

        // TODO : MVP 확정 시 시니어 정보 update

        return this.makeToken(response, userInfo);
    }

    /**
     * private (내부 사용)
     * 토큰 생성
     */
    private TokenResponse makeToken(HttpServletResponse response, USER_INFO userInfo){
        Claims claims = Jwts.claims().setSubject(userInfo.getUserId());
        claims.put(NAME, userInfo.getName());
        claims.put(ROLE, userInfo.getRole());

        String accessToken = jwtTokenProvider.createAccessToken(claims);
        String refreshToken = jwtTokenProvider.createRefreshToken(claims);

        USER_TOKEN userToken = USER_TOKEN.builder()
                .id(userInfo.getId())
                .token(accessToken)
                .build();

        userTokenRepository.save(userToken);

        Cookie cookie = new Cookie(REFRESH_TOKEN_STR, refreshToken);
        cookie.setMaxAge((int) ONE_MONTH_IN_MILLISECONDS);
        response.addCookie(cookie);

        return TokenResponse.builder()
                .accessToken(accessToken)
                .build();
    }

    /**
     * private (내부 사용)
     * 3-5글자의 랜덤한 값 생성
     */
    private String makeRandomValue(){
        int length = (int) (Math.floor(Math.random() * 3) + 2);
        int leftLimit = 48;
        int rightLimit = 122;

        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .filter(j -> (j <= 57 || j >= 65) && (j <= 90 || j >= 97))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    /**
     * private (내부 사용)
     * 비밀번호 인코딩
     */
    private String passwordEncoding(String pw){
        return passwordEncoder.encode(pw);
    }

}
