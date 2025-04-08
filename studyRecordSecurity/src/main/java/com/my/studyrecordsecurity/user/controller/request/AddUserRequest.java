package com.my.studyrecordsecurity.user.controller.request;

import com.my.studyrecordsecurity.user.domain.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddUserRequest {
    private String username;
    private String password;
    private String name;
    private String email;

    public User toEntity(AddUserRequest dto){
        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .name(dto.getName())
                .email(dto.getEmail())
                .build();
    }
}
