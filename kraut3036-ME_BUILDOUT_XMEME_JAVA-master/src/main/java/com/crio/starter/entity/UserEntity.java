package com.crio.starter.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class UserEntity {

    @Id
    private String id;

    private String username;
    private String password; // hashed
    private Role role;

    public UserEntity() {}

    public UserEntity(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}