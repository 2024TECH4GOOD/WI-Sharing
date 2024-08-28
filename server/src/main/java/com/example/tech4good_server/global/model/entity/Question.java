package com.example.tech4good_server.global.model.entity;

import com.example.tech4good_server.global.model.enums.QuestionCategory;
import com.example.tech4good_server.global.security.LoginManager;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionSeq;

    private Integer userSeq;

    private String question;

    @Enumerated(value = EnumType.STRING)
    private QuestionCategory category;

    private Boolean isDeleted;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private LocalDateTime deletedAt;

    @PrePersist
    public void prePersist(){
        this.userSeq = Objects.requireNonNull(LoginManager.getUserDetails()).getUserSeq();
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        if(LoginManager.isLogin())
            this.modifiedAt = LocalDateTime.now();
    }

}
