package com.my.security3.config;

import com.my.security3.oauth2.CustomClientRegistrationRepository;
import com.my.security3.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomClientRegistrationRepository customClientRegistrationRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable);
        // 직접 로그인 구현 x
        http
                .formLogin(AbstractHttpConfigurer::disable);
        // formLogin disable 시 자동 popup 되는 것 해제
        http
                .httpBasic(AbstractHttpConfigurer::disable);
        // default oauth2Login 창
//        http
//                .oauth2Login(Customizer.withDefaults());
        http
                .oauth2Login((oauth2) -> oauth2
                        .loginPage("/login")
                        .clientRegistrationRepository(customClientRegistrationRepository.clientRegistrationRepository())
                        .userInfoEndpoint((userInfoEndpointConfig) ->
                                userInfoEndpointConfig.userService(customOAuth2UserService))
                );
        http
                .authorizeHttpRequests((auth) -> {
                    auth
                            .requestMatchers("/", "/oauth2/**", "/login").permitAll()
                            .anyRequest().authenticated();
                });

        return http.build();
    }
}
