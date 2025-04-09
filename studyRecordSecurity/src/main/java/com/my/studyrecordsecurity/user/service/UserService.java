package com.my.studyrecordsecurity.user.service;

import com.my.studyrecordsecurity.security.userSecurity.dto.CustomUserDetails;
import com.my.studyrecordsecurity.security.userSecurity.dto.CustomUserOAuth2Details;
import com.my.studyrecordsecurity.security.userSecurity.dto.OAuth2Response;
import com.my.studyrecordsecurity.user.controller.request.UpdateUserRequest;
import com.my.studyrecordsecurity.user.domain.User;
import com.my.studyrecordsecurity.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() throws NoSuchElementException {
        List<User> users = userRepository.findAll();
        if(users.isEmpty()){
            throw new NoSuchElementException();
        }
        return users;
    }

    public User findUserById(Long id) throws NoSuchElementException {
        return userRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void insert(User entity) {
        userRepository.save(entity);
    }

    @Transactional
    public void update(Long id, UpdateUserRequest request) throws NoSuchElementException {
        User user = userRepository.findById(id).orElseThrow();
        user.update(request);
        userRepository.save(user);
    }

    @Transactional
    public void delete(Long id) throws NoSuchElementException {
        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
    }

    public User getLoginUser() {
        User user;
        try{
            OAuth2Response oAuth2Response = ((CustomUserOAuth2Details) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getOAuth2Response();
            user = (User) userRepository.findByUsernameAndEmailAndName(
                    oAuth2Response.getProviderId(),
                    oAuth2Response.getEmail(),
                    oAuth2Response.getName()
            ).orElseThrow();
        }catch (Exception e){
            user = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        }
        return user;
    }
}

