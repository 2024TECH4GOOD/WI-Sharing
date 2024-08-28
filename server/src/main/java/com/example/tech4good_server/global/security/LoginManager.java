package com.example.tech4good_server.global.security;

import com.example.tech4good_server.global.model.entity.UserInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

import static com.example.tech4good_server.global.constants.AuthConstants.ANONYMOUS_USER;

public class LoginManager {

    public static UserInfo getUserDetails() {
        UserInfo userDetails;

        try {
            userDetails = (UserInfo) getDetails();
        } catch (Exception e) {
            return null;
        }

        return userDetails;
    }


    public static Object getDetails() {
        Authentication authentication = getAuthentication();

        if (authentication == null) {
            return null;
        }

        return authentication.getPrincipal();
    }

    public static boolean isLogin() {
        String userName = Objects.requireNonNull(getAuthentication()).getName();
        if (ANONYMOUS_USER.equals(userName)) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }

    public static Authentication getAuthentication() {
        SecurityContext context;

        try {
            context = SecurityContextHolder.getContext();
            if (context == null) {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }

        return context.getAuthentication();
    }

}

