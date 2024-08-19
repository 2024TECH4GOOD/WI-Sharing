package com.example.tech4good_server.domain.auth.service;

import com.example.tech4good_server.domain.auth.mapper.UserRegisterMapper;
import com.example.tech4good_server.domain.mypage.repository.SeniorInfoRepository;
import com.example.tech4good_server.domain.mypage.repository.YouthInfoRepository;
import com.example.tech4good_server.global.mapper.SeniorInfoMapper;
import com.example.tech4good_server.domain.auth.model.request.SeniorRegisterRequest;
import com.example.tech4good_server.domain.auth.model.request.YouthRegisterRequest;
import com.example.tech4good_server.domain.auth.model.request.LoginRequest;
import com.example.tech4good_server.domain.auth.model.response.TokenResponse;
import com.example.tech4good_server.domain.auth.repository.RefreshTokenRepository;
import com.example.tech4good_server.domain.auth.repository.UserRepository;
import com.example.tech4good_server.global.mapper.YouthInfoMapper;
import com.example.tech4good_server.global.model.entity.RefreshToken;
import com.example.tech4good_server.global.model.entity.SeniorInfo;
import com.example.tech4good_server.global.model.entity.UserInfo;
import com.example.tech4good_server.global.model.entity.YouthInfo;
import com.example.tech4good_server.global.security.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.tech4good_server.global.constants.AuthConstants.REFRESH_TOKEN_STR;
import static com.example.tech4good_server.global.constants.CommonConstant.ROLE;
import static com.example.tech4good_server.global.util.DateUtil.ONE_MONTH_IN_MILLISECONDS;
import static javax.swing.Action.NAME;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository userTokenRepository;
    private final YouthInfoRepository youthInfoRepository;
    private final SeniorInfoRepository seniorInfoRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRegisterMapper userRegisterMapper;
    private final SeniorInfoMapper seniorInfoMapper;
    private final YouthInfoMapper youthInfoMapper;
    private final PasswordEncoder passwordEncoder;

    /**
     * 로그인
     */
    public TokenResponse login(HttpServletResponse response, LoginRequest request){
        UserInfo userInfo = userRepository.findUserInfoById(request.getId())
                .filter(m -> passwordEncoder.matches(request.getPw(), m.getPassword()))
                .orElseThrow(IllegalArgumentException::new);

        return this.makeToken(response, userInfo);
    }

    /**
     * 청년 회원 가입 요청
     */
    public TokenResponse youthRegister(HttpServletResponse response, YouthRegisterRequest request) {
        request.getUserInfo().setPassword(this.passwordEncoding(request.getUserInfo().getPassword()));
        UserInfo userInfo = userRepository.save(userRegisterMapper.toEntity(request.getUserInfo()));

        YouthInfo youthInfo = youthInfoMapper.toEntity(request.getYouthInfo());
        youthInfo.setUserSeq(userInfo.getUserSeq());
        youthInfoRepository.save(youthInfo);

        return this.makeToken(response, userInfo);
    }

    /**
     * 시니어 회원 가입 요청
     */
    public TokenResponse seniorRegister(HttpServletResponse response, SeniorRegisterRequest request) {
        request.getUserInfo().setPassword(this.passwordEncoding(request.getUserInfo().getPassword()));
        UserInfo userInfo = userRepository.save(userRegisterMapper.toEntity(request.getUserInfo()));

        SeniorInfo seniorInfo = seniorInfoMapper.toEntity(request.getSeniorInfo());
        seniorInfo.setUserSeq(userInfo.getUserSeq());
        seniorInfoRepository.save(seniorInfo);

        return this.makeToken(response, userInfo);
    }

    /**
     * id 유효성 검사
     */
    public Boolean idValidation(String id){
        return userRepository.findUserInfoById(id).isEmpty();
    }

    /**
     * private (내부 사용)
     * 토큰 생성
     */
    private TokenResponse makeToken(HttpServletResponse response, UserInfo userInfo){
        Claims claims = Jwts.claims().setSubject(userInfo.getId());
        claims.put(NAME, userInfo.getName());
        claims.put(ROLE, userInfo.getRole());

        String accessToken = jwtTokenProvider.createAccessToken(claims);
        String refreshToken = jwtTokenProvider.createRefreshToken(claims);

        RefreshToken userToken = RefreshToken.builder()
                .userSeq(userInfo.getUserSeq())
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
     * 비밀번호 인코딩
     */
    private String passwordEncoding(String pw){
        return passwordEncoder.encode(pw);
    }

}
