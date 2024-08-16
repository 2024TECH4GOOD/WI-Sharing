package com.example.tech4good_server.global.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "USER_TOKEN")
@SuperBuilder
@NoArgsConstructor
public class USER_TOKEN {
    @Id
    private String id;
    private String token;
}

