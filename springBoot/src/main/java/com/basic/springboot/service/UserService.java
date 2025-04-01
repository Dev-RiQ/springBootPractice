package com.basic.springboot.service;

import com.basic.springboot.domain.UserEntity;
import com.basic.springboot.domain.request.UserRequestDTO;
import com.basic.springboot.domain.response.UserResponseDTO;
import com.basic.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // 자동 의존성 주입해줌
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
    public List<UserResponseDTO> findAllUsers() {
        List<UserEntity> list = userRepository.findAll();
        return list.stream().map(UserResponseDTO::new).collect(Collectors.toList());
    }

    public UserEntity findUserById(String username) {
        return userRepository.findById(username).orElse(null);
    }

    @Transactional
    public UserEntity saveUser(UserRequestDTO userRequestDTO) throws Exception {
        if(findUserById(userRequestDTO.getUsername()) != null){
            throw new Exception("Username already exists");
        }
        return userRepository.save(userRequestDTO.toUserEntity());
    }
}
