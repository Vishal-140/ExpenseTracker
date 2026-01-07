package com.example.ExpenseTracker.model;

import com.example.ExpenseTracker.model.enums.IncomeSource;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "income")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Enumerated(EnumType.STRING)
    private IncomeSource source;

    private double amount;

    private LocalDate incomeDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;
}
