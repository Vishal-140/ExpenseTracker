package com.example.ExpenseTracker.service;

import com.example.ExpenseTracker.model.UserModel;
import com.example.ExpenseTracker.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoogleAuthService {

    private final UserRepo userRepo;
    private final JwtService jwtService;

    public String handleGoogleLogin(String email, String name) {

        UserModel user = userRepo.findByEmailId(email);

        // CREATE USER IF FIRST TIME
        if (user == null) {
            user = new UserModel();
            user.setEmailId(email);
            user.setUsername(
                    name != null ? name : email.split("@")[0]
            );
            user.setPassword("GOOGLE_USER");
            userRepo.save(user);
        }

        // RETURN JWT
        return jwtService.generateToken(user.getEmailId());
    }
}
