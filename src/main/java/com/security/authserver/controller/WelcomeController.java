package com.security.authserver.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/welcome")
    public  String sayWelcome () {
        return "Welcome to Spring Application with security";
    }

    @GetMapping("/test1")
    public  String test1 () {
        return "Welcome to Spring Application with security /test1";
    }

    @GetMapping("/test2")
    public  String test2 () {
        return "Welcome to Spring Application with security /test2";
    }

    @GetMapping("/help")
    public  String help () {
        return "HELP /help";
    }

} 
