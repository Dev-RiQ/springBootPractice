package com.my.studyrecordsecurity.security.userSecurity.domain;

public interface OAuth2Response {
    String getProvider();
    String getProviderId();
    String getEmail();
    String getName();
}
