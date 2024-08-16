package com.example.tech4good_server.domain.auth.service;

import com.example.tech4good_server.domain.auth.repository.UserRepository;
import com.example.tech4good_server.global.model.entity.USER_INFO;
import com.example.tech4good_server.global.model.enums.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthDelegate implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        USER_INFO userInfo = userRepository.findUSER_INFOById(userId).orElseThrow();
        Role role = userInfo.getRole();
        userInfo.setAuthorities(Collections.singleton(new SimpleGrantedAuthority(role.getValue())));

        return userInfo;
    }
}

