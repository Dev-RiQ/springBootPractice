package com.basic.springboot.domain.response;

import com.basic.springboot.domain.UserEntity;
import lombok.Getter;

@Getter
public class UserResponseDTO {
    private final String username;
    private final String email;
    public UserResponseDTO(UserEntity user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
