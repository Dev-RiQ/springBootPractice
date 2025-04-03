package com.basic.springboot.repository;

import com.basic.springboot.domain.UserAuthenticateEntity;

import java.util.List;

public interface UserSummary {
    String getUsername();
    String getEmail();
    List<UserAuthenticateEntity> getUserAuthenticates();
}
