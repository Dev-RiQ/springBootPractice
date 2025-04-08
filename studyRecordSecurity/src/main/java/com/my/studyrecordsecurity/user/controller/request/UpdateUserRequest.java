package com.my.studyrecordsecurity.user.controller.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UpdateUserRequest {
    private String password;
    private String name;
}
