package com.example.ExpenseTracker.model.DTO;

import com.example.ExpenseTracker.model.enums.ExpenseCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpenseDto {

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Category is required")
    private ExpenseCategory category;

    @Positive(message = "Amount must be greater than 0")
    private double amount;

    @NotNull(message = "Date is required")
    private LocalDate expenseDate;
}
