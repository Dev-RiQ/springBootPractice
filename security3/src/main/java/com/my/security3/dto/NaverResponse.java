package com.my.security3.dto;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.Map;

@ToString
@AllArgsConstructor
public class NaverResponse implements OAuth2Response{

    private final Map<String, Object> attribute;

    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getProviderId() {
        return attribute.get("id").toString();
    }

    @Override
    public String getEmail() {
        return attribute.get("email").toString();
    }

    @Override
    public String getName() {
        return attribute.get("name").toString();
    }
}
