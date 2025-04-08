package com.my.studyrecordsecurity.user.controller;

import com.my.studyrecordsecurity.studyRecord.service.StudyRecordService;
import com.my.studyrecordsecurity.user.controller.request.AddUserRequest;
import com.my.studyrecordsecurity.user.controller.request.LoginUserRequest;
import com.my.studyrecordsecurity.user.controller.request.UpdateUserRequest;
import com.my.studyrecordsecurity.user.controller.response.UserViewResponse;
import com.my.studyrecordsecurity.user.domain.User;
import com.my.studyrecordsecurity.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.jaas.SecurityContextLoginModule;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final StudyRecordService studyRecordService;

    @GetMapping({"","/{id}"})
    public String user(@PathVariable(required = false) Long id, Model model) {
        try{
            User user = userService.findUserById(id);
            model.addAttribute("user", user);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "user/join";
    }

    @GetMapping("/list")
    public String list(Model model) {
        try{
            List<User> users = userService.findAll();
            List<UserViewResponse> userViewResponses = users.stream().map(UserViewResponse::new).toList();
            model.addAttribute("users",userViewResponses);
        }catch (Exception e) {
            log.error(e.getMessage());
        }
        return "user/list";
    }

    @PostMapping({"","/"})
    public String join(AddUserRequest request) {
        userService.insert(new AddUserRequest().toEntity(request));
        return "redirect:/user/list";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, UpdateUserRequest request) {
        try{
            userService.update(id, request);
        }catch (Exception e) {
            log.error(e.getMessage());
        }
        return "redirect:/user/list";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try{
            studyRecordService.deleteAllByUserId(id);
            userService.delete(id);
        }catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}