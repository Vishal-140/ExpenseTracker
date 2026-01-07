package com.example.ExpenseTracker.model.DTO;

import com.example.ExpenseTracker.model.enums.IncomeSource;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class IncomeDto {

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Source is required")
    private IncomeSource source;

    @Positive(message = "Amount must be greater than 0")
    private double amount;

    @NotNull(message = "Date is required")
    private LocalDate incomeDate;
}
