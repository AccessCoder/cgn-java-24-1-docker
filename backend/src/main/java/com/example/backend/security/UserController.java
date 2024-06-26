package com.example.backend.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/me")
    public String getMe(@AuthenticationPrincipal OAuth2User user){
        return user.getName();
    }

    @GetMapping("/me2")
    public String getMe(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
