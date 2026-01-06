package com.example.ExpenseTracker.repo;

import com.example.ExpenseTracker.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserModel, Long> {

    boolean existsByEmailId(String emailId);
    UserModel findByEmailId(String emailId);
}
