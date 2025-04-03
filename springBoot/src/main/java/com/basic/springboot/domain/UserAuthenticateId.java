package com.basic.springboot.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class UserAuthenticateId {
    private String username;
    private String role;
}
