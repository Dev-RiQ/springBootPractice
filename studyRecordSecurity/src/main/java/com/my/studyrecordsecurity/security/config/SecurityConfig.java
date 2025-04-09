package com.my.studyrecordsecurity.security.config;

import com.my.studyrecordsecurity.security.userSecurity.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer configure() { // 스프링 시큐리티 기능 비활성화
        return (web) -> web.ignoring()
                .requestMatchers(
                        new AntPathRequestMatcher("/static/**"),
                        new AntPathRequestMatcher("/img/**"),
                        new AntPathRequestMatcher("/css/**"),
                        new AntPathRequestMatcher("/js/**")
                );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//        http
//                .csrf(AbstractHttpConfigurer::disable);
//        http
//                .formLogin(AbstractHttpConfigurer::disable);
        // formLogin disable 시 자동 popup 되는 것 해제
        http
                .httpBasic(AbstractHttpConfigurer::disable);
        // default oauth2Login 창
//        http
//                .oauth2Login(Customizer.withDefaults())
        http
                .formLogin(form->{
                            form.loginPage("/login")
                                    .loginProcessingUrl("/login")
                                    .failureUrl("/login?error")
                                    .defaultSuccessUrl("/", true)
                                    .permitAll();

                        }
                )
                .oauth2Login((oauth2) -> oauth2
                        .loginPage("/login")
                        .userInfoEndpoint((userInfoEndpointConfig) ->
                                userInfoEndpointConfig.userService(customOAuth2UserService))
                )
                .logout(
                        (logoutConfig) -> logoutConfig.logoutSuccessUrl("/"));
        http
                .authorizeHttpRequests((auth) -> {
                    auth
                            .requestMatchers("/","/oauth","/oauth2/**", "/login","/login?error", "/sign-up", "/user", "/user/").permitAll()
                            .anyRequest().authenticated();
                });
        http
                .sessionManagement((auth) -> auth
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true));

        http
                .sessionManagement((auth) -> auth
                        .sessionFixation().changeSessionId());

        return http.build();
    }
}
