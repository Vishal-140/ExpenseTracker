package com.example.ExpenseTracker.controller;

import com.example.ExpenseTracker.model.DTO.ExpenseDto;
import com.example.ExpenseTracker.model.ExpenseModel;
import com.example.ExpenseTracker.model.enums.ExpenseCategory;
import com.example.ExpenseTracker.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public ExpenseModel addExpense(@Valid @RequestBody ExpenseDto dto, Authentication authentication) {
        return expenseService.addExpense(dto, authentication.getName());
    }

//    @GetMapping()
//    public List<ExpenseModel> getExpenses(Authentication authentication) {
//        return expenseService.getAllExpenses(authentication.getName());
//    }

    @GetMapping("/{id}")
    public ExpenseModel getExpenseById(@PathVariable Long id, Authentication authentication) {
        return expenseService.getExpenseById(id, authentication.getName());
    }

    @PutMapping("/{id}")
    public ExpenseModel updateExpense(@PathVariable Long id, @Valid @RequestBody ExpenseDto dto, Authentication authentication) {
        return expenseService.updateExpense(id, dto, authentication.getName());
    }

    @DeleteMapping("/{id}")
    public String deleteExpense(@PathVariable Long id, Authentication authentication) {
        expenseService.deleteExpense(id, authentication.getName());
        return "Expense deleted successfully";
    }

    // SORT + FILTER
    @GetMapping
    public List<ExpenseModel> getExpenses(
            @RequestParam(required = false) ExpenseCategory category,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate startDate,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate endDate,

            @RequestParam(required = false) Double minAmount,
            @RequestParam(required = false) Double maxAmount,

            @RequestParam(defaultValue = "expenseDate") String sortBy,
            @RequestParam(defaultValue = "asc") String order,

            Authentication authentication
    ) {
        return expenseService.getExpenses(
                authentication.getName(),
                category,
                startDate,
                endDate,
                minAmount,
                maxAmount,
                sortBy,
                order
        );
    }

}
