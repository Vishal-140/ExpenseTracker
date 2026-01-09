package com.example.ExpenseTracker.repo;

import com.example.ExpenseTracker.model.IncomeModel;
import com.example.ExpenseTracker.model.UserModel;
import com.example.ExpenseTracker.model.enums.IncomeSource;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface IncomeRepo extends JpaRepository<IncomeModel, Long> {
    List<IncomeModel> findByUser(UserModel user);

    List<IncomeModel> findByUser_UserId(
            Long userId,
            Sort sort
    );

    List<IncomeModel> findByUser_UserIdAndSource(
            Long userId,
            IncomeSource source,
            Sort sort
    );

    List<IncomeModel> findByUser_UserIdAndIncomeDateBetween(
            Long userId,
            LocalDate startDate,
            LocalDate endDate,
            Sort sort
    );

    List<IncomeModel> findByUser_UserIdAndAmountBetween(
            Long userId,
            Double min,
            Double max,
            Sort sort
    );
}
