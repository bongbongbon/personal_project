package com.zerobase.project.service;

import com.zerobase.project.model.UserCreateForm;
import com.zerobase.project.repository.UserRepository;
import com.zerobase.project.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create(UserCreateForm userCreateForm) {
        SiteUser user = new SiteUser();
        user.setUsername(userCreateForm.getUsername());
        user.setEmail(userCreateForm.getEmail());
        user.setPassword(passwordEncoder.encode(userCreateForm.getPassword1()));
        user.setUserRole(userCreateForm.getUserRole());
        this.userRepository.save(user);
        return user;
    }
}
