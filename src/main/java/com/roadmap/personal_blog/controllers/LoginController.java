package com.roadmap.personal_blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/logout")
    public String login() {
        return "login";
    }

}
