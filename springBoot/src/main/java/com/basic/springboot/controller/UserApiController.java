package com.basic.springboot.controller;

import com.basic.springboot.domain.request.UserRequestDTO;
import com.basic.springboot.domain.response.UserResponseDTO;
import com.basic.springboot.repository.UserSummary;
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

    @GetMapping("/{username}")
    public ResponseEntity<Map<String, Object>> getUser(@PathVariable String username){
        log.trace("get user : {}",username);
        Map<String,Object> response = new HashMap<>();
        try {
            UserResponseDTO user = userService.findUserById(username);
            response.put("status",HttpStatus.OK.value());
            response.put("data",user);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            response.put("status",HttpStatus.NOT_FOUND.value());
            response.put("message","user not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
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
            response.put("status",HttpStatus.BAD_REQUEST.value());
            response.put("message",e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping({"","/"})
    public ResponseEntity<Map<String, Object>> updateUser(@ModelAttribute UserRequestDTO userRequestDTO) {
        log.info("updateUser: userRequestDTO: {}", userRequestDTO);
        Map<String, Object> response = new HashMap<>();
        try {
            userService.updateUser(userRequestDTO);
            response.put("status",HttpStatus.OK.value());
            response.put("message","user updated successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status",HttpStatus.BAD_REQUEST.value());
            response.put("message",e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping({"","/"})
    public ResponseEntity<Map<String, Object>> deleteUser(@ModelAttribute UserRequestDTO userRequestDTO) {
        log.info("deleteUser: userRequestDTO: {}", userRequestDTO);
        Map<String, Object> response = new HashMap<>();
        try {
            userService.deleteUser(userRequestDTO);
            response.put("status",HttpStatus.OK.value());
            response.put("message","user deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status",HttpStatus.BAD_REQUEST.value());
            response.put("message",e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/summary")
    public List<UserSummary> getUserSummary(){
        return userService.findAllUserSummary();
    }
}
