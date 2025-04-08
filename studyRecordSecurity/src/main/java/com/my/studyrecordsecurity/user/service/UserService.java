package com.my.studyrecordsecurity.user.service;

import com.my.studyrecordsecurity.user.controller.request.LoginUserRequest;
import com.my.studyrecordsecurity.user.controller.request.UpdateUserRequest;
import com.my.studyrecordsecurity.user.domain.User;
import com.my.studyrecordsecurity.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
}

