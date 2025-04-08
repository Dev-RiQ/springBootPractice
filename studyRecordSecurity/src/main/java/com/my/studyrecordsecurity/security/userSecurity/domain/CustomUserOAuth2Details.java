package com.my.studyrecordsecurity.security.userSecurity.domain;

import com.my.studyrecordsecurity.user.domain.Role;
import com.my.studyrecordsecurity.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@RequiredArgsConstructor
@ToString
public class CustomUserOAuth2Details implements OAuth2User {

    private OAuth2Response oAuth2Response;
    private Role role;

    public CustomUserOAuth2Details(OAuth2Response oAuth2Response, Role role) {
        this.oAuth2Response = oAuth2Response;
        this.role = role;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add((GrantedAuthority) role::toString);
        return authorities;
    }


    @Override
    public String getName() {
        return oAuth2Response.getName();
    }

    public String getUserId() {
        return oAuth2Response.getProviderId();
    }

    public String getEmail() {
        return oAuth2Response.getEmail();
    }
}
