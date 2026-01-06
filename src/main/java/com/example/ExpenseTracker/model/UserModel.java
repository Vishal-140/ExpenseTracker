package com.example.ExpenseTracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Size(min = 4, max = 50, message = "Username must be between 4 and 50 characters")

    @Column(name = "user_name")
    private String username;

    @Column(name = "email_id",unique = true)
    @Email
    private String emailId;

    @Size(min=6,max=512)
    private String password;

}
