package com.chatApplication.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@Data
@Getter
@Setter
@ToString
@Document
public class User {

    @Id
    private String nickName;
    private String fullName;
    private Status status;
}
