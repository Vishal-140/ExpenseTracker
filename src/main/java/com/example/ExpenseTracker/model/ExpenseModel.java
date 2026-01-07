package com.example.ExpenseTracker.model;

import com.example.ExpenseTracker.model.enums.ExpenseCategory;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "expenses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Enumerated(EnumType.STRING)
    private ExpenseCategory category;

    private double amount;

    private LocalDate expenseDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;
}
