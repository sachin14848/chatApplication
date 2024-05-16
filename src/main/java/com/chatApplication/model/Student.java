package com.chatApplication.model;

import lombok.Data;
import lombok.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "student")
public class Student {

    @Id
//    @Generated
    private String id;
    private String name;
    private String emailId;
}
