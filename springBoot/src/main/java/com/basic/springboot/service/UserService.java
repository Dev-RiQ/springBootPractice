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

    public UserResponseDTO findUserById(String username) throws Exception {
        return new UserResponseDTO(userRepository.findById(username).orElseThrow());
    }

    @Transactional
    public UserEntity saveUser(UserRequestDTO userRequestDTO) throws Exception {
        if(userRepository.findById(userRequestDTO.getUsername()).isPresent()){
            throw new Exception("Username already exists");
        }
        if(userRepository.findByEmail(userRequestDTO.getEmail()).isPresent()){
            throw new Exception("email already exists");
        }
        return userRepository.save(userRequestDTO.toUserEntity());
    }

    @Transactional
    public void updateUser(UserRequestDTO userRequestDTO) throws Exception {
        if(!userRepository.existsByUsernameAndPassword(userRequestDTO.getUsername(), userRequestDTO.getPassword())){
            throw new Exception("Username or password does not exist");
        }
        UserEntity entity = userRepository.findById(userRequestDTO.getUsername()).orElseThrow();
        entity.update(userRequestDTO);
        userRepository.save(entity);
    }

    @Transactional
    public void deleteUser(UserRequestDTO userRequestDTO) throws Exception {
        if(!userRepository.existsByUsernameAndPassword(userRequestDTO.getUsername(), userRequestDTO.getPassword())){
            throw new Exception("Username or password does not exist");
        }
        userRepository.deleteById(userRequestDTO.getUsername());
    }
}
