package com.example.tech4good_server.global.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MentorInfoVo {

    private String name;
    private String career;
    private String personality;
    private String similaritySum;

}
