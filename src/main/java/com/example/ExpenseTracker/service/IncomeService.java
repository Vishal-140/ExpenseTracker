package com.example.ExpenseTracker.service;

import com.example.ExpenseTracker.model.DTO.IncomeDto;
import com.example.ExpenseTracker.model.IncomeModel;
import com.example.ExpenseTracker.model.UserModel;
import com.example.ExpenseTracker.repo.IncomeRepo;
import com.example.ExpenseTracker.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IncomeService {

    private final IncomeRepo incomeRepo;
    private final UserRepo userRepo;

    public IncomeModel addIncome(IncomeDto dto, String email) {
        UserModel user = userRepo.findByEmailId(email);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }

        IncomeModel income = new IncomeModel();
        income.setDescription(dto.getDescription());
        income.setSource(dto.getSource());
        income.setAmount(dto.getAmount());
        income.setIncomeDate(dto.getIncomeDate());
        income.setUser(user);

        return incomeRepo.save(income);
    }

    public List<IncomeModel> getAllIncome(String email) {
        UserModel user = userRepo.findByEmailId(email);
        return incomeRepo.findByUser(user);
    }

    public IncomeModel getIncomeById(Long id, String email) {
        IncomeModel income = incomeRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Income not found"));

        if (!income.getUser().getEmailId().equals(email)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not allowed");
        }

        return income;
    }

    public IncomeModel updateIncome(Long id, IncomeDto dto, String email) {

        IncomeModel income = incomeRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Income not found"));

        // check ownership
        if (!income.getUser().getEmailId().equals(email)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not allowed");
        }

        income.setDescription(dto.getDescription());
        income.setSource(dto.getSource());
        income.setAmount(dto.getAmount());
        income.setIncomeDate(dto.getIncomeDate());

        return incomeRepo.save(income);
    }


    public void deleteIncome(Long id, String email) {
        IncomeModel income = getIncomeById(id, email);
        incomeRepo.delete(income);
    }
}
