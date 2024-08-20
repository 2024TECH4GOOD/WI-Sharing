package com.example.tech4good_server.global.model.entity;

import com.example.tech4good_server.global.model.enums.Role;
import com.example.tech4good_server.global.security.LoginManager;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@Entity(name = "USER_INFO")
public class UserInfo implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userSeq;

    // id
    private String id;

    // pw
    private String password;

    // 이름
    private String name;

    // 성별
    private String sex;

    // 나이
    private Integer age;

    // 생일
    private LocalDate birth;

    // 핸드폰 번호
    private String phoneNumber;

    // 프로필 이미지
    private String profileUrl;

    // 거주지
    private String location;

    // 성격
    private String personality;

    // 관심사
    private String interest;

    // 희망 진로 (청년), 경력 사항 (시니어)
    private String career;

    // 취미
    private String hobby;

    // 자립 준비 청년, 시니어
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Transient
    private Collection<SimpleGrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void updateUserInfo(UserInfo userInfo){
        Integer loginUserSeq = Objects.requireNonNull(LoginManager.getUserDetails()).getUserSeq();

        userInfo.setUserSeq(loginUserSeq);
        userInfo.setPassword(LoginManager.getUserDetails().getPassword());

    }

}
