package com.zerobase.project.controller;

import com.zerobase.project.model.UserCreateForm;
import com.zerobase.project.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signup(UserCreateForm userCreateForm) {

        return "signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup_form";
        }

        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "signup_form";
        }

        userService.create(userCreateForm);

        return "redirect:/home";
    }

    @GetMapping("/login")
    public String login() {
        return "login_form";
    }

    @ModelAttribute("userRoles")
    public List<UserRole> deliveryCodes() {
        List<UserRole> userRoles = new ArrayList<>();
        userRoles.add(new UserRole("USER", "고객"));
        userRoles.add(new UserRole("PARTNER", "파트너"));
        return userRoles;
    }

    @Data
    @AllArgsConstructor
    public class UserRole {
        private String code;
        private String displayName;
    }
}
