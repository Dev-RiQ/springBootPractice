package com.my.studyrecordsecurity.common.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String mainPage(){
        return "home";
    }
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
    @GetMapping("/sign-up")
    public String joinPage(){
        return "user/join";
    }
    @GetMapping("/oauth")
    public String login(Authentication authentication){
        if(authentication == null){
            return "login";
        }
        return "home";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        return "redirect:/";
    }

}
