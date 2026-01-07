package com.example.ExpenseTracker.controller;

import com.example.ExpenseTracker.service.PnlService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/pnl")
@RequiredArgsConstructor
public class PnlController {

    private final PnlService pnlService;

    @GetMapping
    public Map<String, Double> getPnl(Authentication authentication) {
        return pnlService.calculatePnl(authentication.getName());
    }
}
