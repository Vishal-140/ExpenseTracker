package com.example.ExpenseTracker.model.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDto {

    @NotBlank(message = "Email is required")
    private String emailId;

    @NotBlank(message = "Password is required")
    private String password;
}
