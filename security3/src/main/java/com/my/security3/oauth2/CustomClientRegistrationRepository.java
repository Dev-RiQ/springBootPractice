package com.my.security3.oauth2;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

@Configuration
@RequiredArgsConstructor
public class CustomClientRegistrationRepository {
    private final SocialClientRegistration socialClientRegistration;

    // 소셜 객체 2개 뿐이라 인메모리로 저장
    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(socialClientRegistration.googleClientRegistration()
                                                        , socialClientRegistration.naverClientRegistration());
    }
}
