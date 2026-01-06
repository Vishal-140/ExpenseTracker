package com.example.ExpenseTracker.controller;

import com.example.ExpenseTracker.model.DTO.LoginDto;
import com.example.ExpenseTracker.model.DTO.SignUpDto;
import com.example.ExpenseTracker.model.UserModel;
import com.example.ExpenseTracker.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public UserModel signup(@Valid @RequestBody SignUpDto dto) {
        return authService.signup(dto);
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginDto dto) {
        return authService.login(dto);
    }
}
