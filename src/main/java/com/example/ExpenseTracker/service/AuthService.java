package com.example.ExpenseTracker.service;

import com.example.ExpenseTracker.model.DTO.LoginDto;
import com.example.ExpenseTracker.model.DTO.SignUpDto;
import com.example.ExpenseTracker.model.UserModel;
import com.example.ExpenseTracker.repo.UserRepo;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder encoder;
    private final JwtService jwtService;
    private final GoogleAuthService googleAuthService;

    public UserModel signup(SignUpDto dto) {

        if (userRepo.existsByEmailId(dto.getEmailId())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email already exists"
            );
        }

        UserModel user = new UserModel();
        user.setUsername(dto.getUsername());
        user.setEmailId(dto.getEmailId());
        user.setPassword(encoder.encode(dto.getPassword()));

        return userRepo.save(user);
    }

    public String login(LoginDto dto) {

        UserModel user = userRepo.findByEmailId(dto.getEmailId());

        if (user == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Email not registered"
            );
        }

        if (!encoder.matches(dto.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Incorrect password"
            );
        }

        return jwtService.generateToken(user.getEmailId());
    }


    public String googleLogin(String email) {

        UserModel user = userRepo.findByEmailId(email);

        if (user == null) {
            user = new UserModel();
            user.setEmailId(email);
            user.setUsername(email.split("@")[0]);
            user.setPassword(encoder.encode("GOOGLE_LOGIN"));
            userRepo.save(user);
        }

        return jwtService.generateToken(email);
    }
}
