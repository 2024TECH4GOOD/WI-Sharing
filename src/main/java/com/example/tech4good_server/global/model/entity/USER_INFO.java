package com.example.tech4good_server.global.model.entity;

import com.example.tech4good_server.global.constants.MongoField;
import com.example.tech4good_server.global.model.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

@Data
@Document(collection = "USER_INFO")
@SuperBuilder
@NoArgsConstructor
public class USER_INFO implements UserDetails {
    @Id
    @Field(MongoField._ID)
    private String userId;

    // id
    private String id;
    // pw
    private String password;

    // 이름
    private String name;

    // 성별
    private String sex;

    // 생일
    private Date birth;

    // 핸드폰 번호
    @Field(MongoField.PHONE_NUMBER)
    private String phoneNumber;

    // 프로필 이미지
    @Field(MongoField.PROFILE_URL)
    private String profileUrl;

    // 자립 준비 청년, 시니어
    private Role role;

    @Transient
    private Collection<SimpleGrantedAuthority> authorities;

    @Override
    public String getUsername() {
        return this.userId;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

}

