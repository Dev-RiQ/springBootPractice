package com.my.studyrecordsecurity.security.userSecurity.dto;

import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
public class UserPasswordEncoder {
    private final String password;
    public UserPasswordEncoder(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.password = bCryptPasswordEncoder.encode(password);
    }
}
