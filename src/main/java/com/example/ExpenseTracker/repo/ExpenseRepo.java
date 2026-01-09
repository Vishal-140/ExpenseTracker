package com.example.ExpenseTracker.repo;

import com.example.ExpenseTracker.model.ExpenseModel;
import com.example.ExpenseTracker.model.UserModel;
import com.example.ExpenseTracker.model.enums.ExpenseCategory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepo extends JpaRepository<ExpenseModel, Long> {

    List<ExpenseModel> findByUser(UserModel user);

    List<ExpenseModel> findByUser_UserId(
            Long userId,
            Sort sort
    );

    List<ExpenseModel> findByUser_UserIdAndCategory(
            Long userId,
            ExpenseCategory category,
            Sort sort
    );

    List<ExpenseModel> findByUser_UserIdAndExpenseDateBetween(
            Long userId,
            LocalDate startDate,
            LocalDate endDate,
            Sort sort
    );

    List<ExpenseModel> findByUser_UserIdAndAmountBetween(
            Long userId,
            Double min,
            Double max,
            Sort sort
    );
}
