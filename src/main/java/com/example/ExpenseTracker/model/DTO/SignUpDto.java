package com.example.ExpenseTracker.model.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpDto {

    @NotBlank(message = "Username is required")
    @Size(min = 4, max = 50, message = "Username should be between 4 to 50 characters")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Provide a valid email")
    private String emailId;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Minimum password length is 6")
    private String password;
}
