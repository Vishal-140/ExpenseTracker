package com.example.ExpenseTracker.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "user_name", nullable = false)
    private String username;

    @Column(name = "email_id", unique = true, nullable = false)
    private String emailId;

    @Column(nullable = false)
    private String password;
}
