package com.example.ExpenseTracker.controller;

import com.example.ExpenseTracker.model.DTO.IncomeDto;
import com.example.ExpenseTracker.model.IncomeModel;
import com.example.ExpenseTracker.service.IncomeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/income")
@RequiredArgsConstructor
public class IncomeController {

    private final IncomeService incomeService;

    @PostMapping
    public IncomeModel addIncome(@Valid @RequestBody IncomeDto dto, Authentication authentication) {
        return incomeService.addIncome(dto, authentication.getName());
    }

    @GetMapping
    public List<IncomeModel> getIncome(Authentication authentication) {
        return incomeService.getAllIncome(authentication.getName());
    }

    @GetMapping("/{id}")
    public IncomeModel getIncomeById(@PathVariable Long id, Authentication authentication) {
        return incomeService.getIncomeById(id, authentication.getName());
    }

    @PutMapping("/{id}")
    public IncomeModel updateIncome(@PathVariable Long id, @Valid @RequestBody IncomeDto dto,
                                    Authentication authentication) {
        return incomeService.updateIncome(id, dto, authentication.getName());
    }


    @DeleteMapping("/{id}")
    public String deleteIncome(@PathVariable Long id, Authentication authentication) {
        incomeService.deleteIncome(id, authentication.getName());
        return "Income deleted successfully";
    }
}
