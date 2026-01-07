package com.example.ExpenseTracker.repo;

import com.example.ExpenseTracker.model.IncomeModel;
import com.example.ExpenseTracker.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncomeRepo extends JpaRepository<IncomeModel, Long> {
    List<IncomeModel> findByUser(UserModel user);
}
