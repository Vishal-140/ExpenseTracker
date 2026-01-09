package com.example.ExpenseTracker.controller;

import com.example.ExpenseTracker.model.DTO.IncomeDto;
import com.example.ExpenseTracker.model.IncomeModel;
import com.example.ExpenseTracker.model.enums.IncomeSource;
import com.example.ExpenseTracker.service.IncomeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

//    @GetMapping
//    public List<IncomeModel> getIncome(Authentication authentication) {
//        return incomeService.getAllIncome(authentication.getName());
//    }

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

    // SORT + FILTER
    @GetMapping
    public List<IncomeModel> getIncome(
            @RequestParam(required = false) IncomeSource source,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate startDate,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate endDate,

            @RequestParam(required = false) Double minAmount,
            @RequestParam(required = false) Double maxAmount,

            @RequestParam(defaultValue = "incomeDate") String sortBy,
            @RequestParam(defaultValue = "asc") String order,

            Authentication authentication
    ) {
        return incomeService.getIncome(
                authentication.getName(),
                source,
                startDate,
                endDate,
                minAmount,
                maxAmount,
                sortBy,
                order
        );
    }

}
