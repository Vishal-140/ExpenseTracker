package com.example.ExpenseTracker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/")
    public ResponseEntity<String> homePage() {
        return ResponseEntity.ok("Welcome to Expense Tracker Home Page");
    }
}
