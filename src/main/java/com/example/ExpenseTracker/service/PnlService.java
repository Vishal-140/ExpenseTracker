package com.example.ExpenseTracker.service;

import com.example.ExpenseTracker.model.ExpenseModel;
import com.example.ExpenseTracker.model.IncomeModel;
import com.example.ExpenseTracker.model.UserModel;
import com.example.ExpenseTracker.repo.ExpenseRepo;
import com.example.ExpenseTracker.repo.IncomeRepo;
import com.example.ExpenseTracker.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PnlService {

    private final UserRepo userRepo;
    private final ExpenseRepo expenseRepo;
    private final IncomeRepo incomeRepo;

    public Map<String, Double> calculatePnl(String email) {

        UserModel user = userRepo.findByEmailId(email);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }

        List<ExpenseModel> expenses = expenseRepo.findByUser(user);
        List<IncomeModel> incomes = incomeRepo.findByUser(user);

        double totalExpense = expenses.stream()
                .mapToDouble(ExpenseModel::getAmount)
                .sum();

        double totalIncome = incomes.stream()
                .mapToDouble(IncomeModel::getAmount)
                .sum();

        double pnl = totalIncome - totalExpense;

        Map<String, Double> result = new HashMap<>();
        result.put("totalIncome", totalIncome);
        result.put("totalExpense", totalExpense);
        result.put("pnl", pnl);

        return result;
    }
}
