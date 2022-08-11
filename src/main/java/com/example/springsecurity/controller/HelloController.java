package com.example.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 崔令雨
 * @since 2022/8/11
 */
@RestController
public class HelloController {

    @GetMapping("hello")
    public String login() {
        return "hello";
    }

}
