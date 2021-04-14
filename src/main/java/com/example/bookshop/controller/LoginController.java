package com.example.bookshop.controller;

import com.example.bookshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    UserService userService;

    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
    }
}
