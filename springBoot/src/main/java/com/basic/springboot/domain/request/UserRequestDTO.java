package com.basic.springboot.domain.request;

import com.basic.springboot.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class UserRequestDTO {
    private String username;
    private String password;
    private String email;

//    public UserEntity toUserEntity(String userName, String password, String email) {}
    public UserEntity toUserEntity() {
//        return new UserEntity(username, password, email);
        return UserEntity.builder()
                .username(username)
                .password(password)
                .email(email)
                .build();
    }
}
