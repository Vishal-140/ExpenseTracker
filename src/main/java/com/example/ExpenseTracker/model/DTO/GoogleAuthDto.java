package com.example.ExpenseTracker.model.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GoogleAuthDto {
    @NotBlank
    private String token;
}
