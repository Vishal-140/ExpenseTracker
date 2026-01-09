package com.example.ExpenseTracker.controller;

import com.example.ExpenseTracker.model.DTO.LoginDto;
import com.example.ExpenseTracker.model.DTO.SignUpDto;
import com.example.ExpenseTracker.model.UserModel;
import com.example.ExpenseTracker.service.AuthService;
import com.example.ExpenseTracker.service.GoogleAuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final GoogleAuthService googleAuthService;

    @PostMapping("/signup")
    public UserModel signup(@Valid @RequestBody SignUpDto dto) {
        return authService.signup(dto);
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginDto dto) {
        return authService.login(dto);
    }

    //  GOOGLE SUCCESS
    @GetMapping("/google/success")
    public void googleSuccess(
            @AuthenticationPrincipal OAuth2User user,
            HttpServletResponse response
    ) throws IOException {

        String email = user.getAttribute("email");
        String name = user.getAttribute("name");

        String token = googleAuthService.handleGoogleLogin(email, name);

        response.sendRedirect(
                "http://localhost:5173/oauth-success?token=" + token
        );
    }
}
