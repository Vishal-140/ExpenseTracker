package com.example.ExpenseTracker.controller;

import com.example.ExpenseTracker.model.DTO.LoginDto;
import com.example.ExpenseTracker.model.DTO.SignUpDto;
import com.example.ExpenseTracker.model.UserModel;
import com.example.ExpenseTracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public UserModel handleSignup(@RequestBody SignUpDto dto) {
        return userService.handleSignup(dto);
    }
    @GetMapping("/signup")
    public String signupTest() {
        return "Signup API is working";
    }

    @PostMapping("/login")
    public UserModel handleLogin(@RequestBody LoginDto dto) {
        return userService.handleLogin(dto);
    }

}

