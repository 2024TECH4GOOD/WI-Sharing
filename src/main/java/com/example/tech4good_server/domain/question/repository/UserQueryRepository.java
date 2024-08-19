package com.example.tech4good_server.domain.question.repository;

import com.example.tech4good_server.global.model.vo.UserProfileVo;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.tech4good_server.global.model.entity.QUserInfo.userInfo;

@Repository
@RequiredArgsConstructor
public class UserQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<UserProfileVo> findUserNamesByUserSeqIn(List<Integer> userSeqs){
        return queryFactory.from(userInfo)
                .select(Projections.constructor(UserProfileVo.class,
                        userInfo.userSeq,
                        userInfo.name
                ))
                .from(userInfo)
                .where(userInfo.userSeq.in(userSeqs))
                .fetch();
    }
}
