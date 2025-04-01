package com.basic.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String home() {
        return "home";
    }
    @GetMapping("/msg")
    @ResponseBody   // viewResolver 가 아니라 HttpMessageConverter
    public String msg(){
        return "hello~~";  // HttpMessageConverter -> String
    }
    @GetMapping("/obj")
    @ResponseBody
    public Test obj(){
        Test test = new Test("개똥이", 1001);
        return test; // HttpMessageConverter -> json
    }

    public static class Test{
        String name;
        int number;
        public Test(String name, int number){
            this.name = name;
            this.number = number;
        }
        public String getName() {
            return name;
        }
        public int getNumber() {return number;}
        public void setName(String name) {
            this.name = name;
        }
        public void setNumber(int number) {
            this.number = number;
        }
    }
}
