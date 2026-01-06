package com.example.ExpenseTracker.service;

import com.example.ExpenseTracker.model.DTO.LoginDto;
import com.example.ExpenseTracker.model.DTO.SignUpDto;
import com.example.ExpenseTracker.model.UserModel;
import com.example.ExpenseTracker.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserModel handleSignup(SignUpDto dto) {

        if (userRepo.existsByEmailId(dto.getEmailId())) {
            throw new RuntimeException("Email already exists");
        }

        UserModel user = new UserModel();
        user.setUsername(dto.getUsername());
        user.setEmailId(dto.getEmailId());
        user.setPassword(dto.getPassword());

        return userRepo.save(user);
    }

    public UserModel handleLogin(LoginDto dto) {

        UserModel user = userRepo.findByEmailId(dto.getEmailId());

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Wrong password");
        }

        return user;
    }


}
