package com.example.tech4good_server.global.security;

import com.example.tech4good_server.global.model.enums.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import static com.example.tech4good_server.global.constants.AuthConstants.*;
import static com.example.tech4good_server.global.constants.CommonConstant.ROLE;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        try {
            String accessToken = "";
            if(request.getHeader(AUTHORIZATION) != null)
                accessToken = request.getHeader(AUTHORIZATION).replace(BEARER, "");


            if (jwtTokenProvider.validateJwtTokenExpireTime(accessToken)) {
                this.setAuthentication(accessToken);
            } else {
                String refreshToken = request.getHeader(REFRESH_TOKEN_STR);

                if (jwtTokenProvider.validateJwtTokenExpireTime(refreshToken)) {
                    String newAccessToken = this.createNewAccessToken(refreshToken);

                    response.setHeader(ACCESS_TOKEN_STR, accessToken);
                    this.setAuthentication(newAccessToken);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    private String createNewAccessToken(String refreshToken) {
        String userId = jwtTokenProvider.getUserId(refreshToken);
        Role role = jwtTokenProvider.getRole(refreshToken);

        Claims claims = Jwts.claims().setSubject(userId);
        claims.put(ROLE, role);

        return jwtTokenProvider.createNewAccessToken(claims);
    }

    public void setAuthentication(String accessToken) {
        Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
