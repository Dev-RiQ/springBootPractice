package com.my.studyrecordsecurity.user.controller.request;

import com.my.studyrecordsecurity.security.userSecurity.domain.UserPasswordEncoder;
import com.my.studyrecordsecurity.user.domain.User;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class LoginUserRequest {
    private String username;
    private String password;
}
