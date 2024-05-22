package com.zerobase.project.controller;

import com.zerobase.project.repository.UserRepository;
import com.zerobase.project.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserRepository userRepository;


    @GetMapping("/home")
    public String home() {

        return "home";
    }
}
