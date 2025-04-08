package com.my.studyrecordsecurity.user.controller.response;

import com.my.studyrecordsecurity.user.domain.User;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserViewResponse {
    private Long id;
    private String username;
    private String name;
    private String role;

    public UserViewResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getUsername();
        this.role = user.getRole().toString();
    }
}
