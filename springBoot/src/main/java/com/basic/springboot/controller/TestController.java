package com.basic.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        log.info("test");
        return "test";
    }
    @GetMapping("/test/param")
    public String test(@RequestParam("name") String name, @RequestParam("age") String age) {
        return "test param name = " + name + ", age = " + age;
    }
}
