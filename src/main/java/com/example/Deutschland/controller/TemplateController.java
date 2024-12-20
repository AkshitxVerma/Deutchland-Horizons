package com.example.Deutschland.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TemplateController {
    


    @GetMapping("/login")
    public String login() {
        return "login"; // Return the Thymeleaf template login.html
    }
    
}
