package com.Mbarca.expenses.controller;

import com.Mbarca.expenses.dto.request.ExpenseRequestDto;
import com.Mbarca.expenses.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }
@PostMapping
    public ResponseEntity<String> createExpenseHandler(@RequestBody ExpenseRequestDto expenseRequestDto){
        String response = expenseService.createExpense(expenseRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
