package com.my.studyrecordsecurity.security.userSecurity.service;

import com.my.studyrecordsecurity.security.userSecurity.domain.CustomUserDetails;
import com.my.studyrecordsecurity.security.userSecurity.domain.CustomUserOAuth2Details;
import com.my.studyrecordsecurity.user.domain.User;
import com.my.studyrecordsecurity.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));

        if(user != null){
            // 커스텀 유저 디테일 클래스를 만들어서 우리 멤버 객체 주입
            return new CustomUserDetails(user);
        }
        return null;
    }
}
