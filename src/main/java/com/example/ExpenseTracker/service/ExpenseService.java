package com.example.ExpenseTracker.service;

import com.example.ExpenseTracker.model.DTO.ExpenseDto;
import com.example.ExpenseTracker.model.ExpenseModel;
import com.example.ExpenseTracker.model.UserModel;
import com.example.ExpenseTracker.model.enums.ExpenseCategory;
import com.example.ExpenseTracker.repo.ExpenseRepo;
import com.example.ExpenseTracker.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepo expenseRepo;
    private final UserRepo userRepo;

    public ExpenseModel addExpense(ExpenseDto dto, String email) {

        UserModel user = userRepo.findByEmailId(email);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }

        ExpenseModel expense = new ExpenseModel();
        expense.setDescription(dto.getDescription());
        expense.setCategory(dto.getCategory());
        expense.setAmount(dto.getAmount());
        expense.setExpenseDate(dto.getExpenseDate());
        expense.setUser(user);

        return expenseRepo.save(expense);
    }

    public List<ExpenseModel> getAllExpenses(String email) {
        UserModel user = userRepo.findByEmailId(email);
        return expenseRepo.findByUser(user);
    }

    public void deleteExpense(Long id, String email) {
        ExpenseModel expense = expenseRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Expense not found"));

        if (!expense.getUser().getEmailId().equals(email)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not allowed");
        }

        expenseRepo.delete(expense);
    }

    public ExpenseModel getExpenseById(Long id, String email) {
        ExpenseModel expense = expenseRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Expense not found"));

        if (!expense.getUser().getEmailId().equals(email)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
        }
        return expense;
    }

    public ExpenseModel updateExpense(Long id, ExpenseDto dto, String email) {
        ExpenseModel expense = expenseRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Expense not found"));

        if (!expense.getUser().getEmailId().equals(email)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
        }
        // update fields
        expense.setDescription(dto.getDescription());
        expense.setCategory(dto.getCategory());
        expense.setAmount(dto.getAmount());
        expense.setExpenseDate(dto.getExpenseDate());

        return expenseRepo.save(expense);
    }

    // SORT + FILTER
    public List<ExpenseModel> getExpenses(
            String email,
            ExpenseCategory category,
            LocalDate startDate,
            LocalDate endDate,
            Double minAmount,
            Double maxAmount,
            String sortBy,
            String order
    ) {
        UserModel user = getUserByEmail(email);

        Sort sort = order.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        if (category != null) {
            return expenseRepo.findByUser_UserIdAndCategory(
                    user.getUserId(), category, sort
            );
        }

        if (startDate != null && endDate != null) {
            return expenseRepo.findByUser_UserIdAndExpenseDateBetween(
                    user.getUserId(), startDate, endDate, sort
            );
        }

        if (minAmount != null && maxAmount != null) {
            return expenseRepo.findByUser_UserIdAndAmountBetween(
                    user.getUserId(), minAmount, maxAmount, sort
            );
        }

        return expenseRepo.findByUser_UserId(user.getUserId(), sort);
    }

    private UserModel getUserByEmail(String email) {
        UserModel user = userRepo.findByEmailId(email);
        if (user == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "User not found"
            );
        }
        return user;
    }

}