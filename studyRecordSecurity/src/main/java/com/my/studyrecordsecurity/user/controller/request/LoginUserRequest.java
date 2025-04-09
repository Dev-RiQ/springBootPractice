package com.my.studyrecordsecurity.user.controller.request;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class LoginUserRequest {
    private String username;
    private String password;
}
