package com.example.tech4good_server.global.config.security;

import com.example.tech4good_server.global.security.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.example.tech4good_server.global.constants.AuthConstants.AUTHORIZATION;

@Configuration
@EnableWebMvc
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {

        // TODO Domain 확정 나면 추가
        corsRegistry.addMapping("/**")
                .allowedOrigins("http://localhost:3000","https://localhost:3000",
                        "http://localhost:80","https://localhost:80")
                .allowedMethods("*")
                .allowedHeaders("*")
                .maxAge(3600L)
                .exposedHeaders(AUTHORIZATION)
                .allowCredentials(true);

    }

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        LoginInterceptor loginInterceptor = new LoginInterceptor();

        interceptorRegistry.addInterceptor(loginInterceptor)
                .addPathPatterns(loginInterceptor.loginEssential)
                .excludePathPatterns(loginInterceptor.loginInessential);
    }

}

