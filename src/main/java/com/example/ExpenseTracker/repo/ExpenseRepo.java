package com.example.ExpenseTracker.repo;

import com.example.ExpenseTracker.model.ExpenseModel;
import com.example.ExpenseTracker.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepo extends JpaRepository<ExpenseModel, Long> {

    List<ExpenseModel> findByUser(UserModel user);
}
