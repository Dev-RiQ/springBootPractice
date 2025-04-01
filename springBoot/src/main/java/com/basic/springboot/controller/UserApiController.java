package com.basic.springboot.controller;

import com.basic.springboot.domain.request.UserRequestDTO;
import com.basic.springboot.domain.response.UserResponseDTO;
import com.basic.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @GetMapping({"","/"})
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.findAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @PostMapping({"","/"})
    public ResponseEntity<Map<String, Object>> addOneUser(@RequestBody UserRequestDTO userRequestDTO) {
        log.info("addOneUser: userRequestDTO: {}", userRequestDTO);
        Map<String, Object> response = new HashMap<>();
        try {
            userService.saveUser(userRequestDTO);
            response.put("status",HttpStatus.CREATED.value());
            response.put("message","user created successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status",HttpStatus.NOT_FOUND.value());
            response.put("message","user not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
