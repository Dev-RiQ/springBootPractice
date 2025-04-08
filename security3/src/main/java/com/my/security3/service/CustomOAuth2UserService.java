package com.my.security3.service;

import com.my.security3.domain.UserEntity;
import com.my.security3.dto.CustomOAuth2User;
import com.my.security3.dto.GoogleResponse;
import com.my.security3.dto.NaverResponse;
import com.my.security3.dto.OAuth2Response;
import com.my.security3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        switch (registrationId) {
            case "google": oAuth2Response = new GoogleResponse(oAuth2User.getAttributes()); break;
            case "naver": oAuth2Response = new NaverResponse(oAuth2User.getAttribute("response")); break;
        }
        String userId = oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();
        UserEntity existData = userRepository.findByUserId(userId);

        String role = "ROLE_USER";
        if (existData == null) { // 처음에 들어올때
            UserEntity userEntity = new UserEntity();
            userEntity.setUserId(userId);
            userEntity.setUsername(oAuth2Response.getName());
            userEntity.setEmail(oAuth2Response.getEmail());
            userEntity.setRole(role);
            userEntity.setPassword("temp_password");

            userRepository.save(userEntity); // 우리 db 저장
        }
        else {
            existData.setUserId(userId); // 두번째 들어오는 값이면
            existData.setEmail(oAuth2Response.getEmail()); // 다시 들어온 값으로 email 수정

            role = existData.getRole();

            userRepository.save(existData);
        }

        return new CustomOAuth2User(oAuth2Response, role);
    }
}
