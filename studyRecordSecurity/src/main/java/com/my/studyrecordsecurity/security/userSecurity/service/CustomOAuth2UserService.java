package com.my.studyrecordsecurity.security.userSecurity.service;

import com.my.studyrecordsecurity.security.userSecurity.domain.*;
import com.my.studyrecordsecurity.user.domain.Role;
import com.my.studyrecordsecurity.user.domain.Type;
import com.my.studyrecordsecurity.user.domain.User;
import com.my.studyrecordsecurity.user.repository.UserRepository;
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
        OAuth2Response oAuth2Response = switch (registrationId) {
            case "google" -> new GoogleResponse(oAuth2User.getAttributes());
            case "naver" -> new NaverResponse(oAuth2User.getAttribute("response"));
            default -> null;
        };
        String username = oAuth2Response.getProviderId();
        User existData = userRepository.findByUsername(username);

        Role role = Role.ROLE_USER;
        Type type = switch(registrationId) {
            case "google" -> Type.GOOGLE;
            case "naver" -> Type.NAVER;
            default -> Type.NONE;
        };
        if (existData == null) { // 처음에 들어올때
            User user = new User();
            user.setUsername(username);
            user.setName(oAuth2Response.getName());
            user.setEmail(oAuth2Response.getEmail());
            user.setRole(role);
            user.setType(type);
            user.setPassword(new UserPasswordEncoder("temp_password").getPassword());

            userRepository.save(user); // 우리 db 저장
        }
        else {
            existData.setUsername(username); // 두번째 들어오는 값이면
            existData.setEmail(oAuth2Response.getEmail()); // 다시 들어온 값으로 email 수정

            role = existData.getRole();
            userRepository.save(existData);
        }

        return new CustomUserOAuth2Details(oAuth2Response, role);
    }
}
